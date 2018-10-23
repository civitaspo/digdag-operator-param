package pro.civitaspo.digdag.plugin.param.operator
import java.nio.charset.StandardCharsets.UTF_8

import com.google.common.collect.ImmutableList
import io.digdag.client.config.{Config, ConfigKey}
import io.digdag.spi.{OperatorContext, TaskResult, TemplateEngine}

class ParamEvalOperator(operatorName: String, context: OperatorContext, templateEngine: TemplateEngine)
    extends AbstractParamOperator(operatorName, context, templateEngine) {

  protected val key: String = params.get("_command", classOf[String])

  override def runTask(): TaskResult = {
    val evaluated: Config = eval(params)
    val paramsToStore: Config = cf.create()

    val elems: Seq[String] = key.split("\\.")
    val parents: Seq[String] = elems.reverse.tail.reverse
    val child: String = elems.last

    if (parents.isEmpty) paramsToStore.set(child, evaluated.get(child, classOf[Object]))
    else {
      val getter = parents.foldLeft(evaluated) { (nested: Config, k: String) => nested.getNested(k)
      }
      val setter = parents.foldLeft(paramsToStore) { (nested: Config, k: String) => nested.getNestedOrSetEmpty(k)
      }
      setter.set(child, getter.get(child, classOf[Object]))
    }

    val builder = TaskResult.defaultBuilder(cf)
    builder.resetStoreParams(ImmutableList.of(ConfigKey.parse(key)))
    builder.storeParams(paramsToStore)
    builder.build()
  }

  protected def eval(params: Config): Config = {
    val tmpFile: String = workspace.createTempFile("param_eval", ".json")

    val writer = workspace.newBufferedWriter(tmpFile, UTF_8)
    try writer.write(params.toString)
    finally writer.close()

    val content = workspace.templateFile(templateEngine, tmpFile, UTF_8, params)
    cf.fromJsonString(content)
  }
}

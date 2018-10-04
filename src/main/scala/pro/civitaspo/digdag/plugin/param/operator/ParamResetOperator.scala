package pro.civitaspo.digdag.plugin.param.operator
import com.google.common.collect.ImmutableList
import io.digdag.client.config.{Config, ConfigKey}
import io.digdag.spi.{OperatorContext, TaskResult, TemplateEngine}

class ParamResetOperator(operatorName: String, context: OperatorContext, systemConfig: Config, templateEngine: TemplateEngine)
  extends AbstractParamOperator(operatorName, context, systemConfig, templateEngine) {

  protected val resetKey: String = params.get("_command", classOf[String])

  override def runTask(): TaskResult = {
    val builder = TaskResult.defaultBuilder(request)
    builder.resetStoreParams(ImmutableList.of(ConfigKey.parse(resetKey)))
    builder.build()
  }

}

package pro.civitaspo.digdag.plugin.param.operator

import com.google.common.collect.ImmutableList
import io.digdag.client.config.ConfigKey
import io.digdag.spi.{OperatorContext, TaskResult, TemplateEngine}

class ParamResetOperator(
    operatorName: String,
    context: OperatorContext,
    templateEngine: TemplateEngine
) extends AbstractParamOperator(operatorName, context, templateEngine) {

  protected val resetKey: String = params.get("_command", classOf[String])

  override def runTask(): TaskResult = {
    val builder = TaskResult.defaultBuilder(request)
    logger.debug(s"Reset $resetKey")
    builder.resetStoreParams(ImmutableList.of(ConfigKey.parse(resetKey)))
    builder.build()
  }

}

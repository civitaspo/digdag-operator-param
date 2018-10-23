package pro.civitaspo.digdag.plugin.param.operator

import io.digdag.client.config.Config
import io.digdag.spi.{OperatorContext, TaskResult, TemplateEngine}

class ParamStoreOperator(operatorName: String, context: OperatorContext, templateEngine: TemplateEngine) extends AbstractParamOperator(operatorName, context, templateEngine) {

  protected val newParams: Config = params.get("_command", classOf[Config])

  override def runTask(): TaskResult = {
    val builder = TaskResult.defaultBuilder(request)
    logger.debug(s"Store $newParams")
    builder.storeParams(newParams)
    builder.build()
  }

}

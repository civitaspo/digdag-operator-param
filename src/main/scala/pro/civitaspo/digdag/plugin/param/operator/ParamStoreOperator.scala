package pro.civitaspo.digdag.plugin.param.operator
import io.digdag.client.config.Config
import io.digdag.spi.{OperatorContext, TaskResult, TemplateEngine}

class ParamStoreOperator(operatorName: String, context: OperatorContext, systemConfig: Config, templateEngine: TemplateEngine)
  extends AbstractParamOperator(operatorName, context, systemConfig, templateEngine) {

  override def runTask(): TaskResult = {
    TaskResult.empty(request)
  }

}

package pro.civitaspo.digdag.plugin.param.operator

import io.digdag.client.config.{Config, ConfigFactory}
import io.digdag.spi.{OperatorContext, TemplateEngine}
import io.digdag.util.BaseOperator
import org.slf4j.{Logger, LoggerFactory}

abstract class AbstractParamOperator(
    operatorName: String,
    context: OperatorContext,
    templateEngine: TemplateEngine
) extends BaseOperator(context) {

  protected val logger: Logger = LoggerFactory.getLogger(operatorName)
  protected val cf: ConfigFactory = request.getConfig.getFactory
  protected val params: Config = request.getConfig

}

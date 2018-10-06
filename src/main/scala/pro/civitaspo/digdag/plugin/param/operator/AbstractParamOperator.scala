package pro.civitaspo.digdag.plugin.param.operator

import io.digdag.client.config.Config
import io.digdag.spi.OperatorContext
import io.digdag.util.BaseOperator
import org.slf4j.{Logger, LoggerFactory}

abstract class AbstractParamOperator(operatorName: String, context: OperatorContext) extends BaseOperator(context) {

  protected val logger: Logger = LoggerFactory.getLogger(operatorName)
  protected val params: Config = request.getConfig

}

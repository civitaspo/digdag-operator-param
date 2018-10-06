package pro.civitaspo.digdag.plugin.param

import java.util.{Arrays => JArrays, List => JList}
import java.lang.reflect.Constructor

import io.digdag.client.config.Config
import io.digdag.spi.{Operator, OperatorContext, OperatorFactory, OperatorProvider, Plugin, TemplateEngine}
import javax.inject.Inject
import pro.civitaspo.digdag.plugin.param.operator.{AbstractParamOperator, ParamResetOperator, ParamStoreOperator}

object ParamPlugin {

  class AthenaOperatorProvider extends OperatorProvider {

    @Inject protected var systemConfig: Config = null
    @Inject protected var templateEngine: TemplateEngine = null

    override def get(): JList[OperatorFactory] = {
      JArrays.asList(
        operatorFactory("param.reset", classOf[ParamResetOperator]),
        operatorFactory("param.store", classOf[ParamStoreOperator])
      )
    }

    private def operatorFactory[T <: AbstractParamOperator](operatorName: String, klass: Class[T]): OperatorFactory = {
      new OperatorFactory {
        override def getType: String = operatorName
        override def newOperator(context: OperatorContext): Operator = {
          val constructor: Constructor[T] = klass.getConstructor(classOf[String], classOf[OperatorContext], classOf[Config], classOf[TemplateEngine])
          constructor.newInstance(operatorName, context, systemConfig, templateEngine)
        }
      }
    }
  }
}

class ParamPlugin extends Plugin {
  override def getServiceProvider[T](`type`: Class[T]): Class[_ <: T] = {
    if (`type` ne classOf[OperatorProvider]) return null
    classOf[ParamPlugin.AthenaOperatorProvider].asSubclass(`type`)
  }
}

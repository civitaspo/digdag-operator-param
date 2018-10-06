package pro.civitaspo.digdag.plugin.param

import java.lang.reflect.Constructor
import java.util.{Arrays => JArrays, List => JList}

import io.digdag.spi.{Operator, OperatorContext, OperatorFactory, OperatorProvider, Plugin}
import pro.civitaspo.digdag.plugin.param.operator.{AbstractParamOperator, ParamResetOperator, ParamStoreOperator}

object ParamPlugin {

  class AthenaOperatorProvider extends OperatorProvider {

    override def get(): JList[OperatorFactory] = {
      JArrays.asList(operatorFactory("param_reset", classOf[ParamResetOperator]), operatorFactory("param_store", classOf[ParamStoreOperator]))
    }

    private def operatorFactory[T <: AbstractParamOperator](operatorName: String, klass: Class[T]): OperatorFactory = {
      new OperatorFactory {
        override def getType: String = operatorName
        override def newOperator(context: OperatorContext): Operator = {
          val constructor: Constructor[T] = klass.getConstructor(classOf[String], classOf[OperatorContext])
          constructor.newInstance(operatorName, context)
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

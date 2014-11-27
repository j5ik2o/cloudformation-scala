package cfn

import org.json4s._

object JsonFormatUtil {

  def capitalize(name: String) = name.charAt(0).toUpper + name.substring(1)

  def toJFields(properties: Properties)(f: PartialFunction[(String, Any), Option[JField]]): Seq[JField] =
    properties.getClass.getDeclaredFields.map(_.getName)
      .zip(properties.productIterator.toSeq).toMap
      .foldLeft(List.empty[JField]) {
      case (r, (k, v)) =>
        f(k, v).map(_ :: r).getOrElse(r)
    }.toSeq

  val enumHandlerByNumber: PartialFunction[(String, Any), Option[JField]] = {
    case (key, value: Enumeration#Value) => Some(JField(capitalize(key), JInt(value.id)))
  }

  val propertiesHandler: PartialFunction[(String, Any), Option[JField]] = {
    case (key, values: Properties) =>
      Some(
        JField(capitalize(key),
          JObject(toJFields(values)(defaultHandler(None)): _*)
        )
      )
  }
  type PF = PartialFunction[(String, Any), Option[JField]]

  def primitiveHandler: PF = {
    case (key, value: String) => Some(JField(capitalize(key), JString(value)))
    case (key, value: Int) => Some(JField(capitalize(key), JInt(value)))
    case (key, value: Boolean) => Some(JField(capitalize(key), JBool(value)))
  }

  def optionHandler(primitiveHandler: PF): PF = {
    case (key, value: Option[_]) => value match {
      case None => None
      case Some(v) => primitiveHandler(key, v)
    }
  }

  def collectionHandler(primitiveHandler: PF): PF = {
    case (key, values: Iterable[_]) => values.toList match {
      case Nil => None
      case list => Some(
        JField(capitalize(key), JArray(
          list.foldLeft(List.empty[JValue]) { (r, e) =>
            primitiveHandler(key, e).map(_._2 :: r).getOrElse(r)
          }
        ))
      )
    }
    case (key, values: Set[_]) => primitiveHandler(key, values.toSeq)
  }

  def defaultHandler(userHandler: Option[PF] = None): PF = {
    val pf = userHandler.map(_ orElse primitiveHandler).getOrElse(primitiveHandler)
    pf orElse optionHandler(pf) orElse collectionHandler(pf)
  }

}

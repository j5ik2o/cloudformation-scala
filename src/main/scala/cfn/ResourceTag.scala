package cfn

import org.json4s._
import org.json4s.DefaultReaders._

case class ResourceTag(key: String, value: String) extends Properties

object ResourceTag {

  import cfn.JsonFormatUtil._

  implicit object ResourceJsonFormat extends JsonFormat[ResourceTag] {

    override def read(value: JValue): ResourceTag = ResourceTag(
      key = (value \ "Key").as[String],
      value = (value \ "Value").as[String]
    )

    override def write(obj: ResourceTag): JValue = {
      JObject(
        toJFields(obj)(defaultHandler()): _*
      )
    }
  }

}
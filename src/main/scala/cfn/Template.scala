package cfn

import org.json4s._
import org.json4s.DefaultReaders._

case class Template(awsTemplateFormatVersion: String = "2010-09-09", resources: Seq[Resource])

object Template {

  implicit object ResourceJsonFormat extends JsonFormat[Template] {

    override def read(value: JValue): Template = Template(
      awsTemplateFormatVersion = (value \ "AWSTemplateFormatVersion").as[String],
      resources = (value \ "Resources").as[Seq[Resource]]
    )

    override def write(obj: Template): JValue = {
      JObject(
        JField("AWSTemplateFormatVersion", JString(obj.awsTemplateFormatVersion)),
        JField("Resources",
          JObject(obj.resources.map(e => JField(e.name, e.asJValue)).toList)
        )
      )
    }
  }

}

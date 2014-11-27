package cfn.ec2

import cfn.{Properties, ResourceTag}
import org.json4s.DefaultReaders._
import org.json4s._

case class EC2InternetGatewayProperties(tags: Seq[ResourceTag] = Nil) extends Properties

object EC2InternetGatewayProperties {

  import cfn.JsonFormatUtil._

  implicit object JsonFormat extends org.json4s.JsonFormat[EC2InternetGatewayProperties] {

    override def read(value: JValue): EC2InternetGatewayProperties = EC2InternetGatewayProperties(
      tags = (value \ "Tags").as[Seq[ResourceTag]]
    )

    override def write(obj: EC2InternetGatewayProperties): JValue = {
      val pf : PartialFunction[(String, Any), Option[JField]] = {
        case (key, value: ResourceTag) => Some(JField(key, value.asJValue))
      }
      JObject(
        toJFields(obj)(defaultHandler(Some(pf))): _*
      )
    }
  }

}

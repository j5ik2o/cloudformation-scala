package cfn.ec2

import cfn.{Resource, ResourceType}
import org.json4s._
import org.json4s.DefaultReaders._

case class EC2InternetGateway(name: String, properties: EC2InternetGatewayProperties) extends Resource {
  override val resourceType: ResourceType.Value = ResourceType.AWS_EC2_InternetGateway
}

object EC2InternetGateway {

  implicit object JsonFormat extends org.json4s.JsonFormat[EC2InternetGateway] {

    override def read(value: JValue): EC2InternetGateway = EC2InternetGateway(
      name = (value \ "Name").as[String],
      properties = (value \ "Properties").as[EC2InternetGatewayProperties]
    )

    override def write(obj: EC2InternetGateway): JValue = {
      JObject(
        JField("Type", JString(obj.resourceType.toString)),
        JField("Properties", obj.properties.asJValue)
      )
    }

  }

}

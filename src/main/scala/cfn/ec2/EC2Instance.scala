package cfn.ec2

import cfn._
import org.json4s.DefaultReaders._
import org.json4s._

case class EC2Instance(name: String, properties: EC2InstanceProperties) extends Resource {
  override val resourceType: ResourceType.Value = ResourceType.AWS_EC2_Instance
}

object EC2Instance {

  implicit object JsonFormat extends org.json4s.JsonFormat[EC2Instance] {
    override def read(value: JValue): EC2Instance =
      EC2Instance(name = (value \ "Name").as[String],
        properties = (value \ "Properties").as[EC2InstanceProperties])

    override def write(obj: EC2Instance): JValue =
      JObject(
        JField("Type", JString(obj.resourceType.toString)),
        JField("Properties", obj.properties.asJValue)
      )
  }

}

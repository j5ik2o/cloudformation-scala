package cfn

import cfn.ec2.{EC2InternetGateway, EC2Instance, EC2DHCPOptions}
import org.json4s._
import org.json4s.DefaultReaders._

trait Resource extends Product {

  val name: String

  val resourceType: ResourceType.Value

  val properties: Properties

}

object Resource {

  implicit object ResourceJsonFormat extends org.json4s.JsonFormat[Resource] {
    override def read(value: JValue): Resource = {
      ResourceType.withName((value \ "Type").as[String]) match {
        case ResourceType.AWS_EC2_Instance => EC2Instance.JsonFormat.read(value)
        case ResourceType.AWS_EC2_DHCPOptions => EC2DHCPOptions.JsonFormat.read(value)
        case ResourceType.AWS_EC2_InternetGateway => EC2InternetGateway.JsonFormat.read(value)
      }
    }

    override def write(obj: Resource): JValue = obj match {
      case value: EC2Instance => EC2Instance.JsonFormat.write(value)
      case value: EC2DHCPOptions => EC2DHCPOptions.JsonFormat.write(value)
      case value: EC2InternetGateway => EC2InternetGateway.JsonFormat.write(value)
    }
  }

}

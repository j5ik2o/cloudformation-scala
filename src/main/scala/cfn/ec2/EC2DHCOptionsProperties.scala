package cfn.ec2

import cfn.ec2.EC2DHCPOptions.NetbiosNodeType
import cfn.{Properties, ResourceTag}
import org.json4s.DefaultReaders._
import org.json4s._

case class EC2DHCOptionsProperties
(domainName: Option[String] = None,
 domainNameServers: Seq[String] = Nil,
 netbiosNameServers: Seq[String] = Nil,
 netbiosNodeType: Seq[NetbiosNodeType.Value] = Nil,
 ntpServers: Seq[String] = Nil,
 tags: Seq[ResourceTag] = Nil) extends Properties

object EC2DHCOptionsProperties {

  import cfn.JsonFormatUtil._

  implicit object JsonFormat extends org.json4s.JsonFormat[EC2DHCOptionsProperties] {

    override def read(value: JValue): EC2DHCOptionsProperties = {
      EC2DHCOptionsProperties(
        domainName = (value \ "DomainName").getAs[String],
        domainNameServers = (value \ "DomainNameServers").as[Seq[String]],
        netbiosNameServers = (value \ "NetbiosNameServers").as[Seq[String]],
        netbiosNodeType = NetbiosNodeType.fromInt((value \ "NetbiosNodeType").as[Int]),
        ntpServers = (value \ "NtpServers").as[Seq[String]],
        tags = (value \ "Tags").as[Seq[ResourceTag]]
      )
    }

    override def write(obj: EC2DHCOptionsProperties): JValue = {
      val pf: PartialFunction[(String, Any), Option[JField]] = {
        case (key, value: ResourceTag) => Some(JField(key, value.asJValue))
      }
      JObject(
        toJFields(obj)(defaultHandler(Some(pf orElse enumHandlerByNumber))): _*
      )
    }
  }

}

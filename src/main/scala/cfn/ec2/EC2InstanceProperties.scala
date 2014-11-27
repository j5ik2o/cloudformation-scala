package cfn.ec2

import cfn.{MountPointProperty, NetworkInterface, Properties, ResourceTag}
import org.json4s._

case class EC2InstanceProperties
(availabilityZone: Option[String] = None,
 blockDeviceMappings: Seq[String] = Nil,
 disableApiTermination: Option[Boolean] = None,
 ebsOptimized: Option[Boolean] = None,
 iamInstanceProfile: Option[String] = None,
 imageId: String,
 instanceInitiatedShutdownBehavior: Option[String] = None,
 instanceType: Option[String] = None,
 kernelId: Option[String] = None,
 keyName: Option[String] = None,
 monitoring: Option[Boolean] = None,
 networkInterfaces: Seq[NetworkInterface] = Nil,
 placementGroupName: Option[String] = None,
 privateIpAddress: Option[String] = None,
 ramdiskId: Option[String] = None,
 securityGroupIds: Seq[String] = Nil,
 securityGroups: Seq[String] = Nil,
 sourceDestCheck: Option[Boolean] = None,
 subnetId: Option[String] = None,
 tags: Seq[ResourceTag] = Nil,
 tenancy: Option[String] = None,
 userData: Option[String] = None,
 volumes: Seq[MountPointProperty] = Nil) extends Properties

object EC2InstanceProperties {

  import cfn.JsonFormatUtil._

  implicit object JsonFormat extends org.json4s.JsonFormat[EC2InstanceProperties] {

    override def read(value: JValue): EC2InstanceProperties = ???

    override def write(obj: EC2InstanceProperties): JValue = {
      val pf : PartialFunction[(String, Any), Option[JField]] = {
        case (key, value: NetworkInterface) => Some(JField(key, value.asJValue))
        case (key, value: MountPointProperty) => Some(JField(key, value.asJValue))
      }
      JObject(
        toJFields(obj)(defaultHandler(Some(pf))): _*
      )
    }
  }

}
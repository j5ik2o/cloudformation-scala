package cfn

import org.json4s._
import org.json4s.DefaultReaders._

case class NetworkInterface
(associatePublicIpAddress: Option[Boolean] = None,
 deleteOnTermination: Option[Boolean] = None,
 description: Option[String] = None,
 deviceIndex: String,
 groupSet: Set[String] = Set.empty,
 networkInterfaceId: Option[String] = None,
 privateIpAddress: Option[String] = None,
 privateIpAddresses: Seq[PrivateIpAddressSpecification] = Nil,
 secondaryPrivateIpAddressCount: Option[Int] = None,
 subnetId: Option[String] = None) extends Properties


object NetworkInterface {

 import cfn.JsonFormatUtil._

 implicit object JsonFormat extends org.json4s.JsonFormat[NetworkInterface] {

  override def read(value: JValue): NetworkInterface = NetworkInterface(
   associatePublicIpAddress = (value \ "AssociatePublicIpAddress").getAs[Boolean],
   deleteOnTermination = (value \ "DeleteOnTermination").getAs[Boolean],
   description = (value \ "Description").getAs[String],
   deviceIndex = (value \ "DeviceIndex").as[String],
   groupSet = (value \ "groupSet").as[Set[String]],
   networkInterfaceId = (value \ "NetworkInterfaceId").getAs[String],
   privateIpAddress = (value \ "PrivateIpAddress").getAs[String],
   privateIpAddresses = (value \ "PrivateIpAddresses").as[Seq[PrivateIpAddressSpecification]],
   secondaryPrivateIpAddressCount = (value \ "SecondaryPrivateIpAddressCount").getAs[Int],
   subnetId = (value \ "SubnetId").getAs[String]
  )

  override def write(obj: NetworkInterface): JValue = {
   val pf: PartialFunction[(String, Any), Option[JField]] = {
    case (key, value: PrivateIpAddressSpecification) => Some(JField(key, value.asJValue))
   }
   JObject(
    toJFields(obj)(defaultHandler(Some(pf))): _*
   )
  }
 }



}

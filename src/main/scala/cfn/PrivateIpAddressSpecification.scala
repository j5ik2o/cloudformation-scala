package cfn

import org.json4s._
import org.json4s.DefaultReaders._

case class PrivateIpAddressSpecification(privateIpAddress: String, primary: Boolean) extends Properties

object PrivateIpAddressSpecification {

  import cfn.JsonFormatUtil._

  implicit object JsonFormat extends org.json4s.JsonFormat[PrivateIpAddressSpecification] {

    override def read(value: JValue): PrivateIpAddressSpecification = PrivateIpAddressSpecification(
      privateIpAddress = (value \ "PrivateIpAddress").as[String],
      primary = (value \ "Primary").as[Boolean]
    )

    override def write(obj: PrivateIpAddressSpecification): JValue = {
      JObject(
        toJFields(obj)(defaultHandler()): _*
      )
    }
  }
}


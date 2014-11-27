package cfn

import org.json4s._
import org.json4s.DefaultReaders._

case class MountPointProperty(device: String, volumeId: String) extends Properties

object MountPointProperty{

  import cfn.JsonFormatUtil._

  implicit object JsonFormat extends org.json4s.JsonFormat[MountPointProperty] {

    override def read(value: JValue): MountPointProperty = MountPointProperty(
      device = (value \ "Device").as[String],
      volumeId = (value \ "VolumeId").as[String]
    )

    override def write(obj: MountPointProperty): JValue = {
      JObject(
        toJFields(obj)(defaultHandler()): _*
      )
    }
  }

}

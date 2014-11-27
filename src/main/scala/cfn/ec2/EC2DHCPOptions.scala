package cfn.ec2

import cfn.{Resource, ResourceType}
import org.json4s.DefaultReaders._
import org.json4s._

case class EC2DHCPOptions(name: String, properties: EC2DHCOptionsProperties) extends Resource {
  override val resourceType: ResourceType.Value = ResourceType.AWS_EC2_DHCPOptions
}

object EC2DHCPOptions {

  implicit object JsonFormat extends org.json4s.JsonFormat[EC2DHCPOptions] {

    override def read(value: JValue): EC2DHCPOptions =
      EC2DHCPOptions(name = (value \ "Name").as[String],
        properties = (value \ "Properties").as[EC2DHCOptionsProperties])

    override def write(obj: EC2DHCPOptions): JValue = {
      JObject(
        JField("Type", JString(obj.resourceType.toString)),
        JField("Properties", obj.properties.asJValue)
      )
    }

  }

  object NetbiosNodeType extends Enumeration {
    val Broadcast = Value(1)
    val PointToPoint = Value(2)
    val MixedMode = Value(4)
    val Hybrid = Value(8)

    def fromInt(value: Int): Seq[NetbiosNodeType.Value] = {
      (1 to NetbiosNodeType.maxId).foldLeft(Seq.empty[NetbiosNodeType.Value]) { (r, e) =>
        val pow = Math.pow(2, e).toInt
        if ((pow & value) != 0) {
          r :+ NetbiosNodeType(pow)
        } else r
      }
    }

    def toInt(values: Seq[Value]): Int =
      values.foldLeft(0) { (r, e) => r + e.id}

  }

}

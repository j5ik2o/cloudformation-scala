package cfn

import org.json4s._
import org.json4s.native._

object TemplateBuilder {

  def buildAsJValue(template: Template): JValue = template.asJValue

  def buildAsString(template: Template): String = prettyJson(renderJValue(buildAsJValue(template)))

}

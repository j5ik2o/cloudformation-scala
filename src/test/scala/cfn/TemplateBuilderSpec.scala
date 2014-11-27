package cfn

import cfn.ec2._
import org.scalatest.FunSpec

class TemplateBuilderSpec extends FunSpec {

  describe("TemplateBuilder") {
    it("build") {

      val template = Template(
        resources = Seq(
          EC2Instance(name = "Web", properties = EC2InstanceProperties(imageId = "test")),
          EC2DHCPOptions(name = "WebDHCP", properties = EC2DHCOptionsProperties(domainName = Some("chatwork.com"))),
          EC2InternetGateway(name = "Gateway", properties = EC2InternetGatewayProperties(tags = Seq(ResourceTag(key = "name", value ="kato"))))
        )
      )

      val json = TemplateBuilder.buildAsString(template)
      println(json)

    }
  }

}

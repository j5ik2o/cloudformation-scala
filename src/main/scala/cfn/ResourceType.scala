package cfn

/**
 * Created by junichi-kato on 2014/11/26.
 */
object ResourceType extends Enumeration {
  val AWS_EC2_Instance = Value("AWS::EC2::Instance")
  val AWS_EC2_InternetGateway = Value("AWS::EC2:InternetGateway")
  val AWS_EC2_DHCPOptions = Value("AWS::EC2::DHCPOptions")
  val AWS_EC2_EIP = Value("AWS::EC2::EIP")
}

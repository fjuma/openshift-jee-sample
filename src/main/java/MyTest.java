import org.jboss.dmr.ModelNode;
import org.jboss.as.controller.client.ModelControllerClient;

public class MyTest {
  public static void main(String args[]) throws Exception {

     ModelNode op = new ModelNode();
     op.get("operation").set("obtain-certificate");
     op.get("alias").set("server");
     op.get("domain-names").set("[f2f-mydemo.7e14.starter-us-west-2.openshiftapps.com]");
     op.get("certificate-authority-account","myLetsEncryptAccount");
     op.get("agree-to-terms-of-service","true");

     ModelNode addr = op.get("address");
     addr.add("subsystem", "elytron");
     addr.add("key-store","serverKS");

     ModelControllerClient client = ModelControllerClient.Factory.create(java.net.InetAddress.getByName("localhost"), 9990);

     long start = System.currentTimeMillis();

     client.execute(op);

     long end = System.currentTimeMillis();
     System.out.println("DURATION " + (end - start));

  }
}

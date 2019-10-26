package es.edreams.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

/**
 * Server that manages startup/shutdown of a {@code Greeter} server.
 */
public class HelloWorldServer {
  private static final Logger logger = Logger.getLogger(HelloWorldServer.class.getName());

  private Server server;

  private void start() throws IOException {
    /* The port on which the server should run */
    int port = 8082;
    server = ServerBuilder.forPort(port)
        .addService(new GreeterImpl())
        .build()
        .start();
    logger.info("Server started, listening on " + port);
    Runtime.getRuntime().addShutdownHook(new Thread() {
      @Override
      public void run() {
        // Use stderr here since the logger may have been reset by its JVM shutdown hook.
        System.err.println("*** shutting down gRPC server since JVM is shutting down");
        HelloWorldServer.this.stop();
        System.err.println("*** server shut down");
      }
    });
  }

  private void stop() {
    if (server != null) {
      server.shutdown();
    }
  }

  /**
   * Await termination on the main thread since the grpc library uses daemon threads.
   */
  private void blockUntilShutdown() throws InterruptedException {
    if (server != null) {
      server.awaitTermination();
    }
  }

  /**
   * Main launches the server from the command line.
   */
  public static void main(String[] args) throws IOException, InterruptedException {
    final HelloWorldServer server = new HelloWorldServer();
    server.start();
    server.blockUntilShutdown();
  }
  
  static class keenKaleImp {
	  
	  public static String keenKaleConnection() {
		  
		  String response = "";
		  
		  try {
			  
			  URL url = new URL("http://rtb-useast.keenkale.com/rtb?zone=55764");
			  HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		      conn.setDoOutput(true);
		      conn.setRequestMethod("POST");
		      conn.setRequestProperty("Content-Type", "application/json");
		      
		      conn.setRequestProperty("Connection","Keep-Alive");
		      conn.setRequestProperty("Accept-Charset","UTF-8");
		      conn.setRequestProperty("Expect","x-openrtb-version: 2.4");
		      conn.setRequestProperty("x-forwarded-for","216.15.125.142");
		      conn.setRequestProperty("x-device-user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36");
		      conn.setRequestProperty("x-original-user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36");
		  	
		  	
		      String request = "{\"id\":\"dca01512ae9ce5b95712794dcd677d80\",\"imp\":[{\"id\":\"6bbc5bc3eb7081e1b7f4f7cc29b815f9\",\"instl\":0,\"tagid\":\"tx500674895\",\"bidfloor\":0.052500000000000005,\"bidfloorcur\":\"USD\",\"secure\":1,\"banner\":{\"id\":\"d06ca4a034793852b24a6bbc66e14690\",\"w\":320,\"h\":50,\"mimes\":[\"image/jpg\",\"image/gif\"],\"battr\":[],\"wmax\":320,\"hmax\":50,\"wmin\":300,\"hmin\":49,\"api\":[3,5]}}],\"device\":{\"ua\":\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36\",\"geo\":{\"country\":\"ESP\",\"lat\":41.427564,\"lon\":2.185005,\"type\":1},\"dnt\":0,\"lmt\":0,\"ip\":\"88.1.48.62\",\"devicetype\":4,\"make\":\"Samsung\",\"model\":\"gt-9300\",\"os\":\"Android\",\"osv\":\"4.4.4\",\"ifa\":\"1a58da58-4930-4adc-b1a4-2dc1ba386a96\",\"connectiontype\":2,\"js\":1,\"language\":\"es\"},\"test\":0,\"at\":1,\"tmax\":1500,\"badv\":[],\"app\":{\"id\":\"10308\",\"name\":\"Whatsdog\",\"publisher\":{\"id\":\"67fbd985230c665850075df702d12c5e\",\"name\":\"tappx\",\"domain\":\"tappx.com\"},\"bundle\":\"com.secondlemon.whatsdogpremium\",\"storeurl\":\"https://play.google.com/store/apps/details?id=com.secondlemon.whatsdogpremium\"},\"user\":{\"geo\":{\"country\":\"ESP\",\"lat\":41.427564,\"lon\":2.185005,\"type\":1}}}";
			  OutputStream os = conn.getOutputStream();
			  os.write(request.getBytes());
			  os.flush();
			  
			  response = "KeenKale Code (" + conn.getResponseCode() + ") Body [";
			  
			  
			  BufferedReader br = new BufferedReader(new InputStreamReader(
						(conn.getInputStream())));

			  String out;
			  while ((out = br.readLine()) != null) {
					response += out;
			  }

			  response += "]";
			  
			  conn.disconnect();
			  
			  
			  
		  }catch(Exception e) {
			 response = "Something happened with keenKale connection (" + e.getMessage() + ")"; 
		  }
		  
		  return response;
		  
		  
	  }
	  
  }
  

  static class GreeterImpl extends GreeterGrpc.GreeterImplBase {
	  
	private static final String code = "271";  

    @Override
    public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
      
      String returnMessage = "";
      
      if (req.getName().equalsIgnoreCase(code)) {
    	  returnMessage = keenKaleImp.keenKaleConnection();
      }else {
    	  returnMessage = " code (" + req.getName() + ") not found ";
      }
    	
    	
      HelloReply reply = HelloReply.newBuilder().setMessage(returnMessage).build();
      responseObserver.onNext(reply);
      responseObserver.onCompleted();
    }
  }
}

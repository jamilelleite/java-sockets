package babystep;
import java.io.*;
import java.net.*;

public class Client {
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		String serverHost = "localhost";
		int serverPort = 4326;
		String clientName = "mona";
		
		// creating socket for communication and requesting connection
		Socket soc = new Socket(serverHost, serverPort);
		
		// creating output stream
		OutputStream os = soc.getOutputStream();
		DataOutputStream dos = new DataOutputStream(os);
	
		// creating input stream
		InputStream is = soc.getInputStream();
		DataInputStream dis = new DataInputStream(is);
		
		// sending data through output stream
		dos.writeUTF(clientName);
		
		// reading data through input stream
		System.out.println(dis.readUTF());
		
		// closing streams
		dis.close();
		dos.close();
		
		// closing socket
		soc.close();
	}
	
	
	
}

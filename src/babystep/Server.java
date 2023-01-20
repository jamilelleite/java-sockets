package babystep;
import java.io.*;
import java.net.*;

public class Server {

	
	public static void main(String[] args) throws IOException {
		int port = 4326;
		int backlog = 3;
		String clientName;
		
		//creating server socket
		ServerSocket listenSoc = new ServerSocket(port, backlog);
		
		while (true) {
			
			// waiting for connection request
			Socket soc = listenSoc.accept();

			// creating output stream
			OutputStream os = soc.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);
			
			//creating input stream
			InputStream is = soc.getInputStream();
			DataInputStream dis = new DataInputStream(is);
			
			// receiving data through input stream
			clientName = dis.readUTF();
			
			//writing data through input stream
			dos.writeUTF("Hello " + clientName);
	
			// closing streams
			dos.close();
			dis.close();

			// closing socket
			soc.close();			
		}
		
		
	}
	
}

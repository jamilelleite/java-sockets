package poolmtserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException {
		
		int port = 4319; //TODO: input
		int backlog = 3;
		
		ServerSocket listenSoc = new ServerSocket(port, backlog);
		
		ProdConsBuffer clientsBuffer = new ProdConsBuffer(backlog);
		
		Worker worker = new Worker(clientsBuffer);
		
		while(true) {
			Socket soc = listenSoc.accept();
			try {
				clientsBuffer.put(soc);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
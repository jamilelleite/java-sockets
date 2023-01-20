package mtfileserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException {
		
		int port = 4321; //TODO: input
		int backlog = 3;
		
		ServerSocket listenSoc = new ServerSocket(port, backlog);
		
		while(true) {
			Socket soc = listenSoc.accept();
			// create a new worker for each client
			Worker worker = new Worker(soc);
		}
		
	}
}

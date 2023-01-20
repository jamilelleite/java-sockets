package basicfileserver;
import java.io.*;
import java.net.*;

public class Server {

	
	public static void main(String[] args) throws IOException {
		int port = 4322; //TODO: input
		int backlog = 3;
		String fileName;
		
		ServerSocket listenSoc = new ServerSocket(port, backlog);
		
		while (true) {
			Socket soc = listenSoc.accept();

			OutputStream os = soc.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);
			
			InputStream is = soc.getInputStream();
			DataInputStream dis = new DataInputStream(is);
			
			fileName = dis.readUTF();
			
			sendFile(fileName, dos);
			
			soc.close();			
		}
		
		
	}
	
	public static void sendFile(String file, DataOutputStream dos) {
		int count;
		try {
			// creating stream connection to file
			FileInputStream fis = new FileInputStream(file);

			byte[] buffer = new byte[512];
			
			while ((count = fis.read(buffer)) != -1) 
				// reading file by chunks and sending to client
				dos.write(buffer, 0, count);
			fis.close();			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

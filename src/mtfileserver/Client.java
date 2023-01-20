package mtfileserver;
import java.io.*;
import java.net.*;

public class Client {
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		String serverHost = "localhost"; //TODO: use user input value
		int serverPort = 4320; //TODO: use user input value
		String fileName = "/home/jamile/Documents/poly.pdf"; //TODO: use user input value
		String storeFileName = "/home/jamile/Documents/poly_copy.pdf"; //TODO: create new file name automatically
		
		Socket soc = new Socket(serverHost, serverPort);
		
		OutputStream os = soc.getOutputStream();
		DataOutputStream dos = new DataOutputStream(os);
	
		InputStream is = soc.getInputStream();
		DataInputStream dis = new DataInputStream(is);
		
		dos.writeUTF(fileName);
		
		receiveFile(storeFileName, dis);
		
		dos.close();
		dis.close();
		
		soc.close();
	}
	
	public static void receiveFile(String file, DataInputStream dis) {
		try {
			FileOutputStream fos = new FileOutputStream(file);

			byte[] buffer = new byte[512];
			while ((dis.read(buffer)) != -1)
				// receiving file by chunks and writing in new file
				fos.write(buffer);
			
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}

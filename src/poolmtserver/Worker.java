package poolmtserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Worker extends Thread{
	ProdConsBuffer buf;
	String fileName;
	
	public Worker(ProdConsBuffer buf) {
		this.buf = buf;
		start();
	}
	
	public void run() {
		while(true) {
			try {
				Socket soc = buf.get();
				OutputStream os = soc.getOutputStream();
				DataOutputStream dos = new DataOutputStream(os);
				
				InputStream is = soc.getInputStream();
				DataInputStream dis = new DataInputStream(is);
				
				fileName = dis.readUTF();
				
				sendFile(fileName, dos);
				
				dos.close();
				dis.close();
				
				soc.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	}
		
	public void sendFile(String file, DataOutputStream dos) {
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

package poolmtserver;

import java.net.Socket;

public class ProdConsBuffer {
	Socket buffer[];
	int nplaces;
	int size;
	int in = 0;
	int out = 0;
	
	public ProdConsBuffer(int size) {
		this.buffer = new Socket[size];
		this.size = size;
		this.nplaces = size;
	}
	
	public synchronized void put(Socket soc) throws InterruptedException {
		while (nplaces == 0) {
			wait();
		}
		buffer[in] = soc;
		in = (in + 1) % size;
		nplaces--;
		notifyAll();
	}
	
	public synchronized Socket get() throws InterruptedException {
		while (nplaces == size) {
			wait();
		}
		Socket soc = buffer[out];
		out = (out + 1) % size;
		nplaces++;
		return soc;
	}
}

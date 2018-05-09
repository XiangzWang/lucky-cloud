package org.bamboo.io.socket.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class DailyAdviceClient {
	public void go() {
		try {
			while (true) {
				Socket socket = new Socket("localhost", 8081);
				InputStreamReader streamReader = new InputStreamReader(socket.getInputStream());
				BufferedReader reader = new BufferedReader(streamReader);
				String advice = reader.readLine();
				System.out.println(advice);
				
				streamReader.close();
				reader.close();
				
				Thread.sleep(3000);
			}
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public static void main(String[] args) {
		DailyAdviceClient client = new DailyAdviceClient();
		client.go();
	}
}

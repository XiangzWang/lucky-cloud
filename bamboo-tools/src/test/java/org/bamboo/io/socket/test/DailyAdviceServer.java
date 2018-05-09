package org.bamboo.io.socket.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class DailyAdviceServer {
	String[] adviceList = {
			"Take smaller bites",
			"Go for the tight jeans. No they do NOT make you look fat. ",
			"One word: inappropriate",
			"Just for today, be honest. Tell your boss what you *really* think",
			"You might want to rethink haircut."
	};
	
	private String getAdvice() {
		Random r = new Random();
		String advice = adviceList[r.nextInt(adviceList.length)];
		return advice;
	}
	
	public void go() {
		try {
			ServerSocket serverSocket = new ServerSocket(8081);
			while (true) {
				Socket socket = serverSocket.accept();
				PrintWriter writer = new PrintWriter(socket.getOutputStream());
				String advice = getAdvice();
				writer.println(advice);
				writer.close();
				System.out.println(advice);
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		DailyAdviceServer server = new DailyAdviceServer();
		server.go();
	}
}

package de.chatserver.threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import de.chatserver.controller.ChatServer;

public class ReceivingThread extends Thread {
	
	private Socket socket;
	private ChatServer chatServer;
	private InputStreamReader isr;
	private BufferedReader br;
	
	public ReceivingThread(Socket socket, ChatServer chatServer)
	{
		this.socket = socket;
		this.chatServer = chatServer;
		
		try
		{		
			InputStream is = socket.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void run()
	{

		while (true)
		{
			try 
			{
				String message = br.readLine();
				if (message != null)
				{
					chatServer.sendMessage(message);
				}
			} 
			catch (IOException e)
			{
				e.printStackTrace();
			}
			
		}
	}

}

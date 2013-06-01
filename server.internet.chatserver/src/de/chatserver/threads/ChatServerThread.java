package de.chatserver.threads;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import de.chatserver.controller.ChatServer;

public class ChatServerThread extends Thread {
	private ServerSocket serverSocket;
	private ChatServer chatServer;
	private Socket socket;
	
	public ChatServerThread(ChatServer chatServer)
	{
		this.chatServer = chatServer;
		try 
		{
			this.serverSocket = new ServerSocket(1234);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			System.err.println("Failed to initialize the ServerSocket");
		}
	}
	
	public void run()
	{
		
		while (true)
		{
			try 
			{
				socket = serverSocket.accept();
				chatServer.registerSocket(socket);
			}
			catch (IOException e)
			{
				e.printStackTrace();
				System.err.println("Failed to wait for a Socket");
			}
			
		}
	}

}

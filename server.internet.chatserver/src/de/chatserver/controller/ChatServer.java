package de.chatserver.controller;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import de.chatserver.threads.ChatServerThread;
import de.chatserver.threads.ReceivingThread;

public class ChatServer 
{
	private List<Socket> clientSockets;
	
	public ChatServer() 
	{
		clientSockets = new ArrayList<Socket>();
		ChatServerThread chatServerThread = new ChatServerThread(this);
		chatServerThread.start();
	}
	
	public void registerSocket(Socket socket)
	{
		clientSockets.add(socket);
		for (Socket s : clientSockets)
		{
			System.out.println(s.getInetAddress().getHostName());
		}
		this.startReceivingThread(socket);
	}
	
	public void startReceivingThread(Socket socket)
	{
		ReceivingThread receivingThread = new ReceivingThread(socket, this);
		receivingThread.start();
	}
	
	public void sendMessage(String message)
	{
		for (Socket s : clientSockets)
		{
			try
			{				
				OutputStream os = s.getOutputStream();
				OutputStreamWriter osWriter = new OutputStreamWriter(os);
				BufferedWriter bWriter = new BufferedWriter(osWriter);
				PrintWriter pWriter = new PrintWriter(bWriter, true);
				pWriter.println(message);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}

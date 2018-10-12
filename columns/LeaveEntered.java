import java.net.*;
import java.io.*;

class LeaveEntered
{
	public static void main(String args[])
	{	
		ServerSocket s;
		DataInputStream dis;
		DataOutputStream dos;
		Socket client;
		String info[];
		LeaveFrame lf;

		try
		{
			s=new ServerSocket(1200);

			while(true)
			{
				client=s.accept();

				dis=new DataInputStream(client.getInputStream());
				dos=new DataOutputStream(client.getOutputStream());

				info=dis.readUTF().split("-");

				lf=new LeaveFrame(client,info[0],info[1],info[2],info[3],info[4],info[5],info[6]);
				lf.setVisible(true);
			}
		}
		catch(Exception ex)
		{}
	}
}
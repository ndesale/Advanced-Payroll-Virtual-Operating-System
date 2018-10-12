import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

class LeaveFrame extends JFrame implements ActionListener
{
	JTextArea ta;
	JButton btnAccept,btnReject,btnChat,btnRemind;
	Socket client;
	DataOutputStream dos;
	String sendString;

	LeaveFrame(Socket client,String empId,String empName,String deptId,String leaveType,String leaveFrom,String leaveTo,String reason)
	{
		super("Leave");

		this.client=client;

		setBounds(500,150,300,350);
		setResizable(false);

		ta=new JTextArea("Leave Permission\n\nEmployee Id: "+empId+"\nEmployee Name: "+empName+"\nDepartment Id: "+deptId+"\nLeave Type: "+leaveType+"\nLeave From: "+leaveFrom+"\nLeave To: "+leaveTo+"\nReason: "+reason+"                                 \n");
		Container c=getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.LEFT));
		Font f=new Font("Times New Roman",Font.BOLD,20);
		c.add(ta);
		ta.setFont(f);
		ta.setEditable(false);

		btnAccept=new JButton("Accept");
		btnReject=new JButton("Reject");
		btnChat=new JButton("Meet with "+empName);
		btnRemind=new JButton("Remind Later");

		c.add(btnAccept);
		c.add(btnReject);
		c.add(btnChat);
		c.add(btnRemind);

		btnChat.addActionListener(this);
		btnAccept.addActionListener(this);
		btnReject.addActionListener(this);
		btnRemind.addActionListener(this);


		sendString=empId+"-"+empName+"-"+deptId+"-"+leaveType+"-"+leaveFrom+"-"+leaveTo+"-"+reason;
	}
	public void actionPerformed(ActionEvent e)
	{	
		if(e.getSource()==btnAccept)
		{
			try
			{
				dos=new DataOutputStream(client.getOutputStream());

				dos.writeUTF(sendString);
				dos.writeInt(0);

			}catch(Exception ed){}

		}
		else if(e.getSource()==btnReject)
		{
			try
			{
				dos=new DataOutputStream(client.getOutputStream());

				dos.writeUTF(sendString);
				dos.writeInt(1);

			}catch(Exception ed){}
		}
		else if(e.getSource()==btnChat)
		{
			try
			{
				dos=new DataOutputStream(client.getOutputStream());

				dos.writeUTF(sendString);
				dos.writeInt(2);

			}catch(Exception ed){}
		}
		else if(e.getSource()==btnRemind)
		{
			try
			{
				this.setVisible(false);
				Thread.sleep(600);
				this.setVisible(true);
			}
			catch(Exception exc)
			{}
		}
		try
		{
			dos.close();
		}
		catch(Exception ed){}
	}
}
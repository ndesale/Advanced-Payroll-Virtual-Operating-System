import java.sql.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;

class LeaveEntry extends JFrame implements ActionListener
{
	JLabel lblId,lblDeptId,lblFrom,lblTo,lblReason,lblLeave,lblHead;
	
	JTextField txtId,txtFrom,txtTo;

	JComboBox cbReason,cbDeptId,cbLeave;

	JButton btnSend,btnClear;

	String name;

	Socket client;
	DataInputStream dis;
	DataOutputStream dos;

	Connection con;
	Statement stmt;
	ResultSet rs;

	LeavePopup lpp;

	LeaveEntry()
	{
		super("Leave Entry");

		this.setBounds(400,150,400,400);
		setResizable(false);

		Container c=getContentPane();
		c.setLayout(null);


		lblHead=new JLabel("Leave Entry"); 
		lblHead.setFont(new Font("Times New Roman",Font.BOLD,18));

		lblId=new JLabel("Employee Id");
		lblDeptId=new JLabel("Department Id");
		lblLeave=new JLabel("Leave Type");
		lblFrom=new JLabel("Leave From");
		lblTo=new JLabel("Leave To");
		lblReason=new JLabel("Reason of Leave");

		txtId=new JTextField(20);
		txtFrom=new JTextField(20);
		txtTo=new JTextField(20);

		cbReason=new JComboBox();
		cbReason.addItem("Family");
		cbReason.addItem("Personnel");
		cbReason.setEditable(true);

		cbDeptId=new JComboBox();
		cbDeptId.addItem("11");
		cbDeptId.addItem("12");
		cbDeptId.addItem("13");
		cbDeptId.addItem("14");
		cbDeptId.addItem("15");
		cbDeptId.addItem("16");

		cbLeave=new JComboBox();
		cbLeave.addItem("Casual");
		cbLeave.addItem("Sick");

		lblHead.setBounds(50,10,100,30);

		lblId.setBounds(50,50,100,20);
		lblDeptId.setBounds(50,90,100,20);
		lblLeave.setBounds(50,130,100,20);
		lblFrom.setBounds(50,170,100,20);
		lblTo.setBounds(50,210,100,20);
		lblReason.setBounds(50,250,100,20);

		cbReason.setBounds(150,250,200,20);
		cbDeptId.setBounds(150,90,200,20);
		cbLeave.setBounds(150,130,200,20);

		btnSend=new JButton(new ImageIcon("mail.jpg"));
		btnSend.setBounds(350,320,40,40);
		c.add(btnSend);
		btnSend.setRolloverIcon(new ImageIcon("mailshine.jpg"));
		btnSend.setToolTipText("Send Request");

		btnClear=new JButton(new ImageIcon("close.jpg"));
		btnClear.setBounds(300,320,40,40);
		c.add(btnClear);
		btnClear.setRolloverIcon(new ImageIcon("closeshine.jpg"));
		btnClear.setToolTipText("Clear Fields");
		
		c.add(lblHead);
	
		c.add(lblId);
		c.add(lblDeptId);
		c.add(lblLeave);
		c.add(lblFrom);
		c.add(lblTo);
		c.add(lblReason);

		c.add(cbReason);
		c.add(cbDeptId);
		c.add(cbLeave);

		txtId.setBounds(150,50,200,20);
		txtFrom.setBounds(150,170,200,20);
		txtTo.setBounds(150,210,200,20);

		c.add(txtId);
		c.add(txtFrom);
		c.add(txtTo);

		this.repaint();

		btnSend.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==btnSend)
		{
			try
			{
				String url="Jdbc:Odbc:NayanDesale";
				try
				{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

					con=DriverManager.getConnection(url,"","");

					stmt=con.createStatement();

					rs=stmt.executeQuery("SELECT * FROM EmplyeeDetails where EmployeeId="+txtId.getText());

					rs.next();

					name=rs.getString("EmployeeName");

					con.close();
				}
				catch(Exception exc)
				{	
					JOptionPane.showConfirmDialog(null,exc.getMessage());
				}

				this.setVisible(false);

				client=new Socket("192.168.1.18",1200);

				dis=new DataInputStream(client.getInputStream());
				dos=new DataOutputStream(client.getOutputStream());

				dos.writeUTF(txtId.getText()+"-"+name+"-"+cbDeptId.getSelectedItem()+"-"+cbLeave.getSelectedItem()+"-"+txtFrom.getText()+"-"+txtTo.getText()+"-"+cbReason.getSelectedItem());

				JOptionPane.showConfirmDialog(null,"Request Sent");

				String emp[]=dis.readUTF().split("-");
				int result=dis.readInt();

				lpp=new LeavePopup(emp[0],emp[1],emp[2],emp[3],emp[4],emp[5],emp[6],result);
				lpp.setVisible(true);

				client.close();
				dis.close();
				dos.close();
			}
			catch(Exception ex)
			{
			}
		}
	}

	public static void main(String args[])
	{
		LeaveEntry le=new LeaveEntry();
		le.setVisible(true);
	}
}
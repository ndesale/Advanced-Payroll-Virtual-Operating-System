import javax.swing.*;
import java.awt.*;
import java.net.*;
import java.sql.*;
import java.awt.event.*;

class LeavePopup extends JFrame implements ActionListener
{
	JTextArea txt;
	JButton btnOk,btnPrint;
	String from,to;

	LeavePopup(String empId,String empName,String deptId,String leave,String leaveFrom,String leaveTo,String reason,int accept)
	{
		from=leaveFrom;
		to=leaveTo;

		Container c=getContentPane();
		c.setLayout(new FlowLayout());

		setBounds(500,150,290,300);
		setResizable(false);

		txt=new JTextArea();
		txt.setFont(new Font("Times New Roman",Font.BOLD,18));
		txt.setEditable(false);
		
		btnOk=new JButton(new ImageIcon("ok.jpg"));
		btnPrint=new JButton(new ImageIcon("print.jpg"));
		btnOk.setBounds(300,350,40,40);

		btnOk.setRolloverIcon(new ImageIcon("okshine.jpg"));
		btnPrint.setRolloverIcon(new ImageIcon("printshine.jpg"));
		btnPrint.setBounds(350,350,40,40);

		c.add(txt);
		c.add(btnOk);

		btnOk.addActionListener(this);
		btnPrint.addActionListener(this);

		if(accept==0)
		{
			txt.setText("Employee Id    : "+empId+"\n"+
				    "Employee Name: "+empName+"\n"+
				    "Department Id   : "+deptId+"\n"+
				    "Leave Type      :  "+leave+"\n"+
				    "Leave From     :  "+leaveFrom+"\n"+
				    "Leave To     :  "+leaveTo+"\n"+
				    "Reason       :  "+reason+"\n\n"+
				    "Leave Accepted !!!");

			setTitle("Leave Accepted");
			c.add(btnPrint);
		}
		else if(accept==1)
		{
			txt.setText("Employee Id  : "+empId+"\n"+
				    "Employee Name: "+empName+"\n"+
				    "Department Id: "+deptId+"\n"+
				    "Leave Type   :  "+leave+"\n"+
				    "Leave From   :  "+leaveFrom+"\n"+
				    "Leave To     :  "+leaveTo+"\n"+
				    "Reason       :  "+reason+"\n\n"+
				    "Leave Rejected ???");
	
			setTitle("Leave Rejected");
		}
		else if(accept==2)
		{
			txt.setText("Employee Id  : "+empId+"\n"+
				    "Employee Name: "+empName+"\n"+
				    "Department Id: "+deptId+"\n"+
				    "Leave Type   :  "+leave+"\n"+
				    "Leave From   :  "+leaveFrom+"\n"+
				    "Leave To     :  "+leaveTo+"\n"+
				    "Reason       :  "+reason+"\n\n"+
				    "Employee is asked to meet with H.R.");

			setTitle("Leave Popup");
		}
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==btnOk)
		{
		/*	this.setVisible(false);

			String date[]=from.split("/");
			String day,month,year;
			
			day=date[0];
			month=date[1];
			year=date[2];

			Date d=new Date(year,month,day);*/
		}
		if(e.getSource()==btnPrint)
		{
		}
	}
}
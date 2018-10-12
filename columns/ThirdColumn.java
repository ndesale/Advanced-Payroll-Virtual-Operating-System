package columns;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.*;
import loading.*;
import java.util.*;
import java.awt.print.*;

public class ThirdColumn extends Thread implements ActionListener
{
	JButton l1,l2,l3,l4;
	JPanel p;
	static JPanel bottom;
	ImageIcon i;
	JFrame jf;
	

	LeaveEntry le;
	LeaveReport lr;
	LeaveSetting ls;
	
	public ThirdColumn(JFrame j,JPanel s,JPanel bts)
	{
		p=s;
		jf=j;
		bottom=bts;

		l1=new JButton(new ImageIcon("icons\\leave\\leaveentry.jpg"));
		l3=new JButton(new ImageIcon("icons\\leave\\leavereport.jpg"));
		l4=new JButton(new ImageIcon("icons\\leave\\leavesetting.jpg"));

		l1.setRolloverIcon(new ImageIcon("icons\\leave\\leaveentryshine.jpg"));
		l3.setRolloverIcon(new ImageIcon("icons\\leave\\leavereportshine.jpg"));
		l4.setRolloverIcon(new ImageIcon("icons\\leave\\leavesettingshine.jpg"));

	}
	public void run()
	{
		p.add(l1);
		p.add(l3);	
		p.add(l4);

		for(int s=0;s<=9;s++)
		{
			try
			{
				sleep(20);
			}
			catch(Exception e){}
		}
		
		l1.setBounds(450,80,100,69);

		for(int s=0;s<=6;s++)
		{
			try
			{
				sleep(20);
			}
			catch(Exception e){}
		}

		l3.setBounds(450,190,100,69);
		l4.setBounds(450,300,100,69);
		
		l1.addActionListener(this);
		l3.addActionListener(this);
		l4.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==l1)
		{
			le=new LeaveEntry();
			le.setVisible(true);
		}
		else if(e.getSource()==l3)
		{
			lr=new LeaveReport();
		}
		else if(e.getSource()==l4)
		{
			LeaveSetting ls=new LeaveSetting();
			ls.show();
		}
	}
}
class LeaveEntry extends JFrame implements ActionListener
{
	JLabel lblId,lblDeptId,lblFrom,lblTo,lblReason,lblLeave,lblHead;
	
	JTextField txtId;

	static JComboBox cbReason,cbLeave;
	JComboBox cbDeptId;

	JButton btnSend,btnClear;

	LoadingRequest lr;

	String name;

	static JComboBox jcbDate,jcbMonth,jcbYear,jcbDate1,jcbMonth1,jcbYear1;
	String date[]={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
	String month[]={"1","2","3","4","5","6","7","8","9","10","11","12"};
	String year[]={"1990","1991","1992","1993","1994","1995","1996","1997","1998","1999","2000","2001","2002","2003","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020","2021","2022","2023","2024","2025","2026","2027","2028","2029","2030"};

	Socket client;
	DataInputStream dis;
	DataOutputStream dos;

	static String dFrom,dTo;
	static java.sql.Date dateFrom,dateTo;

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

		jcbDate=new JComboBox(date);
		c.add(jcbDate);
		jcbDate.setBounds(150,170,65,20);

		jcbMonth=new JComboBox(month);
		c.add(jcbMonth);
		jcbMonth.setBounds(215,170,65,20);

		jcbYear=new JComboBox(year);
		c.add(jcbYear);
		jcbYear.setBounds(280,170,70,20);

		jcbDate1=new JComboBox(date);
		c.add(jcbDate1);
		jcbDate1.setBounds(150,210,65,20);

		jcbMonth1=new JComboBox(month);
		c.add(jcbMonth1);
		jcbMonth1.setBounds(215,210,65,20);

		jcbYear1=new JComboBox(year);
		c.add(jcbYear1);
		jcbYear1.setBounds(280,210,70,20);

		lblHead=new JLabel("Leave Entry"); 
		lblHead.setFont(new Font("Times New Roman",Font.BOLD,18));

		lblId=new JLabel("Employee Id");
		lblDeptId=new JLabel("Department Id");
		lblLeave=new JLabel("Leave Type");
		lblFrom=new JLabel("Leave From");
		lblTo=new JLabel("Leave To");
		lblReason=new JLabel("Reason of Leave");

		txtId=new JTextField(20);

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

		btnSend=new JButton(new ImageIcon("icons\\leaveicon\\mail.jpg"));
		btnSend.setBounds(350,320,40,40);
		c.add(btnSend);
		btnSend.setRolloverIcon(new ImageIcon("icons\\leaveicon\\mailshine.jpg"));
		btnSend.setToolTipText("Send Request");

		btnClear=new JButton(new ImageIcon("icons\\leaveicon\\close.jpg"));
		btnClear.setBounds(300,320,40,40);
		c.add(btnClear);
		btnClear.setRolloverIcon(new ImageIcon("icons\\leaveicon\\closeshine.jpg"));
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

		c.add(txtId);

		this.repaint();

		btnSend.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==btnSend)
		{
			dateFrom=new java.sql.Date(Integer.parseInt(jcbYear.getSelectedItem().toString()),Integer.parseInt(jcbMonth.getSelectedItem().toString()),Integer.parseInt(jcbDate.getSelectedItem().toString()));
			dateTo=new java.sql.Date(Integer.parseInt(jcbYear1.getSelectedItem().toString()),Integer.parseInt(jcbMonth1.getSelectedItem().toString()),Integer.parseInt(jcbDate1.getSelectedItem().toString()));

			dFrom=jcbDate.getSelectedItem().toString()+"/"+jcbMonth.getSelectedItem().toString()+"/"+jcbYear.getSelectedItem().toString();
			dTo=jcbDate1.getSelectedItem().toString()+"/"+jcbMonth1.getSelectedItem().toString()+"/"+jcbYear1.getSelectedItem().toString();
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

		/* Set IP Address */

				try
				{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con=DriverManager.getConnection("Jdbc:Odbc:Password","","");
					Statement stmt=con.createStatement();
					ResultSet iprs=stmt.executeQuery("Select IpSetting from Password");

					iprs.next();
					client=new Socket(iprs.getString("IpSetting"),1200);					

					con.close();
				}
				catch(Exception esd){}

		/* OVER */

				dis=new DataInputStream(client.getInputStream());
				dos=new DataOutputStream(client.getOutputStream());

				dos.writeUTF(txtId.getText()+"-"+name+"-"+cbDeptId.getSelectedItem()+"-"+cbLeave.getSelectedItem()+"-"+dFrom+"-"+dTo+"-"+cbReason.getSelectedItem());

				JOptionPane.showMessageDialog(null,"Request Sent");

				String emp[]=dis.readUTF().split("-");
				int result=dis.readInt();

				lpp=new LeavePopup(emp[0],emp[1],emp[2],emp[3],emp[4],emp[5],emp[6],result);
				lpp.setVisible(true);

				client.close();
				dis.close();
				dos.close();

				lr=new LoadingRequest(ThirdColumn.bottom);
				lr.setBounds(1200,0,35,35);
				lr.start();
				Thread.sleep(5000);
				lr.stable(false);
				lr.hide();
			}
			catch(Exception ex)
			{
			}
		}
	}
}
class LeavePopup extends JFrame implements ActionListener
{
	JTextArea txt;
	JButton btnOk,btnPrint;
	String from,to;

	Connection con;	
	PreparedStatement pstmt,pstmt2;

	int bc=0,bs=0;
	int dt=LeaveEntry.dateTo.compareTo(LeaveEntry.dateFrom)+1;


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
		
		btnOk=new JButton(new ImageIcon("icons\\leaveicon\\ok.jpg"));
		btnPrint=new JButton(new ImageIcon("icons\\leaveicon\\print.jpg"));
		btnOk.setBounds(300,350,40,40);

		btnOk.setRolloverIcon(new ImageIcon("icons\\leaveicon\\okshine.jpg"));
		btnPrint.setRolloverIcon(new ImageIcon("icons\\leaveicon\\printshine.jpg"));
		btnPrint.setBounds(350,350,40,40);

		c.add(txt);
		c.add(btnOk);

		btnOk.addActionListener(this);
		btnPrint.addActionListener(this);

		System.out.println("=============================== dt: "+dt);

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

			try
			{
				System.out.println("Perfect 1");

				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				String url1="Jdbc:Odbc:Leave";
				con=DriverManager.getConnection(url1,"","");

				System.out.println("Perfect 2");

				pstmt=con.prepareStatement("Select * from LeaveEntry where EmployeeId=?");
				pstmt.clearParameters();
				pstmt.setInt(1,Integer.parseInt(empId));

				System.out.println("Perfect 3");

				ResultSet rs=pstmt.executeQuery();

				if(rs.next())
				{
					bc=rs.getInt("BalancedCasual");
					bs=rs.getInt("BalancedSick");
				}

				rs.close();
				con.close();
				pstmt.close();

				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				String url="Jdbc:Odbc:Leave";
				con=DriverManager.getConnection(url,"","");

				pstmt=con.prepareStatement("Update LeaveEntry set BalancedCasual=? , BalancedSick=? where EmployeeId=?");
				pstmt.clearParameters();
		
				java.sql.Date dfrom=(java.sql.Date)LeaveEntry.dateFrom;
				java.sql.Date dto=(java.sql.Date)LeaveEntry.dateTo;

				if(leave.equals("Casual"))	
				{
					bc=bc-dt;
					if(bc<=0)
					{
						JOptionPane.showMessageDialog(null,"Leave is over cannot permitted ???");
					}
					else
					{
						System.out.println("Perfect 1");

						pstmt2=con.prepareStatement("Insert into LeaveStatus values(?,?,?,?,?)");

						System.out.println("Perfect 1");

						pstmt2.setString(1,empId+"_"+LeaveEntry.dFrom+"_"+LeaveEntry.dTo);
System.out.println("Perfect 11");
						pstmt2.setDate(2,dfrom);
System.out.println("Perfect 12");
						pstmt2.setDate(3,dto);
System.out.println("Perfect 13");
						pstmt2.setString(4,LeaveEntry.cbLeave.getSelectedItem().toString());
System.out.println("Perfect 14");			
						pstmt2.setString(5,LeaveEntry.cbReason.getSelectedItem().toString());
System.out.println("Perfect 15");
						if(pstmt2.executeUpdate()>0)
						System.out.println("Inserted");
						else
						System.out.println("Error");

						System.out.println("Perfect 1");
					}
				}
				else if(leave.equals("Sick"))	
				{
					bs=bs-dt;
					if(bs<=0)
					{
						JOptionPane.showMessageDialog(null,"Leave is over cannot permitted ???");
					}
					else
					{
						pstmt2=con.prepareStatement("Insert into LeaveStatus values(?,?,?,?,?)");

						pstmt2.setString(1,empId+"_"+LeaveEntry.dFrom+"_"+LeaveEntry.dTo);
						pstmt2.setDate(2,dfrom);
						pstmt2.setDate(3,dto);
						pstmt2.setString(4,LeaveEntry.cbLeave.getSelectedItem().toString());
						pstmt2.setString(5,LeaveEntry.cbReason.getSelectedItem().toString());

						pstmt2.executeUpdate();
					}
				}
				
				pstmt.setInt(1,bc);
				pstmt.setInt(2,bs);
				pstmt.setInt(3,Integer.parseInt(empId));

				pstmt.executeUpdate();
			}
			catch(Exception ex)
			{
				System.out.println("Error............: "+ex.getMessage());
			}
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
class LeaveReport extends JPanel implements ActionListener,Printable
{
	JLabel lblHeader;

	JLabel lblEmpId,lblDeptId,lblLeaveType,lblLeaveFrom,lblLeaveTo,lblLeaveReason,lblBalancedCasual,lblBalancedSick,lblEmpName;
	JLabel txtEmpId,txtDeptId,txtLeaveType,txtLeaveFrom,txtLeaveTo,txtLeaveReason,txtBalancedCasual,txtBalancedSick,txtEmpName;
	JLabel lblLeaveHeader=new JLabel("Total Leaves taken by Employee");

	java.awt.List dates;
	Connection con;
	Statement stmt;
	ResultSet rs;

	String name,deptid;

	JButton btnView=new JButton("View Report");
	JFrame f1,f;
	MenuItem print=new MenuItem("Print"),close=new MenuItem("Close");
	String id;

	LeaveReport()
	{
		id=JOptionPane.showInputDialog("Enter the Employee Id: ");
		if(id.equals(""))
		{
		}
		else
		{

		lblLeaveHeader.setFont(new Font("Times New Roman",Font.BOLD,20));
		lblLeaveHeader.setBounds(5,5,280,30);
		btnView.setBounds(80,140,130,30);

		f=new JFrame("LEAVE REPORT");
		Container c=f.getContentPane();

		c.setLayout(null);
		f.setBounds(450,250,300,200);
		f.setResizable(false);
		f.setVisible(true);
		c.add(lblLeaveHeader);
		c.add(btnView);
		btnView.addActionListener(this);

		dates=new java.awt.List(5);
		dates.setBounds(5,35,280,100);
		c.add(dates);

		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			String url="Jdbc:Odbc:Leave";
			con=DriverManager.getConnection(url,"","");
			stmt=con.createStatement();
			rs=stmt.executeQuery("Select * from LeaveStatus where LeaveId LIKE '"+id+"%'");
			while(rs.next())
			{
				String date=rs.getString("LeaveId");
				String reason=rs.getString("Type");	

				date=date+" : "+reason;
			
				dates.add(date);
			}
			con.close();
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		}
	}
	public int print(Graphics g, PageFormat format, int pagenum) 
	{
		if (pagenum > 0)
		{
			return Printable.NO_SUCH_PAGE;
		}
		else
		{
			Graphics2D g2 = (Graphics2D) g;
			g2.translate(format.getImageableX(), format.getImageableY());
			this.paint(g2);

			return Printable.PAGE_EXISTS;
		}
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==btnView)
		{
			String date=dates.getSelectedItem();
			
			if(date==null)
			{
				JOptionPane.showMessageDialog(f,"Please Select the Leave");
			}
			else
			{
				MenuBar mb=new MenuBar();
				Menu file=new Menu("File");
				mb.add(file);
				file.add(print);
				file.addSeparator();
				file.add(close);			

				f1=new JFrame("Leave Report");
				f1.setExtendedState(JFrame.MAXIMIZED_BOTH);
				Container c=f1.getContentPane();
				c.setBackground(Color.GRAY);

				c.setLayout(null);
				f1.setVisible(true);
				f1.setMenuBar(mb);
				c.add(this);

				print.addActionListener(this);
				close.addActionListener(this);

				this.setBackground(Color.WHITE);
				this.setBounds(350,0,650,700);
				f.setVisible(false);

				
		/* START LEAVE REPORT CODE HERE	*/
				this.setLayout(null);

				lblHeader=new JLabel("Employee Leave");
				lblHeader.setBounds(130,100,200,30);
				lblHeader.setFont(new Font("Times New Roman",Font.BOLD,24));

				this.add(lblHeader);
				this.setBorder(BorderFactory.createEtchedBorder(1));


				lblEmpId=new JLabel("Employee ID :",JLabel.RIGHT);
				lblEmpName=new JLabel("Employee Name :",JLabel.RIGHT);
				lblDeptId=new JLabel("Department ID :",JLabel.RIGHT);
				lblLeaveType=new JLabel("Leave Type :",JLabel.RIGHT);
				lblLeaveFrom=new JLabel("Leave From :",JLabel.RIGHT);
				lblLeaveTo=new JLabel("Leave To :",JLabel.RIGHT);
				lblLeaveReason=new JLabel("Leave Reason :",JLabel.RIGHT);
				lblBalancedCasual=new JLabel("Balanced Casual :",JLabel.RIGHT);
				lblBalancedSick=new JLabel("Balanced Sick :",JLabel.RIGHT);

				txtEmpId=new JLabel("Employee ID :",JLabel.LEFT);
				txtEmpName=new JLabel();
				txtDeptId=new JLabel();
				txtLeaveType=new JLabel();
				txtLeaveFrom=new JLabel();
				txtLeaveTo=new JLabel();
				txtLeaveReason=new JLabel();
				txtBalancedCasual=new JLabel();
				txtBalancedSick=new JLabel();

				lblEmpId.setBounds(10,180,200,30);
				lblEmpName.setBounds(10,210,200,30);
				lblDeptId.setBounds(10,240,200,30);
				lblLeaveType.setBounds(10,270,200,30);
				lblLeaveFrom.setBounds(10,300,200,30);
				lblLeaveTo.setBounds(10,330,200,30);
				lblLeaveReason.setBounds(10,360,200,30);
				lblBalancedCasual.setBounds(10,390,200,30);
				lblBalancedSick.setBounds(10,420,200,30);

				txtEmpId.setBounds(260,180,200,30);
				txtEmpName.setBounds(260,210,200,30);
				txtDeptId.setBounds(260,240,200,30);
				txtLeaveType.setBounds(260,270,200,30);
				txtLeaveFrom.setBounds(260,300,200,30);
				txtLeaveTo.setBounds(260,330,200,30);
				txtLeaveReason.setBounds(260,360,200,30);
				txtBalancedCasual.setBounds(260,390,200,30);
				txtBalancedSick.setBounds(260,420,200,30);

				this.add(lblEmpId);
				this.add(lblEmpName);
				this.add(lblDeptId);
				this.add(lblLeaveType);
				this.add(lblLeaveFrom);
				this.add(lblLeaveTo);
				this.add(lblLeaveReason);
				this.add(lblBalancedCasual);
				this.add(lblBalancedSick);
		
				this.add(txtEmpId);
				this.add(txtEmpName);
				this.add(txtDeptId);
				this.add(txtLeaveType);
				this.add(txtLeaveFrom);
				this.add(txtLeaveTo);
				this.add(txtLeaveReason);
				this.add(txtBalancedCasual);
				this.add(txtBalancedSick);

				try
				{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					con=DriverManager.getConnection("Jdbc:Odbc:NayanDesale","","");
					stmt=con.createStatement();
					rs=stmt.executeQuery("Select EmployeeName,DeptID from EmplyeeDetails where EmployeeId="+id);
					rs.next();
					name=rs.getString("EmployeeName");
					deptid=rs.getString("DeptID");
					con.close();
				}
				catch(Exception ex1){}

				txtEmpId.setText(id);
				txtEmpName.setText(name);
				txtDeptId.setText(deptid);


				int balc,bals;
				try
				{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					con=DriverManager.getConnection("Jdbc:Odbc:Leave","","");
					stmt=con.createStatement();

					rs=stmt.executeQuery("Select * from LeaveEntry where EmployeeId="+id);
					rs.next();

					balc=rs.getInt("BalancedCasual");
					bals=rs.getInt("BalancedSick");

					con.close();

					txtBalancedCasual.setText(balc+"");
					txtBalancedSick.setText(bals+"");
				}
				catch(Exception es)
				{
					JOptionPane.showMessageDialog(null,es.getMessage());
				}

				try
				{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					con=DriverManager.getConnection("Jdbc:Odbc:Leave","","");
					stmt=con.createStatement();

					String item=dates.getSelectedItem();
					String lid[]=item.split(" : ");

					rs=stmt.executeQuery("Select * from LeaveStatus where LeaveId='"+lid[0]+"'");
					rs.next();

					String lr,lf,lty,lt;
					java.sql.Date d1,d2;

					d1=rs.getDate("From");
					d2=rs.getDate("To");

					d1.setYear(d1.getYear()-1900);
					d2.setYear(d2.getYear()-1900);

					lty=rs.getString("Type");
					lr=rs.getString("Reason");

					con.close();

					txtLeaveType.setText(lty);
					txtLeaveReason.setText(lr);
					txtLeaveFrom.setText(new java.text.SimpleDateFormat("dd-MM-yyyy").format(d1));
					txtLeaveTo.setText(new java.text.SimpleDateFormat("dd-MM-yyyy").format(d2));		
				}
				catch(Exception es)
				{
					JOptionPane.showMessageDialog(null,es.getMessage());
				}
			}
		}
		else if(e.getSource()==close)
		{
			f1.setVisible(false);
			f.setVisible(true);
		}
		else if(e.getSource()==print)
		{
			try
			{
				PrinterJob pj = PrinterJob.getPrinterJob();
				PageFormat format = pj.pageDialog(pj.defaultPage());
				pj.setPrintable(this, format);
				
				if (pj.printDialog()) 
				pj.print();
			}
			catch(Exception ex1)
			{}
		}
	}
	public static void main(String args[])
	{
		LeaveReport lp=new LeaveReport();
	}
}
class LeaveSetting extends JFrame implements ActionListener
{
	JTabbedPane  jtp;
	JLabel lblTotalCasual,lblTotalSick,lblEmpSet,lblManagerSet,lblTotalCasual1,lblTotalSick1,lblUname,lblPasswd,p2lblUname,p2lblPasswd,p2lblhra,p2lblda,p2lblca,p2lblma,p2lblea,p2lblta,p2lblenterAll,p2lblpt,p2lblpf,lblHead,lblUname1,lblPasswd1,lblnewUname,lblnewPasswd;
	JTextField txtTotalCasual,txtTotalSick,txtTotalCasual1,txtTotalSick1,txtUname,p2txtUname,txtUname1,txtnewUname;
	JPasswordField txtPasswd,p2txtPasswd,txtPasswd1,txtnewPasswd;
	JButton btnUpgrade,btnUpdate,p2btnUpgrade,p2btnUpdate,btnOk,p2btnOk;
	JFrame f1,f2;
	JPanel p1,p2;
	Container c;
	Connection con;
	Statement stmt,stmt1,stmt2;
	String query,url;
	ResultSet rs,rs1;
	String AdminPasswd,LeavePasswd;
	static int count=0;
	LeaveSetting()
	{

		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			url="Jdbc:Odbc:Password";
			con=DriverManager.getConnection(url,"","");
			stmt=con.createStatement();
			query="Select AdminUName,LeavePassword from Password";
			rs=stmt.executeQuery(query);
			if(rs.next())
			{
				AdminPasswd=rs.getString("AdminUName");
				LeavePasswd=rs.getString("LeavePassword");
				System.out.println(AdminPasswd);
				System.out.println(LeavePasswd);

			}	
			con.close();
			stmt.close();
		}
		catch(Exception ee)
		{
			System.out.println(ee.getMessage());
			try
			{
				con.close();
				stmt.close();
			}
			catch(Exception c)
			{
			}
		}
			
		c=getContentPane();
		setBounds(450,120,530,420);
	//	-------------Panel1 Design---------------------
		
		p1=new JPanel();
		p1.setLayout(null);
		
		lblTotalCasual=new JLabel("Total Casual");
		lblTotalCasual.setFont(new Font("Arial",Font.BOLD,18));
		
		lblTotalSick=new JLabel("Total Sick");
		lblTotalSick.setFont(new Font("Arial",Font.BOLD,18));

		lblEmpSet=new JLabel("Employee Leave Setting ");
		lblEmpSet.setFont(new Font("Arial",Font.BOLD,22));

		lblTotalCasual.setBounds(50,100,120,20);
		lblTotalSick.setBounds(50,150,120,20);
		lblEmpSet.setBounds(60,10,300,40);
		
		p1.add(lblTotalCasual);
		p1.add(lblTotalSick);
		p1.add(lblEmpSet);

		txtTotalCasual=new JTextField(20);
		txtTotalSick=new JTextField(20);
		
		txtTotalCasual.setBounds(200,100,120,20);
		txtTotalSick.setBounds(200,150,120,20);

		p1.add(txtTotalCasual);
		p1.add(txtTotalSick);

		btnUpgrade=new JButton("Upgrade");
		btnUpdate=new JButton("Update");
		btnUpdate.setEnabled(false);

		btnUpgrade.setBounds(50,250,100,30);
		btnUpdate.setBounds(200,250,100,30);
	
		p1.add(btnUpgrade);
		p1.add(btnUpdate);

		btnUpgrade.addActionListener(this);
		btnUpdate.addActionListener(this);

		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			url="Jdbc:Odbc:Leave";	
			con=DriverManager.getConnection(url,"","");
		System.out.println("Test1");
			stmt1=con.createStatement();
		System.out.println("Test2");
			stmt2=con.createStatement();
		System.out.println("Test3");
		System.out.println("Test4");
		
			
		System.out.println("Test5");
			query="Select EmployeeId from EmplyeeDetails where Designation='Employee'";

			rs=stmt1.executeQuery(query);
		
		System.out.println("Test6");
			if(rs.next())
			{
				int a=rs.getInt("EmployeeId");
				String query1="Select TotalCasual,TotalSick from LeaveEntry where EmployeeId="+a;
				System.out.println("Test7");
				rs1=stmt2.executeQuery(query1);
				System.out.println("Test8");
			}
			if(rs1.next())
			{
				txtTotalCasual.setText(rs1.getInt("TotalCasual")+"");
				txtTotalSick.setText(rs1.getInt("TotalSick")+"");

				txtTotalCasual.setEditable(false);
				txtTotalSick.setEditable(false);
			}
			else
				System.out.println("Data Not Found");

			con.close();
			stmt1.close();
			stmt2.close();
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			try
			{
				con.close();
				
			}
			catch(Exception ex)	
			{
			}
		}

	//	------Panel2 Design--------------------------

		p2=new JPanel();
		p2.setLayout(null);
		
		lblTotalCasual1=new JLabel("Total Casual");
		lblTotalCasual1.setFont(new Font("Arial",Font.BOLD,18));
		
		lblTotalSick1=new JLabel("Total Sick");
		lblTotalSick1.setFont(new Font("Arial",Font.BOLD,18));

		lblManagerSet=new JLabel("Manager Leave Setting ");
		lblManagerSet.setFont(new Font("Arial",Font.BOLD,22));

		lblTotalCasual1.setBounds(50,100,120,20);
		lblTotalSick1.setBounds(50,150,120,20);
		lblManagerSet.setBounds(60,10,300,40);
		
		p2.add(lblTotalCasual1);
		p2.add(lblTotalSick1);
		p2.add(lblManagerSet);

		txtTotalCasual1=new JTextField(20);
		txtTotalSick1=new JTextField(20);
		
		txtTotalCasual1.setBounds(200,100,120,20);
		txtTotalSick1.setBounds(200,150,120,20);

		p2.add(txtTotalCasual1);
		p2.add(txtTotalSick1);

		p2btnUpgrade=new JButton("Upgrade");
		p2btnUpdate=new JButton("Update");

		p2btnUpgrade.setBounds(50,250,100,30);
		p2btnUpdate.setBounds(200,250,100,30);
		p2btnUpdate.setEnabled(false);
	
		p2.add(p2btnUpgrade);
		p2.add(p2btnUpdate);
	
		p2btnUpgrade.addActionListener(this);
		p2btnUpdate.addActionListener(this);
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			url="Jdbc:Odbc:Leave";	
			con=DriverManager.getConnection(url,"","");
		System.out.println("Test1");
			Statement stmt3=con.createStatement();
		System.out.println("Test2");
			Statement stmt4=con.createStatement();
		System.out.println("Test3");
		System.out.println("Test4");
		
			
		System.out.println("Test5");
			query="Select EmployeeId from EmplyeeDetails where Designation='Manager'";

			rs=stmt3.executeQuery(query);
		
		System.out.println("Test6");
			if(rs.next())
			{
				int s=rs.getInt("EmployeeId");
				String query2="Select TotalCasual,TotalSick from LeaveEntry where EmployeeId="+s;
				System.out.println("Test7");
				rs1=stmt4.executeQuery(query2);
				System.out.println("Test8");
			}
			if(rs1.next())
			{
				txtTotalCasual1.setText(rs1.getInt("TotalCasual")+"");
				txtTotalSick1.setText(rs1.getInt("TotalSick")+"");

				txtTotalCasual1.setEditable(false);
				txtTotalSick1.setEditable(false);
			}
			else
				System.out.println("Data Not Found");

			con.close();
			stmt1.close();
			stmt2.close();
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			try
			{
				con.close();
				
			}
			catch(Exception ex)	
			{
			}
		}
		
		jtp=new JTabbedPane();
		jtp.setTabPlacement(JTabbedPane.LEFT);
		jtp.add("Employee Leave Setting ",p1);
		jtp.add("Manager Leave Setting ",p2);
			
		c.add(jtp);
		
		
		
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==btnUpgrade)
		{
			f1=new JFrame("Login");
			f1.setVisible(true);
			f1.setBounds(650,200,300,200);
			f1.setLayout(null);
			lblUname=new JLabel("User Name");
			lblPasswd=new JLabel("Password");
			
			lblUname.setBounds(30,30,100,20);	
			lblPasswd.setBounds(30,80,100,20);
	
			f1.add(lblUname);
			f1.add(lblPasswd);
	
			txtUname=new JTextField(20);
			txtPasswd=new JPasswordField(20);

			txtUname.setBounds(130,30,130,20);
			txtPasswd.setBounds(130,80,130,20);
	
			f1.add(txtUname);
			f1.add(txtPasswd);
	
			btnOk=new JButton("OK");
			btnOk.setBounds(100,130,80,30);
			f1.add(btnOk);
			btnOk.addActionListener(this);
		}	
		if(e.getSource()==btnOk)
		{		
		
			String str1,str2;
			str1=txtUname.getText();
			str2=txtPasswd.getText();	
			if(str1.equals(AdminPasswd)&&str2.equals(LeavePasswd))
			{
				f1.setVisible(false);
				txtTotalCasual.setEditable(true);
				txtTotalSick.setEditable(true);
				btnUpdate.setEnabled(true);
			}
			else
			{
				f1.setVisible(false);
				JOptionPane.showMessageDialog((Component)null,"Wrong User Name and Password","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		if(e.getSource()==btnUpdate)
		{
			int c,s;
			c=Integer.parseInt(txtTotalCasual.getText());
			s=Integer.parseInt(txtTotalSick.getText());
			System.out.println("Update Button is Pressed");
			try
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				url="Jdbc:Odbc:Leave";	
				con=DriverManager.getConnection(url,"","");
				System.out.println("Test1");
				Statement stmt3=con.createStatement();
				System.out.println("Test2");
				Statement stmt4=con.createStatement();
				System.out.println("Test3");
				System.out.println("Test4");
				query="Select EmployeeId from EmplyeeDetails where Designation='Employee'";
				rs=stmt3.executeQuery(query);
				while(rs.next())
				{
					int q=rs.getInt("EmployeeId");
					String qe="Update LeaveEntry set TotalCasual="+c+", TotalSick="+s+" where EmployeeId="+q;
					int w=stmt4.executeUpdate(qe);	
					if(w>0)
					{
						count=1;
					}

				}
				if(count>0)
					JOptionPane.showMessageDialog((Component)null,"Values Updated","Updated",JOptionPane.INFORMATION_MESSAGE);
				con.close();
				
			}
			catch(Exception cc)
			{
				System.out.println(cc.getMessage());
				try
				{
					con.close();
				}
				catch(Exception cx){}
			}
		
			
			System.out.println("Test5");
		
			
		}
		if(e.getSource()==p2btnUpgrade)
		{
			System.out.println("UpGrade Button pressed");

			f2=new JFrame("Login");
			f2.setVisible(true);
			f2.setBounds(650,200,300,200);
			f2.setLayout(null);
			p2lblUname=new JLabel("User Name");
			p2lblPasswd=new JLabel("Password");
			
			p2lblUname.setBounds(30,30,100,20);	
			p2lblPasswd.setBounds(30,80,100,20);
	
			f2.add(p2lblUname);
			f2.add(p2lblPasswd);
	
			p2txtUname=new JTextField(20);
			p2txtPasswd=new JPasswordField(20);
	

			p2txtUname.setBounds(130,30,130,20);
			p2txtPasswd.setBounds(130,80,130,20);
	
			f2.add(p2txtUname);
			f2.add(p2txtPasswd);
	
			p2btnOk=new JButton("OK");
			p2btnOk.setBounds(100,130,80,30);
			f2.add(p2btnOk);
			
			p2btnOk.addActionListener(this);
		}
		if(e.getSource()==p2btnOk)
		{
			System.out.println("OK Button is pressed");
			
			String str1,str2;
			str1=p2txtUname.getText();
			str2=p2txtPasswd.getText();	
			if(str1.equals(AdminPasswd)&&str2.equals(LeavePasswd))
			{
				System.out.println("Password is correct");
				f2.setVisible(false);
				txtTotalCasual1.setEditable(true);
				txtTotalSick1.setEditable(true);
				p2btnUpdate.setEnabled(true);
			}
			else
			{
				f2.setVisible(false);
				JOptionPane.showMessageDialog((Component)null,"Wrong User Name and Password","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		if(e.getSource()==p2btnUpdate)
		{
			int cc,ss;
			cc=Integer.parseInt(txtTotalCasual1.getText());
			ss=Integer.parseInt(txtTotalSick1.getText());
			System.out.println("Update Button is Pressed");
			try
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				url="Jdbc:Odbc:Leave";	
				con=DriverManager.getConnection(url,"","");
				System.out.println("Test1");
				Statement stmt3=con.createStatement();
				System.out.println("Test2");
				Statement stmt4=con.createStatement();
				System.out.println("Test3");
				System.out.println("Test4");
				query="Select EmployeeId from EmplyeeDetails where Designation='Manager'";
				rs=stmt3.executeQuery(query);
				while(rs.next())
				{
					int q=rs.getInt("EmployeeId");
					String qe="Update LeaveEntry set TotalCasual="+cc+", TotalSick="+ss+" where EmployeeId="+q;
					int w=stmt4.executeUpdate(qe);	
					if(w>0)
					{
						count=1;
					}

				}
				if(count>0)
					JOptionPane.showMessageDialog((Component)null,"Values Updated","Updated",JOptionPane.INFORMATION_MESSAGE);
				con.close();
				
			}
			catch(Exception ce)
			{
				System.out.println(ce.getMessage());
				try
				{
					con.close();
				}
				catch(Exception cx){}
			}
		
			
			System.out.println("Test5");
		
			
		}

	}
}
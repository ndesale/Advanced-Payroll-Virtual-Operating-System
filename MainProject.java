import javax.swing.*;
import java.io.*;
import java.net.*;
import java.applet.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.text.*;
import loading.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;

import columns.*;

class MainProject extends JFrame implements ActionListener
{
	JButton start,turnOff,restart,lock;
	static JButton desktop,home;
	static JPanel top,bottom,mainPanel;
	JButton min=new JButton(new ImageIcon("icons//min2.jpg")),close=new JButton(new ImageIcon("icons//close.jpg"));

	static boolean login=false,boollock=false;

	static JLabel lblDate,lblCal=new JLabel(new ImageIcon("icons\\date.jpg"));
	static JLabel lblD=new JLabel();

	JLabel lblAddEmp,lblDel,lblSearch,lblUpdate,lblBasic,lblIncentives,lblDeduction,lblBonus;
	JLabel lblLeaveEntry,lblLeaveStatus,lblLeaveReport,lblLeaveSettings;
	JLabel lblSystemSettings,lblRecycle,lblDisplay,lblInc,lblPayslip;

	static int count=0;
	
	LoginForm lf;

	DateToolTip dtt;

	JFrameBackground wall;

	Runtime rn;
	
	FirstColumn f1;
	SecondColumn f2;
	ThirdColumn f3;
	ForthColumn f4;
	FifthColumn f5;

	LoadingRing lr;
	LoadingRequest le;
	
	MainProject()
	{
		super("Main Project");
	}
	void setup()
	{
		Container c=getContentPane();
		c.setLayout(null);
		c.setBackground(Color.WHITE);

		setLayout(null);
	
		Date dte=new Date();

		lblD.setFont(new Font("Times New Roman",Font.BOLD,20));
		lblD.setText(dte.getDate()+"");
		lblD.setBounds(40,40,20,20);
		lblCal.add(lblD);
		SimpleDateFormat sdf=new SimpleDateFormat("dd - MM - yyyy");
		lblCal.setToolTipText("Date: "+sdf.format(dte));

		lblCal.setBounds(0,41,100,100);
		add(lblCal);

		lr=new LoadingRing(c);
		lr.start();

		try
		{
			Thread.sleep(2000);
		}catch(Exception exc){}

		turnOff=new JButton("",new ImageIcon("icons\\shutdown.png"));
		restart=new JButton("",new ImageIcon("icons\\restart.png"));
		lock=new JButton("",new ImageIcon("icons\\password.jpg"));
		home=new JButton("",new ImageIcon("icons\\home.jpg"));
		home.setToolTipText("Payroll Home");
		turnOff.setToolTipText("Turn Off");
		restart.setToolTipText("Restart");
		desktop=new JButton(new ImageIcon("icons\\desktop.jpg"));
		desktop.setToolTipText("Show Desktop");
		lock.setToolTipText("Log Off...");

		home.setRolloverIcon(new ImageIcon("icons\\homeShine.jpg"));

		mainPanel=new JPanel();
		mainPanel.setBounds(0,40,1366,694);
		mainPanel.setBackground(new Color(0,0,0,FifthColumn.mainPanel));
		add(mainPanel);
		mainPanel.setLayout(null);
		mainPanel.setVisible(false);

		top=new JPanel();
		top.setBounds(0,0,1366,40);
		top.setBackground(new Color(0,0,0,FifthColumn.top));
		add(top);

		lf=new LoginForm(this);

		bottom=new JPanel();
		bottom.setBounds(0,733,1366,35);
		bottom.setBackground(new Color(0,0,0,FifthColumn.bottom));
		add(bottom);
		bottom.setVisible(false);

		top.setLayout(null);

		top.add(restart);
		top.add(turnOff);

		wall=new JFrameBackground(this);
		wall.setWallpaper("icons\\wall.jpg");

	/* Setting WallPaper */

		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("Jdbc:Odbc:Password","","");
			Statement stmt=con.createStatement();
			ResultSet wallrs=stmt.executeQuery("Select * from Password");

			wallrs.next();

			wall=new JFrameBackground(this);
			wall.setWallpaper(wallrs.getString("WallPath"));

			con.close();
		}
		catch(Exception esd)
		{
			JOptionPane.showMessageDialog(null,esd.getMessage());
		}

	/* OVER */

		turnOff.setBounds(1325,0,40,40);
		restart.setBounds(1285,0,40,40);

		min.setBounds(40,0,40,40);
		top.add(min);
		min.addActionListener(this);
		min.setToolTipText("Minimize");

		close.setBounds(0,0,40,40);
		top.add(close);
		close.addActionListener(this);
		close.setToolTipText("Close System");

		close.addActionListener(this);

		repaint();

		lr.stable(false);
		lr.hide();

		while(!login)
		{
		}
		bottom.setVisible(true);

		lf.hide();

		repaint();
	
		lblDate=new JLabel(new ImageIcon("icons\\clock.jpg"));
		lblDate.setBounds(1286,0,35,35);

		bottom.setLayout(null);

		bottom.add(lock);
		bottom.add(home);
		bottom.add(lblDate);
		
		Date d=new Date();
		lblDate.setToolTipText(d.toString());

		lock.setBounds(1331,0,35,35);
		home.setBounds(0,0,35,35);

		desktop.setBounds(0,0,0,0);
		bottom.add(desktop);

		turnOff.addActionListener(this);
		restart.addActionListener(this);
	
		repaint();

		home.addActionListener(this);
		desktop.addActionListener(this);
		lock.addActionListener(this);

		dtt=new DateToolTip(d);
		dtt.start();
		
	/*	le=new LoadingRequest(bottom);
		le.setBounds(1200,0,60,35);
		le.start();*/
	}
	void setLabels()
	{
		lblAddEmp=new JLabel("Add Employee");
		lblDel=new JLabel("Delete Employee");
		lblSearch=new JLabel("Search");
		lblUpdate=new JLabel("Update Employee");

		lblAddEmp.setForeground(Color.WHITE);
		lblDel.setForeground(Color.WHITE);
		lblSearch.setForeground(Color.WHITE);
		lblUpdate.setForeground(Color.WHITE);

		lblBasic=new JLabel("Basic Salary");
		lblIncentives=new JLabel("Incentives");
		lblDeduction=new JLabel("Deduction");
		lblBonus=new JLabel("Bonus");

		lblInc=new JLabel("Salary Increment");
		lblInc.setBounds(850,480,100,20);
		lblInc.setForeground(Color.WHITE);

		lblBasic.setForeground(Color.WHITE);
		lblIncentives.setForeground(Color.WHITE);
		lblDeduction.setForeground(Color.WHITE);
		lblBonus.setForeground(Color.WHITE);
		
		lblLeaveEntry=new JLabel("Leave Entry");
		lblLeaveReport=new JLabel("Leave Report");
		lblLeaveSettings=new JLabel("Leave Settings");

		lblPayslip=new JLabel("PaySlip");
		lblPayslip.setBounds(650,150,100,20);

		lblLeaveEntry.setForeground(Color.WHITE);
		lblLeaveReport.setForeground(Color.WHITE);
		lblLeaveSettings.setForeground(Color.WHITE);
		lblPayslip.setForeground(Color.WHITE);

		lblSystemSettings=new JLabel("System Settings");
		lblRecycle=new JLabel("Recycle Bin");
		lblDisplay=new JLabel("Display Settings");

		lblSystemSettings.setForeground(Color.WHITE);
		lblRecycle.setForeground(Color.WHITE);
		lblDisplay.setForeground(Color.WHITE);

		lblAddEmp.setBounds(50,150,100,20);
		lblDel.setBounds(50,270,100,20);
		lblSearch.setBounds(50,370,100,20);
		lblUpdate.setBounds(50,480,100,20);

		lblBasic.setBounds(250,150,100,20);
		lblIncentives.setBounds(250,270,100,20);
		lblDeduction.setBounds(250,370,100,20);
		lblBonus.setBounds(250,480,100,20);

		lblLeaveEntry.setBounds(450,150,100,20);
		lblLeaveReport.setBounds(450,270,100,20);
		lblLeaveSettings.setBounds(450,370,100,20);

		lblSystemSettings.setBounds(850,150,100,20);
		lblRecycle.setBounds(850,270,100,20);
		lblDisplay.setBounds(850,370,100,20);

		mainPanel.add(lblAddEmp);
		mainPanel.add(lblDel);
		mainPanel.add(lblSearch);
		mainPanel.add(lblUpdate);

		mainPanel.add(lblBasic);
		mainPanel.add(lblIncentives);
		mainPanel.add(lblDeduction);
		mainPanel.add(lblBonus);

		mainPanel.add(lblLeaveEntry);
		mainPanel.add(lblLeaveReport);
		mainPanel.add(lblLeaveSettings);
		mainPanel.add(lblPayslip);
	
		mainPanel.add(lblSystemSettings);
		mainPanel.add(lblRecycle);
		mainPanel.add(lblDisplay);
		mainPanel.add(lblInc);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==home)
		{
			if(count==0)
			{
				mainPanel.setVisible(true);

				setLabels();

				f1=new FirstColumn(this,mainPanel);
				f2=new SecondColumn(this,mainPanel);
				f3=new ThirdColumn(this,mainPanel,bottom);
				f4=new ForthColumn(this,mainPanel);
				f5=new FifthColumn(this,mainPanel);

				f1.start();
				f2.start();
				f3.start();
				f4.start();
				f5.start();
				count=1;

				lblCal.setVisible(false);
			}
			else
			{
				mainPanel.setVisible(false);
				count=0;
				lblCal.setVisible(true);
			}
		}
		else if(e.getSource()==lock)
		{
			mainPanel.setVisible(false);
			bottom.setVisible(false);

			lf.show();

			JOptionPane.showMessageDialog(null,"The System will Log off.......","Log Off......",JOptionPane.OK_CANCEL_OPTION);
		}
		else if(e.getSource()==turnOff)
		{
			int res=JOptionPane.showConfirmDialog(this,"The System will Turn Off\nSure ??","Turn Off",JOptionPane.OK_CANCEL_OPTION);
			if(res==JOptionPane.OK_OPTION)
			{
				try
				{
					rn=Runtime.getRuntime();
					rn.exec("shutdown -s -p");
				}catch(Exception ex){}

				lr=new LoadingRing(this);
				lr.start();
			}
		}
		else if(e.getSource()==restart)
		{
			int res=JOptionPane.showConfirmDialog(this,"The System will Restart\nSure ??","System Restart",JOptionPane.OK_CANCEL_OPTION);
			if(res==JOptionPane.OK_OPTION)
			{
				try
				{
					rn=Runtime.getRuntime();
					rn.exec("shutdown -r");
				}catch(Exception ex){}

				lr=new LoadingRing(this);
				lr.start();
			}
		}
		else if(e.getSource()==min)
		{
			this.setState(Frame.ICONIFIED);
		}
		else if(e.getSource()==close)
		{
			System.exit(0);
		}
	}
	public static void main(String args[])
	{
		DisplayChanged dc=new DisplayChanged();
		dc.start();
	}
}
class DisplayChanged extends Thread
{
	MainProject m;
	DefaultJFrame f;

	public void run()
	{
		m=new MainProject();
		f=new DefaultJFrame(m);
		f.setExtendedJFrame();
		m.setup();

		while(true)
		{
			if(FifthColumn.displayChanged==1)
			{
				FifthColumn.displayChanged=0;
				
				m.setVisible(false);
				m=new MainProject();
				DefaultJFrame f=new DefaultJFrame(m);
				f.setExtendedJFrame();
				m.setup();
			}
		}
	}
}
class DateToolTip extends Thread
{
	Date d;
	SimpleDateFormat sdf;
	DateToolTip(Date d)
	{
		this.d=d;
	}
	public void run()
	{
		while(true)
		{
			d=new Date();
			sdf=new SimpleDateFormat("HH:mm");
			MainProject.lblDate.setToolTipText("Time: "+sdf.format(d));
		}
	}
}
class LoginForm implements ActionListener
{
	JFrame parent;
	JPanel p;
	JLabel lblUName,lblPasswd;
	TextField txtUName,txtPasswd;
	JButton btnLogin,btnClear;
	EmployeeAnniversary ea;

	LoginForm(JFrame f)
	{
		parent=f;

		lblUName=new JLabel("UserName");
		lblPasswd=new JLabel("Password");

		txtUName=new TextField(20);
		txtPasswd=new TextField(20);

		btnLogin=new JButton(new ImageIcon("icons\\login.jpg"));
		btnClear=new JButton("Clear");

		btnLogin.setRolloverIcon(new ImageIcon("icons\\loginshine.jpg"));

		p=new JPanel();
		p.setBackground(new Color(0,0,0,50));
		parent.add(p);
		p.setBounds(50,500,280,150);
		p.setVisible(true);

		p.setLayout(null);

		p.add(lblUName);
		p.add(lblPasswd);
		p.add(txtUName);
		p.add(txtPasswd);
		p.add(btnLogin);
		p.add(btnClear);

		lblUName.setBounds(10,10,100,20);
		lblPasswd.setBounds(10,50,100,20);
		txtUName.setBounds(110,10,150,20);
		txtPasswd.setBounds(110,50,150,20);

		txtPasswd.setEchoChar('*');

		btnLogin.setBounds(50,100,87,27);
		btnClear.setBounds(150,100,87,27);

		btnLogin.addActionListener(this);
		btnClear.addActionListener(this);
	}
	void hide()
	{
		p.setVisible(false);
		MainProject.login=true;
		
		MainProject.bottom.setVisible(true);
		clear();
	}
	void show()
	{
		p.setVisible(true);
		MainProject.login=false;
	}
	void clear()
	{
		txtUName.setText("");
		txtPasswd.setText("");
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==btnLogin)
		{
			Connection con=null;
			Statement stmt;
			ResultSet rs;
			String un="",pw="";
			
			try
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				String url="jdbc:odbc:Password";

				con=DriverManager.getConnection(url,"","");
				stmt=con.createStatement();

				rs=stmt.executeQuery("Select * from Password");

				rs.next();
				System.out.println("Password Retrived successfully!!!");
				un=rs.getString("AdminUName");
				pw=rs.getString("AdminPassword");
				
				con.close();
			}
			catch(Exception ex)
			{
				try
				{
					con.close();
				}catch(Exception edf){}
			}
			if(true)
			{
				hide();
				JOptionPane.showMessageDialog(null,"Welcome To The System","Welcome",JOptionPane.OK_CANCEL_OPTION);

				ea=new EmployeeAnniversary(parent);
				ea.start();
			}
			else
			JOptionPane.showMessageDialog(null,"Incorrect Username or password.\nPlease Enter valid username or password","Incorrect Password ???",JOptionPane.OK_CANCEL_OPTION);
		}
		if(e.getSource()==btnClear)
		{
			clear();
		}
	}
}

class EmployeeAnniversary extends Thread
{
	Connection con;
	Statement stmt;
	ResultSet rs;
	Display di;
	JFrame jf;

	EmployeeAnniversary(JFrame gf)
	{
		jf=gf;
	}

	public void run()
	{
		try
		{
			Date today=new Date();
			today.setYear(today.getYear()+1900);
			System.out.println("Date: "+today.getYear());

			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

			String url="Jdbc:Odbc:NayanDesale";

			con=DriverManager.getConnection(url,"","");

			stmt=con.createStatement();

			rs=stmt.executeQuery("Select * from EmplyeeDetails");

			while(rs.next())
			{
				Date doj=rs.getDate("DOJ");

				String nm=rs.getString("EmployeeName");
				int id=rs.getInt("EmployeeId");
				int deptId=rs.getInt("DeptID");
				String desg=rs.getString("Designation");

				System.out.println(""+doj.getYear());

				if((today.getDate()==doj.getDate())&&(today.getMonth()==doj.getMonth()))
				{
					di=new Display(id,nm,deptId,desg,doj,jf);
					di.setVisible(true);
				}
			}
			con.close();	
			System.out.println("Exit");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}
class Display extends Dialog implements ActionListener
{
	JTextArea txt;
	JButton btnOk;
	JLabel lbl;

	Display(int id,String nm,int deptid,String desg,Date dt,JFrame jf)
	{
		super((Frame)jf,"Employee Anniversary",true);

		setLayout(new FlowLayout());
		setResizable(false);

		setBounds(550,250,200,220);

		lbl=new JLabel("Employee Anniversary");
		lbl.setFont(new Font("Times New Roman",Font.BOLD,20));

		txt=new JTextArea();
		txt.setFont(new Font("Times New Roman",Font.BOLD,14));

		btnOk=new JButton("OK");

		add(lbl);
		add(txt);
		add(btnOk);

		btnOk.addActionListener(this);
	
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");

		txt.setText("\nEmployee Id  : "+id+"\nEmployee NM: "+nm+"\nDept Id        : "+deptid+"\nDesignation : "+desg+"\nDOJ: "+sdf.format(dt));
		txt.setEditable(false);
	}
	public void actionPerformed(ActionEvent e)
	{
        		if(e.getSource()==btnOk)
		{
			setVisible(false);
		}
	}
}
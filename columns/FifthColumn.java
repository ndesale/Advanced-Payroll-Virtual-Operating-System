package columns;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class FifthColumn extends Thread implements ActionListener
{
	JButton l1,l2,l3,l4;
	JPanel p;
	ImageIcon i;
	JFrame j;
	DisplaySettings ds;
	RecycleBin rb;
	DemoSetting dset;
	DemoIncrement di;

	public static int top=100,bottom=100,mainPanel=50;
	public static int displayChanged=0;
	
	public FifthColumn(JFrame j,JPanel s)
	{
		this.j=j;
		p=s;

		l1=new JButton(new ImageIcon("icons\\settings\\Setting.jpg"));
		l2=new JButton(new ImageIcon("icons\\utilities\\recyclebin.jpg"));
		l3=new JButton(new ImageIcon("icons\\settings\\display.jpg"));
		l4=new JButton(new ImageIcon("icons\\settings\\Increment.jpg"));

		l1.setRolloverIcon(new ImageIcon("icons\\settings\\Settingshine.jpg"));
		l2.setRolloverIcon(new ImageIcon("icons\\utilities\\recyclebinshine.jpg"));
		l3.setRolloverIcon(new ImageIcon("icons\\settings\\displayshine.jpg"));
		l4.setRolloverIcon(new ImageIcon("icons\\settings\\IncrementShine.jpg"));
	}
	public void run()
	{
		p.add(l1);
		p.add(l2);
		p.add(l3);
		p.add(l4);
		
		for(int s=0;s<=6;s++)
		{
			try
			{
				sleep(20);
			}
			catch(Exception e){}
		}

		
		l1.setBounds(850,80,100,69);
		l2.setBounds(850,190,100,69);
		l3.setBounds(850,300,100,69);
		l4.setBounds(850,410,100,69);

		l1.addActionListener(this);
		l2.addActionListener(this);
		l3.addActionListener(this);
		l4.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==l1)
		{
			dset=new DemoSetting();
			dset.setVisible(true);
		}
		else if(e.getSource()==l2)
		{
			rb=new RecycleBin();
			rb.setVisible(true);
		}
		else if(e.getSource()==l3)
		{
			ds=new DisplaySettings();
			ds.setVisible(true);
		}
		else if(e.getSource()==l4)
		{
			di=new DemoIncrement();
			di.setVisible(true);
			di.setup();
		}
	}
}
class DisplaySettings extends JFrame implements ItemListener,ActionListener
{
	Choice chTransperant,chTop,chBottom;
	JLabel lbl1,lbl2,lbl3;
	JButton btnApply=new JButton("Apply"),btnCancel=new JButton("Cancel"),btnWall=new JButton("Set WallPaper");
	String path;
	Connection con;	
	Statement stmt;
	JTextField txtPath=new JTextField();

	DisplaySettings()
	{
		setLocation(380,184);
		setSize(500,400);

		setLayout(null);
	
		chTransperant=new Choice();
		chTransperant.add("glass film (0%)");
		chTransperant.add("sun glass (10%)");
		chTransperant.add("more transperant (30%)");
		chTransperant.add("medium transperant (60%)");
		chTransperant.add("less transperant (80%)");
		chTransperant.add("least transperant (90%)");
		chTransperant.add("no transperancy (Dark Film)");

		chTop=new Choice();
		chTop.add("Low (30%)");
		chTop.add("Medium(70%)");	
		chTop.add("Full(100%)");

		chBottom=new Choice();
		chBottom.add("Low (30%)");
		chBottom.add("Medium(70%)");
		chBottom.add("Full(100%)");

		lbl1=new JLabel(new ImageIcon("icons\\settings\\display1.jpg"));
		lbl2=new JLabel(new ImageIcon("icons\\settings\\display8.jpg"));
		lbl3=new JLabel(new ImageIcon("icons\\settings\\display11.jpg"));

		add(chTransperant);
		add(chTop);
		add(chBottom);
		add(lbl1);
		add(lbl2);
		add(lbl3);
		add(btnApply);
		add(btnCancel);
		add(btnWall);
		add(txtPath);

		chTransperant.setBounds(20,50,180,20);		
		chTop.setBounds(20,150,180,20);		
		chBottom.setBounds(20,200,180,20);		
		lbl1.setBounds(250,20,200,112);
		lbl2.setBounds(250,140,200,50);
		lbl3.setBounds(250,200,200,50);

		txtPath.setBounds(20,260,180,20);
		txtPath.setEditable(false);

		btnWall.setBounds(20,300,150,30);
		btnApply.setBounds(300,300,80,30);
		btnCancel.setBounds(400,300,80,30);

		btnApply.addActionListener(this);
		btnCancel.addActionListener(this);
		btnWall.addActionListener(this);

		chTransperant.addItemListener(this);
		chTop.addItemListener(this);
		chBottom.addItemListener(this);
	}
	public void itemStateChanged(ItemEvent e)
	{
		if(e.getSource()==chTransperant)
		{
			int i=chTransperant.getSelectedIndex();
			if(i==0)
			{
				lbl1.setIcon(new ImageIcon("icons\\settings\\display1.jpg"));
				FifthColumn.mainPanel=0;
			}
			else if(i==1)
			{
				lbl1.setIcon(new ImageIcon("icons\\settings\\display2.jpg"));
				FifthColumn.mainPanel=100;
			}
			else if(i==2)
			{
				lbl1.setIcon(new ImageIcon("icons\\settings\\display3.jpg"));
				FifthColumn.mainPanel=130;
			}
			else if(i==3)
			{
				lbl1.setIcon(new ImageIcon("icons\\settings\\display4.jpg"));
				FifthColumn.mainPanel=170;
			}
			else if(i==4)
			{
				lbl1.setIcon(new ImageIcon("icons\\settings\\display5.jpg"));
				FifthColumn.mainPanel=200;
			}
			else if(i==5)
			{
				lbl1.setIcon(new ImageIcon("icons\\settings\\display6.jpg"));
				FifthColumn.mainPanel=230;
			}
			else if(i==6)
			{
				lbl1.setIcon(new ImageIcon("icons\\settings\\display7.jpg"));
				FifthColumn.mainPanel=255;
			}
		}
		else if(e.getSource()==chTop)
		{
			int i=chTop.getSelectedIndex();
			if(i==0)
			{
				lbl2.setIcon(new ImageIcon("icons\\settings\\display8.jpg"));
				FifthColumn.top=100;
			}
			else if(i==1)
			{
				lbl2.setIcon(new ImageIcon("icons\\settings\\display12.jpg"));
				FifthColumn.top=180;
			}
			else if(i==2)
			{
				lbl2.setIcon(new ImageIcon("icons\\settings\\display14.jpg"));
				FifthColumn.top=255;
			}
		}
		if(e.getSource()==chBottom)
		{
			int i=chBottom.getSelectedIndex();
			if(i==0)
			{
				lbl3.setIcon(new ImageIcon("icons\\settings\\display11.jpg"));
				FifthColumn.bottom=100;
			}
			else if(i==1)
			{
				lbl3.setIcon(new ImageIcon("icons\\settings\\display13.jpg"));
				FifthColumn.bottom=180;
			}
			else if(i==2)
			{
				lbl3.setIcon(new ImageIcon("icons\\settings\\display15.jpg"));
				FifthColumn.bottom=255;
			}
		}
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==btnApply)
		{
			try
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				con=DriverManager.getConnection("Jdbc:Odbc:Password","","");
				stmt=con.createStatement();
				stmt.executeQuery("Update Password set WallPath='"+txtPath.getText()+"'");
				con.close();
			}
			catch(Exception esd){}

			this.setVisible(false);
			FifthColumn.displayChanged=1;
		}
		else if(e.getSource()==btnCancel)
		{
			this.setVisible(false);
		}
		else if(e.getSource()==btnWall)
		{
			FileDialog fd=new FileDialog(this);
			fd.show();

			txtPath.setText(fd.getDirectory()+fd.getFile());
		}
	}
}

class RecycleBin extends JFrame implements ActionListener
{
	static JTabbedPane jtp;
	Container c;
	static JPanel p1,RADPanel,marketingPanel,financePanel,productionPanel,hrPanel,gPanel;
	Connection con;
	ResultSet rs;
	Statement stmt;

	static JPopupMenu pm=new JPopupMenu();
	static JMenuItem restore,delete;

	RecycleBin()
	{
		super("Recycle Bin");

		setExtendedState(JFrame.MAXIMIZED_BOTH);

		c=getContentPane();

		RADPanel=new JPanel();
		RADPanel.setLayout(new GridLayout(0,4,20,20));

		hrPanel=new JPanel();
		hrPanel.setLayout(new GridLayout(0,4,20,20));
	
		marketingPanel=new JPanel();
		marketingPanel.setLayout(new GridLayout(0,4,20,20));

		gPanel=new JPanel();
		gPanel.setLayout(new GridLayout(0,4,20,20));

		financePanel=new JPanel();
		financePanel.setLayout(new GridLayout(0,4,20,20));

		productionPanel=new JPanel();
		productionPanel.setLayout(new GridLayout(0,4,20,20));

		RADPanel.setBackground(Color.WHITE);
		hrPanel.setBackground(Color.WHITE);
		marketingPanel.setBackground(Color.WHITE);
		gPanel.setBackground(Color.WHITE);
		financePanel.setBackground(Color.WHITE);	
		productionPanel.setBackground(Color.WHITE);

		jtp=new JTabbedPane(JTabbedPane.LEFT);
		
		jtp.addTab("Research And Development",RADPanel);
		jtp.addTab("Marketing",marketingPanel);
		jtp.addTab("Finance",financePanel);
		jtp.addTab("Production",productionPanel);
		jtp.addTab("Human Resource",hrPanel);
		jtp.addTab("Geographical",gPanel);

		restore=new JMenuItem("Restore");
		delete=new JMenuItem("Delete");
		
		pm.add(restore);
		pm.add(delete);

		c.add(jtp);
	
		delete.addActionListener(this);
		restore.addActionListener(this);

		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			
			String url="Jdbc:Odbc:NayanDesale";
			con=DriverManager.getConnection(url,"","");

			System.out.println("Stored Data: 1");
			stmt=con.createStatement();
			System.out.println("Stored Data: 2");
			rs=stmt.executeQuery("Select * from EmplyeeDetails");
			System.out.println("Stored Data: 3");

			while(rs.next())
			{
				int id=rs.getInt("EmployeeId");
				String nm=rs.getString("EmployeeName");
				System.out.println("Stored Data: ");
				int d=rs.getInt("DeptID");

				if(rs.getBoolean("Bin"))
				{
					if(d==11)
					RADPanel.add(new AdvanceLabel1(nm+" - "+id,new ImageIcon("icons\\deletedfolder.jpg")));
					else if(d==12)
					financePanel.add(new AdvanceLabel1(nm+" - "+id,new ImageIcon("icons\\deletedfolder.jpg")));
					else if(d==13)
					productionPanel.add(new AdvanceLabel1(nm+" - "+id,new ImageIcon("icons\\deletedfolder.jpg")));
					else if(d==14)
					gPanel.add(new AdvanceLabel1(nm+" - "+id,new ImageIcon("icons\\deletedfolder.jpg")));
					else if(d==15)
					hrPanel.add(new AdvanceLabel1(nm+" - "+id,new ImageIcon("icons\\deletedfolder.jpg")));
					else if(d==17)
					marketingPanel.add(new AdvanceLabel1(nm+" - "+id,new ImageIcon("icons\\deletedfolder.jpg")));
					System.out.println("Entered");
				}
			}
			rs.close();
			con.close();
			stmt.close();
		}
		catch(Exception ex1)
		{
			System.out.println(ex1.getMessage());
			try
			{
				rs.close();
				con.close();
				stmt.close();
			}catch(Exception sef){}
		}	
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==delete)
		{
			JLabel lbl=(JLabel)pm.getInvoker();
			String str[]=lbl.getText().split(" - ");
			int id=Integer.parseInt(str[1]);
			try
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					
				String url="Jdbc:Odbc:NayanDesale";
				con=DriverManager.getConnection(url,"","");

				System.out.println("Stored Data: 1");
				stmt=con.createStatement();
				System.out.println("Stored Data: 2");
				int r=stmt.executeUpdate("Delete from EmplyeeDetails where EmployeeId="+id);
				if(r>0)
					JOptionPane.showMessageDialog((Component)null,"Employee is deleted","Operation sucessfull",JOptionPane.INFORMATION_MESSAGE);	
				con.close();
				stmt.close();
				rs.close();
			}	
			catch(Exception ex){}
			finally
			{
				lbl.setVisible(false);
			}
			
		}
		if(e.getSource()==restore)
		{
			JLabel lbl=(JLabel)pm.getInvoker();
			String str[]=lbl.getText().split(" - ");
			int id=Integer.parseInt(str[1]);
			PreparedStatement pre;
			try
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					
				String url="Jdbc:Odbc:NayanDesale";
				con=DriverManager.getConnection(url,"","");

				System.out.println("Stored Data: 1");
				stmt=con.createStatement();
				System.out.println("Stored Data: 2");
				stmt.executeUpdate("Update EmplyeeDetails set Bin=False where EmployeeId="+id);
				System.out.println("Query is executed");		
				con.close();
				rs.close();
			}
			catch(Exception ee)
			{
				try
				{
					con.close();
					rs.close();
					
				}
				catch(Exception ew){}
			}
			finally
			{
				lbl.setVisible(false);
			}
			
		}
	}
}

class AdvanceLabel1 extends JLabel implements MouseListener
{
	ImageIcon normalIcon,rolloverIcon,pressedIcon;
	JLabel lblName;

	Container parent;
	static JPanel subPanel;

	AdvanceLabel1(String ln,ImageIcon icon)
	{
		super(ln,icon,0);
		normalIcon=icon;
	
		addMouseListener(this);

		rolloverIcon=new ImageIcon("icons\\deletedfolderShine.jpg");
	}
	void setRolloverIcon(ImageIcon icon)
	{
		rolloverIcon=icon;
	}
	void setPressedIcon(ImageIcon icon)
	{
		pressedIcon=icon;
	}
	public void mousePressed(MouseEvent e)
	{
		if(e.getSource()==this)
		{
		}
	}
	public void mouseReleased(MouseEvent e)
	{
		if(e.getSource()==this)
		{
			setIcon(rolloverIcon);
		}
	}
	public void mouseEntered(MouseEvent e)
	{
		if(e.getSource()==this)
		{
			setIcon(rolloverIcon);
		}
	}
	public void mouseExited(MouseEvent e)
	{
		if(e.getSource()==this)
		{
			setIcon(normalIcon);
		}
	}
	public void mouseClicked(MouseEvent e)
	{
		if(e.getSource()==this && e.getButton()==MouseEvent.BUTTON3)
		{
			RecycleBin.pm.show(this,e.getX(),e.getY());
		}
	}
}
class DemoSetting extends JFrame implements ActionListener
{
	JPanel p1,p2,p3,p4;
	JTabbedPane jtp;
	JLabel lblhra,lblda,lblca,lblma,lblea,lblta,lblenterAll,lblpt,lblpf,lblUname,lblPasswd,p2lblUname,p2lblPasswd,p2lblhra,p2lblda,p2lblca,p2lblma,p2lblea,p2lblta,p2lblenterAll,p2lblpt,p2lblpf,lblHead,lblUname1,lblPasswd1,lblnewUname,lblnewPasswd;
	JTextField txthra,txtda,txtca,txtma,txtea,txtta,txtenterAll,txtpt,txtpf,txtUname,p2txthra,p2txtda,p2txtca,p2txtma,p2txtea,p2txtta,p2txtenterAll,p2txtpt,p2txtpf,p2txtUname,txtUname1,txtnewUname;
	JPasswordField txtPasswd,p2txtPasswd,txtPasswd1,txtnewPasswd;
	JButton btnUpgrade,btnUpdate,btnOk,p2btnUpgrade,p2btnUpdate,p2btnOk,btnLogSec,btnSalSec,btnLeaveSec,btnIncrementSec,btnIpSec,btnApply,btnApply1,btnApply2,btnApply3,btnApply4;
	JFrame f,f1;
	Connection con;
	Statement stmt;
	PreparedStatement pstmt;
	String url,query,Uname,adminPasswd,salPasswd,leavePasswd,incPasswd,ipPasswd;
	ResultSet rs;
	JDialog d=new JDialog((JFrame)null,"Login Security",true);
	JDialog d1=new JDialog((JFrame)null,"Salary Security",true);
	JDialog d2=new JDialog((JFrame)null,"Leave Security",true);
	JDialog d3=new JDialog((JFrame)null,"Increment Security",true);
	JDialog d4=new JDialog((JFrame)null,"IP security",true);
	Container cc;
	JTextField t1,t2;

	JLabel lblDept;
	List lst1=new List(4);
	JButton btnAddDept;

	static int max=0;

	DemoSetting()
	{
		super("System Settings");
		setBounds(400,100,500,600);
		Container c;
		c=this.getContentPane();
		p1=new JPanel();
		p1.setLayout(null);
		lblhra=new JLabel("HouseRentAllowance");
		lblda=new JLabel("DearenessAllowance");
		lblca=new JLabel("CityAllowance");
		lblma=new JLabel("MedicalAllowance");
		lblea=new JLabel("EducationAllowance");	
		lblta=new JLabel("TravellingAllowance");
		lblenterAll=new JLabel("EntertainmentAllowance");
		lblpt=new JLabel("ProfessionalTax");
		lblpf=new JLabel("ProvidendFund");

		lblhra.setBounds(30,50,130,20);
		lblda.setBounds(30,90,130,20);
		lblca.setBounds(30,130,130,20);
		lblma.setBounds(30,170,130,20);
		lblea.setBounds(30,210,130,20);
		lblta.setBounds(30,250,130,20);
		lblenterAll.setBounds(30,290,140,20);
		lblpt.setBounds(30,330,120,20);
		lblpf.setBounds(30,370,120,20);
	
		p1.add(lblhra);
		p1.add(lblda);
		p1.add(lblca);
		p1.add(lblma);
		p1.add(lblea);
		p1.add(lblta);
		p1.add(lblenterAll);
		p1.add(lblpt);
		p1.add(lblpf);

		txthra=new JTextField(20);
		txtda=new JTextField(20);
		txtca=new JTextField(20);
		txtma=new JTextField(20);
		txtea=new JTextField(20);
		txtta=new JTextField(20);
		txtenterAll=new JTextField(20);
		txtpt=new JTextField(20);
		txtpf=new JTextField(20);
	
		txthra.setBounds(200,50,50,20);	
		txtda.setBounds(200,90,50,20);	
		txtca.setBounds(200,130,50,20);
		txtma.setBounds(200,170,50,20);
		txtea.setBounds(200,210,50,20);
		txtta.setBounds(200,250,50,20);
		txtenterAll.setBounds(200,290,50,20);
		txtpt.setBounds(200,330,50,20);
		txtpf.setBounds(200,370,50,20);
	
		p1.add(txthra);
		p1.add(txtda);
		p1.add(txtca);
		p1.add(txtma);		
		p1.add(txtea);		
		p1.add(txtta);		
		p1.add(txtenterAll);		
		p1.add(txtpt);		
		p1.add(txtpf);		


		btnUpgrade=new JButton("Upgrade");
		btnUpdate=new JButton("Update");

		btnUpgrade.setBounds(50,450,100,30);		
		btnUpdate.setBounds(180,450,100,30);
		btnUpdate.setEnabled(false);

		p1.add(btnUpgrade);
		p1.add(btnUpdate);


		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			url="Jdbc:Odbc:Incentives";
			con=DriverManager.getConnection(url,"","");
			stmt=con.createStatement();
			query="Select * from Incentives where Designation='Employee'";
			rs=stmt.executeQuery(query);

			rs.next();
			txthra.setText(rs.getFloat("HouseRentAllowance")+"");
			txtda.setText(rs.getFloat("DearenessAllowance")+"");
			txtca.setText(rs.getFloat("CityAllowance")+"");
			txtma.setText(rs.getFloat("MedicalAllowance")+"");
			txtea.setText(rs.getFloat("EducationAllowance")+"");
			txtta.setText(rs.getFloat("TravellingAllowance")+"");
			txtenterAll.setText(rs.getFloat("EntertainmentAllowance")+"");
			txtpt.setText(rs.getFloat("ProfessionalTax")+"");
			txtpf.setText(rs.getFloat("ProvidendFund")+"");
			con.close();
			stmt.close();

			txthra.setEditable(false);
			txtda.setEditable(false);
			txtma.setEditable(false);
			txtca.setEditable(false);
			txtea.setEditable(false);
			txtta.setEditable(false);
			txtenterAll.setEditable(false);
			txtpt.setEditable(false);
			txtpf.setEditable(false);

			url="Jdbc:Odbc:Password";
			con=DriverManager.getConnection(url,"","");
			stmt=con.createStatement();
			System.out.println("Connection is created");
			query="Select * from Password";
			rs=stmt.executeQuery(query);
			System.out.println("Password Query is Executed");
			if(rs.next())
			{
				Uname=rs.getString("AdminUName");
				adminPasswd=rs.getString("AdminPassword");
				salPasswd=rs.getString("SalaryPassword");
				leavePasswd=rs.getString("LeavePassword");
				incPasswd=rs.getString("IncrementPassword");
				ipPasswd=rs.getString("IpSetting");
			}
			System.out.println("User name="+Uname);
			System.out.println("Admin password="+adminPasswd);
			System.out.println("Salary password="+salPasswd);
			System.out.println("Leave Password="+leavePasswd);
			System.out.println("Increment Password="+incPasswd);
			System.out.println("IP Password="+ipPasswd);
			con.close();
			stmt.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			try
			{
				con.close();	
				stmt.close();
			}		
			catch(Exception ee){}
		}

		
		btnUpgrade.addActionListener(this);
		btnUpdate.addActionListener(this);
	//	-----------------------Panel2 start-------------------------------
		
		p2=new JPanel();
		p2.setLayout(null);

		p2lblhra=new JLabel("HouseRentAllowance");
		p2lblda=new JLabel("DearenessAllowance");
		p2lblca=new JLabel("CityAllowance");
		p2lblma=new JLabel("MedicalAllowance");
		p2lblea=new JLabel("EducationAllowance");	
		p2lblta=new JLabel("TravellingAllowance");
		p2lblenterAll=new JLabel("EntertainmentAllowance");
		p2lblpt=new JLabel("ProfessionalTax");
		p2lblpf=new JLabel("ProvidendFund");


		p2lblhra.setBounds(30,50,130,20);
		p2lblda.setBounds(30,90,130,20);
		p2lblca.setBounds(30,130,130,20);
		p2lblma.setBounds(30,170,130,20);
		p2lblea.setBounds(30,210,130,20);
		p2lblta.setBounds(30,250,130,20);
		p2lblenterAll.setBounds(30,290,140,20);
		p2lblpt.setBounds(30,330,120,20);
		p2lblpf.setBounds(30,370,120,20);


		p2.add(p2lblhra);
		p2.add(p2lblda);
		p2.add(p2lblca);
		p2.add(p2lblma);
		p2.add(p2lblea);
		p2.add(p2lblta);
		p2.add(p2lblenterAll);
		p2.add(p2lblpt);
		p2.add(p2lblpf);

		p2txthra=new JTextField(20);
		p2txtda=new JTextField(20);
		p2txtca=new JTextField(20);
		p2txtma=new JTextField(20);
		p2txtea=new JTextField(20);
		p2txtta=new JTextField(20);
		p2txtenterAll=new JTextField(20);
		p2txtpt=new JTextField(20);
		p2txtpf=new JTextField(20);		

		
		p2txthra.setBounds(200,50,50,20);	
		p2txtda.setBounds(200,90,50,20);	
		p2txtca.setBounds(200,130,50,20);
		p2txtma.setBounds(200,170,50,20);
		p2txtea.setBounds(200,210,50,20);
		p2txtta.setBounds(200,250,50,20);
		p2txtenterAll.setBounds(200,290,50,20);
		p2txtpt.setBounds(200,330,50,20);
		p2txtpf.setBounds(200,370,50,20);		

		
		p2.add(p2txthra);
		p2.add(p2txtda);
		p2.add(p2txtca);
		p2.add(p2txtma);		
		p2.add(p2txtea);		
		p2.add(p2txtta);		
		p2.add(p2txtenterAll);		
		p2.add(p2txtpt);		
		p2.add(p2txtpf);	


		p2btnUpgrade=new JButton("Upgrade");
		p2btnUpdate=new JButton("Update");

		p2btnUpgrade.setBounds(50,450,100,30);		
		p2btnUpdate.setBounds(180,450,100,30);
		p2btnUpdate.setEnabled(false );

		p2.add(p2btnUpgrade);
		p2.add(p2btnUpdate);

		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			url="Jdbc:Odbc:Incentives";
			con=DriverManager.getConnection(url,"","");
			stmt=con.createStatement();
			query="Select * from Incentives where Designation='Manager'";
			rs=stmt.executeQuery(query);

			rs.next();
			p2txthra.setText(rs.getFloat("HouseRentAllowance")+"");
			p2txtda.setText(rs.getFloat("DearenessAllowance")+"");
			p2txtca.setText(rs.getFloat("CityAllowance")+"");
			p2txtma.setText(rs.getFloat("MedicalAllowance")+"");
			p2txtea.setText(rs.getFloat("EducationAllowance")+"");
			p2txtta.setText(rs.getFloat("TravellingAllowance")+"");
			p2txtenterAll.setText(rs.getFloat("EntertainmentAllowance")+"");
			p2txtpt.setText(rs.getFloat("ProfessionalTax")+"");
			p2txtpf.setText(rs.getFloat("ProvidendFund")+"");
			con.close();
			stmt.close();

			p2txthra.setEditable(false);
			p2txtda.setEditable(false);
			p2txtma.setEditable(false);
			p2txtca.setEditable(false);
			p2txtea.setEditable(false);
			p2txtta.setEditable(false);
			p2txtenterAll.setEditable(false);
			p2txtpt.setEditable(false);
			p2txtpf.setEditable(false);
			
		}
		catch(Exception e)
		{
			try
			{
				con.close();	
				stmt.close();
			}		
			catch(Exception ee){}
		}

		p2btnUpgrade.addActionListener(this);
		p2btnUpdate.addActionListener(this);

		

	//	------------------Panel3 Start-------------------
		
		p3=new JPanel();
		p3.setLayout(null);
	
		btnLogSec=new JButton("Login Security");
		btnSalSec=new JButton("Salary Security");
		btnLeaveSec=new JButton("Leave Security"); 
		btnIncrementSec=new JButton("Increment Security");
		btnIpSec=new JButton("IP Security");

		btnLogSec.setBounds(50,80,150,40);
		btnSalSec.setBounds(50,160,150,40);
		btnLeaveSec.setBounds(50,240,150,40);
		btnIncrementSec.setBounds(50,320,150,40);
		btnIpSec.setBounds(50,400,150,40);
			
		p3.add(btnLogSec);
		p3.add(btnSalSec);
		p3.add(btnLeaveSec);
		p3.add(btnIncrementSec);
		p3.add(btnIpSec);

		btnLogSec.addActionListener(this);
		btnSalSec.addActionListener(this);
		btnLeaveSec.addActionListener(this);
		btnIncrementSec.addActionListener(this);
		btnIpSec.addActionListener(this);

		jtp=new JTabbedPane();
		jtp.setTabPlacement(JTabbedPane.LEFT);
		jtp.add("Employee Setting",p1);
		jtp.add("Manager Setting",p2);	
		jtp.add("Admin password Setting",p3);
		jtp.setFont(new Font("Times New Roman",Font.BOLD,16));
		c.add(jtp);	

	/*---------------------------Panel 4 Start -----------------------------------*/

		p4=new JPanel();
		p4.setLayout(null);

		lblDept=new JLabel("Departments");
		btnAddDept=new JButton("Add Department");
		
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("Jdbc:Odbc:Dept","","");
			Statement stmt=con.createStatement();

			ResultSet rs=stmt.executeQuery("Select MAX(DeptId) from Department");
			rs.next();
			System.out.println("Executed 1");
			max=rs.getInt(1);
			System.out.println("Executed 2");
			System.out.println("Maximum: "+max);

			rs=stmt.executeQuery("Select * from Department");

			while(rs.next())
			{
				lst1.add(rs.getString("DeptName")+"    ("+rs.getInt("DeptId")+")");
			}
			con.close();
		}
		catch(Exception de)
		{
			System.out.println("Boooooooorinng: "+de.getMessage());
		}

		p4.add(lblDept);
		p4.add(lst1);
		p4.add(btnAddDept);

		lblDept.setBounds(20,40,200,50);
		lblDept.setFont(new Font("Times New Roman",Font.BOLD,16));
		lst1.setBounds(30,100,200,100);

		btnAddDept.setBounds(30,300,200,30);

		jtp.addTab("Edit Departments",p4);

		btnAddDept.addActionListener(this);
	}
		
	public void actionPerformed(ActionEvent e)
	{	
		if(e.getSource()==btnUpgrade)
		{
			
			f=new JFrame("Login");
			f.setVisible(true);
			f.setBounds(500,300,300,200);
			f.setLayout(null);
			lblUname=new JLabel("User Name");
			lblPasswd=new JLabel("Password");
			
			lblUname.setBounds(30,30,100,20);	
			lblPasswd.setBounds(30,80,100,20);
	
			f.add(lblUname);
			f.add(lblPasswd);
	
			txtUname=new JTextField(20);
			txtPasswd=new JPasswordField(20);

			txtUname.setBounds(130,30,130,20);
			txtPasswd.setBounds(130,80,130,20);
	
			f.add(txtUname);
			f.add(txtPasswd);
	
			btnOk=new JButton("OK");
			btnOk.setBounds(100,130,80,30);
			f.add(btnOk);
			btnOk.addActionListener(this);		
		}
		if(e.getSource()==btnAddDept)
		{
			String deptname=JOptionPane.showInputDialog("Enter new Department Name: ");
			JOptionPane.showMessageDialog(null,"The Department Id of "+deptname+" is "+(max+1));

			try
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				Connection con=DriverManager.getConnection("Jdbc:Odbc:Dept","","");
				Statement stmt=con.createStatement();
				
				int res=stmt.executeUpdate("Insert into Department values("+(max+1)+",'"+deptname+"')");
				if(res>0)
				JOptionPane.showMessageDialog(null,"New Department is Inserted Successfully");
				else
				JOptionPane.showMessageDialog(null,"Error.... Please Verify that the Department Id should not same as previous departments");

				con.close();
			}
			catch(Exception ex)
			{}
		}
		if(e.getSource()==btnOk)
		{		
			String str1,str2;
			str1=txtUname.getText();
			str2=txtPasswd.getText();	
			if(str1.equals(Uname)&&str2.equals(salPasswd))
			{
				f.setVisible(false);
				txthra.setEditable(true);
				txtda.setEditable(true);
				txtma.setEditable(true);
				txtca.setEditable(true);
				txtea.setEditable(true);
				txtta.setEditable(true);
				txtenterAll.setEditable(true);
				txtpt.setEditable(true);
				txtpf.setEditable(true);
				btnUpdate.setEnabled(true);
			}
			else
			{
				f.setVisible(false);
				JOptionPane.showMessageDialog((Component)null,"Wrong User Name and Password","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		if(e.getSource()==btnUpdate)
		{
			try
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				url="Jdbc:Odbc:Incentives";
				con=DriverManager.getConnection(url,"","");
				query="Update Incentives set HouseRentAllowance=?,DearenessAllowance=?,CityAllowance=?,MedicalAllowance=?,EducationAllowance=?,TravellingAllowance=?,EntertainmentAllowance=?,ProfessionalTax=?,ProvidendFund=? where Designation='Employee'";
				pstmt=con.prepareStatement(query);
				pstmt.setFloat(1,Float.valueOf(txthra.getText()));
				pstmt.setFloat(2,Float.valueOf(txtda.getText()));
				pstmt.setFloat(3,Float.valueOf(txtca.getText()));
				pstmt.setFloat(4,Float.valueOf(txtma.getText()));
				pstmt.setFloat(5,Float.valueOf(txtea.getText()));
				pstmt.setFloat(6,Float.valueOf(txtta.getText()));
				pstmt.setFloat(7,Float.valueOf(txtenterAll.getText()));
				pstmt.setFloat(8,Float.valueOf(txtpt.getText()));
				pstmt.setFloat(9,Float.valueOf(txtpf.getText()));
				int r=pstmt.executeUpdate();
				if(r>0)	
					JOptionPane.showMessageDialog((Component)null," Parameter sucessfully Updated","Updaed!!",JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog((Component)null,"Parameter Not Updated","Error!!",JOptionPane.ERROR_MESSAGE);
				pstmt.clearParameters();
				con.close();
				pstmt.close();
				
			}
			catch(Exception ex)
			{
				try
				{
					con.close();
					pstmt.close();
				}
				catch(Exception eee){}
			}
		}
		if(e.getSource()==p2btnUpgrade)	
		{
			System.out.println("UpGrade Button pressed");

			f1=new JFrame("Login");
			f1.setVisible(true);
			f1.setBounds(500,300,300,200);
			f1.setLayout(null);
			p2lblUname=new JLabel("User Name");
			p2lblPasswd=new JLabel("Password");
			
			p2lblUname.setBounds(30,30,100,20);	
			p2lblPasswd.setBounds(30,80,100,20);
	
			f1.add(p2lblUname);
			f1.add(p2lblPasswd);
	
			p2txtUname=new JTextField(20);
			p2txtPasswd=new JPasswordField(20);
	

			p2txtUname.setBounds(130,30,130,20);
			p2txtPasswd.setBounds(130,80,130,20);
	
			f1.add(p2txtUname);
			f1.add(p2txtPasswd);
	
			p2btnOk=new JButton("OK");
			p2btnOk.setBounds(100,130,80,30);
			f1.add(p2btnOk);
			p2btnOk.addActionListener(this);
		}
		if(e.getSource()==p2btnOk)
		{
			System.out.println("OK Button is pressed");
			
			String str1,str2;
			str1=p2txtUname.getText();
			str2=p2txtPasswd.getText();	
			if(str1.equals(Uname)&&str2.equals(salPasswd))
			{
				System.out.println("Password is correct");
				f1.setVisible(false);
				p2txthra.setEditable(true);
				p2txtda.setEditable(true);
				p2txtma.setEditable(true);
				p2txtca.setEditable(true);
				p2txtea.setEditable(true);
				p2txtta.setEditable(true);
				p2txtenterAll.setEditable(true);
				p2txtpt.setEditable(true);
				p2txtpf.setEditable(true);
				p2btnUpdate.setEnabled(true);
			}
			else
			{
				f1.setVisible(false);
				JOptionPane.showMessageDialog((Component)null,"Wrong User Name and Password","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		if(e.getSource()==p2btnUpdate)
		{
			System.out.println("Update Button pressed");
			
			try
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				url="Jdbc:Odbc:Incentives";
				con=DriverManager.getConnection(url,"","");
				query="Update Incentives set HouseRentAllowance=?,DearenessAllowance=?,CityAllowance=?,MedicalAllowance=?,EducationAllowance=?,TravellingAllowance=?,EntertainmentAllowance=?,ProfessionalTax=?,ProvidendFund=? where Designation='Manager'";
				pstmt=con.prepareStatement(query);
				pstmt.setFloat(1,Float.valueOf(p2txthra.getText()));
				pstmt.setFloat(2,Float.valueOf(p2txtda.getText()));
				pstmt.setFloat(3,Float.valueOf(p2txtca.getText()));
				pstmt.setFloat(4,Float.valueOf(p2txtma.getText()));
				pstmt.setFloat(5,Float.valueOf(p2txtea.getText()));
				pstmt.setFloat(6,Float.valueOf(p2txtta.getText()));
				pstmt.setFloat(7,Float.valueOf(p2txtenterAll.getText()));
				pstmt.setFloat(8,Float.valueOf(p2txtpt.getText()));
				pstmt.setFloat(9,Float.valueOf(p2txtpf.getText()));
				int r=pstmt.executeUpdate();
				if(r>0)	
					JOptionPane.showMessageDialog((Component)null,"Parameters sucessfully Updated","Updated!!s",JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog((Component)null,"Parameters Not Updated","Error!!",JOptionPane.ERROR_MESSAGE);
				pstmt.clearParameters();
				con.close();
				pstmt.close();
				
			}
			catch(Exception ex)
			{
				try
				{
					con.close();
					pstmt.close();
				}
				catch(Exception eee){}
			}
		}
		if(e.getSource()==btnLogSec)
		{
			System.out.println("Log Button is Preesed");
			cc=d.getContentPane();
			cc.setLayout(null);
			lblHead=new JLabel("Login Security");
			lblHead.setFont(new Font("Times New Roman",Font.BOLD,24));
			lblUname1=new JLabel("Enter Old User Name");
			lblPasswd1=new JLabel("Enter Old Password");
			lblnewUname=new JLabel("Enter New UserName ");
			lblnewPasswd=new JLabel("Enter New Password");

			lblHead.setBounds(100,30,170,40);
			lblUname1.setBounds(50,90,120,20);
			lblPasswd1.setBounds(50,130,120,20);
			lblnewUname.setBounds(50,170,140,20);
			lblnewPasswd.setBounds(50,210,140,20);

			cc.add(lblHead);
			cc.add(lblUname1);
			cc.add(lblPasswd1);
			cc.add(lblnewUname);		
			cc.add(lblnewPasswd);

			txtUname1=new JTextField(20);
			txtPasswd1=new JPasswordField(20);
			txtnewUname=new JTextField(20);
			txtnewPasswd=new JPasswordField(20);

			txtUname1.setBounds(190,90,120,20);
			txtPasswd1.setBounds(190,130,120,20);
			txtnewUname.setBounds(190,170,120,20);
			txtnewPasswd.setBounds(190,210,120,20);
		
		
			cc.add(txtUname1);
			cc.add(txtPasswd1);
			cc.add(txtnewUname);
			cc.add(txtnewPasswd);

			btnApply=new JButton("Apply");
			btnApply.setBounds(120,270,100,30);
			cc.add(btnApply);
			btnApply.addActionListener(this);
	
			d.setBounds(500,200,350,350);
			d.setVisible(true);
			
		}
		if(e.getSource()==btnApply)
		{
			d.hide();
			String str1,str2,s1,s2;
			str1=txtUname1.getText();	
			str2=txtPasswd1.getText();
			s1=txtnewUname.getText();
			s2=txtnewPasswd.getText();
			System.out.println("Apply Button is Pressed");
			if(str1.equals(Uname)&&str2.equals(adminPasswd))
			{
				try
				{
					url="Jdbc:Odbc:Password";
					con=DriverManager.getConnection(url,"","");
					query="Update Password set AdminUName=?,AdminPassword=?";
					pstmt=con.prepareStatement(query);
					pstmt.setString(1,s1);
					pstmt.setString(2,s2);
					int result=pstmt.executeUpdate();
					if(result>0)
						JOptionPane.showMessageDialog((Component)null,"Updated","Hello",JOptionPane.INFORMATION_MESSAGE);		
					else
						JOptionPane.showMessageDialog((Component)null,"Not Updated","Hello",JOptionPane.ERROR_MESSAGE);
					con.close();
					pstmt.close();
				}
				catch(Exception ex)
				{
					System.out.println(ex.getMessage());
					try
					{
						con.close();
						pstmt.close();
					}
					catch(Exception eee){}
				}
				
			}
			else
				JOptionPane.showMessageDialog((Component)null,"Enter Correct UserName and Password","ERROR",JOptionPane.ERROR_MESSAGE);

		}
		if(e.getSource()==btnSalSec)
		{
			System.out.println("SalSec Button is Preesed");
			cc=d1.getContentPane();
			cc.setLayout(null);
			lblHead=new JLabel("Salary Security");
			lblHead.setFont(new Font("Times New Roman",Font.BOLD,24));
			lblUname1=new JLabel("Enter Old User Name");
			lblPasswd1=new JLabel("Enter Old Password");
			lblnewUname=new JLabel("Enter New UserName ");
			lblnewPasswd=new JLabel("Enter New Password");

			lblHead.setBounds(100,30,170,40);
			lblUname1.setBounds(50,90,120,20);
			lblPasswd1.setBounds(50,130,120,20);
			lblnewUname.setBounds(50,170,140,20);
			lblnewPasswd.setBounds(50,210,140,20);

			cc.add(lblHead);
			cc.add(lblUname1);
			cc.add(lblPasswd1);
			cc.add(lblnewUname);		
			cc.add(lblnewPasswd);

			txtUname1=new JTextField(20);
			txtPasswd1=new JPasswordField(20);
			txtnewUname=new JTextField(20);
			txtnewPasswd=new JPasswordField(20);

			txtUname1.setBounds(190,90,120,20);
			txtPasswd1.setBounds(190,130,120,20);
			txtnewUname.setBounds(190,170,120,20);
			txtnewPasswd.setBounds(190,210,120,20);
		
		
			cc.add(txtUname1);
			cc.add(txtPasswd1);
			cc.add(txtnewUname);
			cc.add(txtnewPasswd);

			btnApply1=new JButton("Apply");
			btnApply1.setBounds(120,270,100,30);
			cc.add(btnApply1);
			btnApply1.addActionListener(this);
	
			d1.setBounds(500,200,350,350);
			d1.setVisible(true);

		}	
		if(e.getSource()==btnApply1)
		{
			d1.hide();
			String str1,str2,s1,s2;
			str1=txtUname1.getText();	
			str2=txtPasswd1.getText();
			s1=txtnewUname.getText();
			s2=txtnewPasswd.getText();
			System.out.println("Apply1 Button is Pressed");
			if(str1.equals(Uname)&&str2.equals(salPasswd))
			{
				try
				{
					url="Jdbc:Odbc:Password";
					con=DriverManager.getConnection(url,"","");
					query="Update Password set AdminUName=?,SalaryPassword=?";
					pstmt=con.prepareStatement(query);
					pstmt.setString(1,s1);
					pstmt.setString(2,s2);
					int result=pstmt.executeUpdate();
					if(result>0)
						JOptionPane.showMessageDialog((Component)null,"Updated","Hello",JOptionPane.INFORMATION_MESSAGE);		
					else
						JOptionPane.showMessageDialog((Component)null,"Not Updated","Hello",JOptionPane.ERROR_MESSAGE);
					con.close();
					pstmt.close();
				}
				catch(Exception ex)
				{
					System.out.println(ex.getMessage());
					try
					{
						con.close();
						pstmt.close();
					}
					catch(Exception eee){}
				}
				
			}
			else
				JOptionPane.showMessageDialog((Component)null,"Enter Correct UserName and Password","ERROR",JOptionPane.ERROR_MESSAGE);
		}
		if(e.getSource()==btnLeaveSec)
		{
			System.out.println("LeaveSec Button is Preesed");
			cc=d2.getContentPane();
			cc.setLayout(null);
			lblHead=new JLabel("Leave Security");
			lblHead.setFont(new Font("Times New Roman",Font.BOLD,24));
			lblUname1=new JLabel("Enter Old User Name");
			lblPasswd1=new JLabel("Enter Old Password");
			lblnewUname=new JLabel("Enter New UserName ");
			lblnewPasswd=new JLabel("Enter New Password");

			lblHead.setBounds(100,30,170,40);
			lblUname1.setBounds(50,90,120,20);
			lblPasswd1.setBounds(50,130,120,20);
			lblnewUname.setBounds(50,170,140,20);
			lblnewPasswd.setBounds(50,210,140,20);

			cc.add(lblHead);
			cc.add(lblUname1);
			cc.add(lblPasswd1);
			cc.add(lblnewUname);		
			cc.add(lblnewPasswd);

			txtUname1=new JTextField(20);
			txtPasswd1=new JPasswordField(20);
			txtnewUname=new JTextField(20);
			txtnewPasswd=new JPasswordField(20);

			txtUname1.setBounds(190,90,120,20);
			txtPasswd1.setBounds(190,130,120,20);
			txtnewUname.setBounds(190,170,120,20);
			txtnewPasswd.setBounds(190,210,120,20);
		
		
			cc.add(txtUname1);
			cc.add(txtPasswd1);
			cc.add(txtnewUname);
			cc.add(txtnewPasswd);

			btnApply2=new JButton("Apply");
			btnApply2.setBounds(120,270,100,30);
			cc.add(btnApply2);
			btnApply2.addActionListener(this);
	
			d2.setBounds(500,200,350,350);
			d2.setVisible(true);

		}
		if(e.getSource()==btnApply2)
		{
			d2.hide();
			String str1,str2,s1,s2;
			str1=txtUname1.getText();	
			str2=txtPasswd1.getText();
			s1=txtnewUname.getText();
			s2=txtnewPasswd.getText();
			System.out.println("Apply2 Button is Pressed");
			if(str1.equals(Uname)&&str2.equals(leavePasswd))
			{
				try
				{
					url="Jdbc:Odbc:Password";
					con=DriverManager.getConnection(url,"","");
					query="Update Password set AdminUName=?,LeavePassword=?";
					pstmt=con.prepareStatement(query);
					pstmt.setString(1,s1);
					pstmt.setString(2,s2);
					int result=pstmt.executeUpdate();
					if(result>0)
						JOptionPane.showMessageDialog((Component)null,"Updated","Hello",JOptionPane.INFORMATION_MESSAGE);		
					else
						JOptionPane.showMessageDialog((Component)null,"Not Updated","Hello",JOptionPane.ERROR_MESSAGE);
					con.close();
					pstmt.close();
				}
				catch(Exception ex)
				{
					System.out.println(ex.getMessage());
					try
					{
						con.close();
						pstmt.close();
					}
					catch(Exception eee){}
				}
				
			}
			else
				JOptionPane.showMessageDialog((Component)null,"Enter Correct UserName and Password","ERROR",JOptionPane.ERROR_MESSAGE);
		}
		if(e.getSource()==btnIncrementSec)
		{
			System.out.println("IncrementSec Button is Preesed");
			cc=d3.getContentPane();
			cc.setLayout(null);
			lblHead=new JLabel(" Increment Security");
			lblHead.setFont(new Font("Times New Roman",Font.BOLD,24));
			lblUname1=new JLabel("Enter Old User Name");
			lblPasswd1=new JLabel("Enter Old Password");
			lblnewUname=new JLabel("Enter New UserName ");
			lblnewPasswd=new JLabel("Enter New Password");

			lblHead.setBounds(90,30,220,40);
			lblUname1.setBounds(50,90,120,20);
			lblPasswd1.setBounds(50,130,120,20);
			lblnewUname.setBounds(50,170,140,20);
			lblnewPasswd.setBounds(50,210,140,20);

			cc.add(lblHead);
			cc.add(lblUname1);
			cc.add(lblPasswd1);
			cc.add(lblnewUname);		
			cc.add(lblnewPasswd);

			txtUname1=new JTextField(20);
			txtPasswd1=new JPasswordField(20);
			txtnewUname=new JTextField(20);
			txtnewPasswd=new JPasswordField(20);

			txtUname1.setBounds(190,90,120,20);
			txtPasswd1.setBounds(190,130,120,20);
			txtnewUname.setBounds(190,170,120,20);
			txtnewPasswd.setBounds(190,210,120,20);
		
		
			cc.add(txtUname1);
			cc.add(txtPasswd1);
			cc.add(txtnewUname);
			cc.add(txtnewPasswd);

			btnApply3=new JButton("Apply");
			btnApply3.setBounds(120,270,100,30);
			cc.add(btnApply3);
			btnApply3.addActionListener(this);
	
			d3.setBounds(500,200,350,350);
			d3.setVisible(true);
		}
		if(e.getSource()==btnApply3)
		{
			d3.hide();
			String str1,str2,s1,s2;
			str1=txtUname1.getText();	
			str2=txtPasswd1.getText();
			s1=txtnewUname.getText();
			s2=txtnewPasswd.getText();
			System.out.println("Apply2 Button is Pressed");
			if(str1.equals(Uname)&&str2.equals(incPasswd))
			{
				try
				{
					url="Jdbc:Odbc:Password";
					con=DriverManager.getConnection(url,"","");
					query="Update Password set AdminUName=?,IncrementPassword=?";
					pstmt=con.prepareStatement(query);
					pstmt.setString(1,s1);
					pstmt.setString(2,s2);
					int result=pstmt.executeUpdate();
					if(result>0)
						JOptionPane.showMessageDialog((Component)null,"Updated","Hello",JOptionPane.INFORMATION_MESSAGE);		
					else
						JOptionPane.showMessageDialog((Component)null,"Not Updated","Hello",JOptionPane.ERROR_MESSAGE);
					con.close();
					pstmt.close();
				}
				catch(Exception ex)
				{
					System.out.println(ex.getMessage());
					try
					{
						con.close();
						pstmt.close();
					}
					catch(Exception eee){}
				}
				
			}
			else
				JOptionPane.showMessageDialog((Component)null,"Enter Correct UserName and Password","ERROR",JOptionPane.ERROR_MESSAGE);
		}
		if(e.getSource()==btnIpSec)
		{
			System.out.println("IPSec Button is Preesed");
			cc=d4.getContentPane();
			cc.setLayout(null);
			lblHead=new JLabel(" IP Security");
			lblHead.setFont(new Font("Times New Roman",Font.BOLD,24));		
			lblPasswd1=new JLabel("Enter Old IP");
	
			lblnewPasswd=new JLabel("Enter New IP");

			lblHead.setBounds(90,30,220,40);
			lblPasswd1.setBounds(50,90,120,20);
			lblnewPasswd.setBounds(50,130,140,20);

			cc.add(lblHead);
			cc.add(lblPasswd1);
			cc.add(lblnewPasswd);

			t1=new JTextField(20);
			t2=new JTextField(20);

			
			t1.setBounds(190,90,120,20);
			t2.setBounds(190,130,120,20);
		
			
			cc.add(t1);
			cc.add(t2);

			btnApply4=new JButton("Apply");
			btnApply4.setBounds(120,180,100,30);
			cc.add(btnApply4);
			btnApply4.addActionListener(this);
	
			d4.setBounds(500,200,350,300);
			d4.setVisible(true);
		}
		if(e.getSource()==btnApply4)
		{
			d4.hide();
			String str1,str2,s1,s2;
			
			str2=t1.getText();
			s2=t2.getText();
			System.out.println("Apply4 Button is Pressed");
			if(str2.equals(ipPasswd))
			{
				try
				{
					url="Jdbc:Odbc:Password";
					con=DriverManager.getConnection(url,"","");
					query="Update Password set IpSetting=?";
					pstmt=con.prepareStatement(query);
					pstmt.setString(1,s2);
					int result=pstmt.executeUpdate();
					if(result>0)
						JOptionPane.showMessageDialog((Component)null,"Updated","Hello",JOptionPane.INFORMATION_MESSAGE);		
					else
						JOptionPane.showMessageDialog((Component)null,"Not Updated","Hello",JOptionPane.ERROR_MESSAGE);
					con.close();
					pstmt.close();
				}
				catch(Exception ex)
				{
					System.out.println(ex.getMessage());
					try
					{
						con.close();
						pstmt.close();
					}
					catch(Exception eee){}
				}
				
			}
			else
				JOptionPane.showMessageDialog((Component)null,"Enter Correct UserName and Password","ERROR",JOptionPane.ERROR_MESSAGE);
		}
	}
}
class DemoIncrement extends JFrame implements ActionListener
{
	JLabel lblId,lblName,lblDeptId,lblCurrentIncrement,lblNewIncrement,lblHeading,lblHead,lblUname,lblPasswd;
	JTextField txtId,txtName,txtDeptId,txtCurrentIncrement,txtNewIncrement,txtUname,txtPasswd;
	//JPasswordField txtPasswd;
	JButton btnApply,btnOk,btnClear;
	JFrame f;

	Connection con;
	Statement stmt;
	PreparedStatement pstmt;
	String purl,eurl,surl,iurl,query,Uname,incPasswd,ires;
	ResultSet rs;

	float hraBasic,daBasic,caBasic,ma,ta,ea,entertainmentBasic,pt;
	float hraPer,daPer,caPer,maPer,taPer,eaPer,entertainmentPer,ptPer;
	float fAnnual,Annual;


	int empId=Integer.parseInt(JOptionPane.showInputDialog("Enter the Employee Id"));
	
	DemoIncrement()
	{
		super("Increment");
		setBounds(300,100,500,400);
		setResizable(false);

		Container c=this.getContentPane();
		setLayout(null);
		
		lblId=new JLabel("Id");
		lblName=new JLabel("Name");
		lblDeptId=new JLabel("Department Id");
		lblCurrentIncrement=new JLabel("Current Increment");
		lblNewIncrement=new JLabel("New Increment");		
		
		lblHeading=new JLabel("Salary Increment");
		c.add(lblHeading);
		lblHeading.setBounds(120,10,200,30);

		txtId=new JTextField(20);
		txtName=new JTextField(20);
		txtDeptId=new JTextField(20);
		txtCurrentIncrement=new JTextField(20);
		txtNewIncrement=new JTextField(20);

		btnApply=new JButton("Apply");


		c.add(lblId);
		c.add(lblName);
		c.add(lblDeptId);
		c.add(lblCurrentIncrement);
		c.add(lblNewIncrement);

		c.add(txtId);
		c.add(txtName);
		c.add(txtDeptId);
		c.add(txtCurrentIncrement);
		c.add(txtNewIncrement);

		c.add(btnApply);
		
		lblId.setBounds(40,70,135,20);
		lblName.setBounds(40,120,135,20);
		lblDeptId.setBounds(40,160,135,20);
		lblCurrentIncrement.setBounds(40,200,135,20);
		lblNewIncrement.setBounds(40,240,135,20);

		txtId.setBounds(175,80,130,20);
		txtName.setBounds(175,120,130,20);
		txtDeptId.setBounds(175,160,130,20);
		txtCurrentIncrement.setBounds(175,200,130,20);
		txtNewIncrement.setBounds(175,240,130,20);

		btnApply.setBounds(300,300,100,40);

		lblHeading.setFont(new Font("Times New Roman",Font.BOLD|Font.ITALIC,20));

		lblId.setFont(new Font("Times New Roman",Font.BOLD,15));
		lblName.setFont(new Font("Times New Roman",Font.BOLD,15));
		lblDeptId.setFont(new Font("Times New Roman",Font.BOLD,15));
		lblCurrentIncrement.setFont(new Font("Times New Roman",Font.BOLD,15));
		lblNewIncrement.setFont(new Font("Times New Roman",Font.BOLD,15));

		btnApply.setFont(new Font("Times New Roman",Font.BOLD,19));

		txtId.setEditable(false);
		txtName.setEditable(false);
		txtDeptId.setEditable(false);
		txtCurrentIncrement.setEditable(false);

		btnApply.addActionListener(this);
	}
	void setup()
	{
		try
		{	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			eurl="Jdbc:Odbc:NayanDesale";
			con=DriverManager.getConnection(eurl,"","");
			stmt=con.createStatement();
			query="Select EmployeeId,EmployeeName,DeptID,Designation from EmplyeeDetails where EmployeeId="+empId;
			rs=stmt.executeQuery(query);

			while(rs.next())
			{
			txtId.setText(rs.getInt("EmployeeId")+"");
			txtName.setText(rs.getString("EmployeeName")+"");
			txtDeptId.setText(rs.getInt("DeptID")+"");
			ires=rs.getString("Designation");
			}
			
		
			con.close();
			stmt.close();

			surl="Jdbc:Odbc:SalaryDetails";
			con=DriverManager.getConnection(surl,"","");
			stmt=con.createStatement();
			query="Select * from SalaryDetails where EmployeeId="+empId;
			rs=stmt.executeQuery(query);
		
			while(rs.next())
			{
			txtCurrentIncrement.setText(rs.getFloat("AnnualIncrement")+"");
			}
			con.close();
			stmt.close();

			purl="Jdbc:Odbc:Password";
			con=DriverManager.getConnection(purl,"","");
			stmt=con.createStatement();
			System.out.println("Connection is created");
			query="Select AdminUName,IncrementPassword from Password";
			rs=stmt.executeQuery(query);
			System.out.println("Password Query is Executed");
			if(rs.next())
			{
				Uname=rs.getString("AdminUName");
				incPasswd=rs.getString("IncrementPassword");
			}
			System.out.println("User name="+Uname);
			System.out.println("Increment Password="+incPasswd);
			con.close();
			stmt.close();

		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			try
			{
				con.close();	
				stmt.close();
			}		
			catch(Exception ee){}
		}
	}
	public void actionPerformed(ActionEvent e)
	{	
		if(e.getSource()==btnApply)
		{
			f=new JFrame("Login");
			f.setVisible(true);
			f.setBounds(500,300,300,200);
			f.setLayout(null);
			lblUname=new JLabel("User Name");
			lblPasswd=new JLabel("Password");
			
			txtUname=new JTextField(20);
			txtPasswd=new JPasswordField(20);
			
			btnOk=new JButton("OK");
			btnClear=new JButton("Clear");
		
			f.add(lblUname);
			f.add(lblPasswd);
		
			f.add(txtUname);
			f.add(txtPasswd);
		
			f.add(btnOk);
			f.add(btnClear);
		
			lblUname.setBounds(30,30,100,20);	
			lblPasswd.setBounds(30,80,100,20);
	
			txtUname.setBounds(130,30,130,20);
			txtPasswd.setBounds(130,80,130,20);
	
			btnOk.setBounds(70,130,80,30);
			btnClear.setBounds(180,130,80,30);
			
			btnOk.addActionListener(this);		
			//btnClear.addActionListener(this);		
		
			lblHead=new JLabel("Varification Entry");
			f.add(lblHead);
			lblHead.setBounds(80,5,100,20);
		}
		if(e.getSource()==btnOk)
		{		
			String str1,str2;
			Float cinc,ninc,res;
			str1=txtUname.getText();
			str2=txtPasswd.getText();	
			
			float hraPer,daPer,caPer,maPer,taPer,eaPer,entertainmentPer,ptPer;
		
			if(str1.equals(Uname)&&str2.equals(incPasswd))
			{
				f.setVisible(false);
				
				try
				{
					cinc=Float.valueOf(txtCurrentIncrement.getText());
					ninc=Float.valueOf(txtNewIncrement.getText());
					
					
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					surl="Jdbc:Odbc:SalaryDetails";
					con=DriverManager.getConnection(surl,"","");
					query="Update SalaryDetails set AnnualIncrement=? where EmployeeId="+empId;
					pstmt=con.prepareStatement(query);
					
					pstmt.setFloat(1,Float.valueOf(ninc));		
					
					int r1=pstmt.executeUpdate();
					if(r1>0)	
						JOptionPane.showMessageDialog((Component)null,"Increment is Sucessfully Updated","Updated!!",JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog((Component)null,"Increment is Not Successfully Updated","Error!!",JOptionPane.ERROR_MESSAGE);

				System.out.println("getting of Annual basic 1");

					pstmt.clearParameters();
					con.close();
					pstmt.close();		

				System.out.println("getting of Annual basic 1");
					surl="Jdbc:Odbc:SalaryDetails";
					con=DriverManager.getConnection(surl,"","");
					stmt=con.createStatement();

				System.out.println("getting of Annual basic 1");

					query="select AnnualBasic from SalaryDetails where EmployeeId="+empId;

					System.out.println("getting of Annual basic 1");

				rs=stmt.executeQuery(query);
				
				System.out.println("getting of Annual basic 5");
					rs.next();
					fAnnual=rs.getFloat("AnnualBasic");
				System.out.println("\n "+fAnnual);
					
					System.out.println("getting of Annual basic 6");
					if(cinc<ninc)
					{
					Annual=fAnnual+ninc;
				System.out.println("\n7.1= "+Annual);
				System.out.println("getting of Annual basic 7.1");
					}
					if(cinc>ninc)
					{
						float incres=cinc-ninc;
						Annual=fAnnual-incres;
				System.out.println("\n7.2= "+Annual);
				System.out.println("getting of Annual basic 7.2");
					
					}		
				JOptionPane.showMessageDialog(null,"Employee Annual information is Used !","Salary",JOptionPane.INFORMATION_MESSAGE);	

					con.close();
					stmt.close();
					
		System.out.println("Changing the incentive values");				
			iurl="Jdbc:Odbc:Incentives";
			con=DriverManager.getConnection(iurl,"","");
			System.out.println("Error:12212");
			stmt=con.createStatement();
			System.out.println("Error:12123");
		
			String qwe="Select * from Incentives where Designation="+"'"+ires+"'";

			System.out.println("Ok Executed 1");
			
			rs=stmt.executeQuery(qwe);
			System.out.println("Ok Executed 1");

			rs.next();

			hraPer=rs.getFloat("HouseRentAllowance");

			daPer=rs.getFloat("DearenessAllowance");

			caPer=rs.getFloat("CityAllowance");

			maPer=rs.getFloat("MedicalAllowance");

			taPer=rs.getFloat("TravellingAllowance");

			eaPer=rs.getFloat("EducationAllowance");

			entertainmentPer=rs.getFloat("EntertainmentAllowance");

			ptPer=rs.getFloat("ProfessionalTax");

			float pf=rs.getFloat("ProvidendFund");

			System.out.println("Test 1233");

			/*con.close();
			stmt.close();
			rs.close();*/

		//insertion of values
			surl="Jdbc:Odbc:SalaryDetails";
			con=DriverManager.getConnection(surl,"","");
			query="Update SalaryDetails set AnnualBasic=?,MonthlyBasic=?,MonthlyIncentive=?,ProvidendFund=?,AnnualIncomeTax=?,MonthlyIncomeTax=?,MonthlyDeduction=?,MonthlyGrossSalary=?,PerDayIncome=?,AnnualGrossSalary=? where EmployeeId="+empId;
			pstmt=con.prepareStatement(query);

			System.out.println("Test 1234");

			float monthlyBasic=Annual/12;

			pstmt.setFloat(1,Annual);

			pstmt.setFloat(2,monthlyBasic);

			hraBasic=(hraPer*monthlyBasic)/100;
			daBasic=(daPer*monthlyBasic)/100;
			caBasic=(caPer*monthlyBasic)/100;
			ma=maPer;
			ea=eaPer;
			ta=taPer;
			entertainmentBasic=(entertainmentPer*monthlyBasic)/100;
			pt=ptPer;
			float pfBasic=(pf*monthlyBasic)/100;

			float monthlyIncentives=hraBasic+daBasic+caBasic+ma+ea+ta+entertainmentBasic;
			float monthlyDeduction=pt+pfBasic;
			float monthlyGross=monthlyBasic+monthlyIncentives-monthlyDeduction;
			float perDayIncome=monthlyGross/30;
			float annualGross=monthlyGross*12;

			pstmt.setFloat(3,monthlyIncentives);

			pstmt.setFloat(4,pfBasic);

			pstmt.setFloat(5,0.0f);

			pstmt.setFloat(6,0.0f);

			pstmt.setFloat(7,monthlyDeduction);

			pstmt.setFloat(8,monthlyGross);

			pstmt.setFloat(9,perDayIncome);

			pstmt.setFloat(10,annualGross);
			
			int r2=pstmt.executeUpdate();
			System.out.println("Error:12");

			if(r2>0)
			{
				JOptionPane.showMessageDialog(null,"Incentive Values Updated..!!!","Data Updated !",JOptionPane.INFORMATION_MESSAGE);	
			}
			else
			JOptionPane.showMessageDialog(null,"Error in Incentive and salary..!!!","ERROR !",JOptionPane.INFORMATION_MESSAGE);
System.out.println("Error:12111111111");

			con.close();
System.out.println("Error:1211111111111111");

			stmt.close();
System.out.println("Error:1211111111111111");
	
		}
		catch(Exception ex)
		{
		System.out.println("Error:12");
	
		System.out.println(ex.getMessage());
			try	
			{
				con.close();
				pstmt.close();
			
			}
			catch(Exception eee){}
		}
	}
		else
		{
			f.setVisible(false);
			JOptionPane.showMessageDialog((Component)null,"Wrong User Name and Password","Error",JOptionPane.ERROR_MESSAGE);
		}
	
		/*if(e.getSource()==btnClear)
		{
			txtUname.setText="";
			txtPasswd.setText="";
		}*/
	}
	}
}
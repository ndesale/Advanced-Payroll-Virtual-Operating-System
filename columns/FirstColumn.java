package columns;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import loading.*;

public class FirstColumn extends Thread implements ActionListener
{
	JButton l1,l2,l3,l4;
	JPanel p;
	JFrame j;

	DeleteEmployee dl;

	AddEmployee ae;
	UpdateEmployee1 up;

	DemoSimple ds;

	public FirstColumn(JFrame j,JPanel s)
	{
		p=s;
		this.j=j;

		l1=new JButton(new ImageIcon("icons\\Emp\\addemp.jpg"));
		l1.setRolloverIcon(new ImageIcon("icons\\Emp\\addempShine.jpg"));

		l2=new JButton(new ImageIcon("icons\\Emp\\deleteemp.jpg"));
		l2.setRolloverIcon(new ImageIcon("icons\\Emp\\deleteempShine.jpg"));

		l3=new JButton(new ImageIcon("icons\\Emp\\searchemp.jpg"));
		l3.setRolloverIcon(new ImageIcon("icons\\Emp\\searchempShine.jpg"));

		l4=new JButton(new ImageIcon("icons\\Emp\\updateemp.jpg"));
		l4.setRolloverIcon(new ImageIcon("icons\\Emp\\updateempShine.jpg"));

		l1.setToolTipText("Add Employee");
		l2.setToolTipText("Delete Employee");
		l3.setToolTipText("Search Employee");
		l4.setToolTipText("Update Employee");
	}
	public void run()
	{
		p.add(l1);
		p.add(l2);
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

		l1.setBounds(50,80,100,69);
		l2.setBounds(50,190,100,69);
		l3.setBounds(50,300,100,69);
		l4.setBounds(50,410,100,69);

		l1.addActionListener(this);
		l2.addActionListener(this);
		l3.addActionListener(this);
		l4.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==l1)
		{
			AddEmployee ae=new AddEmployee();
			ae.setExtendedState(JFrame.MAXIMIZED_BOTH);
			ae.setVisible(true);
		}
		else if(e.getSource()==l2)
		{
			dl=new DeleteEmployee();
			dl.setVisible(true);
			dl.setup();
			dl.setValue();
		}
		else if(e.getSource()==l3)
		{
			ds=new DemoSimple();
			ds.setVisible(true);
			ds.setExtendedState(JFrame.MAXIMIZED_BOTH);
		}
		else if(e.getSource()==l4)
		{
			up=new UpdateEmployee1();
			up.setVisible(true);
			up.setValue();
		}
	}
}
class AddEmployee extends JFrame implements ActionListener,ItemListener
{
	JLabel lblId,lblName,lblFName,lblDOB,lblGender,lblAddress,lblCountry,lblState,lblCity,lblPincode,lblContactNo1,lblContactNo2,lblEmail1,lblDeptId,lblDesg,lblQualification,lblDOJ,lblEmail2,lblMaritalStatus,lblChild,lblAnnualSalary;
	JTextField txtId,txtName,txtFName,txtPincode,txtContactNo1,txtContactNo2,txtEmail1,txtEmail2,txtQualification,txtChild,txtAnnualSalary;
	Choice chCountry,chState,chCity;
	Checkbox cbMale,cbFemale,cbmarried,cbunmarried;
	CheckboxGroup cbGroup,cbGroup2;
	JTextArea txtAddress;
	JLabel lblHeading=new JLabel("Add New Employee");

	Date dDOB,dDOJ;

	LoadingRing lr;

	boolean bin;

	JButton btnSave;

	JComboBox jcbDeptId,jcbDesg,jcbDate,jcbMonth,jcbYear,jcbDate1,jcbMonth1,jcbYear1;
	
	String desg[]={"Select Designation","Senior Manager","Junior Manager","Employee","Peon"};

	String date[]={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
	String month[]={"1","2","3","4","5","6","7","8","9","10","11","12"};
	String year[]={"1981","1982","1983","1984","1985","1986","1987","1988","1989","1990","1991","1992","1993","1994","1995","1996","1997","1998","1999","2000","2001","2002","2003","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020"};
	String year1[]={"1990","1991","1992","1993","1994","1995","1996","1997","1998","1999","2000","2001","2002","2003","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020","2021","2022","2023","2024","2025","2026","2027","2028","2029","2030"};

	CardLayout cl=new CardLayout();

	JPanel p1,p2,mainPanel;

	JLabel lblB,lblHeadP;
	JButton btnB;
	JTextField txtPath=new JTextField(30);

	Connection con,conIncentives,conSalaryDetails;
	PreparedStatement query,querySalaryDetails;
	Statement stmtIncentives;
	ResultSet rsIncentives;

	float hraBasic,daBasic,caBasic,ma,ta,ea,entertainmentBasic,pt;

	AddEmployee()
	{
		super("Add Employee");

		setLayout(null);

		lr=new LoadingRing(this);
		lr.start();

		mainPanel=new JPanel();
		add(mainPanel);
		mainPanel.setBounds(0,0,1366,768);
		mainPanel.setLayout(cl);
		mainPanel.setBackground(new Color(223,223,223));
		
		p1=new JPanel();
		p1.setBackground(new Color(150,150,150));

		p2=new JPanel();
		p2.setBackground(new Color(150,150,150));

		lblHeadP=new JLabel();
		lblB=new JLabel(new ImageIcon("icons\\Emp\\admin.jpg"));

		btnB=new JButton("Browse Image");

		lblB.setBounds(600,340,150,150);
		lblHeadP.setBounds(500,340,100,20);
		txtPath.setBounds(600,500,150,20);

		btnB.setBounds(600,520,150,30);

		lblHeadP.setText("Set Image");
		
		mainPanel.add(p1,"Panel1");
		mainPanel.add(p2,"Panel2");

		p1.setLayout(null);
		p2.setLayout(null);

		p1.add(lblB);
		p1.add(lblHeadP);
		p1.add(btnB);
		p1.add(txtPath);

		lblId=new JLabel("Employee ID");
		lblName=new JLabel("Name");
		lblFName=new JLabel("Father's Name");
		lblDOB=new JLabel("Date of Birth");
		lblGender=new JLabel("Gender");
		lblAddress=new JLabel("Address");
		lblCountry=new JLabel("Country");
		lblState=new JLabel("State");
		lblCity=new JLabel("City");
		lblPincode=new JLabel("Pincode");
		lblContactNo1=new JLabel("Contact No 1");
		lblContactNo2=new JLabel("Contact No 2");
		lblEmail1=new JLabel("Email1");
		lblEmail2=new JLabel("Email2");
		lblMaritalStatus=new JLabel("Marital Status");
		lblDesg=new JLabel("Designation");
		lblQualification=new JLabel("Qualification");
		lblDOJ=new JLabel("Date of Joining");
		lblDeptId=new JLabel("Department ID");
		lblChild=new JLabel("Children");
		lblAnnualSalary=new JLabel("AnnualBasicSalary");

		txtId=new JTextField(20);
		txtName=new JTextField(20);
		txtFName=new JTextField(20);
		txtPincode=new JTextField(20);
		txtContactNo1=new JTextField(20);
		txtContactNo2=new JTextField(20);
		txtEmail1=new JTextField(20);
		txtEmail2=new JTextField(20);
		txtQualification=new JTextField(20);
		txtChild=new JTextField(20);
		txtAnnualSalary=new JTextField(20);

		cbGroup=new CheckboxGroup();
		cbMale=new Checkbox("Male",false,cbGroup);
		cbFemale=new Checkbox("Female",false,cbGroup);

		cbGroup2=new CheckboxGroup();
		cbmarried=new Checkbox("Married",false,cbGroup2);
		cbunmarried=new Checkbox("Unmarried",false,cbGroup2);
		

		chCountry=new Choice();
		chState=new Choice();
		chCity=new Choice();

		txtAddress=new JTextArea(5,20);

		btnSave=new JButton(new ImageIcon("icons\\ok.jpg"));

		p1.add(lblId);
		p1.add(lblName);
		p1.add(lblFName);
		p1.add(lblDOB);
		p1.add(lblGender);
		p1.add(lblAddress);
		p1.add(lblCountry);
		p1.add(lblState);
		p1.add(lblCity);
		p1.add(lblPincode);
		p1.add(lblContactNo1);
		p1.add(lblContactNo2);
		p1.add(lblEmail1);
		p1.add(lblEmail2);
		p1.add(lblMaritalStatus);
		p1.add(lblDesg);
		p1.add(lblQualification);
		p1.add(lblDOJ);
		p1.add(lblDeptId);
		p1.add(lblChild);
		p1.add(lblAnnualSalary);
		p1.add(txtAnnualSalary);

		p1.add(lblHeading);
		lblHeading.setFont(new Font("Times New Roman",Font.BOLD,30));
		lblHeading.setBounds(500,30,300,30);

		p1.add(txtId);
		p1.add(txtName);
		p1.add(txtFName);
		p1.add(txtPincode);
		p1.add(txtContactNo1);
		p1.add(txtContactNo2);
		p1.add(txtEmail1);
		p1.add(txtEmail2);
		p1.add(txtQualification);
		p1.add(txtChild);
		p1.add(txtAnnualSalary);
	
		p1.add(cbMale);
		p1.add(cbFemale);
		p1.add(cbmarried);
		p1.add(cbunmarried);

		p1.add(btnSave);

		p1.add(chCountry);
		chCountry.add("India");
		chCountry.add("USA");
		chCountry.add("UK");

		p1.add(chState);
		chState.add("Maharashtra");
		chState.add("Tamilnadu");
		chState.add("Rajastan");
		chState.add("M.P");

		p1.add(chCity);
		chCity.add("Nasik");
		chCity.add("Mumbai");
		chCity.add("Dhule");

		p1.add(txtAddress);

		jcbDate=new JComboBox(date);
		p1.add(jcbDate);
		jcbDate.setBounds(200,220,45,20);

		jcbMonth=new JComboBox(month);
		p1.add(jcbMonth);
		jcbMonth.setBounds(245,220,50,20);

		jcbYear=new JComboBox(year);
		p1.add(jcbYear);
		jcbYear.setBounds(295,220,55,20);

		jcbDate1=new JComboBox(date);
		p1.add(jcbDate1);
		jcbDate1.setBounds(1000,220,45,20);

		jcbMonth1=new JComboBox(month);
		p1.add(jcbMonth1);
		jcbMonth1.setBounds(1045,220,50,20);

		jcbYear1=new JComboBox(year1);
		p1.add(jcbYear1);
		jcbYear1.setBounds(1095,220,55,20);

		jcbDesg=new JComboBox(desg);
		p1.add(jcbDesg);
		jcbDesg.setBounds(1000,140,150,20);

		jcbDeptId=new JComboBox();

		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("Jdbc:Odbc:Dept","","");
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("Select * from Department");

			while(rs.next())
			{
				jcbDeptId.addItem(rs.getString("DeptId"));
			}
			con.close();
		}
		catch(Exception exe)
		{}

		p1.add(jcbDeptId);
		jcbDeptId.setBounds(1000,100,150,20);

		lblId.setBounds(100,100,100,20);
		lblName.setBounds(100,140,100,20);
		lblFName.setBounds(100,180,100,20);
		lblDOB.setBounds(100,220,100,20);
		lblGender.setBounds(100,260,100,20);
		lblAddress.setBounds(100,300,100,20);
		lblCountry.setBounds(100,360,100,20);
		lblState.setBounds(100,400,100,20);
		lblCity.setBounds(100,440,100,20);
		lblPincode.setBounds(100,480,100,20);

		lblContactNo1.setBounds(500,100,100,20);
		lblContactNo2.setBounds(500,140,100,20);
		lblEmail1.setBounds(500,180,100,20);
		lblEmail2.setBounds(500,220,100,20);
		lblMaritalStatus.setBounds(500,260,100,20);
		
		lblDeptId.setBounds(900,100,100,20);
		lblDesg.setBounds(900,140,100,20);
		lblQualification.setBounds(900,180,100,20);
		lblDOJ.setBounds(900,220,100,20);
		lblChild.setBounds(500,300,100,20);
		lblAnnualSalary.setBounds(900,260,100,20);

		txtId.setBounds(200,100,150,20);
		txtName.setBounds(200,140,150,20);
		txtFName.setBounds(200,180,150,20);
		
		cbMale.setBounds(200,260,50,20);
		cbFemale.setBounds(250,260,100,20);

		cbmarried.setBounds(600,260,60,20);
		cbunmarried.setBounds(660,260,75,20);

		txtAddress.setBounds(200,300,150,40);

		chCountry.setBounds(200,360,150,20);
		chState.setBounds(200,400,150,20);
		chCity.setBounds(200,440,150,20);

		txtPincode.setBounds(200,480,150,20);
		
		txtContactNo1.setBounds(600,100,150,20);
		txtContactNo2.setBounds(600,140,150,20);
		txtEmail1.setBounds(600,180,150,20);
		txtEmail2.setBounds(600,220,150,20);

		txtQualification.setBounds(1000,180,150,20);
		txtChild.setBounds(600,300,150,20);
		txtChild.setEnabled(false);
		txtAnnualSalary.setBounds(1000,260,150,20);
	
		btnSave.setBounds(1020,600,40,40);

		btnSave.addActionListener(this);

		cbmarried.addItemListener(this);
		cbunmarried.addItemListener(this);
		btnB.addActionListener(this);

		lr.stable(false);
		lr.hide();
	}
	public void itemStateChanged(ItemEvent e)
	{
		if(cbGroup2.getSelectedCheckbox()==cbmarried)
		{
			txtChild.setEnabled(true);	
		}
		else	
		{
			txtChild.setText("");
			txtChild.setEnabled(false);
		}
		
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==btnSave)
		{
			setup();
		}
		if(e.getSource()==btnB)
		{
			FileDialog f=new FileDialog(this,"Open Image",FileDialog.LOAD);
			f.show();
			String dir=f.getDirectory();
			String fl=f.getFile();
			dir=dir.concat("\\");
			dir=dir.concat(fl);

			txtPath.setText(dir);
			txtPath.setEditable(false);

			lblB.setIcon(new ImageIcon(txtPath.getText()));
		}
	}
	void setup()
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

			String url1="Jdbc:Odbc:NayanDesale";

			System.out.println("Test 1");

			con=DriverManager.getConnection(url1,"","");
			query=con.prepareStatement("Insert Into EmplyeeDetails values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			System.out.println("Test 2");
			
			int date1=Integer.parseInt(jcbDate.getSelectedItem().toString());
			int month1=Integer.parseInt(jcbMonth.getSelectedItem().toString());
			int year1=Integer.parseInt(jcbYear.getSelectedItem().toString());
			year1=year1-1900;

			dDOB=new Date(year1,month1,date1);

			System.out.println("Date 111211");

			query.setInt(1,Integer.parseInt(txtId.getText()));
			query.setString(2,txtName.getText());
			query.setString(3,txtFName.getText());

			System.out.println("Date 111");

			query.setDate(4,dDOB);

			System.out.println("Date 111");

			query.setString(5,cbGroup.getSelectedCheckbox().getLabel());
			query.setString(6,txtAddress.getText());
			query.setString(7,chCountry.getSelectedItem());
			query.setString(8,chState.getSelectedItem());
			query.setString(9,chCity.getSelectedItem());
			query.setInt(10,Integer.parseInt(txtPincode.getText()));
			query.setString(11,txtContactNo1.getText());
			query.setString(12,txtContactNo2.getText());
			query.setString(13,txtEmail1.getText());
			query.setString(14,txtEmail2.getText());
			System.out.println("Date 111111");	
			int dept=Integer.parseInt(jcbDeptId.getSelectedItem().toString());
			query.setInt(15,dept);
			System.out.println("Date 111111");
			query.setString(16,jcbDesg.getSelectedItem().toString());
			System.out.println("Date 111111");
			query.setString(17,txtQualification.getText());

			//Date

			date1=Integer.parseInt(jcbDate1.getSelectedItem().toString());
			month1=Integer.parseInt(jcbMonth1.getSelectedItem().toString());
			year1=Integer.parseInt(jcbYear1.getSelectedItem().toString());
			year1=year1-1900;


			System.out.println("Date 111");
			dDOJ=new Date(year1,month1,date1);

			//Date Over

			System.out.println("Date 111");

			query.setDate(18,dDOJ);

			System.out.println("Date 111");

			query.setString(19,(cbGroup2.getSelectedCheckbox().getLabel()));
			query.setBoolean(21,true);

			System.out.println("Test 3");

			if(txtChild.getText().equals(""))
			query.setInt(20,0);
			else
			query.setInt(20,Integer.parseInt(txtChild.getText()));

			int res=query.executeUpdate();

			System.out.println("Date 111");

			writeSalary();

			if(res>0)
			{
				JOptionPane.showMessageDialog(null,"Record Successfully Inserted..!!!","Employee Stored !",JOptionPane.INFORMATION_MESSAGE);
				query.close();
				con.close();
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			try
			{
				query.close();
				con.close();
			}
			catch(Exception ex1){}
			txtQualification.setText(ex.getMessage());
		}
	}
	void writeSalary()
	{
		try
		{
			String url2="Jdbc:Odbc:Incentives";
			String url3="Jdbc:Odbc:SalaryDetails";


			float annual=Float.valueOf(txtAnnualSalary.getText());

			float hraPer,daPer,caPer,maPer,taPer,eaPer,entertainmentPer,ptPer;

			conIncentives=DriverManager.getConnection(url2,"","");
			System.out.println("Error:12212");
			stmtIncentives=conIncentives.createStatement();
			System.out.println("Error:12123");

			rsIncentives=stmtIncentives.executeQuery("Select * from Incentives where Designation='"+jcbDesg.getSelectedItem()+"'");

			rsIncentives.next();

			hraPer=rsIncentives.getFloat("HouseRentAllowance");

			daPer=rsIncentives.getFloat("DearenessAllowance");

			caPer=rsIncentives.getFloat("CityAllowance");

			maPer=rsIncentives.getFloat("MedicalAllowance");

			taPer=rsIncentives.getFloat("TravellingAllowance");

			eaPer=rsIncentives.getFloat("EducationAllowance");

			entertainmentPer=rsIncentives.getFloat("EntertainmentAllowance");

			ptPer=rsIncentives.getFloat("ProfessionalTax");

			float pf=rsIncentives.getFloat("ProvidendFund");

			System.out.println("Test 1233");


			conSalaryDetails=DriverManager.getConnection(url3,"","");
			querySalaryDetails=conSalaryDetails.prepareStatement("Insert into SalaryDetails values(?,?,?,?,?,?,?,?,?,?,?,?)");

			System.out.println("Test 1233");

			float monthlyBasic=annual/12;

			querySalaryDetails.setInt(1,Integer.parseInt(txtId.getText()));

			querySalaryDetails.setFloat(2,annual);

			querySalaryDetails.setFloat(3,monthlyBasic);

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

			querySalaryDetails.setFloat(4,monthlyIncentives);

			querySalaryDetails.setFloat(5,pfBasic);

			querySalaryDetails.setFloat(6,0.0f);

			querySalaryDetails.setFloat(7,0.0f);

			querySalaryDetails.setFloat(8,monthlyDeduction);

			querySalaryDetails.setFloat(9,monthlyGross);

			querySalaryDetails.setFloat(10,perDayIncome);

			querySalaryDetails.setFloat(11,annualGross);

			querySalaryDetails.setFloat(12,2000.00f);

			
			int r=querySalaryDetails.executeUpdate();
			System.out.println("Error:12");

			if(r>0)
			{
				JOptionPane.showMessageDialog(null,"Record Successfully Inserted incen and salary..!!!","Employee Stored !",JOptionPane.INFORMATION_MESSAGE);
				
			}
			else
			JOptionPane.showMessageDialog(null,"Error ince and salary..!!!","ERROR !",JOptionPane.INFORMATION_MESSAGE);

			querySalaryDetails.close();
			conSalaryDetails.close();
			conIncentives.close();
			stmtIncentives.close();
			rsIncentives.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());

			try
			{
				querySalaryDetails.close();
				conSalaryDetails.close();
				conIncentives.close();
				stmtIncentives.close();
				rsIncentives.close();
			}
			catch(Exception ex)
			{}
		}
	}
}
class DemoSimple extends JFrame implements MouseListener,ActionListener
{
	static JTabbedPane jtp;
	static JScrollPane sp;

	AdvanceLabel next,back;
	JTextField txtAddrs;
	JLabel lblAddrs;

	static JPanel p1;

	JPanel filterPanel;

	JButton btnSearch;

	MenuBar mb;
	Menu search;
	MenuItem filter,close;

	static JPanel resultPanel;

	Statement stmt;
	Connection con;
	ResultSet rs;

	static JPanel p[];
	static int nod=0;

	JLabel lblId,lblName,lblDeptId,lblDesg,lblQf;
	JTextField txtId,txtName,txtQf;
	Choice chDeptId,chDesg;
	JButton btnFind,btnClear;

	DemoSimple()
	{
		super("My Computer");
		String names="";

		try
		{
			System.out.println("TESTED 0");
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			System.out.println("TESTED 1");
			Connection con=DriverManager.getConnection("Jdbc:Odbc:Dept","","");
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("Select * from Department");
			System.out.println("WELCOME SEARCH HERE!!");
			while(rs.next())
			{
				nod++;
				names=names.concat(rs.getString("DeptName")+":");
			}
			con.close();
		}
		catch(Exception exe)
		{
			System.out.println("ERROR: "+exe.getMessage());
		}

		p=new JPanel[nod];

		Container c=getContentPane();
		c.setLayout(null);

		mb=new MenuBar();
		search=new Menu("Search");
		filter=new MenuItem("Filter");
		close=new MenuItem("Close");

		btnSearch=new JButton(new ImageIcon("icons\\shining\\search.jpg"));
		btnSearch.setRolloverIcon(new ImageIcon("icons\\shining\\searchShine.jpg"));

		setMenuBar(mb);
		mb.add(search);
		search.add(filter);
		search.addSeparator();
		search.add(close);

		txtAddrs=new JTextField(20);
		lblAddrs=new JLabel("Search",JLabel.CENTER);
		
		c.add(txtAddrs);
		c.add(lblAddrs);
		c.add(btnSearch);
	
		txtAddrs.setBounds(180,60,1150,20);
		lblAddrs.setBounds(90,60,100,20);

		btnSearch.setBounds(1330,60,30,20);

		back=new AdvanceLabel("",new ImageIcon("back.png"));
		next=new AdvanceLabel("",new ImageIcon("next.png"));		
		
		c.add(back);
		c.add(next);

		back.addMouseListener(this);

		back.setBounds(0,0,60,60);
		next.setBounds(60,0,60,60);

		next.setRolloverIcon(new ImageIcon("nextShine.png"));
		back.setRolloverIcon(new ImageIcon("backShine.png"));

		next.setPressedIcon(new ImageIcon("nextPressed.png"));
		back.setPressedIcon(new ImageIcon("backPressed.png"));

		p1=new JPanel();
		p1.setLayout(new GridLayout(0,5,20,20));

		for(int i=0;i<nod;i++)
		{
			p[i]=new JPanel();
			p[i].setLayout(new GridLayout(0,5,20,20));
			p[i].setBackground(Color.WHITE);
		}

		p1.setBackground(new Color(255,255,255));

		sp=new JScrollPane(p1,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		jtp=new JTabbedPane(JTabbedPane.LEFT);
		jtp.setBounds(0,80,1366,625);

		jtp.addTab("All Depts",sp);
		
		for(int i=0;i<nod;i++)
		{
			jtp.addTab(names.split(":")[i],p[i]);
		}

		sp.setBackground(Color.WHITE);

		c.add(jtp);

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
				p1.add(new AdvanceLabel(nm+" - "+id,new ImageIcon("folder.jpg")));
				int d=rs.getInt("DeptID");

				p[d-11].add(new AdvanceLabel(nm+" - "+id,new ImageIcon("folder.jpg")));
			}
			rs.close();
			con.close();
			stmt.close();

			btnSearch.addActionListener(this);
			filter.addActionListener(this);

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
	public void mousePressed(MouseEvent e)
	{
	}
	public void mouseReleased(MouseEvent e)
	{
	}
	public void mouseEntered(MouseEvent e)
	{
	}
	public void mouseExited(MouseEvent e)
	{
	}
	public void mouseClicked(MouseEvent e)
	{
		if(e.getSource()==back)
		{
			if(jtp.getSelectedIndex()==0)
			{
				jtp.setComponentAt(0,p1);
				p1.repaint();
			}
			else
			{
				jtp.setComponentAt(jtp.getSelectedIndex(),p[jtp.getSelectedIndex()-1]);
				p[jtp.getSelectedIndex()-1].repaint();
			}
		}
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==btnSearch)
		{
			if(jtp.getSelectedIndex()!=0)
			{
				JOptionPane.showConfirmDialog(null,"Please Select the First Tab ('All Depts')");
			}
			else
			{
			try
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				
				String url="Jdbc:Odbc:NayanDesale";
				con=DriverManager.getConnection(url,"","");

				System.out.println("Stored Data: 1");
				stmt=con.createStatement();
				System.out.println("Stored Data: 2");
				rs=stmt.executeQuery("Select * from EmplyeeDetails Where EmployeeName LIKE '"+txtAddrs.getText()+"%'");
				{
					resultPanel=new JPanel();
					resultPanel.setLayout(new FlowLayout(FlowLayout.LEFT,20,20));

					jtp.setComponentAt(0,resultPanel);
						
					System.out.println("Stored Data: 2");
					int count=0;
					while(rs.next())
					{
						count=1;

						int id=rs.getInt("EmployeeId");
						String nm=rs.getString("EmployeeName");
						
						resultPanel.add(new AdvanceLabel(nm+" - "+id,new ImageIcon("folder.jpg")));

					}
					if(count==0)
					JOptionPane.showConfirmDialog(null,txtAddrs.getText()+": Record Not Found ???");

					rs.close();
					con.close();
					stmt.close();
				}
			}
			catch(Exception ex1)
			{
				try
				{
					rs.close();
					con.close();
					stmt.close();
				}
				catch(Exception eed){}
				JOptionPane.showConfirmDialog(null,ex1.getMessage());
			}
			}
		}
		if(e.getSource()==filter)
		{
			jtp.setComponentAt(0,p1); 
			p1.repaint();

			JFrame filterWin=new JFrame("Filter");
			Container cn=filterWin.getContentPane();
			cn.setLayout(null);
			
			cn.setBackground(new Color(120,120,120));

			filterWin.setLocation(400,200);
			filterWin.setSize(400,350);

			filterWin.setVisible(true);
			filterWin.setResizable(false);

			lblId=new JLabel("Employee Id");
			lblName=new JLabel("Employee Name");
			lblDeptId=new JLabel("Dept Id");
			lblDesg=new JLabel("Designation");
			lblQf=new JLabel("Qualification");

			txtId=new JTextField(20);
			txtName=new JTextField(20);
			
			chDeptId=new Choice();
			chDeptId.add("11");	
			chDeptId.add("12");
			chDeptId.add("13");
			chDeptId.add("14");
			chDeptId.add("15");
			chDeptId.add("16");
			chDeptId.add("17");

			chDesg=new Choice();
			chDesg.add("Employee");
			chDesg.add("Manager");

			txtQf=new JTextField(20);

			btnFind=new JButton("Search");
			btnClear=new JButton("Clear");

			cn.add(lblId);
			cn.add(txtId);

			cn.add(lblName);
			cn.add(txtName);
	
			cn.add(lblDeptId);
			cn.add(chDeptId);

			cn.add(lblDesg);
			cn.add(chDesg);

			cn.add(lblQf);
			cn.add(txtQf);

			cn.add(btnFind);
			cn.add(btnClear);

			lblId.setBounds(30,30,100,20);
			txtId.setBounds(130,30,150,20);
	
			lblName.setBounds(30,70,100,20);
			txtName.setBounds(130,70,150,20);

			lblDeptId.setBounds(30,110,100,20);
			chDeptId.setBounds(130,110,150,20);

			lblDesg.setBounds(30,150,100,20);
			chDesg.setBounds(130,150,150,20);

			lblQf.setBounds(30,190,100,20);
			txtQf.setBounds(130,190,150,20);
			
			btnFind.setBounds(30,240,100,30);
			btnClear.setBounds(180,240,100,30);

			btnFind.addActionListener(this);
			btnClear.addActionListener(this);
		}
		if(e.getSource()==btnFind)
		{
			try
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				
				String url="Jdbc:Odbc:NayanDesale";
				con=DriverManager.getConnection(url,"","");

				System.out.println("Stored Data: 1");
				stmt=con.createStatement();
				System.out.println("Stored Data: 2");

				if(txtQf.getText().equals(""))
				rs=stmt.executeQuery("Select * from EmplyeeDetails Where EmployeeId="+txtId.getText()+" AND EmployeeName LIKE '"+txtName.getText()+"%' AND DeptID="+chDeptId.getSelectedItem()+" AND Designation='"+chDesg.getSelectedItem()+"'");
				else
				rs=stmt.executeQuery("Select * from EmplyeeDetails Where EmployeeId="+txtId.getText()+" AND EmployeeName LIKE '"+txtName.getText()+"%' AND DeptID="+chDeptId.getSelectedItem()+" AND Designation='"+chDesg.getSelectedItem()+"' OR Qualification='"+txtQf.getText()+"'");
				
				int count=0;
				
				while(rs.next())
				{
					resultPanel=new JPanel();
					resultPanel.setLayout(new FlowLayout(FlowLayout.LEFT,20,20));

					jtp.setComponentAt(0,resultPanel);

					String nm=rs.getString("EmployeeName");
					int id=rs.getInt("EmployeeId");

					resultPanel.add(new AdvanceLabel(nm+" - "+id,new ImageIcon("folder.jpg")));
					resultPanel.repaint();
					count=1;
				}
				if(count==0)
				JOptionPane.showConfirmDialog(null,txtId.getText()+": Record not Found ???");
				else
				JOptionPane.showConfirmDialog(null,txtId.getText()+": Record is Found !!! Now Close the Filter window and see the search result");
				
				rs.close();
				con.close();
				stmt.close();
			}
			catch(Exception exd)
			{
				try
				{
					rs.close();
					con.close();
					stmt.close();
				}catch(Exception ef){}
			}
		}
		if(e.getSource()==btnClear)
		{
			txtId.setText("");
			txtName.setText("");
			txtQf.setText("");
		}
	}
}
class AdvanceLabel extends JLabel implements MouseListener
{
	ImageIcon normalIcon,rolloverIcon,pressedIcon;
	JLabel lblName;

	Container parent;
	static JPanel subPanel;

	static String strId[];
	Incentive ic;
	Deduction dc;

	AdvanceLabel(String ln,ImageIcon icon)
	{
		super(ln,icon,0);
		normalIcon=icon;
	
		addMouseListener(this);

		rolloverIcon=new ImageIcon("folderShine.jpg");
		pressedIcon=new ImageIcon("folderPressed.jpg");
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
			setIcon(pressedIcon);
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
		AdvanceLabel al=(AdvanceLabel)e.getSource();
		if(al==this)
		{
			if(al.getText().equals("Incentives"))
			{
				ic=new Incentive();
				ic.setExtendedState(JFrame.MAXIMIZED_BOTH);
				ic.setVisible(true);
				ic.setup(Integer.parseInt(strId[1]));
			}
			else if(al.getText().equals("Deduction"))
			{
				dc=new Deduction(Integer.parseInt(strId[1]));
				dc.setExtendedState(JFrame.MAXIMIZED_BOTH);
				dc.setVisible(true);
			}	
			else
			{
				subPanel=new JPanel();
				subPanel.setLayout(new FlowLayout(FlowLayout.LEFT,30,30));

				strId=al.getText().split("- ");	
	
				AdvanceLabel lblIncentive=new AdvanceLabel("Incentives",new ImageIcon("file.png"));
				lblIncentive.setRolloverIcon(new ImageIcon("fileShine.png"));
				lblIncentive.setPressedIcon(new ImageIcon("filePressed.png"));

				AdvanceLabel lblDeduction=new AdvanceLabel("Deduction",new ImageIcon("file.png"));
				lblDeduction.setRolloverIcon(new ImageIcon("fileShine.png"));
				lblDeduction.setPressedIcon(new ImageIcon("filePressed.png"));

				AdvanceLabel lblDeductionRpt=new AdvanceLabel("Deduction Report",new ImageIcon("icons\\shining\\report.jpg"));
				lblDeductionRpt.setRolloverIcon(new ImageIcon("icons\\shining\\reportshine.jpg"));
				lblDeductionRpt.setPressedIcon(new ImageIcon("icons\\shining\\reportpressed.jpg"));

				AdvanceLabel lblIncentiveRpt=new AdvanceLabel("Incentive Report",new ImageIcon("icons\\shining\\report.jpg"));
				lblIncentiveRpt.setRolloverIcon(new ImageIcon("icons\\shining\\reportshine.jpg"));
				lblIncentiveRpt.setPressedIcon(new ImageIcon("icons\\shining\\reportpressed.jpg"));

				subPanel.add(lblIncentive);
				subPanel.add(lblDeduction);
				subPanel.add(lblIncentiveRpt);
				subPanel.add(lblDeductionRpt);		

			for(int i=0;i<=DemoSimple.nod;i++)
			{
				if(DemoSimple.jtp.getSelectedIndex()==i)
				{
					DemoSimple.jtp.setComponentAt(i,subPanel);
				}
				else
				{
					if(i==0)
					DemoSimple.jtp.setComponentAt(i,DemoSimple.p1);
					else
					DemoSimple.jtp.setComponentAt(i,DemoSimple.p[i-1]);
				}
			}
			}
		}
	}
}

class Incentive extends JFrame 
{
	JLabel lblId,lblName,lblDesg,lblDept,lblDOJ,lblAnnualSalary,lblHRA,lblDA,lblMA,lblCA,lblTA,lblENA,lblEDA,lblAmount;
	JTextField txtId,txtName,txtDept,txtDesg,txtDOJ,txtAnnualSalary,txtHRA,txtDA,txtMA,txtCA,txtTA,txtENA,txtEDA;
	JTextField hra,da,ta,ma,ena,eda,ca;
	JLabel hra1,da1,ta1,ma1,ena1,eda1,ca1;
	
	JLabel lblHeading=new JLabel("Incentives");
	
	JPanel p1;

	Connection con;
	Statement stmt;
	ResultSet rs,rs1,rs2,rs3;

	Incentive()
	{
		super("Incentives");
	}

	void setup(int id)
	{
		setLayout(null);
		
		p1=new JPanel();
		add(p1);

		p1.setBounds(0,0,1366,768);
		p1.setLayout(null);
		p1.setBackground(new Color(223,223,223));
		p1.setVisible(true);		

		lblId=new JLabel("Employee ID");
		lblName=new JLabel("Name");
		lblDesg=new JLabel("Department");
		lblDept=new JLabel("Designation");
		lblDOJ=new JLabel("Date of Joining");
		lblAnnualSalary=new JLabel("Annual Basic Salary");
		lblHRA=new JLabel("HRA");
		lblDA=new JLabel("DA");
		lblCA=new JLabel("CA");
		lblMA=new JLabel("MA");
		lblTA=new JLabel("TA");
		lblENA=new JLabel("ENA");
		lblEDA=new JLabel("EDA");	
		lblAmount=new JLabel("Amount(Rs)");

		txtId=new JTextField(20);
		txtName=new JTextField(20);
		txtDept=new JTextField(20);
		txtDesg=new JTextField(20);
		txtDOJ=new JTextField(20);
		txtAnnualSalary=new JTextField(20);
		txtHRA=new JTextField(10);
		txtDA=new JTextField(10);
		txtMA=new JTextField(10);
		txtCA=new JTextField(10);
		txtTA=new JTextField(10);
		txtENA=new JTextField(10);
		txtEDA=new JTextField(10);
	
		hra=new JTextField(20);
		da=new JTextField(20);
		ma=new JTextField(20);
		ca=new JTextField(20);	
		ta=new JTextField(20);
		ena=new JTextField(20);
		eda=new JTextField(20);
				

		hra1=new JLabel(" %");
		da1=new JLabel(" %");
		ma1=new JLabel("RS.");
		ca1=new JLabel(" %");
		ta1=new JLabel("Rs.");
		ena1=new JLabel("Rs.");
		eda1=new JLabel("RS.");
				

		p1.add(lblId);
		p1.add(lblName);
		p1.add(lblDesg);
		p1.add(lblDept);
		p1.add(lblDOJ);
		p1.add(lblAnnualSalary);
		p1.add(lblHRA);
		p1.add(lblDA);
		p1.add(lblCA);
		p1.add(lblMA);
		p1.add(lblTA);
		p1.add(lblENA);
		p1.add(lblEDA);
		p1.add(lblAmount);

		p1.add(txtId);
		p1.add(txtName);
		p1.add(txtDept);
		p1.add(txtDesg);
		p1.add(txtDOJ);
		p1.add(txtAnnualSalary);
		p1.add(txtHRA);
		p1.add(txtDA);
		p1.add(txtMA);
		p1.add(txtCA);
		p1.add(txtTA);
		p1.add(txtENA);
		p1.add(txtEDA);
	
		p1.add(hra);
		p1.add(da);
		p1.add(ma);
		p1.add(ca);
		p1.add(ta);
		p1.add(ena);
		p1.add(eda);


		p1.add(hra1);
		p1.add(da1);
		p1.add(ma1);
		p1.add(ca1);
		p1.add(ta1);
		p1.add(ena1);
		p1.add(eda1);

	
		lblId.setBounds(100,100,100,20);
		lblName.setBounds(100,140,100,20);
		lblDesg.setBounds(100,180,100,20);
		lblDept.setBounds(100,220,100,20);
		lblDOJ.setBounds(100,260,100,20);
		lblAnnualSalary.setBounds(100,300,100,20);
		
		lblHRA.setBounds(550,100,100,20);
		lblDA.setBounds(550,140,100,20);
		lblCA.setBounds(550,180,100,20);
		lblMA.setBounds(550,220,100,20);
		lblTA.setBounds(550,260,100,20);
		lblENA.setBounds(550,300,100,20);
		lblEDA.setBounds(550,340,100,20);
		
		lblAmount.setBounds(550,75,100,20);

		txtId.setBounds(200,100,150,20);
		txtName.setBounds(200,140,150,20);
		txtDept.setBounds(200,180,150,20);
		txtDesg.setBounds(200,220,150,20);
		txtDOJ.setBounds(200,260,150,20);
		txtAnnualSalary.setBounds(200,300,150,20);
		
		txtHRA.setBounds(650,100,60,20);
		txtDA.setBounds(650,140,60,20);
		txtMA.setBounds(650,180,60,20);
		txtCA.setBounds(650,220,60,20);
		txtTA.setBounds(650,260,60,20);
		txtENA.setBounds(650,300,60,20);
		txtEDA.setBounds(650,340,60,20);
	
		hra.setBounds(750,100,100,20);
		da.setBounds(750,140,100,20);
		ma.setBounds(750,180,100,20);
		ca.setBounds(750,220,100,20);
		ta.setBounds(750,260,100,20);
		ena.setBounds(750,300,100,20);
		eda.setBounds(750,340,100,20);


		hra1.setBounds(710,100,10,20);
		da1.setBounds(710,140,10,20);
		ma1.setBounds(710,180,10,20);
		ca1.setBounds(710,220,10,20);
		ta1.setBounds(710,260,10,20);
		ena1.setBounds(710,300,10,20);
		eda1.setBounds(710,340,10,20);

		p1.add(lblHeading);
		lblHeading.setFont(new Font("Times New Roman",Font.BOLD,35));
		lblHeading.setBounds(500,30,300,40);

		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			
			String url="Jdbc:Odbc:NayanDesale";
			con=DriverManager.getConnection(url,"","");

			stmt=con.createStatement();

			rs=stmt.executeQuery("Select * from EmplyeeDetails where EmployeeId="+id);

System.out.println("Tested1");

			rs.next();
System.out.println("Tested2");
			String empName=rs.getString("EmployeeName");
			String desg=rs.getString("Designation");
			String doj=rs.getString("DOJ");
			int deptId=rs.getInt("DeptID");
System.out.println("Tested3");
			
			con.close();
System.out.println("Tested4");
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			url="Jdbc:Odbc:Dept";

			con=DriverManager.getConnection(url,"","");

			stmt=con.createStatement();
System.out.println("Tested5");
			rs=stmt.executeQuery("Select * from Department where DeptId="+deptId);
System.out.println("Tested6");
			rs.next();
System.out.println("Tested7");
			String deptName=rs.getString("DeptName");
System.out.println("Tested8");
			
			con.close();
System.out.println("Tested9");
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			url="Jdbc:Odbc:Incentives";

			con=DriverManager.getConnection(url,"","");

			stmt=con.createStatement();
System.out.println("Tested10");
			rs=stmt.executeQuery("Select * from Incentives where Designation='"+desg+"'");
System.out.println("Tested11");
			rs.next();
System.out.println("Tested12");			
			float hraPer=rs.getFloat("HouseRentAllowance");
			float daPer=rs.getFloat("DearenessAllowance");
			float caPer=rs.getFloat("CityAllowance");
			float maPer=rs.getFloat("MedicalAllowance");
			float eaPer=rs.getFloat("EducationAllowance");
			float taPer=rs.getFloat("TravellingAllowance");
			float enaPer=rs.getFloat("EntertainmentAllowance");
System.out.println("Tested13");
			
			con.close();

			url="Jdbc:Odbc:SalaryDetails";

			con=DriverManager.getConnection(url,"","");

			stmt=con.createStatement();

			rs=stmt.executeQuery("Select * from SalaryDetails where EmployeeId="+id);
System.out.println("Tested14");
			rs.next();
System.out.println("Tested15");
			float as=rs.getFloat("AnnualBasic");
			float monthlyBasic=rs.getFloat("MonthlyBasic");
System.out.println("Tested16");
			
			rs.close();
			con.close();

			float hraAmt=(hraPer*monthlyBasic)/100;
			float daAmt=(daPer*monthlyBasic)/100;
			float caAmt=(caPer*monthlyBasic)/100;
			float eAmt=(enaPer*monthlyBasic)/100;
		
			txtId.setText(id+"");
			txtName.setText(empName);
			txtDept.setText(deptName);
			txtDesg.setText(desg);
			txtDOJ.setText(doj);

			txtAnnualSalary.setText(as+"");
			txtHRA.setText(hraPer+"");
			txtDA.setText(daPer+"");

			ma.setText(maPer+"");

			txtCA.setText(caPer+"");
			ta.setText(taPer+"");
			ena.setText(""+eAmt);
			eda.setText(eaPer+"");

			hra.setText(hraAmt+"");
			da.setText(daAmt+"");
			ca.setText(caAmt+"");

		}
		catch(Exception es)
		{
			try
			{
				stmt.close();
				rs.close();
				con.close();
			}
			catch(Exception ewe){}
			System.out.println(es.getMessage());
		}
	
	}	
}

class Deduction extends JFrame 
{
	JLabel lblId,lblName,lblDesg,lblDept,lblDOJ,lblAnnualSalary,lblPF,lblPT,lblLWP,lblIncomeTax;
	JTextField txtId,txtName,txtDept,txtDesg,txtDOJ,txtAnnualSalary,txtPF,txtPT,txtLWP,txtIncomeTax;
	JTextField PF,PT,LWP,IncomeTax;
	JLabel PF1,PT1,LWP1,IncomeTax1;
	JLabel lblAmount;

	JLabel lblHeading=new JLabel("Deduction");

	JPanel p1;

	Connection con;
	Statement stmt;
	ResultSet rs;

	Deduction(int id)
	{
		super("Deduction");

		setLayout(null);
		
		p1=new JPanel();
		add(p1);
		p1.setBounds(0,0,1366,768);
		p1.setLayout(null);
		p1.setBackground(new Color(223,223,223));
		p1.setVisible(true);		

		lblId=new JLabel("Employee ID");
		lblName=new JLabel("Name");
		lblDesg=new JLabel("Department");
		lblDept=new JLabel("Designation");
		lblDOJ=new JLabel("Date of Joining");
		lblAnnualSalary = new JLabel("Annual Basic Salary");
		lblPF=new JLabel("PF");
		lblPT=new JLabel("PT");
		lblLWP=new JLabel("LWP");
		lblIncomeTax=new JLabel("IncomeTax");
		lblAmount=new JLabel("Amount(Rs)");

		txtId=new JTextField(20);
		txtName=new JTextField(20);
		txtDept=new JTextField(20);
		txtDesg=new JTextField(20);
		txtDOJ=new JTextField(20);
		txtAnnualSalary=new JTextField(20);
		txtPF=new JTextField(10);
		txtPT=new JTextField(10);
		txtLWP=new JTextField(10);
		txtIncomeTax=new JTextField(10);
	
		PF=new JTextField(20);
		PT=new JTextField(20);
		LWP=new JTextField(20);
		IncomeTax=new JTextField(20);	
		
				

		PF1=new JLabel("%");
		PT1=new JLabel("Rs");
		LWP1=new JLabel("Rs");
		IncomeTax1=new JLabel("Rs");	
				

		p1.add(lblId);
		p1.add(lblName);
		p1.add(lblDesg);
		p1.add(lblDept);
		p1.add(lblDOJ);
		p1.add(lblPF);
		p1.add(lblPT);
		p1.add(lblLWP);
		p1.add(lblIncomeTax);
		p1.add(lblAnnualSalary);
		p1.add(lblAmount);

		p1.add(txtId);
		p1.add(txtName);
		p1.add(txtDept);
		p1.add(txtDesg);
		p1.add(txtDOJ);
		p1.add(txtAnnualSalary);
		p1.add(txtPF);
		p1.add(txtPT);
		p1.add(txtLWP);
		p1.add(txtIncomeTax);
	
		p1.add(PF);
		p1.add(PT);
		p1.add(LWP);
		p1.add(IncomeTax);

		p1.add(PF1);
		p1.add(PT1);
		p1.add(LWP1);
		p1.add(IncomeTax1);
	
		lblId.setBounds(100,100,100,20);
		lblName.setBounds(100,140,100,20);
		lblDesg.setBounds(100,180,100,20);
		lblDept.setBounds(100,220,100,20);
		lblDOJ.setBounds(100,260,100,20);
		lblAnnualSalary.setBounds(100,300,100,20);
		
		lblPF.setBounds(550,100,100,20);
		lblPT.setBounds(550,140,100,20);
		lblLWP.setBounds(550,180,100,20);
		lblIncomeTax.setBounds(550,220,100,20);
		
		lblAmount.setBounds(550,75,100,20);
		

		txtId.setBounds(200,100,150,20);
		txtName.setBounds(200,140,150,20);
		txtDept.setBounds(200,180,150,20);
		txtDesg.setBounds(200,220,150,20);
		txtDOJ.setBounds(200,260,150,20);
		txtAnnualSalary.setBounds(200,300,150,20);
		
		txtPF.setBounds(650,100,60,20);
		txtPT.setBounds(650,140,60,20);
		txtLWP.setBounds(650,180,60,20);
		txtIncomeTax.setBounds(650,220,60,20);

		PF.setBounds(750,100,100,20);
		PT.setBounds(750,140,100,20);
		LWP.setBounds(750,180,100,20);
		IncomeTax.setBounds(750,220,100,20);
		
		PF1.setBounds(710,100,10,20);
		PT1.setBounds(710,140,10,20);
		LWP1.setBounds(710,180,10,20);
		IncomeTax1.setBounds(710,220,10,20);
	
		p1.add(lblHeading);
		lblHeading.setFont(new Font("Times New Roman",Font.BOLD,35));
		lblHeading.setBounds(500,30,300,40);
	

		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

			String url="Jdbc:Odbc:NayanDesale";
			con=DriverManager.getConnection(url,"","");

			stmt=con.createStatement();

			rs=stmt.executeQuery("Select * from EmplyeeDetails where EmployeeId="+id);

System.out.println("Tested1");

			rs.next();
System.out.println("Tested2");
			String empName=rs.getString("EmployeeName");
			String desg=rs.getString("Designation");
			String doj=rs.getString("DOJ");
			int deptId=rs.getInt("DeptID");
System.out.println("Tested3");
			
			con.close();
System.out.println("Tested4");
			
			url="Jdbc:Odbc:Dept";

			con=DriverManager.getConnection(url,"","");

			stmt=con.createStatement();
System.out.println("Tested5");
			rs=stmt.executeQuery("Select * from Department where DeptId="+deptId);
System.out.println("Tested6");
			rs.next();
System.out.println("Tested7");
			String deptName=rs.getString("DeptName");
System.out.println("Tested8");
			
			con.close();
System.out.println("Tested9");
			
			url="Jdbc:Odbc:Incentives";

			con=DriverManager.getConnection(url,"","");

			stmt=con.createStatement();
System.out.println("Tested10");
			rs=stmt.executeQuery("Select * from Incentives where Designation='"+desg+"'");

			rs.next();

			float pf=rs.getFloat("ProvidendFund");
System.out.println("Tested11");
			float pt=rs.getFloat("ProfessionalTax");
System.out.println("Tested12");
			con.close();

			url="Jdbc:Odbc:SalaryDetails";

			con=DriverManager.getConnection(url,"","");

			stmt=con.createStatement();

			rs=stmt.executeQuery("Select * from SalaryDetails where EmployeeId="+id);
System.out.println("Tested13");
			rs.next();
System.out.println("Tested14");
			float as=rs.getFloat("AnnualBasic");
			float mit=rs.getFloat("MonthlyIncomeTax");
System.out.println("Tested15");
			con.close();
System.out.println("Tested16");

			txtId.setText(id+"");
			txtName.setText(empName+"");
			txtDept.setText(deptName+"");
			txtDesg.setText(desg+"");
			txtDOJ.setText(doj+"");

			txtPF.setText(pf+"");
			txtPT.setText("");
			txtLWP.setText("");
			txtIncomeTax.setText("");

			float pfAmt=(pf*as)/100;

			PF.setText(pfAmt+"");
			PT.setText(pt+"");
			IncomeTax.setText(mit+"");

			txtAnnualSalary.setText(as+"");
		}
		catch(Exception ex)
		{
			try
			{
				rs.close();
				stmt.close();
				con.close();
			}catch(Exception ad){}
			System.out.println(ex.getMessage());
		}
		finally
		{
			try
			{
				rs.close();
			}catch(Exception sd){}
		}
	}
}

class DeleteEmployee extends JFrame implements ActionListener,ItemListener
{
	Connection con;
	Statement stmt;
	ResultSet rs;

	JLabel lblId,lblName,lblFName,lblDOB,lblGender,lblAddress,lblCountry,lblState,lblCity,lblPincode,lblContactNo1,lblContactNo2,lblEmail1,lblDeptId,lblDesg,lblQualification,lblDOJ,lblEmail2,lblMaritalStatus,lblChild,lblAnnualSalary;
	JTextField txtId,txtName,txtFName,txtDOB,txtPincode,txtContactNo1,txtContactNo2,txtEmail1,txtEmail2,txtQualification,txtDOJ,txtDeptId,txtChild,txtAnnualSalary,txtCountry,txtState,txtCity,txtDesi;
	Checkbox cbMale,cbFemale,cbMarried,cbUnmarried;
	CheckboxGroup cbGroup1,cbGroup2;
	JTextArea txtAddress;
	
	JLabel lblHeading=new JLabel("Delete Employee");

	JButton btnDelete,btnCancel;


	JPanel p1;
	String str;
	int empId;
	
	DeleteEmployee()
	{
		super("Delete Employee");
	}
	void setup()
	{
		setLayout(null);

		str=JOptionPane.showInputDialog("Enter the Employee Id");
		empId=Integer.parseInt(str);
		
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		p1=new JPanel();
		add(p1);
		p1.setBounds(0,0,1366,768);
		p1.setLayout(null);
		p1.setBackground(new Color(223,223,223));
		p1.setVisible(true);		

		lblId=new JLabel("Employee ID");
		lblName=new JLabel("Name");
		lblFName=new JLabel("Father's Name");
		lblDOB=new JLabel("Date of Birth");
		lblGender=new JLabel("Gender");
		lblAddress=new JLabel("Address");
		lblCountry=new JLabel("Country");
		lblState=new JLabel("State");
		lblCity=new JLabel("City");
		lblPincode=new JLabel("Pincode");
		lblContactNo1=new JLabel("Contact No 1");
		lblContactNo2=new JLabel("Contact No 2");
		lblEmail1=new JLabel("Email1");
		lblEmail2=new JLabel("Email2");
		lblMaritalStatus=new JLabel("Marital Status");
		lblDesg=new JLabel("Designation");
		lblQualification=new JLabel("Qualification");
		lblDOJ=new JLabel("Date of Joining");
		lblDeptId=new JLabel("Department ID");
		lblChild=new JLabel("Children");
		lblAnnualSalary=new JLabel("Annual Basic Salary");

		txtId=new JTextField(20);
		txtName=new JTextField(20);
		txtFName=new JTextField(20);
		txtDOB=new JTextField(20);
		txtPincode=new JTextField(20);
		txtContactNo1=new JTextField(20);
		txtContactNo2=new JTextField(20);
		txtEmail1=new JTextField(20);
		txtEmail2=new JTextField(20);
		txtQualification=new JTextField(20);
		txtDOJ=new JTextField(20);
		txtDeptId=new JTextField(20);
		txtChild=new JTextField(20);
		txtAnnualSalary=new JTextField(20);

		btnDelete=new JButton("Delete");
		btnCancel=new JButton("Cancel");

		cbGroup1=new CheckboxGroup();
		cbMale=new Checkbox("Male",false,cbGroup1);
		cbFemale=new Checkbox("Female",false,cbGroup1);

		cbGroup2=new CheckboxGroup();
		cbMarried=new Checkbox("Married",false,cbGroup2);
		cbUnmarried=new Checkbox("Unmarried",false,cbGroup2);
		
		txtAddress=new JTextArea(5,20);
		txtCountry=new JTextField(20);
		txtState=new JTextField(20);
		txtCity=new JTextField(20);
		txtDesi=new JTextField(20);

		p1.add(lblId);
		p1.add(lblName);
		p1.add(lblFName);
		p1.add(lblDOB);
		p1.add(lblGender);
		p1.add(lblAddress);
		p1.add(lblCountry);
		p1.add(lblState);
		p1.add(lblCity);
		p1.add(lblPincode);
		p1.add(lblContactNo1);
		p1.add(lblContactNo2);
		p1.add(lblEmail1);
		p1.add(lblEmail2);
		p1.add(lblMaritalStatus);
		p1.add(lblDesg);
		p1.add(lblQualification);
		p1.add(lblDOJ);
		p1.add(lblDeptId);
		p1.add(lblChild);
		p1.add(lblAnnualSalary);
		p1.add(txtAnnualSalary);

		p1.add(lblHeading);
		lblHeading.setFont(new Font("Times New Roman",Font.BOLD,30));
		lblHeading.setBounds(500,30,300,40);

		p1.add(txtId);
		p1.add(txtName);
		p1.add(txtFName);
		p1.add(txtDOB);
		p1.add(txtPincode);
		p1.add(txtContactNo1);
		p1.add(txtContactNo2);
		p1.add(txtEmail1);
		p1.add(txtEmail2);
		p1.add(txtQualification);
		p1.add(txtDOJ);
		p1.add(txtDeptId);
		p1.add(txtChild);
		p1.add(txtAnnualSalary);
	
		p1.add(btnDelete);
		p1.add(btnCancel);

		p1.add(cbMale);
		p1.add(cbFemale);
		p1.add(cbMarried);
		p1.add(cbUnmarried);

		
		p1.add(txtAddress);
		p1.add(txtCountry);
		p1.add(txtState);
		p1.add(txtCity);
		p1.add(txtDesi);

		lblId.setBounds(100,100,100,20);
		lblName.setBounds(100,140,100,20);
		lblFName.setBounds(100,180,100,20);
		lblDOB.setBounds(100,220,100,20);
		lblGender.setBounds(100,260,100,20);
		lblAddress.setBounds(100,300,100,20);
		lblCountry.setBounds(100,360,100,20);
		lblState.setBounds(100,400,100,20);
		lblCity.setBounds(100,440,100,20);
		lblPincode.setBounds(100,480,100,20);

		lblContactNo1.setBounds(500,100,100,20);
		lblContactNo2.setBounds(500,140,100,20);
		lblEmail1.setBounds(500,180,100,20);
		lblEmail2.setBounds(500,220,100,20);
		lblMaritalStatus.setBounds(500,260,100,20);
		
		lblDeptId.setBounds(900,100,100,20);
		lblDesg.setBounds(900,140,100,20);
		lblQualification.setBounds(900,180,100,20);
		lblDOJ.setBounds(900,220,100,20);
		lblChild.setBounds(500,300,100,20);
		lblAnnualSalary.setBounds(900,260,100,20);

		txtId.setBounds(200,100,150,20);
		txtName.setBounds(200,140,150,20);
		txtFName.setBounds(200,180,150,20);
		txtDOB.setBounds(200,220,150,20);
		
		btnDelete.setBounds(500,600,100,30);		
		btnCancel.setBounds(700,600,100,30);		

		cbMale.setBounds(200,260,50,20);
		cbFemale.setBounds(250,260,100,20);

		cbMarried.setBounds(600,260,60,20);
		cbUnmarried.setBounds(660,260,75,20);

		txtAddress.setBounds(200,300,150,40);
		txtCountry.setBounds(200,360,150,20);
		txtState.setBounds(200,400,150,20);
		txtCity.setBounds(200,440,150,20);
		txtDeptId.setBounds(1000,100,150,20);
		txtDesi.setBounds(1000,140,150,20);

		txtPincode.setBounds(200,480,150,20);
		
		txtContactNo1.setBounds(600,100,150,20);
		txtContactNo2.setBounds(600,140,150,20);
		txtEmail1.setBounds(600,180,150,20);
		txtEmail2.setBounds(600,220,150,20);

				
		txtQualification.setBounds(1000,180,150,20);
		txtDOJ.setBounds(1000,220,150,20);
		txtChild.setBounds(600,300,150,20);
		txtChild.setEnabled(false);
		txtAnnualSalary.setBounds(1000,260,150,20);
		

		cbMarried.addItemListener(this);
		cbUnmarried.addItemListener(this);

		btnCancel.addActionListener(this);
		btnDelete.addActionListener(this);
	}
	void setValue()
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			System.out.println("Driver is Loaded");
		
			String url="jdbc:odbc:NayanDesale";
			con=DriverManager.getConnection(url,"","");
			System.out.println("Connction is created");

			stmt=con.createStatement();
			System.out.println("Statement Object is Craeted");
			String query="Select * from EmplyeeDetails Where EmployeeId="+empId;
			rs=stmt.executeQuery(query);
			System.out.println("Query is Executed");
			if(!rs.next())
			{
				JOptionPane.showMessageDialog(this,"Employee not found ??","Search Error !!!",JOptionPane.OK_CANCEL_OPTION);
				this.setVisible(false);
			}
			else
			{
				txtId.setText(rs.getInt(1)+"");
				txtName.setText(rs.getString(2));
				txtFName.setText(rs.getString(3));
				txtDOB.setText(rs.getString(4));

				String str1,str2;
				str1=cbMale.getLabel();
				str2=rs.getString(5);
				if(str1.equals(str2))
					cbGroup1.setSelectedCheckbox(cbMale);	
				else
					cbGroup2.setSelectedCheckbox(cbFemale);
				
				txtAddress.setText(rs.getString(6));
				txtCountry.setText(rs.getString(7));
				txtState.setText(rs.getString(8));
				txtCity.setText(rs.getString(9));
				txtPincode.setText(rs.getInt(10)+"");
				txtContactNo1.setText(rs.getString(11)+"");
				txtContactNo2.setText(rs.getString(12)+"");
				txtEmail1.setText(rs.getString(13));
				txtEmail2.setText(rs.getString(14));
				txtDeptId.setText(rs.getInt(15)+"");
				txtDesi.setText(rs.getString(16));
				txtQualification.setText(rs.getString(17));
				txtDOJ.setText(rs.getString(18));

				String s1,s2;
				s1=cbMarried.getLabel();
				s2=rs.getString(19);
				if(s1.equals(s2))
					cbGroup2.setSelectedCheckbox(cbMarried);
				else
					cbGroup1.setSelectedCheckbox(cbUnmarried);
				
				txtChild.setText(rs.getInt(20)+"");
			}
			try
			{
				con.close();
				System.out.println("Connection Closed");
				stmt.close();
			}
			catch(Exception e1){}
		}
		catch(Exception e)
		{
			try
			{
				con.close();
				System.out.println(e.getMessage());
				stmt.close();
			}
			catch(Exception ex){}
		}
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==btnCancel)
		{
			setVisible(false);
		}
		else if(e.getSource()==btnDelete)
		{
			int res=JOptionPane.showConfirmDialog(this,"Move to recycle bin or not ???","Delete",JOptionPane.YES_NO_OPTION);

			if(res==JOptionPane.YES_OPTION)
			{
				try
				{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			
					String url="jdbc:odbc:NayanDesale";
					con=DriverManager.getConnection(url,"","");

					stmt=con.createStatement();
					System.out.println("Statement Object is Craeted");
					String query="Update EmplyeeDetails set Bin=True Where EmployeeId="+empId;
					stmt.executeUpdate(query);

					con.close();
				}
				catch(Exception exc){}
			}
			else
			{
				try
				{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			
					String url="jdbc:odbc:NayanDesale";
					con=DriverManager.getConnection(url,"","");

					stmt=con.createStatement();
					System.out.println("Statement Object is Craeted");
					String query="Delete from EmplyeeDetails Where EmployeeId="+empId;
					stmt.executeUpdate(query);

					con.close();
				}
				catch(Exception exc){}
			}
		}
	}
	public void itemStateChanged(ItemEvent e)
	{
		if(cbGroup2.getSelectedCheckbox()==cbMarried)
		{
			txtChild.setEnabled(true);	
		}
		else	
		{
			txtChild.setText("");
			txtChild.setEnabled(false);
		}
	}
}

class UpdateEmployee1 extends JFrame implements ActionListener,ItemListener
{

	Connection con,conSalaryDetails;
	Statement stmt,querySalaryDetails;
	ResultSet rs;

	boolean bin;

	JLabel lblId,lblName,lblFName,lblDOB,lblGender,lblAddress,lblCountry,lblState,lblCity,lblPincode,lblContactNo1,lblContactNo2,lblEmail1,lblDeptId,lblDesg,lblQualification,lblDOJ,lblEmail2,lblMaritalStatus,lblChild,lblAnnualSalary;
	JTextField txtId,txtName,txtFName,txtDOB,txtPincode,txtContactNo1,txtContactNo2,txtEmail1,txtEmail2,txtQualification,txtDOJ,txtChild,txtAnnualSalary,txtCountry;
	Choice chCountry,chState,chCity,chDesg,chDeptId;
	Checkbox cbMale,cbFemale,cbMarried,cbUnmarried;
	CheckboxGroup cbGroup1,cbGroup2;
	JTextArea txtAddress;
	JLabel lblHeading=new JLabel("Update Employee");

	JButton btnUpdate,btnCancel;


	CardLayout cl=new CardLayout();

	JPanel p1,p2,mainPanel;

	

	String str=JOptionPane.showInputDialog("Enter the Employee Id");
	int empId=Integer.parseInt(str);

	UpdateEmployee1()
	{
		super("Update Employee");

		setLayout(null);

		setExtendedState(JFrame.MAXIMIZED_BOTH);

		mainPanel=new JPanel();
		add(mainPanel);
		mainPanel.setBounds(0,0,1366,768);
		mainPanel.setLayout(cl);
		mainPanel.setBackground(new Color(223,223,223));
		
		p1=new JPanel();
		p1.setBackground(new Color(223,223,223));

		p2=new JPanel();
		p2.setBackground(new Color(223,223,223));
		
		mainPanel.add(p1,"Panel1");
		mainPanel.add(p2,"Panel2");

		p1.setLayout(null);
		p2.setLayout(null);


		lblId=new JLabel("Employee ID");
		lblName=new JLabel("Name");
		lblFName=new JLabel("Father's Name");
		lblDOB=new JLabel("Date of Birth");
		lblGender=new JLabel("Gender");
		lblAddress=new JLabel("Address");
		lblCountry=new JLabel("Country");
		lblState=new JLabel("State");
		lblCity=new JLabel("City");
		lblPincode=new JLabel("Pincode");
		lblContactNo1=new JLabel("Contact No 1");
		lblContactNo2=new JLabel("Contact No 2");
		lblEmail1=new JLabel("Email1");
		lblEmail2=new JLabel("Email2");
		lblMaritalStatus=new JLabel("Marital Status");
		lblDesg=new JLabel("Designation");
		lblQualification=new JLabel("Qualification");
		lblDOJ=new JLabel("Date of Joining");
		lblDeptId=new JLabel("Department ID");
		lblChild=new JLabel("Children");
		lblAnnualSalary=new JLabel("AnnualBasicSalary");

		txtId=new JTextField(20);
		txtName=new JTextField(20);
		txtFName=new JTextField(20);
		txtDOB=new JTextField(20);
		txtPincode=new JTextField(20);
		txtContactNo1=new JTextField(20);
		txtContactNo2=new JTextField(20);
		txtEmail1=new JTextField(20);
		txtEmail2=new JTextField(20);
		txtQualification=new JTextField(20);
		txtDOJ=new JTextField(20);
		txtChild=new JTextField(20);
		txtAnnualSalary=new JTextField(20);

		cbGroup1=new CheckboxGroup();
		cbMale=new Checkbox("Male",false,cbGroup1);
		cbFemale=new Checkbox("Female",false,cbGroup1);

		cbGroup2=new CheckboxGroup();
		cbMarried=new Checkbox("Married",false,cbGroup2);
		cbUnmarried=new Checkbox("Unmarried",false,cbGroup2);
		

		chCountry=new Choice();
		chState=new Choice();
		chCity=new Choice();
		chDesg=new Choice();
		chDeptId=new Choice();

		txtAddress=new JTextArea(5,20);

		btnUpdate=new JButton("Update");
		btnCancel=new JButton("Cancel");

		p1.add(lblId);
		p1.add(lblName);
		p1.add(lblFName);
		p1.add(lblDOB);
		p1.add(lblGender);
		p1.add(lblAddress);
		p1.add(lblCountry);
		p1.add(lblState);
		p1.add(lblCity);
		p1.add(lblPincode);
		p1.add(lblContactNo1);
		p1.add(lblContactNo2);
		p1.add(lblEmail1);
		p1.add(lblEmail2);
		p1.add(lblMaritalStatus);
		p1.add(lblDesg);
		p1.add(lblQualification);
		p1.add(lblDOJ);
		p1.add(lblDeptId);
		p1.add(lblChild);
		p1.add(lblAnnualSalary);
		p1.add(txtAnnualSalary);

		p1.add(lblHeading);
		lblHeading.setFont(new Font("Times New Roman",Font.BOLD,30));
		lblHeading.setBounds(500,30,300,30);

		p1.add(txtId);
		p1.add(txtName);
		p1.add(txtFName);
		p1.add(txtDOB);
		p1.add(txtPincode);
		p1.add(txtContactNo1);
		p1.add(txtContactNo2);
		p1.add(txtEmail1);
		p1.add(txtEmail2);
		p1.add(txtQualification);
		p1.add(txtDOJ);
		p1.add(txtChild);
		p1.add(txtAnnualSalary);
	
		p1.add(cbMale);
		p1.add(cbFemale);
		p1.add(cbMarried);
		p1.add(cbUnmarried);
		
		p1.add(btnUpdate);
		p1.add(btnCancel);

		p1.add(chCountry);
		chCountry.add("India");
		chCountry.add("USA");
		chCountry.add("UK");

		p1.add(chState);
		chState.add("Maharashtra");
		chState.add("Tamilnadu");
		chState.add("Rajastan");
		chState.add("M.P");

		p1.add(chCity);
		chCity.add("Nasik");
		chCity.add("Mumbai");
		chCity.add("Dhule");

		p1.add(chDesg);
		chDesg.add("Manager");
		chDesg.add("Employee");
		p1.add(txtAddress);

		p1.add(chDeptId);
		chDeptId.add("11");
		chDeptId.add("12");
		chDeptId.add("13");
		chDeptId.add("14");
		chDeptId.add("15");
		chDeptId.add("16");
		chDeptId.add("17");

		lblId.setBounds(100,100,100,20);
		lblName.setBounds(100,140,100,20);
		lblFName.setBounds(100,180,100,20);
		lblDOB.setBounds(100,220,100,20);
		lblGender.setBounds(100,260,100,20);
		lblAddress.setBounds(100,300,100,20);
		lblCountry.setBounds(100,360,100,20);
		lblState.setBounds(100,400,100,20);
		lblCity.setBounds(100,440,100,20);
		lblPincode.setBounds(100,480,100,20);

		lblContactNo1.setBounds(500,100,100,20);
		lblContactNo2.setBounds(500,140,100,20);
		lblEmail1.setBounds(500,180,100,20);
		lblEmail2.setBounds(500,220,100,20);
		lblMaritalStatus.setBounds(500,260,100,20);
		
		lblDeptId.setBounds(900,100,100,20);
		lblDesg.setBounds(900,140,100,20);
		lblQualification.setBounds(900,180,100,20);
		lblDOJ.setBounds(900,220,100,20);
		lblChild.setBounds(500,300,100,20);
		lblAnnualSalary.setBounds(900,260,100,20);

		txtId.setBounds(200,100,150,20);
		txtName.setBounds(200,140,150,20);
		txtFName.setBounds(200,180,150,20);
		txtDOB.setBounds(200,220,150,20);
		
		cbMale.setBounds(200,260,50,20);
		cbFemale.setBounds(250,260,100,20);

		cbMarried.setBounds(600,260,60,20);
		cbUnmarried.setBounds(660,260,75,20);

		txtAddress.setBounds(200,300,150,40);

		chCountry.setBounds(200,360,150,20);
		chState.setBounds(200,400,150,20);
		chCity.setBounds(200,440,150,20);

		txtPincode.setBounds(200,480,150,20);
		
		txtContactNo1.setBounds(600,100,150,20);
		txtContactNo2.setBounds(600,140,150,20);
		txtEmail1.setBounds(600,180,150,20);
		txtEmail2.setBounds(600,220,150,20);

		chDeptId.setBounds(1000,100,150,20);
		
		chDesg.setBounds(1000,140,150,20);

		txtQualification.setBounds(1000,180,150,20);
		txtDOJ.setBounds(1000,220,150,20);
		txtChild.setBounds(600,300,150,20);
		txtChild.setEnabled(false);
		txtAnnualSalary.setBounds(1000,260,150,20);

		btnUpdate.setBounds(500,600,100,30);
		btnCancel.setBounds(700,600,100,30);

		cbMarried.addItemListener(this);
		cbUnmarried.addItemListener(this);

		btnUpdate.addActionListener(this);
		btnCancel.addActionListener(this);

	}
	void setValue()
	{
		try
		{
	//		------------------values set Logic-------------------------
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			System.out.println("Driver is Loaded");
			String url="jdbc:odbc:NayanDesale";
			con=DriverManager.getConnection(url,"","");
			System.out.println("Connection is Created");
			stmt=con.createStatement();
			System.out.println("Statement Object is Created");
			String query="Select * from EmplyeeDetails where EmployeeId="+empId;
			rs=stmt.executeQuery(query);	
			if(rs.next()&&(!rs.getBoolean("Bin")))
			{
				txtId.setText(rs.getInt("EmployeeId")+"");	
				txtName.setText(rs.getString("EmployeeName"));
				txtFName.setText(rs.getString("FatherName"));
				txtDOB.setText(rs.getString("DOB"));
				String s=rs.getString("Gender");
				if(s.equals(cbMale.getLabel()))
					cbGroup1.setSelectedCheckbox(cbMale);
				else
					cbGroup1.setSelectedCheckbox(cbFemale);
				txtAddress.setText(rs.getString("Address"));
		
				chCountry.select(rs.getString("Country"));
				chState.select(rs.getString("State"));
				chCity.select(rs.getString("City"));
				txtPincode.setText(rs.getInt("Pincode")+"");
				txtContactNo1.setText(rs.getString("ContactNo1"));
				txtContactNo2.setText(rs.getString("ContactNo2"));
				txtEmail1.setText(rs.getString("Email1"));
				txtEmail2.setText(rs.getString("Email2"));
				
				chDeptId.select(rs.getInt("DeptID")+"");
				chDesg.select(rs.getString("Designation"));
				txtQualification.setText(rs.getString("Qualification"));
				txtDOJ.setText(rs.getString("DOJ"));

				String str=rs.getString("MaritalStatus");
				if(str.equals(cbMarried.getLabel()))
					cbGroup2.setSelectedCheckbox(cbMarried);
				else
					cbGroup2.setSelectedCheckbox(cbUnmarried);

				txtChild.setText(rs.getInt("Children")+"");

				try
				{
					con.close();
					stmt.close();
					rs.close();
				}
				catch(Exception r){}

			//	--------New Connection---------
				String url1="jdbc:odbc:SalaryDetails";
				conSalaryDetails=DriverManager.getConnection(url1,"","");
				System.out.println("Second connection is opened");
				querySalaryDetails=conSalaryDetails.createStatement();
				System.out.println("second statement object is created");
				String q="Select AnnualBasic from SalaryDetails where EmployeeId="+empId;

				rs=querySalaryDetails.executeQuery(q);
			
				rs.next();
				txtAnnualSalary.setText(rs.getFloat("AnnualBasic")+"");

				try
				{
					conSalaryDetails.close();
					querySalaryDetails.close();
					rs.close();
				}
				catch(Exception r){}
				//-----------------values Update Logic------------------
							}
			else
			{
				this.setVisible(false);
				JOptionPane.showMessageDialog((Component)null,"Employee Not Found Please Enter correct Employee Id or it may present in recycle bin","Operation sucessfull",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		catch(Exception e)
		{
			try
			{
				con.close();
				stmt.close();
				rs.close();

				conSalaryDetails.close();
				querySalaryDetails.close();
				rs.close();
			}
			catch(Exception ex){}
		}
	}
	public void itemStateChanged(ItemEvent e)
	{
		if(cbGroup2.getSelectedCheckbox()==cbMarried)
		{
			txtChild.setEnabled(true);	
		}
		else	
		{
			txtChild.setText("");
			txtChild.setEnabled(false);
		}
		
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==btnUpdate)
		{
			try
			{
				String url2="Jdbc:Odbc:NayanDesale";
				con=DriverManager.getConnection(url2,"","");
				System.out.println("Connecion is again created");
			
				String d="Update EmplyeeDetails set EmployeeName=?,FatherName=?,DOB=?,Gender=?,Address=?,Country=?,State=?,City=?,Pincode=?,ContactNo1=?,ContactNo2=?,Email1=?,Email2=?,DeptID=?,Designation=?,Qualification=?,DOJ=?,MaritalStatus=?,Children=? where EmployeeId="+empId;
				PreparedStatement pre=con.prepareStatement(d);
				pre.setString(1,txtName.getText());
				pre.setString(2,txtFName.getText());
				pre.setString(3,txtDOB.getText());
				pre.setString(4,cbGroup1.getSelectedCheckbox().getLabel());
				pre.setString(5,txtAddress.getText());
				pre.setString(6,chCountry.getSelectedItem());
				pre.setString(7,chState.getSelectedItem());
				pre.setString(8,chCity.getSelectedItem());
				pre.setInt(9,Integer.parseInt(txtPincode.getText()));
				pre.setString(10,txtContactNo1.getText());
				pre.setString(11,txtContactNo2.getText());
				pre.setString(12,txtEmail1.getText());
				pre.setString(13,txtEmail2.getText());
				pre.setString(14,chDeptId.getSelectedItem());
				pre.setString(15,chDesg.getSelectedItem());
				pre.setString(16,txtQualification.getText());
				pre.setString(17,txtDOJ.getText());
				System.out.println("Hello");
				String s1=(cbGroup2.getSelectedCheckbox().getLabel());
				System.out.println(s1);

			//	----------------Error----------------------

				pre.setString(18,s1);

				if(s1.equals("Married"))
					pre.setInt(19,Integer.parseInt(txtChild.getText()));
				else
					pre.setInt(19,0);

				int a=pre.executeUpdate();
				System.out.println("Update query");
				if(a>0)
					JOptionPane.showMessageDialog((Component)null,"data Updated","Operation sucessfull",JOptionPane.INFORMATION_MESSAGE);
				else
					System.out.println("Error!!");
				con.close();
				pre.close();
				System.out.println("Connection is closed");
				//-------------------------Incentives connection ------------
				Statement stmt1;
				ResultSet result;

				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");			
				String url="Jdbc:Odbc:SalaryDetails";
				String url3="Jdbc:Odbc:Incentives";
				con=DriverManager.getConnection(url3,"","");
				System.out.println("Incentives Connection is created");
				stmt1=con.createStatement();
				System.out.println("Object Created!!!");
				String str=chDesg.getSelectedItem();
				System.out.println(str);
			
				String nquery="Select * from Incentives where Designation='"+chDesg.getSelectedItem()+"'";
				result=stmt1.executeQuery(nquery);
				System.out.println("Query Executed!!!");
				result.next();
				
				float hraPer=result.getFloat("HouseRentAllowance");
				System.out.println("Tested1");
				float daPer=result.getFloat("DearenessAllowance");
				System.out.println("Tested2");
				float ctPer=result.getFloat("CityAllowance");
				System.out.println("Tested3");
				float maAmt=result.getFloat("MedicalAllowance");
				System.out.println("Tested4");
				float eaAmt=result.getFloat("EducationAllowance");	
				System.out.println("Tested5");
				float taAmt=result.getFloat("TravellingAllowance");
				System.out.println("Tested6");
				float EntePer=result.getFloat("EntertainmentAllowance");
				System.out.println("Tested7");
					
				float ptAmt=result.getFloat("ProfessionalTax");
				float pfPer=result.getFloat("ProvidendFund");
				System.out.println("Values are geted!!");
				con.close();
				stmt1.close();
				System.out.println("Incentives connection is closed");
				

				con=DriverManager.getConnection(url,"","");
				String newquery="Update SalaryDetails set AnnualBasic=?,MonthlyBasic=?,MonthlyIncentive=?,ProvidendFund=?,AnnualIncomeTax=?,MonthlyIncomeTax=?,MonthlyDeduction=?,MonthlyGrossSalary=?,PerDayIncome=?,AnnualGrossSalary=?,AnnualIncrement=? where EmployeeId="+empId;
				PreparedStatement p=con.prepareStatement(newquery);
				System.out.println("Object is created");

				float annualbasic=Float.valueOf(txtAnnualSalary.getText());
				float monthlyBasic=annualbasic/12;
				float hrabasic=(hraPer*monthlyBasic)/100;
				float dabasic=(daPer*monthlyBasic)/100;
				float ctbasic=(ctPer*monthlyBasic)/100;				
				float mabasic=maAmt;
				float eabasic=eaAmt;
				float tabasic=taAmt;	
				float entertainmentBasic=(EntePer*monthlyBasic)/100;
				float ptbasic=ptAmt;
				float pfbasic=(pfPer*monthlyBasic)/100;
				float monthlyincentives=hrabasic+dabasic+ctbasic+mabasic+eabasic+tabasic+entertainmentBasic;
				float monthlydeduction=ptbasic+pfbasic;
				float monthlygross=monthlyBasic+monthlyincentives-monthlydeduction;
				float preDayIncome=monthlygross/30;
				float annualGrossSalary=monthlygross*12;
				float annualincrement=2000.0f;
				System.out.println("Second Values are geted!!");

				p.setFloat(1,annualbasic);
				p.setFloat(2,monthlyBasic);
				p.setFloat(3,monthlyincentives);
				p.setFloat(4,pfbasic);
				p.setFloat(5,0.0f);
				p.setFloat(6,0.0f);
				p.setFloat(7,monthlydeduction);
				p.setFloat(8,monthlygross);
				p.setFloat(9,preDayIncome);
				p.setFloat(10,annualGrossSalary);
				p.setFloat(11,annualincrement);
				int r=p.executeUpdate();
				if(r>0)
					JOptionPane.showMessageDialog((Component)null,"Salary Details Updated","Operation sucessfull",JOptionPane.INFORMATION_MESSAGE);
				else
					System.out.println("Error!!");
				con.close();
				p.close();

			}
			catch(Exception q){System.out.println(q.getMessage());}
		}
		if(e.getSource()==btnCancel)
		{
			setVisible(false);
		}
	}
}
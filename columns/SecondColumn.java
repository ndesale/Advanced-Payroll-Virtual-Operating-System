package columns;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;

public class SecondColumn extends Thread implements ActionListener
{
	JButton l1,l2,l3,l4;
	JPanel p;
	ImageIcon i;
	JFrame jf;
	Incentive ic;
	Deduction dd;
	Bonus bn;
	DemoBasic db;

	public SecondColumn(JFrame f,JPanel s)
	{
		p=s;
		jf=f;

		l1=new JButton(new ImageIcon("icons\\Salary\\basicsalary.jpg"));
		l2=new JButton(new ImageIcon("icons\\Salary\\incentives.jpg"));
		l3=new JButton(new ImageIcon("icons\\Salary\\deduction.jpg"));
		l4=new JButton(new ImageIcon("icons\\Salary\\bonus.jpg"));

		l1.setRolloverIcon(new ImageIcon("icons\\Salary\\basicsalaryshine.jpg"));
		l2.setRolloverIcon(new ImageIcon("icons\\Salary\\incentivesshine.jpg"));
		l3.setRolloverIcon(new ImageIcon("icons\\Salary\\deductionshine.jpg"));
		l4.setRolloverIcon(new ImageIcon("icons\\Salary\\bonusshine.jpg"));

		l1.setToolTipText("Basic Salary");
		l2.setToolTipText("Incentives");
		l3.setToolTipText("Deduction");
		l4.setToolTipText("Bonus");
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
		l1.setBounds(250,80,100,69);
		l2.setBounds(250,190,100,69);
		l3.setBounds(250,300,100,69);

		for(int s=0;s<=6;s++)
		{
			try
			{
				sleep(20);
			}
			catch(Exception e){}
		}

		l4.setBounds(250,410,100,69);
				
		l1.addActionListener(this);
		l2.addActionListener(this);
		l3.addActionListener(this);
		l4.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==l1)
		{
			db=new DemoBasic();
			db.show();
		}
		else if(e.getSource()==l2)
		{
			int id=Integer.parseInt(JOptionPane.showInputDialog("Enter he Employee ID"));

			ic=new Incentive();

			ic.setVisible(true);
			ic.setExtendedState(JFrame.MAXIMIZED_BOTH);
			ic.setup(id);
		}
		else if(e.getSource()==l3)
		{
			int id=Integer.parseInt(JOptionPane.showInputDialog("Enter he Employee ID"));

			dd=new Deduction(id);

			dd.setVisible(true);
			dd.setExtendedState(JFrame.MAXIMIZED_BOTH);
		}
		else if(e.getSource()==l4)
		{
			bn=new Bonus();
			bn.setVisible(true);
		}
	}
}
class DemoBasic extends JFrame implements ItemListener
{
	JLabel l1,lblId,lblName,lblDesg,lblDept,lblDOJ,lblSalType,lblSalAmt;
	JTextField txtId,txtName,txtDesg,txtDept,txtDOJ,txtSalAmt;
	String names[]={"Select Any","Monthly","Daily","Yearly"};
	JComboBox ch1;
	Container c;
	Connection con,conSalary;
	Statement stmt,stmt1;
	ResultSet rs,rs1;
	String url,query,url1,q;
	int id;
	float annualSalary,monthlySalary,PerDay;
	DemoBasic()
	{
		super("Basic Salary Details");
		setBounds(400,150,500,500);

		id=Integer.parseInt(JOptionPane.showInputDialog("Enter the Employee Id"));
		c=getContentPane();
		c.setLayout(null);
	
		l1=new JLabel("Basic Salary");
		l1.setBounds(180,30,150,30);
		l1.setFont(new Font("Times New Roman",Font.BOLD,26));
		c.add(l1);

		lblId=new JLabel("EmployeeId");
		lblName=new JLabel("EmployeeName");
		lblDesg=new JLabel("Designation");
		lblDept=new JLabel("Department");
		lblDOJ=new JLabel("DOJ");
		lblSalType=new JLabel("Salary Type");
		lblSalAmt=new JLabel("Salry Ammount");

		lblId.setBounds(50,80,100,20);
		lblName.setBounds(50,120,100,20);
		lblDesg.setBounds(50,160,100,20);
		lblDept.setBounds(50,200,100,20);
		lblDOJ.setBounds(50,240,100,20);
		lblSalType.setBounds(50,320,100,20);
		lblSalAmt.setBounds(50,360,100,20);
	
		c.add(lblId);
		c.add(lblName);
		c.add(lblDesg);
		c.add(lblDept);
		c.add(lblDOJ);
		c.add(lblSalType);
		c.add(lblSalAmt);

		txtId=new JTextField(20);
		txtName=new JTextField(20);
		txtDesg=new JTextField(20);
		txtDept=new JTextField(20);
		txtDOJ=new JTextField(20);
		ch1=new JComboBox(names);
		txtSalAmt=new JTextField(20);
			
		txtId.setBounds(150,80,100,20);
		txtName.setBounds(150,120,100,20);
		txtDesg.setBounds(150,160,100,20);
		txtDept.setBounds(150,200,100,20);
		txtDOJ.setBounds(150,240,100,20);
		ch1.setBounds(150,320,100,20);
		txtSalAmt.setBounds(150,360,100,20);

		c.add(txtId);
		c.add(txtName);
		c.add(txtDesg);
		c.add(txtDept);
		c.add(txtDOJ);
		c.add(ch1);
		c.add(txtSalAmt);

		ch1.addItemListener(this);
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");	
			url="Jdbc:Odbc:NayanDesale";
			con=DriverManager.getConnection(url,"","");
			stmt=con.createStatement();
			query="Select EmployeeId,EmployeeName,Designation,DeptID,DOJ from EmplyeeDetails where EmployeeId="+id;
			rs=stmt.executeQuery(query);
			System.out.println("Query is Executed");
			if(rs.next())
			{
				txtId.setText(rs.getInt("EmployeeId")+"");
				txtName.setText(rs.getString("EmployeeName"));
				txtDesg.setText(rs.getString("Designation"));
				txtDept.setText(rs.getInt("DeptID")+"");
				txtDOJ.setText(rs.getString("DOJ"));
			}
			else
			{
				JOptionPane.showMessageDialog((Component)null,"Employee Id not Found","Error",JOptionPane.ERROR_MESSAGE);
				this.setVisible(false);
			}
			con.close();	
			stmt.close();
			
			ch1.setSelectedItem("Yearly");
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");	
			url1="Jdbc:Odbc:SalaryDetails";
			conSalary=DriverManager.getConnection(url1,"","");
			stmt1=conSalary.createStatement();
			q="Select AnnualBasic,MonthlyBasic,PerDayIncome from SalaryDetails where EmployeeId="+id;
			rs1=stmt1.executeQuery(q);
			if(rs1.next())
			{
				annualSalary=rs1.getFloat("AnnualBasic");
				monthlySalary=rs1.getFloat("MonthlyBasic");
				PerDay=rs1.getFloat("PerDayIncome");
				txtSalAmt.setText(annualSalary+"");
			}	
			conSalary.close();
			stmt1.close();
			
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
	public void itemStateChanged(ItemEvent i)
	{
		if(ch1.getSelectedItem().equals("Select Any"))
			txtSalAmt.setText("");
		else if(ch1.getSelectedItem().equals("Yearly"))
			txtSalAmt.setText(annualSalary+"");
		else if(ch1.getSelectedItem().equals("Monthly"))
			txtSalAmt.setText(monthlySalary+"");
		else
			txtSalAmt.setText(PerDay+"");
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

	void setup(int id)
	{
		setLayout(null);
		
		p1=new JPanel();
		add(p1);

		p1.setBounds(0,0,1366,768);
		p1.setLayout(null);
		p1.setBackground(new Color(150,150,150));
		p1.setVisible(true);		

		lblId=new JLabel("Employee ID");
		lblName=new JLabel("Name");
		lblDesg=new JLabel("Designation");
		lblDept=new JLabel("Department");
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
		setLayout(null);
		
		p1=new JPanel();
		add(p1);
		p1.setBounds(0,0,1366,768);
		p1.setLayout(null);
		p1.setBackground(new Color(150,150,150));
		p1.setVisible(true);		

		lblId=new JLabel("Employee ID");
		lblName=new JLabel("Name");
		lblDesg=new JLabel("Designation");
		lblDept=new JLabel("Department");
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
class Bonus extends JFrame implements ActionListener
{
	JLabel lblHead,lblDesg,lblBonusType,lblBonusAmt,lblDate;
	JTextField txtBonusAmt,txtDate;
	JButton btnApply;
	Checkbox chEmployee,chManager;
	JComboBox choType;
	Container c;
	String name[]={"Diwali Bonus","Christmas Bonus"};
	Connection con;
	PreparedStatement pstmt;
	String url,query;
	int r;
	Bonus()
	{
		c=getContentPane();
		c.setLayout(null);	
		
		lblHead=new JLabel("Bonus");		
		lblHead.setFont(new Font("Times New Roman",Font.BOLD,26));

		lblDesg=new JLabel("Designation");
		lblDesg.setFont(new Font("Times New Roman",Font.BOLD,20));
		lblBonusType=new JLabel("Bonus Type");
		lblBonusType.setFont(new Font("Times New Roman",Font.BOLD,20));
		lblBonusAmt=new JLabel("Bonus Ammount");
		lblBonusAmt.setFont(new Font("Times New Roman",Font.BOLD,20));
		lblDate=new JLabel("Date");
		lblDate.setFont(new Font("Times New Roman",Font.BOLD,20));


		chEmployee=new Checkbox("Employee",false);
		chEmployee.setFont(new Font("Times New Roman",Font.BOLD,17));
		chManager=new Checkbox("Manager",false);
		chManager.setFont(new Font("Times New Roman",Font.BOLD,17));

		choType=new JComboBox(name);

		txtBonusAmt=new JTextField(20);

		btnApply=new JButton("Apply");

		txtDate=new JTextField(20);
		
		lblHead.setBounds(180,30,100,20);
		lblDesg.setBounds(50,100,100,20);
		lblBonusType.setBounds(50,150,100,20);
		lblBonusAmt.setBounds(50,200,150,20);
		lblDate.setBounds(50,250,150,20);

		chEmployee.setBounds(200,100,100,20);		
		chManager.setBounds(320,100,150,20);
		
		choType.setBounds(200,150,150,20);
		txtBonusAmt.setBounds(200,200,150,20);
		txtDate.setBounds(200,250,150,20);

		btnApply.setBounds(300,300,100,30);		
	
		add(lblHead);
		add(lblDesg);
		add(lblBonusType);
		add(lblBonusAmt);
		add(chEmployee);
		add(chManager);
		add(choType);
		add(txtBonusAmt);
		add(lblDate);
		add(txtDate);
		add(btnApply);

		btnApply.addActionListener(this);

		setBounds(430,200,450,400);	
		//----------------------Insert Logic------------------

	}
	public void actionPerformed(ActionEvent ex)
	{
		if(ex.getSource()==btnApply)
		{
			try
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				url="Jdbc:Odbc:Bonus";

				con=DriverManager.getConnection(url,"","");
				query="Insert into Bonus values(?,?,?,?)";
				pstmt=con.prepareStatement(query);
			
				if(chEmployee.getState())
					pstmt.setString(1,chEmployee.getLabel());
				else
					pstmt.setString(1,chManager.getLabel());
				pstmt.setString(2,choType.getSelectedItem()+"");
				pstmt.setInt(3,Integer.parseInt(txtBonusAmt.getText()));
				pstmt.setString(4,txtDate.getText());
				r=pstmt.executeUpdate();
				if(r>0)
				{
					System.out.println("Inserted");
					JOptionPane.showMessageDialog((Component)null,"Data is Inserted","Inserted",JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					System.out.println("Not Inserted");
					JOptionPane.showMessageDialog((Component)null,"Data is Inserted","Inserted",JOptionPane.ERROR_MESSAGE);
				}
				con.close();
			}
			
			catch(Exception e)
			{
				System.out.println(e.getMessage());
				try
				{
					con.close();
					pstmt.close();
				}
				catch(Exception eee){}
			}
		}
	}
}
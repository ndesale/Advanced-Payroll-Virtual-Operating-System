import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

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
	


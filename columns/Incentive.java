package columns;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Incentive extends JFrame 
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

	public void setup(int id)
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
	


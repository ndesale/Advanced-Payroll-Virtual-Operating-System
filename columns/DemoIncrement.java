import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

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
	
	public static void main(String args[])
	{
		DemoIncrement des=new DemoIncrement();
		des.setTitle("Increment");
		des.setVisible(true);
		des.setBounds(300,100,500,400);
		des.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		des.setup();
		des.setResizable(false);
	}
}				
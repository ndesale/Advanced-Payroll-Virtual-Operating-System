import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
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
	JLabel lblHeading=new JLabel("Add New Employee");

	JButton btnUpdate,btnCancel;


	CardLayout cl=new CardLayout();

	JPanel p1,p2,mainPanel;

	

	String str=JOptionPane.showInputDialog("Enter the Employee Id");
	int empId=Integer.parseInt(str);

	UpdateEmployee1()
	{
		setLayout(null);

		mainPanel=new JPanel();
		add(mainPanel);
		mainPanel.setBounds(0,0,1366,768);
		mainPanel.setLayout(cl);
		mainPanel.setBackground(new Color(150,150,150));
		
		p1=new JPanel();
		p1.setBackground(new Color(150,150,150));

		p2=new JPanel();
		p2.setBackground(new Color(150,150,150));
		
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
	public static void main(String args[])
	{
		UpdateEmployee1 u=new UpdateEmployee1();
		u.setVisible(true);
		u.setSize(1362,768);
		u.setValue();
		u.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}




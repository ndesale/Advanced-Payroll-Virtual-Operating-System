import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.*;
import java.io.*;

class AddEmployee extends JFrame implements ActionListener,ItemListener
{
	JLabel lblId,lblName,lblFName,lblDOB,lblGender,lblAddress,lblCountry,lblState,lblCity,lblPincode,lblContactNo1,lblContactNo2,lblEmail1,lblDeptId,lblDesg,lblQualification,lblDOJ,lblEmail2,lblMaritalStatus,lblChild,lblAnnualSalary;
	JTextField txtId,txtName,txtFName,txtDOB,txtPincode,txtContactNo1,txtContactNo2,txtEmail1,txtEmail2,txtQualification,txtDOJ,txtChild,txtAnnualSalary;
	Choice chCountry,chState,chCity,chDesg,chDeptId;
	Checkbox cbMale,cbFemale,cbmarried,cbunmarried;
	CheckboxGroup cbGroup,cbGroup2;
	JTextArea txtAddress;
	JLabel lblHeading=new JLabel("Add New Employee");

	JButton btnNext,btnPrevious,btnSave;

	//JButton btnDelete=new JButton("Delete");

	CardLayout cl=new CardLayout();

	JPanel p1,p2,mainPanel;

	Connection con,conIncentives,conSalaryDetails;
	PreparedStatement query,querySalaryDetails;
	Statement stmtIncentives;
	ResultSet rsIncentives;

	float hraBasic,daBasic,caBasic,ma,ta,ea,entertainmentBasic,pt;

	FileOutputStream fos;
	ObjectOutputStream oos;
	FileInputStream fis;
	ObjectInputStream ois;

	SerializableVector v=new SerializableVector();

	AddEmployee()
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

		//btnDelete.setBounds(550,600,100,30);
		//p1.add(btnDelete);

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

		cbGroup=new CheckboxGroup();
		cbMale=new Checkbox("Male",false,cbGroup);
		cbFemale=new Checkbox("Female",false,cbGroup);

		cbGroup2=new CheckboxGroup();
		cbmarried=new Checkbox("Married",false,cbGroup2);
		cbunmarried=new Checkbox("Unmarried",false,cbGroup2);
		

		chCountry=new Choice();
		chState=new Choice();
		chCity=new Choice();
		chDesg=new Choice();
		chDeptId=new Choice();

		txtAddress=new JTextArea(5,20);

		btnNext=new JButton(new ImageIcon("icons\\next.jpg"));
		btnPrevious=new JButton(new ImageIcon("icons\\back.jpg"));
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
		p1.add(cbmarried);
		p1.add(cbunmarried);
		
		p1.add(btnNext);
		p1.add(btnPrevious);
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

		chDeptId.setBounds(1000,100,150,20);
		
		chDesg.setBounds(1000,140,150,20);

		txtQualification.setBounds(1000,180,150,20);
		txtDOJ.setBounds(1000,220,150,20);
		txtChild.setBounds(600,300,150,20);
		txtChild.setEnabled(false);
		txtAnnualSalary.setBounds(1000,260,150,20);
	
		btnPrevious.setBounds(900,600,40,40);
		btnNext.setBounds(940,600,40,40);
		btnSave.setBounds(1020,600,40,40);

		btnPrevious.setEnabled(false);
		btnSave.setEnabled(false);

		btnPrevious.addActionListener(this);
		btnNext.addActionListener(this);
		btnSave.addActionListener(this);

		cbmarried.addItemListener(this);
		cbunmarried.addItemListener(this);
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
		if(e.getSource()==btnNext)
		{
			cl.show(mainPanel,"Panel2");

			p2.add(btnNext);
			p2.add(btnPrevious);		
			p2.add(btnSave);

			btnPrevious.setEnabled(true);
			btnNext.setEnabled(false);
			btnSave.setEnabled(true);
		}
		if(e.getSource()==btnPrevious)
		{
			cl.show(mainPanel,"Panel1");

			p1.add(btnNext);
			p1.add(btnPrevious);
			p1.add(btnSave);	

			btnPrevious.setEnabled(false);
			btnNext.setEnabled(true);
			btnSave.setEnabled(false);
		}
		if(e.getSource()==btnSave)
		{
			setup();
		}
	}

	void setup()
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

			String url1="Jdbc:Odbc:NayanDesale";

			con=DriverManager.getConnection(url1,"","");
			query=con.prepareStatement("Insert Into EmplyeeDetails values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				
			query.setInt(1,Integer.parseInt(txtId.getText()));
			query.setString(2,txtName.getText());
			query.setString(3,txtFName.getText());
			query.setString(4,txtDOB.getText());
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
			query.setInt(15,Integer.parseInt(chDeptId.getSelectedItem()));
			query.setString(16,chDesg.getSelectedItem());
			query.setString(17,txtQualification.getText());
			query.setString(18,txtDOJ.getText());
			query.setString(19,(cbGroup2.getSelectedCheckbox().getLabel()));

			if(txtChild.getText().equals(""))
			query.setInt(20,0);
			else
			query.setInt(20,Integer.parseInt(txtChild.getText()));

			int res=query.executeUpdate();

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

			rsIncentives=stmtIncentives.executeQuery("Select * from Incentives where Designation='"+chDesg.getSelectedItem()+"'");

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


			conSalaryDetails=DriverManager.getConnection(url3,"","");
			querySalaryDetails=conSalaryDetails.prepareStatement("Insert into SalaryDetails values(?,?,?,?,?,?,?,?,?,?,?,?)");

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
			
			System.out.println(e.getMessage());
		}
	}
}
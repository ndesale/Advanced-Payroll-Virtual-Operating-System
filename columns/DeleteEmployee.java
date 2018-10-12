import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
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
	String str=JOptionPane.showInputDialog("Enter the Employee Id");
	int empId=Integer.parseInt(str);
	void setup()
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
			String query="Select * from EmplyeeDetails Where EmployeeId=empId";
			rs=stmt.executeQuery(query);
			System.out.println("Query is Executed");
			while(rs.next())
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
				txtContactNo1.setText(rs.getInt(11)+"");
				txtContactNo2.setText(rs.getInt(12)+"");
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

	public static void main(String args[])
	{
		DeleteEmployee d = new DeleteEmployee();
		d.setVisible(true);
		d.setup();
		d.setValue();
		d.setExtendedState(JFrame.MAXIMIZED_BOTH);
		d.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
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
		setBounds(400,150,500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

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
	public static void main(String args[])
	{
		DemoBasic d=new DemoBasic();
		d.setVisible(true);		
		
	}
}
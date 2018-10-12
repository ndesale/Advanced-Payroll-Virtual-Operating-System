import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

class Incentives extends JFrame
{
	JLabel lblId,lblName,lblDesg,lblDeptName,lblDOJ;
	JTextField txtId,txtName,txtDesg,txtDeptName,txtDOJ;
	
	JLabel lblHRA,lblDA,lblTA,lblCA,lblMA,lblEA,lblEnA;
	JTextField txtHRAPer,txtDAPer,txtTAPer,txtCAPer,txtMAPer,txtEAPer,txtEnAPer;
	JTextField txtHRA,txtDA,txtTA,txtCA,txtMA,txtEA,txtEnA;

	Connection con;
	Statement stmt;
	ResultSet rs;

	Incentives(int id)
	{
		Container c=getContentPane();

		c.setLayout(null);

		c.setBackground(new Color(120,120,120));

		lblId=new JLabel("Employee ID");
		lblName=new JLabel("Employee Name");
		lblDesg=new JLabel("Designation");
		lblDeptName=new JLabel("Department");
		lblDOJ=new JLabel("DOJ");

		lblHRA=new JLabel("HRA");
		lblDA=new JLabel("DA");
		lblTA=new JLabel("TA");
		lblCA=new JLabel("CA");
		lblMA=new JLabel("MA");
		lblEA=new JLabel("EA");
		lblEnA=new JLabel("Entertainment Allowance");

		txtId=new JTextField(20);
		txtName=new JTextField(20);
		txtDesg=new JTextField(20);
		txtDeptName=new JTextField(20);
		txtDOJ=new JTextField(20);
	

		txtHRA=new JTextField(20);
		txtDA=new JTextField(20);
		txtTA=new JTextField(20);
		txtCA=new JTextField(20);
		txtMA=new JTextField(20);
		txtEA=new JTextField(20);
		txtEnA=new JTextField(20);

		txtHRAPer=new JTextField(20);
		txtDAPer=new JTextField(20);
		txtTAPer=new JTextField(20);
		txtCAPer=new JTextField(20);
		txtMAPer=new JTextField(20);
		txtEAPer=new JTextField(20);
		txtEnAPer=new JTextField(20);

		c.add(lblId);
		c.add(lblName);
		c.add(lblDesg);
		c.add(lblDeptName);
		c.add(lblDOJ);
			
		c.add(txtId);
		c.add(txtName);
		c.add(txtDesg);
		c.add(txtDeptName);
		c.add(txtDOJ);

		c.add(lblHRA);		

		c.add(txtHRAPer);
		c.add(txtDAPer);
		c.add(txtTAPer);
		c.add(txtCAPer);
		c.add(txtMAPer);
		c.add(txtEAPer);
		c.add(txtEnAPer);


		lblId.setBounds(50,100,100,20);
		lblName.setBounds(50,140,100,20);
		lblDesg.setBounds(50,180,100,20);
		lblDeptName.setBounds(50,220,100,20);
		lblDOJ.setBounds(50,260,100,20);

		txtId.setBounds(150,100,150,20);
		txtName.setBounds(150,140,150,20);
		txtDesg.setBounds(150,180,150,20);
		txtDeptName.setBounds(150,220,150,20);
		txtDOJ.setBounds(150,260,150,20);

		lblHRA.setBounds(500,100,150,20);
		lblDA.setBounds(500,140,150,20);
		lblCA.setBounds(500,180,150,20);
		lblMA.setBounds(500,220,150,20);
		lblEA.setBounds(500,260,150,20);
		lblEnA.setBounds(500,300,150,20);


		txtHRAPer.setBounds(650,100,150,20);
		txtDAPer.setBounds(650,140,150,20);
		txtCAPer.setBounds(650,180,150,20);
		txtMAPer.setBounds(650,220,150,20);
		txtEAPer.setBounds(650,260,150,20);
		txtEnAPer.setBounds(650,300,150,20);

		
	}
	public static void main(String args[])
	{
		Incentives s=new Incentives(23);

		s.setExtendedState(MAXIMIZED_BOTH);
		s.setVisible(true);
	}
}
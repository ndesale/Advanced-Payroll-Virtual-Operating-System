import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class DemoSetting extends JFrame implements ActionListener
{
	JPanel p1,p2;
	JTabbedPane jtp;
	JLabel lblhra,lblda,lblca,lblma,lblea,lblta,lblenterAll,lblpt,lblpf,lblUname,lblPasswd,p2lblUname,p2lblPasswd,p2lblhra,p2lblda,p2lblca,p2lblma,p2lblea,p2lblta,p2lblenterAll,p2lblpt,p2lblpf;
	JTextField txthra,txtda,txtca,txtma,txtea,txtta,txtenterAll,txtpt,txtpf,txtUname,p2txthra,p2txtda,p2txtca,p2txtma,p2txtea,p2txtta,p2txtenterAll,p2txtpt,p2txtpf,p2txtUname;
	JPasswordField txtPasswd,p2txtPasswd;
	JButton btnUpgrade,btnUpdate,btnOk,p2btnUpgrade,p2btnUpdate,p2btnOk;
	JFrame f,f1;
	Connection con;
	Statement stmt;
	PreparedStatement pstmt;
	String url,query;
	ResultSet rs;
	DemoSetting()
	{
		Container c;
		c=this.getContentPane();
		p1=new JPanel();
		p1.setLayout(null);
		lblhra=new JLabel("HouseRentAllowance");
		lblda=new JLabel("DearenessAllowance");
		lblca=new JLabel("CityAllowance");
		lblma=new JLabel("MedicalAllowance");
		lblea=new JLabel("EducationAllowance");	
		lblta=new JLabel("TravellingAllowance");
		lblenterAll=new JLabel("EntertainmentAllowance");
		lblpt=new JLabel("ProfessionalTax");
		lblpf=new JLabel("ProvidendFund");

		lblhra.setBounds(30,50,130,20);
		lblda.setBounds(30,90,130,20);
		lblca.setBounds(30,130,130,20);
		lblma.setBounds(30,170,130,20);
		lblea.setBounds(30,210,130,20);
		lblta.setBounds(30,250,130,20);
		lblenterAll.setBounds(30,290,140,20);
		lblpt.setBounds(30,330,120,20);
		lblpf.setBounds(30,370,120,20);
	
		p1.add(lblhra);
		p1.add(lblda);
		p1.add(lblca);
		p1.add(lblma);
		p1.add(lblea);
		p1.add(lblta);
		p1.add(lblenterAll);
		p1.add(lblpt);
		p1.add(lblpf);

		txthra=new JTextField(20);
		txtda=new JTextField(20);
		txtca=new JTextField(20);
		txtma=new JTextField(20);
		txtea=new JTextField(20);
		txtta=new JTextField(20);
		txtenterAll=new JTextField(20);
		txtpt=new JTextField(20);
		txtpf=new JTextField(20);
	
		txthra.setBounds(200,50,50,20);	
		txtda.setBounds(200,90,50,20);	
		txtca.setBounds(200,130,50,20);
		txtma.setBounds(200,170,50,20);
		txtea.setBounds(200,210,50,20);
		txtta.setBounds(200,250,50,20);
		txtenterAll.setBounds(200,290,50,20);
		txtpt.setBounds(200,330,50,20);
		txtpf.setBounds(200,370,50,20);
	
		p1.add(txthra);
		p1.add(txtda);
		p1.add(txtca);
		p1.add(txtma);		
		p1.add(txtea);		
		p1.add(txtta);		
		p1.add(txtenterAll);		
		p1.add(txtpt);		
		p1.add(txtpf);		


		btnUpgrade=new JButton("Upgrade");
		btnUpdate=new JButton("Update");

		btnUpgrade.setBounds(50,450,100,30);		
		btnUpdate.setBounds(200,450,100,30);
		btnUpdate.setEnabled(false);

		p1.add(btnUpgrade);
		p1.add(btnUpdate);

		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			url="Jdbc:Odbc:Incentives";
			con=DriverManager.getConnection(url,"","");
			stmt=con.createStatement();
			query="Select * from Incentives where Designation='Employee'";
			rs=stmt.executeQuery(query);

			rs.next();
			txthra.setText(rs.getFloat("HouseRentAllowance")+"");
			txtda.setText(rs.getFloat("DearenessAllowance")+"");
			txtca.setText(rs.getFloat("CityAllowance")+"");
			txtma.setText(rs.getFloat("MedicalAllowance")+"");
			txtea.setText(rs.getFloat("EducationAllowance")+"");
			txtta.setText(rs.getFloat("TravellingAllowance")+"");
			txtenterAll.setText(rs.getFloat("EntertainmentAllowance")+"");
			txtpt.setText(rs.getFloat("ProfessionalTax")+"");
			txtpf.setText(rs.getFloat("ProvidendFund")+"");
			con.close();
			stmt.close();

			txthra.setEditable(false);
			txtda.setEditable(false);
			txtma.setEditable(false);
			txtca.setEditable(false);
			txtea.setEditable(false);
			txtta.setEditable(false);
			txtenterAll.setEditable(false);
			txtpt.setEditable(false);
			txtpf.setEditable(false);
			
		}
		catch(Exception e)
		{
			try
			{
				con.close();	
				stmt.close();
			}		
			catch(Exception ee){}
		}

		
		btnUpgrade.addActionListener(this);
		btnUpdate.addActionListener(this);
	//	-----------------------Panel2 start-------------------------------
		
		p2=new JPanel();
		p2.setLayout(null);

		p2lblhra=new JLabel("HouseRentAllowance");
		p2lblda=new JLabel("DearenessAllowance");
		p2lblca=new JLabel("CityAllowance");
		p2lblma=new JLabel("MedicalAllowance");
		p2lblea=new JLabel("EducationAllowance");	
		p2lblta=new JLabel("TravellingAllowance");
		p2lblenterAll=new JLabel("EntertainmentAllowance");
		p2lblpt=new JLabel("ProfessionalTax");
		p2lblpf=new JLabel("ProvidendFund");


		p2lblhra.setBounds(30,50,130,20);
		p2lblda.setBounds(30,90,130,20);
		p2lblca.setBounds(30,130,130,20);
		p2lblma.setBounds(30,170,130,20);
		p2lblea.setBounds(30,210,130,20);
		p2lblta.setBounds(30,250,130,20);
		p2lblenterAll.setBounds(30,290,140,20);
		p2lblpt.setBounds(30,330,120,20);
		p2lblpf.setBounds(30,370,120,20);


		p2.add(p2lblhra);
		p2.add(p2lblda);
		p2.add(p2lblca);
		p2.add(p2lblma);
		p2.add(p2lblea);
		p2.add(p2lblta);
		p2.add(p2lblenterAll);
		p2.add(p2lblpt);
		p2.add(p2lblpf);

		p2txthra=new JTextField(20);
		p2txtda=new JTextField(20);
		p2txtca=new JTextField(20);
		p2txtma=new JTextField(20);
		p2txtea=new JTextField(20);
		p2txtta=new JTextField(20);
		p2txtenterAll=new JTextField(20);
		p2txtpt=new JTextField(20);
		p2txtpf=new JTextField(20);		

		
		p2txthra.setBounds(200,50,50,20);	
		p2txtda.setBounds(200,90,50,20);	
		p2txtca.setBounds(200,130,50,20);
		p2txtma.setBounds(200,170,50,20);
		p2txtea.setBounds(200,210,50,20);
		p2txtta.setBounds(200,250,50,20);
		p2txtenterAll.setBounds(200,290,50,20);
		p2txtpt.setBounds(200,330,50,20);
		p2txtpf.setBounds(200,370,50,20);		

		
		p2.add(p2txthra);
		p2.add(p2txtda);
		p2.add(p2txtca);
		p2.add(p2txtma);		
		p2.add(p2txtea);		
		p2.add(p2txtta);		
		p2.add(p2txtenterAll);		
		p2.add(p2txtpt);		
		p2.add(p2txtpf);	


		p2btnUpgrade=new JButton("Upgrade");
		p2btnUpdate=new JButton("Update");

		p2btnUpgrade.setBounds(50,450,100,30);		
		p2btnUpdate.setBounds(200,450,100,30);
		p2btnUpdate.setEnabled(false );

		p2.add(p2btnUpgrade);
		p2.add(p2btnUpdate);

		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			url="Jdbc:Odbc:Incentives";
			con=DriverManager.getConnection(url,"","");
			stmt=con.createStatement();
			query="Select * from Incentives where Designation='Manager'";
			rs=stmt.executeQuery(query);

			rs.next();
			p2txthra.setText(rs.getFloat("HouseRentAllowance")+"");
			p2txtda.setText(rs.getFloat("DearenessAllowance")+"");
			p2txtca.setText(rs.getFloat("CityAllowance")+"");
			p2txtma.setText(rs.getFloat("MedicalAllowance")+"");
			p2txtea.setText(rs.getFloat("EducationAllowance")+"");
			p2txtta.setText(rs.getFloat("TravellingAllowance")+"");
			p2txtenterAll.setText(rs.getFloat("EntertainmentAllowance")+"");
			p2txtpt.setText(rs.getFloat("ProfessionalTax")+"");
			p2txtpf.setText(rs.getFloat("ProvidendFund")+"");
			con.close();
			stmt.close();

			p2txthra.setEditable(false);
			p2txtda.setEditable(false);
			p2txtma.setEditable(false);
			p2txtca.setEditable(false);
			p2txtea.setEditable(false);
			p2txtta.setEditable(false);
			p2txtenterAll.setEditable(false);
			p2txtpt.setEditable(false);
			p2txtpf.setEditable(false);
			
		}
		catch(Exception e)
		{
			try
			{
				con.close();	
				stmt.close();
			}		
			catch(Exception ee){}
		}

		p2btnUpgrade.addActionListener(this);
		p2btnUpdate.addActionListener(this);


		jtp=new JTabbedPane();
		jtp.setTabPlacement(JTabbedPane.LEFT);
		jtp.add("Employee Setting",p1);
		jtp.add("Manager Setting",p2);	
		jtp.setFont(new Font("Times New Roman",Font.BOLD,16));
		c.add(jtp);	
	}
		
	public void actionPerformed(ActionEvent e)
	{	
		if(e.getSource()==btnUpgrade)
		{
			
			f=new JFrame("Login");
			f.setVisible(true);
			f.setBounds(500,300,300,200);
			f.setLayout(null);
			lblUname=new JLabel("User Name");
			lblPasswd=new JLabel("Password");
			
			lblUname.setBounds(30,30,100,20);	
			lblPasswd.setBounds(30,80,100,20);
	
			f.add(lblUname);
			f.add(lblPasswd);
	
			txtUname=new JTextField(20);
			txtPasswd=new JPasswordField(20);
			txtPasswd.setEchoChar('X');	

			txtUname.setBounds(130,30,130,20);
			txtPasswd.setBounds(130,80,130,20);
	
			f.add(txtUname);
			f.add(txtPasswd);
	
			btnOk=new JButton("OK");
			btnOk.setBounds(100,130,80,30);
			f.add(btnOk);
			btnOk.addActionListener(this);		
		}
		if(e.getSource()==btnOk)
		{		
			String str1,str2;
			str1=txtUname.getText();
			str2=txtPasswd.getText();	
			if(str1.equals("scott")&&str2.equals("tiger"))
			{
				f.setVisible(false);
				txthra.setEditable(true);
				txtda.setEditable(true);
				txtma.setEditable(true);
				txtca.setEditable(true);
				txtea.setEditable(true);
				txtta.setEditable(true);
				txtenterAll.setEditable(true);
				txtpt.setEditable(true);
				txtpf.setEditable(true);
				btnUpdate.setEnabled(true);
			}
			else
			{
				f.setVisible(false);
				JOptionPane.showMessageDialog((Component)null,"Wrong User Name and Password","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		if(e.getSource()==btnUpdate)
		{
			try
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				url="Jdbc:Odbc:Incentives";
				con=DriverManager.getConnection(url,"","");
				query="Update Incentives set HouseRentAllowance=?,DearenessAllowance=?,CityAllowance=?,MedicalAllowance=?,EducationAllowance=?,TravellingAllowance=?,EntertainmentAllowance=?,ProfessionalTax=?,ProvidendFund=? where Designation='Employee'";
				pstmt=con.prepareStatement(query);
				pstmt.setFloat(1,Float.valueOf(txthra.getText()));
				pstmt.setFloat(2,Float.valueOf(txtda.getText()));
				pstmt.setFloat(3,Float.valueOf(txtca.getText()));
				pstmt.setFloat(4,Float.valueOf(txtma.getText()));
				pstmt.setFloat(5,Float.valueOf(txtea.getText()));
				pstmt.setFloat(6,Float.valueOf(txtta.getText()));
				pstmt.setFloat(7,Float.valueOf(txtenterAll.getText()));
				pstmt.setFloat(8,Float.valueOf(txtpt.getText()));
				pstmt.setFloat(9,Float.valueOf(txtpf.getText()));
				int r=pstmt.executeUpdate();
				if(r>0)	
					JOptionPane.showMessageDialog((Component)null," Parameter sucessfully Updated","Updaed!!",JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog((Component)null,"Parameter Not Updated","Error!!",JOptionPane.ERROR_MESSAGE);
				pstmt.clearParameters();
				con.close();
				pstmt.close();
				
			}
			catch(Exception ex)
			{
				try
				{
					con.close();
					pstmt.close();
				}
				catch(Exception eee){}
			}
		}
		if(e.getSource()==p2btnUpgrade)	
		{
			System.out.println("UpGrade Button pressed");

			f1=new JFrame("Login");
			f1.setVisible(true);
			f1.setBounds(500,300,300,200);
			f1.setLayout(null);
			p2lblUname=new JLabel("User Name");
			p2lblPasswd=new JLabel("Password");
			
			p2lblUname.setBounds(30,30,100,20);	
			p2lblPasswd.setBounds(30,80,100,20);
	
			f1.add(p2lblUname);
			f1.add(p2lblPasswd);
	
			p2txtUname=new JTextField(20);
			p2txtPasswd=new JPasswordField(20);
			p2txtPasswd.setEchoChar('X');	

			p2txtUname.setBounds(130,30,130,20);
			p2txtPasswd.setBounds(130,80,130,20);
	
			f1.add(p2txtUname);
			f1.add(p2txtPasswd);
	
			p2btnOk=new JButton("OK");
			p2btnOk.setBounds(100,130,80,30);
			f1.add(p2btnOk);
			p2btnOk.addActionListener(this);
		}
		if(e.getSource()==p2btnOk)
		{
			System.out.println("OK Button is pressed");
			
			String str1,str2;
			str1=p2txtUname.getText();
			str2=p2txtPasswd.getText();	
			if(str1.equals("system")&&str2.equals("manager"))
			{
				System.out.println("Password is correct");
				f1.setVisible(false);
				p2txthra.setEditable(true);
				p2txtda.setEditable(true);
				p2txtma.setEditable(true);
				p2txtca.setEditable(true);
				p2txtea.setEditable(true);
				p2txtta.setEditable(true);
				p2txtenterAll.setEditable(true);
				p2txtpt.setEditable(true);
				p2txtpf.setEditable(true);
				p2btnUpdate.setEnabled(true);
			}
			else
			{
				f1.setVisible(false);
				JOptionPane.showMessageDialog((Component)null,"Wrong User Name and Password","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		if(e.getSource()==p2btnUpdate)
		{
			System.out.println("Update Button pressed");
			
			try
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				url="Jdbc:Odbc:Incentives";
				con=DriverManager.getConnection(url,"","");
				query="Update Incentives set HouseRentAllowance=?,DearenessAllowance=?,CityAllowance=?,MedicalAllowance=?,EducationAllowance=?,TravellingAllowance=?,EntertainmentAllowance=?,ProfessionalTax=?,ProvidendFund=? where Designation='Manager'";
				pstmt=con.prepareStatement(query);
				pstmt.setFloat(1,Float.valueOf(p2txthra.getText()));
				pstmt.setFloat(2,Float.valueOf(p2txtda.getText()));
				pstmt.setFloat(3,Float.valueOf(p2txtca.getText()));
				pstmt.setFloat(4,Float.valueOf(p2txtma.getText()));
				pstmt.setFloat(5,Float.valueOf(p2txtea.getText()));
				pstmt.setFloat(6,Float.valueOf(p2txtta.getText()));
				pstmt.setFloat(7,Float.valueOf(p2txtenterAll.getText()));
				pstmt.setFloat(8,Float.valueOf(p2txtpt.getText()));
				pstmt.setFloat(9,Float.valueOf(p2txtpf.getText()));
				int r=pstmt.executeUpdate();
				if(r>0)	
					JOptionPane.showMessageDialog((Component)null,"Parameters sucessfully Updated","Updated!!s",JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog((Component)null,"Parameters Not Updated","Error!!",JOptionPane.ERROR_MESSAGE);
				pstmt.clearParameters();
				con.close();
				pstmt.close();
				
			}
			catch(Exception ex)
			{
				try
				{
					con.close();
					pstmt.close();
				}
				catch(Exception eee){}
			}
		}
	}
	public static void main(String args[])
	{
		DemoSetting s=new DemoSetting();
		s.setVisible(true);	
		s.setBounds(400,100,500,600);
		s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

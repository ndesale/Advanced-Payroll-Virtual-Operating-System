import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class DemoSetting extends JFrame implements ActionListener
{
	JPanel p1,p2,p3;
	JTabbedPane jtp;
	JLabel lblhra,lblda,lblca,lblma,lblea,lblta,lblenterAll,lblpt,lblpf,lblUname,lblPasswd,p2lblUname,p2lblPasswd,p2lblhra,p2lblda,p2lblca,p2lblma,p2lblea,p2lblta,p2lblenterAll,p2lblpt,p2lblpf,lblHead,lblUname1,lblPasswd1,lblnewUname,lblnewPasswd;
	JTextField txthra,txtda,txtca,txtma,txtea,txtta,txtenterAll,txtpt,txtpf,txtUname,p2txthra,p2txtda,p2txtca,p2txtma,p2txtea,p2txtta,p2txtenterAll,p2txtpt,p2txtpf,p2txtUname,txtUname1,txtnewUname;
	JPasswordField txtPasswd,p2txtPasswd,txtPasswd1,txtnewPasswd;
	JButton btnUpgrade,btnUpdate,btnOk,p2btnUpgrade,p2btnUpdate,p2btnOk,btnLogSec,btnSalSec,btnLeaveSec,btnIncrementSec,btnIpSec,btnApply,btnApply1,btnApply2,btnApply3,btnApply4;
	JFrame f,f1;
	Connection con;
	Statement stmt;
	PreparedStatement pstmt;
	String url,query,Uname,adminPasswd,salPasswd,leavePasswd,incPasswd,ipPasswd;
	ResultSet rs;
	JDialog d=new JDialog((JFrame)null,"Login Security",true);
	JDialog d1=new JDialog((JFrame)null,"Salary Security",true);
	JDialog d2=new JDialog((JFrame)null,"Leave Security",true);
	JDialog d3=new JDialog((JFrame)null,"Increment Security",true);
	JDialog d4=new JDialog((JFrame)null,"IP security",true);
	Container cc;
	JTextField t1,t2;

	DemoSetting()
	{
		super("System Settings");
		s.setBounds(400,100,500,600);
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
		btnUpdate.setBounds(180,450,100,30);
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

			url="Jdbc:Odbc:Password";
			con=DriverManager.getConnection(url,"","");
			stmt=con.createStatement();
			System.out.println("Connection is created");
			query="Select * from Password";
			rs=stmt.executeQuery(query);
			System.out.println("Password Query is Executed");
			if(rs.next())
			{
				Uname=rs.getString("AdminUName");
				adminPasswd=rs.getString("AdminPassword");
				salPasswd=rs.getString("SalaryPassword");
				leavePasswd=rs.getString("LeavePassword");
				incPasswd=rs.getString("IncrementPassword");
				ipPasswd=rs.getString("IpSetting");
			}
			System.out.println("User name="+Uname);
			System.out.println("Admin password="+adminPasswd);
			System.out.println("Salary password="+salPasswd);
			System.out.println("Leave Password="+leavePasswd);
			System.out.println("Increment Password="+incPasswd);
			System.out.println("IP Password="+ipPasswd);
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
		p2btnUpdate.setBounds(180,450,100,30);
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

		

	//	------------------Panel3 Start-------------------
		
		p3=new JPanel();
		p3.setLayout(null);
	
		btnLogSec=new JButton("Login Security");
		btnSalSec=new JButton("Salary Security");
		btnLeaveSec=new JButton("Leave Security"); 
		btnIncrementSec=new JButton("Increment Security");
		btnIpSec=new JButton("IP Security");

		btnLogSec.setBounds(50,80,150,40);
		btnSalSec.setBounds(50,160,150,40);
		btnLeaveSec.setBounds(50,240,150,40);
		btnIncrementSec.setBounds(50,320,150,40);
		btnIpSec.setBounds(50,400,150,40);
			
		p3.add(btnLogSec);
		p3.add(btnSalSec);
		p3.add(btnLeaveSec);
		p3.add(btnIncrementSec);
		p3.add(btnIpSec);

		btnLogSec.addActionListener(this);
		btnSalSec.addActionListener(this);
		btnLeaveSec.addActionListener(this);
		btnIncrementSec.addActionListener(this);
		btnIpSec.addActionListener(this);

		jtp=new JTabbedPane();
		jtp.setTabPlacement(JTabbedPane.LEFT);
		jtp.add("Employee Setting",p1);
		jtp.add("Manager Setting",p2);	
		jtp.add("Admin password Setting",p3);
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
			if(str1.equals(Uname)&&str2.equals(salPasswd))
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
			if(str1.equals(Uname)&&str2.equals(salPasswd))
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
		if(e.getSource()==btnLogSec)
		{
			System.out.println("Log Button is Preesed");
			cc=d.getContentPane();
			cc.setLayout(null);
			lblHead=new JLabel("Login Security");
			lblHead.setFont(new Font("Times New Roman",Font.BOLD,24));
			lblUname1=new JLabel("Enter Old User Name");
			lblPasswd1=new JLabel("Enter Old Password");
			lblnewUname=new JLabel("Enter New UserName ");
			lblnewPasswd=new JLabel("Enter New Password");

			lblHead.setBounds(100,30,170,40);
			lblUname1.setBounds(50,90,120,20);
			lblPasswd1.setBounds(50,130,120,20);
			lblnewUname.setBounds(50,170,140,20);
			lblnewPasswd.setBounds(50,210,140,20);

			cc.add(lblHead);
			cc.add(lblUname1);
			cc.add(lblPasswd1);
			cc.add(lblnewUname);		
			cc.add(lblnewPasswd);

			txtUname1=new JTextField(20);
			txtPasswd1=new JPasswordField(20);
			txtnewUname=new JTextField(20);
			txtnewPasswd=new JPasswordField(20);

			txtUname1.setBounds(190,90,120,20);
			txtPasswd1.setBounds(190,130,120,20);
			txtnewUname.setBounds(190,170,120,20);
			txtnewPasswd.setBounds(190,210,120,20);
		
		
			cc.add(txtUname1);
			cc.add(txtPasswd1);
			cc.add(txtnewUname);
			cc.add(txtnewPasswd);

			btnApply=new JButton("Apply");
			btnApply.setBounds(120,270,100,30);
			cc.add(btnApply);
			btnApply.addActionListener(this);
	
			d.setBounds(500,200,350,350);
			d.setVisible(true);
			
		}
		if(e.getSource()==btnApply)
		{
			d.hide();
			String str1,str2,s1,s2;
			str1=txtUname1.getText();	
			str2=txtPasswd1.getText();
			s1=txtnewUname.getText();
			s2=txtnewPasswd.getText();
			System.out.println("Apply Button is Pressed");
			if(str1.equals(Uname)&&str2.equals(adminPasswd))
			{
				try
				{
					url="Jdbc:Odbc:Password";
					con=DriverManager.getConnection(url,"","");
					query="Update Password set AdminUName=?,AdminPassword=?";
					pstmt=con.prepareStatement(query);
					pstmt.setString(1,s1);
					pstmt.setString(2,s2);
					int result=pstmt.executeUpdate();
					if(result>0)
						JOptionPane.showMessageDialog((Component)null,"Updated","Hello",JOptionPane.INFORMATION_MESSAGE);		
					else
						JOptionPane.showMessageDialog((Component)null,"Not Updated","Hello",JOptionPane.ERROR_MESSAGE);
					con.close();
					pstmt.close();
				}
				catch(Exception ex)
				{
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
				JOptionPane.showMessageDialog((Component)null,"Enter Correct UserName and Password","ERROR",JOptionPane.ERROR_MESSAGE);

		}
		if(e.getSource()==btnSalSec)
		{
			System.out.println("SalSec Button is Preesed");
			cc=d1.getContentPane();
			cc.setLayout(null);
			lblHead=new JLabel("Salary Security");
			lblHead.setFont(new Font("Times New Roman",Font.BOLD,24));
			lblUname1=new JLabel("Enter Old User Name");
			lblPasswd1=new JLabel("Enter Old Password");
			lblnewUname=new JLabel("Enter New UserName ");
			lblnewPasswd=new JLabel("Enter New Password");

			lblHead.setBounds(100,30,170,40);
			lblUname1.setBounds(50,90,120,20);
			lblPasswd1.setBounds(50,130,120,20);
			lblnewUname.setBounds(50,170,140,20);
			lblnewPasswd.setBounds(50,210,140,20);

			cc.add(lblHead);
			cc.add(lblUname1);
			cc.add(lblPasswd1);
			cc.add(lblnewUname);		
			cc.add(lblnewPasswd);

			txtUname1=new JTextField(20);
			txtPasswd1=new JPasswordField(20);
			txtnewUname=new JTextField(20);
			txtnewPasswd=new JPasswordField(20);

			txtUname1.setBounds(190,90,120,20);
			txtPasswd1.setBounds(190,130,120,20);
			txtnewUname.setBounds(190,170,120,20);
			txtnewPasswd.setBounds(190,210,120,20);
		
		
			cc.add(txtUname1);
			cc.add(txtPasswd1);
			cc.add(txtnewUname);
			cc.add(txtnewPasswd);

			btnApply1=new JButton("Apply");
			btnApply1.setBounds(120,270,100,30);
			cc.add(btnApply1);
			btnApply1.addActionListener(this);
	
			d1.setBounds(500,200,350,350);
			d1.setVisible(true);

		}	
		if(e.getSource()==btnApply1)
		{
			d1.hide();
			String str1,str2,s1,s2;
			str1=txtUname1.getText();	
			str2=txtPasswd1.getText();
			s1=txtnewUname.getText();
			s2=txtnewPasswd.getText();
			System.out.println("Apply1 Button is Pressed");
			if(str1.equals(Uname)&&str2.equals(salPasswd))
			{
				try
				{
					url="Jdbc:Odbc:Password";
					con=DriverManager.getConnection(url,"","");
					query="Update Password set AdminUName=?,SalaryPassword=?";
					pstmt=con.prepareStatement(query);
					pstmt.setString(1,s1);
					pstmt.setString(2,s2);
					int result=pstmt.executeUpdate();
					if(result>0)
						JOptionPane.showMessageDialog((Component)null,"Updated","Hello",JOptionPane.INFORMATION_MESSAGE);		
					else
						JOptionPane.showMessageDialog((Component)null,"Not Updated","Hello",JOptionPane.ERROR_MESSAGE);
					con.close();
					pstmt.close();
				}
				catch(Exception ex)
				{
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
				JOptionPane.showMessageDialog((Component)null,"Enter Correct UserName and Password","ERROR",JOptionPane.ERROR_MESSAGE);
		}
		if(e.getSource()==btnLeaveSec)
		{
			System.out.println("LeaveSec Button is Preesed");
			cc=d2.getContentPane();
			cc.setLayout(null);
			lblHead=new JLabel("Leave Security");
			lblHead.setFont(new Font("Times New Roman",Font.BOLD,24));
			lblUname1=new JLabel("Enter Old User Name");
			lblPasswd1=new JLabel("Enter Old Password");
			lblnewUname=new JLabel("Enter New UserName ");
			lblnewPasswd=new JLabel("Enter New Password");

			lblHead.setBounds(100,30,170,40);
			lblUname1.setBounds(50,90,120,20);
			lblPasswd1.setBounds(50,130,120,20);
			lblnewUname.setBounds(50,170,140,20);
			lblnewPasswd.setBounds(50,210,140,20);

			cc.add(lblHead);
			cc.add(lblUname1);
			cc.add(lblPasswd1);
			cc.add(lblnewUname);		
			cc.add(lblnewPasswd);

			txtUname1=new JTextField(20);
			txtPasswd1=new JPasswordField(20);
			txtnewUname=new JTextField(20);
			txtnewPasswd=new JPasswordField(20);

			txtUname1.setBounds(190,90,120,20);
			txtPasswd1.setBounds(190,130,120,20);
			txtnewUname.setBounds(190,170,120,20);
			txtnewPasswd.setBounds(190,210,120,20);
		
		
			cc.add(txtUname1);
			cc.add(txtPasswd1);
			cc.add(txtnewUname);
			cc.add(txtnewPasswd);

			btnApply2=new JButton("Apply");
			btnApply2.setBounds(120,270,100,30);
			cc.add(btnApply2);
			btnApply2.addActionListener(this);
	
			d2.setBounds(500,200,350,350);
			d2.setVisible(true);

		}
		if(e.getSource()==btnApply2)
		{
			d2.hide();
			String str1,str2,s1,s2;
			str1=txtUname1.getText();	
			str2=txtPasswd1.getText();
			s1=txtnewUname.getText();
			s2=txtnewPasswd.getText();
			System.out.println("Apply2 Button is Pressed");
			if(str1.equals(Uname)&&str2.equals(leavePasswd))
			{
				try
				{
					url="Jdbc:Odbc:Password";
					con=DriverManager.getConnection(url,"","");
					query="Update Password set AdminUName=?,LeavePassword=?";
					pstmt=con.prepareStatement(query);
					pstmt.setString(1,s1);
					pstmt.setString(2,s2);
					int result=pstmt.executeUpdate();
					if(result>0)
						JOptionPane.showMessageDialog((Component)null,"Updated","Hello",JOptionPane.INFORMATION_MESSAGE);		
					else
						JOptionPane.showMessageDialog((Component)null,"Not Updated","Hello",JOptionPane.ERROR_MESSAGE);
					con.close();
					pstmt.close();
				}
				catch(Exception ex)
				{
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
				JOptionPane.showMessageDialog((Component)null,"Enter Correct UserName and Password","ERROR",JOptionPane.ERROR_MESSAGE);
		}
		if(e.getSource()==btnIncrementSec)
		{
			System.out.println("IncrementSec Button is Preesed");
			cc=d3.getContentPane();
			cc.setLayout(null);
			lblHead=new JLabel(" Increment Security");
			lblHead.setFont(new Font("Times New Roman",Font.BOLD,24));
			lblUname1=new JLabel("Enter Old User Name");
			lblPasswd1=new JLabel("Enter Old Password");
			lblnewUname=new JLabel("Enter New UserName ");
			lblnewPasswd=new JLabel("Enter New Password");

			lblHead.setBounds(90,30,220,40);
			lblUname1.setBounds(50,90,120,20);
			lblPasswd1.setBounds(50,130,120,20);
			lblnewUname.setBounds(50,170,140,20);
			lblnewPasswd.setBounds(50,210,140,20);

			cc.add(lblHead);
			cc.add(lblUname1);
			cc.add(lblPasswd1);
			cc.add(lblnewUname);		
			cc.add(lblnewPasswd);

			txtUname1=new JTextField(20);
			txtPasswd1=new JPasswordField(20);
			txtnewUname=new JTextField(20);
			txtnewPasswd=new JPasswordField(20);

			txtUname1.setBounds(190,90,120,20);
			txtPasswd1.setBounds(190,130,120,20);
			txtnewUname.setBounds(190,170,120,20);
			txtnewPasswd.setBounds(190,210,120,20);
		
		
			cc.add(txtUname1);
			cc.add(txtPasswd1);
			cc.add(txtnewUname);
			cc.add(txtnewPasswd);

			btnApply3=new JButton("Apply");
			btnApply3.setBounds(120,270,100,30);
			cc.add(btnApply3);
			btnApply3.addActionListener(this);
	
			d3.setBounds(500,200,350,350);
			d3.setVisible(true);
		}
		if(e.getSource()==btnApply3)
		{
			d3.hide();
			String str1,str2,s1,s2;
			str1=txtUname1.getText();	
			str2=txtPasswd1.getText();
			s1=txtnewUname.getText();
			s2=txtnewPasswd.getText();
			System.out.println("Apply2 Button is Pressed");
			if(str1.equals(Uname)&&str2.equals(incPasswd))
			{
				try
				{
					url="Jdbc:Odbc:Password";
					con=DriverManager.getConnection(url,"","");
					query="Update Password set AdminUName=?,IncrementPassword=?";
					pstmt=con.prepareStatement(query);
					pstmt.setString(1,s1);
					pstmt.setString(2,s2);
					int result=pstmt.executeUpdate();
					if(result>0)
						JOptionPane.showMessageDialog((Component)null,"Updated","Hello",JOptionPane.INFORMATION_MESSAGE);		
					else
						JOptionPane.showMessageDialog((Component)null,"Not Updated","Hello",JOptionPane.ERROR_MESSAGE);
					con.close();
					pstmt.close();
				}
				catch(Exception ex)
				{
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
				JOptionPane.showMessageDialog((Component)null,"Enter Correct UserName and Password","ERROR",JOptionPane.ERROR_MESSAGE);
		}
		if(e.getSource()==btnIpSec)
		{
			System.out.println("IPSec Button is Preesed");
			cc=d4.getContentPane();
			cc.setLayout(null);
			lblHead=new JLabel(" IP Security");
			lblHead.setFont(new Font("Times New Roman",Font.BOLD,24));		
			lblPasswd1=new JLabel("Enter Old IP");
	
			lblnewPasswd=new JLabel("Enter New IP");

			lblHead.setBounds(90,30,220,40);
			lblPasswd1.setBounds(50,90,120,20);
			lblnewPasswd.setBounds(50,130,140,20);

			cc.add(lblHead);
			cc.add(lblPasswd1);
			cc.add(lblnewPasswd);

			t1=new JTextField(20);
			t2=new JTextField(20);

			
			t1.setBounds(190,90,120,20);
			t2.setBounds(190,130,120,20);
		
			
			cc.add(t1);
			cc.add(t2);

			btnApply4=new JButton("Apply");
			btnApply4.setBounds(120,180,100,30);
			cc.add(btnApply4);
			btnApply4.addActionListener(this);
	
			d4.setBounds(500,200,350,300);
			d4.setVisible(true);
		}
		if(e.getSource()==btnApply4)
		{
			d4.hide();
			String str1,str2,s1,s2;
			
			str2=t1.getText();
			s2=t2.getText();
			System.out.println("Apply4 Button is Pressed");
			if(str2.equals(ipPasswd))
			{
				try
				{
					url="Jdbc:Odbc:Password";
					con=DriverManager.getConnection(url,"","");
					query="Update Password set IpSetting=?";
					pstmt=con.prepareStatement(query);
					pstmt.setString(1,s2);
					int result=pstmt.executeUpdate();
					if(result>0)
						JOptionPane.showMessageDialog((Component)null,"Updated","Hello",JOptionPane.INFORMATION_MESSAGE);		
					else
						JOptionPane.showMessageDialog((Component)null,"Not Updated","Hello",JOptionPane.ERROR_MESSAGE);
					con.close();
					pstmt.close();
				}
				catch(Exception ex)
				{
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
				JOptionPane.showMessageDialog((Component)null,"Enter Correct UserName and Password","ERROR",JOptionPane.ERROR_MESSAGE);
		}
	}
}

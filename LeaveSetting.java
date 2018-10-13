import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
class LeaveSetting extends JFrame implements ActionListener
{
	JTabbedPane  jtp;
	JLabel lblTotalCasual,lblTotalSick,lblEmpSet,lblManagerSet,lblTotalCasual1,lblTotalSick1,lblUname,lblPasswd,p2lblUname,p2lblPasswd,p2lblhra,p2lblda,p2lblca,p2lblma,p2lblea,p2lblta,p2lblenterAll,p2lblpt,p2lblpf,lblHead,lblUname1,lblPasswd1,lblnewUname,lblnewPasswd;
	JTextField txtTotalCasual,txtTotalSick,txtTotalCasual1,txtTotalSick1,txtUname,p2txtUname,txtUname1,txtnewUname;
	JPasswordField txtPasswd,p2txtPasswd,txtPasswd1,txtnewPasswd;
	JButton btnUpgrade,btnUpdate,p2btnUpgrade,p2btnUpdate,btnOk,p2btnOk;
	JFrame f1,f2;
	JPanel p1,p2;
	Container c;
	Connection con;
	Statement stmt,stmt1,stmt2;
	String query,url;
	ResultSet rs,rs1;
	String AdminPasswd,LeavePasswd;
	static int count=0;
	LeaveSetting()
	{

		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			url="Jdbc:Odbc:Password";
			con=DriverManager.getConnection(url,"","");
			stmt=con.createStatement();
			query="Select AdminUName,LeavePassword from Password";
			rs=stmt.executeQuery(query);
			if(rs.next())
			{
				AdminPasswd=rs.getString("AdminUName");
				LeavePasswd=rs.getString("LeavePassword");
				System.out.println(AdminPasswd);
				System.out.println(LeavePasswd);

			}	
			con.close();
			stmt.close();
		}
		catch(Exception ee)
		{
			System.out.println(ee.getMessage());
			try
			{
				con.close();
				stmt.close();
			}
			catch(Exception c)
			{
			}
		}
			
		c=getContentPane();
		setBounds(450,120,530,420);
	//	-------------Panel1 Design---------------------
		
		p1=new JPanel();
		p1.setLayout(null);
		
		lblTotalCasual=new JLabel("Total Casual");
		lblTotalCasual.setFont(new Font("Arial",Font.BOLD,18));
		
		lblTotalSick=new JLabel("Total Sick");
		lblTotalSick.setFont(new Font("Arial",Font.BOLD,18));

		lblEmpSet=new JLabel("Employee Leave Setting ");
		lblEmpSet.setFont(new Font("Arial",Font.BOLD,22));

		lblTotalCasual.setBounds(50,100,120,20);
		lblTotalSick.setBounds(50,150,120,20);
		lblEmpSet.setBounds(60,10,300,40);
		
		p1.add(lblTotalCasual);
		p1.add(lblTotalSick);
		p1.add(lblEmpSet);

		txtTotalCasual=new JTextField(20);
		txtTotalSick=new JTextField(20);
		
		txtTotalCasual.setBounds(200,100,120,20);
		txtTotalSick.setBounds(200,150,120,20);

		p1.add(txtTotalCasual);
		p1.add(txtTotalSick);

		btnUpgrade=new JButton("Upgrade");
		btnUpdate=new JButton("Update");
		btnUpdate.setEnabled(false);

		btnUpgrade.setBounds(50,250,100,30);
		btnUpdate.setBounds(200,250,100,30);
	
		p1.add(btnUpgrade);
		p1.add(btnUpdate);

		btnUpgrade.addActionListener(this);
		btnUpdate.addActionListener(this);

		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			url="Jdbc:Odbc:Leave";	
			con=DriverManager.getConnection(url,"","");
		System.out.println("Test1");
			stmt1=con.createStatement();
		System.out.println("Test2");
			stmt2=con.createStatement();
		System.out.println("Test3");
		System.out.println("Test4");
		
			
		System.out.println("Test5");
			query="Select EmployeeId from EmplyeeDetails where Designation='Employee'";

			rs=stmt1.executeQuery(query);
		
		System.out.println("Test6");
			if(rs.next())
			{
				int a=rs.getInt("EmployeeId");
				String query1="Select TotalCasual,TotalSick from LeaveEntry where EmployeeId="+a;
				System.out.println("Test7");
				rs1=stmt2.executeQuery(query1);
				System.out.println("Test8");
			}
			if(rs1.next())
			{
				txtTotalCasual.setText(rs1.getInt("TotalCasual")+"");
				txtTotalSick.setText(rs1.getInt("TotalSick")+"");

				txtTotalCasual.setEditable(false);
				txtTotalSick.setEditable(false);
			}
			else
				System.out.println("Data Not Found");

			con.close();
			stmt1.close();
			stmt2.close();
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			try
			{
				con.close();
				
			}
			catch(Exception ex)	
			{
			}
		}

	//	------Panel2 Design--------------------------

		p2=new JPanel();
		p2.setLayout(null);
		
		lblTotalCasual1=new JLabel("Total Casual");
		lblTotalCasual1.setFont(new Font("Arial",Font.BOLD,18));
		
		lblTotalSick1=new JLabel("Total Sick");
		lblTotalSick1.setFont(new Font("Arial",Font.BOLD,18));

		lblManagerSet=new JLabel("Manager Leave Setting ");
		lblManagerSet.setFont(new Font("Arial",Font.BOLD,22));

		lblTotalCasual1.setBounds(50,100,120,20);
		lblTotalSick1.setBounds(50,150,120,20);
		lblManagerSet.setBounds(60,10,300,40);
		
		p2.add(lblTotalCasual1);
		p2.add(lblTotalSick1);
		p2.add(lblManagerSet);

		txtTotalCasual1=new JTextField(20);
		txtTotalSick1=new JTextField(20);
		
		txtTotalCasual1.setBounds(200,100,120,20);
		txtTotalSick1.setBounds(200,150,120,20);

		p2.add(txtTotalCasual1);
		p2.add(txtTotalSick1);

		p2btnUpgrade=new JButton("Upgrade");
		p2btnUpdate=new JButton("Update");

		p2btnUpgrade.setBounds(50,250,100,30);
		p2btnUpdate.setBounds(200,250,100,30);
		p2btnUpdate.setEnabled(false);
	
		p2.add(p2btnUpgrade);
		p2.add(p2btnUpdate);
	
		p2btnUpgrade.addActionListener(this);
		p2btnUpdate.addActionListener(this);
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			url="Jdbc:Odbc:Leave";	
			con=DriverManager.getConnection(url,"","");
		System.out.println("Test1");
			Statement stmt3=con.createStatement();
		System.out.println("Test2");
			Statement stmt4=con.createStatement();
		System.out.println("Test3");
		System.out.println("Test4");
		
			
		System.out.println("Test5");
			query="Select EmployeeId from EmplyeeDetails where Designation='Manager'";

			rs=stmt3.executeQuery(query);
		
		System.out.println("Test6");
			if(rs.next())
			{
				int s=rs.getInt("EmployeeId");
				String query2="Select TotalCasual,TotalSick from LeaveEntry where EmployeeId="+s;
				System.out.println("Test7");
				rs1=stmt4.executeQuery(query2);
				System.out.println("Test8");
			}
			if(rs1.next())
			{
				txtTotalCasual1.setText(rs1.getInt("TotalCasual")+"");
				txtTotalSick1.setText(rs1.getInt("TotalSick")+"");

				txtTotalCasual1.setEditable(false);
				txtTotalSick1.setEditable(false);
			}
			else
				System.out.println("Data Not Found");

			con.close();
			stmt1.close();
			stmt2.close();
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			try
			{
				con.close();
				
			}
			catch(Exception ex)	
			{
			}
		}
		
		jtp=new JTabbedPane();
		jtp.setTabPlacement(JTabbedPane.LEFT);
		jtp.add("Employee Leave Setting ",p1);
		jtp.add("Manager Leave Setting ",p2);
			
		c.add(jtp);
		
		
		
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==btnUpgrade)
		{
			f1=new JFrame("Login");
			f1.setVisible(true);
			f1.setBounds(650,200,300,200);
			f1.setLayout(null);
			lblUname=new JLabel("User Name");
			lblPasswd=new JLabel("Password");
			
			lblUname.setBounds(30,30,100,20);	
			lblPasswd.setBounds(30,80,100,20);
	
			f1.add(lblUname);
			f1.add(lblPasswd);
	
			txtUname=new JTextField(20);
			txtPasswd=new JPasswordField(20);

			txtUname.setBounds(130,30,130,20);
			txtPasswd.setBounds(130,80,130,20);
	
			f1.add(txtUname);
			f1.add(txtPasswd);
	
			btnOk=new JButton("OK");
			btnOk.setBounds(100,130,80,30);
			f1.add(btnOk);
			btnOk.addActionListener(this);
		}	
		if(e.getSource()==btnOk)
		{		
		
			String str1,str2;
			str1=txtUname.getText();
			str2=txtPasswd.getText();	
			if(str1.equals(AdminPasswd)&&str2.equals(LeavePasswd))
			{
				f1.setVisible(false);
				txtTotalCasual.setEditable(true);
				txtTotalSick.setEditable(true);
				btnUpdate.setEnabled(true);
			}
			else
			{
				f1.setVisible(false);
				JOptionPane.showMessageDialog((Component)null,"Wrong User Name and Password","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		if(e.getSource()==btnUpdate)
		{
			int c,s;
			c=Integer.parseInt(txtTotalCasual.getText());
			s=Integer.parseInt(txtTotalSick.getText());
			System.out.println("Update Button is Pressed");
			try
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				url="Jdbc:Odbc:Leave";	
				con=DriverManager.getConnection(url,"","");
				System.out.println("Test1");
				Statement stmt3=con.createStatement();
				System.out.println("Test2");
				Statement stmt4=con.createStatement();
				System.out.println("Test3");
				System.out.println("Test4");
				query="Select EmployeeId from EmplyeeDetails where Designation='Employee'";
				rs=stmt3.executeQuery(query);
				while(rs.next())
				{
					int q=rs.getInt("EmployeeId");
					String qe="Update LeaveEntry set TotalCasual="+c+", TotalSick="+s+" where EmployeeId="+q;
					int w=stmt4.executeUpdate(qe);	
					if(w>0)
					{
						count=1;
					}

				}
				if(count>0)
					JOptionPane.showMessageDialog((Component)null,"Values Updated","Updated",JOptionPane.INFORMATION_MESSAGE);
				con.close();
				
			}
			catch(Exception cc)
			{
				System.out.println(cc.getMessage());
				try
				{
					con.close();
				}
				catch(Exception cx){}
			}
		
			
			System.out.println("Test5");
		
			
		}
		if(e.getSource()==p2btnUpgrade)
		{
			System.out.println("UpGrade Button pressed");

			f2=new JFrame("Login");
			f2.setVisible(true);
			f2.setBounds(650,200,300,200);
			f2.setLayout(null);
			p2lblUname=new JLabel("User Name");
			p2lblPasswd=new JLabel("Password");
			
			p2lblUname.setBounds(30,30,100,20);	
			p2lblPasswd.setBounds(30,80,100,20);
	
			f2.add(p2lblUname);
			f2.add(p2lblPasswd);
	
			p2txtUname=new JTextField(20);
			p2txtPasswd=new JPasswordField(20);
	

			p2txtUname.setBounds(130,30,130,20);
			p2txtPasswd.setBounds(130,80,130,20);
	
			f2.add(p2txtUname);
			f2.add(p2txtPasswd);
	
			p2btnOk=new JButton("OK");
			p2btnOk.setBounds(100,130,80,30);
			f2.add(p2btnOk);
			
			p2btnOk.addActionListener(this);
		}
		if(e.getSource()==p2btnOk)
		{
			System.out.println("OK Button is pressed");
			
			String str1,str2;
			str1=p2txtUname.getText();
			str2=p2txtPasswd.getText();	
			if(str1.equals(AdminPasswd)&&str2.equals(LeavePasswd))
			{
				System.out.println("Password is correct");
				f2.setVisible(false);
				txtTotalCasual1.setEditable(true);
				txtTotalSick1.setEditable(true);
				p2btnUpdate.setEnabled(true);
			}
			else
			{
				f2.setVisible(false);
				JOptionPane.showMessageDialog((Component)null,"Wrong User Name and Password","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		if(e.getSource()==p2btnUpdate)
		{
			int cc,ss;
			cc=Integer.parseInt(txtTotalCasual1.getText());
			ss=Integer.parseInt(txtTotalSick1.getText());
			System.out.println("Update Button is Pressed");
			try
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				url="Jdbc:Odbc:Leave";	
				con=DriverManager.getConnection(url,"","");
				System.out.println("Test1");
				Statement stmt3=con.createStatement();
				System.out.println("Test2");
				Statement stmt4=con.createStatement();
				System.out.println("Test3");
				System.out.println("Test4");
				query="Select EmployeeId from EmplyeeDetails where Designation='Manager'";
				rs=stmt3.executeQuery(query);
				while(rs.next())
				{
					int q=rs.getInt("EmployeeId");
					String qe="Update LeaveEntry set TotalCasual="+cc+", TotalSick="+ss+" where EmployeeId="+q;
					int w=stmt4.executeUpdate(qe);	
					if(w>0)
					{
						count=1;
					}

				}
				if(count>0)
					JOptionPane.showMessageDialog((Component)null,"Values Updated","Updated",JOptionPane.INFORMATION_MESSAGE);
				con.close();
				
			}
			catch(Exception ce)
			{
				System.out.println(ce.getMessage());
				try
				{
					con.close();
				}
				catch(Exception cx){}
			}
		
			
			System.out.println("Test5");
		
			
		}

	}
	public static void main(String args[])
	{
		LeaveSetting l=new LeaveSetting();
		l.setVisible(true);
		l.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
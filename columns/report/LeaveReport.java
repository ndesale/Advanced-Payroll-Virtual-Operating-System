import java.io.*;
import javax.swing.*;
import java.awt.print.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class LeaveReport extends JPanel implements ActionListener,Printable
{
	JLabel lblHeader;

	JLabel lblEmpId,lblDeptId,lblLeaveType,lblLeaveFrom,lblLeaveTo,lblLeaveReason,lblBalancedCasual,lblBalancedSick,lblEmpName;
	JLabel txtEmpId,txtDeptId,txtLeaveType,txtLeaveFrom,txtLeaveTo,txtLeaveReason,txtBalancedCasual,txtBalancedSick,txtEmpName;
	JLabel lblLeaveHeader=new JLabel("Total Leaves taken by Employee");

	List dates;
	Connection con;
	Statement stmt;
	ResultSet rs;

	String name,deptid;

	JButton btnView=new JButton("View Report");
	JFrame f1,f;
	MenuItem print=new MenuItem("Print"),close=new MenuItem("Close");
	String id;

	LeaveReport()
	{
		id=JOptionPane.showInputDialog("Enter the Employee Id: ");
		if(id.equals(""))
		{
		}
		else
		{

		lblLeaveHeader.setFont(new Font("Times New Roman",Font.BOLD,20));
		lblLeaveHeader.setBounds(5,5,280,30);
		btnView.setBounds(80,140,130,30);

		f=new JFrame("LEAVE REPORT");
		Container c=f.getContentPane();

		c.setLayout(null);
		f.setBounds(450,250,300,200);
		f.setResizable(false);
		f.setVisible(true);
		c.add(lblLeaveHeader);
		c.add(btnView);
		btnView.addActionListener(this);

		dates=new List(5);
		dates.setBounds(5,35,280,100);
		c.add(dates);

		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			String url="Jdbc:Odbc:Leave";
			con=DriverManager.getConnection(url,"","");
			stmt=con.createStatement();
			rs=stmt.executeQuery("Select * from LeaveStatus where LeaveId LIKE '"+id+"%'");
			while(rs.next())
			{
				String date=rs.getString("LeaveId");
				String reason=rs.getString("Type");	

				date=date+" : "+reason;
			
				dates.add(date);
			}
			con.close();
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		}
	}
	public int print(Graphics g, PageFormat format, int pagenum) 
	{
		if (pagenum > 0)
		{
			return Printable.NO_SUCH_PAGE;
		}
		else
		{
			Graphics2D g2 = (Graphics2D) g;
			g2.translate(format.getImageableX(), format.getImageableY());
			this.paint(g2);

			return Printable.PAGE_EXISTS;
		}
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==btnView)
		{
			String date=dates.getSelectedItem();
			
			if(date==null)
			{
				JOptionPane.showMessageDialog(f,"Please Select the Leave");
			}
			else
			{
				MenuBar mb=new MenuBar();
				Menu file=new Menu("File");
				mb.add(file);
				file.add(print);
				file.addSeparator();
				file.add(close);			

				f1=new JFrame("Leave Report");
				f1.setExtendedState(JFrame.MAXIMIZED_BOTH);
				Container c=f1.getContentPane();
				c.setBackground(Color.GRAY);

				c.setLayout(null);
				f1.setVisible(true);
				f1.setMenuBar(mb);
				c.add(this);

				print.addActionListener(this);
				close.addActionListener(this);

				this.setBackground(Color.WHITE);
				this.setBounds(350,0,650,700);
				f.setVisible(false);

				
		/* START LEAVE REPORT CODE HERE	*/
				this.setLayout(null);

				lblHeader=new JLabel("Employee Leave");
				lblHeader.setBounds(130,100,200,30);
				lblHeader.setFont(new Font("Times New Roman",Font.BOLD,24));

				this.add(lblHeader);
				this.setBorder(BorderFactory.createEtchedBorder(1));


				lblEmpId=new JLabel("Employee ID :",JLabel.RIGHT);
				lblEmpName=new JLabel("Employee Name :",JLabel.RIGHT);
				lblDeptId=new JLabel("Department ID :",JLabel.RIGHT);
				lblLeaveType=new JLabel("Leave Type :",JLabel.RIGHT);
				lblLeaveFrom=new JLabel("Leave From :",JLabel.RIGHT);
				lblLeaveTo=new JLabel("Leave To :",JLabel.RIGHT);
				lblLeaveReason=new JLabel("Leave Reason :",JLabel.RIGHT);
				lblBalancedCasual=new JLabel("Balanced Casual :",JLabel.RIGHT);
				lblBalancedSick=new JLabel("Balanced Sick :",JLabel.RIGHT);

				txtEmpId=new JLabel("Employee ID :",JLabel.LEFT);
				txtEmpName=new JLabel();
				txtDeptId=new JLabel();
				txtLeaveType=new JLabel();
				txtLeaveFrom=new JLabel();
				txtLeaveTo=new JLabel();
				txtLeaveReason=new JLabel();
				txtBalancedCasual=new JLabel();
				txtBalancedSick=new JLabel();

				lblEmpId.setBounds(10,180,200,30);
				lblEmpName.setBounds(10,210,200,30);
				lblDeptId.setBounds(10,240,200,30);
				lblLeaveType.setBounds(10,270,200,30);
				lblLeaveFrom.setBounds(10,300,200,30);
				lblLeaveTo.setBounds(10,330,200,30);
				lblLeaveReason.setBounds(10,360,200,30);
				lblBalancedCasual.setBounds(10,390,200,30);
				lblBalancedSick.setBounds(10,420,200,30);

				txtEmpId.setBounds(260,180,200,30);
				txtEmpName.setBounds(260,210,200,30);
				txtDeptId.setBounds(260,240,200,30);
				txtLeaveType.setBounds(260,270,200,30);
				txtLeaveFrom.setBounds(260,300,200,30);
				txtLeaveTo.setBounds(260,330,200,30);
				txtLeaveReason.setBounds(260,360,200,30);
				txtBalancedCasual.setBounds(260,390,200,30);
				txtBalancedSick.setBounds(260,420,200,30);

				this.add(lblEmpId);
				this.add(lblEmpName);
				this.add(lblDeptId);
				this.add(lblLeaveType);
				this.add(lblLeaveFrom);
				this.add(lblLeaveTo);
				this.add(lblLeaveReason);
				this.add(lblBalancedCasual);
				this.add(lblBalancedSick);
		
				this.add(txtEmpId);
				this.add(txtEmpName);
				this.add(txtDeptId);
				this.add(txtLeaveType);
				this.add(txtLeaveFrom);
				this.add(txtLeaveTo);
				this.add(txtLeaveReason);
				this.add(txtBalancedCasual);
				this.add(txtBalancedSick);

				try
				{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					con=DriverManager.getConnection("Jdbc:Odbc:NayanDesale","","");
					stmt=con.createStatement();
					rs=stmt.executeQuery("Select EmployeeName,DeptID from EmplyeeDetails where EmployeeId="+id);
					rs.next();
					name=rs.getString("EmployeeName");
					deptid=rs.getString("DeptID");
					con.close();
				}
				catch(Exception ex1){}

				txtEmpId.setText(id);
				txtEmpName.setText(name);
				txtDeptId.setText(deptid);


				int balc,bals;
				try
				{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					con=DriverManager.getConnection("Jdbc:Odbc:Leave","","");
					stmt=con.createStatement();

					rs=stmt.executeQuery("Select * from LeaveEntry where EmployeeId="+id);
					rs.next();

					balc=rs.getInt("BalancedCasual");
					bals=rs.getInt("BalancedSick");

					con.close();

					txtBalancedCasual.setText(balc+"");
					txtBalancedSick.setText(bals+"");
				}
				catch(Exception es)
				{
					JOptionPane.showMessageDialog(null,es.getMessage());
				}

				try
				{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					con=DriverManager.getConnection("Jdbc:Odbc:Leave","","");
					stmt=con.createStatement();

					rs=stmt.executeQuery("Select * from LeaveStatus where LeaveId LIKE '"+id+"%'");
					rs.next();

					String lr,lf,lty,lt;
					java.sql.Date d1,d2;

					d1=rs.getDate("From");
					d2=rs.getDate("To");

					d1.setYear(d1.getYear()-1900);
					d2.setYear(d2.getYear()-1900);

					lty=rs.getString("Type");
					lr=rs.getString("Reason");

					con.close();

					txtLeaveType.setText(lty);
					txtLeaveReason.setText(lr);
					txtLeaveFrom.setText(new java.text.SimpleDateFormat("dd-MM-yyyy").format(d1));
					txtLeaveTo.setText(new java.text.SimpleDateFormat("dd-MM-yyyy").format(d2));		
				}
				catch(Exception es)
				{
					JOptionPane.showMessageDialog(null,es.getMessage());
				}
			}
		}
		else if(e.getSource()==close)
		{
			f1.setVisible(false);
			f.setVisible(true);
		}
		else if(e.getSource()==print)
		{
			try
			{
				PrinterJob pj = PrinterJob.getPrinterJob();
				PageFormat format = pj.pageDialog(pj.defaultPage());
				pj.setPrintable(this, format);
				
				if (pj.printDialog()) 
				pj.print();
			}
			catch(Exception ex1)
			{}
		}
	}
	public static void main(String args[])
	{
		LeaveReport lp=new LeaveReport();
	}
}
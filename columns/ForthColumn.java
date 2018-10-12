package columns;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;
import javax.swing.border.*;
import java.sql.*;
import java.awt.print.*;

public class ForthColumn extends Thread implements ActionListener
{
	JButton l1;
	JPanel p;
	ImageIcon i;
	JFrame jf;

	DemoPaySlip2 dp;

	public ForthColumn(JFrame j,JPanel s)
	{
		p=s;
		jf=j;

		l1=new JButton(new ImageIcon("icons\\report\\payslip.jpg"));

		l1.setRolloverIcon(new ImageIcon("icons\\report\\payslip.jpg"));
	}
	public void run()
	{
		p.add(l1);
		
		for(int s=0;s<=9;s++)
		{
			try
			{
				sleep(20);
			}
			catch(Exception e){}
		}

		l1.setBounds(650,80,100,69);

		for(int s=0;s<=6;s++)
		{
			try
			{
				sleep(20);
			}
			catch(Exception e){}
		}
		
		l1.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==l1)
		{
			dp=new DemoPaySlip2();
		}
	}
}
class DemoPaySlip2 extends JPanel implements ActionListener,Printable
{
	JLabel lblHead,lblEmp,lblLeave,lblType,lblTotal,lblReLeave,lblIncentive,lblDeduction,lblRs,lblAmt,lbltotalInc,lblVal1,lbltotalDed,lblVal2,lblNetPay,lblNetval,lblHeadEmp;
	JFrame f;
	Container fc;
	JTable table,table1,table2,table3;
	TableColumnModel tcm,tcm1,tcm2,tcm3;
	Connection con;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs,rs1;
	String query,url;
	int id,dept,balSick,balCasual;
	Date d;
	String EmpName,fatherName,Gender,City,Desg;
	java.sql.Date DOJ;
	float hraPer,daPer,caPer,maPer,eaPer,entPer,taPer,pfPer;
	float basic,hraAmt,daAmt,caAmt,maAmt,eaAmt,entAmt,taAmt,ptAmt,pfAmt,totalInc,totalDed,NetAmt;
	JButton btnPrint;
	
	DemoPaySlip2()
	{
		this.setBorder(new LineBorder(Color.BLACK,2));

		id=Integer.parseInt(JOptionPane.showInputDialog("Enter the Employee Id"));
		try
		{
	//		------------EmployeeDetails Connection---------------
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			url="Jdbc:Odbc:NayanDesale";
			con=DriverManager.getConnection(url,"","");
			stmt=con.createStatement();
			query="Select EmployeeName,FatherName,Gender,City,DeptID,Designation,DOJ from EmplyeeDetails where EmployeeId= "+id;
			rs=stmt.executeQuery(query);	
			if(rs.next())
			{
				
				EmpName=rs.getString("EmployeeName");
				fatherName=rs.getString("FatherName");
				Gender=rs.getString("Gender");
				City=rs.getString("City");
				dept=rs.getInt("DeptID");
				Desg=rs.getString("Designation");
				DOJ =rs.getDate("DOJ");

			
				System.out.println("Employee:-Values are sucessfully stored in varibles");
				con.close();
				stmt.close();

	//			----------------Leave Connection--------------
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				url="Jdbc:Odbc:Leave";
				con=DriverManager.getConnection(url,"","");	
				stmt=con.createStatement();
			System.out.println("Yes");
				query="Select BalancedCasual,BalancedSick from LeaveEntry where EmployeeId="+id;
				rs=stmt.executeQuery(query);
			
				rs.next();
				balCasual=rs.getInt("BalancedCasual");
				balSick=rs.getInt("BalancedSick");
				System.out.println("Leave:-Values are sucessfully stored in varibles");
				con.close();
				stmt.close();	
				
				
	//			----------Incentive Connection--------
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				url="Jdbc:Odbc:Incentives";
				con=DriverManager.getConnection(url,"","");
				String query1="Select HouseRentAllowance,DearenessAllowance,CityAllowance,MedicalAllowance,EducationAllowance,TravellingAllowance,EntertainmentAllowance,ProfessionalTax,ProvidendFund from Incentives where Designation=?";
				pstmt=con.prepareStatement(query1);
				pstmt.setString(1,Desg);
				rs=pstmt.executeQuery();
				
				if(rs.next())	
				{
					hraPer=rs.getInt("HouseRentAllowance");
					daPer=rs.getFloat("DearenessAllowance");
					caPer=rs.getFloat("CityAllowance");
					maPer=rs.getFloat("MedicalAllowance");
					eaPer=rs.getFloat("EducationAllowance");
					taPer=rs.getFloat("TravellingAllowance");
					entPer=rs.getFloat("EntertainmentAllowance");
					ptAmt=rs.getFloat("ProfessionalTax");		
					pfPer=rs.getFloat("ProvidendFund");
					System.out.println("Incentive:-Vsalues are sucessfully stored in varibles");
				}
				else
					System.out.println("No Record");
				System.out.println(hraAmt);
				con.close();
				pstmt.close();

		//              ---------------SalaryDetails Connection --------------------------
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				url="Jdbc:Odbc:SalaryDetails";
				con=DriverManager.getConnection(url,"","");
				
				stmt=con.createStatement();
				query="Select MonthlyBasic from SalaryDetails where EmployeeId="+id;
				rs=stmt.executeQuery(query);				
				rs.next();
				basic=rs.getFloat("MonthlyBasic");
				System.out.println("Salary Details:-Values are sucessfully stored in varibles");
				con.close();
				stmt.close();

				hraAmt=(hraPer*basic)/100;
				daAmt=(daPer*basic)/100;
				caAmt=(caPer*basic)/100;
				maAmt=maPer;
				eaAmt=eaPer;
				taAmt=taPer;
				entAmt=(entPer*basic)/100;
				totalInc=hraAmt+daAmt+caAmt+maAmt+eaAmt+taAmt+entAmt;
				ptAmt=ptAmt/12;
				pfAmt=(pfPer*basic)/100;
				totalDed=ptAmt+pfAmt;
				NetAmt=totalInc-totalDed;
				System.out.println("Value Converted");
				

				f=new JFrame("Payslip");
				fc=f.getContentPane();
				fc.setLayout(null);
				f.setVisible(true);
				f.setBounds(0,0,1368,768);
				f.setExtendedState(JFrame.MAXIMIZED_BOTH);

				btnPrint=new JButton(new ImageIcon("icons\\leaveicon\\print.jpg"));
				btnPrint.addActionListener(this);
				btnPrint.setBounds(0,0,40,40);
				btnPrint.setRolloverIcon(new ImageIcon("icons\\leaveicon\\printshine.jpg"));
				fc.add(btnPrint);
				fc.repaint();
	
				this.setLayout(null);
				this.setBounds(350,0,650,700);
				this.setBackground(Color.WHITE);
				f.add(this);

				
	
				lblHead=new JLabel("PAYSLIP");
				lblHead.setFont(new Font("Arial",Font.BOLD,26));
		

				lblHead.setBounds(260,0,120,50);
				this.add(lblHead);

				String str[]=EmpName.split(" ");
				String inital,last,name;
				inital=str[0];
				last=str[1];
				System.out.println(str[0]);
				System.out.println(str[1]);
				if(Gender.equals("Male"))
					 name=" Mr."+inital+" "+fatherName+" "+last; 
				else
					 name=" Mrs."+inital+" "+fatherName+" "+last; 
				


			
				lblHeadEmp=new JLabel(name);
				lblHeadEmp.setFont(new Font("Arial",Font.BOLD,18));
				lblHeadEmp.setBounds(0,60,250,30);
				add(lblHeadEmp);
				
				lblEmp=new JLabel("Employee Details",JLabel.CENTER);
				lblEmp.setFont(new Font("Arial",Font.BOLD,22));
				lblEmp.setBounds(0,110,650,30);
				lblEmp.setBorder(new LineBorder(Color.BLACK,1));
				add(lblEmp);

				java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd-MM-yyyy");

				String cols[]={"","","",""};
				String rowss[][]={{ " Id",id+"" ,"Dept",dept+""},
				 	          {" Name",EmpName,"City",City},
				 	          {" Designation",Desg,"DOJ",sdf.format(DOJ)}};
				table=new JTable(rowss,cols){
					private static final long serialVersionUID = 1L;

					public boolean isCellEditable(int row, int column) 
					{                
					                return false;               
					} };
				table.setBounds(0,140,650,90);
				this.add(table);
	
				table.setRowHeight(30);
				tcm=table.getColumnModel();
				tcm.getColumn(0).setPreferredWidth(700);
				tcm.getColumn(1).setPreferredWidth(1500);
				tcm.getColumn(2).setPreferredWidth(500);
				
				tcm.getColumn(3).setPreferredWidth(800);
				table.setFont(new Font("Arial",Font.BOLD,16));
				table.setBorder(new LineBorder(Color.BLACK,2));

				lblLeave=new JLabel("Leave Details",lblLeave.CENTER);
				lblLeave.setFont(new Font("Arial",Font.BOLD,22));
				lblLeave.setBounds(0,230,650,30);
				lblLeave.setBorder(new LineBorder(Color.BLACK,1));
				repaint();
	
				lblType=new JLabel("Leave Type",JLabel.CENTER);
				lblType.setFont(new Font("Arial",Font.BOLD,18));
				lblType.setBorder(new LineBorder(Color.BLACK,1));
				lblType.setBounds(0,260,216,30);
				add(lblType);

				lblTotal=new JLabel("Total Leave",JLabel.CENTER);
				lblTotal.setFont(new Font("Arial",Font.BOLD,18));
				lblTotal.setBorder(new LineBorder(Color.BLACK,1));				
				lblTotal.setBounds(216,260,216,30);
				add(lblTotal);

				lblReLeave=new JLabel("Balence Leave",JLabel.CENTER);
				lblReLeave.setFont(new Font("Arial",Font.BOLD,18));
				lblReLeave.setBorder(new LineBorder(Color.BLACK,1));
				lblReLeave.setBounds(432,260,218,30);
				add(lblReLeave);

				String col[]={"","",""};
				String rows[][]={{" Casual Leave","  15",balCasual+""},
					         {" Sick Leave","  12",balSick+""}};
				table1=new JTable(rows,col){
							private static final long serialVersionUID = 1L;

							public boolean isCellEditable(int row, int column) 
							{                
							                return false;               
							} };
				table1.setBounds(0,290,650,60);
				this.add(table1);
	
				table1.setRowHeight(30);
				tcm1=table1.getColumnModel();
				tcm1.getColumn(0).setPreferredWidth(75);
				table1.setFont(new Font("Arial",Font.BOLD,16));
				table1.setBorder(new LineBorder(Color.BLACK,2));

				lblIncentive=new JLabel("Incentive",JLabel.CENTER);
				lblIncentive.setFont(new Font("Arial",Font.BOLD,18));
				lblIncentive.setBorder(new LineBorder(Color.BLACK,1));
				lblIncentive.setBounds(0,380,218,30);
				add(lblIncentive);

				lblRs=new JLabel("Rs.",JLabel.CENTER);
				lblRs.setFont(new Font("Arial",Font.BOLD,18));
				lblRs.setBorder(new LineBorder(Color.BLACK,1));
				lblRs.setBounds(218,380,150,30);
				add(lblRs);

				String column[]={"",""};
				String rowscol[][]={{" Basic",basic+""},
				 	            {" HouseRentAllowance",hraAmt+""},
				                    {" DearenessAllowance",daAmt+""},
					            {" CityAllowance",caAmt+""},
					            {" MedicalAllowance",maAmt+""},
				                    {" EducationAllowance",eaAmt+""},
				                    {" TravellingAllowancse",taAmt+""},
				                    {" EntertainmentAllowance",entAmt+""}};
				table2=new JTable(rowscol,column){
					private static final long serialVersionUID = 1L;

					public boolean isCellEditable(int row, int column) 
					{                
					                return false;               
					} };
				table2.setBounds(0,410,368,240);
				this.add(table2);
	
				table2.setRowHeight(30);
				tcm2=table2.getColumnModel();
				tcm2.getColumn(0).setPreferredWidth(145);
				table2.setFont(new Font("Arial",Font.BOLD,16));
				table2.setBorder(new LineBorder(Color.BLACK,2));
	
				lblDeduction=new JLabel("Deduction",JLabel.CENTER);
				lblDeduction.setFont(new Font("Arial",Font.BOLD,18));
				lblDeduction.setBorder(new LineBorder(Color.BLACK,1));
				lblDeduction.setBounds(400,380,150,30);
				add(lblDeduction);

				lblAmt=new JLabel("Ammount",JLabel.CENTER);
				lblAmt.setFont(new Font("Arial",Font.BOLD,18));
				lblAmt.setBorder(new LineBorder(Color.BLACK,1));
				lblAmt.setBounds(550,380,100,30);
				add(lblAmt);
	
				String c[]={"",""};
				String r[][]={{" IncomeTax","0"},
					      {" ProvidendFund",pfAmt+""},
					      {" ProfessionalTax",ptAmt+""}};
				table3=new JTable(r,c){
					private static final long serialVersionUID = 1L;

					public boolean isCellEditable(int row, int column) 
					{                
					                return false;               
					} };
				table3.setBounds(400,410,250,90);
				this.add(table3);
		
				table3.setRowHeight(30);
				tcm3=table3.getColumnModel();
				tcm3.getColumn(0).setPreferredWidth(125);
				table3.setFont(new Font("Arial",Font.BOLD,16));
				table3.setBorder(new LineBorder(Color.BLACK,2));

				lbltotalInc=new JLabel("Total Incentive",JLabel.CENTER);
				lbltotalInc.setFont(new Font("Arial",Font.BOLD,18));
				lbltotalInc.setBorder(new LineBorder(Color.BLACK,1));
				lbltotalInc.setBounds(0,650,220,30);
				add(lbltotalInc);

				lblVal1=new JLabel(totalInc+"",JLabel.CENTER);
				lblVal1.setFont(new Font("Arial",Font.BOLD,18));
				lblVal1.setBorder(new LineBorder(Color.BLACK,1));
				lblVal1.setBounds(220,650,147,30);
				add(lblVal1);

				lbltotalDed=new JLabel("Total Deduction",JLabel.CENTER);
				lbltotalDed.setFont(new Font("Arial",Font.BOLD,18));
				lbltotalDed.setBorder(new LineBorder(Color.BLACK,1));
				lbltotalDed.setBounds(400,500,150,30);
				add(lbltotalDed);
			
				lblVal2=new JLabel(totalDed+"",JLabel.CENTER);
				lblVal2.setFont(new Font("Arial",Font.BOLD,18));
				lblVal2.setBorder(new LineBorder(Color.BLACK,1));
				lblVal2.setBounds(550,500,100,30);
				add(lblVal2);

				lblNetPay=new JLabel("NetPay",JLabel.CENTER);
				lblNetPay.setFont(new Font("Arial",Font.BOLD,18));
				lblNetPay.setBorder(new LineBorder(Color.BLACK,3));
				lblNetPay.setBounds(400,650,100,50);
				add(lblNetPay);
		

				lblNetval=new JLabel(NetAmt+"",JLabel.CENTER);
				lblNetval.setFont(new Font("Arial",Font.BOLD,18));
				lblNetval.setBorder(new LineBorder(Color.BLACK,1));
				lblNetval.setBounds(500,650,120,50);
				add(lblNetval);
				repaint();			
			}
			else
				JOptionPane.showMessageDialog((Component)null,"Employee Id not Found","Error",JOptionPane.ERROR_MESSAGE);
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			try
			{
				con.close();
				stmt.close();
			}
			catch(Exception ee)
			{
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
		if(e.getSource()==btnPrint)
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
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.sql.*;


class DemoSimple extends JFrame implements MouseListener,ActionListener
{
	static JTabbedPane jtp;
	static JScrollPane sp;
	static JPanel p1,RADPanel,marketingPanel,financePanel,productionPanel,hrPanel,gPanel;
	AdvanceLabel next,back;
	JTextField txtAddrs;
	JLabel lblAddrs;

	JPanel filterPanel;

	JButton btnSearch;

	MenuBar mb;
	Menu search;
	MenuItem filter,close;

	static JPanel resultPanel;


	SerializableVector v=new SerializableVector();

	FileInputStream fis;
	ObjectInputStream ois;

	Statement stmt;
	Connection con;
	ResultSet rs;

	JLabel lblId,lblName,lblDeptId,lblDesg,lblQf;
	JTextField txtId,txtName,txtQf;
	Choice chDeptId,chDesg;
	JButton btnFind,btnClear;

	DemoSimple()
	{
		super("My Computer");
		
		Container c=getContentPane();
		c.setLayout(null);

		mb=new MenuBar();
		search=new Menu("Search");
		filter=new MenuItem("Filter");
		close=new MenuItem("Close");

		btnSearch=new JButton(new ImageIcon("search.jpg"));
		btnSearch.setRolloverIcon(new ImageIcon("searchShine.jpg"));

		setMenuBar(mb);
		mb.add(search);
		search.add(filter);
		search.addSeparator();
		search.add(close);

		txtAddrs=new JTextField(20);
		lblAddrs=new JLabel("Search",JLabel.CENTER);
		
		c.add(txtAddrs);
		c.add(lblAddrs);
		c.add(btnSearch);
	
		txtAddrs.setBounds(180,60,1150,20);
		lblAddrs.setBounds(90,60,100,20);

		btnSearch.setBounds(1330,60,30,20);

		back=new AdvanceLabel("",new ImageIcon("back.png"));
		next=new AdvanceLabel("",new ImageIcon("next.png"));		
		
		c.add(back);
		c.add(next);

		back.addMouseListener(this);

		back.setBounds(0,0,60,60);
		next.setBounds(60,0,60,60);

		next.setRolloverIcon(new ImageIcon("nextShine.png"));
		back.setRolloverIcon(new ImageIcon("backShine.png"));

		next.setPressedIcon(new ImageIcon("nextPressed.png"));
		back.setPressedIcon(new ImageIcon("backPressed.png"));

		p1=new JPanel();
		p1.setLayout(new GridLayout(0,5,20,20));

		RADPanel=new JPanel();
		RADPanel.setLayout(new GridLayout(0,5,20,20));

		hrPanel=new JPanel();
		hrPanel.setLayout(new GridLayout(0,5,20,20));
	
		marketingPanel=new JPanel();
		marketingPanel.setLayout(new GridLayout(0,5,20,20));

		gPanel=new JPanel();
		gPanel.setLayout(new GridLayout(0,5,20,20));

		financePanel=new JPanel();
		financePanel.setLayout(new GridLayout(0,5,20,20));

		productionPanel=new JPanel();
		productionPanel.setLayout(new GridLayout(0,5,20,20));

		p1.setBackground(new Color(255,255,255));

		sp=new JScrollPane(p1,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		jtp=new JTabbedPane(JTabbedPane.LEFT);
		jtp.setBounds(0,80,1366,625);

		jtp.addTab("All Depts",sp);
		jtp.addTab("Research And Development",RADPanel);
		jtp.addTab("Marketing",marketingPanel);
		jtp.addTab("Finance",financePanel);
		jtp.addTab("Production",productionPanel);
		jtp.addTab("Human Resource",hrPanel);
		jtp.addTab("Geographical",gPanel);

		c.add(jtp);

		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			
			String url="Jdbc:Odbc:NayanDesale";
			con=DriverManager.getConnection(url,"","");

			System.out.println("Stored Data: 1");
			stmt=con.createStatement();
			System.out.println("Stored Data: 2");
			rs=stmt.executeQuery("Select * from EmplyeeDetails");
			System.out.println("Stored Data: 3");

			while(rs.next())
			{
				int id=rs.getInt("EmployeeId");
				String nm=rs.getString("EmployeeName");
				System.out.println("Stored Data: ");
				p1.add(new AdvanceLabel(nm+" - "+id,new ImageIcon("folder.jpg")));
				int d=rs.getInt("DeptID");

				if(d==11)
				RADPanel.add(new AdvanceLabel(nm+" - "+id,new ImageIcon("folder.jpg")));
				else if(d==12)
				financePanel.add(new AdvanceLabel(nm+" - "+id,new ImageIcon("folder.jpg")));
				else if(d==13)
				productionPanel.add(new AdvanceLabel(nm+" - "+id,new ImageIcon("folder.jpg")));
				else if(d==14)
				gPanel.add(new AdvanceLabel(nm+" - "+id,new ImageIcon("folder.jpg")));
				else if(d==15)
				hrPanel.add(new AdvanceLabel(nm+" - "+id,new ImageIcon("folder.jpg")));
				else if(d==17)
				marketingPanel.add(new AdvanceLabel(nm+" - "+id,new ImageIcon("folder.jpg")));
			}
			rs.close();
			con.close();
			stmt.close();

			btnSearch.addActionListener(this);
			filter.addActionListener(this);

		}
		catch(Exception ex1)
		{
			System.out.println(ex1.getMessage());
			try
			{
				rs.close();
				con.close();
				stmt.close();
			}catch(Exception sef){}
		}
	}
	public void mousePressed(MouseEvent e)
	{
	}
	public void mouseReleased(MouseEvent e)
	{
	}
	public void mouseEntered(MouseEvent e)
	{
	}
	public void mouseExited(MouseEvent e)
	{
	}
	public void mouseClicked(MouseEvent e)
	{
		if(e.getSource()==back)
		{
			if(jtp.getSelectedIndex()==0)
			{
				jtp.setComponentAt(0,p1);
				p1.repaint();
			}
			if(jtp.getSelectedIndex()==1)
			{
				jtp.setComponentAt(1,RADPanel);
				RADPanel.repaint();
			}
			if(jtp.getSelectedIndex()==2)
			{
				jtp.setComponentAt(2,marketingPanel);
				marketingPanel.repaint();
			}
			if(jtp.getSelectedIndex()==3)
			{
				jtp.setComponentAt(3,financePanel);
				financePanel.repaint();
			}
			if(jtp.getSelectedIndex()==4)
			{
				jtp.setComponentAt(4,productionPanel);
				productionPanel.repaint();
			}
			if(jtp.getSelectedIndex()==5)
			{
				jtp.setComponentAt(5,hrPanel);
				hrPanel.repaint();
			}
			if(jtp.getSelectedIndex()==6)
			{
				jtp.setComponentAt(6,gPanel);
				gPanel.repaint();
			}
		}
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==btnSearch)
		{
			if(jtp.getSelectedIndex()!=0)
			{
				JOptionPane.showConfirmDialog(null,"Please Select the First Tab ('All Depts')");
			}
			else
			{
			try
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				
				String url="Jdbc:Odbc:NayanDesale";
				con=DriverManager.getConnection(url,"","");

				System.out.println("Stored Data: 1");
				stmt=con.createStatement();
				System.out.println("Stored Data: 2");
				rs=stmt.executeQuery("Select * from EmplyeeDetails Where EmployeeName='"+txtAddrs.getText()+"'");				
				{
					resultPanel=new JPanel();
					resultPanel.setLayout(new FlowLayout(FlowLayout.LEFT,20,20));

					jtp.setComponentAt(0,resultPanel);
						
					System.out.println("Stored Data: 2");
					int count=0;
					while(rs.next())
					{
						count=1;

						int id=rs.getInt("EmployeeId");
						String nm=rs.getString("EmployeeName");
						
						resultPanel.add(new AdvanceLabel(nm+" - "+id,new ImageIcon("folder.jpg")));

					}
					if(count==0)
					JOptionPane.showConfirmDialog(null,txtAddrs.getText()+": Record Not Found ???");

					rs.close();
					con.close();
					stmt.close();
				}
			}
			catch(Exception ex1)
			{
				try
				{
					rs.close();
					con.close();
					stmt.close();
				}
				catch(Exception eed){}
				JOptionPane.showConfirmDialog(null,ex1.getMessage());
			}
			}
		}
		if(e.getSource()==filter)
		{
			jtp.setComponentAt(0,p1); 
			p1.repaint();

			JFrame filterWin=new JFrame("Filter");
			Container cn=filterWin.getContentPane();
			cn.setLayout(null);
			
			cn.setBackground(new Color(120,120,120));

			filterWin.setLocation(400,200);
			filterWin.setSize(400,350);

			filterWin.setVisible(true);
			filterWin.setResizable(false);

			lblId=new JLabel("Employee Id");
			lblName=new JLabel("Employee Name");
			lblDeptId=new JLabel("Dept Id");
			lblDesg=new JLabel("Designation");
			lblQf=new JLabel("Qualification");

			txtId=new JTextField(20);
			txtName=new JTextField(20);
			
			chDeptId=new Choice();
			chDeptId.add("11");	
			chDeptId.add("12");
			chDeptId.add("13");
			chDeptId.add("14");
			chDeptId.add("15");
			chDeptId.add("16");
			chDeptId.add("17");

			chDesg=new Choice();
			chDesg.add("Employee");
			chDesg.add("Manager");

			txtQf=new JTextField(20);

			btnFind=new JButton("Search");
			btnClear=new JButton("Clear");

			cn.add(lblId);
			cn.add(txtId);

			cn.add(lblName);
			cn.add(txtName);
	
			cn.add(lblDeptId);
			cn.add(chDeptId);

			cn.add(lblDesg);
			cn.add(chDesg);

			cn.add(lblQf);
			cn.add(txtQf);

			cn.add(btnFind);
			cn.add(btnClear);

			lblId.setBounds(30,30,100,20);
			txtId.setBounds(130,30,150,20);
	
			lblName.setBounds(30,70,100,20);
			txtName.setBounds(130,70,150,20);

			lblDeptId.setBounds(30,110,100,20);
			chDeptId.setBounds(130,110,150,20);

			lblDesg.setBounds(30,150,100,20);
			chDesg.setBounds(130,150,150,20);

			lblQf.setBounds(30,190,100,20);
			txtQf.setBounds(130,190,150,20);
			
			btnFind.setBounds(30,240,100,30);
			btnClear.setBounds(180,240,100,30);

			btnFind.addActionListener(this);
			btnClear.addActionListener(this);
		}
		if(e.getSource()==btnFind)
		{
			try
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				
				String url="Jdbc:Odbc:NayanDesale";
				con=DriverManager.getConnection(url,"","");

				System.out.println("Stored Data: 1");
				stmt=con.createStatement();
				System.out.println("Stored Data: 2");

				if(txtQf.getText().equals(""))
				rs=stmt.executeQuery("Select * from EmplyeeDetails Where EmployeeId="+txtId.getText()+" AND EmployeeName='"+txtName.getText()+"' AND DeptID="+chDeptId.getSelectedItem()+" AND Designation='"+chDesg.getSelectedItem()+"'");
				else
				rs=stmt.executeQuery("Select * from EmplyeeDetails Where EmployeeId="+txtId.getText()+" AND EmployeeName='"+txtName.getText()+"' AND DeptID="+chDeptId.getSelectedItem()+" AND Designation='"+chDesg.getSelectedItem()+"' AND Qualification='"+txtQf.getText()+"'");
				
				int count=0;
				
				while(rs.next())
				{
					resultPanel=new JPanel();
					resultPanel.setLayout(new FlowLayout(FlowLayout.LEFT,20,20));

					jtp.setComponentAt(0,resultPanel);

					String nm=rs.getString("EmployeeName");
					int id=rs.getInt("EmployeeId");

					resultPanel.add(new AdvanceLabel(nm+" - "+id,new ImageIcon("folder.jpg")));
					count=1;
				}
				if(count==0)
				JOptionPane.showConfirmDialog(null,txtId.getText()+": Record not Found ???");
				else
				JOptionPane.showConfirmDialog(null,txtId.getText()+": Record is Found !!! Now Close the Filter window and see the search result");
				
				rs.close();
				con.close();
				stmt.close();
			}
			catch(Exception exd)
			{
				try
				{
					rs.close();
					con.close();
					stmt.close();
				}catch(Exception ef){}
			}
		}
		if(e.getSource()==btnClear)
		{
			txtId.setText("");
			txtName.setText("");
			txtQf.setText("");
		}
	}
	public static void main(String args[])
	{
		DemoSimple s=new DemoSimple();
		s.setVisible(true);
		s.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
}
class AdvanceLabel extends JLabel implements MouseListener
{
	ImageIcon normalIcon,rolloverIcon,pressedIcon;
	JLabel lblName;

	Container parent;
	static JPanel subPanel;

	static String strId[];
	Incentive ic;
	Deduction dc;

	AdvanceLabel(String ln,ImageIcon icon)
	{
		super(ln,icon,0);
		normalIcon=icon;
	
		addMouseListener(this);

		rolloverIcon=new ImageIcon("folderShine.jpg");
		pressedIcon=new ImageIcon("folderPressed.jpg");
	}
	void setRolloverIcon(ImageIcon icon)
	{
		rolloverIcon=icon;
	}
	void setPressedIcon(ImageIcon icon)
	{
		pressedIcon=icon;
	}
	public void mousePressed(MouseEvent e)
	{
		if(e.getSource()==this)
		{
			setIcon(pressedIcon);
		}
	}
	public void mouseReleased(MouseEvent e)
	{
		if(e.getSource()==this)
		{
			setIcon(rolloverIcon);
		}
	}
	public void mouseEntered(MouseEvent e)
	{
		if(e.getSource()==this)
		{
			setIcon(rolloverIcon);
		}
	}
	public void mouseExited(MouseEvent e)
	{
		if(e.getSource()==this)
		{
			setIcon(normalIcon);
		}
	}
	public void mouseClicked(MouseEvent e)
	{
		AdvanceLabel al=(AdvanceLabel)e.getSource();
		if(al==this)
		{
			if(al.getText().equals("Incentives"))
			{
				ic=new Incentive();
				ic.setExtendedState(JFrame.MAXIMIZED_BOTH);
				ic.setVisible(true);
				ic.setup(Integer.parseInt(strId[1]));
			}
			else if(al.getText().equals("Deduction"))
			{
				dc=new Deduction(Integer.parseInt(strId[1]));
				dc.setExtendedState(JFrame.MAXIMIZED_BOTH);
				dc.setVisible(true);
			}	
			else
			{
				subPanel=new JPanel();
				subPanel.setLayout(new FlowLayout(FlowLayout.LEFT,30,30));

				strId=al.getText().split("- ");	
	
				AdvanceLabel lblIncentive=new AdvanceLabel("Incentives",new ImageIcon("file.png"));
				lblIncentive.setRolloverIcon(new ImageIcon("fileShine.png"));
				lblIncentive.setPressedIcon(new ImageIcon("filePressed.png"));

				AdvanceLabel lblDeduction=new AdvanceLabel("Deduction",new ImageIcon("file.png"));
				lblDeduction.setRolloverIcon(new ImageIcon("fileShine.png"));
				lblDeduction.setPressedIcon(new ImageIcon("filePressed.png"));

				subPanel.add(lblIncentive);
				subPanel.add(lblDeduction);
			
			if(DemoSimple.jtp.getSelectedIndex()==0)
			{
				DemoSimple.jtp.setComponentAt(0,subPanel);

				DemoSimple.jtp.setComponentAt(1,DemoSimple.RADPanel);
				DemoSimple.jtp.setComponentAt(2,DemoSimple.marketingPanel);
				DemoSimple.jtp.setComponentAt(3,DemoSimple.financePanel);
				DemoSimple.jtp.setComponentAt(4,DemoSimple.productionPanel);
				DemoSimple.jtp.setComponentAt(5,DemoSimple.hrPanel);
				DemoSimple.jtp.setComponentAt(6,DemoSimple.gPanel);
			}
			if(DemoSimple.jtp.getSelectedIndex()==1)
			{
				DemoSimple.jtp.setComponentAt(1,subPanel);

				DemoSimple.jtp.setComponentAt(0,DemoSimple.p1);
				DemoSimple.jtp.setComponentAt(2,DemoSimple.marketingPanel);
				DemoSimple.jtp.setComponentAt(3,DemoSimple.financePanel);
				DemoSimple.jtp.setComponentAt(4,DemoSimple.productionPanel);
				DemoSimple.jtp.setComponentAt(5,DemoSimple.hrPanel);
				DemoSimple.jtp.setComponentAt(6,DemoSimple.gPanel);
			}
			if(DemoSimple.jtp.getSelectedIndex()==2)
			{
				DemoSimple.jtp.setComponentAt(2,subPanel);

				DemoSimple.jtp.setComponentAt(0,DemoSimple.p1);
				DemoSimple.jtp.setComponentAt(1,DemoSimple.RADPanel);
				DemoSimple.jtp.setComponentAt(3,DemoSimple.financePanel);
				DemoSimple.jtp.setComponentAt(4,DemoSimple.productionPanel);
				DemoSimple.jtp.setComponentAt(5,DemoSimple.hrPanel);
				DemoSimple.jtp.setComponentAt(6,DemoSimple.gPanel);
			}
			if(DemoSimple.jtp.getSelectedIndex()==3)
			{
				DemoSimple.jtp.setComponentAt(3,subPanel);

				DemoSimple.jtp.setComponentAt(0,DemoSimple.p1);
				DemoSimple.jtp.setComponentAt(1,DemoSimple.RADPanel);
				DemoSimple.jtp.setComponentAt(2,DemoSimple.marketingPanel);
				DemoSimple.jtp.setComponentAt(4,DemoSimple.productionPanel);
				DemoSimple.jtp.setComponentAt(5,DemoSimple.hrPanel);
				DemoSimple.jtp.setComponentAt(6,DemoSimple.gPanel);
			}
			if(DemoSimple.jtp.getSelectedIndex()==4)
			{
				DemoSimple.jtp.setComponentAt(4,subPanel);

				DemoSimple.jtp.setComponentAt(0,DemoSimple.p1);
				DemoSimple.jtp.setComponentAt(1,DemoSimple.RADPanel);
				DemoSimple.jtp.setComponentAt(2,DemoSimple.marketingPanel);
				DemoSimple.jtp.setComponentAt(3,DemoSimple.financePanel);
				DemoSimple.jtp.setComponentAt(5,DemoSimple.hrPanel);
				DemoSimple.jtp.setComponentAt(6,DemoSimple.gPanel);
			}
			if(DemoSimple.jtp.getSelectedIndex()==5)
			{
				DemoSimple.jtp.setComponentAt(5,subPanel);

				DemoSimple.jtp.setComponentAt(0,DemoSimple.p1);
				DemoSimple.jtp.setComponentAt(1,DemoSimple.RADPanel);
				DemoSimple.jtp.setComponentAt(2,DemoSimple.marketingPanel);
				DemoSimple.jtp.setComponentAt(3,DemoSimple.financePanel);
				DemoSimple.jtp.setComponentAt(4,DemoSimple.productionPanel);
				DemoSimple.jtp.setComponentAt(6,DemoSimple.gPanel);
			}
			}
		}
	}
}
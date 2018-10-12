import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class RecycleBin extends JFrame implements ActionListener
{
	static JTabbedPane jtp;
	Container c;
	static JPanel p1,RADPanel,marketingPanel,financePanel,productionPanel,hrPanel,gPanel;
	Connection con;
	ResultSet rs;
	Statement stmt;

	static JPopupMenu pm=new JPopupMenu();
	static JMenuItem restore,delete;

	RecycleBin()
	{
		super("Recycle Bin");

		c=getContentPane();

		RADPanel=new JPanel();
		RADPanel.setLayout(new GridLayout(0,4,20,20));

		hrPanel=new JPanel();
		hrPanel.setLayout(new GridLayout(0,4,20,20));
	
		marketingPanel=new JPanel();
		marketingPanel.setLayout(new GridLayout(0,4,20,20));

		gPanel=new JPanel();
		gPanel.setLayout(new GridLayout(0,4,20,20));

		financePanel=new JPanel();
		financePanel.setLayout(new GridLayout(0,4,20,20));

		productionPanel=new JPanel();
		productionPanel.setLayout(new GridLayout(0,4,20,20));

		RADPanel.setBackground(Color.WHITE);
		hrPanel.setBackground(Color.WHITE);
		marketingPanel.setBackground(Color.WHITE);
		gPanel.setBackground(Color.WHITE);
		financePanel.setBackground(Color.WHITE);	
		productionPanel.setBackground(Color.WHITE);

		jtp=new JTabbedPane(JTabbedPane.LEFT);
		
		jtp.addTab("Research And Development",RADPanel);
		jtp.addTab("Marketing",marketingPanel);
		jtp.addTab("Finance",financePanel);
		jtp.addTab("Production",productionPanel);
		jtp.addTab("Human Resource",hrPanel);
		jtp.addTab("Geographical",gPanel);

		restore=new JMenuItem("Restore");
		delete=new JMenuItem("Delete");
		
		pm.add(restore);
		pm.add(delete);

		c.add(jtp);
	
		delete.addActionListener(this);
		restore.addActionListener(this);

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
				int d=rs.getInt("DeptID");

				if(rs.getBoolean("Bin"))
				{
					if(d==11)
					RADPanel.add(new AdvanceLabel(nm+" - "+id,new ImageIcon("deletedfolder.jpg")));
					else if(d==12)
					financePanel.add(new AdvanceLabel(nm+" - "+id,new ImageIcon("deletedfolder.jpg")));
					else if(d==13)
					productionPanel.add(new AdvanceLabel(nm+" - "+id,new ImageIcon("deletedfolder.jpg")));
					else if(d==14)
					gPanel.add(new AdvanceLabel(nm+" - "+id,new ImageIcon("deletedfolder.jpg")));
					else if(d==15)
					hrPanel.add(new AdvanceLabel(nm+" - "+id,new ImageIcon("deletedfolder.jpg")));
					else if(d==17)
					marketingPanel.add(new AdvanceLabel(nm+" - "+id,new ImageIcon("deletedfolder.jpg")));
					System.out.println("Entered");
				}
			}
			rs.close();
			con.close();
			stmt.close();
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
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==delete)
		{
			JLabel lbl=(JLabel)pm.getInvoker();
			String str[]=lbl.getText().split(" - ");
			int id=Integer.parseInt(str[1]);
			try
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					
				String url="Jdbc:Odbc:NayanDesale";
				con=DriverManager.getConnection(url,"","");

				System.out.println("Stored Data: 1");
				stmt=con.createStatement();
				System.out.println("Stored Data: 2");
				int r=stmt.executeUpdate("Delete from EmplyeeDetails where EmployeeId="+id);
				if(r>0)
					JOptionPane.showMessageDialog((Component)null,"Employee is deleted","Operation sucessfull",JOptionPane.INFORMATION_MESSAGE);	
				con.close();
				stmt.close();
				rs.close();
			}	
			catch(Exception ex){}
			finally
			{
				lbl.setVisible(false);
			}
			
		}
		if(e.getSource()==restore)
		{
			JLabel lbl=(JLabel)pm.getInvoker();
			String str[]=lbl.getText().split(" - ");
			int id=Integer.parseInt(str[1]);
			PreparedStatement pre;
			try
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					
				String url="Jdbc:Odbc:NayanDesale";
				con=DriverManager.getConnection(url,"","");

				System.out.println("Stored Data: 1");
				stmt=con.createStatement();
				System.out.println("Stored Data: 2");
				stmt.executeUpdate("Update EmplyeeDetails set Bin=False where EmployeeId="+id);
				System.out.println("Query is executed");		
				con.close();
				rs.close();
			}
			catch(Exception ee)
			{
				try
				{
					con.close();
					rs.close();
					
				}
				catch(Exception ew){}
			}
			finally
			{
				lbl.setVisible(false);
			}
			
		}
	}
	public static void main(String args[])
	{
		RecycleBin r=new RecycleBin();
		r.setExtendedState(MAXIMIZED_BOTH);
		r.setVisible(true);
	}
}
class AdvanceLabel extends JLabel implements MouseListener
{
	ImageIcon normalIcon,rolloverIcon,pressedIcon;
	JLabel lblName;

	Container parent;
	static JPanel subPanel;

	AdvanceLabel(String ln,ImageIcon icon)
	{
		super(ln,icon,0);
		normalIcon=icon;
	
		addMouseListener(this);

		rolloverIcon=new ImageIcon("deletedfolderShine.jpg");
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
		if(e.getSource()==this && e.getButton()==MouseEvent.BUTTON3)
		{
			RecycleBin.pm.show(this,e.getX(),e.getY());
		}
	}
}
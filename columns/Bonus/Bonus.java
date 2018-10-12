import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
class Bonus extends JFrame implements ActionListener
{
	JLabel lblHead,lblDesg,lblBonusType,lblBonusAmt,lblDate;
	JTextField txtBonusAmt,txtDate;
	JButton btnApply;
	Checkbox chEmployee,chManager;
	JComboBox choType;
	Container c;
	String name[]={"Diwali Bonus","Christmas Bonus"};
	Connection con;
	PreparedStatement pstmt;
	String url,query;
	int r;
	Bonus()
	{
		c=getContentPane();
		c.setLayout(null);	
		
		lblHead=new JLabel("Bonus");		
		lblHead.setFont(new Font("Times New Roman",Font.BOLD,26));

		lblDesg=new JLabel("Designation");
		lblDesg.setFont(new Font("Times New Roman",Font.BOLD,20));
		lblBonusType=new JLabel("Bonus Type");
		lblBonusType.setFont(new Font("Times New Roman",Font.BOLD,20));
		lblBonusAmt=new JLabel("Bonus Ammount");
		lblBonusAmt.setFont(new Font("Times New Roman",Font.BOLD,20));
		lblDate=new JLabel("Date");
		lblDate.setFont(new Font("Times New Roman",Font.BOLD,20));


		chEmployee=new Checkbox("Employee",false);
		chEmployee.setFont(new Font("Times New Roman",Font.BOLD,17));
		chManager=new Checkbox("Manager",false);
		chManager.setFont(new Font("Times New Roman",Font.BOLD,17));

		choType=new JComboBox(name);

		txtBonusAmt=new JTextField(20);

		btnApply=new JButton("Apply");

		txtDate=new JTextField(20);
		
		lblHead.setBounds(180,30,100,20);
		lblDesg.setBounds(50,100,100,20);
		lblBonusType.setBounds(50,150,100,20);
		lblBonusAmt.setBounds(50,200,150,20);
		lblDate.setBounds(50,250,150,20);

		chEmployee.setBounds(200,100,100,20);		
		chManager.setBounds(320,100,150,20);
		
		choType.setBounds(200,150,150,20);
		txtBonusAmt.setBounds(200,200,150,20);
		txtDate.setBounds(200,250,150,20);

		btnApply.setBounds(300,300,100,30);		
	
		add(lblHead);
		add(lblDesg);
		add(lblBonusType);
		add(lblBonusAmt);
		add(chEmployee);
		add(chManager);
		add(choType);
		add(txtBonusAmt);
		add(lblDate);
		add(txtDate);
		add(btnApply);

		btnApply.addActionListener(this);

		setBounds(430,200,450,400);	
		//----------------------Insert Logic------------------

	}
	public void actionPerformed(ActionEvent ex)
	{
		if(ex.getSource()==btnApply)
		{
			try
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				url="Jdbc:Odbc:Bonus";

				con=DriverManager.getConnection(url,"","");
				query="Insert into Bonus values(?,?,?,?)";
				pstmt=con.prepareStatement(query);
			
				if(chEmployee.getState())
					pstmt.setString(1,chEmployee.getLabel());
				else
					pstmt.setString(1,chManager.getLabel());
				pstmt.setString(2,choType.getSelectedItem()+"");
				pstmt.setInt(3,Integer.parseInt(txtBonusAmt.getText()));
				pstmt.setString(4,txtDate.getText());
				r=pstmt.executeUpdate();
				if(r>0)
				{
					System.out.println("Inserted");
					JOptionPane.showMessageDialog((Component)null,"Data is Inserted","Inserted",JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					System.out.println("Not Inserted");
					JOptionPane.showMessageDialog((Component)null,"Data is Inserted","Inserted",JOptionPane.ERROR_MESSAGE);
				}
				con.close();
			}
			
			catch(Exception e)
			{
				System.out.println(e.getMessage());
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
		Bonus b=new Bonus();
		b.setVisible(true);
		b.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}	
}
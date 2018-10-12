import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.io.*;

class DemoSimple1 extends JFrame
{
	JTabbedPane jtp;
	static JScrollPane sp;
	static JPanel p1;
	AdvanceLabel back,next;
	JTextField txtAddrs;
	JLabel lblAddrs;

	FileInputStream fis;
	ObjectInputStream ois;

	DemoSimple1()
	{
		super("My Computer");

		Container c=getContentPane();
		c.setLayout(null);

		txtAddrs=new JTextField(20);
		lblAddrs=new JLabel("Address",JLabel.LEFT);
		
		c.add(txtAddrs);
		c.add(lblAddrs);
	
		txtAddrs.setBounds(190,60,1150,20);
		lblAddrs.setBounds(90,60,100,20);

		back=new AdvanceLabel("",new ImageIcon("back.png"));
		next=new AdvanceLabel("",new ImageIcon("next.png"));		
		
		c.add(back);
		c.add(next);

		back.setBounds(0,0,60,60);
		next.setBounds(60,0,60,60);

		next.setRolloverIcon(new ImageIcon("nextShine.png"));
		back.setRolloverIcon(new ImageIcon("backShine.png"));

		next.setPressedIcon(new ImageIcon("nextPressed.png"));
		back.setPressedIcon(new ImageIcon("backPressed.png"));

		p1=new JPanel();
		p1.setLayout(new GridLayout(0,4,0,20));

		p1.setBackground(new Color(255,255,255));

		sp=new JScrollPane(p1,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		jtp=new JTabbedPane(JTabbedPane.LEFT);
		jtp.setBounds(0,80,1366,688);

		jtp.addTab("Employees",sp);
		jtp.addTab("Incentives",new JPanel());
		jtp.addTab("Deduction",new JPanel());
		jtp.addTab("Search",new JPanel());

		c.add(jtp);

		try
		{
			fis=new FileInputStream("folder.txt");
			ois=new ObjectInputStream(fis);

			Vector v=(Vector)ois.readObject();

			for(int i=0;i<v.size();i++)
			p1.add((AdvanceLabel)v.elementAt(i));

			fis.close();
			ois.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	public static void main(String args[])
	{
		DemoSimple1 d=new DemoSimple1();
		d.setVisible(true);
		d.setExtendedState(MAXIMIZED_BOTH);
	}
}
class AdvanceLabel extends JLabel implements MouseListener,Serializable
{
	ImageIcon normalIcon,rolloverIcon,pressedIcon;
	JLabel lblName;

	Container parent;
	JPanel subPanel;

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
	}
	void setPressedIcon(ImageIcon icon)
	{
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
		if(e.getSource()==this)
		{
			subPanel=new JPanel();
			DemoSimple.p1.setVisible(false);
		}
	}
}
import javax.swing.*;
import java.awt.*;

class Demo extends JFrame
{
	LoadingRing lr;
	JLabel lbl;
	JPanel p1=new JPanel();

	Demo()
	{
		Container c=getContentPane();

		c.setLayout(null);

		lr=new LoadingRing(c);

		lr.start();
	}
	public static void main(String args[])
	{
		Demo d=new Demo();
		d.setExtendedState(MAXIMIZED_BOTH);
		d.setVisible(true);
	}
}
class LoadingRing extends Thread
{
	JLabel lbl;
	JPanel p1=new JPanel();
	Container f;
	boolean stop=true;

	LoadingRing(Container fr)
	{
		f=fr;

		lbl=new JLabel();

		p1.setBounds(600,300,100,100);

		f.add(p1);

		p1.setLayout(null);

		p1.add(lbl);

		lbl.setBounds(0,0,100,100);

		System.out.println("Tested 5");

		lbl.setIcon(new ImageIcon("loading0.jpg"));
	}
	void stable(boolean stop)
	{
		this.stop=stop;
	}
	public void run()
	{
		int i=0;
		while(stop)
		{
			lbl.setIcon(new ImageIcon("loading"+i+".jpg"));
			i++;
			try
			{
				Thread.sleep(100);
			}catch(Exception ex){}
			
			if(i>3)
			i=0;
		}
	}
}
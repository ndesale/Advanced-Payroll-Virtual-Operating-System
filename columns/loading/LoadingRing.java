package loading;

import javax.swing.*;
import java.awt.*;

public class LoadingRing extends Thread
{
	JLabel lbl;
	JPanel p1=new JPanel();
	Container f;
	boolean stop=true;

	public LoadingRing(Container fr)
	{
		f=fr;

		lbl=new JLabel();

		p1.setBounds(600,300,100,100);

		f.add(p1);

		p1.setLayout(null);

		p1.add(lbl);

		lbl.setBounds(0,0,100,100);

		System.out.println("Tested 5");

		lbl.setIcon(new ImageIcon("icons\\loading0.jpg"));
	}
	public void stable(boolean stop)
	{
		this.stop=stop;
	}
	public void hide()
	{
		p1.setVisible(false);
	}
	public void show()
	{
		p1.setVisible(true);
	}
	public void run()
	{
		int i=0;
		while(stop)
		{
			lbl.setIcon(new ImageIcon("icons\\loading"+i+".jpg"));
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
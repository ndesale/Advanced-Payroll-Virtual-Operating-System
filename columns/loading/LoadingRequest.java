package loading;

import javax.swing.*;
import java.awt.*;

public class LoadingRequest extends Thread
{
	JLabel lbl;
	JPanel p1=new JPanel();
	Container f;
	boolean stop=true;

	public LoadingRequest(Container fr)
	{
		f=fr;

		lbl=new JLabel();

		p1.setBounds(0,0,60,35);

		f.add(p1);

		p1.setLayout(null);

		p1.add(lbl);

		lbl.setBounds(0,0,60,35);

		System.out.println("Tested 5");

		lbl.setIcon(new ImageIcon("icons\\request\\r1.jpg"));
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
	public void setBounds(int x,int y,int w,int h)
	{
		p1.setBounds(x,y,w,h);
	}
	public void run()
	{
		int i=1;
		while(stop)
		{
			lbl.setIcon(new ImageIcon("icons\\request\\r"+i+".jpg"));
			i++;
			try
			{
				Thread.sleep(150);
			}catch(Exception ex){}
			
			if(i>5)
			i=1;
		}
	}
}
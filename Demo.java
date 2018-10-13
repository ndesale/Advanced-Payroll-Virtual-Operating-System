import loading.*;
import javax.swing.*;
class Demo
{
	public static void main(String args[])
	{
		JFrame j=new JFrame();
		j.setExtendedState(JFrame.MAXIMIZED_BOTH);
		j.setLayout(null);
		j.setVisible(true);

		LoadingRequest lr=new LoadingRequest(j);
		lr.setBounds(50,50,60,35);
		lr.start();
	}
}
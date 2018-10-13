import javax.swing.*;
import java.awt.*;

class JFrameBackground
{
	JFrame j;
	JLabel l;

	JFrameBackground(JFrame e)
	{
		j=e;
	}
	void setWallpaper(String s)
	{
		l=new JLabel(new ImageIcon(s));
		l.setBounds(0,0,1366,768);
		j.add(l);
	}
}
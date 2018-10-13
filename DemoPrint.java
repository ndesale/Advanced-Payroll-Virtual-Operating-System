import java.awt.print.*;
import java.io.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

class DemoPrint extends JFrame implements ActionListener
{
	JLabel lbl1,lbl2,lbl3,lbl4;
	JTextArea ta;

	JButton btnPrint=new JButton("Print");
	JPanel p1=new JPanel();
	JScrollPane jsp;

	PrinterJob pj;
	PageFormat pf;

	DemoPrint()
	{
		super("Printing");

		ta=new JTextArea(20,20);
		ta.setLayout(new GridLayout(2,2));

		lbl1=new JLabel("RollNo:  ");
		lbl2=new JLabel("27");
		lbl3=new JLabel("Name  : ");
		lbl4=new JLabel("Nayan Desale");

		ta.add(lbl1);
		ta.add(lbl2);
		ta.add(lbl3);
		ta.add(lbl4);

		lbl1.setFont(new Font("Times New Roman",Font.BOLD,20));
		lbl2.setFont(new Font("Times New Roman",Font.BOLD,20));
		lbl3.setFont(new Font("Times New Roman",Font.BOLD,20));
		lbl4.setFont(new Font("Times New Roman",Font.BOLD,20));

		int vsb=ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
		int hsb=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;

		jsp=new JScrollPane(ta,vsb,hsb);

		p1.setLayout(new BorderLayout());
		p1.add(jsp);

		Container c=getContentPane();

		c.setLayout(null);
		c.add(p1);

		p1.setBounds(200,0,600,600);

		btnPrint.setBounds(0,0,100,30);
		c.add(btnPrint);

		btnPrint.addActionListener(this);

		ta.setEditable(false);
	}
	public void actionPerformed(ActionEvent e)
	{
		pj=PrinterJob.getPrinterJob();

		pf=pj.pageDialog(pj.defaultPage());

		pj.printDialog();
	}
	public static void main(String args[])
	{
		DemoPrint p=new DemoPrint();
		p.setVisible(true);
		p.setExtendedState(MAXIMIZED_BOTH);
	}
}
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class Menu extends JFrame implements ActionListener{
	
	String str;
	int totalprise=0;
	JLabel[] sum;
	JButton[] add;
	JButton[] minus;
	JLabel display;
	
	public Menu(String str) throws IOException
	{
		this.str=str;
		
		File dir=new File("E:\\shops\\"+str+"\\dishs");
		File[] files=dir.listFiles();
		
		sum=new JLabel[files.length];
		add=new JButton[files.length];
		minus=new JButton[files.length];
		
		this.setBounds(0,0,300,300);
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		this.setTitle(str);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		display=new JLabel(String.valueOf(totalprise));
		this.getContentPane().add(display,BorderLayout.NORTH);
		
		pay();
		creatmenu();
		for(int i=0;i<files.length;i++) 
		{
			add[i].addActionListener(this);
			minus[i].addActionListener(this);
		}
		this.setVisible(true);
	}
	
	public void pay() 
	{
		JMenuBar menu = new JMenuBar();
		this.setJMenuBar(menu);
		
		JMenu set=new JMenu("提交订单");
		menu.add(set);
	}
	
	public void creatmenu() throws IOException
	{
		File dir=new File("E:\\shops\\"+str+"\\dishs");
		File[] files=dir.listFiles();
		
		JPanel menus=new JPanel();
		menus.setLayout(new GridLayout(files.length,1));
		
		JScrollPane jsp=new JScrollPane(menus);
		this.getContentPane().add(jsp,BorderLayout.CENTER);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		if(files!=null) 
		{
			for(int i=0;i<files.length;i++) 
			{
				JPanel menu=new JPanel();
				menus.add(menu);
				menu.setLayout(new BorderLayout());
				menu.setBorder(BorderFactory.createTitledBorder("菜"+String.valueOf(i+1)));
				
				String str=files[i].getName();
				JLabel nameofdish=new JLabel(str.substring(0,str.length()-4));
				menu.add(nameofdish,BorderLayout.NORTH);
				
				FileReader prise=new FileReader(files[i].getAbsolutePath());
				BufferedReader bufprise=new BufferedReader(prise);
				
				
				JLabel sale=new JLabel();
				sale=new JLabel("价格："+bufprise.readLine());
				menu.add(sale,BorderLayout.CENTER);
				
				JPanel buy=new JPanel();
				buy.setLayout(new BorderLayout());
				menu.add(buy,BorderLayout.SOUTH);
				
				sum[i]=new JLabel("0");
				sum[i].setFont(new Font(" ",Font.BOLD,15));
				sum[i].setHorizontalAlignment(SwingConstants.CENTER);
				buy.add(sum[i],BorderLayout.CENTER);
				
				add[i]=new JButton("+");
				buy.add(add[i],BorderLayout.WEST);
				
				minus[i]=new JButton("-");
				buy.add(minus[i],BorderLayout.EAST);
				
				bufprise.close();
			}
		}
	}
	
	public void actionPerformed(ActionEvent e)
	{
		File dir=new File("E:\\shops\\"+str+"\\dishs");
		File[] files=dir.listFiles();
		for(int i=0;i<files.length;i++)
		{
			if(e.getSource()==add[i])
			{
				int tempi=Integer.parseInt(sum[i].getText());
				tempi++;
				String temps=String.valueOf(tempi);
				sum[i].setText(temps);
				
				try {
					FileReader prise = new FileReader(files[i]);
					BufferedReader buf=new BufferedReader(prise);
					totalprise+=Integer.parseInt(buf.readLine());
					display.setText(String.valueOf(totalprise));
				} 
				catch (FileNotFoundException e1){e1.printStackTrace();}
				catch (NumberFormatException e1){e1.printStackTrace();} 
				catch (IOException e1){e1.printStackTrace();}
			}
			if((e.getSource()==minus[i])&&(Integer.parseInt(sum[i].getText())>=1))
			{
				int tempi=Integer.parseInt(sum[i].getText());
				tempi--;
				String temps=String.valueOf(tempi);
				sum[i].setText(temps);
				
				try {
					FileReader prise = new FileReader(files[i]);
					BufferedReader buf=new BufferedReader(prise);
					totalprise-=Integer.parseInt(buf.readLine());
					display.setText(String.valueOf(totalprise));
				} 
				catch (FileNotFoundException e1){e1.printStackTrace();}
				catch (NumberFormatException e1){e1.printStackTrace();} 
				catch (IOException e1){e1.printStackTrace();}
			}
		}
	}
}

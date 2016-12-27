import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

public class BuyerInterface extends JFrame implements ActionListener{
	
	JButton[] entershop;
	
	public BuyerInterface() throws IOException
	{
		File dir=new File("E:\\shops");
		File[] files=dir.listFiles();
		entershop=new JButton[files.length];
		
		this.setBounds(0,0,350,300);
		this.setResizable(false);
		this.setTitle("您好，尊敬的客户");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		change();
		creatshop();
		for(int i=0;i<files.length;i++)
		{
			entershop[i].addActionListener(this);
		}
		this.setVisible(true);
	}
	
	public void change() 
	{
		JMenuBar menu = new JMenuBar();
		this.setJMenuBar(menu);
		
		JMenu set=new JMenu("设置");
		menu.add(set);
		
		JMenuItem changname=new JMenuItem("修改名称");
		set.add(changname);
		JMenuItem changpassword=new JMenuItem("修改密码");
		set.add(changpassword);
	}
	
	public void creatshop() throws IOException
	{
		File dir=new File("E:\\shops");
		File[] files=dir.listFiles();
		
		JPanel shops=new JPanel();
		shops.setLayout(new GridLayout(files.length,1));
		
		JScrollPane jsp=new JScrollPane(shops);
		this.getContentPane().add(jsp);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		if(files!=null) 
		{
			for(int i=0;i<files.length;i++) 
			{
				
				JPanel shop=new JPanel();
				shops.add(shop);
				shop.setLayout(new BorderLayout());
				shop.setBorder(BorderFactory.createTitledBorder("店家"+String.valueOf(i+1)));
				
				FileReader name=new FileReader(files[i].getAbsolutePath()+"\\name.txt");
				BufferedReader bufname=new BufferedReader(name);
				
				ImageIcon picture=new ImageIcon(files[i].getAbsolutePath()+"\\"+"picture.jpg");
				JLabel showpicture=new JLabel(picture);
				shop.add(showpicture,BorderLayout.WEST);
				
				entershop[i]=new JButton(bufname.readLine());
				shop.add(entershop[i],BorderLayout.CENTER);
				
				FileReader number=new FileReader(files[i].getAbsolutePath()+"\\"+"sale.txt");
				BufferedReader bufnumber=new BufferedReader(number);
				
				JLabel sale=new JLabel("月销售："+bufnumber.readLine());
				shop.add(sale,BorderLayout.SOUTH);
				
				bufname.close();
				bufnumber.close();
			}
		}
	}


	public void actionPerformed(ActionEvent e)
	{
		File dir=new File("E:\\shops");
		File[] files=dir.listFiles();
		for(int i=0;i<files.length;i++)
		{
			if(e.getSource()==entershop[i])
			{
				try 
				{
					Menu menu=new Menu(files[i].getName());
				} 
				catch (IOException e1) {}
			}
		}
		
	}
}

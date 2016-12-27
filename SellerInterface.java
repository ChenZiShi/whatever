import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class SellerInterface extends JFrame implements ActionListener{

	String str;
	JButton[] delete;
	JButton[] changeprise;
	JTextField[] prise;
	JMenuItem addnewdish;
	
	public SellerInterface(String str) throws IOException
	{
		File dir=new File("E:\\shops\\"+str+"\\dishs");
		File[] files=dir.listFiles();
		
		this.str=str;
		delete=new JButton[files.length];
		changeprise=new JButton[files.length];
		prise=new JTextField[files.length];
		
		this.setBounds(0,0,350,250);
		this.setResizable(false);
		this.setTitle("您好，尊敬的店家");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		change();
		creatmenu();
		
		for(int i=0;i<files.length;i++)
		{
			changeprise[i].addActionListener(this);
			delete[i].addActionListener(this);
		}
			
		this.setVisible(true);
	}
	
	public void change() 
	{
		JMenuBar menu = new JMenuBar();
		this.setJMenuBar(menu);
		
		JMenu set=new JMenu("设置");
		menu.add(set);
		
		JMenuItem changpassword=new JMenuItem("修改密码");
		set.add(changpassword);
		addnewdish=new JMenuItem("添加新菜");
		set.add(addnewdish);
		addnewdish.addActionListener(this);
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
				
				JPanel action=new JPanel();
				action.setLayout(new BorderLayout());
				menu.add(action,BorderLayout.SOUTH);
				
				changeprise[i]=new JButton("改价");
				action.add(changeprise[i],BorderLayout.WEST);
				
				delete[i]=new JButton("删除");
				action.add(delete[i],BorderLayout.EAST);
				
				FileReader showprise=new FileReader(files[i].getAbsolutePath());
				BufferedReader bufprise=new BufferedReader(showprise);
				
				prise[i]=new JTextField(bufprise.readLine());
				action.add(prise[i], BorderLayout.CENTER);
				
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
			if(e.getSource()==changeprise[i])
			{
				File file=new File(files[i].getAbsolutePath());
				try 
				{
					FileWriter fw = new FileWriter(file,false);
					fw.write(prise[i].getText());
					fw.close();
				} 
				catch (IOException e1){e1.printStackTrace();}
			}
			if(e.getSource()==delete[i])
			{
				File file=new File(files[i].getAbsolutePath());
				file.delete();
				dispose();
				try 
				{
					SellerInterface sellerInterface=new SellerInterface(str);
				} 
				catch (IOException e1){e1.printStackTrace();}
			}
		}
		if(e.getSource()==addnewdish)
		{
			AddDish adddish=new AddDish(str);
			dispose();
		}
	}
	
}

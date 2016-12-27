import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class LoginInterface extends JFrame implements ActionListener{

	JRadioButton buyer;
	JRadioButton seller;
	
	JButton login;
	JButton quit;
	
	JTextField entername;
	JPasswordField enterpassword;
	
	public LoginInterface()
	{
		this.setBounds(0,0,325,175);
		this.setResizable(false);
		this.setTitle("»¶Ó­£¬ÇëµÇÂ¼");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		enterzone();
		choosezone();
		loginzone();
		login.addActionListener(this);
		quit.addActionListener(this);
		this.setVisible(true);
	}
	
	public void enterzone()
	{
		JPanel loginzone=new JPanel();
		loginzone.setLayout(new BorderLayout());
		this.getContentPane().add(loginzone,BorderLayout.NORTH);
		
		JPanel namezone=new JPanel();
		JPanel passwordzone=new JPanel();
		loginzone.add(namezone,BorderLayout.NORTH);
		loginzone.add(passwordzone,BorderLayout.SOUTH);
		
		JLabel name=new JLabel("ÕËºÅ£º");
		entername=new JTextField(15);
		namezone.add(name,BorderLayout.WEST);
		namezone.add(entername,BorderLayout.EAST);
		
		JLabel password=new JLabel("ÃÜÂë£º");
		enterpassword=new JPasswordField(15);
		passwordzone.add(password,BorderLayout.WEST);
		passwordzone.add(enterpassword,BorderLayout.EAST);
	}
	
	public void choosezone()
	{
		JPanel choosezone=new JPanel();
		this.getContentPane().add(choosezone,BorderLayout.CENTER);
		
		buyer=new JRadioButton("¿Í»§",true);
		seller=new JRadioButton("µê¼Ò");
		ButtonGroup choose=new ButtonGroup();
		choose.add(buyer);
		choose.add(seller);
		choosezone.add(buyer);
		choosezone.add(seller);
		
	}
	
	public void loginzone()
	{
		JPanel loginzone=new JPanel();
		this.getContentPane().add(loginzone,BorderLayout.SOUTH);
		
		login=new JButton("µÇÂ¼");
		quit=new JButton("ÍË³ö");
		loginzone.add(login);
		loginzone.add(quit);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==login)
		{
			if(buyer.isSelected())
			{
				File file = new File("E:\\users\\"+entername.getText());
				if(file.exists())
				{
					try
					{
						FileReader password=new FileReader("E:\\users\\"+entername.getText()+"\\password.txt");
						BufferedReader buf=new BufferedReader(password);

						if(buf.readLine().equals(String.valueOf(enterpassword.getPassword())))
						{
							BuyerInterface shopinterface=new BuyerInterface();
						}
						buf.close();
						dispose();
					}
					catch(FileNotFoundException e1){e1.printStackTrace();} 
					catch(IOException e1){e1.printStackTrace();}
				}
			}
			if(seller.isSelected())
			{
				File file = new File("E:\\shops\\"+entername.getText());
				if(file.exists())
				{
					try
					{
						FileReader password=new FileReader("E:\\shops\\"+entername.getText()+"\\password.txt");
						BufferedReader buf=new BufferedReader(password);

						if(buf.readLine().equals(String.valueOf(enterpassword.getPassword())))
						{
							SellerInterface sellerInterface=new SellerInterface(entername.getText());
						}
						buf.close();
						dispose();
					}
					catch(FileNotFoundException e1){e1.printStackTrace();} 
					catch(IOException e1){e1.printStackTrace();}
				}
				else System.out.println("ÕËºÅ²»´æÔÚ");
			}
		}
		if(e.getSource()==quit)
		{
			System.exit(0);
		}
	}
	
}

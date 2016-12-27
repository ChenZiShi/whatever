import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class AddDish extends JFrame implements ActionListener{
	
	String str;
	JTextField entername;
	JTextField enterprise;
	JButton carryout;
	
	public AddDish(String str)
	{
		this.str=str;
		this.setBounds(0,0,400,75);
		this.setResizable(false);
		this.setTitle("新菜添加中……");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		this.setLayout(new BorderLayout());
		
		JPanel jp=new JPanel();
		jp.setLayout(new BorderLayout());
		this.getContentPane().add(jp,BorderLayout.WEST);
		
		JPanel name=new JPanel();
		jp.add(name,BorderLayout.NORTH);
		JLabel tips1=new JLabel("菜名：");
		name.add(tips1,BorderLayout.WEST);
		entername=new JTextField("请在此输入菜名");
		name.add(entername,BorderLayout.EAST);
		
		JPanel prise=new JPanel();
		jp.add(name,BorderLayout.SOUTH);
		JLabel tips2=new JLabel("价格：");
		name.add(tips2,BorderLayout.WEST);
		enterprise=new JTextField("请在此输入菜价");
		name.add(enterprise,BorderLayout.EAST);
		
		carryout=new JButton("添加");
		this.getContentPane().add(carryout, BorderLayout.EAST);
		carryout.addActionListener(this);
		
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==carryout)
		{
			File file = new File("E:\\shops\\"+str+"\\dishs\\"+entername.getText()+".txt");
			if(!file.exists())
			{
				try 
				{
					file.createNewFile();
					FileWriter fw = new FileWriter(file,true);
					fw.write(enterprise.getText());
					fw.close();
					SellerInterface sellerInterface=new SellerInterface(str);
				} 
				catch (IOException e1){e1.printStackTrace();}
			}
		}
	}
}

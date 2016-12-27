import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

public class FinalList extends JFrame implements ActionListener{
	
	String str;
	JButton yes;
	
	public FinalList(String str) throws IOException
	{
		this.str=str;
		this.setBounds(0,0,400,200);
		this.setResizable(false);
		this.setTitle("订单确认中……");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		creatmenu();
		yes=new JButton("提交");
		this.getContentPane().add(yes,BorderLayout.SOUTH);
		yes.addActionListener(this);
		this.setVisible(true);
	}
	
	public void creatmenu() throws IOException
	{
		File dir=new File("D:\\common\\"+str);
		File[] files=dir.listFiles();
		
		JPanel menus=new JPanel();
		menus.setLayout(new GridLayout(files.length,1));
		
		JScrollPane jsp=new JScrollPane(menus);
		this.getContentPane().add(jsp,BorderLayout.CENTER);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		for(int i=0;i<files.length;i++) 
		{
			JPanel menu=new JPanel();
			menus.add(menu);
			menu.setBorder(BorderFactory.createTitledBorder("项目"+String.valueOf(i+1)));
			String string=files[i].getName();
			FileReader fr=new FileReader(files[i]);
			BufferedReader buf=new BufferedReader(fr);
			JLabel show=new JLabel(string.substring(0,string.length()-4)+" * "+buf.readLine());
			fr.close();
			menu.add(show);
		}
	}
	
	 private void deleteFile(File file)
	 {  
		 if (file.exists())
		 {
			 if (file.isFile()){file.delete();} 
			 else if (file.isDirectory())
			 {  
				 File[] files = file.listFiles();
		         for (int i = 0;i < files.length;i ++){this.deleteFile(files[i]);}  
		         file.delete();
		     }  	 
		 }
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==yes)
		{
			dispose();
			File file1=new File("D:\\common\\"+str);
			deleteFile(file1);
			File file2=new File("D:\\shops\\"+str+"\\sale.txt");
			try 
			{
				FileReader fr=new FileReader(file2);
				BufferedReader buf=new BufferedReader(fr);
				int temp=Integer.parseInt(buf.readLine())+1;
				buf.close();
				
				FileWriter fw=new FileWriter(file2,false);
				fw.write(String.valueOf(temp));
				fw.close();
			} 
			catch (FileNotFoundException e1) {e1.printStackTrace();} 
			catch (NumberFormatException e1) {e1.printStackTrace();} 
			catch (IOException e1) {e1.printStackTrace();}
		}
	}
}

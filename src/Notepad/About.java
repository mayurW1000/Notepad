package Notepad;

import java.awt.Font;

import javax.swing.*;

public class About extends JFrame {
	
	About(){
		setBounds(100,100,500,400);
		setTitle("About Notepad Application");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ImageIcon icon=new ImageIcon(getClass().getResource("notepad icon.jfif"));
		setIconImage(icon.getImage());
		setLayout(null);
		
		JLabel textlabel=new JLabel("<html>Welcome to Notepad !<br>Notepad is simple text editor for Microsoft windows and a basic text-editing program which enables computer users to create documents.<br>All rights reserved@2022</html> ");
		textlabel.setBounds(60,0,400,500);
		textlabel.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,12));
		add(textlabel);
		
	}
	public static void main(String []args) {
		new About().setVisible(true);
	}
}

package Notepad;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main extends JFrame implements ActionListener{
	
	JMenuBar menubar = new JMenuBar();
	JMenu file = new JMenu("File");
	JMenu Edit = new JMenu("Edit");
	JMenu Help = new JMenu("Help");
	JMenuItem newfile = new JMenuItem("New");
	JMenuItem openfile = new JMenuItem("Open");
	JMenuItem savefile = new JMenuItem("Save");
	JMenuItem print = new JMenuItem("Print");
	JMenuItem exit = new JMenuItem("Exit");
	
	JMenuItem copy=new JMenuItem("Copy");
	JMenuItem cut=new JMenuItem("Cut");
	JMenuItem paste=new JMenuItem("Paste");
	JMenuItem selectall=new JMenuItem("Select all");
	JMenuItem about=new JMenuItem("About");
	JTextArea area=new JTextArea();
	
	Main(){
		setTitle("Notepad Application");
		setBounds(100,100,800,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon icon=new ImageIcon(getClass().getResource("notepad icon.jfif"));
		setIconImage(icon.getImage());
		setJMenuBar(menubar);
		menubar.add(file);
		menubar.add(Edit);
		menubar.add(Help);
		file.add(newfile);
		file.add(openfile);
		file.add(savefile);
		file.add(print);
		file.add(exit);
		
		Edit.add(cut);
		Edit.add(copy);
		Edit.add(paste);
		Edit.add(selectall);
		
		Help.add(about);
		
		JScrollPane scrollpane = new JScrollPane(area);
		scrollpane.setBorder(BorderFactory.createEmptyBorder());
		add(scrollpane);
		area.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,16));
		
		newfile.addActionListener(this);
		openfile.addActionListener(this);
		savefile.addActionListener(this);
		print.addActionListener(this);
		exit.addActionListener(this);
		copy.addActionListener(this);
		cut.addActionListener(this);
		paste.addActionListener(this);
		selectall.addActionListener(this);
		about.addActionListener(this);
		
		newfile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,KeyEvent.CTRL_DOWN_MASK));
		openfile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,KeyEvent.CTRL_DOWN_MASK));
		savefile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,KeyEvent.CTRL_DOWN_MASK));
		print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,KeyEvent.CTRL_DOWN_MASK));
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,KeyEvent.CTRL_DOWN_MASK));
		cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,KeyEvent.CTRL_DOWN_MASK));
		copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,KeyEvent.CTRL_DOWN_MASK));
		paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,KeyEvent.CTRL_DOWN_MASK));
		selectall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,KeyEvent.CTRL_DOWN_MASK));
		about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J,KeyEvent.CTRL_DOWN_MASK));
		
	}

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		new Main().setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equalsIgnoreCase("New")) {
			area.setText(null);
		}else if(e.getActionCommand().equalsIgnoreCase("Open")) {
			JFileChooser filechooser = new JFileChooser();
			FileNameExtensionFilter textFilter = new FileNameExtensionFilter ("Only Text Files(.txt)","txt");
			filechooser.setAcceptAllFileFilterUsed(false);
			filechooser.addChoosableFileFilter(textFilter);
			
			int action=filechooser.showOpenDialog(null);
			if(action!=JFileChooser.APPROVE_OPTION) {
				return;
			}else {
				try {
					BufferedReader reader = new BufferedReader(new FileReader(filechooser.getSelectedFile()));
					area.read(reader, null);
					}catch(IOException ex) {
						ex.printStackTrace();
					}
			}
		
		}else if(e.getActionCommand().equalsIgnoreCase("Save")) {
			
			JFileChooser filechooser = new JFileChooser();
			FileNameExtensionFilter textFilter = new FileNameExtensionFilter ("Only Text Files(.txt)","txt");
			filechooser.setAcceptAllFileFilterUsed(false);
			filechooser.addChoosableFileFilter(textFilter);
			int action = filechooser.showSaveDialog(null);
			if(action!=JFileChooser.APPROVE_OPTION) {
				return;
			}else {
				String filename=filechooser.getSelectedFile().getAbsolutePath().toString();
				if(!filename.contains(".txt")) {
					filename+=filename+".txt";
				}
				try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
				area.write(writer);
				}catch(IOException ex) {
					ex.printStackTrace();
				}
			}
			
		}else if(e.getActionCommand().equalsIgnoreCase("Exit")) {
			System.exit(0);
			
		}else if(e.getActionCommand().equalsIgnoreCase("Print")) {
			
			try {
				area.print();
			} catch (PrinterException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}else if(e.getActionCommand().equalsIgnoreCase("Cut")) {
			area.cut();
			
		}else if(e.getActionCommand().equalsIgnoreCase("Copy")) {
			area.copy();
			
		}else if(e.getActionCommand().equalsIgnoreCase("Paste")) {
			area.paste();
			
		}else if(e.getActionCommand().equalsIgnoreCase("Select All")) {
			area.selectAll();
		}else if(e.getActionCommand().equalsIgnoreCase("About")) {
			new About().setVisible(true);
		}
	}

}

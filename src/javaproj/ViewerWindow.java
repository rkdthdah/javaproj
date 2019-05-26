package javaproj;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ViewerWindow extends JFrame {
	JMenuBar menuBar;
	JMenu menu;
	JMenuItem open, save, exit;
	
	public ViewerWindow() {
		setTitle("C++ Å¬·¡½º Viewer");
		setSize(1200,800);
		menuBar = new JMenuBar();
		menu = new JMenu("File");
		open = new JMenuItem("Open");
		save = new JMenuItem("Save");
		exit = new JMenuItem("Exit");
		menu.add(open);
		menu.add(save);
		menu.addSeparator();
		menu.add(exit);
		menuBar.add(menu);
		
		setJMenuBar(menuBar);
		setVisible(true);
	}
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ViewerWindow f = new ViewerWindow();

	}

}

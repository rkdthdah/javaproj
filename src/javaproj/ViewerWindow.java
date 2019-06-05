package javaproj;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ViewerWindow extends JFrame {
	
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem open, save, exit;
	
	
	ViewerWindow() {
		setTitle("C++ Å¬·¡½º Viewer");
		setSize(900,700);
		createMenu();
		
		setVisible(true);
	}
	
	void createMenu() {
		
		menuBar = new JMenuBar();
		menu = new JMenu("File");
		open = new JMenuItem("Open");
		save = new JMenuItem("Save");
		exit = new JMenuItem("Exit");
		menu.add(open);
		menu.add(save);
		menu.addSeparator();
		menu.add(exit);
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object source = e.getSource();
				if (source == exit) {
					System.exit(0);
				}
			}
		});
		menuBar.add(menu);
		
		setJMenuBar(menuBar);
	}
	
	
	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ViewerWindow();

	}
	*/

}

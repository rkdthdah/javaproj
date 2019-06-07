package javaproj;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ViewerWindow extends JFrame {
	
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem open, save, exit;
	private TreeWindow treePanel;
	//private ContentWindow contentPanel;
	static ContentWindow contentPanel = new ContentWindow();
	private CardMethodUse use;
	
	
	
	public ViewerWindow() {
		setTitle("C++ Å¬·¡½º Viewer");
		setSize(900,700);
		setLayout(null);
		createMenu();
		showTree();
		showContent();
		
		
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
	
	void showTree() {
		treePanel = new TreeWindow();
		treePanel.setLocation(0,0);
		treePanel.setSize(250,400);
		add(treePanel);
	}
	
	void showContent() {
		//contentPanel = new ContentWindow();
		contentPanel.setLocation(240,0);
		contentPanel.setSize(650,700);
		add(contentPanel);
		
	}
	
	void showMethodUse() {
		use = contentPanel.u;
		use.setLocation(0,500);
		use.setSize(200,200);
		add(use);
	}
	
	public ContentWindow getContentWindow() {
		return contentPanel;
	}

}

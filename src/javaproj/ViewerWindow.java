package javaproj;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// GUI�� �ֻ��� �����̳ʸ� �����ϴ� Ŭ���� 
public class ViewerWindow extends JFrame {
	
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem open, save, exit;
	private TreeWindow treePanel;
	static ContentWindow contentPanel = new ContentWindow();
	static UseWindow use = new UseWindow();
	
	// ViewerWindow(JFrame) ������
	public ViewerWindow() {
		setTitle("C++ Ŭ���� Viewer");
		setSize(900,700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		createMenu();
		showTree();
		showContent();
		showMethodUse();	
		setVisible(true);
	}
	
	// �����ӿ� �޴��� �����ϴ� �޼ҵ�
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
	
	// Ʈ���� �����ִ� �г��� �����ӿ� �߰��ϴ� �޼ҵ�
	void showTree() {
		treePanel = new TreeWindow();
		treePanel.setLocation(0,0);
		treePanel.setSize(250,400);
		add(treePanel);
	}
	
	// ������ �����ִ� �г��� �����ӿ� �߰��ϴ� �޼ҵ�
	void showContent() {
		contentPanel.setLocation(240,0);
		contentPanel.setSize(650,700);
		add(contentPanel);
	}
	
	// �޼ҵ尡 ����ϴ� ������ ����� ����ϴ� �г��� �����ӿ� �߰��ϴ� �޼ҵ�
	void showMethodUse() {
		use.setLocation(8,410);
		use.setSize(233,200);
		add(use);	
	}
	
}

package javaproj;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// GUI의 최상위 컨테이너를 생성하는 클래스 
public class ViewerWindow extends JFrame {
	
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem open, save, exit;
	private TreeWindow treePanel;
	static ContentWindow contentPanel = new ContentWindow();
	static UseWindow use = new UseWindow();
	
	// ViewerWindow(JFrame) 생성자
	public ViewerWindow() {
		setTitle("C++ 클래스 Viewer");
		setSize(900,700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		createMenu();
		showTree();
		showContent();
		showMethodUse();	
		setVisible(true);
	}
	
	// 프레임에 메뉴를 설정하는 메소드
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
	
	// 트리를 보여주는 패널을 프레임에 추가하는 메소드
	void showTree() {
		treePanel = new TreeWindow();
		treePanel.setLocation(0,0);
		treePanel.setSize(250,400);
		add(treePanel);
	}
	
	// 내용을 보여주는 패널을 프레임에 추가하는 메소드
	void showContent() {
		contentPanel.setLocation(240,0);
		contentPanel.setSize(650,700);
		add(contentPanel);
	}
	
	// 메소드가 사용하는 변수의 목록을 출력하는 패널을 프레임에 추가하는 메소드
	void showMethodUse() {
		use.setLocation(8,410);
		use.setSize(233,200);
		add(use);	
	}
	
}

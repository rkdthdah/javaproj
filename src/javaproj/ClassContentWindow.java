package javaproj;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.TableModelListener;
import java.util.*;

public class ClassContentWindow extends JFrame {
	
	private JTable table;
	private JScrollPane contentScroll;
	private String[] columnType = {"Name", "Type", "Access"};
	private ArrayList<Object[]> data;
	
	public ClassContentWindow() {
		
	}
	

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ClassContentWindow();
	}

}
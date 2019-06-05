package javaproj;

import java.util.*;
import javax.swing.tree.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;

class ClassTreeModel implements TreeModel {
	protected ClassInfo classinfo;
	
	public ClassTreeModel(ClassInfo c) {
		this.classinfo = c;
	}
	
	public Object getChild(Object parent, int index) {
		if(parent instanceof ClassInfo) {
			ClassInfo c = (ClassInfo) parent;
			return c.getInfo(index);
		}
		return null;
	}
	
	public int getChildCount(Object parent) {
		if(parent instanceof ClassInfo) {
			ClassInfo c = (ClassInfo) parent;
			return  c.sizeInfo();
		}
		return 0;
	}
	
	public int getIndexOfChild(Object parent, Object child) {
		if(child instanceof MethodInfo) {
			ClassInfo c = (ClassInfo) parent;
			return c.getIndexOfMethod((MethodInfo)child);
		}
		else if(child instanceof VariableInfo) {
			ClassInfo c = (ClassInfo) parent;
			return c.getIndexOfVariable((VariableInfo)child);
		}
		return -1;
	}
	
	public Object getRoot() {
		return classinfo;
	}
	
	public boolean isLeaf(Object node) {
		if(node instanceof ClassInfo) {
			return false;
		}
		return true;
	}
	
	public void addTreeModelListener(TreeModelListener I) {
		
	}
	
	public void removeTreeModelListener(TreeModelListener I) {
		
	}
	
	public void valueForPathChanged(TreePath path, Object newValue) {
		
	}
}


public class TreeWindow extends JSplitPane{
	protected JTree tree;
	// protected CardLayout card;
	protected JTextArea display;
	
	public TreeWindow() {
		super(HORIZONTAL_SPLIT);
		ClassInfo c = (ClassInfo) Main.t.getClassInfo(); 
		ClassTreeModel model = new ClassTreeModel(c);
		tree = new JTree(model);
		
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				Object o = e.getPath().getLastPathComponent();
				if(o instanceof MethodInfo) {
					String met = "MethodInfo입니다\n";
					display.append(met);
				}
				else if(o instanceof VariableInfo) {
					String var = "VariableInfo입니다.\n";
					display.append(var);
				}
			}
		});
		display = new JTextArea(20,20);
		add(new JScrollPane(tree));
		add(new JScrollPane(display));
	}
	
	
	public void gui() {
		JFrame f = new JFrame("Tree Model Example");
		TreeWindow tree = new TreeWindow();
		f.getContentPane().add("Center", tree);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(400,300);
		f.setVisible(true);
	}
	

}











/*
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;
import java.io.*;

public class TreeWindow extends JFrame  {
	
	private JScrollPane treeScroll;
	private JTree tree;
	private DefaultMutableTreeNode top;
	private DefaultMutableTreeNode[] method, variable;
	private DefaultTreeModel treemodel;
	private TreePath path;
	
	private String [] method1 = {"Stack", "~Stack", "push", "pop", "isEmpty", "isFull"};
	private String [] variable1 = {"size", "top", "*ptr"};
	
	private DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
	
	
	public TreeWindow() {
		
		
		
		top = new DefaultMutableTreeNode("Stack"); // ClassInfo.getName()


		method = new DefaultMutableTreeNode[method1.length];
		variable = new DefaultMutableTreeNode[variable1.length];
		
		for (int i=0 ; i<method1.length ; i++) {
			method[i] = new DefaultMutableTreeNode(method1[i]);
			top.add(method[i]);
		}
		
		for (int i=0 ; i<variable1.length ; i++) {
			variable[i] = new DefaultMutableTreeNode(variable1[i]);
			top.add(variable[i]);
		}
		
		treemodel = new DefaultTreeModel(top);
		tree = new JTree(treemodel);
		tree.setCellRenderer(renderer);
		path = new TreePath(top);
		
		tree.collapsePath(path); 	//
		
		
		treeScroll = new JScrollPane(tree);
		getContentPane().add(treeScroll, BorderLayout.CENTER);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 TreeWindow frame = new TreeWindow();
		 frame.setVisible(true);
		 frame.pack();
		 frame.setSize(500, 500);
	}

}
*/
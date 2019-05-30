package javaproj;

import java.util.*;
import javax.swing.tree.*;
import javax.swing.*;
import javax.swing.event.*;

class TreeWindow extends JPanel implements TreeModel {
	protected ClassInfo classinfo;
	
	public TreeWindow(ClassInfo c) {
		this.classinfo = c;
	}
	
	public Object getChild(Object parent, int index) {
		if(parent instanceof ClassInfo) {
			ClassInfo c = (ClassInfo) parent;
			return c.getMethod(index);
			
		}
		return null;
	}
	
	public int getChildCount(Object parent) {
		if(parent instanceof ClassInfo) {
			ClassInfo c = (ClassInfo) parent;
			return  c.sizeMethod() + c.sizeVariable();
		}
		return 0;
	}
	
	public int getIndexOfChild(Object parent, Object child) {
		
	}
	
	public Object getRoot() {
		return classinfo;
	}
	
	public boolean isLeaf(Object node) {
		
	}
	
	public void addTreeModelListener(TreeModelListener I) {
		
	}
	
	public void removeTreeModelListener(TreeModelListener I) {
		
	}
	
	public void valueForPathChanged(TreePath path, Object newValue) {
		
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

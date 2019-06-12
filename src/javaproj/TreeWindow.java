package javaproj;

import java.util.*;
import javax.swing.tree.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;

// Stack.h�� Ŭ����, �޼ҵ�, ���� ������ �����ִ� Ʈ�� ��
class ClassTreeModel implements TreeModel {
	
	protected ClassInfo classinfo;
	
	public ClassTreeModel(ClassInfo c) {
		classinfo = c;
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
			return c.sizeInfo();
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
			return c.sizeMethod() + c.getIndexOfVariable((VariableInfo)child);
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
	
	public void addTreeModelListener(TreeModelListener I) {}
	public void removeTreeModelListener(TreeModelListener I) {}
	public void valueForPathChanged(TreePath path, Object newValue) {}
	
}


// Ʈ���� �����ִ� �г��� �����ϴ� Ŭ����
public class TreeWindow extends JPanel{
	
	protected JTree tree;
	protected JScrollPane scroll;
	protected ViewerWindow viewerwindow = Main.window;
	protected ContentWindow contentwindow = viewerwindow.contentPanel;
	protected UseWindow usewindow = viewerwindow.use;
	
	// TreeWindow(JPanel) ������
	public TreeWindow() {
		ClassInfo c = Main.t.classinfo[0]; 
		ClassTreeModel model = new ClassTreeModel(c);
		tree = new JTree(model);
		tree.setFont(new Font("SansSerif", Font.PLAIN, 12));
		
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				Object o = e.getPath().getLastPathComponent();
				// ClassInfo ��ü�� Ŭ�� �Ǿ��� ��
				if(o instanceof ClassInfo) {
					contentwindow.showCard((ClassInfo)o);
					usewindow.showCard((ClassInfo)o);
				}
				// MethodInfo ��ü�� Ŭ�� �Ǿ��� ��
				else if(o instanceof MethodInfo) {
					contentwindow.showCard((MethodInfo)o);
					usewindow.showCard((MethodInfo)o);
				}
				// VariableInfo ��ü�� Ŭ�� �Ǿ��� ��
				else if(o instanceof VariableInfo) {
					contentwindow.showCard((VariableInfo)o);
					usewindow.showCard((VariableInfo)o);
				}
			}
		});
		
		scroll = new JScrollPane(tree);
		scroll.setPreferredSize(new Dimension(233,390));
		this.add(scroll);
	}
	
}
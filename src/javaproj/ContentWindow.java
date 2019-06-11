package javaproj;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.TableModelListener;
import java.util.*;

public class ContentWindow extends JPanel {
	
	private CardLayout card = new CardLayout();
	private CardClass c;
	private CardMethod m;
	private CardVariable v;
	
	public ContentWindow() {
		this.setLayout(card);		
		c = new CardClass(Main.t.classinfo[0]);
		add(c, "CardClass");
		// card.show(this,  "CardMethod");
		card.show(this, "CardClass");
	}
	
	public void showCard(Object o) {
		if (o instanceof ClassInfo) {
			c = new CardClass((ClassInfo)o);
			add(c, "CardClass");
			card.show(this, "CardClass");
		}
		else if (o instanceof MethodInfo) {
			m = new CardMethod((MethodInfo)o);
			add(m, "CardMethod");
			card.show(this, "CardMethod");
		}
		else if (o instanceof VariableInfo) {
			v = new CardVariable((VariableInfo)o);
			add(v, "CardVariable");
			card.show(this, "CardVariable");
		}
	}
	
}


class CardClass extends JPanel {
	
	private ClassInfo classinfo;
	private JTable table;
	private JScrollPane scroll;
	
	public CardClass(ClassInfo c) {
		classinfo = c;
		TableModel model = new TableModel(classinfo);
		table = new JTable(model);
		table.setRowHeight(25);
		scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(630,630));
		this.add(scroll);
	}
	
	
	class TableModel extends AbstractTableModel {
				
		private ArrayList<MethodInfo> method;
		private ArrayList<VariableInfo> variable;
		private String[] columnName = {"Name", "Type", "Access"};
		private int sizeMethod, sizeVariable;
		
		public TableModel(ClassInfo c) {
			method = c.getMethodList();
			variable = c.getVariableList();
			sizeMethod = method.size();
			sizeVariable = variable.size();
		}
		
		public int getColumnCount() {
			return columnName.length;
		}
		
		public int getRowCount() {
			return sizeMethod + sizeVariable;
		}
		
		public String getColumnName(int col) {
			return columnName[col];
		}
		
		public Object getValueAt(int row, int col) {
			Object value = null;
			if (row < sizeMethod) {
				MethodInfo method = classinfo.getMethod(row);
				switch(col) {
				case 0:
					value = method.getName() + "(" + method.getFactor() + ")";
					break;
				case 1:
					value = method.getType();
					break;
				case 2:
					value = method.getAccess();
					break;
				}
			}
			else {
				VariableInfo variable = classinfo.getVariable(row - sizeMethod);
				switch(col) {
				case 0:
					value = variable.getName();
					break;
				case 1:
					value = variable.getType();
					break;
				case 2:
					value = variable.getAccess();
					break;
				}
			}
			return value;
		}
		
		public Class getColumnClass(int c) {
			return getValueAt(0,c).getClass();
		}
		
	}
	
}


class CardMethod extends JPanel {
	
	private MethodInfo methodinfo;
	private JTextArea field;
	private JScrollPane scroll;
	
	public CardMethod (MethodInfo m) {
		methodinfo = m;
		field = new JTextArea(30,30);
		field.append(methodinfo.getCode());
		scroll = new JScrollPane(field);
		scroll.setPreferredSize(new Dimension(630,630));
		this.add(scroll);
	}
	
}


class CardVariable extends JPanel {
	
	private VariableInfo variableinfo;
	private JTable table;
	private JScrollPane scroll;
	
	
	public CardVariable (VariableInfo v) {
		variableinfo = v;
		TableModel model = new TableModel(variableinfo);
		table = new JTable(model);
		table.setRowHeight(25);
		table.getColumnModel().getColumn(1).setPreferredWidth(280);
		scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(630,630));
		this.add(scroll);
	}
	
	
	class TableModel extends AbstractTableModel {
		
		private VariableInfo variable;
		private String[] columnName = {"Name", "Methods"};
		
		public TableModel(VariableInfo v) {
			variable = variableinfo;
		}
		
		public int getColumnCount() {
			return columnName.length;
		}
		
		public int getRowCount() {
			return 1;
		}
		
		public String getColumnName(int col) {
			return columnName[col];
		}
		
		public Object getValueAt(int row, int col) {
			Object value = "";	
			switch(col) {
			case 0:
				value = variableinfo.getName();			
				break;
			case 1:
				for(int i=0; i<variableinfo.getMethodSize(); i++) {
					value = value + variableinfo.getMethodList().get(i).getName() 
							+ "(" + variableinfo.getMethodList().get(i).getFactor()  + ")" ; 
					if(i != variableinfo.getMethodSize() - 1)
						value += ", ";
				}
				break;
			}
			return value;
		}
		
		public Class getColumnClass(int c) {
			return getValueAt(0,c).getClass();
		}
		
	}
	
}
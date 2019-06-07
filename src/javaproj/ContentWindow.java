package javaproj;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.TableModelListener;
import java.util.*;

/*
public class ContentWindow extends JFrame {
	
	//
	public ContentWindow() {
		ClassInfo c = Main.t.classinfo[0]; 
		MethodInfo m = Main.t.classinfo[0].getMethod(1);
		VariableInfo v = Main.t.classinfo[0].getVariable(2);
		setSize(900,700);
		setTitle("ContentWindow");
		//add(new CardClass(c));
		add(new CardMethod(m));
		//add(new VariableClass(v));
		setVisible(true);
		
	}
	//
	
	private CardLayout cards = new CardLayout();
	
	public ContentWindow() {
		setSize(600,450);
		getContentPane().setLayout(cards);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		CardClass c = new CardClass(Main.t.classinfo[0]);
		CardMethod m = new CardMethod(Main.t.classinfo[0].getMethod(0));
		CardVariable v = new CardVariable(Main.t.classinfo[0].getVariable(0));
		getContentPane().add(c, "CardClass");
		getContentPane().add(m, "CardMethod");
		getContentPane().add(v, "CardVariable");
		
		setVisible(true);
		
		
	}

}
*/

public class ContentWindow extends JPanel{
	private CardLayout card = new CardLayout();
	private CardClass c;
	private CardMethod m;
	public CardMethodUse u;
	private CardVariable v;
	
	public ContentWindow() {
		this.setLayout(card);
		
		
		c = new CardClass(Main.t.classinfo[0]);
		add(c, "CardClass");
		card.show(this,  "CardMethod");
		
		
		//CardClass c = new CardClass(Main.t.classinfo[0]);
		//CardMethod m = new CardMethod(Main.t.classinfo[0].getMethod(0));
		//CardVariable v = new CardVariable(Main.t.classinfo[0].getVariable(0));
		
		/*
		c = new CardClass(Main.t.classinfo[0]);
		m = new CardMethod(Main.t.classinfo[0].getMethod(0));
		v = new CardVariable(Main.t.classinfo[0].getVariable(0));
		add(c, "CardClass");
		add(m, "CardMethod");
		add(v, "CardVariable");
		
		card.show(this, "CardMethod");
		*/
	}
	
	public void showCard(Object o) {
		if (o instanceof ClassInfo) {
			c = new CardClass((ClassInfo)o);
			
			add(c, "CardClass");
			card.show(this, "CardClass");
			//System.out.println("showCardClass");
		}
		else if (o instanceof MethodInfo) {
			m = new CardMethod((MethodInfo)o);
			u = new CardMethodUse((MethodInfo)o);
			//System.out.println("showCardMethod");
			add(m, "CardMethod");
			card.show(this, "CardMethod");
		}
		else if (o instanceof VariableInfo) {
			v = new CardVariable((VariableInfo)o);
			//System.out.println("showCardVariable");
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
		//getContentPane().add(new JScrollPane(table), "Center");
		scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(630,630));
		this.add(scroll);
		//add(new JScrollPane(table));
	}
	
	class TableModel extends AbstractTableModel {
				
		private ArrayList<MethodInfo> method;
		private ArrayList<VariableInfo> variable;
		//private ArrayList<Object> every;
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
			//return sizeVariable;
			//return classinfo.sizeMethod();
			//return sizeMethod + sizeVariable;
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
			/*
			MethodInfo method = classinfo.getMethod(row);
			VariableInfo variable = classinfo.getVariable(row - sizeMethod);
			Object value = null;
			
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
			
			
			//if (row < classinfo.sizeMethod()) {
				
			//}
			
			else {
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
			*/
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
		//add(new JScrollPane(field));
	}
}

//
class CardMethodUse extends JPanel {
	private MethodInfo methodinfo;
	private JTextArea field;
	
	public CardMethodUse (MethodInfo m) {
		methodinfo = m;
		field = new JTextArea(30,30);
		field.append(methodinfo.getUse());
		//System.out.println(methodinfo.getUse());
		field.setPreferredSize(new Dimension(200,200));
		this.add(field);
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
		//getContentPane().add(new JScrollPane(table), "Center");
		//add(new JScrollPane(table));
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
			//return variableinfo.getMethodList().size();
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
			
			
			/*
			MethodInfo method = variableinfo.getMethodList().get(row);
			Object value = null;
			
			switch(col) {
			case 0:
				if (row == 0)
					value = variableinfo.getName();
				else
					value = null;
				break;
			case 1:
				value = method.getName() + "(" + method.getFactor() + ")";
				break;
			}
			return value;
			*/
		}
		
		public Class getColumnClass(int c) {
			return getValueAt(0,c).getClass();
		}
		
	}
}
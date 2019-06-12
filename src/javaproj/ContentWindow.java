package javaproj;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.TableModelListener;
import java.util.*;

// 트리에서 선택된 노드에 해당하는 내용을 CardLayout을 통해 보여주는 패널을 생성하는 클래스
public class ContentWindow extends JPanel {
	
	private CardLayout card = new CardLayout();
	private CardClass c;
	private CardMethod m;
	private CardVariable v;
	private CardBlank b;
	
	// ContentWindow(JPanel) 생성자
	public ContentWindow() {
		this.setLayout(card);
		b = new CardBlank();
		add(b, "CardBlank");
		card.show(this, "CardBlank");
	}
	
	// 트리에서 선택된 객체의 타입(클래스)에 따라 해당 카드를 보여주는 메소드
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


// 트리에서 클래스 클릭 시 표시되는 카드 CardClass
class CardClass extends JPanel {
	
	private ClassInfo classinfo;
	private JTable table;
	private JScrollPane scroll;
	
	public CardClass(ClassInfo c) {
		classinfo = c;
		TableModel model = new TableModel(classinfo);
		table = new JTable(model);
		table.setFont(new Font("SansSerif", Font.PLAIN, 13));
		table.setRowHeight(27);
		scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(625,605));
		this.add(scroll);
	}
	
	
	// CardClass의 내용이 되는 Table
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
			// row가 메소드의 개수보다 작을 때, 표에 메소드 관련 정보(name, type, access) 출력
			if (row < sizeMethod) {
				MethodInfo method = classinfo.getMethod(row);
				switch(col) {
				case 0: // Name
					value = method.getName() + "(" + method.getFactor() + ")";
					break;
				case 1:	// Type
					value = method.getType();
					break;
				case 2:	// Access
					value = method.getAccess();
					break;
				}
			}
			// row가 메소드의 개수보다 크거나 같을 때, 표에 변수 관련 정보(name, type, access) 출력
			else {
				VariableInfo variable = classinfo.getVariable(row - sizeMethod);
				switch(col) {
				case 0:	// Name
					value = variable.getName();
					break;
				case 1:	// Type
					value = variable.getType();
					break;
				case 2:	// Access
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


// 트리에서 메소드 클릭 시 표시되는 카드 CardMethod
class CardMethod extends JPanel {
	
	private MethodInfo methodinfo;
	private JTextArea field;
	private JScrollPane scroll;
	
	public CardMethod (MethodInfo m) {
		methodinfo = m;
		field = new JTextArea(30,30);
		field.setFont(new Font("SansSerif", Font.PLAIN, 13));
		field.append(methodinfo.getCode()); // TextArea에 해당 메소드의 코드를 출력
		scroll = new JScrollPane(field);
		scroll.setPreferredSize(new Dimension(625,605));
		this.add(scroll);
	}
	
}


// 트리에서 변수 클릭 시 표시되는 카드 CardVariable
class CardVariable extends JPanel {
	
	private VariableInfo variableinfo;
	private JTable table;
	private JScrollPane scroll;
	
	
	public CardVariable (VariableInfo v) {
		variableinfo = v;
		TableModel model = new TableModel(variableinfo);
		table = new JTable(model);
		table.setFont(new Font("SansSerif", Font.PLAIN, 13));
		table.setRowHeight(27);
		table.getColumnModel().getColumn(1).setPreferredWidth(280);
		scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(625,605));
		this.add(scroll);
	}
	
	
	// CardVariable의 내용이 되는 Table
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
			case 0:	// Name
				value = variableinfo.getName();			
				break;
			case 1:	// Method
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


// 프로그램 처음 실행 시 표시되는 빈 카드 CardBlank
class CardBlank extends JPanel {
	
	private JLabel label = new JLabel("");
	
	public CardBlank() {
		this.add(label);
	}
	
}
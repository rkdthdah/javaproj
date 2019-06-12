package javaproj;

import java.util.*;

// Stack.h�� Ŭ������ ���� ������ �����ϴ� Ŭ����
public class ClassInfo {
	
	private String name; // Ŭ���� �̸�
	private ArrayList<MethodInfo> method = new ArrayList<MethodInfo>(); // �޼ҵ� (ArrayList)
	private ArrayList<VariableInfo> variable = new ArrayList<VariableInfo>(); // ���� (ArrayList)
	
	public ClassInfo(String name) {
		this.name = name;
	}
	
	// setters
	public void setMethod(MethodInfo method) {
		this.method.add(method);
	}
	
	public void setVariable(VariableInfo variable) {
		this.variable.add(variable);
	}
	
	// CardClass���� ���
	public ArrayList<MethodInfo> getMethodList(){
		return method;
	}
	
	public ArrayList<VariableInfo> getVariableList(){
		return variable;
	}

	public MethodInfo getMethod(int index) {
		return method.get(index);
	}
	
	public VariableInfo getVariable(int index) {
		return variable.get(index);
	}
	
	public int sizeMethod() {
		return method.size();
	}
	
	public int sizeVariable() {
		return variable.size();
	}
	
	public int getIndexOfMethod(MethodInfo m) {
		for(int i=0 ; i<method.size(); i++) {
			MethodInfo mi = method.get(i);
			if(mi == m)
				return i;
		}
		return -1;
	}
	
	public int getIndexOfVariable(VariableInfo v) {
		for(int i=0 ; i<variable.size() ; i++) {
			VariableInfo vi = variable.get(i);
			if(vi == v)
				return i;
		}
		return -1;
	}
	
	// �޼ҵ� ArrayList�� ���� ArrayList�� ������� ��ģ Object�� ArrayList�� info() ���� �޼ҵ�
	public ArrayList<Object> info() {
		ArrayList<Object> o = new ArrayList<Object>();
		for(int i=0; i<method.size(); i++) {
			o.add(method.get(i));
		}
		for(int i=0; i<variable.size(); i++) {
			o.add(variable.get(i));
		}
		return o;
	}
	
	public Object getInfo(int index) {
		return info().get(index);
	}
	
	public int sizeInfo() {
		return info().size();
	}
	
	public int getIndexOfInfo(Object o) {
		for(int i=0; i<sizeInfo(); i++) {
			Object oi = getInfo(i);
			if(oi == o)
				return i;
		}
		return -1;
	}

	// TreeModel���� ���
	public String toString() {
		return name + " <class T>";
	} 
	
	// Console�� ���
	public void printall() {
		System.out.println(name);
		System.out.println(method);
		System.out.println(variable);
	}
	
}

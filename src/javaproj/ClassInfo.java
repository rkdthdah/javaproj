package javaproj;
import java.util.*;

public class ClassInfo {
	private String name; // Ŭ���� �̸�
	private ArrayList<MethodInfo> method = new ArrayList<MethodInfo>(); // �޼ҵ�
	private ArrayList<VariableInfo> variable = new ArrayList<VariableInfo>(); // ����
	
	public ClassInfo(String name) {
		this.name = name;
	}
	
	public void setMethod(MethodInfo method) {
		this.method.add(method);
	}
	
	public void setVariable(VariableInfo variable) {
		this.variable.add(variable);
	}
	
	
	public void printall() {
		System.out.println(name);
		System.out.println(method);
		System.out.println(variable);
	}

	// TreeModel���� �̿�
	public String toString() {
		return name;
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
	
	public int getIndexOfMethod(VariableInfo v) {
		for(int i=0 ; i<variable.size() ; i++) {
			VariableInfo vi = variable.get(i);
			if(vi == v)
				return i;
		}
		return -1;
	}

}

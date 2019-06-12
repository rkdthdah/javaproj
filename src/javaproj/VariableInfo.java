package javaproj;

import java.util.*;

// Stack.h�� ������ ���� ������ �����ϴ� Ŭ����
public class VariableInfo extends CommonInfo {
	
	private ArrayList<MethodInfo> method = new ArrayList<MethodInfo>(); // ���Ǵ� �޼ҵ� (ArrayList)
	
	public VariableInfo(String name, String type, String access) {
		super(name, type, access);
	}
	
	// setter
	public void setMethod(MethodInfo method) {
		this.method.add(method);
	}
	
	// CardClass���� ���
	public String getName() {
		return name;
	}
	
	public ArrayList<MethodInfo> getMethodList() {
		return method;
	}
	
	public int getMethodSize() {
		return method.size();
	}
	
	// TreeModel���� ��� 
	public String toString() {
		return name + " : " + type;
	}

	// Console�� ���
	public void printall() {
		System.out.println(name);
		System.out.println(type);
		System.out.println(access);
		System.out.println(method);
	}

}

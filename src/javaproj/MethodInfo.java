package javaproj;

import java.util.*;

// Stack.h�� �޼ҵ忡 ���� ������ �����ϴ� Ŭ����
public class MethodInfo extends CommonInfo {
	
	private String code, factor; // �ҽ��ڵ�, ��������
	private ArrayList<VariableInfo> variable = new ArrayList<VariableInfo>(); // ������� (ArrayList)
	
	public MethodInfo(String name, String type, String access) {
		super(name, type, access);
	}
	
	// setters
	public void setCode(String code) {
		this.code = code;
	}
	
	public void setFactor(String factor) {
		this.factor = factor;
	}
	
	public void setVariable(VariableInfo variable) {
		this.variable.add(variable);
	}
	
	// CardClass���� ���
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	
	public String getAccess() {
		return access;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getFactor() {
		if (factor == null)
			return "";
		return factor;
	}
	
	public ArrayList<VariableInfo> getVariableList() {
		return variable;
	}
	
	// CardMethodUse���� ���	
	public String getUse() {
		String use = "  ";
		for(int i=0; i<variable.size(); i++) {
			use += variable.get(i).getName() + "\n  ";
		}
		return use;
	}

	// TreeModel���� ��� 
	public String toString() {
		if(factor == null)
			return name + "()";
		else
			return name + "(" + factor + ")";
	} 

	// Console�� ���
	public void printall() {
		System.out.println(name);
		System.out.println(type);
		System.out.println(access);
		System.out.println(factor);
		System.out.println(code);
		System.out.println(variable);
	}
	
}

package javaproj;

import java.util.*;

public class MethodInfo extends CommonInfo {
	private String code, factor; // �ҽ��ڵ�, ��������
	private ArrayList<VariableInfo> variable = new ArrayList<VariableInfo>(); // ������� (��ü)
	
	public MethodInfo(String name, String type, String access) {
		super(name, type, access);
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public void setFactor(String factor) {
		this.factor = factor;
	}
	
	public void setVariable(VariableInfo variable) {
		this.variable.add(variable);
	}
	
	public void printall() {
		System.out.println(name);
		System.out.println(type);
		System.out.println(access);
		System.out.println(factor);
		System.out.println(code);
		System.out.println(variable);
	}

	

}

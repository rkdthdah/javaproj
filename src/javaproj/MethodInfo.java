package javaproj;

import java.util.*;

public class MethodInfo extends CommonInfo {
	private String code, factor; // �ҽ��ڵ�, ��������
	private ArrayList<VariableInfo> variable; // ������� (��ü)
	
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

	

}

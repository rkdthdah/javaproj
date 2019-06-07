package javaproj;

import java.util.*;

public class MethodInfo extends CommonInfo {
	private String code, factor; // 소스코드, 인자정보
	private ArrayList<VariableInfo> variable = new ArrayList<VariableInfo>(); // 멤버변수 (객체)
	
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
	
	// CardClass에서 사용
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
	
	
	public void printall() {
		System.out.println(name);
		System.out.println(type);
		System.out.println(access);
		System.out.println(factor);
		System.out.println(code);
		System.out.println(variable);
	}

	// TreeModel에서 이용 + public display()
	public String toString() {
		return name;
	} 

}

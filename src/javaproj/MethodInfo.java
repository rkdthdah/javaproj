package javaproj;

import java.util.*;

// Stack.h의 메소드에 관한 정보를 저장하는 클래스
public class MethodInfo extends CommonInfo {
	
	private String code, factor; // 소스코드, 인자정보
	private ArrayList<VariableInfo> variable = new ArrayList<VariableInfo>(); // 멤버변수 (ArrayList)
	
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
	
	// CardMethodUse에서 사용	
	public String getUse() {
		String use = "  ";
		for(int i=0; i<variable.size(); i++) {
			use += variable.get(i).getName() + "\n  ";
		}
		return use;
	}

	// TreeModel에서 사용 
	public String toString() {
		if(factor == null)
			return name + "()";
		else
			return name + "(" + factor + ")";
	} 

	// Console에 출력
	public void printall() {
		System.out.println(name);
		System.out.println(type);
		System.out.println(access);
		System.out.println(factor);
		System.out.println(code);
		System.out.println(variable);
	}
	
}

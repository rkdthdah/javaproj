package javaproj;

import java.util.*;

public class VariableInfo extends CommonInfo {
	private ArrayList<MethodInfo> method = new ArrayList<MethodInfo>(); // 사용되는 메소드 (객체)
	
	public VariableInfo(String name, String type, String access) {
		super(name, type, access);
	}
	
	public void setMethod(MethodInfo method) {
		this.method.add(method);
	}
	
	public void printall() {
		System.out.println(name);
		System.out.println(type);
		System.out.println(access);
		System.out.println(method);
	}
	
	// CardVariable에서 사용
	public String getName() {
		return name;
	}
	
	public ArrayList<MethodInfo> getMethodList() {
		return method;
	}
	
	public int getMethodSize() {
		return method.size();
	}
	
	// TreeModel에서 이용 
	public String toString() {
		return name + " : " + type;
	}
	

}

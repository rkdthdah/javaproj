package javaproj;

import java.util.*;

// Stack.h의 변수에 관한 정보를 저장하는 클래스
public class VariableInfo extends CommonInfo {
	
	private ArrayList<MethodInfo> method = new ArrayList<MethodInfo>(); // 사용되는 메소드 (ArrayList)
	
	public VariableInfo(String name, String type, String access) {
		super(name, type, access);
	}
	
	// setter
	public void setMethod(MethodInfo method) {
		this.method.add(method);
	}
	
	// CardClass에서 사용
	public String getName() {
		return name;
	}
	
	public ArrayList<MethodInfo> getMethodList() {
		return method;
	}
	
	public int getMethodSize() {
		return method.size();
	}
	
	// TreeModel에서 사용 
	public String toString() {
		return name + " : " + type;
	}

	// Console에 출력
	public void printall() {
		System.out.println(name);
		System.out.println(type);
		System.out.println(access);
		System.out.println(method);
	}

}

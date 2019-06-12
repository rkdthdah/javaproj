package javaproj;

import java.util.*;

// Stack.h의 클래스에 관한 정보를 저장하는 클래스
public class ClassInfo {
	
	private String name; // 클래스 이름
	private ArrayList<MethodInfo> method = new ArrayList<MethodInfo>(); // 메소드 (ArrayList)
	private ArrayList<VariableInfo> variable = new ArrayList<VariableInfo>(); // 변수 (ArrayList)
	
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
	
	// CardClass에서 사용
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
	
	// 메소드 ArrayList와 변수 ArrayList를 순서대로 합친 Object형 ArrayList인 info() 관련 메소드
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

	// TreeModel에서 사용
	public String toString() {
		return name + " <class T>";
	} 
	
	// Console에 출력
	public void printall() {
		System.out.println(name);
		System.out.println(method);
		System.out.println(variable);
	}
	
}

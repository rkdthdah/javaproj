package javaproj;
import java.util.*;

public class ClassInfo {
	private String name; // 클래스 이름
	private ArrayList<MethodInfo> method; // 메소드
	private ArrayList<VariableInfo> variable; // 변수
	
	public ClassInfo(String name) {
		this.name = name;
	}
	
	public void setMethod(MethodInfo method) {
		this.method.add(method);
	}
	
	public void setVariable(VariableInfo variable) {
		this.variable.add(variable);
	}

	

}

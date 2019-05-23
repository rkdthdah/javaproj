package javaproj;
import java.util.*;

public class ClassInfo {
	private String name; // 클래스 이름
	private ArrayList<MethodInfo> method; // 메소드
	private ArrayList<VariableInfo> variable; // 변수
	
	public ClassInfo(ArrayList<MethodInfo> method, ArrayList<VariableInfo> variable) {
		this.method = method;
		this.variable = variable;
	}

	

}

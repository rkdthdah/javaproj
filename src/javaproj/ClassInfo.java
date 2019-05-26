package javaproj;
import java.util.*;

public class ClassInfo {
	private String name; // Ŭ���� �̸�
	private ArrayList<MethodInfo> method = new ArrayList<MethodInfo>(); // �޼ҵ�
	private ArrayList<VariableInfo> variable = new ArrayList<VariableInfo>(); // ����
	
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

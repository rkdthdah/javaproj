package javaproj;
import java.util.*;

public class ClassInfo {
	private String name; // Ŭ���� �̸�
	private ArrayList<MethodInfo> method; // �޼ҵ�
	private ArrayList<VariableInfo> variable; // ����
	
	public ClassInfo(ArrayList<MethodInfo> method, ArrayList<VariableInfo> variable) {
		this.method = method;
		this.variable = variable;
	}

	

}

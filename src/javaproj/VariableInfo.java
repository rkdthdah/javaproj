package javaproj;

import java.util.*;

public class VariableInfo extends CommonInfo {
	private ArrayList<MethodInfo> method; // ���Ǵ� �޼ҵ� (��ü)
	
	public VariableInfo(String name, String type, String access) {
		super(name, type, access);
	}
	
	public void setMethod(MethodInfo method) {
		this.method.add(method);
	}

	

}

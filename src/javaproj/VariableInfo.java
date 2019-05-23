package javaproj;

public class VariableInfo {
	private String name, type, access; // 자료 이름, 타입, 접근권한
	private MethodInfo method; // 사용되는 메소드 (객체)
	
	public VariableInfo(String name, String type, String access) {
		this.name = name;
		this.type = type;
		this.access = access;
	}
	
	public VariableInfo(String name, String access) {
		this.name = name;
		this.type = "void";
		this.access = access;
	}

	

}

package javaproj;

public class MethodInfo  {
	private String name, type, access, code, factor; // 메소드 이름, 타입, 접근권한, 소스코드, 인자정보
	private VariableInfo variable; // 멤버변수 (객체)
	
	public MethodInfo(String name, String type, String access, String factor) {
		this.name = name;
		this.type = type;
		this.access = access;
		this.factor = factor;
	}
	
	public MethodInfo(String name, String access, String factor) {
		this.name = name;
		this.type = "void";
		this.access = access;
		this.factor = factor;
	}

	

}

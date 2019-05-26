package javaproj;

public class MethodInfo extends CommonInfo {
	private String code, factor; // 소스코드, 인자정보
	private VariableInfo variable; // 멤버변수 (객체)
	
	public MethodInfo(String name, String type, String access) {
		super(name, type, access);
	}
	
	public MethodInfo(String name, String access) {
		super(name, access);
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public void setFactor(String factor) {
		this.factor = factor;
	}
	
	public void setVariable(VariableInfo variable) {
		this.variable = variable;
	}

	

}

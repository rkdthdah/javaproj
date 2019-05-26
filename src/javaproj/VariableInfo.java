package javaproj;

public class VariableInfo extends CommonInfo {
	private MethodInfo method; // 사용되는 메소드 (객체)
	
	public VariableInfo(String name, String type, String access) {
		super(name, type, access);
	}
	
	public VariableInfo(String name, String access) {
		super(name, access);
	}
	
	public void setMethod(MethodInfo method) {
		this.method = method;
	}

	

}

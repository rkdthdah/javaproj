package javaproj;

public class VariableInfo extends CommonInfo {
	private MethodInfo method; // ���Ǵ� �޼ҵ� (��ü)
	
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

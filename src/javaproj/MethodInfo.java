package javaproj;

public class MethodInfo extends CommonInfo {
	private String code, factor; // �ҽ��ڵ�, ��������
	private VariableInfo variable; // ������� (��ü)
	
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

package javaproj;

public class VariableInfo {
	private String name, type, access; // �ڷ� �̸�, Ÿ��, ���ٱ���
	private MethodInfo method; // ���Ǵ� �޼ҵ� (��ü)
	
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

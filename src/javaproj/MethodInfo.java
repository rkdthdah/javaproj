package javaproj;

public class MethodInfo  {
	private String name, type, access, code, factor; // �޼ҵ� �̸�, Ÿ��, ���ٱ���, �ҽ��ڵ�, ��������
	private VariableInfo variable; // ������� (��ü)
	
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

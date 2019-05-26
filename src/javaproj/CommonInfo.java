package javaproj;

public class CommonInfo {
	protected String name, type, access; // �̸�, �ڷ���, ���ٱ���
	
	public CommonInfo(String name, String type, String access) {
		this.name = name;
		this.type = type;
		this.access = access;
	}
	
	public CommonInfo(String name, String access) {
		this.name = name;
		this.type = "void";
		this.access = access;
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	
	public String getAccess() {
		return access;
	}

}

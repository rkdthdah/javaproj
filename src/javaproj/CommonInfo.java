package javaproj;

public class CommonInfo {
	String name, type, access; // �̸�, ��ȯ��/�ڷ���, ���ٱ���
	
	public CommonInfo(String name, String type, String access) {
		this.name = name;
		this.type = type;
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

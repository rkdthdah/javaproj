package javaproj;

// Stack.h�� �޼ҵ�� ������ ����� ������ �����ϴ� Ŭ����
public class CommonInfo {
	
	protected String name, type, access; // �̸�, ��ȯ��/�ڷ���, ���ٱ���
	
	public CommonInfo(String name, String type, String access) {
		this.name = name;
		this.type = type;
		this.access = access;
	}
	
	// getters
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

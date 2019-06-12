package javaproj;

// Stack.h의 메소드와 변수의 공통된 정보를 저장하는 클래스
public class CommonInfo {
	
	protected String name, type, access; // 이름, 반환형/자료형, 접근권한
	
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

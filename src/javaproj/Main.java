package javaproj;

public class Main {
	
	static Parsing t = new Parsing(); // �Ľ� ��ü ���� �� ����
	static ViewerWindow window; // GUI ��ü ����
	
	public static void main(String[] args) {
		t.ParsingLine();
		window = new ViewerWindow();
	}
	
}

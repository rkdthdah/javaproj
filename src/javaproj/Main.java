package javaproj;

public class Main {
	
	static Parsing t = new Parsing();
	static ViewerWindow window;
	
	public static void main(String[] args) {
		t.ParsingLine();
		window = new ViewerWindow();
	}
	
}

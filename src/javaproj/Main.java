package javaproj;

public class Main {
	
	static Parsing t = new Parsing(); // ÆÄ½Ì °´Ã¼ ¼±¾ð ¹× »ý¼º
	static ViewerWindow window; // GUI °´Ã¼ ¼±¾ð
	
	public static void main(String[] args) {
		t.ParsingLine();
		window = new ViewerWindow();
	}
	
}

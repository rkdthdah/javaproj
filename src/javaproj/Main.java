package javaproj;

public class Main {
	static Parsing t = new Parsing();

	public static void main(String[] args) {
		t.ParsingLine();
		
		TreeWindow w = new TreeWindow();
		w.gui();
	}
	
	Parsing getParsing () {
		return t;
	}

}

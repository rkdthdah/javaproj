package javaproj;

public class Main {
	static Parsing t = new Parsing();
	static ViewerWindow window;
	
	public static void main(String[] args) {
		t.ParsingLine();
		
		//TreeWindow w = new TreeWindow();
		//w.gui();
		
		//ContentWindow cont = new ContentWindow();
		
		
		
		//ViewerWindow window = new ViewerWindow();
		
		window = new ViewerWindow();
		
		
		

		
		
	}
	
	
	Parsing getParsing () {
		return t;
	}
	
	ViewerWindow getViewerWindow() {
		return window;
	}

}

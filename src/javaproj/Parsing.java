package javaproj;

import java.util.ArrayList;

public class Parsing {
	private Token t;
	private ArrayList<String> line;
	
	Parsing(){
		t = new Token();
		line = new ArrayList<String>();
	}
	
	void ParsingLine(){
		while(line != null) {
			BringToken();
			
			String classname;
			int loc = FindWord("class");
			if (loc != -1) {
				classname = line.get(loc + 1);
				System.out.println(classname);
			}
			//System.out.println(line);
		}
	}
	
	void BringToken() {
		if (line != null) {
			line = t.Tokenizer();
		}
	}
	
	int FindWord(String w) {
		int loc = -1;
		while (true) {
			try {
				if (line.get(loc + 1).equals(w)) {
					loc += 1;
					return loc;
				}
			}
			catch (IndexOutOfBoundsException e) {
				break;
			}
			catch (NullPointerException e) {
				break;
			}
			loc += 1;
		}
		
		return -1;
	}
}

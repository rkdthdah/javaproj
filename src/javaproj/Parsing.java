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
			
			ClassInfo arrayofclass[];
			int classcnt = 0;
			int loc = FindWord("class");
			if (loc != -1) {
				arrayofclass[classcnt] = new ClassInfo(line.get(loc + 1));
				ClassParsing();
			}
			
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
				if (line.get(loc + 1).matches(".*"+ w + ".*")) {
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
	
	void ClassParsing () {
		BringToken();
		int bracecnt = -1;
		
	}
}

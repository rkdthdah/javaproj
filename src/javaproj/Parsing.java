package javaproj;

import java.util.ArrayList;

public class Parsing {
	void Parsingg() {
		Token t = new Token();
	
		ArrayList<String> a = new ArrayList<>();
		while (a != null) {
			a = t.Tokenizer();
			System.out.println(a);
		}
	}
}

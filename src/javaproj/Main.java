package javaproj;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		Token t = new Token();
		ArrayList<String> a = new ArrayList<>();
		while (a != null) {
			a = t.Tokenizer();
			System.out.println(a);
		}


	}

}

package javaproj;
import java.util.*;

public class Token {
	String str;
	StringTokenizer linetoken;
	
	Token () {
		StringBuffer buffer = ReadFileData.toReadFileData();
		str = buffer.toString();
		linetoken = new StringTokenizer(str, "\n");
	}
	
	ArrayList<String> Tokenizer () {
		String line;
		ArrayList<String> list = new ArrayList<String>();
		if (linetoken.hasMoreTokens()) {
			line = linetoken.nextToken();
		}
		else {
			return null;
		}
		StringTokenizer ntoken = new StringTokenizer(line, " ");
		while (ntoken.hasMoreTokens()) {
			String token = ntoken.nextToken();
			if ((token == "//")||(token == "\n")) break;
			list.add(token);
		}
		return list;
	}
}

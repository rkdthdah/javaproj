package javaproj;
import java.util.*;

// 받은 파일을 적절히 쪼개기
public class Token {
	private String str;
	private StringTokenizer linetoken;
	
	// ReadFileData에서 파일이 입력된 버퍼를 받아 한줄 단위로 쪼갬
	Token () {
		ReadFileData r = new ReadFileData();
		StringBuffer buffer = r.toReadFileData();
		str = buffer.toString();
		linetoken = new StringTokenizer(str, "\n");
	}
	
	// 한줄 단위로 쪼갠 토큰을 받아 띄어쓰기 기준으로 arraylist에 넣음. 만약 //이 감지되면 바로 만들던 arraylist를 반환하고 다음줄로 이동
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
			if (token.matches(".*//.*")) return list;
			list.add(token);
		}
		return list;
	}
}

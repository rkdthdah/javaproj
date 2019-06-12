package javaproj;

import java.util.ArrayList;
import java.util.Stack;

public class Parsing {
	private Token t;  // Token class에 연결
	private ArrayList<String> line;  // Token class로부터 받아온 ArrayList - 한 줄의 정보가 담김
	
	ClassInfo classinfo[] = new ClassInfo[5];  // class의 수 만큼 ClassInfo 객체 생성 - 최대 5개까지 가능
	int classcnt = 0;  // 총 클래스의 수
	
	private int strloc = 0;  // FindWord(String) 함수에서 찾기 대상 String의 문자열 내의 위치
	private int bracecnt = -1;  // ClassParsing 함수에서 중괄호의 상태
	
	MethodInfo arrayofmethod[] = new MethodInfo[20];  // method의 수 만큼 MethodInfo 객체 생성 - 최대 20개까지 가능
	int methodcnt = 0;  // 총 method의 수
	
	VariableInfo arrayofvariable[] = new VariableInfo[20];  // variable의 수 만큼 VariableInfo 객체 생성 - 최대 20개까지 가능
	int variablecnt = 0;  // 총 variable의 수
	
	String code = null;  // method의 추출된 코드
	
	// 생성자: Token 클래스와 연결
	Parsing(){
		t = new Token();
		line = new ArrayList<String>();
	}
	
	public ClassInfo getClassInfo() {
		if (classcnt <= 0) {
			classcnt -= 1;
			return classinfo[classcnt];
		}
		return null;
	}
	
	// 메인 함수: class Token에서 null을 반환할 때 까지 반복 - class 부분을 먼저 파싱한 후, method 정보 부분을 파싱
	void ParsingLine(){
		while(line != null) {
			BringToken();
			
			if (line == null) break;  // 더이상 파싱할 line이 없으면 끝

			int loc = FindWord("class");  // class명을 감지하면 ClassInfo 객체 생성 및 해당 class 내부 파싱 시작
			if (loc != -1) {
				classinfo[classcnt] = new ClassInfo(line.get(loc + 1)); // classinfo 객체생성하여 클래스 정보 저장
				classcnt += 1;
				//System.out.println(classinfo[0].name);
				ClassParsing();  // class 내부 파싱
				continue;
			}
			BringToken();
			MethodParsing();  // class 내부 파싱이 끝나면 method정보가 쓰인 부분을 파싱
		}
		
		// Test - 제대로 저장되었는지
		classinfo[0].printall();
		System.out.println();
		for (int i = 0; i < methodcnt; i++) {
			arrayofmethod[i].printall();
			System.out.println();
		}
		System.out.println();
		for (int i = 0; i < variablecnt; i++) {
			arrayofvariable[i].printall();
			System.out.println();
		}
		System.out.println();
		
	}
	
	// 줄 교체 함수: 호출되면 다음줄을 반환
	void BringToken() {
		if (line != null) {
			line = t.Tokenizer();
		}
		//System.out.println(line);
	}
	
	// 문자열 검색 함수: 넘겨받은 문자열의 ArrayList상 위치를 반환, 지역변수 strloc에 문자열 안에서의 위치를 배정
	int FindWord(String w) {
		//System.out.println("Find " + w);
		int loc = -1;
		while (true) {
			try {
				strloc = line.get(loc + 1).indexOf(w);
				if (strloc != -1) {
					loc += 1;
					//System.out.println(w +" "+ loc);
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
	
	// 메소드 위치 검색 함수: 메소드의 이름을 받으면, 해당 이름을 가진 객체의 객체리스트 상 위치를 반환
	int SearchMethodName(String name) {
		int i;
		for (i = 0; i < methodcnt; i++) {
			if (name.equals(arrayofmethod[i].getName())) break;
		}
		//System.out.println("methodname: " + name + "  " + "methodloc: " + i);
		return i;
	}
	
	// 변수 탐색 함수: 메소드의 코드와 객체리스트상 위치를 받아 코드에서 변수명을 찾고, 이를 각 변수 및 메소드 객체의 ArrayList에 업데이트
	void FindVariable(String code, int loc) {
		//System.out.println("variable found" + code);
		int i;
		for (i = 0; i < variablecnt; i++) {
			String target = arrayofvariable[i].getName();
			if (code.indexOf(target) != -1) {
				//System.out.println("variable found: " + target);
				//System.out.println(loc + " " + i);
				//System.out.println(arrayofmethod[loc] + " " + arrayofvariable[i]);
				arrayofmethod[loc].setVariable(arrayofvariable[i]);
				arrayofvariable[i].setMethod(arrayofmethod[loc]);
			}
		}
	}
	
	// 코드 생성 함수2: 범위 내에 중괄호가 없을 때 사용
	void CodeFor(int a, int b) {
		if (a != b) {
			for (int i = a;i < b; i++) {
				code = code + " " + line.get(i);
			}
		}
	}
	
	// 코드 생성 함수1: 범위 내에 중괄호가 있을 때 이를 처리하고, 이외는 코드 생성 함수2로 넘김
	void CodeMaking(int openloc, int oploc, int closeloc, int clsloc) {
		//System.out.println("openloc: " + openloc + "  " + "oploc: " + oploc + "  " + "closeloc: " + closeloc + "  " + "clsloc: " + clsloc);
		try {
			if ((openloc == closeloc)&&(openloc != -1)) {
				code = line.get(openloc).substring(oploc + 1, clsloc);
			}
			else if ((openloc != -1)&&(closeloc != -1)) {
				code = line.get(openloc).substring(oploc + 1);
				CodeFor(openloc + 1, closeloc);
				code = code + " " + line.get(closeloc).substring(0, clsloc);
			}
			else if ((openloc != -1)&&(closeloc == -1)) {
				code = line.get(openloc).substring(oploc + 1);
				CodeFor(openloc + 1, line.size());
			}
			else if ((openloc == -1)&&(closeloc != -1)) {
				CodeFor(0, closeloc);
				code = code + " " + line.get(closeloc).substring(0, clsloc);
			}
			else if ((openloc == -1)&&(closeloc == -1)) {
				CodeFor(0, line.size());
			}
		}
		catch (NullPointerException e) {}
	}
	
	// 클래스 내부 파싱 함수: 클래스 내부에서 메소드(이름, 인자, 접근) 와 변수(이름, 접근)을 찾고, 이를 ClassInfo에 업데이트하고, 각 MethodInfo 와 VariableInfo 객체 생성 및 업데이트
	void ClassParsing () {
		//System.out.println("cp");
		
		String access = null;  // 접근정보 지역변수
		Stack<Integer> leftmethodloc = new Stack<Integer>();  // 코드 내용이 바로 정의될 경우 해당 메소드 객체의 위치
		Stack<String> leftmethodcode = new Stack<String>();  // 코드 내용이 바로 정의될 경우 해당 메소드 코드의 내용
		
		while (true) {
			
			// 중괄호를 기준으로 class 시작
			if (FindWord("{") != -1) {
				bracecnt += 1;
				//System.out.println("bracecnt: " + bracecnt);
			}
			
			// :를 기준으로 앞의 단어를 접근정보 지역변수로 저장
			int accessloc = FindWord(":"); 
			if (accessloc != -1) {
				access = line.get(accessloc).substring(0, strloc);
				//System.out.println("access: " + access);
			}
			
			// 메소드 이름 찾기
			int methodopen = FindWord("(");
			int methodop = strloc;
			String methodname;
			String methodtype;
			String factor = null;
			boolean activefactor = true;  // 찾은 키워드가 인장정보일 경우 이를 변수타입으로 고려하지 않기 위해
			// 열린 소괄호가 있으면 앞 단어를 메소드 이름으로 저장 및 이름 앞 단어로 메소드 반환타입 저장 - 없을경우 반환타입 void
			if (methodopen != -1) {
				activefactor = false;
				methodname = line.get(methodopen).substring(0, methodop);
				try {
					methodtype = line.get(methodopen - 1);
				}
				catch (IndexOutOfBoundsException e) {
					methodtype = "void";
				}
				if (!(methodtype.equals("void")||methodtype.equals("bool")||methodtype.equals("int"))) {
					methodtype = "void";
				}
				
				// 소괄호 닫힘을 찾아 소괄호 내부에 int가 있다면 인자로 저장
				int methodclose = FindWord(")");
				int factorloc = FindWord("int");
				//System.out.println(methodopen +" "+ methodclose);
				if ((methodopen <= factorloc)&&(factorloc <= methodclose)&&(factorloc != -1)) {
					factor = line.get(factorloc);
					activefactor = false;
					//System.out.println(activefactor);
				}
				// 
				arrayofmethod[methodcnt] = new MethodInfo(methodname, methodtype, access);
				if (bracecnt >= 1) {
					int startloc = FindWord("{");
					int sloc = strloc;
					int closeloc = FindWord("}");
					int cloc = strloc;
					CodeMaking(startloc, sloc, closeloc, cloc);
					arrayofmethod[methodcnt].setCode(code);
					leftmethodloc.push(methodcnt);
					leftmethodcode.push(code);
					}
				arrayofmethod[methodcnt].setFactor(factor);
				classinfo[classcnt - 1].setMethod(arrayofmethod[methodcnt]); // methodinfo 객체 생성 및 메소드 정보 저장
				//System.out.println("methodcnt: " + methodcnt);
				//System.out.println(arrayofmethod[methodcnt].name);
				//System.out.println(arrayofmethod[methodcnt].type);
				//System.out.println(arrayofmethod[methodcnt].access);
				//System.out.println(arrayofmethod[methodcnt].factor);
				methodcnt += 1;
			}
			
			// 줄에 인자 int가 이미 존재하지 않을때 변수 타입 int 감지
			if (activefactor) {
				int loc = FindWord("int");
				if ((loc != -1)&&(FindWord("*") == -1)) {
					arrayofvariable[variablecnt] = new VariableInfo(line.get(loc + 1).replaceAll(";", ""), "int", access); // variableinfo 객체 생성 및 변수정보 저장
					classinfo[classcnt - 1].setVariable(arrayofvariable[variablecnt]); // 감지한 변수를 classinfo의 arrayofvariable에 업데이트
					//System.out.println("variablecnt: " + variablecnt);
					//System.out.println(arrayofvariable[variablecnt].name);
					//System.out.println(arrayofvariable[variablecnt].type);
					//System.out.println(arrayofvariable[variablecnt].access);
					variablecnt += 1;
				}
				
				// 포인터 형식 변수를 감지하면 int형 변수 객체에 정보를 덮어씌움
				loc = FindWord("*");
				if (loc != -1) {
					arrayofvariable[variablecnt] = new VariableInfo(line.get(loc).substring(strloc + 1).replaceAll(";", ""), "int *", access);
					classinfo[classcnt - 1].setVariable(arrayofvariable[variablecnt]);
					//System.out.println("variablecnt: " + variablecnt);
					//System.out.println(arrayofvariable[variablecnt].name);
					//System.out.println(arrayofvariable[variablecnt].type);
					//System.out.println(arrayofvariable[variablecnt].access);
					variablecnt += 1;
				}
			}
			
			// 중괄호 닫힘을 감지하여 class가 닫힘을 감지
			if (FindWord("}") != -1) {
				bracecnt -= 1;
				//System.out.println("bracecut: " + bracecnt);
				if (bracecnt == -1) break;
			}
			BringToken();
		}
		
		// class중간에 내용이 전부 선언된 method의 코드정보를 업데이트
		while (true) {
			if (leftmethodloc.isEmpty()) break;
			FindVariable(leftmethodcode.pop(), leftmethodloc.pop());
		}
		
	}

	// 메소드 부분 파싱 메소드
	void MethodParsing() {
		//System.out.println("methodparsing");
		int bracecnt = -1;
		int methodloc = 20;
		while (true) {
			// 중괄호를 감지하여 메소드의 시작 혹은 끝나는 위치를 감지
			int openloc;
			int oploc = -1;
			openloc = FindWord("{");
			if ((openloc != -1)) {
				bracecnt += 1;
				oploc = strloc;
				//System.out.println("bracecnt: " + bracecnt);
			}
			
			boolean notyet = false;			
			int closeloc;
			int clsloc = -1;
			closeloc = FindWord("}");
			if ((closeloc != -1)) {
				bracecnt -= 1;
				clsloc = strloc;
				//System.out.println(notyet);
				notyet = true;
				//System.out.println("bracecnt: " + bracecnt);
			}
			
			// 메소드 이름 감지하여 arrayofmethod에서의 해당 메소드 위치를 찾음
			String name = null;
			int nameoploc = FindWord("::");
			int nameoloc;
			int nameclsloc;
			int namecloc;
			if (nameoploc != -1) {
				nameoloc = strloc;
				nameclsloc = FindWord("(");
				namecloc = strloc;
				
				if (nameoploc == nameclsloc) {
					name = line.get(nameoploc).substring(nameoloc + 2, namecloc);
				}
				else {
					name = line.get(nameoploc).substring(nameoloc + 2) + line.get(nameclsloc).substring(0, namecloc);
				}
				methodloc = SearchMethodName(name);
			}
			
			//System.out.println(bracecnt);
			
			// 메소드 내부의 코드를 string code에 붙임
			if ((bracecnt != -1)&&notyet) {
				//System.out.println("notyet");
				CodeFor(0, closeloc);
				code = code + line.get(closeloc);
			}
			else if ((bracecnt == 0)||(bracecnt == -1)) {
				CodeMaking(openloc, oploc, closeloc, clsloc);
			}
			else {
				CodeMaking(-1, -1, -1, -1);
			}
			
			// 메소드의 끝까지 코드가 만들어지면 메소드인포에 코드 업데이트 및 포함된 변수 업데이트
			if (bracecnt == -1) {
				//System.out.println("methodloc: " + methodloc);
				arrayofmethod[methodloc].setCode(code.replaceAll("\r", "\n"));
				FindVariable(code, methodloc);
				//System.out.println(code);
				break;
			}
			
			BringToken();
		}
		//System.out.println("end :)");
	}
}

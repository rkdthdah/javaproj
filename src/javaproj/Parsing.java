package javaproj;

import java.util.ArrayList;

public class Parsing {
	private Token t;
	private ArrayList<String> line;
	private int bracecnt = -1;
	private String access;
	
	Parsing(){
		t = new Token();
		line = new ArrayList<String>();
	}
	
	void ParsingLine(){
		while(line != null) {
			BringToken();
			
			ClassInfo classinfo[] = new ClassInfo[5];
			int classcnt = 0;
			int loc = FindWord("class");
			if (loc != -1) {
				classinfo[classcnt] = new ClassInfo(line.get(loc + 1));
				classcnt += 1;
				ClassParsing();
				continue;
			}
			
		}
		
	}
	
	void BringToken() {
		if (line != null) {
			line = t.Tokenizer();
		}
		System.out.println(line);
	}
	
	int FindWord(String w) {
		System.out.println("Find " + w);
		int loc = -1;
		while (true) {
			try {
				String findstr = ".*" + w + ".*";
				if (line.get(loc + 1).matches(findstr)) {
					loc += 1;
					System.out.println(w +" "+ loc);
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
		System.out.println("cp");
		int methodopen = 0;
		while (true) {
			if (FindWord("\\{") != -1) {
				bracecnt += 1;
				System.out.println(bracecnt);
				if (bracecnt >= 1) MethodParsing();
			}
			
			int accessloc = FindWord(":");
			if (accessloc != -1) {
				access = line.get(accessloc).split(":").toString();
				System.out.println(access);
			}
			
			methodopen = FindWord("\\(");
			String methodname;
			String methodtype;
			String factor = null;
			
			int methodcnt = 0;
			MethodInfo arrayofmethod[] = new MethodInfo[10];
			
			if (methodopen != -1) {
				methodname = line.get(methodopen).split("\\(").toString();
				if (methodname.equals("")) {
					methodopen -= 1;
					methodname = line.get(methodopen);			
				}
				
				try {
					methodtype = line.get(methodopen - 1);
				}
				catch (IndexOutOfBoundsException e) {
					methodtype = "void";
				}
				if (!(methodtype.equals("void")||methodtype.equals("bool")||methodtype.equals("int"))) {
					methodtype = "void";
				}
				
				int methodclose = FindWord("\\)");
				int factorloc = FindWord("int");
				System.out.println(methodopen +" "+ methodclose);
				if ((methodopen <= factorloc)||(factorloc <= methodclose)) {
					factor = line.get(factorloc);
				}
				arrayofmethod[methodcnt] = new MethodInfo(methodname, methodtype, access);
				arrayofmethod[methodcnt].setFactor(factor);
				methodcnt += 1;
				System.out.println(arrayofmethod[0].name);
			}
			if (FindWord("\\}") != -1) {
				bracecnt -= 1;
				if (bracecnt == -1) break;
			}
			BringToken();
		}
		
	}

		
	void MethodParsing() {
	}
		
		
}

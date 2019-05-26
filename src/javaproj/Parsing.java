package javaproj;

import java.util.ArrayList;

public class Parsing {
	private Token t;
	private ArrayList<String> line;
	private int strloc = 0;
	private int bracecnt = -1;
	int a = 0;
	
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
				System.out.println(classinfo[0].name);
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
	
	void ClassParsing () {
		System.out.println("cp");
		
		String access = null;
		
		MethodInfo arrayofmethod[] = new MethodInfo[10];
		int methodcnt = 0;
		
		VariableInfo arrayofvariable[] = new VariableInfo[10];
		int variablecnt = 0;
		
		while (true) {
			if (FindWord("{") != -1) {
				bracecnt += 1;
				//System.out.println("bracecnt: " + bracecnt);
				if (bracecnt >= 1) MethodParsing();
			}
			
			int accessloc = FindWord(":");
			if (accessloc != -1) {
				access = line.get(accessloc).substring(0, strloc);
				System.out.println("access: " + access);
			}
			
			int methodopen = FindWord("(");
			int methodop = strloc;
			String methodname;
			String methodtype;
			String factor = null;
			
			boolean activefactor = true;
			
			if (methodopen != -1) {
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
				
				int methodclose = FindWord(")");
				int factorloc = FindWord("int");
				System.out.println(methodopen +" "+ methodclose);
				if ((methodopen <= factorloc)&&(factorloc <= methodclose)&&(factorloc != -1)) {
					factor = line.get(factorloc);
					activefactor = false;
					System.out.println(activefactor);
				}
				arrayofmethod[methodcnt] = new MethodInfo(methodname, methodtype, access);
				arrayofmethod[methodcnt].setFactor(factor);
				System.out.println("methodcnt: " + methodcnt);
				System.out.println(arrayofmethod[methodcnt].name);
				System.out.println(arrayofmethod[methodcnt].type);
				System.out.println(arrayofmethod[methodcnt].access);
				System.out.println(arrayofmethod[methodcnt].factor);
				methodcnt += 1;
			}
			
			if (activefactor) {
				int loc = FindWord("int");
				if (loc != -1) {
					arrayofvariable[variablecnt] = new VariableInfo(line.get(loc + 1), "int", access);
					System.out.println("variablecnt: " + variablecnt);
					System.out.println(arrayofvariable[variablecnt].name);
					System.out.println(arrayofvariable[variablecnt].type);
					System.out.println(arrayofvariable[variablecnt].access);
					variablecnt += 1;
				}
				
				loc = FindWord("*");
				if (loc != -1) {
					variablecnt -= 1;
					arrayofvariable[variablecnt] = new VariableInfo(line.get(loc).substring(strloc + 1), "int *", access);
					System.out.println("variablecnt: " + variablecnt);
					System.out.println(arrayofvariable[variablecnt].name);
					System.out.println(arrayofvariable[variablecnt].type);
					System.out.println(arrayofvariable[variablecnt].access);
					variablecnt += 1;
				}
			}
			
			if (FindWord("}") != -1) {
				bracecnt -= 1;
				//System.out.println("bracecut: " + bracecnt);
				if (bracecnt == -1) break;
			}
			BringToken();
		}
		
	}

		
	void MethodParsing() {
	}
		
		
}

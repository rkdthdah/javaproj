package javaproj;

import java.util.ArrayList;

public class Parsing {
	private Token t;
	private ArrayList<String> line;
	
	ClassInfo classinfo[] = new ClassInfo[5];
	int classcnt = 0;
	
	private int strloc = 0;
	private int bracecnt = -1;
	int a = 0;
	
	MethodInfo arrayofmethod[] = new MethodInfo[20];
	int methodcnt = 0;
	
	VariableInfo arrayofvariable[] = new VariableInfo[20];
	int variablecnt = 0;
	
	String code = null;
	
	Parsing(){
		t = new Token();
		line = new ArrayList<String>();
	}
	
	void ParsingLine(){
		while(line != null) {
			BringToken();
			
			if (line == null) break;

			int loc = FindWord("class");
			if (loc != -1) {
				classinfo[classcnt] = new ClassInfo(line.get(loc + 1));
				classcnt += 1;
				//System.out.println(classinfo[0].name);
				ClassParsing();
				continue;
			}
			BringToken();
			MethodParsing();
		}
		
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
	
	int SearchMethodName(String name) {
		int i;
		for (i = 0; i < methodcnt; i++) {
			if (name.equals(arrayofmethod[i].getName())) break;
		}
		System.out.println("methodname: " + name + "  " + "methodloc: " + i);
		return i;
	}
	
	void FindVariable(String code, int loc) {
		System.out.println("variable found");
		int i;
		for (i = 0; i < variablecnt; i++) {
			String target = arrayofvariable[i].getName();
			if (code.indexOf(target) != -1) {
				System.out.println("variable found: " + target);
				System.out.println(loc + " " + i);
				System.out.println(arrayofmethod[loc] + " " + arrayofvariable[i]);
				arrayofmethod[loc].setVariable(arrayofvariable[i]);
				arrayofvariable[i].setMethod(arrayofmethod[loc]);
			}
		}
	}
	
	void CodeFor(int a, int b) {
		if (a != b) {
			for (int i = a;i < b; i++) {
				code = code + " " + line.get(i);
			}
		}
	}
	
	void CodeMaking(int openloc, int oploc, int closeloc, int clsloc) {
		System.out.println("openloc: " + openloc + "  " + "oploc: " + oploc + "  " + "closeloc: " + closeloc + "  " + "clsloc: " + clsloc);
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
	
	void ClassParsing () {
		//System.out.println("cp");
		
		String access = null;
		
		while (true) {
			if (FindWord("{") != -1) {
				bracecnt += 1;
				//System.out.println("bracecnt: " + bracecnt);
			}
			
			int accessloc = FindWord(":");
			if (accessloc != -1) {
				access = line.get(accessloc).substring(0, strloc);
				//System.out.println("access: " + access);
			}
			
			int methodopen = FindWord("(");
			int methodop = strloc;
			String methodname;
			String methodtype;
			String factor = null;
			
			boolean activefactor = true;
			
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
				
				int methodclose = FindWord(")");
				int factorloc = FindWord("int");
				//System.out.println(methodopen +" "+ methodclose);
				if ((methodopen <= factorloc)&&(factorloc <= methodclose)&&(factorloc != -1)) {
					factor = line.get(factorloc);
					activefactor = false;
					//System.out.println(activefactor);
				}
				arrayofmethod[methodcnt] = new MethodInfo(methodname, methodtype, access);
				if (bracecnt >= 1) {
					//MethodParsing(arrayofmethod[methodcnt].getName());
					}
				arrayofmethod[methodcnt].setFactor(factor);
				classinfo[classcnt - 1].setMethod(arrayofmethod[methodcnt]);
				//System.out.println("methodcnt: " + methodcnt);
				//System.out.println(arrayofmethod[methodcnt].name);
				//System.out.println(arrayofmethod[methodcnt].type);
				//System.out.println(arrayofmethod[methodcnt].access);
				//System.out.println(arrayofmethod[methodcnt].factor);
				methodcnt += 1;
			}
			
			if (activefactor) {
				int loc = FindWord("int");
				if (loc != -1) {
					arrayofvariable[variablecnt] = new VariableInfo(line.get(loc + 1).replaceAll(";", ""), "int", access);
					classinfo[classcnt - 1].setVariable(arrayofvariable[variablecnt]);
					//System.out.println("variablecnt: " + variablecnt);
					//System.out.println(arrayofvariable[variablecnt].name);
					//System.out.println(arrayofvariable[variablecnt].type);
					//System.out.println(arrayofvariable[variablecnt].access);
					variablecnt += 1;
				}
				
				loc = FindWord("*");
				if (loc != -1) {
					variablecnt -= 1;
					arrayofvariable[variablecnt] = new VariableInfo(line.get(loc).substring(strloc + 1).replaceAll(";", ""), "int *", access);
					//System.out.println("variablecnt: " + variablecnt);
					//System.out.println(arrayofvariable[variablecnt].name);
					//System.out.println(arrayofvariable[variablecnt].type);
					//System.out.println(arrayofvariable[variablecnt].access);
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
		System.out.println("methodparsing");
		int bracecnt = -1;
		int methodloc = 20;
		while (true) {
			int openloc;
			int oploc = -1;
			openloc = FindWord("{");
			if ((openloc != -1)) {
				bracecnt += 1;
				oploc = strloc;
				System.out.println("bracecnt: " + bracecnt);
			}
			
			boolean notyet = false;			
			int closeloc;
			int clsloc = -1;
			closeloc = FindWord("}");
			if ((closeloc != -1)) {
				bracecnt -= 1;
				clsloc = strloc;
				System.out.println(notyet);
				notyet = true;
				System.out.println("bracecnt: " + bracecnt);
			}
			
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
			
			System.out.println(bracecnt);
			
			if ((bracecnt != -1)&&notyet) {
				System.out.println("notyet");
				CodeFor(0, closeloc);
				code = code + line.get(closeloc);
			}
			else if ((bracecnt == 0)||(bracecnt == -1)) {
				CodeMaking(openloc, oploc, closeloc, clsloc);
			}
			else {
				CodeMaking(-1, -1, -1, -1);
			}
			
			
			if (bracecnt == -1) {
				System.out.println("methodloc: " + methodloc);
				arrayofmethod[methodloc].setCode(code);
				FindVariable(code, methodloc);
				System.out.println(code);
				break;
			}
			
			BringToken();
		}
		System.out.println("end :)");
	}
}

package javaproj;

import java.util.ArrayList;
import java.util.Stack;

public class Parsing {
	private Token t;  // Token class�� ����
	private ArrayList<String> line;  // Token class�κ��� �޾ƿ� ArrayList - �� ���� ������ ���
	
	ClassInfo classinfo[] = new ClassInfo[5];  // class�� �� ��ŭ ClassInfo ��ü ���� - �ִ� 5������ ����
	int classcnt = 0;  // �� Ŭ������ ��
	
	private int strloc = 0;  // FindWord(String) �Լ����� ã�� ��� String�� ���ڿ� ���� ��ġ
	private int bracecnt = -1;  // ClassParsing �Լ����� �߰�ȣ�� ����
	
	MethodInfo arrayofmethod[] = new MethodInfo[20];  // method�� �� ��ŭ MethodInfo ��ü ���� - �ִ� 20������ ����
	int methodcnt = 0;  // �� method�� ��
	
	VariableInfo arrayofvariable[] = new VariableInfo[20];  // variable�� �� ��ŭ VariableInfo ��ü ���� - �ִ� 20������ ����
	int variablecnt = 0;  // �� variable�� ��
	
	String code = null;  // method�� ����� �ڵ�
	
	// ������: Token Ŭ������ ����
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
	
	// ���� �Լ�: class Token���� null�� ��ȯ�� �� ���� �ݺ� - class �κ��� ���� �Ľ��� ��, method ���� �κ��� �Ľ�
	void ParsingLine(){
		while(line != null) {
			BringToken();
			
			if (line == null) break;  // ���̻� �Ľ��� line�� ������ ��

			int loc = FindWord("class");  // class���� �����ϸ� ClassInfo ��ü ���� �� �ش� class ���� �Ľ� ����
			if (loc != -1) {
				classinfo[classcnt] = new ClassInfo(line.get(loc + 1)); // classinfo ��ü�����Ͽ� Ŭ���� ���� ����
				classcnt += 1;
				//System.out.println(classinfo[0].name);
				ClassParsing();  // class ���� �Ľ�
				continue;
			}
			BringToken();
			MethodParsing();  // class ���� �Ľ��� ������ method������ ���� �κ��� �Ľ�
		}
		
		// Test - ����� ����Ǿ�����
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
	
	// �� ��ü �Լ�: ȣ��Ǹ� �������� ��ȯ
	void BringToken() {
		if (line != null) {
			line = t.Tokenizer();
		}
		//System.out.println(line);
	}
	
	// ���ڿ� �˻� �Լ�: �Ѱܹ��� ���ڿ��� ArrayList�� ��ġ�� ��ȯ, �������� strloc�� ���ڿ� �ȿ����� ��ġ�� ����
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
	
	// �޼ҵ� ��ġ �˻� �Լ�: �޼ҵ��� �̸��� ������, �ش� �̸��� ���� ��ü�� ��ü����Ʈ �� ��ġ�� ��ȯ
	int SearchMethodName(String name) {
		int i;
		for (i = 0; i < methodcnt; i++) {
			if (name.equals(arrayofmethod[i].getName())) break;
		}
		//System.out.println("methodname: " + name + "  " + "methodloc: " + i);
		return i;
	}
	
	// ���� Ž�� �Լ�: �޼ҵ��� �ڵ�� ��ü����Ʈ�� ��ġ�� �޾� �ڵ忡�� �������� ã��, �̸� �� ���� �� �޼ҵ� ��ü�� ArrayList�� ������Ʈ
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
	
	// �ڵ� ���� �Լ�2: ���� ���� �߰�ȣ�� ���� �� ���
	void CodeFor(int a, int b) {
		if (a != b) {
			for (int i = a;i < b; i++) {
				code = code + " " + line.get(i);
			}
		}
	}
	
	// �ڵ� ���� �Լ�1: ���� ���� �߰�ȣ�� ���� �� �̸� ó���ϰ�, �ܴ̿� �ڵ� ���� �Լ�2�� �ѱ�
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
	
	// Ŭ���� ���� �Ľ� �Լ�: Ŭ���� ���ο��� �޼ҵ�(�̸�, ����, ����) �� ����(�̸�, ����)�� ã��, �̸� ClassInfo�� ������Ʈ�ϰ�, �� MethodInfo �� VariableInfo ��ü ���� �� ������Ʈ
	void ClassParsing () {
		//System.out.println("cp");
		
		String access = null;  // �������� ��������
		Stack<Integer> leftmethodloc = new Stack<Integer>();  // �ڵ� ������ �ٷ� ���ǵ� ��� �ش� �޼ҵ� ��ü�� ��ġ
		Stack<String> leftmethodcode = new Stack<String>();  // �ڵ� ������ �ٷ� ���ǵ� ��� �ش� �޼ҵ� �ڵ��� ����
		
		while (true) {
			
			// �߰�ȣ�� �������� class ����
			if (FindWord("{") != -1) {
				bracecnt += 1;
				//System.out.println("bracecnt: " + bracecnt);
			}
			
			// :�� �������� ���� �ܾ �������� ���������� ����
			int accessloc = FindWord(":"); 
			if (accessloc != -1) {
				access = line.get(accessloc).substring(0, strloc);
				//System.out.println("access: " + access);
			}
			
			// �޼ҵ� �̸� ã��
			int methodopen = FindWord("(");
			int methodop = strloc;
			String methodname;
			String methodtype;
			String factor = null;
			boolean activefactor = true;  // ã�� Ű���尡 ���������� ��� �̸� ����Ÿ������ ������� �ʱ� ����
			// ���� �Ұ�ȣ�� ������ �� �ܾ �޼ҵ� �̸����� ���� �� �̸� �� �ܾ�� �޼ҵ� ��ȯŸ�� ���� - ������� ��ȯŸ�� void
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
				
				// �Ұ�ȣ ������ ã�� �Ұ�ȣ ���ο� int�� �ִٸ� ���ڷ� ����
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
				classinfo[classcnt - 1].setMethod(arrayofmethod[methodcnt]); // methodinfo ��ü ���� �� �޼ҵ� ���� ����
				//System.out.println("methodcnt: " + methodcnt);
				//System.out.println(arrayofmethod[methodcnt].name);
				//System.out.println(arrayofmethod[methodcnt].type);
				//System.out.println(arrayofmethod[methodcnt].access);
				//System.out.println(arrayofmethod[methodcnt].factor);
				methodcnt += 1;
			}
			
			// �ٿ� ���� int�� �̹� �������� ������ ���� Ÿ�� int ����
			if (activefactor) {
				int loc = FindWord("int");
				if ((loc != -1)&&(FindWord("*") == -1)) {
					arrayofvariable[variablecnt] = new VariableInfo(line.get(loc + 1).replaceAll(";", ""), "int", access); // variableinfo ��ü ���� �� �������� ����
					classinfo[classcnt - 1].setVariable(arrayofvariable[variablecnt]); // ������ ������ classinfo�� arrayofvariable�� ������Ʈ
					//System.out.println("variablecnt: " + variablecnt);
					//System.out.println(arrayofvariable[variablecnt].name);
					//System.out.println(arrayofvariable[variablecnt].type);
					//System.out.println(arrayofvariable[variablecnt].access);
					variablecnt += 1;
				}
				
				// ������ ���� ������ �����ϸ� int�� ���� ��ü�� ������ �����
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
			
			// �߰�ȣ ������ �����Ͽ� class�� ������ ����
			if (FindWord("}") != -1) {
				bracecnt -= 1;
				//System.out.println("bracecut: " + bracecnt);
				if (bracecnt == -1) break;
			}
			BringToken();
		}
		
		// class�߰��� ������ ���� ����� method�� �ڵ������� ������Ʈ
		while (true) {
			if (leftmethodloc.isEmpty()) break;
			FindVariable(leftmethodcode.pop(), leftmethodloc.pop());
		}
		
	}

	// �޼ҵ� �κ� �Ľ� �޼ҵ�
	void MethodParsing() {
		//System.out.println("methodparsing");
		int bracecnt = -1;
		int methodloc = 20;
		while (true) {
			// �߰�ȣ�� �����Ͽ� �޼ҵ��� ���� Ȥ�� ������ ��ġ�� ����
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
			
			// �޼ҵ� �̸� �����Ͽ� arrayofmethod������ �ش� �޼ҵ� ��ġ�� ã��
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
			
			// �޼ҵ� ������ �ڵ带 string code�� ����
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
			
			// �޼ҵ��� ������ �ڵ尡 ��������� �޼ҵ������� �ڵ� ������Ʈ �� ���Ե� ���� ������Ʈ
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

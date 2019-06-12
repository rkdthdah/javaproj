package javaproj;
import java.io.*;

// 파일 입력하여 입력된 버퍼 반환
public class ReadFileData {

	StringBuffer toReadFileData() {
		int b = 0;
		StringBuffer buffer = new StringBuffer();
		FileInputStream file = null;
		try {
			file = new FileInputStream("Stack.h");
			b = file.read();
			while (b != -1) {
				buffer.append((char)b);
				b = file.read();
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("Oops: FileNotFoundException");
		}
		catch (IOException e) {
			System.out.println("Input error");
		}
		return buffer;
	}
}

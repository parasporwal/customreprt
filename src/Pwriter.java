import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Pwriter {
	
	public static void main(String[] args) throws IOException {
		FileWriter fw=new FileWriter("D://file2.html");
		PrintWriter out=new PrintWriter(fw);
		String constant="<html><head><title>THIS FILE IS GENERATED BY CODE</title></head><body>this is the first file by paras</body></html>";
		out.println(constant);
		out.flush();
		
		
		FileReader fr=new FileReader("D://file2.html");
		BufferedReader bfr=new BufferedReader(fr);
		String line=bfr.readLine();
		while(line!=null){
			System.out.println(line);
			line=bfr.readLine();
		}
		
	}

}

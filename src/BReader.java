import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BReader {
	public static void main(String[] args) throws IOException {
		FileReader fr=new FileReader("D://file1.txt");
		BufferedReader bufReader=new BufferedReader(fr);
		String line=bufReader.readLine();
		while(line!=null){
			System.out.println(line);
			line=bufReader.readLine();
			
		}
		bufReader.close();
	}

}

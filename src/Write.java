import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Write {
	
	public static void main(String[] args) throws IOException {
		File f=new File("D://file1.txt");
		if(!f.exists()){
			f.createNewFile();
		}
		FileWriter fw=new FileWriter(f);
		fw.write("mayank chauhan kal parathe banwa kr layega");
		fw.write('\n');
		fw.write("30-08-2017");
		fw.flush();
		System.out.println("length: "+f.length());
		
		char [] charr=new char[(int) f.length()];
		FileReader fr=new FileReader(f);
		fr.read(charr);
		for(char c: charr){
			System.out.println(c);
		}
		
	}

}

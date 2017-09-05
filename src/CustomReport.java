import java.io.File;

public class CustomReport {
	String fileName;
	private final String curDir;
	
	public CustomReport() {
		curDir=System.getProperty("user.dir");
		System.out.println(curDir);
	}
public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

   public void getFilePath() {
	    getFilePath(new File(curDir));
   }

public void getFilePath( File curDir) {
	System.out.println("**********filepath**************"+curDir.getName());
	File[] files=curDir.listFiles();
	for(File file: files) {
		if(file.isFile())
			System.out.println(file.getName());
		else if(file.isDirectory()&&file.getName().charAt(0)!='.') {
			System.out.println("Dir:"+file.getName());
			 getFilePath(file);
		}
	}
}

public String searchFileInFolder( File currentDir) {
	System.out.println("search");
	File[] list=currentDir.listFiles();
	for(File file: list) {
	    if(file.isDirectory()) {
	    	System.out.println("serdfke"+file.getName());
	   }
	   
	    
	}
	return null;
}
public static void main(String[] args) {
	CustomReport c=new CustomReport();
	c.setFileName("testng");
	c.getFilePath();
}
}

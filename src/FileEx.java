import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileEx {
	
	public static void main(String[] args)  {
		try{
		File file=new File("D://task2");
		if(file.isFile()){
			System.out.println("file exists");
			System.out.println("abs pth ="+file.getAbsolutePath());
			System.out.println("pth= "+file.getCanonicalPath());
		}
		else if (file.isDirectory()){
			System.out.println("directory");
			System.out.println("abs pth ="+file.getAbsolutePath());
			System.out.println("pth= "+file.getCanonicalPath());
			System.out.println("can write="+file.canWrite());
			System.out.println("parent file="+file.getParentFile());
			java.text.SimpleDateFormat sf = new java.text.SimpleDateFormat("yyyyMMddHHmmssSSS");
			Date d=new Date(file.lastModified());
			String time=sf.format(d);
			System.out.println("time"+time);
			System.out.println("parent="+file.getParent());
			System.out.println("name ="+file.getName());
			System.out.println("usuable space: "+file.getUsableSpace()/(1024*1024*1024));
			System.out.println("total space: "+file.getTotalSpace()/(1024*1024*1024));
			System.out.println("free space: "+file.getFreeSpace()/(1024*1024*1024));
			
		}
		else{
			file.createNewFile();
		}
		
		}catch(IOException io){
			io.printStackTrace();
		}
	}

}

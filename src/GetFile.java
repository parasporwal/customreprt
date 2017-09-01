import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class GetFile {
	File curDir;
	String fileName;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public GetFile(){
		curDir=new File(System.getProperty("user.dir"));
	}
	
	public File  getFile(File file){
		
		if(file!=null &&file.getName().equals(getFileName())){
			return file;
		}
		
		System.out.println("**************"+file.getName()+"*************");
		File[] listOfFiles=file.listFiles();
		for(File f: listOfFiles){
			if(f.isDirectory()){
				File temp=getFile(f);
				if(temp==null){
					System.out.println("null");
				}
				else
				{
					System.out.println("returned value"+temp.getName());
					return temp;
				}
			}
			else if(f.isFile()){
				if(f.getName().equalsIgnoreCase(getFileName())){
					System.out.println("@@@@file found:"+f.getName());
					return f;
				}
			}
		}
		return null;
	}
	
	Map<String,Map<String,String>> parseFile(File file) throws FileNotFoundException{
		FileInputStream in=new FileInputStream(file);
		Map<String,Map<String,String>> report=new HashMap<>();
		try{
			SAXParserFactory factory=SAXParserFactory.newInstance();
			SAXParser saxParser=factory.newSAXParser();
			
			DefaultHandler handler=new DefaultHandler(){
				public void startElement(String uri, String localName,String qName,
		                Attributes attributes) throws SAXException {
					Map<String,String> map;
                     if(qName.equalsIgnoreCase("test-method")){
                    	 map=new HashMap<String,String>();
                    	 for(int i=0;i<attributes.getLength();i++){
                    		 map.put(attributes.getQName(i), attributes.getValue(i));
                    	 }
                    	 report.put(qName, map);
                     }
			}

			public void endElement(String uri, String localName,
				String qName) throws SAXException {

           
			}

			public void characters(char ch[], int start, int length) throws SAXException {


			}

			};
			saxParser.parse(in, handler);
		}
		catch(Exception e){
			
		}
		return report;
		
	}
	public static void main(String[] args) throws FileNotFoundException {
		GetFile ob=new GetFile();
		ob.setFileName("testng-results.xml");
	 File f=ob.getFile();
	 System.out.println(":"+f.getName());
	 System.out.println(ob.parseFile(f));
	}

	private File getFile() {
		 return  getFile(curDir);
	}

}

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class GetFile {
	File curDir;
	String fileName;
	String path;

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
					path=f.getAbsolutePath();
					return f;
				}
			}
		}
		return null;
	}
	
	Map<String,Map<String,String>> parseFile(File file) throws FileNotFoundException{
		FileInputStream in=new FileInputStream(file);
		final Map<String,Map<String,String>> report=new HashMap<String,Map<String,String>>();
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
                    	 System.out.println("map:"+map);
                    	 report.put(attributes.getValue("name"), map);
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
	public static void main(String[] args) throws IOException {
		int passed=0;
		int skipped=0;
		int failed=0;
		Map<String,String>internalMap;
		GetFile ob=new GetFile();
		ob.setFileName("testng-results.xml");
	 File f=ob.getFile();
	 System.out.println(":"+f.getName());
	Map<String,Map<String,String>>report=ob.parseFile(f);
	Set<Entry<String,Map<String,String>>> set=report.entrySet();
	Iterator<Entry<String,Map<String,String>>> itr=set.iterator();
	while(itr.hasNext()) {
		Map.Entry<String,Map<String,String>> entry=itr.next();
		System.out.println(entry.getKey()+":"+entry.getValue());
		internalMap=entry.getValue();
		if(internalMap.get("status").equalsIgnoreCase("PASS")) {
			passed++;
		}
		else if(internalMap.get("status").equalsIgnoreCase("FAIL")) {
			failed++;
		}
		else if(internalMap.get("status").equalsIgnoreCase("SKIP")) {
			skipped++;
		}
	}
	
	String html=ob.getHTMLReport(passed,skipped,failed);
	System.out.println("$$$$$$$$:::::"+html);
	System.out.println("passed:"+passed+"failed:"+failed+"skipped:"+skipped);
	
	ob.generateReport(html);
	}

	private void generateReport(String html) throws IOException {
		File file =new File(path.substring(0, path.lastIndexOf('\\'))+"/customReport.html");
		if(file.exists())
			file.delete();
		FileWriter fw=new FileWriter(file);
		PrintWriter outwriter=new PrintWriter(fw);
		outwriter.println(html);
		outwriter.flush();
			
		
	}

	private  String getHTMLReport(int passed, int skipped, int failed) {
		String HTML="<html><head>$head</head><body>$body</body></html>";
		String script=" <script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>\r\n" + 
				"    <script type=\"text/javascript\">\r\n" + 
				"  google.charts.load('current', {'packages':['corechart']});\r\n" + 
				"  google.charts.setOnLoadCallback(drawChart);"
				+ " function drawChart() {\r\n" + 
				"        var data = new google.visualization.DataTable();";
		
		String dataforMap="data.addRows(['passed',"+passed+"],['failed',"+failed+"],['skipped',"+skipped+"]);";
		String options=" var options = {'title':'Test Run ',\r\n" + 
				"                       'width':400,\r\n" + 
				"                       'height':300};"
				+ " var chart = new google.visualization.PieChart(document.getElementById('chart_div'));\r\n" + 
				"        chart.draw(data, options);\r\n" + 
				"      }\r\n" + 
				"    </script>\r\n" ;
		
		String repBody=" <div id=\"chart_div\"></div>";
		HTML=HTML.replace("$head", script+dataforMap+options);
		HTML=HTML.replace("$body",repBody);
		
		
		return HTML;
	}

	private File getFile() {
		 return  getFile(curDir);
	}

}

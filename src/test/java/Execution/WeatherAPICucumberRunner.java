package Execution;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WeatherAPICucumberRunner {
	
	@BeforeAll
    public static void before() {
        System.setProperty("karate.env", "SIT");
    }
	
	@Test
	  public void testParallel() throws InterruptedException, IOException

	{
			String createDirectory = callCreateDirectory();
			String sureFireReportPath = callSureFireReportPath();
			String karateReportPath= callkarateReportPath(createDirectory);
			
			//For not running parallely as these are E2E APIs send parallel thread count as 1:-
			Results results = Runner.path("classpath:OpenWeatherAPIFeatures").tags("@OpenWeatherMapAPIs").parallel(1);
			
			generateReport(sureFireReportPath,karateReportPath);
			
	        assertTrue(true);
	    }
	    
	public static String callCreateDirectory() throws InterruptedException, IOException
	{
		LocalDateTime CurrentDtTime = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm_ss");
		String reportDir = CurrentDtTime.format(dtf);

			
		return(reportDir);
	}

	public static String callSureFireReportPath()  throws InterruptedException, IOException
	{
		String sureFirePath = "target/surefire-reports/";
		return(sureFirePath);
		
	}

	public static String callkarateReportPath(String DirPath) throws InterruptedException, IOException
	{
		String ReportDirPath =" WeatherAPI_Reports_"+DirPath;
		String karateReportPath = System.getProperty("user.dir")+ "//target//"+ReportDirPath;
		File ReportDirectory = new File(karateReportPath);
		
		Files.createDirectory(ReportDirectory.toPath());
							    		   		
		return(karateReportPath);
	}
	
	
	public static void generateReport(String sureFireReportPath, String karateReportPath) throws InterruptedException 
		{        
	        Collection<File> jsonFiles = FileUtils.listFiles(new File(sureFireReportPath), new String[] {"json"}, true);
	        List<String> jsonPaths = new ArrayList<String>(jsonFiles.size());
	        jsonFiles.forEach(file -> jsonPaths.add(file.getAbsolutePath()));
	             
	        Configuration config = new Configuration(new File(karateReportPath), "Open Weather Map APIs Automation Test Execution Summary");
	        ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
	        reportBuilder.generateReports();  
	       
	    }	
}
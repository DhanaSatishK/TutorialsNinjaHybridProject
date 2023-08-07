package TutorialsNinja.QAFox.Base;

import java.time.Duration;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import TutorialsNinja.QAFox.Utils.Utilities;

public class Base {
	WebDriver driver;
	public Properties prop;
	public Properties dataProp;
	
	
	
	public Base() {
		prop=new Properties();
		File propFile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\TutorialsNinja\\QAFox\\config\\Config.properties");
		
		try {
		dataProp=new Properties();
		File dataPropFile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\TutorialsNinja\\QAFox\\testdata\\testdata.properties");
		FileInputStream fis1=new FileInputStream(dataPropFile);
		dataProp.load(fis1);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		try {
		FileInputStream fis=new FileInputStream(propFile);
		prop.load(fis);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
	}
	
	public WebDriver initializeBrowserAndOpenApplication(String browserName) {
		
		if(browserName.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
		}else if(browserName.equalsIgnoreCase("firefox")) {
			driver=new FirefoxDriver();
		}else if(browserName.equalsIgnoreCase("edge")) {
			driver=new EdgeDriver();
		}else if(browserName.equalsIgnoreCase("safari")) {
			driver=new SafariDriver();
		}
					
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.get(prop.getProperty("url"));
		
		return driver;
	}

}

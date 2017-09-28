import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class Statuscode {

	public static void main(String[] args) {
     Statuscode s = new Statuscode();
     try
     {
    	 s.statusCode();
     }
     catch(Exception e)
     {
    	 System.out.println("Please check your network connection. If you are getting this message, then it means your internet is conflicting with openConnection() method");
     }
	}
	
	public void statusCode()throws Exception
	{
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.lego.com/en-us/juniors");
		driver.navigate().refresh();
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		ArrayList<String> links = new ArrayList<String>();
		List<WebElement> list = driver.findElements(By.xpath("//a[contains(@href,'www.lego.com/en-us/juniors')]"));
		for(int i=0; i<list.size(); i++)
		{
			links.add(list.get(i).getAttribute("href"));
			Set<String> s = new HashSet<String>();
			s.addAll(links);
			links.clear();
			links.addAll(s);
		}
		list.clear();
		for(int j=0; j<links.size(); j++)
		{
			driver.get(links.get(j));
			
				URL url = new URL(links.get(j));
	            HttpURLConnection http = (HttpURLConnection)url.openConnection();
	            int statuscode = http.getResponseCode();
	            System.out.println(statuscode);
	            
			list = driver.findElements(By.xpath("//a[contains(@href,'https://www.lego.com/en-us/juniors')]"));
			for(int k=0; k<list.size(); k++)
			{
				links.add(list.get(k).getAttribute("href"));
				Set<String> s1 = new HashSet<>();
				s1.addAll(links);
				links.clear();
				links.addAll(s1);
			}
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			System.out.println("link name"+links.get(j));
		}
		System.out.println("Crawl Completed");
		System.out.println("No of Urls:"+links.size());	
	}

}





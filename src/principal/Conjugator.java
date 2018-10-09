package principal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Conjugator {

	public List<String> conjugate(List<String> sentimentList) {
		
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Desenvolvimento\\Downloads\\TCC\\Trabalho de graduação II\\selenium-java-3.14.0\\chromedriver.exe");

		WebDriver driver = null;
		String address = "https://www.conjugacao.com.br/verbo-";
		
		List<String> verbs = new ArrayList<String>();

		try {

			driver = new ChromeDriver();

			//driver.manage().timeouts().implicitlyWait(01, TimeUnit.SECONDS);
			//driver.manage().window().maximize();
			
			for (int j = 0; j < sentimentList.size(); j++) {
				
				System.out.println(j);
			
				driver.get(address + sentimentList.get(j) + "/");
				//Thread.sleep(100);
			
				int conjugate = driver.findElements(By.xpath("//*[@id=\"conjugacao\"]/div[1]/div/div[1]/p/span/span")).size();
			
				if (conjugate > 0) {
				
					for (int i = 1; i <= conjugate; i++) {
						if (!driver.findElement(By.xpath("//*[@id=\"conjugacao\"]/div[1]/div/div[1]/p/span/span[" + i + "]")).getText().equals("--")) {
							System.out.println(driver.findElement(By.xpath("//*[@id=\"conjugacao\"]/div[1]/div/div[1]/p/span/span[" + i + "]/span[2]")).getText());
							verbs.add(driver.findElement(By.xpath("//*[@id=\"conjugacao\"]/div[1]/div/div[1]/p/span/span[" + i + "]/span[2]")).getText() + "%" + j + "@");
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (driver != null) {
				driver.close();
			}
		}
		return verbs;
	}
}

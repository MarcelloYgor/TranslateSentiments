package principal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Main {

	public static void main(String [] args) {
		
		String palavra = "fear";
		String inicio = "https://translate.google.com/?hl=pt#en/pt/";
		System.out.println("Traduzindo...");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Desenvolvimento\\Downloads\\TCC\\Trabalho de graduação II\\selenium-java-3.14.0\\chromedriver.exe");
		WebDriver driver = null;
		try {
			/*URLConnection connection = new URL(inicio + palavra).openConnection();
			connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
			connection.connect();
			*/
			
			driver = new ChromeDriver();
			
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.get(inicio + palavra);
			Thread.sleep(1000);
			
			System.out.println(driver.findElement(By.id("result_box")).getText());
			
			int linhas = driver.findElements(By.xpath("//*[@id=\"gt-lc\"]/div[2]/div[2]/div/div/div[2]/table/tbody/tr")).size();
			
			for (int i = 1; i <= linhas; i++) {
				
				if (driver.findElement(By.xpath("//*[@id=\"gt-lc\"]/div[2]/div[2]/div/div/div[2]/table/tbody/tr[" + i + "]/td[1]")).getText().equals("substantivo")) {
					System.out.println("É substantivo");
				}
				
				if (driver.findElement(By.xpath("//*[@id=\"gt-lc\"]/div[2]/div[2]/div/div/div[2]/table/tbody/tr[" + i + "]/td[1]")).getText().equals("verbo")) {
					System.out.println("É verbo");
				}
				
				if (driver.findElement(By.xpath("//*[@id=\"gt-lc\"]/div[2]/div[2]/div/div/div[2]/table/tbody/tr[" + i + "]/td[1]")).getText().equals("adjetivo")) {
					System.out.println("É adjetivo");
				}
				
				
				if (driver.findElements(By.xpath("//*[@id=\"gt-lc\"]/div[2]/div[2]/div/div/div[2]/table/tbody/tr[" + i +"]/td")).size() > 2) {
					System.out.println(driver.findElement(By.xpath("//*[@id=\"gt-lc\"]/div[2]/div[2]/div/div/div[2]/table/tbody/tr[" + i + "]/td[3]")).getText());
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.close();
		}
		
		
	}
}

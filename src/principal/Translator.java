package principal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Translator {

	public List<String> translateList(List<String> wordTable) {

		List<String> result = new ArrayList<>();
		
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Desenvolvimento\\Downloads\\TCC\\Trabalho de graduação II\\selenium-java-3.14.0\\chromedriver.exe");

		WebDriver driver = null;
		String endereco = "https://translate.google.com/?hl=pt#en/pt/";
		String resultBox;

		try {

			driver = new ChromeDriver();

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			//driver.manage().window().maximize();

			for (int j = 0; j < wordTable.size(); j++) {
				
				System.out.println(j);

				driver.get(endereco + wordTable.get(j));
				Thread.sleep(1000);

				resultBox = driver.findElement(By.id("result_box")).getText();
				result.add(resultBox + "%" + j + "@");

				int linhas = driver
						.findElements(By.xpath("//*[@id=\"gt-lc\"]/div[2]/div[2]/div/div/div[2]/table/tbody/tr"))
						.size();

				for (int i = 1; i <= linhas; i++) {
					
					if (!driver
							.findElement(By.xpath(
									"//*[@id=\"gt-lc\"]/div[2]/div[2]/div/div/div[2]/table/tbody/tr[" + i + "]/td[1]"))
							.getText().equals("substantivo")
							&& !driver.findElement(By.xpath(
									"//*[@id=\"gt-lc\"]/div[2]/div[2]/div/div/div[2]/table/tbody/tr[" + i + "]/td[1]"))
									.getText().equals("verbo")
							&& !driver.findElement(By.xpath(
									"//*[@id=\"gt-lc\"]/div[2]/div[2]/div/div/div[2]/table/tbody/tr[" + i + "]/td[1]"))
									.getText().equals("adjetivo")
							&& driver.findElements(By.xpath(
									"//*[@id=\"gt-lc\"]/div[2]/div[2]/div/div/div[2]/table/tbody/tr[" + i + "]/td"))
									.size() != 1) {
						if (driver
								.findElements(By.xpath(
										"//*[@id=\"gt-lc\"]/div[2]/div[2]/div/div/div[2]/table/tbody/tr[" + i + "]/td"))
								.size() == 4) {

							if (driver
									.findElement(
											By.xpath("//*[@id=\"gt-lc\"]/div[2]/div[2]/div/div/div[2]/table/tbody/tr["
													+ i + "]/td[1]/div[1]"))
									.getAttribute("Title") != null) {
								if (driver
										.findElement(
												By.xpath("//*[@id=\"gt-lc\"]/div[2]/div[2]/div/div/div[2]/table/tbody/tr["
														+ i + "]/td[1]/div[1]"))
										.getAttribute("Title").equals("Tradução comum")) {
									if (!driver.findElement(
											By.xpath("//*[@id=\"gt-lc\"]/div[2]/div[2]/div/div/div[2]/table/tbody/tr[" + i + "]/td[3]"))
												.getText().equals(resultBox) &&
												!driver.findElement(
														By.xpath("//*[@id=\"gt-lc\"]/div[2]/div[2]/div/div/div[2]/table/tbody/tr[" + i + "]/td[3]"))
															.getText().equals("")) {
	
										result.add(driver.findElement(
												By.xpath("//*[@id=\"gt-lc\"]/div[2]/div[2]/div/div/div[2]/table/tbody/tr[" + i
														+ "]/td[3]"))
												.getText() + "%" + j + "@");
									}
								}
							}
						} else {

							if (driver
									.findElement(
											By.xpath("//*[@id=\"gt-lc\"]/div[2]/div[2]/div/div/div[2]/table/tbody/tr["
													+ i + "]/td[1]/div[1]"))
									.getAttribute("Title") != null) {
								if (driver
										.findElement(
												By.xpath("//*[@id=\"gt-lc\"]/div[2]/div[2]/div/div/div[2]/table/tbody/tr["
														+ i + "]/td[1]/div[1]"))
										.getAttribute("Title").equals("Tradução comum")) {
									if (!driver.findElement(
											By.xpath("//*[@id=\"gt-lc\"]/div[2]/div[2]/div/div/div[2]/table/tbody/tr[" + i
													+ "]/td[2]"))
											.getText().equals(resultBox) && 
											!driver.findElement(
													By.xpath("//*[@id=\"gt-lc\"]/div[2]/div[2]/div/div/div[2]/table/tbody/tr[" + i
															+ "]/td[2]"))
													.getText().equals("")) {
										result.add(driver.findElement(
												By.xpath("//*[@id=\"gt-lc\"]/div[2]/div[2]/div/div/div[2]/table/tbody/tr[" + i
														+ "]/td[2]"))
												.getText() + "%" + j + "@");
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.close();
		}
		return result;
	}
	
	public static void main(String [] args) {
		
		Translator t = new Translator();
		
		List<String> wordsTranslated = new ArrayList<>();
		wordsTranslated.add("attack");
		t.translateList(wordsTranslated);
	}
}

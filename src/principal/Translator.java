package principal;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Translator {

	public void translateList(List<String> wordTable) {

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Desenvolvimento\\Downloads\\TCC\\Trabalho de graduação II\\selenium-java-3.14.0\\chromedriver.exe");

		WebDriver driver = null;
		String endereco = "https://translate.google.com/?hl=pt#en/pt/";

		try {

			driver = new ChromeDriver();

			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			driver.manage().window().maximize();

			for (int j = 0; j < 10; j++) {

				driver.get(endereco + wordTable.get(j));
				Thread.sleep(1000);

				System.out.println(driver.findElement(By.id("result_box")).getText());

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
									.getAttribute("Title").equals("Tradução comum")) {
								System.out.println(driver.findElement(
										By.xpath("//*[@id=\"gt-lc\"]/div[2]/div[2]/div/div/div[2]/table/tbody/tr[" + i
												+ "]/td[3]"))
										.getText());
							}
						} else {

							if (driver
									.findElement(
											By.xpath("//*[@id=\"gt-lc\"]/div[2]/div[2]/div/div/div[2]/table/tbody/tr["
													+ i + "]/td[1]/div[1]"))
									.getAttribute("Title").equals("Tradução comum")) {
								System.out.println(driver.findElement(
										By.xpath("//*[@id=\"gt-lc\"]/div[2]/div[2]/div/div/div[2]/table/tbody/tr[" + i
												+ "]/td[2]"))
										.getText());
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
	}
}

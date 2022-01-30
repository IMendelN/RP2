import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteLogin {
	public static WebDriver driver = new ChromeDriver();
	
	@Before
	public final void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", ".\\src\\main\\resources\\chromedriver.exe");
	}

	@Test
	public void test() throws InterruptedException {
		driver.manage().window().setPosition(new Point(100, 100));
		driver.manage().window().setSize(new Dimension(675, 450));
		driver.get("http://www.lesse.com.br/tools/silverbullet/rp2");
		Thread.sleep(5000);
		driver.findElement(By.id("email")).sendKeys("dionasmuller.aluno@unipampa.edu.br");
		assertEquals("dionasmuller.aluno@unipampa.edu.br",driver.findElement(By.id("email")).getAttribute("value"));
		driver.findElement(By.id("password")).sendKeys("senhaTeste");
		assertEquals("senhaTeste",driver.findElement(By.id("password")).getAttribute("value"));
		driver.findElement(By.id("login-submit")).click();
		Thread.sleep(1000);
		assertEquals("http://www.lesse.com.br/tools/silverbullet/rp2/projects", driver.getCurrentUrl());
	}
	
	 @After
	  public final void after() throws InterruptedException {
		 Thread.sleep(5000);
		 driver.quit();
	 }

}

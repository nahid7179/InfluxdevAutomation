package appsTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.testng.annotations.*;

import io.appium.java_client.android.AndroidDriver;

public class TesttingApps {
	public AndroidDriver driver;

	@SuppressWarnings("deprecation")

	@BeforeTest
	public void setUp() throws MalformedURLException {
		DesiredCapabilities apps = new DesiredCapabilities();
		apps.setCapability("appium:deviceName", "Pixel7Pro");
		apps.setCapability("platformName", "android");
		apps.setCapability("appium:automationName", "UiAutomator2");
		apps.setCapability("appium:udid", "emulator-5554");
		apps.setCapability("appium:appPackage", "com.saucelabs.mydemoapp.rn");
		apps.setCapability("appium:appActivity", "com.saucelabs.mydemoapp.rn.MainActivity");
		URL remoteurl = new URL("http://127.0.0.1:4723");
		driver = new AndroidDriver(remoteurl, apps);
	}

	@Test(priority = 1)
	public void AppTestOne() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		System.out.println("we are in the app");

		// Verify Products page
		WebElement product = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Products\"]"));
		String pageName = product.getText();
		System.out.println(pageName);
		Thread.sleep(5000);
		// Assertion with page title
		Assert.assertEquals(pageName, "Products");
		System.out.println("Assertion completed");
		Thread.sleep(5000);
	}

	@Test(priority = 2)
	public void AppTestTwo() throws InterruptedException {
		// Go to details of a product
		driver.findElement(By.xpath(
				"//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.ImageView"))
				.click();
		// Incress product number three
		driver.findElement(
				By.xpath("//android.view.ViewGroup[@content-desc=\"counter plus button\"]/android.widget.ImageView"))
				.click();
		driver.findElement(
				By.xpath("//android.view.ViewGroup[@content-desc=\"counter plus button\"]/android.widget.ImageView"))
				.click();

	}

	@Test(priority = 3)
	public void Addtocart() throws InterruptedException {
		// Add product to cart

		driver.findElement(By.xpath("//android.widget.TextView[@text=\"Add To Cart\"]")).click();
		// click in the cart icon
		driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"cart badge\"]/android.widget.ImageView"))
				.click();
		// Assertion with my cart
		Thread.sleep(5000);
	}
	@Test(priority = 4)
	public void GoToCatalogAndAddSecondProduct() throws InterruptedException {
		// Open the hamburger menu
		driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"open menu\"]/android.widget.ImageView"))
				.click();
		Thread.sleep(2000);
 
		// Click the Catalog option from the menu
		driver.findElement(By.xpath("//android.widget.TextView[@text=\"Catalog\"]")).click();
		Thread.sleep(2000);
 
		// Verify Products page loaded again
		WebElement product = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Products\"]"));
		Assert.assertEquals(product.getText(), "Products");
 
		// Open a second product (index 2 this time, so it's a different item than step 2)
		driver.findElement(By.xpath(
				"//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup[2]/android.view.ViewGroup[1]/android.widget.ImageView"))
				.click();
 
		// Increase quantity to 3 (two clicks from default 1)
		driver.findElement(
				By.xpath("//android.view.ViewGroup[@content-desc=\"counter plus button\"]/android.widget.ImageView"))
				.click();
		driver.findElement(
				By.xpath("//android.view.ViewGroup[@content-desc=\"counter plus button\"]/android.widget.ImageView"))
				.click();
 
		// Add second product to cart
		driver.findElement(By.xpath("//android.widget.TextView[@text=\"Add To Cart\"]")).click();
 
		// Go to the cart
		driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"cart badge\"]/android.widget.ImageView"))
				.click();
		Thread.sleep(3000);
	}

	@Test(priority = 5)
	public void ReduceOrRemoveCartItem() throws InterruptedException {
		// Reduce the quantity of the first cart item by one using the minus/counter button
		driver.findElement(
				By.xpath("(//android.view.ViewGroup[@content-desc=\"counter minus button\"]/android.widget.ImageView)[1]"))
				.click();
		Thread.sleep(2000);
 
		// If a cart item's quantity is reduced to zero the app typically removes it automatically.
		
		if (!driver.findElements(By.xpath("//android.view.ViewGroup[@content-desc=\"Remove product\"]")).isEmpty()) {
			driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Remove product\"]")).click();
		}
		Thread.sleep(2000);
	}
 
	@Test(priority = 6)
	public void Login() throws InterruptedException {
		// Verify Proceed To Checkout button
		driver.findElement(By.xpath("//android.widget.TextView[@text=\"Proceed To Checkout\"]")).click();

		// Verify Username field
		driver.findElement(By.xpath("//android.widget.EditText[@content-desc=\"Username input field\"]"))
				.sendKeys("bob@example.com");

		// Verify Password field

		driver.findElement(By.xpath("//android.widget.EditText[@content-desc=\"Password input field\"]"))
				.sendKeys("10203040");

		// Verify Login button
		driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Login button\"]")).click();
		Thread.sleep(5000);
	}

	@Test(priority = 7)
	public void Checkout() throws InterruptedException {
		// Verify address page
		
		// Full Name
		driver.findElement(By.xpath("//android.widget.EditText[@content-desc=\"Full Name* input field\"]"))
				.sendKeys("Rebecca Winter");
		// Address
		driver.findElement(By.xpath("//android.widget.EditText[@content-desc=\"Address Line 1* input field\"]"))
				.sendKeys("Mandorley 112");
		// City
		driver.findElement(By.xpath("//android.widget.EditText[@content-desc=\"City* input field\"]"))
				.sendKeys("Truno");

		// State
		driver.findElement(By.xpath("//android.widget.EditText[@content-desc=\"State/Region input field\"]"))
				.sendKeys("Cornwall");

		// Zip Code
		driver.findElement(By.xpath("//android.widget.EditText[@content-desc=\"Zip Code* input field\"]"))
				.sendKeys("89750");

		// Country
		driver.findElement(By.xpath("//android.widget.EditText[@content-desc=\"Country* input field\"]"))
				.sendKeys("United Kingdom");
		driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"To Payment button\"]")).click();

		Thread.sleep(5000);
	}

	@Test(priority = 8)
	public void Payment() throws InterruptedException {
		// To Payment
		
		// Full Name
		driver.findElement(By.xpath("//android.widget.EditText[@content-desc=\"Full Name* input field\"]"))
				.sendKeys("Rebecca Winter");
		
		// Card Number
		driver.findElement(By.xpath("//android.widget.EditText[@content-desc=\"Card Number* input field\"]"))
				.sendKeys("325812657568789");
		
		// Expiration Date
		driver.findElement(By.xpath("//android.widget.EditText[@content-desc=\"Expiration Date* input field\"]"))
				.sendKeys("03/25");
		
		// Security Code
		driver.findElement(By.xpath("//android.widget.EditText[@content-desc=\"Security Code* input field\"]"))
				.sendKeys("123");
		
		// Review Order
		driver.findElement(By.xpath("//android.widget.TextView[@text=\"Review Order\"]")).click();
		
		// Place Order
		driver.findElement(By.xpath("//android.widget.TextView[@text=\"Review Order\"]")).click();
		Thread.sleep(5000);

		// Place Order

		driver.findElement(By.xpath("//android.widget.TextView[@text=\"Place Order\"]")).click();
	}

	@AfterTest
	public void closeApp() {
		driver.quit();
	}
}

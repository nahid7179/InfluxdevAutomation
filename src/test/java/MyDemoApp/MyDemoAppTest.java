 package MyDemoApp;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;

import io.appium.java_client.android.AndroidDriver;

public class MyDemoAppTest {
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


	// ================= TEST 1 =================

	@Test(priority = 1)
	public void AppTestOne() throws InterruptedException {

	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

	    System.out.println("We are in the app");

	    // Verify Products page
	    WebElement product = driver.findElement( By.xpath("//android.widget.TextView[@text=\"Products\"]"));

	    // Assertion 1 - Products page is displayed
	    Assert.assertTrue(product.isDisplayed(),
	            "Products page is not displayed");

	    // Get page name
	    String pageName = product.getText();

	    System.out.println("Page Name: " + pageName);

	    // Assertion 2 - Verify page title
	    Assert.assertEquals(pageName, "Products",
	            "Page title is incorrect");

	    System.out.println("Products page assertion completed");

	    Thread.sleep(3000);
	}


	// ================= TEST 2 =================

	@Test(priority = 2)
	public void AppTestTwo() throws InterruptedException {

	    // Go to product details
	    WebElement productImage = driver.findElement(
	            By.xpath("//android.widget.ScrollView/android.view.ViewGroup/"
	                    + "android.view.ViewGroup[3]/android.view.ViewGroup[1]/"
	                    + "android.view.ViewGroup[1]/android.widget.ImageView"));

	    productImage.click();

	    Thread.sleep(2000);

	    // Assertion - Product details page is displayed
	    WebElement addToCartButton = driver.findElement(
	            By.xpath("//android.widget.TextView[@text=\"Add To Cart\"]"));

	    Assert.assertTrue(addToCartButton.isDisplayed(),
	            "Product details page is not displayed");

	    System.out.println("Product details page assertion completed");


	    // Increase product quantity
	    WebElement plusButton = driver.findElement(
	            By.xpath("//android.view.ViewGroup[@content-desc=\"counter plus button\"]/"
	                    + "android.widget.ImageView"));

	    plusButton.click();
	    plusButton.click();

	    Thread.sleep(2000);

	    // Verify quantity = 3
	    WebElement quantity = driver.findElement(
	            By.xpath("//android.widget.TextView[@text=\"3\"]"));

	    Assert.assertTrue(quantity.isDisplayed(),
	            "Product quantity 3 is not displayed");

	    Assert.assertEquals(quantity.getText(), "3",
	            "Product quantity is not 3");

	    System.out.println("Quantity assertion completed");
	}


	// ================= TEST 3 =================

	@Test(priority = 3)
	public void Addtocart() throws InterruptedException {

	    // Add product to cart
	    WebElement addToCart = driver.findElement(
	            By.xpath("//android.widget.TextView[@text=\"Add To Cart\"]"));

	    // Assertion - Add To Cart button displayed
	    Assert.assertTrue(addToCart.isDisplayed(),
	            "Add To Cart button is not displayed");

	    addToCart.click();

	    Thread.sleep(2000);

	    // Cart badge
	    WebElement cartBadge = driver.findElement(
	            By.xpath("//android.view.ViewGroup[@content-desc=\"cart badge\"]/"
	                    + "android.widget.ImageView"));

	    // Assertion - Cart icon displayed
	    Assert.assertTrue(cartBadge.isDisplayed(),
	            "Cart icon is not displayed");

	    cartBadge.click();

	    Thread.sleep(3000);

	    // Verify My Cart page
	    WebElement myCart = driver.findElement(
	            By.xpath("//android.widget.TextView[@text=\"My Cart\"]"));

	    Assert.assertTrue(myCart.isDisplayed(),
	            "My Cart page is not displayed");

	    Assert.assertEquals(myCart.getText(), "My Cart",
	            "My Cart page title is incorrect");

	    System.out.println("My Cart assertion completed");
	}


	// ================= TEST 4 =================

	@Test(priority = 4)
	public void Login() throws InterruptedException {

	    // Verify Proceed To Checkout button
	    WebElement checkoutButton = driver.findElement(
	            By.xpath("//android.widget.TextView[@text=\"Proceed To Checkout\"]"));

	    Assert.assertTrue(checkoutButton.isDisplayed(),
	            "Proceed To Checkout button is not displayed");

	    checkoutButton.click();

	    Thread.sleep(3000);

	    // Verify Username field
	    WebElement username = driver.findElement(
	            By.xpath("//android.widget.EditText[@content-desc=\"Username input field\"]"));

	    Assert.assertTrue(username.isDisplayed(),
	            "Username field is not displayed");

	    username.sendKeys("bob@example.com");


	    // Verify Password field
	    WebElement password = driver.findElement(
	            By.xpath("//android.widget.EditText[@content-desc=\"Password input field\"]"));

	    Assert.assertTrue(password.isDisplayed(),
	            "Password field is not displayed");

	    password.sendKeys("10203040");


	    // Verify Login button
	    WebElement loginButton = driver.findElement(
	            By.xpath("//android.view.ViewGroup[@content-desc=\"Login button\"]"));

	    Assert.assertTrue(loginButton.isDisplayed(),
	            "Login button is not displayed");

	    loginButton.click();

	    Thread.sleep(5000);

	    // Verify checkout/address page
	    WebElement fullName = driver.findElement(
	            By.xpath("//android.widget.EditText[@content-desc=\"Full Name* input field\"]"));

	    Assert.assertTrue(fullName.isDisplayed(),
	            "Checkout address page is not displayed");

	    System.out.println("Login successful - Checkout page displayed");
	}


	// ================= TEST 5 =================

	@Test(priority = 5)
	public void Checkout() throws InterruptedException {

	    // Full Name
	    WebElement fullName = driver.findElement(
	            By.xpath("//android.widget.EditText[@content-desc=\"Full Name* input field\"]"));

	    Assert.assertTrue(fullName.isDisplayed(),
	            "Full Name field is not displayed");

	    fullName.sendKeys("Rebecca Winter");


	    // Address
	    WebElement address = driver.findElement(
	            By.xpath("//android.widget.EditText[@content-desc=\"Address Line 1* input field\"]"));

	    Assert.assertTrue(address.isDisplayed(),
	            "Address field is not displayed");

	    address.sendKeys("Mandorley 112");


	    // City
	    WebElement city = driver.findElement(
	            By.xpath("//android.widget.EditText[@content-desc=\"City* input field\"]"));

	    Assert.assertTrue(city.isDisplayed(),
	            "City field is not displayed");

	    city.sendKeys("Truno");


	    // State
	    WebElement state = driver.findElement(
	            By.xpath("//android.widget.EditText[@content-desc=\"State/Region input field\"]"));

	    Assert.assertTrue(state.isDisplayed(),
	            "State field is not displayed");

	    state.sendKeys("Cornwall");


	    // Zip Code
	    WebElement zipCode = driver.findElement(
	            By.xpath("//android.widget.EditText[@content-desc=\"Zip Code* input field\"]"));

	    Assert.assertTrue(zipCode.isDisplayed(),
	            "Zip Code field is not displayed");

	    zipCode.sendKeys("89750");


	    // Country
	    WebElement country = driver.findElement(
	            By.xpath("//android.widget.EditText[@content-desc=\"Country* input field\"]"));

	    Assert.assertTrue(country.isDisplayed(),
	            "Country field is not displayed");

	    country.sendKeys("United Kingdom");


	    // To Payment
	    WebElement toPayment = driver.findElement(
	            By.xpath("//android.view.ViewGroup[@content-desc=\"To Payment button\"]"));

	    Assert.assertTrue(toPayment.isDisplayed(),
	            "To Payment button is not displayed");

	    toPayment.click();

	    Thread.sleep(5000);


	    // Verify Payment page
	    WebElement cardNumber = driver.findElement(
	            By.xpath("//android.widget.EditText[@content-desc=\"Card Number* input field\"]"));

	    Assert.assertTrue(cardNumber.isDisplayed(),
	            "Payment page is not displayed");

	    System.out.println("Checkout information submitted successfully");
	}


	// ================= TEST 6 =================

	@Test(priority = 6)
	public void Payment() throws InterruptedException {

	    // Full Name
	    WebElement fullName = driver.findElement(
	            By.xpath("//android.widget.EditText[@content-desc=\"Full Name* input field\"]"));

	    Assert.assertTrue(fullName.isDisplayed(),
	            "Payment Full Name field is not displayed");

	    fullName.sendKeys("Rebecca Winter");


	    // Card Number
	    WebElement cardNumber = driver.findElement(
	            By.xpath("//android.widget.EditText[@content-desc=\"Card Number* input field\"]"));

	    Assert.assertTrue(cardNumber.isDisplayed(),
	            "Card Number field is not displayed");

	    cardNumber.sendKeys("325812657568789");


	    // Expiration Date
	    WebElement expirationDate = driver.findElement(
	            By.xpath("//android.widget.EditText[@content-desc=\"Expiration Date* input field\"]"));

	    Assert.assertTrue(expirationDate.isDisplayed(),
	            "Expiration Date field is not displayed");

	    expirationDate.sendKeys("03 25");


	    // Security Code
	    WebElement securityCode = driver.findElement(
	            By.xpath("//android.widget.EditText[@content-desc=\"Security Code* input field\"]"));

	    Assert.assertTrue(securityCode.isDisplayed(),
	            "Security Code field is not displayed");

	    securityCode.sendKeys("123");


	    // Review Order
	    WebElement reviewOrder = driver.findElement(
	            By.xpath("//android.widget.TextView[@text=\"Review Order\"]"));

	    Assert.assertTrue(reviewOrder.isDisplayed(),
	            "Review Order button is not displayed");

	    reviewOrder.click();

	    Thread.sleep(3000);


	    // Verify Review Order page
	    WebElement placeOrder = driver.findElement(
	            By.xpath("//android.widget.TextView[@text=\"Place Order\"]"));

	    Assert.assertTrue(placeOrder.isDisplayed(),
	            "Place Order button is not displayed");

	    System.out.println("Review Order page assertion completed");


	    // Place Order
	    placeOrder.click();

	    Thread.sleep(5000);


	    // ================= FINAL ASSERTION =================

	    WebElement thankYouMessage = driver.findElement(
	            By.xpath("//android.widget.TextView[contains(@text,\"Thank\")]"));

	    Assert.assertTrue(thankYouMessage.isDisplayed(),
	            "Order confirmation message is not displayed");

	    System.out.println("Order placed successfully");
	}


	// ================= CLOSE APP =================

	@AfterTest
	public void closeApp() {

	    Assert.assertNotNull(driver,
	            "Driver is null");

	    driver.quit();

	    System.out.println("Application closed successfully");
	}
	
}

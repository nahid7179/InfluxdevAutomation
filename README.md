# MyDemoApp Appium Test Suite

Automated Appium + TestNG (Java) test suite for the **MyDemoApp** Android app
(`com.saucelabs.mydemoapp.rn`), a sample shopping app published by Sauce Labs
for practicing mobile test automation.

## What it covers

The suite walks through a full shopping journey:

1. Launch the app and verify the Products page loads.
2. Open a product, increase quantity to 3, and add it to the cart.
3. Navigate back to the product catalog via the hamburger menu and add a second product.
4. Reduce the quantity of (or remove) an item from the cart.
5. Proceed to checkout and log in.
6. Fill in the shipping/checkout form and continue to payment.
7. Fill in payment details, review the order, and place it.
8. Continue shopping and log out.

## Tech stack

- **Java 21**
- **Appium Java Client**
- **Selenium WebDriver**
- **TestNG**
- **UiAutomator2** driver (Android)

## Prerequisites

- Node.js and Appium server (`npm install -g appium`, then `appium driver install uiautomator2`)
- Android SDK + an emulator or physical device (`adb devices` should list it)
- The MyDemoApp APK installed on the device/emulator (`com.saucelabs.mydemoapp.rn`)
- Java 21+ and Maven/Gradle configured with Appium Java Client, Selenium, and TestNG

## Running the tests

1. Start an Android emulator (or connect a device) — update `appium:deviceName`
   and `appium:udid` in `setUp()` to match.
2. Start the Appium server:
   ```
   appium
   ```
3. Run the TestNG suite from your IDE or with Maven:
   ```
   mvn test
   ```

## Project structure

```
src/test/java/appsTest/TesttingApps.java   # Main test class, methods ordered by @Test(priority = n)
```

## Notes

- Locators are based on the app's `content-desc`/`text` accessibility attributes and
  may need adjusting via Appium Inspector if your build of MyDemoApp differs.
- Several test methods include diagnostic fallbacks that print `driver.getPageSource()`
  when an expected element isn't found, to help pinpoint the correct locator.
- Credentials and form data used in the tests are for the Sauce Labs demo app only.

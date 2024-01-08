package steps;

import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class ebebekSteps {

    public AndroidDriver driver;
    public WebDriverWait wait;
    protected float firstAmount;
    protected float secondAmount;

    //Elements By

    By notificationDenyBy = By.id("com.android.permissioncontroller:id/permission_deny_button");
    By navCategoryButtonBy = By.id("com.solidict.ebebek:id/navCategories");
    By menuCategoryBy = By.xpath("//*[contains(@text,\"Bebek Odası\")]");
    By menuSubCategoryBy = By.xpath("//*[contains(@text,\"Bebek Odası Aksesuar\")]");
    By filterButtonBy = By.id("com.solidict.ebebek:id/linearLayout_ProductFilter");
    By priceRangeBy = By.xpath("//*[contains(@text,\"Fiyat Aralığı\")]");
    By priceRange50_100By = By.xpath("//*[contains(@text,\"50 - 100\")]");
    By filterApplyBy = By.id("com.solidict.ebebek:id/btApplyFilter");
    By sortButtonBy = By.id("com.solidict.ebebek:id/linearLayout_ProductSorting");
    By sortMostReviewed = By.xpath("//*[contains(@text,\"Çok Değerlendirilenler\")]");
    By addToBoxFirstItemBy = By.xpath("(//*[contains(@text,'Sepete Ekle')])[1]");
    By navGoToBasketButtonBy = By.id("com.solidict.ebebek:id/navMyCarts");
    By itemBasketPriceBy = By.id("com.solidict.ebebek:id/textView_CartProduct_Price");
    By increaseAmountBy = By.id("com.solidict.ebebek:id/lytPlusQuantity");
    By checkoutButtonBy = By.id("com.solidict.ebebek:id/tvCompleteOrder");

    //Methods
    public float TextPriceToFloat(By id)
    {
        String stringAmount = (wait.until(ExpectedConditions.visibilityOfElementLocated(id)).getText());
        String formattedAmount = stringAmount.replace(",", ".");
        formattedAmount = formattedAmount.replace(" TL", "");
        return Float.parseFloat(formattedAmount);
    }

    @Before
    public void setup() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "Pixel 4 API 33");
        caps.setCapability("udid", "emulator-5554"); //DeviceId from "adb devices" command
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "11.0");
        //caps.setCapability("skipUnlock", "true");
        caps.setCapability("appPackage", "com.solidict.ebebek");
        caps.setCapability("appActivity", "com.ebebek.android.view.MainActivity");
        //caps.setCapability("noReset", "false");
        //caps.setCapability("autoGrantPermissions", "true");

        try {
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        wait = new WebDriverWait(driver, Duration.ofSeconds(30L));
    }

    @Given("Setup the driver")
    public void baslat() {
        setup();
    }

    @Given("the notification popup is denied.")
    public void theNotificationPopupIsDenied()
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(notificationDenyBy)).click();
    }

    @When("the user clicks category button")
    public void theUserClicksCategoryButton()
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(navCategoryButtonBy)).click();
    }

    @And("the user selects Bebek Odasi category")
    public void theUserSelectsBebekOdasiCategory()
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(menuCategoryBy)).click();
    }

    @And("the user selects Bebek Odasi Aksesuar sub category")
    public void theUserSelectsBebekBesigiSubCategory()
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(menuSubCategoryBy)).click();
    }

    @And("the user filters the price range betwen 50 to 100")
    public void theUserFiltersThePriceRangeBetwenTo()
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(filterButtonBy)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(priceRangeBy)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(priceRange50_100By)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(filterApplyBy)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(filterApplyBy)).click();
    }

    @And("the user selects sorting by most reviewed")
    public void theUserSelectsSortingByMostReviewed()
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(sortButtonBy)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(sortMostReviewed)).click();
    }

    @And("the user adds the first product to basket")
    public void theUserAddsTheFirstProductToBasket()
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(addToBoxFirstItemBy)).click();
    }

    @And("the user goes to basket")
    public void theUserGoesToBasket()
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(navGoToBasketButtonBy)).click();
    }

    @And("the user increases the amount by one")
    public void theUserIncreasesTheAmountByOne()
    {
        firstAmount = TextPriceToFloat(itemBasketPriceBy);
        wait.until(ExpectedConditions.visibilityOfElementLocated(increaseAmountBy)).click();
        secondAmount = TextPriceToFloat(itemBasketPriceBy);
    }

    @Then("the price should be matched with the current amount")
    public void thePriceShouldBeMatchedWithTheCurrentAmount()
    {
        Assert.assertTrue(secondAmount == firstAmount * 2);
    }

    @Then("the user clicks checkout button")
    public void theUserClicksCheckoutButton()
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutButtonBy)).click();
    }

    @After
    public void teardown() {
        driver.quit();
    }

}
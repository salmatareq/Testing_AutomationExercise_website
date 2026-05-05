import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Sign_Up {

    WebDriver driver;
    WebDriverWait wait;

    public Sign_Up(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Locators
    By name = By.xpath("//input[@data-qa='signup-name']");
    By email = By.xpath("//input[@data-qa='signup-email']");
    By signupBtn = By.xpath("//button[@data-qa='signup-button']");

    By titleMr = By.id("id_gender1");
    By password = By.id("password");
    By day = By.id("days");
    By month = By.id("months");
    By year = By.id("years");

    By firstName = By.id("first_name");
    By lastName = By.id("last_name");
    By address = By.id("address1");
    By state = By.id("state");
    By city = By.id("city");
    By zipcode = By.id("zipcode");
    By mobile = By.id("mobile_number");

    By createAccountBtn = By.xpath("//button[@data-qa='create-account']");

    By accountCreatedText = By.xpath("//h2[@data-qa='account-created'] | //h2[contains(text(),'Account Created')]");
    By continueBtn = By.xpath("//a[@data-qa='continue-button']");

    public void enterNameAndEmail(String userName, String userEmail) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(name)).sendKeys(userName);
        driver.findElement(email).sendKeys(userEmail);
        driver.findElement(signupBtn).click();
    }

    public void fillAccountInfo(String pass) {
        wait.until(ExpectedConditions.elementToBeClickable(titleMr)).click();
        driver.findElement(password).sendKeys(pass);

        driver.findElement(day).sendKeys("10");
        driver.findElement(month).sendKeys("May");
        driver.findElement(year).sendKeys("2000");
    }

    public void fillAddressInfo() {
        driver.findElement(firstName).sendKeys("Yara");
        driver.findElement(lastName).sendKeys("Ahmed");
        driver.findElement(address).sendKeys("Cairo Street");
        driver.findElement(state).sendKeys("Cairo");
        driver.findElement(city).sendKeys("Nasr City");
        driver.findElement(zipcode).sendKeys("12345");
        driver.findElement(mobile).sendKeys("01000000000");
    }

    public void createAccount() {
        wait.until(ExpectedConditions.elementToBeClickable(createAccountBtn)).click();
    }

    public boolean isAccountCreated() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(accountCreatedText)).isDisplayed();
        } catch (Exception e) {
            System.out.println("Account Created message not visible: " + e.getMessage());
            return false;
        }
    }

    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();
    }
}

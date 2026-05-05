import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Cart {
    WebDriver driver;
    WebDriverWait wait;

    public Cart(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateToCart() {
        driver.navigate().to("https://automationexercise.com/view_cart");
        driver.manage().window().maximize();
    }

    public void deleteItem(int id) {
        navigateToCart();

        By deleteBtn = By.cssSelector("a[data-product-id='" + id + "'].cart_quantity_delete");

        wait.until(ExpectedConditions.elementToBeClickable(deleteBtn)).click();
    }

    public int getCartProductId() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        By firstRow = By.xpath("//table[@id='cart_info_table']//tbody/tr[1]");

        WebElement row = wait.until(ExpectedConditions.visibilityOfElementLocated(firstRow));

        String id = row.getAttribute("id");
        int productId = Integer.parseInt(id.split("-")[1]);

        System.out.println("First product id = " + productId);

        return productId;
    }

    public void incrementItem(int amount) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        int id = getCartProductId();
        By productLink = By.xpath("//a[@href='/product_details/" + id + "']");
        wait.until(ExpectedConditions.elementToBeClickable(productLink)).click();


        WebElement qty = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("quantity")));
        qty.clear();
        qty.sendKeys(String.valueOf(amount));


        By addToCart = By.xpath("//button[contains(@class,'cart')]");
        wait.until(ExpectedConditions.elementToBeClickable(addToCart)).click();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        By viewCart = By.xpath("//a[.//u[text()='View Cart']]");
        wait.until(ExpectedConditions.elementToBeClickable(viewCart)).click();
    }

    public void proceedToCheckout() {

        driver.findElement(By.xpath("//a[normalize-space()='Proceed To Checkout']")).click();

    }

    public void addComment(String comment) {
        driver.findElement(By.name("message")).sendKeys(comment);

    }

    public void placeOrder() throws InterruptedException {
        driver.findElement(By.cssSelector("a[href='/payment']")).click();
        driver.findElement(By.name("name_on_card")).sendKeys("salma");
        driver.findElement(By.name("card_number"))
                .sendKeys("4111111111111111");
        Thread.sleep(10);
        driver.findElement(By.name("cvc"))
                .sendKeys("123");


        driver.findElement(By.name("expiry_month"))
                .sendKeys("12");


        driver.findElement(By.name("expiry_year"))
                .sendKeys("2027");
        driver.findElement(By.id("submit"))
                .click();
        Thread.sleep(10);
    }

    public void downloadInvoice() {
        driver.findElement(By.cssSelector("a[href*='download_invoice']")).click();
    }

    public void continueGoingToHome() throws InterruptedException {
        Thread.sleep(10);
        driver.findElement(By.xpath("//a[@data-qa='continue-button']")).click();
    }

}
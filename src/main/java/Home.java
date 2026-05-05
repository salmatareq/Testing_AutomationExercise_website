import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Home {

    WebDriver driver;

    Home(WebDriver driver) {
        this.driver = driver;
    }

    public void openCategory(String category) throws InterruptedException {
        driver.findElement(
                By.xpath("//a[@data-toggle='collapse' and contains(normalize-space(),'" + category + "')]")
        ).click();
        Thread.sleep(1000);
    }

    public void selectSubCategory(String category, String subCategory) throws InterruptedException {

        Thread.sleep(2000);
        driver.findElement(
                By.xpath("//div[@id='" + category + "']//a[normalize-space()='" + subCategory + "']")
        ).click();

    }

    public String getCategoryTitle() {
        return driver.findElement(By.className("title")).getText();

    }

    public void openProductDetails(int id) throws InterruptedException {
        int realID = getProductId(id);
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("a[href='/product_details/" + realID + "']")).click();

    }

    public int getProductId(int index) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        By productCards = By.xpath("//div[@class='product-image-wrapper']");

        List<WebElement> cards = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(productCards)
        );

        WebElement card = cards.get(index - 1);

        WebElement addToCartBtn = card.findElement(
                By.cssSelector("a.add-to-cart[data-product-id]")
        );

        String id = addToCartBtn.getAttribute("data-product-id");

        System.out.println("Product ID = " + id);

        return Integer.parseInt(id);
    }

    public void addToCart(int id) throws InterruptedException {
        int realID = getProductId(id);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2000));
        By productCard = By.xpath("//div[@class='product-image-wrapper' and .//a[@data-product-id='" + realID + "']]");
        WebElement card = wait.until(ExpectedConditions.visibilityOfElementLocated(productCard));
        new Actions(driver).moveToElement(card).perform();
        By overlay = By.xpath("//div[@class='product-overlay' and .//a[@data-product-id='" + realID + "']]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(overlay));
        By addToCartBtn = By.xpath("//div[@class='product-overlay']//a[@data-product-id='" + realID + "']");
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn)).click();
        By modal = By.id("cartModal");
        wait.until(ExpectedConditions.visibilityOfElementLocated(modal));
    }


    public void viewCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By viewCart = By.xpath("//a[.//u[text()='View Cart']]");
        wait.until(ExpectedConditions.elementToBeClickable(viewCart)).click();
    }

    public void continueShopping() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By btn = By.xpath("//button[contains(.,'Continue Shopping')]");
        wait.until(ExpectedConditions.elementToBeClickable(btn)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("cartModal")));
    }

    public void scrollUpBtn() {
        driver.findElement(By.id("scrollUp")).click();
    }
}
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Products {

    WebDriver driver;

    public Products(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    By productsBtn = By.xpath("//a[@href='/products']");
    By searchBox = By.id("search_product");
    By searchBtn = By.id("submit_search");
    By firstViewProduct = By.xpath("(//a[contains(text(),'View Product')])[1]");
    By addToCartBtn = By.xpath("//button[contains(text(),'Add to cart')]");
    By continueBtn = By.xpath("//button[contains(text(),'Continue Shopping')]");

    // Open Products page
    public void openProductsPage() {
        driver.findElement(productsBtn).click();
    }

    // Search product
    public void searchProduct(String name) throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(searchBox).sendKeys(name);
        driver.findElement(searchBtn).click();
    }

    // Open first product
    public void openFirstProduct() {
        driver.findElement(firstViewProduct).click();
    }

    // Add to cart
    public void addToCart() {
        driver.findElement(addToCartBtn).click();
    }

    // Continue shopping
    public void continueShopping() {
        driver.findElement(continueBtn).click();
    }
}
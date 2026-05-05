import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Tests extends BaseTest {
    Home home;
    Cart cart;
    login log;

    @BeforeMethod
    public void initObjects() {
        home = new Home(driver);
        cart = new Cart(driver);
        log = new login(driver);
    }

    @Test(priority = 1)
    public void testlogin()
    {
        log.login("yn123@gmail.com", "123456789");
    }

    @Test(priority = 2)
    public void testOpenCategory() throws InterruptedException {
        home.openCategory("Women");
    }

    @Test(priority = 3)
    public void testSelectSubCategory() throws InterruptedException {
        home.selectSubCategory("Women", "Dress");
    }

    @Test(priority = 4)
    public void testGetCategoryTitle() {
        System.out.println(home.getCategoryTitle());
    }


    @Test(priority = 5)
    public void testAddToCart() throws InterruptedException {
        home.addToCart(1);
    }

    @Test(priority = 6)
    public void testViewCart() {
        home.viewCart();
    }


    @Test(priority = 8)
    public void testIncrementItem() {
        cart.incrementItem(1);
    }

//    @Test(priority = 7)
//    public void testDeleteItem() {
//        cart.deleteItem(1);
//    }

    @Test(priority = 9)
    public void testProceedToCheckout() throws InterruptedException {
        Thread.sleep(200);
        cart.proceedToCheckout();
    }

    @Test(priority = 10)
    public void testAddComment() {
        cart.addComment("Automation test comment");
    }

    @Test(priority = 11)
    public void testPlaceOrder() throws InterruptedException {
        cart.placeOrder();
    }

    @Test(priority = 12)
    public void testDownloadInvoice() {
        cart.downloadInvoice();
    }

    @Test(priority = 13)
    public void testContinueGoingToHome() throws InterruptedException {
        cart.continueGoingToHome();
    }
}

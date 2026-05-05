import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestProducts extends BaseTest{

Products product;
login log;
   Home cart;
    @BeforeMethod
    public void initObjects() {
     product=new Products(driver);
     log=new login(driver);
     cart=new Home(driver);
    }

    @Test(priority = 1)
    public void testlogin()
    {
        log.login("yn123@gmail.com", "123456789");
    }
    @Test(priority = 2)
    public void openProductsPage() {
       product.openProductsPage();
    }

    @Test(priority = 3)
    public void searchProduct() throws InterruptedException {
        product.searchProduct("dress");
    }


   @Test(priority = 5)
    public void addToCart() {
       try {
           cart.addToCart(1);
       } catch (InterruptedException e) {
           throw new RuntimeException(e);
       }
   }

   @Test(priority = 6)
    public void continueShopping() {
        product.continueShopping();
    }

}

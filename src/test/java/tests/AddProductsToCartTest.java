package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utility.BaseTest;
import utility.SampleTestListener;

import java.io.IOException;
@Listeners(SampleTestListener.class)
public class AddProductsToCartTest extends BaseTest {
    SoftAssert softAssert = new SoftAssert();
    @Test(priority = 1, description = "Test description if product is visible")
    @Description("DESCRIPTION: Check if product is visible")
    public void isProductVisibleTest() throws IOException {
        boolean result = launchApplication().isProductVisible("Tomato");
        //Assert.assertTrue(result);
        softAssert.assertTrue(result);
    }
    @Epic("EPIC - test")
    @Description("DESCRIPTION - test")
    @Test(description = "Test description")
    public void addOneProductToCartTest() throws IOException {
        String expectedResult = "Thankyou, your order has been placed successfully\n" +
                "You'll be redirected to Home page shortly!!";
        String result = launchApplication().addProductToCart("Cucumber")
                                        .goToCheckout()
                                        .placeOrder()
                                        .selectCountry("Poland")
                                        .proceedOrder()
                                        .getFinalMessage();
        Assert.assertEquals(result, expectedResult);
    }

    @Test
    public void addTwoProductToCartTest() throws IOException {
        launchApplication().addTwoProductsToCart("Cucumber", "Tomato");
    }

    @Test
    public void addCoupleProductsToCartTest() throws IOException {
        launchApplication().addCoupleDeclaredProducts();
    }

}

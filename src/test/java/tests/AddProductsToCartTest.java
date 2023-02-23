package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import utility.BaseTest;

import java.io.IOException;

public class AddProductsToCartTest extends BaseTest {

    @Test(priority = 1, description = "Test description if product is visible")
    @io.qameta.allure.Description("DESCRIPTION: Check if product is visible")
    public void isProductVisibleTest() throws IOException {
        boolean result = launchApplication().isProductVisible("Tomato");
        Assert.assertTrue(result);
    }
    @Test
    public void addOneProductToCartTest() throws IOException {
        String expectedResult = "Thank you, your order has been placed successfully\n" +
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

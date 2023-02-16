package org.example.pages;

import org.example.utility.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "h4.product-name")
    private List<WebElement> productNames;

    @FindBy(css = "div.product-action button")
    private List<WebElement> addToCartButton;

    @FindBy(css = ".cart-icon img")
    private WebElement cartButton;

    @FindBy(css = ".cart-preview button")
    private WebElement proceedToCheckoutButton;

    public List<String> getProductByName(String productName) {
        System.out.println("Second random sout for GIT training on master");
//        WebElement product = productNames.stream().filter(p ->
//                p.findElement(By.cssSelector("h4")).getText().contains(productName))
//                .findFirst()
//                .orElse(null);

        List<String> productNamesText = productNames.stream().map(name -> name.getAttribute("textContent")).collect(Collectors.toList());
        String names = productNames.stream().map(name -> name.getAttribute("textContent")).filter(name -> name.contains(productName)).findFirst().orElse(null);
        System.out.println(productNamesText);
        System.out.println(names);
        System.out.println("Random sout for GIT training on master and feature_branch");
        return productNamesText;
    }

    public boolean isProductVisible(String productName) {
        webDriverWait.until(ExpectedConditions.visibilityOf(cartButton));
        Boolean result = productNames.stream().anyMatch(p -> p.getText().contains(productName));
        return result;
    }

    public HomePage addProductToCart(String productName) {
        for (int i = 0; i < productNames.size(); i++){
            String name = productNames.get(i).getText();
            if (name.contains(productName)) {
                addToCartButton.get(i).click();
            }
        }
        return  this;
    }

    public void addTwoProductsToCart(String firstProduct, String secondProduct){
        for (int i = 0; i < productNames.size(); i++){
            String name = productNames.get(i).getText();
            if (name.contains(firstProduct) || name.contains(secondProduct)) {
                addToCartButton.get(i).click();
            }
        }
    }

    public void addCoupleDeclaredProducts() {
        List<String> products = Arrays.asList("Cucumber", "Tomato", "Pumpkin");
        System.out.println(products);
        for(int i = 0; i < productNames.size(); i++) {
            String[] name = productNames.get(i).getText().split("-");
            String trimedName = name[0].trim();
            if (products.contains(trimedName)) {
                addToCartButton.get(i).click();
            }
        }
    }

    public CheckoutPage goToCheckout(){
        cartButton.click();
        proceedToCheckoutButton.click();
        return new CheckoutPage(driver);
    }

}

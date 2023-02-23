package org.example.pages;

import io.qameta.allure.Step;
import org.example.utility.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage extends BasePage {
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[text()='Place Order']")
    private WebElement placeOrderButton;

    @FindBy(css = "select")
    private WebElement selectCountry;

    @FindBy(css = "[type='checkbox']")
    private WebElement checkbox;

    @FindBy(css = "button")
    private WebElement proceedButton;

    @Step("STEP: Place order")
    public CheckoutPage placeOrder() {
        webDriverWait.until(ExpectedConditions.visibilityOf(placeOrderButton));
        placeOrderButton.click();
        return this;
    }

    @Step("STEP: Select country")
    public CheckoutPage selectCountry(String countryy) {
        Select country = new Select(selectCountry);
        country.selectByValue(countryy);
        checkbox.click();
        return this;
    }

    @Step("STEP: Proceed order")
    public OrderPage proceedOrder() {
        webDriverWait.until(ExpectedConditions.visibilityOf(proceedButton));
        proceedButton.click();
        return new OrderPage(driver);
    }
}

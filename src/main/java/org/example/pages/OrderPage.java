package org.example.pages;

import io.qameta.allure.Step;
import org.example.utility.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderPage extends BasePage {
    public OrderPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[contains(text(),'Thank you')]")
    private WebElement finalMessage;

    @Step("STEP: Get final message")
    public String getFinalMessage() {
        String message = finalMessage.getText();
        System.out.println(message);
        return message;
    }
}

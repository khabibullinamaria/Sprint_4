package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// Класс страницы подтверждения оформления заказа
public class ConfirmationPageSamokat {

    private WebDriver driver;
    private By confirmationButton = By.xpath(".//button[text()='Да']");

    public ConfirmationPageSamokat(WebDriver driver){
        this.driver = driver;
    }

    public void clickConfirmationButton() {
        driver.findElement(confirmationButton).click();
    }
}

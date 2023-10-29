package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

// Класс главной страницы
public class MainPageSamokat {

    private WebDriver driver;

    private By headerOrderButton = By.className("Button_Button__ra12g");
    private By bottomOrderButton = By.xpath(".//button[contains(@class, 'Button_Button__ra12g Button_Middle__1CSJM') and text()='Заказать']");

    public MainPageSamokat(WebDriver driver){
        this.driver = driver;
    }

    public void clickOrderButton(Boolean isScroll) {
        if (isScroll) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
            js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
            driver.findElement(bottomOrderButton).click();
        }
        else {
            driver.findElement(headerOrderButton).click();
        }
    }

}
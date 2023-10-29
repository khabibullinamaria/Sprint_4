package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// Класс страницы про аренду
public class RentPageSamokat {

    private WebDriver driver;
    private By dateField = By.xpath("//input[contains(@placeholder,'* Когда привезти самокат')]");
    private By rentTimeField = By.className("Dropdown-arrow");
    private By commentField = By.xpath("//input[contains(@placeholder,'Комментарий для курьера')]");
    private By orderButton = By.xpath(".//button[contains(@class, 'Button_Middle__1CSJM') and text()='Заказать']");

    public RentPageSamokat(WebDriver driver){
        this.driver = driver;
    }

    public void setRentData(String data, String rentDataValue, String color, String comment) {
        driver.findElement(dateField).sendKeys(data);
        driver.findElement(rentTimeField).click();
        driver.findElement(By.xpath("//div[text()='" + rentDataValue + "']")).click(); ////Добавить другие сроки аренды
        driver.findElement(By.xpath("//label[contains(@for,'" + color + "')]")).click();
        driver.findElement(commentField).sendKeys(comment);
    }
    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

}


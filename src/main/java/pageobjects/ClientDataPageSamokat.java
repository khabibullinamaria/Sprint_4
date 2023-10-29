package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// Класс страницы для кого самокат
public class ClientDataPageSamokat {

    private WebDriver driver;
    private By nameField = By.xpath("//input[contains(@placeholder,'* Имя')]");
    private By surnamelField = By.xpath("//input[contains(@placeholder,'* Фамилия')]");
    private By addressField = By.xpath("//input[contains(@placeholder,'* Адрес: куда привезти заказ')]");
    private By metroStationField = By.xpath("//input[contains(@placeholder,'* Станция метро')]");
    private By phoneNumberField = By.xpath("//input[contains(@placeholder,'* Телефон: на него позвонит курьер')]");
    private By furtherButton = By.xpath(".//button[text()='Далее']");

    public ClientDataPageSamokat(WebDriver driver){
        this.driver = driver;
    }

    public void setClientData(String name, String surname, String address, String metroStation, String phoneNumber) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(surnamelField).sendKeys(surname);
        driver.findElement(addressField).sendKeys(address);
        setMetroStation(metroStation);
        driver.findElement(phoneNumberField).sendKeys(phoneNumber);
    }
    public void setMetroStation(String metroStation) {
        driver.findElement(metroStationField).click();
        driver.findElement(By.xpath("//div[text()='" + metroStation + "']")).click();
    }
    public void clickFurtherButton() {
        driver.findElement(furtherButton).click();
    }

}
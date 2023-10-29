package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// Класс сообщения о заказе
public class OrderInformationPageSamokat {

    private WebDriver driver;
    private By trackStatusButton = By.xpath(".//button[contains(@class, 'Button_Button__ra12g Button_Middle__1CSJM') and text()='Посмотреть статус']");
    public OrderInformationPageSamokat (WebDriver driver){
        this.driver = driver;
    }

    public String getOrderInformation() {
        return (driver.findElement(By.xpath(".//div[contains(@class, 'Order_ModalHeader__3FDaJ') and text()='Заказ оформлен']")).getText());
    }
}

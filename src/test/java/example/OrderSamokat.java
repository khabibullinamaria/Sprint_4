package example;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// Класс главной страницы
class MainPageSamokat {

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

// Класс страницы для кого самокат
class ClientDataPageSamokat {

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

// Класс страницы про аренду
class RentPageSamokat {

    private WebDriver driver;
    public By dateField = By.xpath("//input[contains(@placeholder,'* Когда привезти самокат')]");
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

// Класс страницы подтверждения оформления заказа
class ConfirmationPageSamokat {

    private WebDriver driver;
    public By confirmationButton = By.xpath(".//button[text()='Да']");

    public ConfirmationPageSamokat(WebDriver driver){
        this.driver = driver;
    }

    public void clickConfirmationButton() {
        driver.findElement(confirmationButton).click();
    }
}

// Класс сообщения о заказе
class OrderInformationPageSamokat {

    private WebDriver driver;
    public By trackStatusButton = By.xpath(".//button[contains(@class, 'Button_Button__ra12g Button_Middle__1CSJM') and text()='Посмотреть статус']");
    public OrderInformationPageSamokat (WebDriver driver){
        this.driver = driver;
    }

    public String getOrderInformation() {
        return (driver.findElement(By.xpath(".//div[contains(@class, 'Order_ModalHeader__3FDaJ') and text()='Заказ оформлен']")).getText());
    }
}

//Класс с автотестом
@RunWith(Parameterized.class)
public class OrderSamokat {
    private WebDriver driver;
    private final boolean isScroll;
    private final String name;
    private final String surname;
    private final String address;
    private final String metroStation;
    private final String phoneNumber;
    private final String date;
    private final String rentDataValue;
    private final String color;
    private final String comment;

    public OrderSamokat(Boolean isScroll, String name, String surname, String address, String metroStation, String phoneNumber, String date, String rentDataValue, String color,String comment) {

        this.isScroll = isScroll;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.rentDataValue = rentDataValue;
        this.color = color;
        this.comment = comment;

    }

    @Parameterized.Parameters
    public static Object[][] orderData() {
        //Сгенерируй тестовые данные (свою учётку и несколько случайных)
        return new Object[][] {
                {true,"Марина","Андреева", "Москва", "Сокольники", "89192345192", "29.11.2023", "сутки", "black", "hihi"},
                {false,"Кирилл","Аленович", "Сидней", "Черкизовская", "89192345195", "22.12.2023", "двое суток", "black", ""},
                {true,"Арина","Сашеева", "Екатеринбург", "Красносельская", "89122365145", "06.12.2023", "шестеро суток", "grey", "haha"},
                {false,"Настя","Эдвардова", "Ижевск", "Лубянка", "88892345133", "19.12.2023", "трое суток", "grey", "hyhy"},
        };
    }

    @Test
    public void orderSamokat() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");
        // создай объект класса
        MainPageSamokat objMainPage = new MainPageSamokat(driver);
        objMainPage.clickOrderButton(isScroll);
        // создай объект класса
        ClientDataPageSamokat objClientDataPage = new ClientDataPageSamokat(driver);
        objClientDataPage.setClientData(name,surname, address, metroStation, phoneNumber);
        objClientDataPage.clickFurtherButton();
        // создай объект класса
        RentPageSamokat objRentPage = new RentPageSamokat(driver);
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(objRentPage.dateField));
        objRentPage.setRentData(date, rentDataValue, color, comment);
        objRentPage.clickOrderButton();
        // создай объект класса
        ConfirmationPageSamokat objConfirmationPage = new ConfirmationPageSamokat(driver);
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(objConfirmationPage.confirmationButton));
        objConfirmationPage.clickConfirmationButton();
        // создай объект класса
        OrderInformationPageSamokat objOrderInformationPage = new OrderInformationPageSamokat(driver);
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(objOrderInformationPage.trackStatusButton));
        var trackText = objOrderInformationPage.getOrderInformation();
        Assert.assertTrue(trackText.contains("Заказ оформлен"));
    }

    @After
    public void teardown() {
        // Закрой браузер
        driver.quit();
    }
}
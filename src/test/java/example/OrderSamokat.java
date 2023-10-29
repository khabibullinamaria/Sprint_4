package example;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pageobjects.*;

//Класс с автотестом
@RunWith(Parameterized.class)
public class OrderSamokat extends BaseTest {
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
        // создай объект класса
        MainPageSamokat objMainPage = new MainPageSamokat(driver);
        objMainPage.clickOrderButton(isScroll);
        // создай объект класса
        ClientDataPageSamokat objClientDataPage = new ClientDataPageSamokat(driver);
        objClientDataPage.setClientData(name,surname, address, metroStation, phoneNumber);
        objClientDataPage.clickFurtherButton();
        // создай объект класса
        RentPageSamokat objRentPage = new RentPageSamokat(driver);
        objRentPage.setRentData(date, rentDataValue, color, comment);
        objRentPage.clickOrderButton();
        // создай объект класса
        ConfirmationPageSamokat objConfirmationPage = new ConfirmationPageSamokat(driver);
        objConfirmationPage.clickConfirmationButton();
        // создай объект класса
        OrderInformationPageSamokat objOrderInformationPage = new OrderInformationPageSamokat(driver);
        var trackText = objOrderInformationPage.getOrderInformation();
        Assert.assertTrue(trackText.contains("Заказ оформлен"));
    }

}
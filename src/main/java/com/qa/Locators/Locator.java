package com.qa.Locators;

import org.openqa.selenium.By;

public class Locator {
    public static By userName_txtBox = By.xpath("//input[@id = 'user-name']");
    public static By password_txtBox = By.xpath("//input[@id = 'password']");
    public static By login_BTN = By.xpath("//input_test[@id = 'login-button']");
    public static By verifyLogin = By.xpath("//div[@class='app_logo']");
    public static By errorPopup = By.xpath("//div[@class='error-message-container error']");
    public static By errorPopup_MSG = By.xpath("//div[@class='error-message-container error']/h3");

}

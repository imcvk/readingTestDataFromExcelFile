package org.demo.objecr_repo;

import org.demo.base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Register extends BaseTest {
  public Register () {
    PageFactory.initElements (driver, this);
  }

  @FindBy(id = "firstname")
  public static WebElement firstName;
  @FindBy(id = "lastname")
  public static WebElement lastName;
  @FindBy(id = "username")
  public static WebElement username;
  @FindBy(id = "password")
  public static WebElement password;
  @FindBy(xpath = "//*[@value=\"Register\"]")
  public static WebElement RegisterBtn;

  public void fillRegisterForm (String firstNameInput, String lastNameInput, String useNameInput, String passwordInput) {
    firstName.sendKeys (firstNameInput);
    lastName.sendKeys (lastNameInput);
    username.sendKeys (useNameInput);
    password.sendKeys (passwordInput);
    RegisterBtn.click ();
  }
}

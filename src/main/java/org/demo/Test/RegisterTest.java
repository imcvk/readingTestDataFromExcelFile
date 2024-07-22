package org.demo.Test;

import org.demo.base.BaseTest;
import org.demo.objecr_repo.Register;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegisterTest extends BaseTest {
  public RegisterTest () {
    super ();
  }

  Register register;
  String testCaseId;
  JSONObject testDataFromRegisterForm;
  String firstname, lastname, username, password;

  @BeforeMethod
  public void setup () {
    register = new Register ();
  }

  @Test
  public void TC1 () {
    String testCaseId = "TC1";
    testDataFromRegisterForm = getTestDataForRegisterForm (testCaseId);

    firstname = (String) testDataFromRegisterForm.get ("firstname");
    lastname = (String) testDataFromRegisterForm.get ("lastname");
    username = (String) testDataFromRegisterForm.get ("username");
    password = (String) testDataFromRegisterForm.get ("password");
    register.fillRegisterForm (firstname, lastname, username, password);
  }

  @Test
  public void TC2 () {
    testCaseId = "TC2";
    testDataFromRegisterForm = getTestDataForRegisterForm (testCaseId);

    firstname = (String) testDataFromRegisterForm.get ("firstname");
    lastname = (String) testDataFromRegisterForm.get ("lastname");
    username = (String) testDataFromRegisterForm.get ("username");
    password = (String) testDataFromRegisterForm.get ("password");
    register.fillRegisterForm (firstname, lastname, username, password);
  }

  @Test
  public void TC3 () {
    testCaseId = "TC3";
    testDataFromRegisterForm = getTestDataForRegisterForm (testCaseId);

    firstname = (String) testDataFromRegisterForm.get ("firstname");
    lastname = (String) testDataFromRegisterForm.get ("lastname");
    username = (String) testDataFromRegisterForm.get ("username");
    password = (String) testDataFromRegisterForm.get ("password");
    register.fillRegisterForm (firstname, lastname, username, password);
  }

  @Test
  public void TC4 () {
    testCaseId = "TC4";
    testDataFromRegisterForm = getTestDataForRegisterForm (testCaseId);

    firstname = (String) testDataFromRegisterForm.get ("firstname");
    lastname = (String) testDataFromRegisterForm.get ("lastname");
    username = (String) testDataFromRegisterForm.get ("username");
    password = (String) testDataFromRegisterForm.get ("password");
    register.fillRegisterForm (firstname, lastname, username, password);
  }

  @Test
  public void TC5 () {
    testCaseId = "TC5";
    testDataFromRegisterForm = getTestDataForRegisterForm (testCaseId);
    firstname = (String) testDataFromRegisterForm.get ("firstname");
    lastname = (String) testDataFromRegisterForm.get ("lastname");
    username = (String) testDataFromRegisterForm.get ("username");
    password = (String) testDataFromRegisterForm.get ("password");
    register.fillRegisterForm (firstname, lastname, username, password);
  }

  public JSONObject getTestDataForRegisterForm (String testCaseId) {
    JSONObject testDataForTestCase = testData.getJSONObject (testCaseId);
    return testDataForTestCase;
  }
}

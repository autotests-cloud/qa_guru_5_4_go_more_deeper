package tests.pageobjects.steps;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class StudentRegistrationFormPage {
    private SelenideElement
            pageHeader = $(".practice-form-wrapper"),
            firstNameInput = $("#firstName");


    public void checkPageHeader(String headerText) {
        pageHeader.shouldHave(text(headerText));
    }

    public void setFirstName(String nameValue) {
        firstNameInput.val(nameValue);
    }

}
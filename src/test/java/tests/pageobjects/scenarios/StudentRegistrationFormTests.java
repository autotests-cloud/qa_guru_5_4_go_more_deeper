package tests.pageobjects.scenarios;

import org.junit.jupiter.api.Test;

public class StudentRegistrationFormTests {
    StudentRegistrationFormPage studentRegistrationFormPage = new StudentRegistrationFormPage();

    @Test
    void successfulFillFormTest() {
        studentRegistrationFormPage.openPage();
        studentRegistrationFormPage.fillForm();
        studentRegistrationFormPage.checkData();
    }
}

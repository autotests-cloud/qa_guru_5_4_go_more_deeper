package tests.pageobjects.chain;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class StudentRegistrationFormTests {

    StudentRegistrationFormPage studentRegistrationFormPage = new StudentRegistrationFormPage();

    @BeforeAll
    static void openPage() {
        Configuration.browser = "chrome";
    }

    @Test
    void successfulFillFormTest() {
        studentRegistrationFormPage
                .openPage()
                .fillForm()
                .checkData();
    }
}

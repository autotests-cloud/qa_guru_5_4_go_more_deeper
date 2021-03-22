package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxTests {

    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
    }


    @Test
    void successfulFillTestWithNotBestLocators() {
        open("https://demoqa.com/text-box");

        $("#userName").setValue("alex");
        $("[type=email]").setValue("aa@aa.aa");
        $("[placeholder='Current Address']").setValue("1234");
        $(byId("permanentAddress")).setValue("54321");
        $(".btn.btn-primary").click();

        System.out.println("debug");
    }

    @Test
    void successfulFillTest() {
        open("https://demoqa.com/text-box");

        $("#userName").setValue("alex");
        $("#userEmail").setValue("aa@aa.aa");
        $("#currentAddress").setValue("1234");
        $("#permanentAddress").setValue("54321");
        $("#submit").click();

        $("#name").shouldHave(text("alex"));
    }

    @Test
    void successfulFillTestWithVariables() {
        String name = "alex";

        open("https://demoqa.com/text-box");

        $("#userName").setValue(name);
        $("#userEmail").setValue("aa@aa.aa");
        $("#currentAddress").setValue("1234");
        $("#permanentAddress").setValue("54321");
        $("#submit").click();

        $("#name").shouldHave(text(name));
        $("#email").shouldHave(text("aa@aa.aa"));
    }

    @Test
    void successfulFillTestWithSearchInOutput() {
        String name = "alex";

        open("https://demoqa.com/text-box");

        $("#userName").setValue(name);
        $("#userEmail").setValue("aa@aa.aa");
        $("#currentAddress").setValue("1234");
        $("#permanentAddress").setValue("54321");
        $("#submit").click();

        $("#output").shouldHave(text(name), text("aa@aa.aa"));
    }


}

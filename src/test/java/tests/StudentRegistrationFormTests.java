package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class StudentRegistrationFormTests {

    String firstName = "Egor",
            lastName = "Alexov",
            email = "aa@aa.com",
            gender = "Other",
            mobile = "1234567890",
            monthOfBirth = "May",
            yearOfBirth = "1988",
            dayOfBirth = "10",
            dayOfWeekOfBirth = "Tuesday",
            subject1 = "Chemistry",
            subject2 = "Commerce",
            hobby1 = "Sports",
            hobby2 = "Reading",
            hobby3 = "Music",
            picture = "1.png",
            currentAddress = "Montenegro 123",
            state = "Uttar Pradesh",
            city = "Merrut";

    @Test
    void successfulRegistrationTest() {

        // Arrange / Given / Open site
        open("https://demoqa.com/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));

        // Act / When / Fill the form
        $("#firstName").val(firstName);
        $("#lastName").val(lastName);
        $("#userEmail").val(email);
        $("#genterWrapper").$(byText(gender)).click();
//        $(byText(gender)).click();
//        $("[for=gender-radio-1]").click();
//        $("#gender-radio-1").doubleClick();
        $("#userNumber").val(mobile);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(monthOfBirth);
        $(".react-datepicker__year-select").selectOption(yearOfBirth);
        $(String.format("[aria-label='Choose %s, %s %sth, %s']",
                dayOfWeekOfBirth, monthOfBirth, dayOfBirth, yearOfBirth)).click();
//        $(by("aria-label", "Choose Tuesday, May 10th, 1988")).click();
//        $$(String.format(".react-datepicker__day--0%s", dayOfBirth))
//                .filter(not(cssClass("react-datepicker__day--outside-month")))
//                .first().click();
//        $$(".react-datepicker__day--0" + dayOfBirth)
//                .filter(not(cssClass("react-datepicker__day--outside-month")))
//                .first().click();
//        $(String.format(".react-datepicker__day--0%s:not(.react-datepicker__day--outside-month)", dayOfBirth)).click();
//        $(".react-datepicker__day--0" + dayOfBirth + ":not(.react-datepicker__day--outside-month)").click();
        $("#subjectsInput").val(subject1).pressEnter();
        $("#subjectsInput").val(subject2);
        $(".subjects-auto-complete__menu-list").$(byText(subject2)).click();
        $("#hobbiesWrapper").$(byText(hobby1)).click();
        $("#hobbiesWrapper").$(byText(hobby2)).click();
        $("#hobbiesWrapper").$(byText(hobby3)).click();
        $("#uploadPicture").uploadFromClasspath("img/" + picture);
//        $("#uploadPicture").uploadFile(new File("src/test/resources/" + picture));
        $("#currentAddress").val(currentAddress);
        $("#state").scrollTo().click();
        $("#stateCity-wrapper").$(byText(state)).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).click();

        $("#submit").click();

        // Assert / Then / Verify successful form submit
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
//        $(".table-responsive").shouldHave(text(firstName + " " + lastName),
//                text(email), text(gender));
//        $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text(firstName + " " + lastName));
//        $(byText("Student Name")).parent().shouldHave(text(firstName + " " + lastName)); // wrong!
        $x("//td[text()='Student Name']").parent().shouldHave(text(firstName + " " + lastName));
        $x("//td[text()='Student Email']").parent().shouldHave(text(email));
        $x("//td[text()='Gender']").parent().shouldHave(text(gender));
        $x("//td[text()='Mobile']").parent().shouldHave(text(mobile));
        $x("//td[text()='Date of Birth']").parent().shouldHave(text(dayOfBirth + " " + monthOfBirth + "," + yearOfBirth));
        $x("//td[text()='Subjects']").parent().shouldHave(text(subject1 + ", " + subject2));
        $x("//td[text()='Hobbies']").parent().shouldHave(text(hobby1 + ", " + hobby2 + ", " + hobby3));
        $x("//td[text()='Picture']").parent().shouldHave(text(picture));
        $x("//td[text()='Address']").parent().shouldHave(text(currentAddress));
        $x("//td[text()='State and City']").parent().shouldHave(text(state + " " + city));
    }
}

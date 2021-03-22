package tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import static utils.FileUtils.readStringFromFile;

public class StudentRegistrationFormWithExecuteScriptTests {

    static String firstName = "Egor",
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

    static Map<String, String> expectedData = new HashMap<String, String>() {{
        put("Student Name", firstName + " " + lastName);
        put("Student Email", email);
        put("Gender", gender);
        put("Mobile", mobile);
        put("Date of Birth", dayOfBirth + " " + monthOfBirth + "," + yearOfBirth);
        put("Subjects", subject1 + ", " + subject2);
    }};

    @BeforeAll
    static void successfulRegistrationTest() {

        // Arrange / Given / Open site
        open("https://demoqa.com/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));

        // Act / When / Fill the form
        $("#firstName").val(firstName);
        $("#lastName").val(lastName);
        $("#userEmail").val(email);
        $("#genterWrapper").$(byText(gender)).click();

        $("#userNumber").val(mobile);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(monthOfBirth);
        $(".react-datepicker__year-select").selectOption(yearOfBirth);
        $(String.format("[aria-label='Choose %s, %s %sth, %s']",
                dayOfWeekOfBirth, monthOfBirth, dayOfBirth, yearOfBirth)).click();
        $("#subjectsInput").val(subject1).pressEnter();
        $("#subjectsInput").val(subject2);
        $(".subjects-auto-complete__menu-list").$(byText(subject2)).click();
        $("#hobbiesWrapper").$(byText(hobby1)).click();
        $("#hobbiesWrapper").$(byText(hobby2)).click();
        $("#hobbiesWrapper").$(byText(hobby3)).click();
        $("#uploadPicture").uploadFromClasspath("img/" + picture);
        $("#currentAddress").val(currentAddress);
        $("#state").scrollTo().click();
        $("#stateCity-wrapper").$(byText(state)).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).click();

        $("#submit").click();
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
    }

    @ParameterizedTest
    @MethodSource("getTableDataAsStream")
    void checkTableData(String key, String actualValue) {
        System.out.println(key + " " + actualValue);

        assertThat(expectedData.get(key), is(actualValue));
    }

    public static Stream<Arguments> getTableDataAsStream() {
        return createList(getBodyContentWithExecuteScript()).stream();
    }

    private static List<Arguments> createList(Map<String, String> data) {
        return data.entrySet()
                .stream()
                .map(e -> Arguments.of(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }

    public static Map<String, String> getBodyContentWithExecuteScript() {
        String jsCode = readStringFromFile("./src/test/resources/javascript/get_table_data.js");
        String browserResponse = executeJavaScript(jsCode);
        System.out.println(browserResponse);

        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> data = null;
        try {
            data = mapper.readValue(browserResponse,
                    new TypeReference<Map<String, String>>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println(data);

        return data;
    }

}

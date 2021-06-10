package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideDriver;
import extensions.Chrome;
import extensions.FF;
import extensions.SelenideDriverParameterResolver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({SelenideDriverParameterResolver.class})
public class JunitAdvancedTest {

    @Chrome
    @Test
    void yaTest(SelenideDriver driver) {
        driver.open("http://ya.ru");
        driver.$("button[type='submit']").shouldHave(Condition.text("Найти"));
    }

    @FF
    @Test
    void yaFfTest(SelenideDriver driver) {
        driver.open("http://ya.ru");
        driver.$("button[type='submit']").shouldHave(Condition.text("Найти"));
    }


}

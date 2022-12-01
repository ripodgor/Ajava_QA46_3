package ru.netology;


import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CallBackTest {
    @BeforeEach
    void setUp() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
    }

    @AfterEach
    void memoryClear() {
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }

    @Test
    void shouldCriticalPathTest() {
        $("input[name='name']").setValue("Петров Алексей");
        $("input[type='tel']").setValue("+79111234567");
        $("[data-test-id=agreement]").click();
        $x("//button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    //todo bug
    @Test
    void shouldValidNameTestOne() {
        $("input[name='name']").setValue("Ёжикова Алёна");
        $("input[type='tel']").setValue("+79111234567");
        $("[data-test-id=agreement]").click();
        $x("//button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldValidNameTestTwo() {
        $("input[name='name']").setValue("Гончаров-Сидоров Николай");
        $("input[type='tel']").setValue("+79111234567");
        $("[data-test-id=agreement]").click();
        $x("//button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldValidNameTestThree() {
        $("input[name='name']").setValue("Ахмед ибн Рашид");
        $("input[type='tel']").setValue("+79111234567");
        $("[data-test-id=agreement]").click();
        $x("//button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldInvalidNameTestOne() {
        $("input[name='name']").setValue("Ivan Ivanov");
        $("input[type='tel']").setValue("+79111234567");
        $("[data-test-id=agreement]").click();
        $x("//button").click();
        $("[data-test-id=name].input_invalid span.input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    //todo bug
    @Test
    void shouldInvalidNameTestTwo() {
        $("input[name='name']").setValue("Андрей");
        $("input[type='tel']").setValue("+79111234567");
        $("[data-test-id=agreement]").click();
        $x("//button").click();
        $("[data-test-id=name].input_invalid span.input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldInvalidNameTestThree() {
        $("input[name='name']").setValue("+79111234567");
        $("input[type='tel']").setValue("+79111234567");
        $("[data-test-id=agreement]").click();
        $x("//button").click();
        $("[data-test-id=name].input_invalid span.input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldInvalidPhoneTestOne() {
        $("input[name='name']").setValue("Петров Алексей");
        $("input[type='tel']").setValue("89111234567");
        $("[data-test-id=agreement]").click();
        $x("//button").click();
        $("[data-test-id=phone].input_invalid span.input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldInvalidPhoneTestTwo() {
        $("input[name='name']").setValue("Петров Алексей");
        $("input[type='tel']").setValue("+7911123456");
        $("[data-test-id=agreement]").click();
        $x("//button").click();
        $("[data-test-id=phone].input_invalid span.input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldInvalidPhoneTestThree() {
        $("input[name='name']").setValue("Петров Алексей");
        $("input[type='tel']").setValue("+791112345678");
        $("[data-test-id=agreement]").click();
        $x("//button").click();
        $("[data-test-id=phone].input_invalid span.input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldInvalidPhoneTestFour() {
        $("input[name='name']").setValue("Петров Алексей");
        $("input[type='tel']").setValue("IPhone");
        $("[data-test-id=agreement]").click();
        $x("//button").click();
        $("[data-test-id=phone].input_invalid span.input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldEmptyInputTestOne() {
        $("input[type='tel']").setValue("+79111234567");
        $("[data-test-id=agreement]").click();
        $x("//button").click();
        $("[data-test-id=name].input_invalid span.input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldEmptyInputTestTwo() {
        $("input[name='name']").setValue("Петров Алексей");
        $("[data-test-id=agreement]").click();
        $x("//button").click();
        $("[data-test-id=phone].input_invalid span.input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

}

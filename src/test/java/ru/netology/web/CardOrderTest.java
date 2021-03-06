package ru.netology.web;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class CardOrderTest {
    @Test
    void shouldEnterValidData() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Александр Сергеевич Осипов");
        $("[data-test-id=phone] input").setValue("+79801234567");
        $("[data-test-id=agreement]").click();
        $("[type='button']").click();
        $("[data-test-id='order-success']").shouldHave(text("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldEmptyFiledName() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79801234567");
        $("[data-test-id=agreement]").click();
        $("[type='button']").click();
        $(".input_invalid[data-test-id='name']").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldEmptyFiledPhone() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Александр Сергеевич Осипов");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $("[type='button']").click();
        $(".input_invalid[data-test-id='phone']").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldEmptyCheckBox() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Александр Сергеевич Осипов");
        $("[data-test-id=phone] input").setValue("+79801234567");
        //$("[data-test-id=agreement]").click();
        $("[type='button']").click();
        $(".input_invalid[data-test-id='agreement']").shouldHave(text("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }

    @Test
    void shouldNotValidName() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Aleksandr");
        $("[data-test-id=phone] input").setValue("+79801234567");
        $("[data-test-id=agreement]").click();
        $("[type='button']").click();
        $(".input_invalid[data-test-id='name']").shouldHave(text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldNotValidPhone() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Александр Сергеевич Осипов");
        $("[data-test-id=phone] input").setValue("+7980123456");
        $("[data-test-id=agreement]").click();
        $("[type='button']").click();
        $(".input_invalid[data-test-id='phone']").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
}

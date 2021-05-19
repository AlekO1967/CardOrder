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
}

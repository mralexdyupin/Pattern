import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;


import java.text.SimpleDateFormat;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestApp {
    private Faker faker;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
    SelenideElement city = $("[data-test-id = city]");
    SelenideElement dateField = $("[data-test-id = date]");
    SelenideElement nameField = $("[data-test-id = name]");
    SelenideElement phoneField = $("[data-test-id = phone]");
    SelenideElement agreement = $("[data-test-id = agreement]");
    SelenideElement notification = $("[data-test-id = success-notification]");
    SelenideElement notificationReplan = $("[data-test-id = replan-notification");

    @Test
    void shouldSuccess() {
        faker = new Faker(new Locale("ru"));
        String date = simpleDateFormat.format(faker.date().future(1500, 2, TimeUnit.DAYS));
        String date1 = simpleDateFormat.format(faker.date().future(1500, 2, TimeUnit.DAYS));
        String name = faker.name().firstName() + " " + faker.name().lastName();
        open("http://localhost:9999");
        city.$(".input__control").setValue(String.valueOf(faker.address().cityName()));
        dateField.$(".input__control").setValue("\b\b\b\b\b\b\b\b");
        dateField.$(".input__control").setValue(date);
        nameField.$(".input__control").setValue(name);
        phoneField.$(".input__control").setValue(faker.numerify("+7 ### ### ## ##"));
        agreement.click();
        $(byText("Запланировать")).click();
        notification.$(byText("Успешно!")).waitUntil(Condition.visible, 15000);
        notification.$(byText(date)).waitUntil(Condition.visible, 15000);
        dateField.$(".input__control").setValue("\b\b\b\b\b\b\b\b");
        dateField.$(".input__control").setValue(date1);
        $(byText("Запланировать")).click();
        notificationReplan.$(byText("Необходимо подтверждение")).waitUntil(Condition.visible, 15000);
        notificationReplan.$(byText("Перепланировать")).click();
        notification.$(byText("Успешно!")).waitUntil(Condition.visible, 15000);
        notification.$(byText(date1)).waitUntil(Condition.visible, 15000);
    }

    @Test
    void testCityFieldShouldError() {
        faker = new Faker(new Locale("ru"));
        String date = simpleDateFormat.format(faker.date().future(1500, 2, TimeUnit.DAYS));
        String name = faker.name().firstName() + " " + faker.name().lastName();
        faker = new Faker(new Locale("eng"));
        open("http://localhost:9999");
        city.$(".input__control").setValue(String.valueOf(faker.address().cityName()));
        dateField.$(".input__control").setValue("\b\b\b\b\b\b\b\b");
        dateField.$(".input__control").setValue(date);
        nameField.$(".input__control").setValue(name);
        phoneField.$(".input__control").setValue(faker.numerify("+7 ### ### ## ##"));
        agreement.click();
        $(byText("Запланировать")).click();
        city.$(".input__sub").shouldHave(Condition.exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    void testDateFieldShouldError() {
        faker = new Faker(new Locale("ru"));
        String date = simpleDateFormat.format(faker.date().past(300, -2, TimeUnit.DAYS));
        String name = faker.name().firstName() + " " + faker.name().lastName();
        open("http://localhost:9999");
        city.$(".input__control").setValue(String.valueOf(faker.address().cityName()));
        dateField.$(".input__control").setValue("\b\b\b\b\b\b\b\b");
        dateField.$(".input__control").setValue(date);
        nameField.$(".input__control").setValue(name);
        phoneField.$(".input__control").setValue(faker.numerify("+7 ### ### ## ##"));
        agreement.click();
        $(byText("Запланировать")).click();
        dateField.$(".input__sub").shouldHave(Condition.exactText("Заказ на выбранную дату невозможен"));
    }

    @Test
    void testNameFieldShouldError() {
        faker = new Faker(new Locale("eng"));
        String date = simpleDateFormat.format(faker.date().future(1500, 2, TimeUnit.DAYS));
        String date1 = simpleDateFormat.format(faker.date().future(1500, 2, TimeUnit.DAYS));
        String name = faker.name().firstName() + " " + faker.name().lastName();
        faker = new Faker(new Locale("ru"));
        open("http://localhost:9999");
        city.$(".input__control").setValue(String.valueOf(faker.address().cityName()));
        dateField.$(".input__control").setValue("\b\b\b\b\b\b\b\b");
        dateField.$(".input__control").setValue(date);
        nameField.$(".input__control").setValue(name);
        phoneField.$(".input__control").setValue(faker.numerify("+7 ### ### ## ##"));
        agreement.click();
        $(byText("Запланировать")).click();
        nameField.$(".input__sub").shouldHave(Condition.exactText
                ("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void testDateWideGetForm() {
        faker = new Faker(new Locale("ru"));
        String date = simpleDateFormat.format(faker.date().future(1500, 2, TimeUnit.DAYS));
        String date1 = simpleDateFormat.format(faker.date().past(300, -2, TimeUnit.DAYS));
        String name = faker.name().firstName() + " " + faker.name().lastName();
        open("http://localhost:9999");
        city.$(".input__control").setValue(String.valueOf(faker.address().cityName()));
        dateField.$(".input__control").setValue("\b\b\b\b\b\b\b\b");
        dateField.$(".input__control").setValue(date);
        nameField.$(".input__control").setValue(name);
        phoneField.$(".input__control").setValue(faker.numerify("+7 ### ### ## ##"));
        agreement.click();
        $(byText("Запланировать")).click();
        notification.$(byText("Успешно!")).waitUntil(Condition.visible, 15000);
        notification.$(byText(date)).waitUntil(Condition.visible, 15000);
        $(byText("Запланировать")).click();
        dateField.$(".input__control").setValue("\b\b\b\b\b\b\b\b");
        dateField.$(".input__control").setValue(date1);
        notificationReplan.$(byText("Перепланировать")).click();
        SelenideElement notificationError = $("[data-test-id = error-notification]");
        notificationError.$(byText("Ошибка")).shouldBe(Condition.visible);
    }
}

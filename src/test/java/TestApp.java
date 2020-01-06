import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.open;

public class TestApp {

    @ParameterizedTest
    @CsvFileSource(resources = "/DataCityPositive.csv", numLinesToSkip = 1)
    void shouldSuccess(String cityName) {
        open("http://localhost:9999");
        SelectorsHelp.city.$(".input__control").setValue(cityName);
        SelectorsHelp.dateField.$(".input__control").setValue("\b\b\b\b\b\b\b\b");
        SelectorsHelp.dateField.$(".input__control").setValue(GeneratorData.PositiveData.date);
        SelectorsHelp.nameField.$(".input__control").setValue(GeneratorData.PositiveData.name);
        SelectorsHelp.phoneField.$(".input__control").setValue(GeneratorData.PositiveData.phone);
        SelectorsHelp.agreement.click();
        SelectorsHelp.button.click();
        SelectorsHelp.notification.$(byText("Успешно!")).waitUntil(Condition.visible, 15000);
        SelectorsHelp.notification.$(byText(GeneratorData.PositiveData.date)).waitUntil(Condition.visible, 15000);
        SelectorsHelp.dateField.$(".input__control").setValue("\b\b\b\b\b\b\b\b");
        SelectorsHelp.dateField.$(".input__control").setValue(GeneratorData.PositiveData.dateForReplan);
        SelectorsHelp.button.click();
        SelectorsHelp.notificationReplan.$(byText("Необходимо подтверждение")).waitUntil(Condition.visible, 15000);
        SelectorsHelp.notificationReplan.$(byText("Перепланировать")).click();
        SelectorsHelp.notification.$(byText("Успешно!")).waitUntil(Condition.visible, 15000);
        SelectorsHelp.notification.$(byText(GeneratorData.PositiveData.dateForReplan)).waitUntil(Condition.visible, 15000);
    }

    @Test
    void testCityFieldShouldError() {
        open("http://localhost:9999");
        SelectorsHelp.city.$(".input__control").setValue(GeneratorData.NegativeData.city);
        SelectorsHelp.dateField.$(".input__control").setValue("\b\b\b\b\b\b\b\b");
        SelectorsHelp.dateField.$(".input__control").setValue(GeneratorData.PositiveData.date);
        SelectorsHelp.nameField.$(".input__control").setValue(GeneratorData.PositiveData.name);
        SelectorsHelp.phoneField.$(".input__control").setValue(GeneratorData.PositiveData.phone);
        SelectorsHelp.agreement.click();
        SelectorsHelp.button.click();
        SelectorsHelp.city.$(".input__sub").shouldHave(Condition.exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    void testDateFieldShouldError() {
        open("http://localhost:9999");
        SelectorsHelp.city.$(".input__control").setValue("Москва");
        SelectorsHelp.dateField.$(".input__control").setValue("\b\b\b\b\b\b\b\b");
        SelectorsHelp.dateField.$(".input__control").setValue(GeneratorData.NegativeData.date);
        SelectorsHelp.nameField.$(".input__control").setValue(GeneratorData.PositiveData.name);
        SelectorsHelp.phoneField.$(".input__control").setValue(GeneratorData.PositiveData.phone);
        SelectorsHelp.agreement.click();
        SelectorsHelp.button.click();
        SelectorsHelp.dateField.$(".input__sub").shouldHave(Condition.exactText("Заказ на выбранную дату невозможен"));
    }

    @Test
    void testNameFieldShouldError() {
        open("http://localhost:9999");
        SelectorsHelp.city.$(".input__control").setValue("Москва");
        SelectorsHelp.dateField.$(".input__control").setValue("\b\b\b\b\b\b\b\b");
        SelectorsHelp.dateField.$(".input__control").setValue(GeneratorData.PositiveData.date);
        SelectorsHelp.nameField.$(".input__control").setValue(GeneratorData.NegativeData.name);
        SelectorsHelp.phoneField.$(".input__control").setValue(GeneratorData.PositiveData.phone);
        SelectorsHelp.agreement.click();
        SelectorsHelp.button.click();
        SelectorsHelp.nameField.$(".input__sub").shouldHave(Condition.exactText
                ("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void testDateWideGetForm() {
        open("http://localhost:9999");
        SelectorsHelp.city.$(".input__control").setValue("Москва");
        SelectorsHelp.dateField.$(".input__control").setValue("\b\b\b\b\b\b\b\b");
        SelectorsHelp.dateField.$(".input__control").setValue(GeneratorData.PositiveData.date);
        SelectorsHelp.nameField.$(".input__control").setValue(GeneratorData.PositiveData.name);
        SelectorsHelp.phoneField.$(".input__control").setValue(GeneratorData.PositiveData.phone);
        SelectorsHelp.agreement.click();
        SelectorsHelp.button.click();
        SelectorsHelp.notification.$(byText("Успешно!")).waitUntil(Condition.visible, 15000);
        SelectorsHelp.notification.$(byText(GeneratorData.PositiveData.date)).waitUntil(Condition.visible, 15000);
        SelectorsHelp.dateField.$(".input__control").setValue("\b\b\b\b\b\b\b\b");
        SelectorsHelp.dateField.$(".input__control").setValue(GeneratorData.PositiveData.date);
        SelectorsHelp.button.click();
        SelectorsHelp.dateField.$(".input__control").setValue("\b\b\b\b\b\b\b\b");
        SelectorsHelp.dateField.$(".input__control").setValue(GeneratorData.NegativeData.date);
        SelectorsHelp.notificationReplan.$(byText("Перепланировать")).click();
        SelectorsHelp.notificationError.$(byText("Ошибка")).shouldBe(Condition.visible);
    }
}

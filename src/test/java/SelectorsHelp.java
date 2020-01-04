import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class SelectorsHelp {
    final static SelenideElement city = $("[data-test-id = city]");
    final static SelenideElement dateField = $("[data-test-id = date]");
    final static SelenideElement nameField = $("[data-test-id = name]");
    final static SelenideElement phoneField = $("[data-test-id = phone]");
    final static SelenideElement agreement = $("[data-test-id = agreement]");
    final static SelenideElement notification = $("[data-test-id = success-notification]");
    final static SelenideElement notificationReplan = $("[data-test-id = replan-notification");
    final static SelenideElement button = $(byText("Запланировать"));
    final static SelenideElement notificationError = $("[data-test-id = error-notification]");
}

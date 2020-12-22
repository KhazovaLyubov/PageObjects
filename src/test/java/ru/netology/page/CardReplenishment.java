package ru.netology.page;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class CardReplenishment {
    private SelenideElement heading = $(byText("Пополнение карты"));
    private SelenideElement codeFieldValue = $("[class='input__box'] [type='text']");
    private SelenideElement codeFieldFrom = $("[class='input__box'] [type='tel']");
    private SelenideElement codeButton = $("[data-test-id='action-transfer'] [class='button__text']");

    public CardReplenishment() {
        heading.shouldBe(visible);
    }

    public DashboardPage validVerify1(DataHelper.CardReplenishmentInfo cardReplenishmentInfo, int value) {
        codeFieldValue.sendKeys(Keys.chord(Keys.CONTROL,"a"));
        codeFieldValue.sendKeys(Keys.BACK_SPACE);
        codeFieldValue.setValue(String.valueOf(value));
        codeFieldFrom.sendKeys(Keys.chord(Keys.CONTROL,"a"));
        codeFieldFrom.sendKeys(Keys.DELETE);
        codeFieldFrom.setValue(cardReplenishmentInfo.getCardNumber());
        codeButton.click();
        return new DashboardPage();
    }

    public SelenideElement noTransfer() {
        return $(withText("Операция невозможна")).shouldBe(visible);
    }
}

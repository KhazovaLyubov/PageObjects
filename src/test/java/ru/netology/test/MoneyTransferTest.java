package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;
import org.junit.jupiter.api.Order;


import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {
    @Test
    @Order(1)
    void transferOfFundsFromCardTo2ToCard1() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage =  verificationPage.validVerify(verificationCode);
        val value = 1000;
        val firstCardBalance = dashboardPage.getFirstCardBalance();
        val secondCardBalance = dashboardPage.getSecondCardBalance();
        val firstCardBalanceAfter = DataHelper.getBalanceAfterGet(firstCardBalance, value);
        val secondCardBalanceAfter = DataHelper.getBalanceAfterTransfer(secondCardBalance, value);
        val cardReplenishmentInfo = DataHelper.getSecondCard();
        val transferPage = dashboardPage.validToTransferAccount1();
        transferPage.validVerify1(cardReplenishmentInfo, value);
        val actualCurrentFirstCard = dashboardPage.getFirstCardBalance();
        Assertions.assertEquals(firstCardBalanceAfter, actualCurrentFirstCard);
        val actualCurrentSecondCard = dashboardPage.getSecondCardBalance();
        Assertions.assertEquals(secondCardBalanceAfter, actualCurrentSecondCard);
    }

    @Test
    @Order(2)
    void transferOfFundsFromCardTo1ToCard2() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage =  verificationPage.validVerify(verificationCode);
        val value = 1000;
        val firstCardBalance = dashboardPage.getFirstCardBalance();
        val secondCardBalance = dashboardPage.getSecondCardBalance();
        val firstCardBalanceAfter = DataHelper.getBalanceAfterTransfer(firstCardBalance, value);
        val secondCardBalanceAfter = DataHelper.getBalanceAfterGet(secondCardBalance, value);
        val cardReplenishmentInfo = DataHelper.getFirstCard();
        val cardReplenishment = dashboardPage.validToTransferAccount2();
        cardReplenishment.validVerify1(cardReplenishmentInfo, value);
        val actualCurrentFirstCard = dashboardPage.getFirstCardBalance();
        Assertions.assertEquals(firstCardBalanceAfter, actualCurrentFirstCard);
        val actualCurrentSecondCard = dashboardPage.getSecondCardBalance();
        Assertions.assertEquals(secondCardBalanceAfter, actualCurrentSecondCard);
    }

    @Test
    @Order(3)
    void NotTransfer() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage =  verificationPage.validVerify(verificationCode);
        val value = 11000;
        val cardReplenishmentInfo = DataHelper.getSecondCard();
        val cardReplenishment = dashboardPage.validToTransferAccount1();
        cardReplenishment.validVerify1(cardReplenishmentInfo, value);
        cardReplenishment.noTransfer();
    }
}

package ru.netology.data;

import lombok.AllArgsConstructor;
import lombok.Value;
import ru.netology.page.DashboardPage;


public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo(AuthInfo original) {
        return new AuthInfo("petya", "123qwerty");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    @AllArgsConstructor
    public static class CardReplenishmentInfo {
        private String cardNumber;
        private String startBalance;
    }

    public static CardReplenishmentInfo getFirstCard() {
        return new CardReplenishmentInfo("5559000000000001", "10000");
    }

    public static CardReplenishmentInfo getSecondCard() {
        return new CardReplenishmentInfo("5559000000000002", "10000");
    }

    public static int getBalanceAfterTransfer(int balanceBefore, int value) {
        int balanceAfter = balanceBefore - value;
        return balanceAfter;
    }

    public static int getBalanceAfterGet(int balanceBefore, int value) {
        int balanceAfter = balanceBefore + value;
        return balanceAfter;
    }
}


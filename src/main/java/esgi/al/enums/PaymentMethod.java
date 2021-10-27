package esgi.al.enums;

// Todo: enums sure are weird in java
public enum PaymentMethod
{
    CARD {
        String cardNumber;
        int expirationMonth;
        int expirationYear;
        int cryptogram;
    },
    PAYPAL {
        String account;
    },
}

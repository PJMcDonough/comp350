package com.example.comp350;
public class Banking {

    private int cardNumber;
    private int cardMonth;
    private int cardYear;
    private int cvv;

    public Banking(int cardNumber, int cardMonth, int cardYear, int cvvNumber)
    {
        setCardNumber(cardNumber);
        setCardMonth(cardMonth);
        setCardYear(cardYear);
        setCvv(cvvNumber);
    }

    public void setCardMonth(int cardMonth) {
        this.cardMonth = cardMonth;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setCardYear(int cardYear) {
        this.cardYear = cardYear;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public int getCardMonth() {
        return cardMonth;
    }

    public int getCardYear() {
        return cardYear;
    }

    public int getCvv() {
        return cvv;
    }
}

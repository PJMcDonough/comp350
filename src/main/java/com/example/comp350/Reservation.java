package com.example.comp350;
public class Reservation
{
    private int time;
    private double startTime;
    private String customer;
    private SpecialType special;
    private double price;
    private SpaType spa;
    private Banking banking;

    public Reservation(double startTime, String customer,SpaType spa,Banking banking,SpecialType special, int chooseTimeDuration, double value)
    {
        setPrice(value);
        setBank(banking);
        setSpa(spa);
        setTime(chooseTimeDuration);
        setStartTime(startTime);
        setName(customer);
        setSpecial(special);
    }

    public void setSpa(SpaType spa) {
        this.spa = spa;
    }

    //Not adding a getter?
    public void setBank(Banking banking) {
        this.banking = banking;
    }

    public void setName(String name){this.customer = customer;}

    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setTime(int timeVal)
    {
        this.time = timeVal;
    }

    public void setSpecial(SpecialType special) {
        this.special = special;
    }

    public SpecialType getSpecial() {
        return special;
    }

    public SpaType getSpa(){return spa;}

    public double getPrice() {
        return price;
    }

    public double getStartTime() { return startTime; }

    public String getName(){return this.customer;}

    public int getTime() {
        return time;
    }
}

public class Reservation
{
    private int time;
    private int startTime;
    private String customer;
    private specialType special;
    private double price;
    public Reservation(int startTime,String customer,specialType special,int chooseTimeDuration, double value)
    {
        setPrice(value);
        setTime(chooseTimeDuration);
        setStartTime(startTime);
        setName(customer);
        setSpecial(special);
    }

    public void setName(String name){this.customer = customer;}

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setTime(int timeVal)
    {
        this.time = timeVal;
    }

    public void setSpecial(specialType special) {
        this.special = special;
    }

    public specialType getSpecial() {
        return special;
    }

    public double getPrice() {
        return price;
    }

    public int getStartTime() { return startTime; }

    public String getName(){return this.customer;}

    public int getTime() {
        return time;
    }
}

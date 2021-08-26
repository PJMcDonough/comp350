public class Reservation {
    private int time;
    private int startTime,endTime;
    private specialType special;
    private double price;
    public Reservation(int startTime, specialType special,int chooseTimeDuration, double value,int endTime)
    {
        setPrice(value);
        setTime(chooseTimeDuration);
        setStartTime(startTime);
        setEndTime(endTime);
        this.special = special;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setTime(int timeVal)
    {
        this.time = timeVal;
    }

    public double getPrice() {
        return price;
    }

    public int getTime() {
        return time;
    }
}

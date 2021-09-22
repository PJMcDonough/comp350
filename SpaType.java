public enum SpaType {
    MASSAGE("MASSAGE",3.0),MINERAL_BATH("MINERAL BATH",2.5),FACIALS("FACIALS",2.0),
    SPECIAL_TREATMENT("SPECIAL TREATMENT",3.5);

    public final String label;
    public final double price;

    SpaType(String label, double price)
    {
        this.label = label;
        this.price = price;
    }
}

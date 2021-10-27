package com.example.comp350;
public enum SpecialType {
    SWEDISH("SWEDISH"),SHIATSU("SHIATSU"),DEEP_TISSUE("DEEP TISSUE"),
    NORMAL("NORMAL"), COLLAGEN("COLLAGEN"),
    HOT_STONE("HOT STONE"),SUGAR_SCRUB("SUGAR SCRUB"),
    HERBAL_BODY_WRAP("HERBAL BODY WRAP"),BOTANICAL_MUD_WRAP("BOTANICAL MUD WRAP");

    public final String label;

    SpecialType(String label)
    {
        this.label = label;
    }
}

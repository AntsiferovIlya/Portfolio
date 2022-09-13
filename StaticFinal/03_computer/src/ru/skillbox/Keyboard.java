package ru.skillbox;

public class Keyboard {

    private final String name;
    private final typeKB type;
    private final boolean illumination;
    private final double weightKey;

    public Keyboard(String name, typeKB type, boolean illumination, double weight) {
        this.name = name;
        this.type = type;
        this.illumination = illumination;
        this.weightKey = weight;
    }

    public Keyboard setName(String name) {
        return new Keyboard(name, type, illumination, weightKey);
    }

    public Keyboard setType(typeKB type) {
        return new Keyboard(name, type, illumination, weightKey);
    }

    public Keyboard setIllumination(boolean illumination) {
        return new Keyboard(name, type, illumination, weightKey);
    }

    public Keyboard setWeightKey(double weightKey) {
        return new Keyboard(name, type, illumination, weightKey);
    }

    public String getName() {
        return name;
    }

    public typeKB getType() {
        return type;
    }

    public boolean isIllumination() {
        return illumination;
    }

    public double getWeightKey() {
        return weightKey;
    }

    public String isIll() {
        if(isIllumination()) return "Да";
        else return "Нет";
    }

    enum typeKB {
        MEMBRANE,
        CAPACITIVE,
        MECHANICAL,
        OPTICAL,
        PROJECTION
    }

    public String toString() {
        return    name + ":\n"
                + "\tТип клавиатуры: " + type + "\n"
                + "\tПодстветка: " + illumination + "\n"
                + "\tВес: " + weightKey + "кг.\n";
    }
}

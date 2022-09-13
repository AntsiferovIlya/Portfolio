package ru.skillbox;

public class RAM {

    private final String name;
    private final TypeRAM type;
    private final int volume;
    private final double weightRAM;

    public RAM(String name, TypeRAM type, int volume, double weight) {
        this.name = name;
        this.type = type;
        this.volume = volume;
        this.weightRAM = weight;
    }

    public RAM setName(String name) {
        return new RAM(name, type, volume, weightRAM);
    }

    public RAM setType(TypeRAM type) {
        return new RAM(name, type, volume, weightRAM);
    }

    public RAM setVolume(int volume) {
        return new RAM(name, type, volume, weightRAM);
    }

    public RAM setWeightRAM(double weightRAM) {
        return new RAM(name, type, volume, weightRAM);
    }

    public String getName() {
        return name;
    }

    public TypeRAM getType() {
        return type;
    }

    public int getVolume() {
        return volume;
    }

    public double getWeightRAM() {
        return weightRAM;
    }

    enum TypeRAM {
        DDR3,
        DDR4,
        DDR5
    }

    public String toString() {
        return    name + ":\n"
                + "\tТип памяти: " + type + "\n"
                + "\tОбъем: " + volume + "ГБ\n"
                + "\tВес: " + weightRAM + "кг.\n";
    }
}

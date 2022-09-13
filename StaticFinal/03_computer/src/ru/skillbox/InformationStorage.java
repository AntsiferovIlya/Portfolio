package ru.skillbox;

public class InformationStorage {

    private final String name;
    private final TypeIS type;
    private final int memorySize;
    private final double weightIS;



    public InformationStorage(String name, TypeIS type, int memorySize, double weight) {
        this.name = name;
        this.type = type;
        this.memorySize = memorySize;
        this.weightIS = weight;
    }

    public InformationStorage setName(String name) {
        return new InformationStorage(name, type, memorySize, weightIS);
    }

    public InformationStorage setType(TypeIS type) {
        return new InformationStorage(name, type, memorySize, weightIS);
    }

    public InformationStorage setMemorySize(int memorySize) {
        return new InformationStorage(name, type, memorySize, weightIS);
    }

    public InformationStorage setWeightIS(double weightIS) {
        return new InformationStorage(name, type, memorySize, weightIS);
    }

    public String getName() {
        return name;
    }

    public TypeIS getType() {
        return type;
    }

    public int getMemorySize() {
        return memorySize;
    }

    public double getWeightIS() {
        return weightIS;
    }

    enum TypeIS {
        SSD,
        HDD
    }

    public String toString() {
        return    name + ":\n"
                + "\tТип памяти: " + type + "\n"
                + "\tОбъем: " + memorySize + "ГБ\n"
                + "\tВес: " + weightIS + "кг.\n";
    }
}
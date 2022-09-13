package ru.skillbox;

public class Processor {

    private final String name;
    private final double frequency;
    private final int numberOfCores;
    private final manufact manufacturer;
    private final double weightProc;

    public Processor(String name, double frequency, int numberOfCores, manufact manufacturer, double weight) {
        this.name = name;
        this.frequency = frequency;
        this.numberOfCores = numberOfCores;
        this.manufacturer = manufacturer;
        this.weightProc = weight;
    }

    public Processor setName(String name) {
        return new Processor(name, frequency, numberOfCores, manufacturer, weightProc);
    }

    public Processor setFrequency(double frequency) {
        return new Processor(name, frequency, numberOfCores, manufacturer, weightProc);
    }

    public Processor setNumberOfCores(int numberOfCores) {
        return new Processor(name, frequency, numberOfCores, manufacturer, weightProc);
    }

    public Processor setManufacturer(manufact manufacturer) {
        return new Processor(name, frequency, numberOfCores, manufacturer, weightProc);
    }

    public Processor setWeightProc(double weightProc) {
        return new Processor(name, frequency, numberOfCores, manufacturer, weightProc);
    }

    public String getName() {
        return name;
    }

    public double getFrequency() {
        return frequency;
    }

    public int getNumberOfCores() {
        return numberOfCores;
    }

    public manufact getManufacturer() {
        return manufacturer;
    }

    public double getWeightProc() {
        return weightProc;
    }

    enum manufact {
        INTEL,
        AMD,
        IBM,
        APPLE
    }

    public String toString() {
        return    name + ":\n"
                + "\tЧастота: " + frequency + "ГГц\n"
                + "\tЧисло ядер: " + numberOfCores + "\n"
                + "\tПроизводитель: " + manufacturer + "\n"
                + "\tВес: " + weightProc + "кг.\n";
    }
}

package ru.skillbox;

public class Computer {

    private final String vendor;
    private final String name;
    private final InformationStorage informationStorage;
    private final Keyboard keyboard;
    private final Processor processor;
    private final RAM ram;
    private final Screen screen;


    public Computer(String vendor, String name, InformationStorage informationStorage, Keyboard keyboard, Processor processor, RAM ram, Screen screen) {
        this.vendor = vendor;
        this.name = name;
        this.informationStorage = informationStorage;
        this.keyboard = keyboard;
        this.processor = processor;
        this.ram = ram;
        this.screen = screen;
    }


    public String getVendor() {
        return vendor;
    }

    public Computer setVendor(String vendor) {
        return new Computer(vendor, name, informationStorage, keyboard, processor, ram, screen);
    }

    public String getName() {
        return name;
    }

    public Computer setName(String name) {
        return new Computer(vendor, name, informationStorage, keyboard, processor, ram, screen);
    }

    public InformationStorage getInformationStorage() {
        return informationStorage;
    }

    public Computer setInformationStorage(InformationStorage informationStorage) {
        return new Computer(vendor, name, informationStorage, keyboard, processor, ram, screen);
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public Computer setKeyboard(Keyboard keyboard) {
        return new Computer(vendor, name, informationStorage, keyboard, processor, ram, screen);
    }

    public Processor getProcessor() {
        return processor;
    }

    public Computer setProcessor(Processor processor) {
        return new Computer(vendor, name, informationStorage, keyboard, processor, ram, screen);
    }

    public RAM getRam() {
        return ram;
    }

    public Computer setRam(RAM ram) {
        return new Computer(vendor, name, informationStorage, keyboard, processor, ram, screen);
    }

    public Screen getScreen() {
        return screen;
    }

    public Computer setScreen(Screen screen) {
        return new Computer(vendor, name, informationStorage, keyboard, processor, ram, screen);
    }

    public double getComputerWeight() {
        double computerWeight = informationStorage.getWeightIS() + keyboard.getWeightKey()
                + processor.getWeightProc() + ram.getWeightRAM() + screen.getWeightScreen();
        return computerWeight;
    }

    public String toString() {
        return    "=======================\n"
                + "=======================\n"
                + "Производитель: " + vendor + "\n"
                + "Название: " + name + "\n"
                + informationStorage + "\n"
                + keyboard + "\n"
                + processor + "\n"
                + ram + "\n"
                + screen + "\n"
                + "Общая масса компьютера: " + getComputerWeight() + "\n"
                + "=======================\n"
                + "=======================\n";
    }

}

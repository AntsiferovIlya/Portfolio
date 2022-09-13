package ru.skillbox;

public class Main {

    public static void main(String[] args) {

        InformationStorage samsung = new InformationStorage("Samsung", InformationStorage.TypeIS.SSD, 1024, 0.045);
        InformationStorage wd = new InformationStorage("WD", InformationStorage.TypeIS.HDD, 1024, 0.1);

        Keyboard razer = new Keyboard("Razer BlackWidow V3 Mini", Keyboard.typeKB.MECHANICAL, true, 0.849);
        Keyboard logitech = new Keyboard("Logitech Craft", Keyboard.typeKB.MEMBRANE, false, 0.96);

        Processor intel = new Processor("Intel Core i7", 2.9, 8, Processor.manufact.INTEL, 0.028);
        Processor amd = new Processor("AMD Ryzen 9 5950X ", 4.9, 16, Processor.manufact.AMD, 0.030);

        RAM corsair = new RAM("Corsair", RAM.TypeRAM.DDR5, 64, 0.020);
        RAM ibm = new RAM("IBM 43X5290", RAM.TypeRAM.DDR3, 8, 0.070);

        Screen asus = new Screen("Asus TUF Gaming VG259QR", 23.8, Screen.TypeScreen.IPS, 6.5);
        Screen lg = new Screen("LG 38GN950-B", 37.5, Screen.TypeScreen.IPS, 9.2);







        Computer rocket = new Computer("NIX", "Rocket X10", wd, razer, intel, corsair, asus);
        System.out.println(rocket);

        Computer rog = new Computer("Asus", "Asus TUF Gaming", samsung, logitech, amd, ibm, lg);
        System.out.println(rog);

    }
}

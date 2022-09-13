package ru.skillbox;

public class Screen {

    private final String name;
    private final double diagonal;
    private final TypeScreen type;
    private final double weightScreen;

    public Screen(String name, double diagonal, TypeScreen type, double weight) {
        this.name = name;
        this.diagonal = diagonal;
        this.type = type;
        this.weightScreen = weight;
    }

    public Screen setName(String name) {
        return new Screen(name, diagonal, type, weightScreen);
    }

    public Screen setDiagonal(double diagonal) {
        return new Screen(name, diagonal, type, weightScreen);
    }

    public Screen setType(TypeScreen type) {
        return new Screen(name, diagonal, type, weightScreen);
    }

    public Screen setWeightScreen(double weightScreen) {
        return new Screen(name, diagonal, type, weightScreen);
    }

    public String getName() {
        return name;
    }

    public double getDiagonal() {
        return diagonal;
    }

    public TypeScreen getType() {
        return type;
    }

    public double getWeightScreen() {
        return weightScreen;
    }

    enum TypeScreen {
        IPS,
        TN,
        VA
    }

    public String toString() {
        return    name + ":\n"
                + "\tДиагональ: " + diagonal + "\n"
                + "\tМатрица: " + type + "\n"
                + "\tВес: " + weightScreen + "кг.\n";
    }
}


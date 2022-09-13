public class Operator implements Employee{

    private static final int FIX_SALARY = 60_000;
    private int salary;
    private String position;
    private String info;


    public Operator() {
        getPosition();
        getMonthSalary();
    }


    @Override
    public int getMonthSalary() {
        salary = FIX_SALARY;
        return salary;
    }

    @Override
    public String getInfo() {
        this.info = "Operator — зарплата складывается только из" +
                "фиксированной части.";
        return this.info;
    }

    public String getPosition() {
        this.position = "Operator";
        return this.position;
    }

    public String toString() {
        return position + " " + salary;
    }
}

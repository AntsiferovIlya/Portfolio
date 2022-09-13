public class Manager implements Employee {

    private final int FIXSALARY = 70_000;
    private int salary;
    private String position;
    private String info;
    private final int earningsForCompany;

    public Manager() {
        getPosition();
        getMonthSalary();
        this.earningsForCompany = (int) (Math.random() * 25_000) + 115_000;
    }


    @Override
    public int getMonthSalary() {
        return salary = FIXSALARY + (int) (earningsForCompany * 0.05);
    }

    @Override
    public String getInfo() {
        this.info = "Manager — зарплата складывается из фиксированной части" +
                "и бонуса в виде 5% от заработанных для компании денег.";
        return this.info;
    }

    public String getPosition() {
        this.position = "Manager";
        return this.position;
    }

    public String toString() {
        return "\n" + position + " " + salary;
    }
}

public class TopManager implements Employee{
    private final int FIXEDSALARY = 150_000;
    private int salary;
    private String position;
    private String info;

    public TopManager() {
        getPosition();
        getMonthSalary();
    }

    @Override
    public int getMonthSalary() {
        if(Company.getIncome() > 10_000_000) {
            salary = (int) (FIXEDSALARY + FIXEDSALARY * 1.5);
        } else salary = FIXEDSALARY;
        return salary;
    }

    @Override
    public String getInfo() {
        this.info = "TopManager — зарплата складывается из фиксированной части" +
                "и бонуса в виде 150% от заработной платы," +
                "если доход компании более 10 млн рублей.";
        return this.info;
    }

    public String getPosition() {
        this.position = "Top Manager";
        return this.position;
    }

    @Override
    public String toString() {
        return position + " " + salary;
    }
}

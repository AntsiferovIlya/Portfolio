public class Main {

    public static void main(String[] args) {
        Company company = new Company();
        company.setIncome(15_000_000);
        hireEmployees(company);
        printHighestSalaries(company, 15);
        printLowestSalaries(company, 15);
        fireHalfEmployees(company);
        printHighestSalaries(company, 15);
        printLowestSalaries(company, 15);
    }

    private static void hireEmployees(Company company) {
        for(int i = 0; i < 180; i++) {
            Employee operator = new Operator();
            company.hire(operator);
        }
        for(int i = 0; i < 80; i++) {
            Employee manager = new Manager();
            company.hire(manager);
        }
        for(int i = 0; i < 10; i++) {
            Employee topManager = new TopManager();
            company.hire(topManager);
        }
        System.out.println("Employees count: " + company.countEmployees());
    }

    private static void fireHalfEmployees (Company company) {
        for(int i = 0; i < (company.countEmployees()) / 2; i++) {
            int index = (int) (Math.random() * company.countEmployees());
            Employee loser = company.getEmployeeArrayList().get(index);
            company.fire(loser);
        }
    }

    private static void printHighestSalaries(Company company, int count) {
        System.out.println(count + " самых высоких зарплат: ");
        for (Employee employee : company.getTopSalaryStaff(count)) {
            System.out.println(employee.getMonthSalary());
        }
    }

    private static void printLowestSalaries(Company company, int count) {
        System.out.println(count + " самых низких зарплат: ");
        for (Employee employee : company.getLowestSalaryStaff(count)) {
            System.out.println(employee.getMonthSalary());
        }
    }

}

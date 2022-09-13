import java.util.*;
import java.util.stream.Collectors;

public class Company {

    public static double income;

    public static List<Employee> employeeArrayList = new ArrayList<>();

    public static double getIncome() {
        return income;
    }

    public static void setIncome(double income) {
        Company.income = income;
    }

    public Company() {
        new ArrayList<String>();
        setIncome(income);
    }

    public void hireAll(Collection<Employee> employees) {
        this.employeeArrayList.addAll(employees);
    }

    public void hire(Employee employee) {
        this.employeeArrayList.add(employee);
    }

    public void fire(Employee employee) {
        employeeArrayList.remove(employee);
    }

    public List<Employee> getTopSalaryStaff(int count) {
        return getFilteredList(count, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o2.getMonthSalary() - o1.getMonthSalary();
            }
        });
    }

    public List<Employee> getLowestSalaryStaff(int count) {
        return getFilteredList(count, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.getMonthSalary() - o2.getMonthSalary();
            }
        });
    }

//    public List<Employee> getLowestSalary(int count) {
//        return employeeArrayList.stream()
//                .sorted(Comparator.comparing(Employee::getMonthSalary))
//                .limit(count)
//                .collect(Collectors.toList());
//    }

    private List<Employee> getFilteredList(int count, Comparator<Employee> comparator) {
        List<Employee> copyList = new ArrayList<Employee>(employeeArrayList);
        Collections.sort(copyList, comparator);
        List<Employee> result = new ArrayList<Employee>();
        for (int i = 0; i < count; i++) {
            result.add(copyList.get(i));
        }
        return result;
    }

    public int countEmployees() {
        return employeeArrayList.size();
    }

    public List<Employee> getEmployeeArrayList() {
        return employeeArrayList;
    }
}

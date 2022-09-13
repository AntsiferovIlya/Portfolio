import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static final String STAFF_TXT = "data/staff.txt";

    public static void main(String[] args) {
        List<Employee> staff = Employee.loadStaffFromFile(STAFF_TXT);
        sortBySalaryAndAlphabet(staff);
        System.out.println(staff);
    }

    public static void sortBySalaryAndAlphabet(List<Employee> staff) {
        //TODO Метод должен отсортировать сотрудников по заработной плате и алфавиту.

        staff.sort((o1, o2) -> {
                    int compare = o1.getSalary().compareTo(o2.getSalary());
                    if(compare == 0) return o1.getName().compareTo(o2.getName());
                    else return compare;
                });


    }
}
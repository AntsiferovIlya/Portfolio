import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerStorage {
    private final Map<String, Customer> storage;

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) {
        final int INDEX_NAME = 0;
        final int INDEX_SURNAME = 1;
        final int INDEX_EMAIL = 2;
        final int INDEX_PHONE = 3;

        String[] components = data.split("\\s+");
        if(components.length != 4) {
            throw new IllegalArgumentException("Неверный формат команды!");
        }

        String name = components[INDEX_NAME] + " " + components[INDEX_SURNAME];

        if(!validEmail(components[INDEX_EMAIL]) || !isPhone(components[INDEX_PHONE])) {
            throw  new IllegalArgumentException("Email или(и) номер телефона введен(ы) некорректно!");
        }

        storage.put(name, new Customer(name, components[INDEX_PHONE], components[INDEX_EMAIL]));
    }

    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        storage.remove(name);
    }

    public Customer getCustomer(String name) {
        return storage.get(name);
    }

    public int getCount() {
        return storage.size();
    }

    public boolean emptyName(String name) {
        if(name.isEmpty()) return false;
        return true;
    }

    public boolean validEmail(String email) {
        String regex = "^[a-z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-z\\-0-9]+\\.)+[a-z]{2,}))$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean isPhone(String phone) {
        String regex = "[+][\\d]{11}";
        return phone.matches(regex);
    }

}
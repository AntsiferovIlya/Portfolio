import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public class Main {

    private static Logger logger;
    private static final Marker EXCEPTION_MARKER = MarkerManager.getMarker("Exception");
    private static final Marker SEARCH_MARKER = MarkerManager.getMarker("Search");

    private static final String ADD_COMMAND = "add Василий Петров " +
            "vasily.petrov@gmail.com +79215637722";
    private static final String COMMAND_EXAMPLES = "\t" + ADD_COMMAND + "\n" +
            "\tlist\n\tcount\n\tremove Василий Петров";
    private static final String COMMAND_ERROR = "Wrong command! Available command examples: \n" +
            COMMAND_EXAMPLES;
    private static final String helpText = "Command examples:\n" + COMMAND_EXAMPLES;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerStorage executor = new CustomerStorage();

        logger = LogManager.getRootLogger();

        while (true) {

            try {
                String command = scanner.nextLine();
                String[] tokens = command.split("\\s+", 2);

                if (tokens[0].equals("add")) {
                    executor.addCustomer(tokens[1]);
                    logger.info(SEARCH_MARKER, "Добавление покупателя " + tokens[1]);
                } else if (tokens[0].equals("list")) {
                    executor.listCustomers();
                    logger.info(SEARCH_MARKER, "Просмотр пользователей ");
                } else if (tokens[0].equals("remove")) {
                    executor.removeCustomer(tokens[1]);
                    logger.info(SEARCH_MARKER, "Удаление пользователя ", tokens[1]);
                } else if (tokens[0].equals("count")) {
                    System.out.println("There are " + executor.getCount() + " customers");
                    logger.info(SEARCH_MARKER, "Подсчет количества пользователей ");
                } else if (tokens[0].equals("help")) {
                    System.out.println(helpText);
                    logger.info(SEARCH_MARKER, "Просмотр help ");
                } else {
                    System.out.println(COMMAND_ERROR);
                    logger.info(SEARCH_MARKER, "Некорректный ввод команды, вызов подсказки ", COMMAND_ERROR);
                }
            } catch(Exception ex) {
                System.out.println("Команда введена некорректно!");
                logger.error(EXCEPTION_MARKER, ex.toString());
            }
        }
    }
}

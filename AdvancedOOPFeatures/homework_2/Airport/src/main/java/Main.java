import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println(findPlanesLeavingInTheNextTwoHours(Airport.getInstance()));

    }

    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {
        //TODO Метод должден вернуть список рейсов вылетающих в ближайшие два часа.

        LocalDateTime nowDate = LocalDateTime.now(); // создаем переменную для текущей даты
        LocalDateTime twoHoursLater = nowDate.plusHours(2);  // создаем переменную с крайней датой
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm"); // задаем формат времени

       return airport.getTerminals().stream()
                .map(Terminal::getFlights)
                .flatMap(Collection::stream)//для упрощения
                .filter(flight -> {
                    LocalDateTime dateTime = toLocalDateTime(flight.getDate());
                    return flight.getType().equals(Flight.Type.DEPARTURE)
                            && dateTime.isAfter(nowDate)
                            && dateTime.isBefore(twoHoursLater);
                })  //фильтруем записи по статусу, и диапазону дат
               .collect(Collectors.toList());        //собираем данные в List<Flight>

    }

    private static LocalDateTime toLocalDateTime(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

}
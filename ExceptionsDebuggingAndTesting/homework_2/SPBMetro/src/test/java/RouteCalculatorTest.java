import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RouteCalculatorTest extends TestCase{

    List<Station> threeTransfersRoute;
    List<Station> twoTransfersRoute;
    List<Station> noTransferRoute;

    StationIndex stationIndex;
    RouteCalculator calculator;

    Station shabolovskaya;
    Station tretyakovskaya;
    Station paveletskaya;
    Station chistiePrudi;


    //Схема:
    //      Line1      |      |      Line2       |      |      Line3
    //  [Шаболовская]  |      |   [Павелецкая]   |      |  [Чистые пруды]
    //  [Октябрьская]  | ---> |  [Новокузнецкая] |      |  [Лубянка]
    // [Третьяковская] |      |   [Театральная]  | ---> |  [Охотный ряд]


    @Override
    protected void setUp() throws Exception {
        stationIndex = new StationIndex();

        Line line1 = new Line(1, "ККрасная");
        Line line2 = new Line(2, "Зеленая");
        Line line3 = new Line(3, "Фиолетовая");

        shabolovskaya = new Station("Шаболовская", line1);
        Station octyaborskaya = new Station("Октябрьская", line1);
        tretyakovskaya = new Station("Третьяковская", line1);
        paveletskaya = new Station("Павелецкая", line2);
        Station novokuznetskaya = new Station("Новокузнецкая", line2);
        Station teatralnaya = new Station("Театральная", line2);
        chistiePrudi = new Station("Чистые пруды", line3);
        Station lubynka = new Station("Лубянка", line3);
        Station ohotniyRyd = new Station("Охотный ряд", line3);

        Stream.of(line1, line2, line3).forEach(stationIndex::addLine);

        Stream.of(shabolovskaya, octyaborskaya, tretyakovskaya, paveletskaya, novokuznetskaya, teatralnaya,
                        chistiePrudi, lubynka, ohotniyRyd)
                .peek(s -> s.getLine().addStation(s)).forEach(stationIndex::addStation);

        stationIndex.addConnection(Stream.of(octyaborskaya, novokuznetskaya).collect(Collectors.toList()));
        stationIndex.addConnection(Stream.of(teatralnaya, ohotniyRyd).collect(Collectors.toList()));
        stationIndex.getConnectedStations(octyaborskaya);
        stationIndex.getConnectedStations(teatralnaya);

        calculator = new RouteCalculator(stationIndex);

        noTransferRoute = Stream.of(shabolovskaya, octyaborskaya, tretyakovskaya).collect(Collectors.toList());
        twoTransfersRoute = Stream.of(shabolovskaya, octyaborskaya, novokuznetskaya, paveletskaya).collect(Collectors.toList());
        threeTransfersRoute = Stream.of(shabolovskaya, octyaborskaya, novokuznetskaya, teatralnaya, ohotniyRyd, lubynka, chistiePrudi)
                .collect(Collectors.toList());
    }

    public void testCalculateDuration() {
        double actual = RouteCalculator.calculateDuration(threeTransfersRoute);
        double expected = 17.0;
        assertEquals(expected, actual);
    }

    public void testGetShortestRouteNoTransfer() {
        List<Station> actualNoTransfer = calculator.getShortestRoute(shabolovskaya, tretyakovskaya);
        List<Station> expectedNoTransfers = noTransferRoute;
        assertEquals(expectedNoTransfers, actualNoTransfer);


    }

    public void testGetShortestRouteWithOneTransfer() {
        List<Station> actualTwoTransfer = calculator.getShortestRoute(shabolovskaya, paveletskaya);
        List<Station> expectedTwoTransfers = twoTransfersRoute;
        assertEquals(expectedTwoTransfers, actualTwoTransfer);
    }

    public void testGetShortestRouteWithTwoTransfer() {
        List<Station> actualThreeTransfer = calculator.getShortestRoute(shabolovskaya, chistiePrudi);
        List<Station> expectedThreeTransfers = threeTransfersRoute;
        assertEquals(expectedThreeTransfers, actualThreeTransfer);
    }
}




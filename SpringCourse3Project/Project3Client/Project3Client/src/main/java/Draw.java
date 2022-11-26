import dto.MeasurementDTO;
import dto.SensorDTO;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*********************
 * @CREATED: 20.11.2022
 * @AUTHOR: Hedgy
 **********************/
public class Draw {
    public static void main(String[] args) {
        List<Double> temperatures = getTemperaturesFromServer();
        drawChart(temperatures);
    }

    private static List<Double> getTemperaturesFromServer() {
        final RestTemplate restTemplate = new RestTemplate();
        final String url = "http://localhost:8080/measurements";

        MeasurementDTO[] jResponse = restTemplate.getForObject(url, MeasurementDTO[].class);

        if (jResponse == null)
            return Collections.emptyList();

        List<Double> tempList =  Arrays.stream(jResponse).map(MeasurementDTO::getValue).collect(Collectors.toList());
        for (Double val: tempList) {
            System.out.println(val);
        }

        return tempList;
    }

    private static void drawChart(List<Double> temperatures) {
        double[] xData = IntStream.range(0, temperatures.size()).asDoubleStream().toArray();
        double[] yData = temperatures.stream().mapToDouble(x -> x).toArray();

        XYChart chart = QuickChart.getChart("Temperatures", "X", "Y", "temperature",
                xData, yData);

        new SwingWrapper(chart).displayChart();
    }
}

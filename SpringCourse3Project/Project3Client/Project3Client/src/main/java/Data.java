import dto.SensorDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/*********************
 * @CREATED: 20.11.2022
 * @AUTHOR: Hedgy
 **********************/
public class Data {
    private final static String serverUrl = "http://localhost:8080/";
    private final static String sensorName = "Sensor1";
    private final static int count = 10;

    public static void main(String[] args) {

        registerSensor();

        double minTemp = 1.0;
        double maxTemp = 50.0;
        for (int i = 0; i < count; i++) {
            System.out.println(i+" : "+addMeasurement(ThreadLocalRandom.current().nextDouble(minTemp, maxTemp), ThreadLocalRandom.current().nextBoolean()));
        }

    }

    private static void registerSensor() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String url = "";
        Boolean sensorExists = false;

        try {
            url = serverUrl + "sensors/";
            SensorDTO[] response = restTemplate.getForObject(url, SensorDTO[].class);
            for (SensorDTO sensor: response) {
                if (sensor.getName().equals(sensorName)) {
                    sensorExists = true;
                }
            }

            if (!sensorExists) {
                url = serverUrl + "sensors/registration";
                Map<String, Object> jData = new HashMap<>();
                jData.put("name", sensorName);

                HttpEntity<Object> request = new HttpEntity<>(jData, headers);
                String result = restTemplate.postForObject(url, request, String.class);

                System.out.println("Регистрация сенсора - " + sensorName + " : OK");
            }
        } catch (HttpClientErrorException e) {
            System.out.println("Регистрация сенсора - "+ sensorName + " : ERROR : "+e.getMessage());
        }
    }

    private static String addMeasurement(double value, boolean raining) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String url = serverUrl + "measurements/add";

        Map<String, Object> jData = new HashMap<>();
        jData.put("value", value);
        jData.put("raining", raining);
        jData.put("sensor", Map.of("name", sensorName));

        HttpEntity<Object> request = new HttpEntity<>(jData, headers);

        try {
            restTemplate.postForObject(url, request, String.class);
            return "OK";
        } catch (HttpClientErrorException e) {
            System.out.println("Измерение -"+ value +" : ERROR : "+e.getMessage());
            return "ERROR";
        }
    }
}

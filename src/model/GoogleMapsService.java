package model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class GoogleMapsService {

    private static final String API_KEY = "AIzaSyDtGGwGqr5li-Dtews0_eqkaTxG0cR6OJc"; // Ersätt med din API-nyckel

    // Hämta användarens position i Malmö (exempelvis latitud och longitud)
    public static String getNearbyOutdoorGyms(double userLat, double userLng) {
        try {
            // Bygg URL-sträng för Google Places API-sökning
            String placesUrl = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?"
                    + "location=" + userLat + "," + userLng
                    + "&radius=5000"  // Sök inom 5 km radius
                    + "&type=gym"  // Sök efter gym (kan vara utegym eller andra gym)
                    + "&keyword=outdoor"  // Lägg till "outdoor" för att fokusera på utegym
                    + "&key=" + API_KEY;

            // Gör en GET-förfrågan till Places API
            URL url = new URL(placesUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Hantera JSON-svaret
            JSONObject jsonResponse = new JSONObject(response.toString());
            JSONArray results = jsonResponse.getJSONArray("results");

            // Om vi hittar resultat, skriv ut gymmens namn och adress
            StringBuilder gyms = new StringBuilder();
            for (int i = 0; i < results.length(); i++) {
                JSONObject gym = results.getJSONObject(i);
                String name = gym.getString("name");
                String address = gym.getString("vicinity");
                gyms.append(name).append(" - ").append(address).append("\n");
            }

            if (gyms.length() > 0) {
                return gyms.toString();
            } else {
                return "Inga utegym hittades i närheten.";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Ett fel uppstod vid API-anropet.";
        }
    }
}

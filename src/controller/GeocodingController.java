package controller;

import model.GoogleMapsService;  // Uppdatera till den nya klassen

public class GeocodingController {

    private GoogleMapsService googleMapsService;

    public GeocodingController() {
        this.googleMapsService = new GoogleMapsService();
    }

    public String getCoordinatesForAddress(double userLat, double userLng) {
        // Anropa modellen för att hämta närmaste utegym baserat på användarens position
        return googleMapsService.getNearbyOutdoorGyms(userLat, userLng);
    }

    public void getCoordinatesForAddress(String address) {

    }
}

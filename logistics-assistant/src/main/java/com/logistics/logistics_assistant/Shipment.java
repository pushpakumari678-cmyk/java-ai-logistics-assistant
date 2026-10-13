
package com.logistics.logistics_assistant;

public class Shipment {

    private String id;
    private String status;
    private String location;
    private String weatherCondition;

    public Shipment() {
    }

    public Shipment(String id, String status, String location,
                    String weatherCondition) {
        this.id = id;
        this.status = status;
        this.location = location;
        this.weatherCondition = weatherCondition;
    }

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getLocation() {
        return location;
    }

    public String getWeatherCondition() {
        return weatherCondition;
    }
}

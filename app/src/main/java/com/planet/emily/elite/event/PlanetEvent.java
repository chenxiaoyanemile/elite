package com.planet.emily.elite.event;

public class PlanetEvent {

    private String planetId;
    private String planetName;
    private String planetDescription;

    public PlanetEvent() {
    }

    public PlanetEvent(String planetId, String planetName, String planetDescription) {
        this.planetId = planetId;
        this.planetName = planetName;
        this.planetDescription = planetDescription;
    }

    public String getPlanetId() {
        return planetId;
    }

    public void setPlanetId(String planetId) {
        this.planetId = planetId;
    }

    public String getPlanetName() {
        return planetName;
    }

    public void setPlanetName(String planetName) {
        this.planetName = planetName;
    }

    public String getPlanetDescription() {
        return planetDescription;
    }

    public void setPlanetDescription(String planetDescription) {
        this.planetDescription = planetDescription;
    }
}

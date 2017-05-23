package se.wgco.jgf.adventura_ultima.city;

import se.wgco.jgf.adventura_ultima.city_elements.CityElement;
import se.wgco.jgf.adventura_ultima.city_elements.CityRoad;
import se.wgco.jgf.adventura_ultima.city_elements.buildings.Building;
import se.wgco.jgf.adventura_ultima.city_elements.city_element_interfaces.CityElementInitializer;
import se.wgco.jgf.adventura_ultima.navigation.CityCoordinate;

/**
 * Created by Josef on 30/04/2014.
 * <p>
 *     The class representing cities.
 *     <br/>
 *     Cities are presented in a screen separate
 *     from the open world and can be entered by
 *     standing on their open world representation
 *     and executing the "enter city"-command.
 * </p>
 */
public class City {
    private String name = "";
    private CitySize size = null;
    private CityElement[][] cityMap = null;
    private int mapWidth = 0;
    private int mapHeight = 0;

    public City(String name, CitySize size) {
        this.name = name;
        this.size = size;
        randomizeMap();
    }

    public String getName() {
        return name;
    }

    public CitySize getSize() {
        return size;
    }

    public CityElement[][] getCityMap() {
        return cityMap;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    private void randomizeMap() {
        int buildingNumber = 0;
        switch (size) {
            case SMALL:
                buildingNumber = 6;
                mapWidth = 4 + ((int) (Math.random() * 2));
                mapHeight = 4 + ((int) (Math.random() * 2));
                break;
            case MEDIUM:
                buildingNumber = 9;
                mapWidth = 5 + ((int) (Math.random() * 2));
                mapHeight = 5 + ((int) (Math.random() * 2));
                break;
            case LARGE:
                buildingNumber = 12;
                mapWidth = 6 + ((int) (Math.random() * 2));
                mapHeight = 5 + ((int) (Math.random() * 2));
                break;
        }

        cityMap = new CityElement[mapWidth][mapHeight];

        randomizeBuildings(buildingNumber);
        generateRoads();
    }

    private void randomizeBuildings(int buildingNumber) {
        for (int n = 0; n < buildingNumber; n++) {
            int randomX = 1 + ((int) (Math.random() * (mapWidth - 2)));
            int randomY = 1 + ((int) (Math.random() * (mapHeight - 2)));
            while (cityMap[randomX][randomY] != null) {
                randomX = 1 + ((int) (Math.random() * (mapWidth - 2)));
                randomY = 1 + ((int) (Math.random() * (mapHeight - 2)));
            }
            Building randomBuilding = new Building(Building.getRandomBuildingInitializer());
            CityCoordinate currentCoordinate = new CityCoordinate(randomX, randomY);
            cityMap[randomX][randomY] = new CityElement(currentCoordinate, randomBuilding);
        }
    }

    private void generateRoads() {
        for (int x = 0; x < mapWidth; x++) {
            for (int y = 0; y < mapHeight; y++) {
                if(cityMap[x][y] == null) {
                    CityElementInitializer cityRoad = new CityRoad();
                    cityMap[x][y] = new CityElement(new CityCoordinate(x, y), cityRoad);
                }
            }
        }
    }
}

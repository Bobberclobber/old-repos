package se.wgco.jgf.adventura_ultima.city_elements.buildings.shops;

import se.wgco.jgf.adventura_ultima.city_elements.buildings.BuildingType;
import se.wgco.jgf.adventura_ultima.city_elements.buildings.building_interfaces.ShopInitializer;
import se.wgco.jgf.adventura_ultima.city_elements.buildings.shops.initializers.Alchemist;
import se.wgco.jgf.adventura_ultima.city_elements.buildings.shops.initializers.Armorer;
import se.wgco.jgf.adventura_ultima.city_elements.buildings.shops.initializers.MagicDealer;
import se.wgco.jgf.adventura_ultima.city_elements.buildings.shops.initializers.WeaponDealer;
import se.wgco.jgf.adventura_ultima.city_elements.city_element_interfaces.BuildingInitializer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Josef on 14/05/2014.
 * <p>
 *     A meta-class which all shops extend
 * </p>
 */
public class Shop implements BuildingInitializer {

    private ShopType type = null;
    private static List<ShopInitializer> shopInitializers = null;

    public Shop(ShopInitializer initializer) {
        type = initializer.getShopType();
    }

    public ShopType getShopType() {
        return type;
    }

    @Override
    public BuildingType getBuildingType() {
        return BuildingType.SHOP;
    }

    private static void makeInitializerList() {
        shopInitializers.add(new Alchemist());
        shopInitializers.add(new Armorer());
        shopInitializers.add(new MagicDealer());
        shopInitializers.add(new WeaponDealer());
    }

    public static ShopInitializer getRandomShopInitializer() {
        shopInitializers = new ArrayList<ShopInitializer>();
        makeInitializerList();
        int randomNum = (int) (Math.random() * shopInitializers.size());
        return shopInitializers.get(randomNum);
    }
}

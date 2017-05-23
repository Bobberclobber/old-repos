package se.wgco.jgf.adventura_ultima.city_elements.buildings.shops.initializers;

import se.wgco.jgf.adventura_ultima.city_elements.buildings.BuildingType;
import se.wgco.jgf.adventura_ultima.city_elements.buildings.building_interfaces.ShopInitializer;
import se.wgco.jgf.adventura_ultima.city_elements.buildings.shops.ShopType;

/**
 * Created by Josef on 14/05/2014.
 * <p>
 *     An ShopInitializer defining the shop weapon dealer
 * </p>
 */
public class WeaponDealer implements ShopInitializer {

    @Override
    public ShopType getShopType() {
        return ShopType.WEAPON_DEALER;
    }

    @Override
    public BuildingType getBuildingType() {
        return BuildingType.SHOP;
    }
}

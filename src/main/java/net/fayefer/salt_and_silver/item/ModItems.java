package net.fayefer.salt_and_silver.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fayefer.salt_and_silver.SaltAndSilver;
import net.fayefer.salt_and_silver.item.custom.EctoplasmItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item RAW_IMPURE_SILVER = registerItem("raw_impure_silver", new Item(new Item.Settings()));
    public static final Item IMPURE_SILVER_INGOT = registerItem("impure_silver_ingot", new Item(new Item.Settings()));
    public static final Item SILVER_INGOT = registerItem("silver_ingot", new Item(new Item.Settings()));
    public static final Item SILVER_NUGGET = registerItem("silver_nugget", new Item(new Item.Settings()));

    public static final Item ECTOPLASM = registerItem("ectoplasm", new EctoplasmItem(new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(SaltAndSilver.MOD_ID,name), item);
    }

    public static void registerModItems() {
        SaltAndSilver.LOGGER.info("Registering Mod Items for " + SaltAndSilver.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(RAW_IMPURE_SILVER);
            entries.add(IMPURE_SILVER_INGOT);
            entries.add(SILVER_INGOT);
            entries.add(SILVER_NUGGET);

            entries.add(ECTOPLASM);
        });
    }
}

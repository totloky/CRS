package mod.totloky.crs.blocks.farms;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class FarmLogic {

    public FarmLogic() {}

    public static ItemStack farmConsumer(String farmType) {

        try {
            EFarmType eFarmType = EFarmType.valueOf(farmType);
            switch (eFarmType) {
                case IRON:
                    return new ItemStack(Item.getByNameOrId("minecraft:iron_ingot"));

                case WOOD:
                    return new ItemStack(Item.getByNameOrId("minecraft:log"));

                case STONE:
                    return new ItemStack(Item.getByNameOrId("minecraft:stone"));
            }
        }
        catch (IllegalArgumentException ignored) {
        }

        return new ItemStack(Item.getByNameOrId("minecraft:air"));
    }
}

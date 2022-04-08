package mod.totloky.crs.nbt;

import mod.totloky.crs.CRS;
import mod.totloky.crs.MySQLHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

import java.sql.SQLException;

import static mod.totloky.crs.nbt.InjectionUtil.Null;

public class StatsStorage {

    @CapabilityInject(IStat.class)
    public static final Capability<IStat> STATS_CAPABILITY = Null();

    public static final EnumFacing DEFAULT_FACING = Null();

    public static final ResourceLocation ID = new ResourceLocation(CRS.MODID, "Stats");

    public static void register(){
        CapabilityManager.INSTANCE.register(IStat.class, new Capability.IStorage<IStat>() {

            @Override
            public NBTBase writeNBT(Capability<IStat> capability, IStat instance, EnumFacing side) {
                NBTTagCompound nbt = new NBTTagCompound();

                nbt.setString("STR", instance.get("STR"));
                nbt.setString("DEX", instance.get("DEX"));
                nbt.setString("KNO", instance.get("KNO"));
                nbt.setString("PER", instance.get("PER"));
                nbt.setString("END", instance.get("END"));
                nbt.setString("MAG", instance.get("MAG"));

                return nbt;
            }

            @Override
            public void readNBT(Capability<IStat> capability, IStat instance, EnumFacing side, NBTBase nbt) {
                if(nbt instanceof NBTTagCompound)
                {
                    NBTTagCompound tag = (NBTTagCompound)nbt;

                    instance.set(tag.getString("STR"), "0");
                    instance.set(tag.getString("DEX"), "0");
                    instance.set(tag.getString("KNO"), "0");
                    instance.set(tag.getString("PER"), "0");
                    instance.set(tag.getString("END"), "0");
                    instance.set(tag.getString("MAG"), "0");

                    //instance.set(tag.getString("any"), "any");
                    System.out.println(tag);
                }
            }
        }, Stats::new);
    }


    @Nullable
    public static IStat getStats(final EntityLivingBase entity){
        return CapabilityUtils.getCapability(entity, STATS_CAPABILITY, DEFAULT_FACING);
    }

    public static ICapabilityProvider createProvider(final IStat stats){
        return new CapabilityProviderSerializable<>(STATS_CAPABILITY, DEFAULT_FACING, stats);
    }

    @Mod.EventBusSubscriber(modid = CRS.MODID)
    private static class EventHandler {
        @SubscribeEvent
        public static void attachCapabilities(final AttachCapabilitiesEvent<Entity> event) {
            if (event.getObject() instanceof EntityPlayer) {
                final Stats stats = new Stats();
                event.addCapability(ID, createProvider(stats));
            }
        }

        /*@SubscribeEvent
        public static void playerClone(final PlayerEvent.Clone event) {
            final IStat oldMana = getStats(event.getOriginal());
            final IStat newMana = getStats(event.getEntityPlayer());

            if (newMana != null && oldMana != null) {
                newMana.setmax(oldMana.getmax());
                newMana.setcurrent(oldMana.getcurrent());
            }
        }*/

        @SideOnly(Side.SERVER)
        @SubscribeEvent
        public static void onPlayerLogsIn(PlayerEvent.PlayerLoggedInEvent event) throws SQLException {
            MySQLHandler.dbOpenConnection();

            EntityPlayer player = event.player;
            String name = player.getName();
            IStat stats = player.getCapability(STATS_CAPABILITY, null);
            stats.setCurrent("STR", name);
            stats.setCurrent("DEX", name);
            stats.setCurrent("KNO", name);
            stats.setCurrent("PER", name);
            stats.setCurrent("END", name);
            stats.setCurrent("MAG", name);
        }
    }
}

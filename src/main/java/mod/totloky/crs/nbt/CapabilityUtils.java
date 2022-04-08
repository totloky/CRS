package mod.totloky.crs.nbt;

import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;

public class CapabilityUtils {
    /**
     * Utility methods for Capabilities.
     *
     * @author Choonster
     */
    @Nullable
    public static <T> T getCapability(@Nullable final ICapabilityProvider provider, final Capability<T> capability, @Nullable final EnumFacing facing) {
        return provider != null && provider.hasCapability(capability, facing) ? provider.getCapability(capability, facing) : null;
    }
}

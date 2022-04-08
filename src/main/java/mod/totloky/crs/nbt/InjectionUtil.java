package mod.totloky.crs.nbt;

/**
 * Utility methods for automatic injection systems like {@link GameRegistry.ObjectHolder} and {@link CapabilityInject}.
 *
 * @author Choonster
 */
public class InjectionUtil {

    @SuppressWarnings({"ConstantConditions", "SameReturnValue"})
    public static <T> T Null() {
        return null;
    }
}

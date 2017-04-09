package io.github.Tantol.CustomMonsters;
import net.minecraft.server.v1_11_R1.Entity;
import net.minecraft.server.v1_11_R1.EntityTypes;
import net.minecraft.server.v1_11_R1.MinecraftKey;
import net.minecraft.server.v1_11_R1.RegistryMaterials;

public class EntityRegistry {

    public static void overrideEntity(Class<? extends Entity> customEntityClass) {
        Class<? extends Entity> search = customEntityClass;

        do {
            MinecraftKey key = getKey(search);

            if(key != null) {
                int entityId = getEntityId(search);

                registerEntity(entityId, key, customEntityClass);
                return;
            }

            if(!Entity.class.isAssignableFrom(search.getSuperclass())) {
                break;
            }

            search = (Class<? extends Entity>) search.getSuperclass();
        } while(true);
       
        throw new IllegalArgumentException("Unable to find entity superclass to override for class " + customEntityClass);
    }

    private static RegistryMaterials<MinecraftKey, Class<? extends Entity>> getRegistry() {
        // Identified as the field that is of type RegistryMaterials
        return EntityTypes.b;
    }

    private static MinecraftKey getKey(Class<? extends Entity> entityClass) {
        // Identified as the field that takes Class<? extends Entity> and returns a MinecraftKey
        return getRegistry().b(entityClass);
    }

    private static int getEntityId(Class<? extends Entity> entityClass) {
        // Identified as the field that takes Class<? extends Entity> and returns an int
        return getRegistry().a(entityClass);
    }

    private static void registerEntity(int entityId, MinecraftKey key, Class<? extends Entity> entityClass) {
        // Identified as the field that takes an int, MinecraftKey and Class<? extends Entity> and returns nothing
        getRegistry().a(entityId, key, entityClass);
    }

}
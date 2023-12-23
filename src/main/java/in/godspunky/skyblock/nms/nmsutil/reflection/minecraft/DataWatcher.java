package in.godspunky.skyblock.nms.nmsutil.reflection.minecraft;

import in.godspunky.skyblock.nms.nmsutil.reflection.resolver.*;
import in.godspunky.skyblock.nms.nmsutil.reflection.resolver.minecraft.NMSClassResolver;
import in.godspunky.skyblock.nms.nmsutil.reflection.resolver.*;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Map;

public class DataWatcher {
    static ClassResolver classResolver;
    static NMSClassResolver nmsClassResolver;
    static Class<?> ItemStack;
    static Class<?> ChunkCoordinates;
    static Class<?> BlockPosition;
    static Class<?> Vector3f;
    static Class<?> DataWatcher;
    static Class<?> Entity;
    static Class<?> TIntObjectMap;
    static ConstructorResolver DataWacherConstructorResolver;
    static FieldResolver DataWatcherFieldResolver;
    static MethodResolver TIntObjectMapMethodResolver;
    static MethodResolver DataWatcherMethodResolver;

    public static Object newDataWatcher(final Object entity) throws ReflectiveOperationException {
        return in.godspunky.skyblock.nms.nmsutil.reflection.minecraft.DataWatcher.DataWacherConstructorResolver.resolve(new Class[][]{{in.godspunky.skyblock.nms.nmsutil.reflection.minecraft.DataWatcher.Entity}}).newInstance(entity);
    }

    public static Object setValue(final Object dataWatcher, final int index, final Object dataWatcherObject, final Object value) throws ReflectiveOperationException {
        if (Minecraft.VERSION.olderThan(Minecraft.Version.v1_9_R1)) {
            return V1_8.setValue(dataWatcher, index, value);
        }
        return V1_9.setValue(dataWatcher, dataWatcherObject, value);
    }

    public static Object setValue(final Object dataWatcher, final int index, final V1_9.ValueType type, final Object value) throws ReflectiveOperationException {
        return setValue(dataWatcher, index, type.getType(), value);
    }

    public static Object setValue(final Object dataWatcher, final int index, final Object value, final FieldResolver dataWatcherObjectFieldResolver, final String... dataWatcherObjectFieldNames) throws ReflectiveOperationException {
        if (Minecraft.VERSION.olderThan(Minecraft.Version.v1_9_R1)) {
            return V1_8.setValue(dataWatcher, index, value);
        }
        final Object dataWatcherObject = dataWatcherObjectFieldResolver.resolve(dataWatcherObjectFieldNames).get(null);
        return V1_9.setValue(dataWatcher, dataWatcherObject, value);
    }

    @Deprecated
    public static Object getValue(final DataWatcher dataWatcher, final int index) throws ReflectiveOperationException {
        if (Minecraft.VERSION.olderThan(Minecraft.Version.v1_9_R1)) {
            return V1_8.getValue(dataWatcher, index);
        }
        return V1_9.getValue(dataWatcher, index);
    }

    public static Object getValue(final Object dataWatcher, final int index, final V1_9.ValueType type) throws ReflectiveOperationException {
        return getValue(dataWatcher, index, type.getType());
    }

    public static Object getValue(final Object dataWatcher, final int index, final Object dataWatcherObject) throws ReflectiveOperationException {
        if (Minecraft.VERSION.olderThan(Minecraft.Version.v1_9_R1)) {
            return V1_8.getWatchableObjectValue(V1_8.getValue(dataWatcher, index));
        }
        return V1_9.getValue(dataWatcher, dataWatcherObject);
    }

    public static int getValueType(final Object value) {
        int type = 0;
        if (value instanceof Number) {
            if (value instanceof Byte) {
                type = 0;
            } else if (value instanceof Short) {
                type = 1;
            } else if (value instanceof Integer) {
                type = 2;
            } else if (value instanceof Float) {
                type = 3;
            }
        } else if (value instanceof String) {
            type = 4;
        } else if (value != null && value.getClass().equals(in.godspunky.skyblock.nms.nmsutil.reflection.minecraft.DataWatcher.ItemStack)) {
            type = 5;
        } else if (value != null && (value.getClass().equals(in.godspunky.skyblock.nms.nmsutil.reflection.minecraft.DataWatcher.ChunkCoordinates) || value.getClass().equals(in.godspunky.skyblock.nms.nmsutil.reflection.minecraft.DataWatcher.BlockPosition))) {
            type = 6;
        } else if (value != null && value.getClass().equals(in.godspunky.skyblock.nms.nmsutil.reflection.minecraft.DataWatcher.Vector3f)) {
            type = 7;
        }
        return type;
    }

    private DataWatcher() {
    }

    static {
        in.godspunky.skyblock.nms.nmsutil.reflection.minecraft.DataWatcher.classResolver = new ClassResolver();
        in.godspunky.skyblock.nms.nmsutil.reflection.minecraft.DataWatcher.nmsClassResolver = new NMSClassResolver();
        in.godspunky.skyblock.nms.nmsutil.reflection.minecraft.DataWatcher.ItemStack = in.godspunky.skyblock.nms.nmsutil.reflection.minecraft.DataWatcher.nmsClassResolver.resolveSilent("ItemStack");
        in.godspunky.skyblock.nms.nmsutil.reflection.minecraft.DataWatcher.ChunkCoordinates = in.godspunky.skyblock.nms.nmsutil.reflection.minecraft.DataWatcher.nmsClassResolver.resolveSilent("ChunkCoordinates");
        in.godspunky.skyblock.nms.nmsutil.reflection.minecraft.DataWatcher.BlockPosition = in.godspunky.skyblock.nms.nmsutil.reflection.minecraft.DataWatcher.nmsClassResolver.resolveSilent("BlockPosition");
        in.godspunky.skyblock.nms.nmsutil.reflection.minecraft.DataWatcher.Vector3f = in.godspunky.skyblock.nms.nmsutil.reflection.minecraft.DataWatcher.nmsClassResolver.resolveSilent("Vector3f");
        in.godspunky.skyblock.nms.nmsutil.reflection.minecraft.DataWatcher.DataWatcher = in.godspunky.skyblock.nms.nmsutil.reflection.minecraft.DataWatcher.nmsClassResolver.resolveSilent("DataWatcher");
        in.godspunky.skyblock.nms.nmsutil.reflection.minecraft.DataWatcher.Entity = in.godspunky.skyblock.nms.nmsutil.reflection.minecraft.DataWatcher.nmsClassResolver.resolveSilent("Entity");
        in.godspunky.skyblock.nms.nmsutil.reflection.minecraft.DataWatcher.TIntObjectMap = in.godspunky.skyblock.nms.nmsutil.reflection.minecraft.DataWatcher.classResolver.resolveSilent("gnu.trove.map.TIntObjectMap", "net.minecraft.util.gnu.trove.map.TIntObjectMap");
        in.godspunky.skyblock.nms.nmsutil.reflection.minecraft.DataWatcher.DataWacherConstructorResolver = new ConstructorResolver(in.godspunky.skyblock.nms.nmsutil.reflection.minecraft.DataWatcher.DataWatcher);
        in.godspunky.skyblock.nms.nmsutil.reflection.minecraft.DataWatcher.DataWatcherFieldResolver = new FieldResolver(in.godspunky.skyblock.nms.nmsutil.reflection.minecraft.DataWatcher.DataWatcher);
        in.godspunky.skyblock.nms.nmsutil.reflection.minecraft.DataWatcher.TIntObjectMapMethodResolver = new MethodResolver(in.godspunky.skyblock.nms.nmsutil.reflection.minecraft.DataWatcher.TIntObjectMap);
        in.godspunky.skyblock.nms.nmsutil.reflection.minecraft.DataWatcher.DataWatcherMethodResolver = new MethodResolver(in.godspunky.skyblock.nms.nmsutil.reflection.minecraft.DataWatcher.DataWatcher);
    }

    public static class V1_9 {
        static Class<?> DataWatcherItem;
        static Class<?> DataWatcherObject;
        static ConstructorResolver DataWatcherItemConstructorResolver;
        static FieldResolver DataWatcherItemFieldResolver;
        static FieldResolver DataWatcherObjectFieldResolver;

        public static Object newDataWatcherItem(final Object dataWatcherObject, final Object value) throws ReflectiveOperationException {
            if (V1_9.DataWatcherItemConstructorResolver == null) {
                V1_9.DataWatcherItemConstructorResolver = new ConstructorResolver(V1_9.DataWatcherItem);
            }
            return V1_9.DataWatcherItemConstructorResolver.resolveFirstConstructor().newInstance(dataWatcherObject, value);
        }

        public static Object setItem(final Object dataWatcher, final int index, final Object dataWatcherObject, final Object value) throws ReflectiveOperationException {
            return setItem(dataWatcher, index, newDataWatcherItem(dataWatcherObject, value));
        }

        public static Object setItem(final Object dataWatcher, final int index, final Object dataWatcherItem) throws ReflectiveOperationException {
            final Map<Integer, Object> map = (Map<Integer, Object>) in.godspunky.skyblock.nms.nmsutil.reflection.minecraft.DataWatcher.DataWatcherFieldResolver.resolveByLastTypeSilent(Map.class).get(dataWatcher);
            map.put(index, dataWatcherItem);
            return dataWatcher;
        }

        public static Object setValue(final Object dataWatcher, final Object dataWatcherObject, final Object value) throws ReflectiveOperationException {
            in.godspunky.skyblock.nms.nmsutil.reflection.minecraft.DataWatcher.DataWatcherMethodResolver.resolve("set").invoke(dataWatcher, dataWatcherObject, value);
            return dataWatcher;
        }

        public static Object getItem(final Object dataWatcher, final Object dataWatcherObject) throws ReflectiveOperationException {
            return in.godspunky.skyblock.nms.nmsutil.reflection.minecraft.DataWatcher.DataWatcherMethodResolver.resolve(new ResolverQuery("c", new Class[]{V1_9.DataWatcherObject})).invoke(dataWatcher, dataWatcherObject);
        }

        public static Object getValue(final Object dataWatcher, final Object dataWatcherObject) throws ReflectiveOperationException {
            return in.godspunky.skyblock.nms.nmsutil.reflection.minecraft.DataWatcher.DataWatcherMethodResolver.resolve("get").invoke(dataWatcher, dataWatcherObject);
        }

        public static Object getValue(final Object dataWatcher, final ValueType type) throws ReflectiveOperationException {
            return getValue(dataWatcher, type.getType());
        }

        public static Object getItemObject(final Object item) throws ReflectiveOperationException {
            if (V1_9.DataWatcherItemFieldResolver == null) {
                V1_9.DataWatcherItemFieldResolver = new FieldResolver(V1_9.DataWatcherItem);
            }
            return V1_9.DataWatcherItemFieldResolver.resolve("a").get(item);
        }

        public static int getItemIndex(final Object dataWatcher, final Object item) throws ReflectiveOperationException {
            int index = -1;
            final Map<Integer, Object> map = (Map<Integer, Object>) in.godspunky.skyblock.nms.nmsutil.reflection.minecraft.DataWatcher.DataWatcherFieldResolver.resolveByLastTypeSilent(Map.class).get(dataWatcher);
            for (final Map.Entry<Integer, Object> entry : map.entrySet()) {
                if (entry.getValue().equals(item)) {
                    index = entry.getKey();
                    break;
                }
            }
            return index;
        }

        public static Type getItemType(final Object item) throws ReflectiveOperationException {
            if (V1_9.DataWatcherObjectFieldResolver == null) {
                V1_9.DataWatcherObjectFieldResolver = new FieldResolver(V1_9.DataWatcherObject);
            }
            final Object object = getItemObject(item);
            final Object serializer = V1_9.DataWatcherObjectFieldResolver.resolve("b").get(object);
            final Type[] genericInterfaces = serializer.getClass().getGenericInterfaces();
            if (genericInterfaces.length > 0) {
                final Type type = genericInterfaces[0];
                if (type instanceof ParameterizedType) {
                    final Type[] actualTypes = ((ParameterizedType) type).getActualTypeArguments();
                    if (actualTypes.length > 0) {
                        return actualTypes[0];
                    }
                }
            }
            return null;
        }

        public static Object getItemValue(final Object item) throws ReflectiveOperationException {
            if (V1_9.DataWatcherItemFieldResolver == null) {
                V1_9.DataWatcherItemFieldResolver = new FieldResolver(V1_9.DataWatcherItem);
            }
            return V1_9.DataWatcherItemFieldResolver.resolve("b").get(item);
        }

        public static void setItemValue(final Object item, final Object value) throws ReflectiveOperationException {
            V1_9.DataWatcherItemFieldResolver.resolve("b").set(item, value);
        }

        static {
            V1_9.DataWatcherItem = in.godspunky.skyblock.nms.nmsutil.reflection.minecraft.DataWatcher.nmsClassResolver.resolveSilent("DataWatcher$Item");
            V1_9.DataWatcherObject = in.godspunky.skyblock.nms.nmsutil.reflection.minecraft.DataWatcher.nmsClassResolver.resolveSilent("DataWatcherObject");
        }

        public enum ValueType {
            ENTITY_FLAG("Entity", 57, 0),
            ENTITY_AIR_TICKS("Entity", 58, 1),
            ENTITY_NAME("Entity", 59, 2),
            ENTITY_NAME_VISIBLE("Entity", 60, 3),
            ENTITY_SILENT("Entity", 61, 4),
            ENTITY_as("EntityLiving", 2, 0),
            ENTITY_LIVING_HEALTH("EntityLiving", new String[]{"HEALTH"}),
            ENTITY_LIVING_f("EntityLiving", 4, 2),
            ENTITY_LIVING_g("EntityLiving", 5, 3),
            ENTITY_LIVING_h("EntityLiving", 6, 4),
            ENTITY_INSENTIENT_FLAG("EntityInsentient", 0, 0),
            ENTITY_SLIME_SIZE("EntitySlime", 0, 0),
            ENTITY_WITHER_a("EntityWither", 0, 0),
            ENTITY_WIHER_b("EntityWither", 1, 1),
            ENTITY_WITHER_c("EntityWither", 2, 2),
            ENTITY_WITHER_bv("EntityWither", 3, 3),
            ENTITY_WITHER_bw("EntityWither", 4, 4),
            ENTITY_AGEABLE_CHILD("EntityAgeable", 0, 0),
            ENTITY_HORSE_STATUS("EntityHorse", 3, 0),
            ENTITY_HORSE_HORSE_TYPE("EntityHorse", 4, 1),
            ENTITY_HORSE_HORSE_VARIANT("EntityHorse", 5, 2),
            ENTITY_HORSE_OWNER_UUID("EntityHorse", 6, 3),
            ENTITY_HORSE_HORSE_ARMOR("EntityHorse", 7, 4),
            ENTITY_HUMAN_ABSORPTION_HEARTS("EntityHuman", 0, 0),
            ENTITY_HUMAN_SCORE("EntityHuman", 1, 1),
            ENTITY_HUMAN_SKIN_LAYERS("EntityHuman", 2, 2),
            ENTITY_HUMAN_MAIN_HAND("EntityHuman", 3, 3);

            private Object type;

            ValueType(final String className, final String[] fieldNames) {
                try {
                    this.type = new FieldResolver(in.godspunky.skyblock.nms.nmsutil.reflection.minecraft.DataWatcher.nmsClassResolver.resolve(className)).resolve(fieldNames).get(null);
                } catch (final Exception e) {
                    if (Minecraft.VERSION.newerThan(Minecraft.Version.v1_9_R1)) {
                        System.err.println("[SkySim Reflection Injector] Failed to find DataWatcherObject for " + className + " " + Arrays.toString(fieldNames));
                    }
                }
            }

            ValueType(final String className, final int index) {
                try {
                    this.type = new FieldResolver(in.godspunky.skyblock.nms.nmsutil.reflection.minecraft.DataWatcher.nmsClassResolver.resolve(className)).resolveIndex(index).get(null);
                } catch (final Exception e) {
                    if (Minecraft.VERSION.newerThan(Minecraft.Version.v1_9_R1)) {
                        System.err.println("[SkySim Reflection Injector] Failed to find DataWatcherObject for " + className + " #" + index);
                    }
                }
            }

            ValueType(final String className, final int index, final int offset) {
                int firstObject = 0;
                try {
                    final Class<?> clazz = in.godspunky.skyblock.nms.nmsutil.reflection.minecraft.DataWatcher.nmsClassResolver.resolve(className);
                    for (final Field field : clazz.getDeclaredFields()) {
                        if ("DataWatcherObject".equals(field.getType().getSimpleName())) {
                            break;
                        }
                        ++firstObject;
                    }
                    this.type = new FieldResolver(clazz).resolveIndex(firstObject + offset).get(null);
                } catch (final Exception e) {
                    if (Minecraft.VERSION.newerThan(Minecraft.Version.v1_9_R1)) {
                        System.err.println("[SkySim Reflection Injector] Failed to find DataWatcherObject for " + className + " #" + index + " (" + firstObject + "+" + offset + ")");
                    }
                }
            }

            public boolean hasType() {
                return this.getType() != null;
            }

            public Object getType() {
                return this.type;
            }
        }
    }

    public static class V1_8 {
        static Class<?> WatchableObject;
        static ConstructorResolver WatchableObjectConstructorResolver;
        static FieldResolver WatchableObjectFieldResolver;

        public static Object newWatchableObject(final int index, final Object value) throws ReflectiveOperationException {
            return newWatchableObject(in.godspunky.skyblock.nms.nmsutil.reflection.minecraft.DataWatcher.getValueType(value), index, value);
        }

        public static Object newWatchableObject(final int type, final int index, final Object value) throws ReflectiveOperationException {
            if (V1_8.WatchableObjectConstructorResolver == null) {
                V1_8.WatchableObjectConstructorResolver = new ConstructorResolver(V1_8.WatchableObject);
            }
            return V1_8.WatchableObjectConstructorResolver.resolve(new Class[][]{{Integer.TYPE, Integer.TYPE, Object.class}}).newInstance(type, index, value);
        }

        public static Object setValue(final Object dataWatcher, final int index, final Object value) throws ReflectiveOperationException {
            final int type = in.godspunky.skyblock.nms.nmsutil.reflection.minecraft.DataWatcher.getValueType(value);
            final Map map = (Map) in.godspunky.skyblock.nms.nmsutil.reflection.minecraft.DataWatcher.DataWatcherFieldResolver.resolveByLastType(Map.class).get(dataWatcher);
            map.put(index, newWatchableObject(type, index, value));
            return dataWatcher;
        }

        public static Object getValue(final Object dataWatcher, final int index) throws ReflectiveOperationException {
            final Map map = (Map) in.godspunky.skyblock.nms.nmsutil.reflection.minecraft.DataWatcher.DataWatcherFieldResolver.resolveByLastType(Map.class).get(dataWatcher);
            return map.get(index);
        }

        public static int getWatchableObjectIndex(final Object object) throws ReflectiveOperationException {
            if (V1_8.WatchableObjectFieldResolver == null) {
                V1_8.WatchableObjectFieldResolver = new FieldResolver(V1_8.WatchableObject);
            }
            return V1_8.WatchableObjectFieldResolver.resolve("b").getInt(object);
        }

        public static int getWatchableObjectType(final Object object) throws ReflectiveOperationException {
            if (V1_8.WatchableObjectFieldResolver == null) {
                V1_8.WatchableObjectFieldResolver = new FieldResolver(V1_8.WatchableObject);
            }
            return V1_8.WatchableObjectFieldResolver.resolve("a").getInt(object);
        }

        public static Object getWatchableObjectValue(final Object object) throws ReflectiveOperationException {
            if (V1_8.WatchableObjectFieldResolver == null) {
                V1_8.WatchableObjectFieldResolver = new FieldResolver(V1_8.WatchableObject);
            }
            return V1_8.WatchableObjectFieldResolver.resolve("c").get(object);
        }

        static {
            V1_8.WatchableObject = in.godspunky.skyblock.nms.nmsutil.reflection.minecraft.DataWatcher.nmsClassResolver.resolveSilent("WatchableObject", "DataWatcher$WatchableObject");
        }
    }
}
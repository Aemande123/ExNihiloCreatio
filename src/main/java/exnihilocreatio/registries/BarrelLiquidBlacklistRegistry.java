package exnihilocreatio.registries;

import com.google.common.collect.Maps;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exnihilocreatio.ModBlocks;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class BarrelLiquidBlacklistRegistry {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final Map<Integer, List<String>> blacklist = new HashMap<>();
    private static final Map<Integer, List<String>> externalBlacklist = new HashMap<>();

    @SuppressWarnings("unchecked")
    public static boolean isBlacklisted(int level, String fluid) {
        return level < 0 || blacklist.getOrDefault(level, Collections.EMPTY_LIST).contains(fluid);
    }

    public static void register(int level, String fluid) {
        registerInternal(level, fluid);

        List<String> list = externalBlacklist.computeIfAbsent(level, k -> new ArrayList<>());

        list.add(fluid);
    }

    private static void registerInternal(int level, String fluid) {
        List<String> list = blacklist.computeIfAbsent(level, k -> new ArrayList<>());

        list.add(fluid);
    }

    public static void registerDefaults() {
        registerInternal(ModBlocks.barrelWood.getTier(), "lava");
        registerInternal(ModBlocks.barrelWood.getTier(), "fire_water");
        registerInternal(ModBlocks.barrelWood.getTier(), "rocket_fuel");
    }

    public static void loadJson(File file) {
        blacklist.clear();

        if (file.exists()) {
            try {
                String json = new String(Files.readAllBytes(file.toPath()));

                Map<Integer, List<String>> loaded = gson.fromJson(json, new TypeToken<Map<Integer, List<String>>>() {
                }.getType());

                blacklist.putAll(Maps.transformValues(loaded, list -> new ArrayList<>(list)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            registerDefaults();
            saveJson(file);
        }

        for (Map.Entry<Integer, List<String>> entry : externalBlacklist.entrySet()) {
            if (!blacklist.containsKey(entry.getKey())) {
                blacklist.put(entry.getKey(), new ArrayList<>());
            }

            blacklist.get(entry.getKey()).addAll(entry.getValue());
        }
    }

    public static void saveJson(File file) {
        try {
            String json = gson.toJson(blacklist, new TypeToken<Map<Integer, List<String>>>() {
            }.getType());

            Files.write(file.toPath(), json.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

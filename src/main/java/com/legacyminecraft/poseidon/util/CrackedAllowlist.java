package com.legacyminecraft.poseidon.util;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class CrackedAllowlist {

    private static CrackedAllowlist singleton;
    private final Gson parser = new GsonBuilder().setPrettyPrinting().create();
    private JsonArray namesJsonArray;
    private File crackedListFile = new File("cracked-allowlist.json");

    public CrackedAllowlist() {

        if (!crackedListFile.exists()) {
            namesJsonArray = new JsonArray();
            saveAllowlist();
            return;
        }

        try {
            System.out.println("[Poseidon] Reading cracked-allowlist.json for Project Poseidon");

            JsonReader reader = new JsonReader(new FileReader(crackedListFile));
            namesJsonArray = parser.fromJson(reader, JsonArray.class);

        } catch (JsonSyntaxException e) {
            System.out.println("[Poseidon] Cracked names allowlist is corrupt or unreadable, resetting");
            namesJsonArray = new JsonArray();
            saveAllowlist();

        } catch (Throwable t) {
            System.out.println("[Poseidon] Error reading cracked-allowlist.json: " + t + ": " + t.getMessage());
            namesJsonArray = new JsonArray();
        }
    }

    public void saveAllowlist() {
        try {
            System.out.println("[Poseidon] Saving cracked names allowlist");

            Files.write(crackedListFile.toPath(), parser.toJson(namesJsonArray).getBytes("UTF-8"));
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /**
     * Adds username to cracked names allowlist.
     *
     * @param name username to allowlist
     * @return true if added, false if already on the list
     */
    public boolean addName(String name) {
        JsonPrimitive jsonString = new JsonPrimitive(name.toLowerCase());
        if (namesJsonArray.contains(jsonString)) {
            return false;
        }

        namesJsonArray.add(jsonString);
        saveAllowlist();
        return true;
    }

    /**
     * Removes username from cracked names allowlist.
     *
     * @param name username to remove
     * @return true if removed, false if the name wasn't on the list
     */
    public boolean removeName(String name) {
        JsonPrimitive jsonString = new JsonPrimitive(name.toLowerCase());

        boolean removed = namesJsonArray.remove(jsonString);
        saveAllowlist();

        return removed;
    }

    public boolean contains(String name) {
        JsonPrimitive jsonString = new JsonPrimitive(name.toLowerCase());

        return namesJsonArray.contains(jsonString);
    }

    public List<String> getAsList() {
        Type listType = new TypeToken<List<String>>() {}.getType();
        return parser.fromJson(namesJsonArray, listType);
    }

    public static CrackedAllowlist get() {
        if (singleton == null) {
            singleton = new CrackedAllowlist();
        }
        return singleton;
    }
}

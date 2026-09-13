package me.elythern.ebooster;

import java.util.UUID;

public class DataManager {


    public static double get(UUID uuid){

        return EBooster.get()
                .getConfig()
                .getDouble(
                "players."+uuid+".balance");
    }


    public static void set(UUID uuid,double amount){

        EBooster.get()
        .getConfig()
        .set(
        "players."+uuid+".balance",
        amount);

        EBooster.get()
        .saveConfig();
    }
}

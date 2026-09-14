package me.elythern.ebooster;

import java.util.UUID;

public class DataManager {


    // Booster balansı (toplanan pul)
    public static double getBalance(UUID uuid){

        return EBooster.get()
                .getConfig()
                .getDouble(
                "players."+uuid+".balance",
                0);

    }



    public static void setBalance(UUID uuid, double amount){


        EBooster.get()
                .getConfig()
                .set(
                "players."+uuid+".balance",
                amount);


        EBooster.get().saveConfig();

    }




    // İlkin qoyulan booster məbləği
    public static double getAmount(UUID uuid){


        return EBooster.get()
                .getConfig()
                .getDouble(
                "players."+uuid+".amount",
                0);

    }




    public static void setAmount(UUID uuid, double amount){


        EBooster.get()
                .getConfig()
                .set(
                "players."+uuid+".amount",
                amount);


        EBooster.get().saveConfig();

    }



    // Hər saniyə verilən gəlir
    public static double getPerSecond(UUID uuid){


        return getAmount(uuid) * 0.03;

    }



    public static String format(double amount){


        if(amount >= 1000000000){

            return String.format("%.1fB",
                    amount / 1000000000);

        }


        if(amount >= 1000000){

            return String.format("%.1fM",
                    amount / 1000000);

        }


        if(amount >= 1000){

            return String.format("%.1fK",
                    amount / 1000);

        }


        return String.format("%.0f", amount);

    }


}

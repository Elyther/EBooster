package me.elythern.ebooster;

import java.util.UUID;

public class DataManager {


    // Kazanılan booster bakiyesi
    public static double getBalance(UUID uuid){

        return EBooster.get()
                .getConfig()
                .getDouble(
                "players." + uuid + ".balance",
                0);

    }



    public static void setBalance(UUID uuid, double amount){


        EBooster.get()
                .getConfig()
                .set(
                "players." + uuid + ".balance",
                amount);


        EBooster.get().saveConfig();

    }





    // İlk yatırılan booster miktarı

    public static double getAmount(UUID uuid){


        return EBooster.get()
                .getConfig()
                .getDouble(
                "players." + uuid + ".amount",
                0);

    }





    public static void setAmount(UUID uuid, double amount){


        EBooster.get()
                .getConfig()
                .set(
                "players." + uuid + ".amount",
                amount);


        EBooster.get().saveConfig();

    }





    // Saniyelik kazanç (%3)

    public static double getPerSecond(UUID uuid){


        return getAmount(uuid) * 0.03;

    }





    // Kısa sayı formatı

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






    // Eski kodlarla uyumluluk için

    public static double get(UUID uuid){

        return getBalance(uuid);

    }




    public static void set(UUID uuid, double amount){

        setBalance(uuid, amount);

    }


}

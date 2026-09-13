package me.elythern.ebooster;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;


public class BoosterPlaceholder extends PlaceholderExpansion {


    private final EBooster plugin;


    public BoosterPlaceholder(EBooster plugin){

        this.plugin = plugin;

    }



    @Override
    public String getIdentifier(){

        return "ebooster";

    }



    @Override
    public String getAuthor(){

        return "Elyther";

    }



    @Override
    public String getVersion(){

        return "1.0";

    }



    @Override
    public String onPlaceholderRequest(Player p, String id){


        double balance =
                DataManager.get(
                p.getUniqueId());



        if(id.equals("balance")){

            return format(balance) + "$";

        }



        if(id.equals("booster")){


            double rate =
                    balance *
                    plugin.getConfig()
                    .getDouble(
                    "booster.rate");



            return "⚡ "
                    + format(rate)
                    + "$/s";

        }



        return null;

    }




    private String format(double number){


        if(number >= 1000000000){

            return String.format("%.1fB",
            number / 1000000000);

        }


        if(number >= 1000000){

            return String.format("%.1fM",
            number / 1000000);

        }


        if(number >= 1000){

            return String.format("%.1fK",
            number / 1000);

        }


        return String.format("%.0f", number);

    }

}

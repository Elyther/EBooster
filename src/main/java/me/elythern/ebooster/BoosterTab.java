package me.elythern.ebooster;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class BoosterTab implements TabCompleter {



    @Override
    public List<String> onTabComplete(
            CommandSender sender,
            Command command,
            String label,
            String[] args){



        List<String> liste = new ArrayList<>();



        // /booster a
        if(args.length == 1){


            liste.add("add");
            liste.add("ekle");


            return filtrele(args[0], liste);

        }





        // /booster add miktar

        if(args.length == 2 &&
                (args[0].equalsIgnoreCase("add")
                || args[0].equalsIgnoreCase("ekle"))){



            liste.add("1000");
            liste.add("10k");
            liste.add("100k");
            liste.add("1m");



            return filtrele(args[1], liste);

        }



        return new ArrayList<>();

    }





    private List<String> filtrele(
            String girilen,
            List<String> liste){



        List<String> sonuc =
                new ArrayList<>();



        for(String kelime : liste){


            if(kelime.toLowerCase()
                    .startsWith(
                    girilen.toLowerCase())){


                sonuc.add(kelime);

            }

        }



        return sonuc;

    }


}

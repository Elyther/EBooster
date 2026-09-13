package me.elythern.ebooster;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;


public class BoosterTab implements TabCompleter {


    @Override
    public List<String> onTabComplete(
            CommandSender sender,
            Command command,
            String label,
            String[] args) {


        List<String> list = new ArrayList<>();


        // /booster e...
        if(args.length == 1){


            list.add("ekle");


        }



        // /booster ekle ...
        if(args.length == 2
                && args[0].equalsIgnoreCase("ekle")){


            list.add("10k");
            list.add("50k");
            list.add("100k");
            list.add("1m");


        }


        return list;
    }

}

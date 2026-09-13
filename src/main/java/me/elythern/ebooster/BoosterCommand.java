package me.elythern.ebooster;

import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class BoosterCommand implements CommandExecutor {

    private EBooster plugin;

    public BoosterCommand(EBooster plugin){
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender,
                             Command cmd,
                             String label,
                             String[] args){

        if(!(sender instanceof Player)){
            return true;
        }

        Player p=(Player)sender;


        if(args.length == 0){

            new BoosterGUI(plugin).open(p);
            return true;

        }


        if(args[0].equalsIgnoreCase("ekle")){


            if(args.length < 2){

                p.sendMessage("§cKullanım: §e/booster ekle <miktar>");
                return true;

            }


            double amount;


            try{

                amount=Double.parseDouble(args[1]);

            }catch(Exception e){

                p.sendMessage("§cGeçersiz miktar!");
                return true;

            }


            if(amount <= 0){

                p.sendMessage("§cMiktar 0'dan büyük olmalı!");
                return true;

            }


            if(VaultHook.take(p,amount)){


                double old =
                DataManager.get(p.getUniqueId());


                DataManager.set(
                p.getUniqueId(),
                old + amount);



                p.sendMessage(
                "§aBooster hesabına §e"
                +amount+
                "$ §ayatırıldı!");



            }else{


                p.sendMessage(
                "§cYeterli paran yok!");

            }


            return true;

        }



        p.sendMessage(
        "§cKullanım: /booster veya /booster ekle <miktar>");

        return true;
    }
}

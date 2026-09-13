package me.elythern.ebooster;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class BoosterCommand implements CommandExecutor {


    private final EBooster plugin;


    public BoosterCommand(EBooster plugin){
        this.plugin = plugin;
    }



    @Override
    public boolean onCommand(CommandSender sender,
                             Command command,
                             String label,
                             String[] args){


        if(!(sender instanceof Player)){
            sender.sendMessage("Bu komutu sadece oyuncular kullanabilir.");
            return true;
        }


        Player p = (Player)sender;



        // /booster
        if(args.length == 0){

            new BoosterGUI(plugin).open(p);

            return true;
        }



        // /booster ekle <miktar>
        if(args[0].equalsIgnoreCase("ekle")
        || args[0].equalsIgnoreCase("add")){


            if(args.length < 2){

                p.sendMessage(
                "§cKullanım: §e/booster ekle <miktar>");

                return true;
            }



            double amount;


            try{


                amount = parseAmount(args[1]);


            }catch(Exception e){


                p.sendMessage(
                "§cGeçersiz miktar! Örnek: §e10k, 1m, 5000");


                return true;
            }



            if(amount <= 0){


                p.sendMessage(
                "§cMiktar 0'dan büyük olmalı!");


                return true;
            }




            // Oyuncunun parasını kontrol et

            if(!VaultHook.take(p, amount)){


                p.sendMessage(
                "§cYeterli paran yok!");


                return true;

            }




            double old =
            DataManager.get(
            p.getUniqueId());



            DataManager.set(
            p.getUniqueId(),
            old + amount);



            p.sendMessage(
            "§aBooster bakiyene §e"
            +format(amount)
            +"$ §akoyuldu!");



            return true;

        }




        p.sendMessage(
        "§cKullanım:\n"
        +"§e/booster\n"
        +"§e/booster ekle <miktar>");


        return true;

    }





    // 10k, 1m, 1b sistemi

    private double parseAmount(String text){


        text = text.toLowerCase()
                .replace(",","")
                .replace("$","");



        double multiplier = 1;



        if(text.endsWith("k")){


            multiplier = 1000;
            text = text.substring(0,text.length()-1);


        }else if(text.endsWith("m")){


            multiplier = 1000000;
            text = text.substring(0,text.length()-1);


        }else if(text.endsWith("b")){


            multiplier = 1000000000;
            text = text.substring(0,text.length()-1);

        }



        return Double.parseDouble(text) * multiplier;

    }





    private String format(double amount){


        if(amount >= 1000000000){

            return (amount / 1000000000)+"B";

        }


        if(amount >= 1000000){

            return (amount / 1000000)+"M";

        }


        if(amount >= 1000){

            return (amount / 1000)+"K";

        }


        return String.valueOf(amount);

    }

}

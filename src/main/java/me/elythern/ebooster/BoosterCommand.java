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

            sender.sendMessage(
                    "Bu komutu sadece oyuncular kullanabilir."
            );

            return true;
        }



        Player oyuncu = (Player)sender;



        // /booster
        if(args.length == 0){

            new BoosterGUI(plugin).open(oyuncu);

            return true;

        }





        // /booster add miktar

        if(args[0].equalsIgnoreCase("add")
        || args[0].equalsIgnoreCase("ekle")){


            if(args.length < 2){

                oyuncu.sendMessage(
                "§cKullanım: §e/booster add <miktar>");

                return true;

            }




            double miktar;


            try{

                miktar = parseAmount(args[1]);

            }
            catch(Exception e){

                oyuncu.sendMessage(
                "§cGeçersiz miktar! Örnek: §e10k, 1m, 5000");

                return true;

            }




            if(miktar <= 0){

                oyuncu.sendMessage(
                "§cMiktar 0'dan büyük olmalı!");

                return true;

            }





            // Para kontrolü

            if(!VaultHook.take(oyuncu,miktar)){


                oyuncu.sendMessage(
                "§cYeterli paran yok!");

                return true;

            }





            // Eski booster miktarı

            double eskiMiktar =
                    DataManager.getAmount(
                    oyuncu.getUniqueId());




            // Yeni miktar ekle

            DataManager.setAmount(
                    oyuncu.getUniqueId(),
                    eskiMiktar + miktar);





            double saniyelikKazanc =
                    (eskiMiktar + miktar) * 0.03;




            oyuncu.sendMessage(
            "§aBooster'a §e"
            + DataManager.format(miktar)
            +"$ §ayüklendi!");



            oyuncu.sendMessage(
            "§7Kazanç: §e"
            + DataManager.format(saniyelikKazanc)
            +"$/s");



            return true;


        }





        oyuncu.sendMessage(
        "§cKullanım:\n"
        +"§e/booster\n"
        +"§e/booster add <miktar>");



        return true;

    }







    // 10k - 1m - 1b sistemi

    private double parseAmount(String text){


        text = text.toLowerCase()
                .replace(",","")
                .replace("$","");



        double carpan = 1;




        if(text.endsWith("k")){


            carpan = 1000;

            text = text.substring(
                    0,
                    text.length()-1);

        }


        else if(text.endsWith("m")){


            carpan = 1000000;

            text = text.substring(
                    0,
                    text.length()-1);

        }


        else if(text.endsWith("b")){


            carpan = 1000000000;

            text = text.substring(
                    0,
                    text.length()-1);

        }




        return Double.parseDouble(text) * carpan;


    }


}

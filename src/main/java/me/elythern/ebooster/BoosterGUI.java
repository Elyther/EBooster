package me.elythern.ebooster;


import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;


public class BoosterGUI implements Listener {


    private final EBooster plugin;


    public BoosterGUI(EBooster plugin){
        this.plugin = plugin;
    }



    public void open(Player p){


        Inventory inv = Bukkit.createInventory(
                null,
                27,
                "§6EBooster Menü"
        );



        // Booster Bakiyesi

        ItemStack info =
                new ItemStack(Material.GOLD_BLOCK);


        ItemMeta im =
                info.getItemMeta();


        im.setDisplayName(
                "§eMevcut Booster Bakiyesi"
        );


        im.setLore(java.util.List.of(
                "§7Bakiyen: §a"
                + format(DataManager.get(
                p.getUniqueId()))
                +"$"
        ));



        info.setItemMeta(im);


        inv.setItem(11,info);





        // İptal

        ItemStack cancel =
                new ItemStack(Material.RED_WOOL);


        ItemMeta cm =
                cancel.getItemMeta();


        cm.setDisplayName(
                "§cBooster İptal Et"
        );


        cm.setLore(java.util.List.of(
                "§7Booster bakiyesinin",
                "§7%50'si geri verilir."
        ));


        cancel.setItemMeta(cm);


        inv.setItem(13,cancel);






        // Çek

        ItemStack take =
                new ItemStack(Material.EMERALD);


        ItemMeta tm =
                take.getItemMeta();


        tm.setDisplayName(
                "§aBakiyeyi Çek"
        );


        tm.setLore(java.util.List.of(
                "§7Tüm booster paran",
                "§7hesabına aktarılır."
        ));


        take.setItemMeta(tm);


        inv.setItem(15,take);




        p.openInventory(inv);

    }






    @EventHandler
    public void click(InventoryClickEvent e){


        if(!e.getView()
                .getTitle()
                .equals("§6EBooster Menü"))
            return;



        e.setCancelled(true);



        if(!(e.getWhoClicked() instanceof Player))
            return;



        Player p =
                (Player)e.getWhoClicked();




        double money =
                DataManager.get(
                p.getUniqueId());



        // Bakiyeyi çek

        if(e.getSlot()==15){



            if(money <= 0){

                p.sendMessage(
                "§cÇekilecek booster bakiyesi yok!");

                return;
            }



            DataManager.set(
            p.getUniqueId(),
            0);



            VaultHook.give(
            p,
            money);



            p.closeInventory();


            p.sendMessage(
            "§a"
            +format(money)
            +"$ hesabına aktarıld!");

        }





        // İptal

        if(e.getSlot()==13){



            if(money <= 0){

                p.sendMessage(
                "§cİptal edilecek booster yok!");

                return;
            }



            double refund =
                    money / 2;



            DataManager.set(
            p.getUniqueId(),
            0);



            VaultHook.give(
            p,
            refund);



            p.closeInventory();



            p.sendMessage(
            "§cBooster iptal edildi!");
            

            p.sendMessage(
            "§aGeri verilen miktar: §e"
            +format(refund)
            +"$");

        }

    }





    private String format(double amount){


        if(amount >= 1000000000)
            return (amount / 1000000000)+"B";


        if(amount >= 1000000)
            return (amount / 1000000)+"M";


        if(amount >= 1000)
            return (amount / 1000)+"K";


        return String.valueOf(amount);

    }

}

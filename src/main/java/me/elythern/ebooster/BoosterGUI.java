package me.elythern.ebooster;


import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;


public class BoosterGUI implements Listener{


private EBooster plugin;


public BoosterGUI(EBooster plugin){
this.plugin=plugin;
}



public void open(Player p){


Inventory inv =
Bukkit.createInventory(
null,27,
"EBooster");


ItemStack info =
new ItemStack(Material.GOLD_BLOCK);


ItemMeta im=info.getItemMeta();

im.setDisplayName(
"§6Hazırkı Booster Balansı");


im.setLore(java.util.List.of(
"§7Balans: §e"+
DataManager.get(p.getUniqueId())
+"$"
));


info.setItemMeta(im);


inv.setItem(11,info);



ItemStack cancel=
new ItemStack(Material.RED_WOOL);


ItemMeta cm=cancel.getItemMeta();

cm.setDisplayName(
"§cBooster Ləğv Et");

cancel.setItemMeta(cm);


inv.setItem(13,cancel);



ItemStack take=
new ItemStack(Material.EMERALD);


ItemMeta tm=take.getItemMeta();

tm.setDisplayName(
"§aBalansı götür");


take.setItemMeta(tm);


inv.setItem(15,take);



p.openInventory(inv);

}



@EventHandler
public void click(InventoryClickEvent e){

if(!e.getView()
.getTitle()
.equals("EBooster"))
return;


e.setCancelled(true);


Player p=(Player)e.getWhoClicked();


if(e.getSlot()==15){


double money=
DataManager.get(
p.getUniqueId());


p.closeInventory();


DataManager.set(
p.getUniqueId(),0);


VaultHook.give(
p,money);


}


if(e.getSlot()==13){


double money=
DataManager.get(
p.getUniqueId());


double refund=
money/2;


DataManager.set(
p.getUniqueId(),0);


VaultHook.give(
p,refund);


p.closeInventory();


}

}

}

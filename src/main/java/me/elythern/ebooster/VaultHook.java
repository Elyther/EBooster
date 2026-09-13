package me.elythern.ebooster;


import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;



public class VaultHook {



public static Economy eco;



public static void setup(){


RegisteredServiceProvider<Economy> rsp =
Bukkit.getServicesManager()
.getRegistration(Economy.class);



if(rsp != null){

eco=rsp.getProvider();

}


}





public static boolean take(Player p,double amount){


if(eco.getBalance(p)>=amount){


eco.withdrawPlayer(p,amount);


return true;


}


return false;

}





public static void give(Player p,double amount){


eco.depositPlayer(p,amount);


}



}

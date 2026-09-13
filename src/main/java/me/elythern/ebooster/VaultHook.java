package me.elythern.ebooster;


import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;


public class VaultHook {


static Economy eco;



public static boolean take(Player p,double amount){


if(eco.getBalance(p) >= amount){

    eco.withdrawPlayer(p, amount);

    return true;

}


return false;

}



public static void give(Player p,double amount){

eco.depositPlayer(p,amount);

}

}

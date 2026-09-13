package me.elythern.ebooster;


import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;


public class VaultHook {


static Economy eco;


public static void give(
Player p,double amount){

eco.depositPlayer(
p,amount);

}

}

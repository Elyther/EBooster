package me.elythern.ebooster;

import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class BoosterTask {


    public BoosterTask(EBooster plugin){


        new BukkitRunnable(){


            @Override
            public void run(){


                if(plugin.getConfig()
                        .getConfigurationSection("players") == null)
                    return;



                for(String key :
                        plugin.getConfig()
                        .getConfigurationSection("players")
                        .getKeys(false)){



                    UUID uuid =
                    UUID.fromString(key);



                    double amount =
                    DataManager.getAmount(uuid);



                    if(amount <= 0)
                        continue;



                    // İlkin məbləğin 3%-i
                    double reward =
                    amount * 0.03;



                    double oldBalance =
                    DataManager.getBalance(uuid);



                    double newBalance =
                    oldBalance + reward;



                    DataManager.setBalance(
                    uuid,
                    newBalance);


                }


            }


        }.runTaskTimer(
                plugin,
                20L,
                20L
        );

    }


}

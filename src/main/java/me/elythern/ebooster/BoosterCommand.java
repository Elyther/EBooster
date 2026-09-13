package me.elythern.ebooster;

import org.bukkit.command.*;
import org.bukkit.entity.Player;


public class BoosterCommand implements CommandExecutor {


private EBooster plugin;


public BoosterCommand(EBooster plugin){
this.plugin=plugin;
}


@Override
public boolean onCommand(
CommandSender sender,
Command cmd,
String label,
String[] args){


if(!(sender instanceof Player))
return true;


Player p=(Player)sender;


new BoosterGUI(plugin)
.open(p);


return true;
}

}

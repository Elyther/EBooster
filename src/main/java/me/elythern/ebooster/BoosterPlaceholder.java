package me.elythern.ebooster;


import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;


public class BoosterPlaceholder 
extends PlaceholderExpansion {


private EBooster plugin;


public BoosterPlaceholder(EBooster plugin){
this.plugin=plugin;
}


public String getIdentifier(){
return "ebooster";
}


public String getAuthor(){
return "Elythern";
}


public String getVersion(){
return "1.0";
}



public String onPlaceholderRequest(
Player p,String id){


if(id.equals("balance")){

return DataManager.get(
p.getUniqueId())+"$";

}


if(id.equals("booster")){


double balance=
DataManager.get(
p.getUniqueId());


double rate =
balance*0.0001;


return rate+"$/s";

}


return null;

}

}

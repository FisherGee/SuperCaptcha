package me.fishergee.supercaptcha.managers;

import me.fishergee.supercaptcha.utility.CaptchaInventory;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class CaptchaPlayerManager {

    HashMap<Player, CaptchaInventory> currentCaptchas = new HashMap<Player, CaptchaInventory>();

    public void addPlayerCaptcha(Player p, CaptchaInventory inv){
        currentCaptchas.put(p, inv);
    }

    public void removePlayerCaptcha(Player p){
        if(currentCaptchas.containsKey(p)){
            currentCaptchas.remove(p);
        }
    }

    public CaptchaInventory getCaptchaInventory(Player p){
        return currentCaptchas.get(p);
    }
}

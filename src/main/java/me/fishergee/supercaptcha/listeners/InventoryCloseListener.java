package me.fishergee.supercaptcha.listeners;

import me.fishergee.supercaptcha.SuperCaptcha;
import me.fishergee.supercaptcha.managers.CaptchaPlayerManager;
import me.fishergee.supercaptcha.utility.CaptchaInventory;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryCloseListener implements Listener {

    private CaptchaPlayerManager captchaPlayerManager = SuperCaptcha.plugin.getCaptchaPlayerManager();


    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e){
        if (!e.getInventory().getTitle().equals("Solve the captcha!")) {
            return;
        }

        Player closer = (Player) e.getPlayer();
        CaptchaInventory captcha = captchaPlayerManager.getCaptchaInventory(closer);

        if(captcha.allSlotsVerified() == false){
            closer.openInventory(captcha.getCaptchaInventory());
            closer.sendMessage(ChatColor.RED + "You are not verified yet!");
        }
    }
}

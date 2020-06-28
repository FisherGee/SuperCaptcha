package me.fishergee.supercaptcha.listeners;

import me.fishergee.supercaptcha.SuperCaptcha;
import me.fishergee.supercaptcha.utility.CaptchaInventory;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

public class PlayerJoinListener implements Listener {

    Plugin plugin = SuperCaptcha.plugin;

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e){
        Player p = e.getPlayer();
        CaptchaInventory captcha = new CaptchaInventory(p);

        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable(){

            @Override
            public void run() {
                p.openInventory(captcha.getCaptchaInventory());
            }
        },20);
    }
}

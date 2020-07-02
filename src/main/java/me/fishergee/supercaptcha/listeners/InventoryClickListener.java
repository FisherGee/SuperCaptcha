package me.fishergee.supercaptcha.listeners;

import me.fishergee.supercaptcha.SuperCaptcha;
import me.fishergee.supercaptcha.managers.CaptchaPlayerManager;
import me.fishergee.supercaptcha.utility.CaptchaInventory;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClickListener implements Listener {

    private CaptchaPlayerManager captchaPlayerManager = SuperCaptcha.plugin.getCaptchaPlayerManager();


    @EventHandler(ignoreCancelled = true)
    public void onInventoryClickEvent(InventoryClickEvent e) {
        if (!e.getInventory().getTitle().equals("Solve the captcha!")) {
            return;
        }

        e.setCancelled(true);

        ItemStack item = e.getCurrentItem();

        if (item == null) {
            return;
        }

        if(item.getItemMeta() == null){
            return;
        }

        if(item.getItemMeta().getDisplayName() == null){
            return;
        }

        Player player = (Player) e.getWhoClicked();
        CaptchaInventory captcha = captchaPlayerManager.getCaptchaInventory(player);

        if (item.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Click here to verify!")) {
            captcha.changeSlot(e.getClickedInventory(), e.getSlot());
            captcha.slotVerified();
            if(captcha.allSlotsVerified() == true){
                player.closeInventory();
                player.sendMessage(ChatColor.GREEN + "You have been verified!");
                captchaPlayerManager.removePlayerCaptcha(player);
                return;
            }
        }

    }
}

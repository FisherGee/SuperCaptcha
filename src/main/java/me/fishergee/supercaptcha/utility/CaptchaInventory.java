package me.fishergee.supercaptcha.utility;

import me.fishergee.supercaptcha.SuperCaptcha;
import me.fishergee.supercaptcha.managers.CaptchaPlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Random;

public class CaptchaInventory{

    private CaptchaPlayerManager captchaPlayerManager = SuperCaptcha.plugin.getCaptchaPlayerManager();
    private Inventory captchaInv;
    private Random verifyRandom = new Random();
    private ArrayList<Integer> verifyLocations = new ArrayList<>();
    private int clickedSlots;

    public CaptchaInventory(Player player){
        captchaInv = Bukkit.createInventory(player, 27, "Solve the captcha!");

        this.randomizeVerifyItems(5);
        this.setFillerItems(captchaInv);

        this.captchaPlayerManager.addPlayerCaptcha(player, this);
    }

    public void setFillerItems(Inventory inv){
        ItemStack filler_item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);

        for(int i = 0; i < inv.getSize(); i++){
            if(inv.getItem(i) == null){
                inv.setItem(i, filler_item);
            }
        }
    }

    public Inventory getCaptchaInventory(){
        return this.captchaInv;
    }

    public void randomizeVerifyItems(int amountOfVerify){
        ItemStack verify_item = new ItemStack(Material.STAINED_GLASS_PANE, 1,(short) 5);

        ItemMeta verify_item_meta = verify_item.getItemMeta();
        verify_item_meta.setDisplayName(ChatColor.GREEN + "Click here to verify!");
        verify_item.setItemMeta(verify_item_meta);

        for(int i = 0; i < amountOfVerify; i++){
            int slot = verifyRandom.nextInt(27);

            if(verifyLocations.contains(slot)){
                amountOfVerify++;
            }

            captchaInv.setItem(slot, verify_item);
            verifyLocations.add(slot);
        }
        clickedSlots = amountOfVerify;
    }

    public void slotVerified(){
        clickedSlots--;
    }

    public boolean allSlotsVerified(){
        if(clickedSlots == 0){
            return true;
        }
        return false;
    }

    public void changeSlot(Inventory inv, int slot){
        ItemStack filler_item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
        inv.setItem(slot, filler_item);
    }
}
package me.fishergee.supercaptcha.utility;

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

    private Inventory captchaInv;
    private Random verifyRandom = new Random();
    private ArrayList<Integer> verifyLocations = new ArrayList<>();

    public CaptchaInventory(Player player){
        captchaInv = Bukkit.createInventory(player, 27, "Solve the captcha!");

        this.randomizeVerifyItems(5);
        this.setFillerItems(captchaInv);
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

    public ArrayList<Integer> getVerifyLocations(){
        return this.verifyLocations;
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
    }
}
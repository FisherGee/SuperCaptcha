package me.fishergee.supercaptcha;

import me.fishergee.supercaptcha.listeners.InventoryClickListener;
import me.fishergee.supercaptcha.listeners.InventoryCloseListener;
import me.fishergee.supercaptcha.listeners.PlayerJoinListener;
import me.fishergee.supercaptcha.managers.CaptchaPlayerManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class SuperCaptcha extends JavaPlugin {

    public static SuperCaptcha plugin;
    final private CaptchaPlayerManager captchaInventoryPlayerManager = new CaptchaPlayerManager();

    @Override
    public void onEnable() {
        plugin = this;
        registerListeners();

    }

    @Override
    public void onDisable() {
        plugin = null;
    }

    public void registerListeners(){
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(),this);
        getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryCloseListener(), this);
    }

    public CaptchaPlayerManager getCaptchaPlayerManager(){
        return this.captchaInventoryPlayerManager;
    }
}

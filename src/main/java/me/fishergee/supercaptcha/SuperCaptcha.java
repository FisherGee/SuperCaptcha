package me.fishergee.supercaptcha;

import me.fishergee.supercaptcha.listeners.InventoryClickListener;
import me.fishergee.supercaptcha.listeners.PlayerJoinListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class SuperCaptcha extends JavaPlugin {

    public static SuperCaptcha plugin;

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
    }
}

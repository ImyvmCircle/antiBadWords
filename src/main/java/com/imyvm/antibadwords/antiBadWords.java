package com.imyvm.antibadwords;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class antiBadWords extends JavaPlugin implements Listener {

    private List<String> words;
    private String replacement;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        words = this.getConfig().getStringList("badwords");
        replacement = this.getConfig().getString("replacement");
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {

        for (String cens : words) {
            String message = event.getMessage();
            if (message.toLowerCase().contains(cens)) {
                //String smessage = event.getMessage().replaceAll("(?i)"+ cens, replacement);
                String smessage = replaceAll3(message, cens, replacement);
                event.setMessage(smessage);
            }
        }
    }


    private String replaceAll3(String input, String regex, String replacement) {
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(input);
        return m.replaceAll(replacement);
    }
}

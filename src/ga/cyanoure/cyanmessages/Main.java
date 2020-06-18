package ga.cyanoure.cyanmessages;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;

public class Main extends Plugin{
	public Configuration config;
	public PrivateMessages pm;
	
	public String prefix = "&8[&3CyanMessages&8] &3";
	
	public void pluginMessage(String msg) {
		//String prefix = this.config.getString("prefix");
		getLogger().info(ChatColor.translateAlternateColorCodes('&', msg));
		//getProxy().getConsole().sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
	}
	
	public void simpleMessage(String msg) {
		getProxy().getConsole().sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
	}
	
	@Override
    public void onEnable() {
		this.pm = new PrivateMessages(this);
        getProxy().getPluginManager().registerCommand(this, new PMCommand(this));
        getProxy().getPluginManager().registerCommand(this, new ReplyCommand(this));
        getProxy().getPluginManager().registerCommand(this, new HelpopCommand(this));
        getProxy().getPluginManager().registerCommand(this, new ToggleCommand(this));
        getProxy().getPluginManager().registerListener(this, new Events(this));
    }
}

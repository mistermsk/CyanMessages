package ga.cyanoure.cyanmessages;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class PrivateMessages {
	public Main plugin;
	public Map<UUID,UUID> replies = new HashMap<UUID,UUID>();
	public PrivateMessages(Main plugin) {
		this.plugin = plugin;
	}
	
	public void sendMessage(ProxiedPlayer from, ProxiedPlayer to, String message) {
		if(to != null) {
			if(from != to/* || true*/) {
				String SenderName = from.getName();
				String ReceiverName = to.getName();
				String msg1 = "&8[&cÉn &3»&6 "+ReceiverName+"&8] &7"+message;
				String msg2 = "&8[&6"+SenderName+" &3»&c Én&8] &7"+message;
				
				from.sendMessage(ChatColor.translateAlternateColorCodes('&', msg1));
				to.sendMessage(ChatColor.translateAlternateColorCodes('&', msg2));
				this.plugin.simpleMessage("&8[&7["+from.getServer().getInfo().getName()+"] &6"+SenderName+" &3» &7["+to.getServer().getInfo().getName()+"] &6"+ReceiverName+"&8] &7"+message);
				
				if(replies.containsKey(from.getUniqueId())) {
					replies.remove(from.getUniqueId());
				}
				if(replies.containsKey(to.getUniqueId())) {
					replies.remove(to.getUniqueId());
				}
				replies.put(from.getUniqueId(), to.getUniqueId());
				replies.put(to.getUniqueId(), from.getUniqueId());
			}else {
				from.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cMagadnak nem küldhetsz üzenetet!"));
			}
		}else {
			from.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cA játékos nem található!"));
		}
	}
	
	public void replyMessage(ProxiedPlayer from, String message) {
		if(replies.containsKey(from.getUniqueId())) {
			ProxiedPlayer to = plugin.getProxy().getPlayer(replies.get(from.getUniqueId()));
			this.sendMessage(from, to, message);
		}
	}
}

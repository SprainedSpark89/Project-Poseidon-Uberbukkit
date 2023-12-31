package pl.moresteck.uberbukkit;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.Bukkit;

import com.legacyminecraft.poseidon.PoseidonConfig;

import net.minecraft.server.MinecraftServer;
import pl.moresteck.uberbukkit.protocol.Protocol;

public class Uberbukkit {
    private static Integer pvn = null;
    private static List<Integer> pvns = null;

    public static int getTargetPVN() {
        if (pvn != null) return pvn;

        String pvnstr = PoseidonConfig.getInstance().getString("version.allow_join.protocol", "14");
        
        // separate target version from other allowed PVNs
        int commaIndex = pvnstr.indexOf(",");
        if (commaIndex != -1) {
        	pvnstr = pvnstr.substring(0, commaIndex);
        }
        
        pvn = 14;

        try {
            pvn = Integer.parseInt(pvnstr);
        } catch (Throwable t) {
            MinecraftServer.log.warning("[Uberbukkit] Target PVN is not a number! Can't proceed!");
            Bukkit.getServer().shutdown();
        }
        
        return pvn;
    }
    
    public static List<Integer> getAllowedPVNs() {
    	if (pvns != null) return pvns;

        String[] pvnstrs = PoseidonConfig.getInstance().getString("version.allow_join.protocol", "14").split(",");
        
        pvns = new LinkedList<>();
        
        for (String pvnstr : pvnstrs) {
        	try {
                pvns.add(Integer.parseInt(pvnstr));
            } catch (Throwable t) {
                MinecraftServer.log.warning("[Uberbukkit] PVN \"" + pvnstr + "\" is not a number! Can't proceed!");
                Bukkit.getServer().shutdown();
            }
        }
        
        return pvns;
    }

    public static Protocol getProtocolHandler() {
        return Protocol.getProtocolClass(getTargetPVN());
    }
}

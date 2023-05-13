package model.units.workerunits;

import model.environment.buildings.Building;
import model.environment.buildings.enums.BuildingName;
import model.map.Block;
import model.map.Texture;
import model.society.Government;
import model.units.WorkerUnit;
import model.units.enums.UnitName;

import java.util.LinkedList;
import java.util.Queue;

public class Tunneler extends WorkerUnit {

    Queue<Block> tunnelQueue = new LinkedList<>();

    public Tunneler() {
        super(UnitName.TUNNELER);
    }

    public Tunneler(UnitName name, Block block, Government government) {
        super(name, block, government);
    }

    public boolean canDigThere(Block blockToDig) {
        if (!canGoThere(blockToDig))
            return false;

        return true;
    }

    public void setTunnelQueue(Block block) {
        findPath(block);
        tunnelQueue.add(block);
    }

    public void digTunnel() {
        if (!tunnelQueue.isEmpty()) {
            if (moveQueue.isEmpty()) {
                if (tunnelQueue.peek().getEnvironment() != null && tunnelQueue.peek().getEnvironment() instanceof Building)
                    ((Building) tunnelQueue.peek().getEnvironment()).die();
                new Building(BuildingName.TUNNEL_ENTRANCE, government, tunnelQueue.peek());
                tunnelQueue.peek().setTexture(Texture.TUNNEL);
            }
        }
    }
}

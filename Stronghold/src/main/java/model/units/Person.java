package model.units;

import model.environment.buildings.Traps;
import model.map.Block;
import model.map.Direction;
import model.map.Map;
import model.resource.Armour;
import model.resource.ResourcesName;
import model.resource.Weapon;
import model.society.Government;
import model.units.enums.UnitName;
import utility.DataManager;
import view.GameViewController;

import java.util.*;

public class Person {
    protected int hp;
    protected int speed;
    protected int defencePower;

    protected UnitName name;

    protected final HashMap<ResourcesName, Integer> price;
    protected Block block;
    protected Government government;
    protected Queue<Block> moveQueue = new LinkedList<>();
    protected Block[] patrolBlocks = new Block[2];
    protected Block whichPatrolBlock;

    protected boolean canClimbLadder;
    protected boolean canDigMoat;
    protected static final ArrayList<Person> allUnits = new ArrayList<>();

    protected boolean isMoving = false;

    static {
        ArrayList<String[]> resourceCsv = DataManager.getArrayListFromCSV(DataManager.UNITS_PATH);
        String[] attributeNames = resourceCsv.get(0);

        start:
        for (int i = 1; i < resourceCsv.size(); i++) {

            String[] attributes = resourceCsv.get(i);
            String kind = "";
            int hp = 0, speed = 0, defencePower = 0, damage = 0, attackRange = 0;
            UnitName name = null;
            boolean canClimbLadder = false, canDigMoat = false;
            HashMap<ResourcesName, Integer> price = new HashMap<>();
            Weapon weaponToAdd = null;
            ArrayList<Armour> armours = new ArrayList<>();

            for (int j = 0; j < attributeNames.length; j++) {
                switch (attributeNames[j]) {
                    case "Kind": {
                        kind = attributes[j];
                        break;
                    }
                    case "Name": {
                        name = UnitName.getUnitByName(attributes[j]);
                        break;
                    }
                    case "Hp": {
                        hp = Integer.parseInt(attributes[j]);
                        break;
                    }
                    case "Speed": {
                        speed = Integer.parseInt(attributes[j]);
                        break;
                    }
                    case "Defence Power": {
                        defencePower = Integer.parseInt(attributes[j]);
                        break;
                    }
                    case "Armour": {
                        if (!attributes[j].equals("null")) {
                            String[] armourNames = attributes[j].split("~");

                            for (String armourName : armourNames) {
                                armours.add(new Armour(armourName));
                            }
                        }
                        break;
                    }
                    case "Weapon": {
                        if (!attributes[j].equals("null"))
                            weaponToAdd = new Weapon(attributes[j]);
                        break;
                    }
                    case "Price kind": {
                        String[] priceKinds = attributes[j].split("~");
                        String[] priceCounts = attributes[j + 1].split("~");

                        for (int i1 = 0; i1 < priceKinds.length && i1 < priceCounts.length; i1++) {
                            price.put(
                                    ResourcesName.getResourceByName(priceKinds[i1]),
                                    Integer.parseInt(priceCounts[i1])
                            );
                        }
                        break;
                    }
                    case "Climb Ladder": {
                        switch (attributes[j]) {
                            case "Yes": {
                                canClimbLadder = true;
                                break;
                            }
                            case "No": {
                                canClimbLadder = false;
                                break;
                            }
                        }
                    }
                    case "Dig Moat": {
                        switch (attributes[j]) {
                            case "Yes": {
                                canDigMoat = true;
                                break;
                            }
                            case "No": {
                                canDigMoat = false;
                                break;
                            }
                        }
                    }
                }
            }

            if (weaponToAdd != null) {
                damage = weaponToAdd.getDamage();
                attackRange = weaponToAdd.getAttackRange();
            }

            for (Armour armour : armours) {
                defencePower += armour.getDefenceBoost();
                speed += armour.getSpeedBoost();
            }

            switch (kind) {
                case "Soldier": {
                    new Soldier(hp, speed, defencePower, damage, attackRange, name, canClimbLadder, canDigMoat, price);
                    break;
                }
                case "Worker": {
                    new WorkerUnit(hp, speed, defencePower, name, price, canClimbLadder, canDigMoat);
                    break;
                }
                default:
                    throw new RuntimeException();
            }

        }
    }

    protected Person(int hp, int speed, int defencePower,
                     UnitName name,
                     HashMap<ResourcesName, Integer> price,
                     boolean canClimbLadder, boolean canDigMoat) {
        this.hp = hp;
        this.speed = speed;
        this.defencePower = defencePower;
        this.name = name;
        this.price = price;
        this.canClimbLadder = canClimbLadder;
        this.canDigMoat = canDigMoat;

        allUnits.add(this);
    }

    public Person(String name) {
        Person personToClone = getPersonByName(name);

        this.hp = personToClone.hp;
        this.speed = personToClone.speed;
        this.defencePower = personToClone.defencePower;
        this.name = personToClone.name;
        this.price = personToClone.price;
        this.canClimbLadder = personToClone.canClimbLadder;
        this.canDigMoat = personToClone.canDigMoat;

    }

    public Person(UnitName name) {
        Person personToClone = getPersonByUnitName(name);

        this.hp = personToClone.hp;
        this.speed = personToClone.speed;
        this.defencePower = personToClone.defencePower;
        this.name = personToClone.name;
        this.price = personToClone.price;
        this.canClimbLadder = personToClone.canClimbLadder;
        this.canDigMoat = personToClone.canDigMoat;
    }

    public Person(UnitName name, Block block, Government government) {
        Person personToClone = getPersonByUnitName(name);

        this.hp = personToClone.hp;
        this.speed = personToClone.speed;
        this.defencePower = personToClone.defencePower;
        this.name = personToClone.name;
        this.price = personToClone.price;
        this.canClimbLadder = personToClone.canClimbLadder;
        this.canDigMoat = personToClone.canDigMoat;
        this.setBlock(block);
        isMoving = false;
        setGovernment(government);
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    protected Person getPersonByName(String name) {
        for (Person unit : allUnits) {
            if (unit.getUnitName().equals(UnitName.getUnitByName(name)))
                return unit;
        }
        return null;
    }

    protected Person getPersonByUnitName(UnitName name) {
        for (Person unit : allUnits) {
            if (unit.getUnitName().equals(name))
                return unit;
        }
        return null;
    }

    public void setGovernment(Government government) {
        this.government = government;

        government.addUnit(this);
    }

    public void setBlock(Block block) {
        this.block = block;

        block.addUnit(this);
    }


    public int getHp() {
        return hp;
    }

    public int getSpeed() {
        return speed;
    }

    public int getDefencePower() {
        return defencePower;
    }

    public UnitName getUnitName() {
        return name;
    }

    public Block getBlock() {
        return block;
    }

    public HashMap<ResourcesName, Integer> getPrice() {
        return price;
    }

    public Government getGovernment() {
        return government;
    }

    public boolean canClimbLadder() {
        return canClimbLadder;
    }

    public boolean isCanDigMoat() {
        return canDigMoat;
    }

    public void takeDamage(int damage) {
        damage = (damage - defencePower) < 0 ? 1 : damage - defencePower;
        if (damage <= hp) {
            hp -= damage;
        } else hp = 0;
        if (hp <= 0)
            die();
    }

    public boolean findPath(Block destination) {
        HashMap<Block, Block> route = BFS(destination);
        if (route == null)
            return false;
        addRouteToQueue(route, destination);
        return true;
    }

    public Queue<Block> move() {
        Queue<Block> movedBlocks = new LinkedList<>();
        if (!moveQueue.isEmpty()) {
            Block lastBlock = block;
            int blocksMoved = 0;
            while (!moveQueue.isEmpty() && blocksMoved < speed) {
                lastBlock = moveQueue.peek();
                movedBlocks.add(lastBlock);
                if (lastBlock.getEnvironment() != null)
                    if (lastBlock.getEnvironment() instanceof Traps) {
                        die();
                        return movedBlocks;
                    }

                moveQueue.remove();
                blocksMoved += 1;
            }

            this.block.removeUnit(this);
            lastBlock.addUnit(this);
            this.block = lastBlock;
        }

        if (whichPatrolBlock != null) {
            whichPatrolBlock = whichPatrolBlock.equals(patrolBlocks[0]) ? patrolBlocks[1] : patrolBlocks[0];
            findPath(whichPatrolBlock);
        }

        return movedBlocks;
    }

    public boolean canGoThere(Block destination) {
        return BFS(destination) != null;
    }

    private HashMap<Block, Block> BFS(Block destination) {
        Map map = block.getMap();

        Queue<Block> queue = new LinkedList<>();
        queue.add(block);


        boolean[][] visited = new boolean[map.getHeight()][map.getWidth()];
        HashMap<Block, Block> route = new HashMap<>();

        long startTime = System.currentTimeMillis();
        long end = startTime + 2 * 1000;
//todo : add time limit
        while (!queue.isEmpty() && System.currentTimeMillis() < end) {
            Block currentBlock = queue.poll();
            int x = currentBlock.getX();
            int y = currentBlock.getY();
            for (Direction dir : Direction.values()) {
                int nextX = x + dir.deltaX, nextY = y + dir.deltaY;
                if (map.isValidXY(nextX, nextY) && !visited[nextY][nextX]) {
                    Block nextBlock = map.getBlockByXY(nextX, nextY);

                    if (nextBlock.canPassThisBlock(this)) {

                        visited[nextY][nextX] = true;
                        queue.add(nextBlock);

                        route.put(nextBlock, currentBlock);

                        if (nextBlock.equals(destination)) {
                            queue.clear();
                            return route;
                        }
                    }
                }
            }
        }
        return null;
    }

    private void addRouteToQueue(HashMap<Block, Block> route, Block destination) {
        moveQueue = new LinkedList<>();
        Block blockIter = destination;
        while (!blockIter.equals(block)) {
            moveQueue.add(blockIter);
            blockIter = route.get(blockIter);
        }

        Stack<Block> s = new Stack();

        while (!moveQueue.isEmpty()) {
            s.push(moveQueue.poll());
        }

        while (!s.isEmpty()) {
            moveQueue.add(s.pop());
        }
    }

    public void setPatrol(Block firstBlock, Block secondBlock) {
        patrolBlocks = new Block[]{firstBlock, secondBlock};
        whichPatrolBlock = firstBlock;
        findPath(firstBlock);
    }

    public void stopPatroling() {
        whichPatrolBlock = null;
    }

    public void die() {
        GameViewController.removePerson(this);
        this.hp = 0;
        this.block.removeUnit(this);
        this.government.removeUnit(this);
    }

    @Override
    public String toString() {
        return this.name + " Block: " + this.getBlock() + " Government: " + government;
    }

    public int getInitialHp() {
        return getPersonByUnitName(this.name).hp;
    }
}
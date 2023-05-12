package model.map;

import model.environment.Environment;
import model.environment.Rock;
import model.environment.buildings.Building;
import model.environment.tree;
import model.units.Person;

import java.util.ArrayList;
import java.util.Objects;
import java.lang.Math;

public class Block {
    private final int x;
    private final int y;
    private Texture texture;
    private Map map;

    private Environment environment;
    private ArrayList<Person> units;

    protected Block(int x, int y, Texture texture, Map map) {
        this.x = x;
        this.y = y;
        this.texture = texture;
        this.map = map;
    }

    {
        units = new ArrayList<Person>();
    }

    public Map getMap() {
        return map;
    }

    public ArrayList<Person> getUnits() {
        return units;
    }

    public void addUnit(Person person) {
        units.add(person);
    }

    public void removeUnit(Person person) {
        for (Person unit : units) {
            if (unit.equals(person))
                units.remove(person);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
    public boolean canPassThisBlock(Person person) {
//        TODO: sang derakht bazi_textura wall
        return true;
    }
    public Block findClosestBlock(int range, Block block) {
        int xCorner = this.x - range, yCorner = this.y - range;
        Block closestBlock = null;
        double distance = Double.MAX_VALUE;
        for (int y = yCorner; y < 2 * range + 1 + yCorner; y++) {
            for (int x = xCorner; x < 2 * range + 1 + xCorner; x++) {
                if (map.isValidXY(x, y)) {
                    Block blockToCheck = getMap().getBlockByXY(x, y);
                    if (distance > block.distanceTo(blockToCheck)){
                        closestBlock = blockToCheck;
                        distance = this.distanceTo(blockToCheck);
                    }
                }
            }
        }
        return closestBlock;
    }

    public double distanceTo(Block block) {
        int delX = this.x - block.getX();
        int delY = this.y - block.getY();
        return Math.sqrt(Math.pow(delX, 2) + Math.pow(delY, 2));
    }
    //TODO: complete details.
    public String showDetails() {
        String output ="texture: " + this.texture.getName();

        if(environment instanceof Building)
            output += "\nBuilding: " + ((Building) environment).getName().getName();

        if(environment instanceof tree)
            output += "\ntree";

        if(environment instanceof Rock)
            output += "\nrock";

        for (Person unit : units) {
            output += "\n" +unit.getName().getName();
        }

        return output;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Block block = (Block) o;
        return x == block.x && y == block.y && Objects.equals(map, block.map);
    }

}

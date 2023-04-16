package model.environment.buldings;

import model.environment.Environment;
import model.environment.buldings.enums.BuildingCategory;
import model.society.Government;
import model.resourecs.Resource;

import java.util.ArrayList;

public class Building extends Environment
{
    Government owner;
    BuildingCategory category;
    int hp;
    ArrayList<Resource> price;

    public Building(String name,int size,BuildingCategory category,int hp,Government owner, ArrayList<Resource> price) {
        super(name,size);
        this.category = category;
        this.hp = hp;
        this.owner = owner;
        this.price = price;
    }

    public Government getOwner() {
        return owner;
    }

    public void setOwner(Government owner) {
        this.owner = owner;
    }

    public ArrayList<Resource> getPrice() {
        return price;
    }

    public void setPrice(ArrayList<Resource> price) {
        this.price = price;
    }
}

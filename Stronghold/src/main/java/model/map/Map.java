package model.map;

import model.environment.buildings.Castle;
import model.environment.buildings.StorageBuilding;
import model.environment.buildings.enums.BuildingName;
import model.society.Government;
import model.society.enums.Colors;
import utility.*;

import java.util.ArrayList;

public class Map {
    Block[][] grid;

    private final int height;
    private final int width;

    public Map(int height, int width) {
        this.height = height;
        this.width = width;

        grid = new Block[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = new Block(x, y, Texture.GROUND, this);
            }
        }
    }

    public void initGovernments(ArrayList<Government> governments) {
        for (Government government : governments) {
            int randX = RandomGenerators.randomNumber(2, width - 2), randY = RandomGenerators.randomNumber(2, height - 2);
            new Castle(BuildingName.CASTLE, government,
                    getBlockByXY(randX, randY));
            new StorageBuilding(BuildingName.STOCKPILE, government, getBlockByXY(randX + 1, randY));
            // TODO: 6/1/2023 delete: 
//            System.out.println(government.getColor() + ": " + "X=" + randX + " Y=" + randY);
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Block getBlockByXY(int x, int y) {
        return grid[y][x];
    }

    public void setTexture(int x, int y, Texture texture) {
        getBlockByXY(x, y).setTexture(texture);
    }

    public void setGroupTexture(int leftCornerX, int leftCornerY,
                                int rightCornerX, int rightCornerY,
                                Texture texture) {

        int width = rightCornerX - leftCornerX, height = rightCornerY - leftCornerY;

        for (int x = leftCornerX; x < width + leftCornerX + 1; x++) {
            for (int y = leftCornerY; y < height + leftCornerY + 1; y++) {
                getBlockByXY(x, y).setTexture(texture);
            }
        }

    }

    public void clearBlock(int x, int y) {
        grid[y][x] = new Block(x, y, Texture.GROUND, this);
    }

    public void dropRock(int x, int y) {
    }

    public boolean isValidXY(int x, int y) {
        if (x < 0 || x > width - 1)
            return false;
        if (y < 0 || y > height - 1)
            return false;
        return true;
    }

    public String showMap(Block block, int widthRange, int heightRange) {
        int blockWidth = 5, blockHeight = 3;
        StringBuilder output = new StringBuilder();
        int xCorner = block.getX() - widthRange, yCorner = block.getY() - heightRange;

        for (int y = yCorner; y < heightRange * 2 + 1 + yCorner; y++) {

            output.append("-".repeat((widthRange * 2 + 1) * (blockWidth + 1)));
            output.append("-\n");

            for (int i = 0; i < blockHeight; i++) {

                for (int x = xCorner; x < widthRange * 2 + 1 + xCorner; x++) {
                    Block blockToPrint = this.getBlockByXY(x, y);

                    output.append("|");
                    output.append(blockToPrint.getTexture().getColor());
                    for (int i1 = 0; i1 < blockWidth; i1++) {
                        char c = blockToPrint.getBlockDetailForShowMap().get(i1 % blockToPrint.getBlockDetailForShowMap().size());
                        output.append(c);
                    }
//                    output.append("#".repeat(blockWidth));
                    output.append(Colors.RESET);
                }
                output.append("|\n");
            }


        }
        output.append("-".repeat((widthRange * 2 + 1) * (blockWidth + 1)));
        output.append("-");

        return output.toString();
    }
}

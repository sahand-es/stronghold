package model.map;

import model.society.enums.Colors;

public class Map
{
    Block[][] grid;

    private final int height;
    private final int width;

    public Map(int height, int width)
    {
        this.height = height;
        this.width = width;

        grid = new Block[height][width];
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                grid[y][x] = new Block(x, y, Texture.GROUND, this);
            }
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Block getBlockByXY(int x, int y)
    {
        return grid[y][x];
    }

    public Block[][] getMiniMap(int x, int y, int width, int height)
    {
        Block[][] miniMap;

//        ToDo: x, y.

        return null;
    }

    public void setTexture(int x, int y, Texture texture) {
        getBlockByXY(x, y).setTexture(texture);
    }

    public void setGroupTexture(int leftCornerX, int leftCornerY,
                                int rightCornerX, int rightCornerY,
                                Texture texture) {

        int width = rightCornerX - leftCornerX, height = rightCornerY - leftCornerY;

        for (int x = leftCornerX; x < width + leftCornerX; x++) {
            for (int y = leftCornerY; y < height + leftCornerY; y++) {
                getBlockByXY(x,y).setTexture(texture);
            }
        }

    }

    public boolean isValidXY(int x, int y) {
        if (x < 0 || x > width)
            return false;
        if (y < 0 || y > height)
            return false;
        return true;
    }

    public String showMap(Block block) {
        int blockWidth = 5, blockHeight = 3;
        StringBuilder output = new StringBuilder();
        int heightRange = 1, widthRange= 5;
        int xCorner = block.getX() - widthRange, yCorner = block.getY() - heightRange;

        for (int y = yCorner; y < heightRange * 2 + 1 + yCorner; y++) {

            output.append("-".repeat((widthRange * 2 + 1) * (blockWidth + 1)));
            output.append("-\n");

            for (int i = 0; i < blockHeight; i++) {

                for (int x = xCorner; x < widthRange * 2 + 1 + xCorner; x++) {
                    Block blockToPrint = this.getBlockByXY(x, y);

                    output.append("|");
                    output.append(blockToPrint.getTexture().getColor());
                    output.append("#".repeat(blockWidth));
                    output.append(Colors.RESET);
                }
            output.append("|\n");
            }


        }
        output.append("-".repeat((widthRange * 2 + 1) * (blockWidth + 1)));
        output.append("-");

        return output.toString();
    }

    public static void main(String[] args) {
        Map map = new Map(100, 100);
//        map.setTexture(50, 50, Texture.WATER);
        map.setGroupTexture(50,50,55,51, Texture.WATER);
        System.out.println(map.showMap(map.getBlockByXY(50,50)));
    }

}

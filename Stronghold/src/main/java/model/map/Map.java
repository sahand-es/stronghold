package model.map;

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
        return grid[x][y];
    }

    public Block[][] getMiniMap(int x, int y, int width, int height)
    {
        Block[][] miniMap;

//        ToDo: x, y.

        return null;
    }

    public boolean isValidXY(int x, int y) {
        if (x < 0 || x > width)
            return false;
        if (y < 0 || y > height)
            return false;
        return true;
    }

}

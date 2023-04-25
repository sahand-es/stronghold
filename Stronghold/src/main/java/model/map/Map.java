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

        for (int x = 0; x < grid.length; x++)
        {
            for (int y = 0; y < grid[x].length; y++)
            {
                grid[x][y] = new Block(x, y, Texture.GROUND);
            }
        }
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

}

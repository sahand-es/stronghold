package model.map;

public class Map
{
    Block[][] grid;

    public Map(int height, int width)
    {
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
        return null;
    }

    public Block[][] getMiniMap(int x, int y, int size)
    {
        return null;
    }
}

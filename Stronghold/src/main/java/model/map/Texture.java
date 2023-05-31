package model.map;

import model.society.enums.Colors;

public enum Texture
{
    GROUND(Colors.YELLOW_BACKGROUND,"Ground",true, "ground.png"),
    GRAVEL(Colors.YELLOW_BACKGROUND_BRIGHT, "Gravel",true, "gravel.png"),
    ROCK(Colors.WHITE_BACKGROUND_BRIGHT, "Rock",true, "rock.png"),
    BIG_ROCK(Colors.WHITE_BACKGROUND, "Big Rock",false, "big-rock.png"),
    IRON(Colors.BLACK, "Iron",true, "iron.png"),
    GRASS(Colors.CYAN_BACKGROUND_BRIGHT, "Grass",true, "grass.png"),
    SHORT_GRASS_LAWN(Colors.GREEN_BACKGROUND_BRIGHT, "Short Grass Lawn",true, "grass.png"),
    TALL_GRASS_LAWN(Colors.CYAN_BACKGROUND, "Tall Grass Lawn",true,"grass.png"),
    WATER(Colors.BLUE_BACKGROUND_BRIGHT, "Water",true, "water.png"),
    DEEP_WATER(Colors.BLUE_BACKGROUND,"Deep Water",false, "deep-water.png"),
    SAND(Colors.YELLOW_BOLD,"Sand" , true, "sand.png"),
    PITCH(Colors.BLACK_BACKGROUND_BRIGHT,"Pitch",false, "pitch.png"),
    OIL(Colors.BLACK_BACKGROUND,"Oil",false, "oil.png"),
    TUNNEL(Colors.BLACK_BACKGROUND_BRIGHT, "Tunnel",true, "tunnel.png");
    ;

    private final String name;
    private final String color;

    private final boolean canPass;
    private final String imagePath;

    Texture(String name, String color, boolean canPass, String imagePath) {
        this.name = name;
        this.color = color;
        this.canPass = canPass;
        this.imagePath = imagePath;
    }

    public static boolean isValid(String type) {
        return getTextureByString(type) != null;
    }

    public String getName() {
        return name;
    }

    public static Texture getTextureByString(String string) {
        for (Texture texture : Texture.values()) {
            if (texture.getName().equalsIgnoreCase(string))
                return texture;
        }
        return null;
    }

    public String getColor() {
        return color;
    }

    public boolean canPass() {
        return canPass;
    }

    public String getImagePath() {
        return  "file:src/main/resources/images/tiles/textures/" + imagePath;
    }
}

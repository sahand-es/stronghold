package model.map;

import model.society.enums.Colors;

public enum Texture
{
    GROUND(Colors.YELLOW_BACKGROUND,"Ground",true),
    GRAVEL(Colors.YELLOW_BACKGROUND_BRIGHT, "Gravel",true),
    ROCK(Colors.WHITE_BACKGROUND_BRIGHT, "Rock",true),
    BIG_ROCK(Colors.WHITE_BACKGROUND, "Big Rock",false),
    IRON(Colors.BLACK, "Iron",true),
    GRASS(Colors.CYAN_BACKGROUND_BRIGHT, "Grass",false),
    SHORT_GRASS_LAWN(Colors.GREEN_BACKGROUND_BRIGHT, "Short Grass Lawn",true),
    TALL_GRASS_LAWN(Colors.CYAN_BACKGROUND, "Tall Grass Lawn",true),
    WATER(Colors.BLUE_BACKGROUND_BRIGHT, "Water",true),
    DEEP_WATER(Colors.BLUE_BACKGROUND,"Deep Water",false),
    SAND(Colors.YELLOW_BOLD,"Sand" , true),
    PITCH(Colors.BLACK_BACKGROUND_BRIGHT,"Pitch",false),
    OIL(Colors.BLACK_BACKGROUND,"Oil",false);
    ;

    private final String name;
    private final String color;

    private final boolean canPass;

    Texture(String color, String string, boolean canPass) {
        this.color = color;
        this.name = string;
        this.canPass = canPass;
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
}

package model.map;

import model.society.enums.Colors;
import org.jetbrains.annotations.NotNull;

public enum Texture
{
    GROUND(Colors.YELLOW_BACKGROUND,"Ground"),
    GRAVEL(Colors.YELLOW_BACKGROUND_BRIGHT, "Gravel"),
    ROCK(Colors.WHITE_BACKGROUND_BRIGHT, "Rock"),
    BIG_ROCK(Colors.WHITE_BACKGROUND, "Big Rock"),
    IRON(Colors.BLACK, "Iron"),
    GRASS(Colors.CYAN_BACKGROUND_BRIGHT, "Grass"),
    SHORT_GRASS_LAWN(Colors.GREEN_BACKGROUND_BRIGHT, "Short Grass Lawn"),
    TALL_GRASS_LAWN(Colors.CYAN_BACKGROUND, "Tall Grass Lawn"),
    WATER(Colors.BLUE_BACKGROUND, "Water");
    ;

    private final String name;
    private final String color;

    Texture(String color, String string) {
        this.color = color;
        this.name = string;
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
}

package model.map;

import model.society.enums.Colors;

public enum Texture
{
    GROUND(Colors.YELLOW_BACKGROUND),
    GRAVEL(Colors.YELLOW_BACKGROUND_BRIGHT),
    ROCK(Colors.WHITE_BACKGROUND_BRIGHT),
    BIG_ROCK(Colors.WHITE_BACKGROUND),
    IRON(Colors.BLACK),
    GRASS(Colors.CYAN_BACKGROUND_BRIGHT),
    SHORT_GRASS_LAWN(Colors.GREEN_BACKGROUND_BRIGHT),
    TALL_GRASS_LAWN(Colors.CYAN_BACKGROUND);

    private final String color;

    Texture(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}

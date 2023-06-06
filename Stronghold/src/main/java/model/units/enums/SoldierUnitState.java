package model.units.enums;

public enum SoldierUnitState
{
    STANDING("Standing", "standing.png"),
    DEFENSIVE("Defencive", "defencive.png"),
    OFFENSIVE("Offensive", "offensive.png");
    public final String string;
    private final String imagePath;

    SoldierUnitState(String string, String imagePath) {
        this.string = string;
        this.imagePath = imagePath;
    }

    public static boolean isValid(String type) {
        for (SoldierUnitState state : SoldierUnitState.values()) {
            if(state.string.equalsIgnoreCase(type))
                return true;
        }
        return false;
    }
    public static SoldierUnitState getByType(String type) {
        for (SoldierUnitState state : SoldierUnitState.values()) {
            if(state.string.equalsIgnoreCase(type))
                return state;
        }
        return null;
    }

    public String getImagePath() {
        return "file:src/main/resources/images/soldiers/soldier states/" + imagePath;
    }
}

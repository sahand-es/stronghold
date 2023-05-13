package model.units.enums;

public enum SoldierUnitState
{
    STANDING("Standing"),
    DEFENSIVE("Defencive"),
    OFFENSIVE("Offensive");
    public final String string;

    SoldierUnitState(String string) {
        this.string = string;
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
}

package view.enums.messages;

public enum GameMessages {
    INVALID_RATE("Rate is invalid"),
    INVALID_XY("Invalid Coordinates"),
    INVALID_RECTANGLE("Invalid coordinates, correct form: x1 <= x2, y1 <= y2"),
    NOT_ENOUGH_RESOURCE("Not enough resource"),
// create building:
    CANNOT_BUILD_HERE("Can not build on this block"),
// select building:
    INVALID_BUILDING_TYPE("There is no building with this name!"),
    EMPTY_XY_BUILDING("There is no building on this block!"),
    NOT_YOURS_BUILDING("This building doesn't belong to you"),
    NOT_A_BUILDING("You can not select this Environment"),
// create unit:
    BUILDING_NOT_SELECTED("You should select a Building"),
    INVALID_UNIT_TYPE("There is no unit with this name!"),
    INVALID_COUNT("Unit count is Invalid"),
    CANNOT_MAKE_THIS_UNIT_IN_THIS_BUILDING("you cant make this unit in this building"),
    NOT_UNIT_MAKER("You should select a UnitMaker Building"),
// repair:
    NOT_ENOUGH_ROCK("You doesn't have enough stones to repair this building"),
    ENEMY_CLOSE("Cannot repair: enemy units are to close"),
    CANNOT_REPAIR_THIS_BUILDING_TYPE("Cannot repair: this building is not a castle type building"),
// select unit:
    EMPTY_XY_UNIT("There is no unit on this coordinates"),
    NOT_YOURS_UNIT("This unit does not belong to you"),
// move unit:
    CANNOT_GO_THERE("Unit cant go there"),
// patrol unit:
    INVALID_UNIT_STATE("Invalid Unit state"),

    UNIT_NOT_SELECTED("You have not selected a unit"),
    THIS_UNIT_DOES_NOT_HAVE_STATE("This unit doesnt have state"),
    SUCCESS("success"),
//  attack:
    CAN_NOT_ATTACK_WITH_THIS_UNIT_TYPE("This unit cannot attack"),
    NO_ENEMY_TO_ATTACK("There is no enemy to attack"),
    CAN_NOT_GO_THERE_TO_ATTACK("Unit cant go there"),
    TRADE_MENU("trade menu"),
//  pour oil:
    SELECTED_UNIT_IS_NOT_ENGINEER("This unit is not an engineer"),
    INVALID_DIRECTION("Invalid Direction"),

//  dig tunnel:

    SELECTED_UNIT_IS_NOT_TUNNELER("This unit is not a tunneler"),

    CAN_NOT_DIG_THERE("can not dig this block"),


//  build:
    INVALID_SIEGE_BUILD_NAME("Invalid siege equipment name"),
    SIEGE_TENT_NOT_BUILD("Make siege tent first"),

    CAN_NOT_DROP_UNIT_HERE("Can not build siege equipment here"),

//  map:
    INVALID_TEXTURE("Invalid texture"),


;
    final String message;

    GameMessages(String message){
        this.message = message;
    }

    public String message(){
        return this.message;
    }
//  state:
;

    ;
}

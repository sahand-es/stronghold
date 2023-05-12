package view.enums.messages;

public enum GameMessages {
    INVALID_XY,
    NOT_ENOUGH_RESOURCE,
// create building:
    CANNOT_BUILD_HERE,
// select building:
    INVALID_BUILDING_TYPE,
    EMPTY_XY_BUILDING,
    NOT_YOURS_BUILDING,
    NOT_A_BUILDING,
// create unit:
    BUILDING_NOT_SELECTED,
    INVALID_UNIT_TYPE,
    INVALID_COUNT,
    CANNOT_MAKE_THIS_UNIT_IN_THIS_BUILDING,
    NOT_UNIT_MAKER,
// repair:
    NOT_ENOUGH_ROCK,
    ENEMY_CLOSE,
// select unit:
    EMPTY_XY_UNIT,
    NOT_YOURS_UNIT,
    // move unit:
// patrol unit:
    INVALID_UNIT_STATE,
    CLOSE_RANGE_UNIT,
    NOT_SELECTED_UNIT,
    SUCCESS,

    ;
}

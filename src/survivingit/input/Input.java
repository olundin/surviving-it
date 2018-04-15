package survivingit.input;

public enum Input
{
    KEY_A(65),
    KEY_W(87),
    KEY_D(68),
    KEY_S(83),

    KEY_I(73),
    KEY_M(77),
    KEY_C(67),

    KEY_LEFT(37),
    KEY_UP(38),
    KEY_RIGHT(39),
    KEY_DOWN(40),

    KEY_SPACE(32),
    KEY_RETURN(10),
    KEY_SHIFT(16),

    ESC(27),

    BUTTON_LEFT(1),
    BUTTON_RIGHT(3),
    BUTTON_SCROLL(2);

    public final int id;

    Input(int id) {
    	this.id = id;
    }

}

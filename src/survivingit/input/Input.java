package survivingit.input;

/**
 * Key and button identifiers
 */
public enum Input
{
    /**
     * 'A' key's code
     */
    KEY_A(65),
    /**
     * 'W' key's code
     */
    KEY_W(87),
    /**
     * 'D' key's code
     */
    KEY_D(68),
    /**
     * 'A' key's code
     */
    KEY_S(83),
    /**
     * 'I' key's code
     */
    KEY_I(73),
    /**
     * 'M' key's code
     */
    KEY_M(77),
    /**
     * 'C' key's code
     */
    KEY_C(67),
    /**
     * 'LEFT' key's code
     */
    KEY_LEFT(37),
    /**
     * 'UP' key's code
     */
    KEY_UP(38),
    /**
     * 'RIGHT' key's code
     */
    KEY_RIGHT(39),
    /**
     * 'DOWN' key's code
     */
    KEY_DOWN(40),

    /**
     * 'SPACE' key's code
     */
    KEY_SPACE(32),
    /**
     * 'RETURN' key's code
     */
    KEY_RETURN(10),
    /**
     * 'SHIFT' key's code
     */
    KEY_SHIFT(16),

    /**
     * 'ESC' key's code
     */
    ESC(27),

    /**
     * 'LEFT' mouse button's code
     */
    BUTTON_LEFT(1),
    /**
     * 'RIGHT' mouse button's code
     */
    BUTTON_RIGHT(3),
    /**
     * 'SCROLL' mouse button's code
     */
    BUTTON_SCROLL(2);

    /**
     * Identifier of key
     */
    public final int id;

    Input(int id) {
    	this.id = id;
    }

}

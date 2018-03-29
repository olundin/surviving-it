package survivingit.items;

public class DamageEffect extends AbstractEffect implements EffectInterface {

    private int damage;

    public DamageEffect(final int damage, final Item source) {
        super(source);
        this.damage = damage;
    }

    public void react() {

    }

}

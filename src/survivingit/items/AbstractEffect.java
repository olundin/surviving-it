package survivingit.items;

public abstract class AbstractEffect {

   private final Item source;

   public AbstractEffect(Item source) {
       this.source = source;
   }

   public Item getSource() {
       return this.source;
   }

}

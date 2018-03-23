package survivingit.containers;

import survivingit.items.Item;

/**
 * Created by AngusLothian on 2018-03-23.
 */
public abstract class Container {

    private Item[] items;
    private int size;

    public Container(int size) {
        this.items = new Item[size];
        this.size = size;
    }

    public void addItem(Item item) {
        items.
    }
}

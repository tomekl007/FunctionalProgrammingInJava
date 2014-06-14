package beinglazy;

import java.util.function.Supplier;

public class Holder {
    private Supplier<Heavy> heavy = this::createAndCacheHeavy;

    public Holder() {
        System.out.println("Holder created");
    }

    public Heavy getHeavy() {

        return heavy.get();
    }
//...

    private synchronized Heavy createAndCacheHeavy() {
        if (!HeavyFactory.class.isInstance(heavy)) {
            heavy = new HeavyFactory();
        }
        return heavy.get();
    }

    public static void main(String[] args) {
        final Holder holder = new Holder();
        System.out.println("deferring heavy creation...");
        System.out.println(holder.getHeavy());
        System.out.println(holder.getHeavy());
    }
}



class HeavyFactory implements Supplier<Heavy> {
    private final Heavy heavyInstance = new Heavy();

    public Heavy get() {
        return heavyInstance;
    }
}
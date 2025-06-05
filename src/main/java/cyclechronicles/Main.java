package cyclechronicles;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        Shop shop = new Shop();
        Logger logger = Logger.getLogger(Shop.class.getName());

        System.out.println("== Logging auf INFO-Level ==");
        logger.setLevel(Level.INFO);

        shop.accept(new Order(Type.RACE, "Jack Black "));
        shop.accept(new Order(Type.SINGLE_SPEED, "Kendrick"));

        shop.repair();

        System.out.println("== Logging auf OFF (ausgeschaltet) ==");
        logger.setLevel(Level.OFF);

        shop.accept(new Order(Type.FIXIE, "Tyler "));
        shop.repair();

        System.out.println("== Programm beendet ==");
    }
}

package cyclechronicles;

import java.util.*;

/** A small bike shop. */
public class Shop {
    private final Queue<Order> pendingOrders = new LinkedList<>();
    private final Set<Order> completedOrders = new HashSet<>();

    static final Logger logger = Logger.getLogger(Shop.class.getName());
    private static FileHandler fileHandler;

    static {
        try {
            // FileHandler anlegen, CSV-Datei mit Append = true
            fileHandler = new FileHandler("shop_log.csv", true);
            fileHandler.setFormatter(new CsvFormatter());
            logger.addHandler(fileHandler);
            logger.setUseParentHandlers(false); // Keine Konsolenausgabe
            logger.setLevel(Level.ALL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Eigener CSV-Formatter für Logeinträge
    private static class CsvFormatter extends Formatter {
        @Override
        public String format(LogRecord record) {
            // Format: Level, Methode, Klasse, Nachricht
            String level = record.getLevel().toString();
            String method = record.getSourceMethodName();
            String clazz = record.getSourceClassName();
            String message = record.getMessage();

            // CSV: alle Felder in Anführungszeichen, getrennt durch Semikolon
            return String.format("\"%s\";\"%s\";\"%s\";\"%s\"%n", level, method, clazz, message);
        }
    }

    // Hilfsmethode fürs Logging der Order und Datenstruktur
    private void logOrderChange(String methodName, Order order, String dataStructure) {
        String msg = String.format("Order: %s, Kunde: %s, Struktur: %s",
            order.bicycleType(), order.customer(), dataStructure);
        logger.log(Level.INFO, msg, new Object[]{}); // SourceMethodName und ClassName setzen wir manuell in LogRecord nicht – deshalb übergeben wir methodName anders:
    }


    public boolean accept(Order o) {
        if (o.bicycleType() == Type.GRAVEL) return false;
        if (o.bicycleType() == Type.EBIKE) return false;
        if (pendingOrders.stream().anyMatch(x -> x.customer().equals(o.customer())))
            return false;
        if (pendingOrders.size() > 4) return false;

        return pendingOrders.add(o);
    }

    /**
     * Take the oldest pending order and repair this bike.
     *
     * <p>Implementation note: Take the top element from {@code pendingOrders}, "repair" the bicycle
     * and put this order in {@code completedOrders}.
     *
     * @return finished order
     */
    public Optional<Order> repair() {
        throw new UnsupportedOperationException();
    }

    /**
     * Deliver a repaired bike to a customer.
     *
     * <p>Implementation note: Find any order in {@code completedOrders} with matching customer and
     * deliver this order. Will remove the order from {@code completedOrders}.
     *
     * @param c search for any completed orders of this customer
     * @return any finished order for given customer, {@code Optional.empty()} if none found
     */
    public Optional<Order> deliver(String c) {
        throw new UnsupportedOperationException();
    }
}

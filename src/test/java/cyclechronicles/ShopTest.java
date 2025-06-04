package cyclechronicles;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ShopTest {

    private Shop shop;

    @BeforeEach
    void setup() {
        shop = new Shop();
    }

    /**
     * Mockito wird hier verwendet, um die Klasse Order zu mocken,
     * da deren Methoden nicht implementiert sind und sonst
     * UnsupportedOperationExceptions werfen würden.
     * Dadurch können wir kontrollierte Rückgabewerte simulieren
     * und die Methode Shop#accept zuverlässig testen.
     */

    @Test
    public void testValidOrderRaceaccept() {
        Order order = mock(Order.class);
        when(order.getBicycleType()).thenReturn(Type.RACE);
        when(order.getCustomer()).thenReturn("Kunde1");

        boolean result = shop.accept(order);

        assertTrue(result);
    }

    @Test
    public void testGravelBike_shouldBeRejected() {
        Order order = mock(Order.class);
        when(order.getBicycleType()).thenReturn(Type.GRAVEL);
        when(order.getCustomer()).thenReturn("Kunde2");

        boolean result = shop.accept(order);

        assertFalse(result);
    }

    @Test
    public void testEbike_shouldBeRejected() {
        Order order = mock(Order.class);
        when(order.getBicycleType()).thenReturn(Type.EBIKE);
        when(order.getCustomer()).thenReturn("Kunde3");

        boolean result = shop.accept(order);

        assertFalse(result);
    }

    @Test
    public void testCustomerAlreadyHasOrder_shouldBeRejected() {
        Order firstOrder = mock(Order.class);
        when(firstOrder.getBicycleType()).thenReturn(Type.RACE);
        when(firstOrder.getCustomer()).thenReturn("Kunde4");

        shop.accept(firstOrder); // erste Bestellung geht durch

        Order secondOrder = mock(Order.class);
        when(secondOrder.getBicycleType()).thenReturn(Type.FIXIE);
        when(secondOrder.getCustomer()).thenReturn("Kunde4"); // gleicher Kunde

        boolean result = shop.accept(secondOrder);

        assertFalse(result);
    }

    @Test
    public void testOrderLimitAt4_shouldBeAccepted() {
        for (int i = 0; i < 4; i++) {
            Order order = mock(Order.class);
            when(order.getBicycleType()).thenReturn(Type.FIXIE);
            when(order.getCustomer()).thenReturn("Kunde" + i);
            shop.accept(order);
        }

        Order newOrder = mock(Order.class);
        when(newOrder.getBicycleType()).thenReturn(Type.RACE);
        when(newOrder.getCustomer()).thenReturn("KundeNeu");

        boolean result = shop.accept(newOrder);

        assertTrue(result);
    }

    @Test
    public void testOrderLimitExceededAt5_shouldBeRejected() {
        for (int i = 0; i < 5; i++) {
            Order order = mock(Order.class);
            when(order.getBicycleType()).thenReturn(Type.SINGLE_SPEED);
            when(order.getCustomer()).thenReturn("User" + i);
            shop.accept(order);
        }

        Order newOrder = mock(Order.class);
        when(newOrder.getBicycleType()).thenReturn(Type.RACE);
        when(newOrder.getCustomer()).thenReturn("ExtraKunde");

        boolean result = shop.accept(newOrder);

        assertFalse(result);
    }

    @Test
    public void testMultipleInvalidConditions_shouldBeRejected() {
        Order order = mock(Order.class);
        when(order.getBicycleType()).thenReturn(Type.GRAVEL);
        when(order.getCustomer()).thenReturn("MultiFail");

        // 5 Aufträge vorher hinzufügen
        for (int i = 0; i < 5; i++) {
            Order o = mock(Order.class);
            when(o.getBicycleType()).thenReturn(Type.RACE);
            when(o.getCustomer()).thenReturn("AltKunde" + i);
            shop.accept(o);
        }

        boolean result = shop.accept(order);

        assertFalse(result);
    }
}

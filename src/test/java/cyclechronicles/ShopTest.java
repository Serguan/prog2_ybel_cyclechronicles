package cyclechronicles;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class ShopTest {

    private Shop shop;

    @BeforeEach
    void setUp() {
        shop = new Shop();
    }


    @Test
    void testRepair_shouldThrowUnsupportedOperationException() {
        Order order = mock(Order.class);
        when(order.getBicycleType()).thenReturn(Type.ROAD);
        when(order.getCustomer()).thenReturn("Luciel");

        assertTrue(shop.accept(order));
        assertThrows(UnsupportedOperationException.class, () -> shop.repair());
    }



    @Test
    void testDeliver_shouldThrowUnsupportedOperationException() {
        Order order = mock(Order.class);
        when(order.getBicycleType()).thenReturn(Type.ROAD);
        when(order.getCustomer()).thenReturn("Don");

        assertTrue(shop.accept(order));
        assertThrows(UnsupportedOperationException.class, () -> shop.deliver("Don"));
    }
}

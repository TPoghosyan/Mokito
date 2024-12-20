package test;

import com.epam.tamentoring.bo.DiscountUtility;
import com.epam.tamentoring.bo.OrderService;
import com.epam.tamentoring.bo.ShoppingCart;
import com.epam.tamentoring.bo.UserAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    @Mock
    private DiscountUtility discountUtility;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testNoOtherInteractions() {
        // Arrange
        UserAccount user = new UserAccount("John", "Smith", "1990/10/10", new ShoppingCart(new ArrayList<>()));
        when(discountUtility.calculateDiscount(user)).thenReturn(3.0);

        // Act
        orderService.getOrderPrice(user);

        // Verify that there are no other interactions with the mocked object
        verify(discountUtility).calculateDiscount(user);
        verifyNoMoreInteractions(discountUtility);
    }


    @Test
    public void testGetOrderPrice_WithDiscount() {
        // Arrange
        UserAccount user = new UserAccount("John", "Smith", "1990/10/10", new ShoppingCart(new ArrayList<>()));
        when(discountUtility.calculateDiscount(user)).thenReturn(3.0);

        // Act
        double orderPrice = orderService.getOrderPrice(user);

        // Assert
        assertEquals(-3.0, orderPrice); // Assuming the cart is empty, so total price is 0 and discount is 3
    }

    @Test
    public void testCalculateDiscountCalledOnce() {
        // Arrange
        UserAccount user = new UserAccount("John", "Smith", "1990/10/10", new ShoppingCart(new ArrayList<>()));
        when(discountUtility.calculateDiscount(user)).thenReturn(3.0);

        // Act
        orderService.getOrderPrice(user);

        // Verify that the mocked object is called only once
        verify(discountUtility).calculateDiscount(user);
    }
}
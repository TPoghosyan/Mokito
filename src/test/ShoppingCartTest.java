package test;

import com.epam.tamentoring.bo.Product;
import com.epam.tamentoring.bo.ShoppingCart;
import com.epam.tamentoring.exceptions.ProductNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ShoppingCartTest {

    private ShoppingCart shoppingCart;

    @BeforeEach
    public void setUp() {
        List<Product> products = new ArrayList<>();
        shoppingCart = new ShoppingCart(products);
    }

    @Test
    public void testAddProductToCart_NewProduct() {
        Product product = new Product(1, "Product1", 10.0, 2.0);
        shoppingCart.addProductToCart(product);
        assertEquals(1, shoppingCart.getProducts().size());
        assertEquals(product, shoppingCart.getProducts().get(0));
    }

    @Test
    public void testAddProductToCart_ExistingProduct() {
        Product product1 = new Product(1, "Product1", 10.0, 2.0);
        Product product2 = new Product(1, "Product1", 10.0, 3.0);
        shoppingCart.addProductToCart(product1);
        shoppingCart.addProductToCart(product2);
        assertEquals(1, shoppingCart.getProducts().size());
        assertEquals(5.0, shoppingCart.getProducts().get(0).getQuantity());
    }

    @Test
    public void testRemoveProductFromCart_ProductExists() {
        Product product = new Product(1, "Product1", 10.0, 2.0);
        shoppingCart.addProductToCart(product);
        shoppingCart.removeProductFromCart(product);
        assertEquals(0, shoppingCart.getProducts().size());
    }

    @Test
    public void testRemoveProductFromCart_ProductDoesNotExist() {
        Product product = new Product(1, "Product1", 10.0, 2.0);
        assertThrows(ProductNotFoundException.class, () -> shoppingCart.removeProductFromCart(product));
    }

    @Test
    public void testGetCartTotalPrice() {
        Product product1 = new Product(1, "Product1", 10.0, 2.0);
        Product product2 = new Product(2, "Product2", 20.0, 1.0);
        shoppingCart.addProductToCart(product1);
        shoppingCart.addProductToCart(product2);
        assertEquals(40.0, shoppingCart.getCartTotalPrice());
    }
}
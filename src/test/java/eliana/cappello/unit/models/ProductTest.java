package eliana.cappello.unit.models;

import eliana.cappello.models.Product;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

import java.lang.reflect.Field;

public class ProductTest {

    final Product product = new Product("testTitle", 12, 123.23, "dummy");

    @Test
    @DisplayName("Test Get Title")
    public void getTitleTest() throws NoSuchFieldException, IllegalAccessException {
        final Field field = this.product.getClass().getDeclaredField("title");
        field.setAccessible(true);
        field.set(this.product, "Test get title");

        Assertions.assertEquals("Test get title", this.product.getTitle(), "Field title wasn't retrieved properly");
    }

    @Test
    @DisplayName("Test Set Title")
    public void setTitleTest() throws NoSuchFieldException, IllegalAccessException {
        this.product.setTitle("Test set title");

        final Field field = this.product.getClass().getDeclaredField("title");
        field.setAccessible(true);

        Assertions.assertEquals("Test set title", field.get(this.product), "Field title didn't match");
    }

    @Test
    @DisplayName("Test Get kcal_per_100g")
    public void getKcalTest() throws NoSuchFieldException, IllegalAccessException {
        final Field field = this.product.getClass().getDeclaredField("kcal_per_100g");
        field.setAccessible(true);
        field.set(this.product, 10);

        Assertions.assertEquals(10, this.product.getKcal_per_100g(), "Field kcal_per_100g wasn't retrieved properly");
    }

    @Test
    @DisplayName("Test Set kcal_per_100g")
    public void setKcalTest() throws NoSuchFieldException, IllegalAccessException {
        this.product.setKcal_per_100g(100);

        final Field field = this.product.getClass().getDeclaredField("kcal_per_100g");
        field.setAccessible(true);

        Assertions.assertEquals(100, field.get(this.product), "Field kcal_per_100g didn't match");
    }

    @Test
    @DisplayName("Test Get unit_price")
    public void getUnitPriceTest() throws NoSuchFieldException, IllegalAccessException {
        final Field field = this.product.getClass().getDeclaredField("unit_price");
        field.setAccessible(true);
        field.set(this.product, 10.77);

        Assertions.assertEquals(10.77, this.product.getUnit_price(), "Field unit_price wasn't retrieved properly");
    }

    @Test
    @DisplayName("Test Set unit_price")
    public void setUnitPriceTest() throws NoSuchFieldException, IllegalAccessException {
        this.product.setUnit_price(1.89);

        final Field field = this.product.getClass().getDeclaredField("unit_price");
        field.setAccessible(true);

        Assertions.assertEquals(1.89, field.get(this.product), "Field unit_price didn't match");
    }

    @Test
    @DisplayName("Test Get description")
    public void getDescriptionTest() throws NoSuchFieldException, IllegalAccessException {
        final Field field = this.product.getClass().getDeclaredField("description");
        field.setAccessible(true);
        field.set(this.product, "Test get description");

        Assertions.assertEquals("Test get description", this.product.getDescription(), "Field description wasn't retrieved properly");
    }

    @Test
    @DisplayName("Test Set description")
    public void setDescriptionTest() throws NoSuchFieldException, IllegalAccessException {
        this.product.setDescription("Test set description");

        final Field field = this.product.getClass().getDeclaredField("description");
        field.setAccessible(true);

        Assertions.assertEquals("Test set description", field.get(this.product), "Field description didn't match");
    }
}

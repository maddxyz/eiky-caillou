package com.javapro.eiky;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import com.javapro.eiky.Models.others.Barcode;
import com.javapro.eiky.Models.others.Nutriments;
import com.javapro.eiky.Models.product.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EikyApplicationTests {

    @Autowired
    private APIClient apiClient;
    private Barcode barcode;

    @Before
    public void setUp(){
        this.barcode = this.apiClient.fetchProduct("3029330003533");
    }
    @Test
    public void testAPIClient(){
        assertNotNull(this.barcode);
        assertEquals(this.barcode.getCode(),"3029330003533");
        assertNotNull(this.barcode.getProduct());
        assertNotNull(this.barcode.getProduct().getNutriments());
    }
    @Test
    public void testProductInfo(){
        Product product = this.barcode.getProduct();
        assertEquals(product.getQuantity(),"730 g (12 Tranches)");
        assertEquals(product.getProduct_name(),"Crousti Moelleux Complet");
    }
    @Test
    public void testNutrimentsValues(){
        Nutriments nutriments = this.barcode.getProduct().getNutriments();
        assertEquals(nutriments.getEnergy_100g(),1110,0.05);
        assertEquals(nutriments.getSugars_100g(),7.2,0.05);
        assertEquals(nutriments.getSalt_100g(),1.2,0.05);
        assertEquals(nutriments.getFiber_100g(),5.5,0.05);
        assertEquals(nutriments.getProteins_100g(),8.9,0.05);
        assertEquals(nutriments.getSaturated_fat_100g(),0.4,0.05);
    }

    @Test
    public void testProductResults(){
        Product product = this.barcode.getProduct();
        assertEquals(product.getNutritional_score(),-6);
        assertEquals(product.getFlaws().size(),0);
        assertEquals(product.getColor(),"Green");
        assertEquals(product.getClasse(),"Trop bon");
        assertTrue(product.getQualities().contains("Densité énergétique (3)"));
        assertTrue(product.getQualities().contains("Graisses saturées (0)"));
        assertTrue(product.getQualities().contains("Sodium1 (0)"));
        assertTrue(product.getQualities().contains("Sucres simples (1)"));
        assertTrue(product.getQualities().contains("Fibres (5)"));
        assertTrue(product.getQualities().contains("Proteins (5)"));
    }

}

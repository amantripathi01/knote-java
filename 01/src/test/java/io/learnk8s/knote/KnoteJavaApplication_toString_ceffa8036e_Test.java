package io.learnk8s.knote;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class KnoteJavaApplication_toString_ceffa8036e_Test {

    @Test
    public void testToString() {
        KnoteJavaApplication knoteJavaApplication = new KnoteJavaApplication();
        knoteJavaApplication.setDescription("Test Description");
        String result = knoteJavaApplication.toString();
        assertEquals("Test Description", result);
    }

    @Test
    public void testToStringWithNullDescription() {
        KnoteJavaApplication knoteJavaApplication = new KnoteJavaApplication();
        knoteJavaApplication.setDescription(null);
        String result = knoteJavaApplication.toString();
        assertEquals(null, result);
    }
}

package io.learnk8s.knote;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;

public class KnoteJavaApplication_getUploadDir_caabfc00fd_Test {

    @InjectMocks
    KnoteJavaApplication knoteJavaApplication;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        knoteJavaApplication = new KnoteJavaApplication();
    }

    @Test
    public void testGetUploadDirSuccess() {
        String expectedUploadDir = "/uploads";
        knoteJavaApplication.setUploadDir(expectedUploadDir);
        String actualUploadDir = knoteJavaApplication.getUploadDir();

        assertEquals(expectedUploadDir, actualUploadDir, "Upload directory should be /uploads");
    }

    @Test
    public void testGetUploadDirFailure() {
        String expectedUploadDir = "/uploads";
        knoteJavaApplication.setUploadDir("/otherdir");
        String actualUploadDir = knoteJavaApplication.getUploadDir();

        assertNotEquals(expectedUploadDir, actualUploadDir, "Upload directory should not be /uploads");
    }
}

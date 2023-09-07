package io.learnk8s.knote;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class KnoteJavaApplication_uploadImage_e097a52780_Test {

    @Mock
    private Properties properties;

    @InjectMocks
    private KnoteJavaApplication knoteJavaApplication;

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelMap();
    }

    @Test
    public void testUploadImageWhenUploadDirDoesNotExist() throws Exception {
        MultipartFile file = new MockMultipartFile("file", "hello.png", "image/png", "some image".getBytes());
        String description = "Test description";
        when(properties.getUploadDir()).thenReturn("/uploads/");
        File fileMock = mock(File.class);
        when(fileMock.exists()).thenReturn(false);
        knoteJavaApplication.uploadImage(file, description, model);
        verify(fileMock, times(1)).mkdir();
        String expectedDescription = description + " ![](/uploads/" + file.getOriginalFilename() + ")";
        assertEquals(expectedDescription, model.getAttribute("description"));
    }

    @Test
    public void testUploadImageWhenUploadDirExists() throws Exception {
        MultipartFile file = new MockMultipartFile("file", "hello.png", "image/png", "some image".getBytes());
        String description = "Test description";
        when(properties.getUploadDir()).thenReturn("/uploads/");
        File fileMock = mock(File.class);
        when(fileMock.exists()).thenReturn(true);
        knoteJavaApplication.uploadImage(file, description, model);
        verify(fileMock, times(0)).mkdir();
        String expectedDescription = description + " ![](/uploads/" + file.getOriginalFilename() + ")";
        assertEquals(expectedDescription, model.getAttribute("description"));
    }

    @Test
    public void testUploadImageThrowsException() throws Exception {
        MultipartFile file = new MockMultipartFile("file", "hello.png", "image/png", "some image".getBytes());
        String description = "Test description";
        when(properties.getUploadDir()).thenReturn("/uploads/");
        File fileMock = mock(File.class);
        when(fileMock.exists()).thenReturn(true);
        doThrow(new IOException()).when(file).transferTo(any(File.class));
        assertThrows(Exception.class, () -> knoteJavaApplication.uploadImage(file, description, model));
    }
}

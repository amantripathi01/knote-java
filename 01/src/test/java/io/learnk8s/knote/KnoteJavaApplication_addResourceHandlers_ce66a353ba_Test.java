package io.learnk8s.knote;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.resource.PathResourceResolver;
import java.util.Properties;
import static org.mockito.Mockito.*;

public class KnoteJavaApplication_addResourceHandlers_ce66a353ba_Test {

    @InjectMocks
    KnoteJavaApplication knoteJavaApplication;

    @Mock
    ResourceHandlerRegistry registry;

    @Mock
    PathResourceResolver pathResourceResolver;

    @Mock
    Properties properties;

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddResourceHandlers() {
        String uploadDir = "/uploads";
        when(properties.getProperty("uploadDir")).thenReturn(uploadDir);

        knoteJavaApplication.addResourceHandlers(registry);

        verify(registry, times(1)).addResourceHandler("/uploads/**");
        verify(registry, times(1)).addResourceLocations("file:" + uploadDir);
        verify(registry, times(1)).setCachePeriod(3600);
        verify(registry, times(1)).resourceChain(true);
        verify(registry, times(1)).addResolver(pathResourceResolver);
    }

    @Test(expected = RuntimeException.class)
    public void testAddResourceHandlersWithException() {
        when(properties.getProperty("uploadDir")).thenThrow(new RuntimeException());

        knoteJavaApplication.addResourceHandlers(registry);
    }
}

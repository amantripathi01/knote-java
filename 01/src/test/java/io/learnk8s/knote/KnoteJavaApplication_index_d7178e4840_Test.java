package io.learnk8s.knote;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class KnoteJavaApplication_index_d7178e4840_Test {

    @Mock
    private Model model;

    @Mock
    private NoteService noteService;

    @InjectMocks
    private KnoteJavaApplication knoteJavaApplication;

    @Test
    public void testIndex_Success() {
        when(noteService.getAllNotes(model)).thenReturn("All notes");
        String result = knoteJavaApplication.index(model);
        verify(noteService).getAllNotes(model);
        assertEquals("index", result);
    }

    @Test
    public void testIndex_Failure() {
        when(noteService.getAllNotes(model)).thenReturn(null);
        String result = knoteJavaApplication.index(model);
        verify(noteService).getAllNotes(model);
        assertEquals("index", result);
    }
}

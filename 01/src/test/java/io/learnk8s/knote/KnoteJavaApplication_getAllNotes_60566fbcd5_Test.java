package io.learnk8s.knote;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;

public class KnoteJavaApplication_getAllNotes_60566fbcd5_Test {

    @Mock
    private NotesRepository notesRepository;

    @Mock
    private Model model;

    @InjectMocks
    private KnoteJavaApplication knoteJavaApplication;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllNotes() {
        Note note1 = new Note();
        Note note2 = new Note();
        List<Note> notes = Arrays.asList(note1, note2);

        when(notesRepository.findAll()).thenReturn(notes);

        knoteJavaApplication.getAllNotes(model);

        verify(notesRepository, times(1)).findAll();
        verify(model, times(1)).addAttribute("notes", Arrays.asList(note2, note1));
    }

    @Test
    public void testGetAllNotesEmpty() {
        List<Note> notes = Arrays.asList();

        when(notesRepository.findAll()).thenReturn(notes);

        knoteJavaApplication.getAllNotes(model);

        verify(notesRepository, times(1)).findAll();
        verify(model, times(1)).addAttribute("notes", notes);
    }
}

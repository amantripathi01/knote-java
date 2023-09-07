package io.learnk8s.knote;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class KnoteJavaApplication_saveNote_5180763a9e_Test {

    @InjectMocks
    private KnoteJavaApplication knoteJavaApplication;

    @Mock
    private Parser parser;

    @Mock
    private HtmlRenderer renderer;

    @Mock
    private NotesRepository notesRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        knoteJavaApplication = new KnoteJavaApplication();
        parser = mock(Parser.class);
        renderer = mock(HtmlRenderer.class);
        notesRepository = mock(NotesRepository.class);
    }

    @Test
    public void testSaveNote_ValidDescription() {
        String description = "Valid Description";
        Model model = new ModelMap();

        Node document = mock(Node.class);
        when(parser.parse(description.trim())).thenReturn(document);

        String html = "HTML Content";
        when(renderer.render(document)).thenReturn(html);

        knoteJavaApplication.saveNote(description, model);

        verify(notesRepository).save(any(Note.class));
        verify(model).addAttribute("description", "");
    }

    @Test
    public void testSaveNote_EmptyDescription() {
        String description = "";
        Model model = new ModelMap();

        knoteJavaApplication.saveNote(description, model);

        verify(notesRepository, never()).save(any(Note.class));
        verify(model, never()).addAttribute(anyString(), anyString());
    }
}

package DSI_Graph_Main_Controlers.Editors;


import java.util.Collection;
import java.util.Collections;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.GenericStyledArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.model.Paragraph;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;
//import org.reactfx.collection.ListModification;

public class TSLEditor {

    private static final String[] KEYWORDS = new String[] {
            "TestConnection", "TestResistor", "TestDiode", "Switch", "End"
    };

    private static final String KEYWORD_PATTERN = "\\b(" + String.join("|", KEYWORDS) + ")\\b";
    private static final String PAREN_PATTERN = "\\(|\\)";
    private static final String BRACE_PATTERN = "\\{|\\}";
    private static final String BRACKET_PATTERN = "\\[|\\]";
    private static final String SEMICOLON_PATTERN = "\\;";
    private static final String STRING_PATTERN = "\"([^\"\\\\]|\\\\.)*\"";
    private static final String COMMENT_PATTERN = "//[^\n]*" + "|" + "/\\*(.|\\R)*?\\*/"   // for whole text processing (text blocks)
            + "|" + "/\\*[^\\v]*" + "|" + "^\\h*\\*([^\\v]*|/)";  // for visible paragraph processing (line by line)

    private static final Pattern PATTERN = Pattern.compile(
            "(?<KEYWORD>" + KEYWORD_PATTERN + ")"
                    + "|(?<PAREN>" + PAREN_PATTERN + ")"
                    + "|(?<BRACE>" + BRACE_PATTERN + ")"
                    + "|(?<BRACKET>" + BRACKET_PATTERN + ")"
                    + "|(?<SEMICOLON>" + SEMICOLON_PATTERN + ")"
                    + "|(?<STRING>" + STRING_PATTERN + ")"
                    + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
    );

    private static final String sampleCode = String.join("\n", new String[] {
            "TestConnection('connectionName' ,\"Pin A\", \"Pin B\");",
            "End."

    });




    CodeArea codeArea = new CodeArea();

    public VirtualizedScrollPane getEditor() {


        // add line numbers to the left of area
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
        codeArea.setContextMenu( new TSLEditor.DefaultContextMenu() );

//        codeArea.getVisibleParagraphs().addModificationObserver
//                (
//                        new TSLEditor.VisibleParagraphStyler<>( codeArea, this::computeHighlighting )
//                );

        // auto-indent: insert previous line's indents on enter
        final Pattern whiteSpace = Pattern.compile( "^\\s+" );
        codeArea.addEventHandler( KeyEvent.KEY_PRESSED, KE ->
        {
            if ( KE.getCode() == KeyCode.ENTER ) {
                int caretPosition = codeArea.getCaretPosition();
                int currentParagraph = codeArea.getCurrentParagraph();
                Matcher m0 = whiteSpace.matcher( codeArea.getParagraph( currentParagraph-1 ).getSegments().get( 0 ) );
                if ( m0.find() ) Platform.runLater( () -> codeArea.insertText( caretPosition, m0.group() ) );
            }
        });


        codeArea.replaceText(0, 0, sampleCode);


        VirtualizedScrollPane virtualizedScrollPane = new VirtualizedScrollPane<>(codeArea);
        virtualizedScrollPane.getStylesheets().add(TSLEditor.class.getResource("java-keywords.css").toExternalForm());

        return virtualizedScrollPane;
    }

    public CodeArea getCodeArea() {
        return codeArea;
    }

    private StyleSpans<Collection<String>> computeHighlighting(String text) {
        Matcher matcher = PATTERN.matcher(text);
        int lastKwEnd = 0;
        StyleSpansBuilder<Collection<String>> spansBuilder
                = new StyleSpansBuilder<>();
        while(matcher.find()) {
            String styleClass =
                    matcher.group("KEYWORD") != null ? "keyword" :
                            matcher.group("PAREN") != null ? "paren" :
                                    matcher.group("BRACE") != null ? "brace" :
                                            matcher.group("BRACKET") != null ? "bracket" :
                                                    matcher.group("SEMICOLON") != null ? "semicolon" :
                                                            matcher.group("STRING") != null ? "string" :
                                                                    matcher.group("COMMENT") != null ? "comment" :
                                                                            null; /* never happens */ assert styleClass != null;
            spansBuilder.add(Collections.emptyList(), matcher.start() - lastKwEnd);
            spansBuilder.add(Collections.singleton(styleClass), matcher.end() - matcher.start());
            lastKwEnd = matcher.end();
        }
        spansBuilder.add(Collections.emptyList(), text.length() - lastKwEnd);
        return spansBuilder.create();
    }

//    private class VisibleParagraphStyler<PS, SEG, S> implements Consumer<ListModification<? extends Paragraph<PS, SEG, S>>>
//    {
//        private final GenericStyledArea<PS, SEG, S> area;
//        private final Function<String,StyleSpans<S>> computeStyles;
//        private int prevParagraph, prevTextLength;
//
//        public VisibleParagraphStyler( GenericStyledArea<PS, SEG, S> area, Function<String,StyleSpans<S>> computeStyles )
//        {
//            this.computeStyles = computeStyles;
//            this.area = area;
//        }
//
//        @Override
//        public void accept( ListModification<? extends Paragraph<PS, SEG, S>> lm )
//        {
//            if ( lm.getAddedSize() > 0 )
//            {
//                int paragraph = Math.min( area.firstVisibleParToAllParIndex() + lm.getFrom(), area.getParagraphs().size()-1 );
//                String text = area.getText( paragraph, 0, paragraph, area.getParagraphLength( paragraph ) );
//
//                if ( paragraph != prevParagraph || text.length() != prevTextLength )
//                {
//                    int startPos = area.getAbsolutePosition( paragraph, 0 );
//                    Platform.runLater( () -> area.setStyleSpans( startPos, computeStyles.apply( text ) ) );
//                    prevTextLength = text.length();
//                    prevParagraph = paragraph;
//                }
//            }
//        }
//    }

    private class DefaultContextMenu extends ContextMenu
    {
        private MenuItem fold, unfold, print;

        public DefaultContextMenu()
        {
            fold = new MenuItem( "Zwiñ zaznaczony tekst" );
            fold.setOnAction( AE -> { hide(); fold(); } );

            unfold = new MenuItem( "Rozwiñ od kursora" );
            unfold.setOnAction( AE -> { hide(); unfold(); } );

            print = new MenuItem( "Drukuj" );
            print.setOnAction( AE -> { hide(); print(); } );

            getItems().addAll( fold, unfold  );
        }

        /**
         * Folds multiple lines of selected text, only showing the first line and hiding the rest.
         */
        private void fold() {
            ((CodeArea) getOwnerNode()).foldSelectedParagraphs();
        }

        /**
         * Unfold the CURRENT line/paragraph if it has a fold.
         */
        private void unfold() {
            CodeArea area = (CodeArea) getOwnerNode();
            area.unfoldParagraphs( area.getCurrentParagraph() );
        }

        private void print() {
            System.out.println( ((CodeArea) getOwnerNode()).getText() );
        }
    }
}
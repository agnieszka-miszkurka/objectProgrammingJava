package CV;

import java.io.PrintStream;

public class Paragraph {
    String content;
    public Paragraph(String text) {
        this.content = text;
    }

    Paragraph setContent(String newContent) {
        content = newContent;
        return this;
    }

    void writeHTML(PrintStream out){
        out.printf("<p>" + content + "</p>\n");
    }
}

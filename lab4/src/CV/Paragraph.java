package CV;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlValue;
import java.io.PrintStream;


public class Paragraph {
    @XmlValue
    String content;
    public Paragraph(String text) {
        this.content = text;
    }

    Paragraph(){ }
    Paragraph setContent(String newContent) {
        content = newContent;
        return this;
    }

    void writeHTML(PrintStream out){
        out.printf("<p>\n" + content + "\n</p>\n");
    }

}

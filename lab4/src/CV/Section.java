package CV;

import javax.xml.bind.annotation.XmlElement;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElements;


public class Section {
    @XmlAttribute
    String title;

    @XmlElements(value= {
            @XmlElement(name = "paragraph", type = Paragraph.class),
            @XmlElement(name = "paragraph-with-list", type = ParagraphWithList.class)
    })
    List<Paragraph> paragraphs = new ArrayList<>() ;

    Section(String title){
        this.title = title;
    }

    Section setTitle(String title){
        this.title = title;
        return this;
    }

    Section addParagraph(String paragraphText){
        Paragraph newParagraph = new Paragraph(paragraphText);
        paragraphs.add(newParagraph);
        return this;
    }

    Section addParagraph(Paragraph p){
        paragraphs.add(p);
        return this;
    }

    Section addParagraph(ParagraphWithList p){
        paragraphs.add(p);
        return this;
    }

    void writeHTML(PrintStream out){
        if (paragraphs.isEmpty())
            return;

        out.printf("<section>");
        out.printf("\n<h2> %s </h2>\n",title);
        for (Paragraph p : paragraphs) {
            p.writeHTML(out);
        }
        out.printf("</section>\n");
    }
}

package CV;

import java.io.PrintStream;
import javax.xml.bind.annotation.XmlAttribute;

public class Photo {

    @XmlAttribute
    private String url;

    Photo(String url){
        this.url =url;
    }

    void writeHTML(PrintStream out){
        out.printf("<img src=\"%s\" alt=\"Smiley face\" height=\"42\" width=\"42\"/>\n",url);
    }
}
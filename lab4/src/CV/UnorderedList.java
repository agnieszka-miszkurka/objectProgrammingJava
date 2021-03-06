package CV;

import javax.xml.bind.annotation.XmlElement;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class UnorderedList {
    @XmlElement
    List<ListItem> list;

    UnorderedList(){
        list = new ArrayList<>();
    }

    void addItem(ListItem item){
        list.add(item);
    }

    void writeHTML(PrintStream out) {
        out.printf("<ul>\n");
        for (ListItem i : list) {
            i.writeHTML(out);
        }
        out.printf("</ul>\n");
    }
}

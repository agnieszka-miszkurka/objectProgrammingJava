package CV;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class UnorderedList {
    List<ListItem> list;

    UnorderedList(){
        list = new ArrayList<>();
    }

    void addItem(ListItem item){
        list.add(item);
    }

    void writeHTML(PrintStream out) {
        for (ListItem i : list) {
            i.writeHTML(out);
        }
    }
}

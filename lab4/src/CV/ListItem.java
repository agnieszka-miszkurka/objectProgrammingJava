package CV;

import java.io.PrintStream;

public class ListItem {
    String content;

    ListItem(String content) {
        this.content = content;
    }

    void writeHTML(PrintStream out) {
        out.printf("%s \n", content);
    }
}
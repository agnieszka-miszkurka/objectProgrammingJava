package CV;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Document {
    String title;
    List<Section> sections = new ArrayList<>();
    Photo photo;

    public Document(String title){
        this.title = title;
    }

    Document setTitle(String title){
        this.title = title;
        return this;
    }

    Document setPhoto(String photoUrl){
        photo = new Photo(photoUrl);
        return this;
    }

    Section addSection(String sectionTitle){
        // utwórz sekcję o danym tytule i dodaj do sections
        Section newSection = new Section(sectionTitle);
        newSection.setTitle(sectionTitle);
        sections.add(newSection);
        return newSection;
    }

    Document addSection(Section s){
        sections.add(s);
        return this;
    }


    void writeHTML(PrintStream out){
        // zapisz niezbędne znaczniki HTML
        // dodaj tytuł i obrazek
        // dla każdej sekcji wywołaj section.writeHTML(out)
        out.printf("<h1> %s </h1>",title);
        photo.writeHTML(out);
        for (Section s : sections) {
            s.writeHTML(out);
        }
    }
}

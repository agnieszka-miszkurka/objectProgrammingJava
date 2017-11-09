package CV;

public class ParagraphWithList extends Paragraph {

    UnorderedList unorderedList;

    public ParagraphWithList(String text) {
        super(text);
        unorderedList = new UnorderedList();
    }

    @Override
    Paragraph setContent(String newContent) {
        content = newContent;
        return this;
    }


}

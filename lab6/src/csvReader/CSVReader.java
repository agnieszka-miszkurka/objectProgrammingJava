package csvReader;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class CSVReader {
    BufferedReader reader;
    String delimiter;
    boolean hasHeader;

    // nazwy kolumn w takiej kolejności, jak w pliku
    List<String> columnLabels = new ArrayList<>();
    // odwzorowanie: nazwa kolumny -> numer kolumny
    Map<String,Integer> columnLabelsToInt = new HashMap<>();

    /**
     *
     * @param filename - nazwa pliku
     * @param delimiter - separator pól
     * @param hasHeader - czy plik ma wiersz nagłówkowy
     */

    public CSVReader(Reader reader, String delimiter, boolean hasHeader) throws IOException {
        this.reader = new BufferedReader(reader);
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
        if(hasHeader)parseHeader();
    }

    public CSVReader(String filename,String delimiter,boolean hasHeader) throws IOException {
        this(new FileReader(filename), delimiter, hasHeader);
    }

    public CSVReader(String filename,String delimiter) throws IOException {
        this(filename, delimiter, true);
    }

    public CSVReader(String filename) throws IOException {
        this(filename, ",",true);
    }

    //...

    String[]current;
    boolean next(){
        // czyta następny wiersz, dzieli na elementy i przypisuje do current
        //
        return false;
    }

    void parseHeader() throws IOException {
        // wczytaj wiersz
        String line = reader.readLine();
        if (line == null) {
            return;
        }
        // podziel na pola
        String[] header = line.split(delimiter);
        // przetwarzaj dane w wierszu

        for (int i = 0; i < header.length; i++) {
            // dodaj nazwy kolumn do columnLabels i numery do columnLabelsToInt
            columnLabels.add(header[i]);
            columnLabelsToInt.put(header[i],i);
        }
    }

    List<String> getColumnLabels(){
        return columnLabels;
    }

    int getRecordLength() {

    }

    public static void main(String[] args) throws IOException {
        CSVReader reader = new CSVReader("no-header.csv",",",true);
        while(reader.next()){
            int id = reader.getInt("PassengerId");
            String name = reader.get("Name");
            double fare = reader.getDouble("Fare");

            System.out.printf(Locale.US,"%d %s %d",id, name, fare);
        }

    }
}
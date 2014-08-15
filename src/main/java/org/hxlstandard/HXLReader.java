package org.hxlstandard;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

/**
 * Class to read HXL data from a CSV file.
 *
 * @author David Megginson
 */
public class HXLReader implements Iterable {

    private Reader input;

    private CSVReader csv_reader;

    private ArrayList<HXLColumn> columns;

    private HashMap<Integer,HXLColumn> columnMap;

    private HXLIterator hxl_iterator;

    /**
     * Create a new HXL CSV data reader.
     */
    public HXLReader(Reader input) {
        // TODO can add extra params for custom separators, etc.
        this.input = input;
        csv_reader = new CSVReader(input);
    }

    /**
     * Read the next row of HXL data.
     *
     * @return A row of HXL data, or null if the CSV file is finished.
     * @exception IOException if there is an error reading or parsing
     * the HXL data from the CSV source.
     */
    public HXLRow read() throws IOException {
        
        return null;
    }

    /**
     * Get an iterator for rows of HXL data.
     *
     * <p>It will always be the same iterator for any given HXLReader
     * object, so once it's exhausted, there's no way to restart.</p>
     *
     * <p>This method is syntactic sugar that allows you to do
     * something like this:</p>
     *
     * <pre>
     * for (HXLRow row : read) {
     *   // do something with each row
     * }
     * </pre>
     *
     * <p>Instead of the equivalent</p>
     *
     * HXLRow row = reader.read();
     * while (row != null) {
     *   // do something with each row
     *   row = reader.read();
     * }
     * </pre>
     *
     * <p><b>Warning:</b> because {@link #read()} can throw a {@link
     * IOException} but {@link Iterator#next()} does not allow any
     * exceptions, the iterator will wrap any exceptions in a generic
     * {@link RuntimeException}. If you are using the iterator, be
     * prepared to catch that exception.</p>
     *
     * @return An iterator object.
     */
    @Override
    public Iterator<HXLRow> iterator() {
        if (hxl_iterator == null) {
            hxl_iterator = new HXLIterator();
        }
        return hxl_iterator;
    }

    public List<HXLColumn> getColumns() throws IOException {
        if (columns == null) {
            find_columns();
        }
        return columns;
    }

    /**
     * Seek forward to the row of HXL headers.
     */
    private void find_columns() throws IOException {
       String fields[] = csv_reader.readNext();
       while (fields != null) {
           if (is_header_row(fields)) {
               make_columns(fields);
               return;
           }
           fields = csv_reader.readNext();
       }
       throw new IOException("HXL header row not found.");
    }

    private void make_columns(String fields[]) {
        int n = 0;
        columns = new ArrayList<HXLColumn>();
        columnMap = new HashMap<Integer,HXLColumn>();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].length() > 0) {
                HXLColumn column = new HXLColumn(fields[i], null, n++, i);
                columns.add(column);
                columnMap.put(i, column);
            }
        }
    }

    /**
     * Test if a raw CSV row consists of HXL headers.
     */
    private boolean is_header_row(String fields[]) {
        boolean seen_tag = false;
        for (String field : fields) {
            if (field.length() > 0) {
                if (is_hxl_tag(field)) {
                    seen_tag = true;
                } else {
                    return false;
                }
            }
        }
        return seen_tag;
    }

    /**
     * Test if a string is a HXL tag.
     */
    private boolean is_hxl_tag(String field) {
        // TODO proper test
        return field.startsWith("#");
    }


    /**
     * Inner class to implement an iterator for HXLRow objects.
     *
     * Exceptions will be wrapped in {@link RuntimeException}. The
     * iterator may sometimes have to read one row ahead, if the
     * client invokes {@link #hasNext()}.
     */
    private class HXLIterator implements Iterator<HXLRow> {

        private HXLRow next_row = null;

        @Override
        public boolean hasNext() {
            if (next_row == null) {
                next_row = next();
            }
            return (next_row == null);
        }

        @Override
        public HXLRow next() {
            HXLRow row;
            if (next_row != null) {
                row = next_row;
                next_row = null;
            } else {
                try {
                    row = read();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            return null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

}
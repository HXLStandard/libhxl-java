package org.hxlstandard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * A row of data in a HXL dataset.
 *
 * <p>The row is equivalent to a single entity or object, and its
 * values ({@link HXLValue}) together make up a collection of
 * information about that object. There is also sequence information
 * about where this row falls in the logical and source datasets.</p>
 *
 * <p>During HXL parsing, the {@link HXLReader} class returns a series
 * of these objects, one for each logical row in the original
 * dataset.</p>
 *
 * <p>Example:</p>
 *
 * <pre>
 * HXLRow row = hxl.read();
 * for (HXLValue value : row) {
 *   // do something with the value
 * }
 * </pre>
 *
 * @author David Megginson
 */
public class HXLRow implements Iterable<HXLValue> {

    private int rowNumber;

    private int sourceRowNumber;

    private ArrayList<HXLValue> values = new ArrayList<HXLValue>();

    /**
     * Create a new row of HXL data.
     *
     * @param rowNumber The logical (HXL) row number, zero-based.
     * @param sourceRowNumber The original source row number, zero-based.
     */
    public HXLRow(int rowNumber, int sourceRowNumber) {
        init(rowNumber, sourceRowNumber);
    }

    /**
     * Reinitialise the row with new values.
     *
     * This method is useful for reusing objects.
     *
     * @param rowNumber The logical (HXL) row number, zero-based.
     * @param sourceRowNumber The original source row number, zero-based.
     */
    protected void init(int rowNumber, int sourceRowNumber) {
        values.clear();
        this.rowNumber = rowNumber;
        this.sourceRowNumber = sourceRowNumber;
    }

    /**
     * Get a modifiable list of this row's values.
     *
     * {@link HXLReader} and other builders can add HXLValue objects to this list.
     *
     * @return A list of type {@link HXLValue}
     */
    protected List<HXLValue> getValuesModifiable() {
        return values;
    }

    /**
     * Get a read-only list of this row's values.
     *
     * @return An unmodifiable list of type {@link HXLValue}.
     */
    public List<HXLValue> getValues() {
        return Collections.unmodifiableList(values);
    }

    /**
     * Get a read-only iterator over the values.
     *
     * @return A read-only value iterator.
     */
    public Iterator<HXLValue> iterator() {
        return getValues().iterator();
    }

    /**
     * Get the logical (HXL) row number.
     *
     * @return The row number, zero-based.
     */
    public int getRowNumber() {
        return rowNumber;
    }

    /**
     * Get the original row number in the source data.
     *
     * This value can be useful for error reporting.
     *
     * @return The original row number, zero-based.
     */
    public int getSourceRowNumber() {
        return sourceRowNumber;
    }

}
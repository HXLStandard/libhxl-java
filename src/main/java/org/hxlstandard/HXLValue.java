package org.hxlstandard;

/**
 * A single value in a HXL dataset.
 *
 * <p>This class corresponds to a single cell in a spreadsheet, at the
 * intersection of a row and a column.  The associated {@link
 * HXLColumn} object provides the column metadata, including the HXL
 * tag for the value. There is also information about this value's
 * position in the table (row and column).</p>
 *
 * <p>During HXL parsing, each {@link HXLRow} consists of a series of
 * these values; together, the values provide the information about
 * the thing being described.</p>
 *
 * <p>Example:</p>
 *
 * <pre>
 * HXLValue value = row.getValue(0);
 * System.out.println("The HXL tag is " + value.getTag());
 * System.out.println("The value is " + value.getContent());
 * </pre>
 *
 * @author David Megginson
 */
public class HXLValue {

    private HXLColumn column;

    private String content;

    private int rowNumber;

    private int sourceRowNumber;

    /**
     * Create a new value object.
     *
     * @param column The column metadata for this value.
     * @param content A string representation of the value.
     * @param rowNumber The logical (HXL) row number, zero-based.
     * @param sourceRowNumber The row number in the original source,
     * zero-based, or -1 if unspecified.
     */
    public HXLValue(HXLColumn column, String content, int rowNumber, int sourceRowNumber) {
        init(column, content, rowNumber, sourceRowNumber);
    }

    /**
     * Reinitialize an existing value object.
     *
     * This method is potentially useful for reusing objects.
     *
     * @param column The column metadata for this value.
     * @param content A string representation of the value.
     * @param rowNumber The logical (HXL) row number, zero-based.
     * @param sourceRowNumber The row number in the original source,
     * zero-based, or -1 if unspecified.
     */
    protected void init(HXLColumn column, String content, int rowNumber, int sourceRowNumber) {
        this.column = column;
        this.content = content;
        this.rowNumber = rowNumber;
        this.sourceRowNumber = sourceRowNumber;
    }

    /**
     * Get the column metadata associated with this value.
     *
     * This metadata includes the column number and the HXL tag.
     *
     * @return the column metadata.
     */
    public HXLColumn getColumn() {
        return column;
    }

    /**
     * Get the content of the value.
     *
     * @return The value content as a string.
     */
    public String getContent() {
        return content;
    }

    /**
     * Get the row number for this value.
     *
     * @return The zero-based row number.
     */
    public int getRowNumber() {
        return rowNumber;
    }

    /**
     * Get the original source row number for this value.
     *
     * This information can be useful for error reporting.
     *
     * @return The zero-based original source row number, or -1 if
     * unspecified.
     */
    public int getSourceRowNumber() {
        return sourceRowNumber;
    }

    /**
     * Get the HXL tag from the column metadata.
     *
     * @return A HXL tag as a string (including the leading "#").
     * @see HXLColumn#getTag
     */
    public String getTag() {
        return getColumn().getTag();
    }

    /**
     * Get the language code from the column metadata.
     *
     * @return A two-character ISO 639 language code.
     * @see HXLColumn#getLang
     */
    public String getLang() {
        return getColumn().getLang();
    }

    /**
     * Get the column number from the column metadata.
     *
     * @return The logical (HXL) column number, zero-based.
     * @see HXLColumn#getColumnNumber
     */
    public int getColumnNumber() {
        return getColumn().getColumnNumber();
    }

    /**
     * Get the source column number from the column metadata.
     *
     * @return The original source column number, zero-based, or -1 if
     * unspecified.
     * @see HXLColumn#getSourceColumnNumber
     */
    public int getSourceColumnNumber() {
        return getColumn().getSourceColumnNumber();
    }

}

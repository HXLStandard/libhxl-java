package org.hxlstandard;

/**
 * A single value in a HXL dataset.
 *
 * @author David Megginson
 */
public class HXLValue {

    private HXLColumn column;

    private String content;

    private int row_number;

    private int source_row_number;

    /**
     * Create a new value object.
     *
     * @param column The column metadata for this value.
     * @param content A string representation of the value.
     * @param row_number The logical (HXL) row number, zero-based.
     * @param source_row_number The row number in the original source,
     * zero-based, or -1 if unspecified.
     */
    public HXLValue(HXLColumn column, String content, int row_number, int source_row_number) {
        init(column, content, row_number, source_row_number);
    }

    /**
     * Reinitialize an existing value object.
     *
     * This method is potentially useful for reusing objects.
     *
     * @param column The column metadata for this value.
     * @param content A string representation of the value.
     * @param row_number The logical (HXL) row number, zero-based.
     * @param source_row_number The row number in the original source,
     * zero-based, or -1 if unspecified.
     */
    public void init(HXLColumn column, String content, int row_number, int source_row_number) {
        this.column = column;
        this.content = content;
        this.row_number = row_number;
        this.source_row_number = source_row_number;
    }

    public HXLColumn getColumn() {
        return column;
    }

    public String getContent() {
        return content;
    }

    public int getRowNumber() {
        return row_number;
    }

    public int getSourceRowNumber() {
        return source_row_number;
    }

    /**
     * @see HXLColumn#getTag
     */
    public String getTag() {
        return getColumn().getTag();
    }

    /**
     * @see HXLColumn#getLang
     */
    public String getLang() {
        return getColumn().getLang();
    }

    /**
     * @see HXLColumn#getColumnNumber
     */
    public int getColumnNumber() {
        return getColumn().getColumnNumber();
    }

    /**
     * @see HXLColumn#getSourceColumnNumber
     */
    public int getSourceColumnNumber() {
        return getColumn().getSourceColumnNumber();
    }

}
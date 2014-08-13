package org.hxlstandard;

/**
 * Column metadata for a HXL dataset.
 *
 * This is a shared data object referenced from every {@link HXLValue}
 * object.
 *
 * @author David Megginson
 */
public class HXLColumn {

    private String tag;

    private String lang;

    private int column_number;

    private int source_column_number;

    /**
     * Create a new set of column metadata.
     *
     * @param tag The HXL hashtag (including the "#").
     * @param lang The ISO 639 language code, or null if unspecified.
     * @param column_number The logical (HXL) column number, zero-based.
     * @param source_column_number The column number in the source
     * data, zero-based, or -1 if unspecified.
     */
    public HXLColumn(String tag, String lang, int column_number, int source_column_number) {
        this.tag = tag.intern();
        this.lang = lang.intern();
        this.column_number = column_number;
        this.source_column_number = source_column_number;
    }

    /**
     * Get the HXL hashtag for the column.
     *
     * @return A string containing a HXL hashtag.
     */
    public String getTag() {
        return tag;
    }

    /**
     * Get the ISO 639 language code for the column.
     *
     * @return The language code, or null if unspecified.
     */
    public String getLang() {
        return lang;
    }

    /**
     * Get the logical (HXL) column number.
     *
     * @return The zero-based column number.
     */
    public int getColumnNumber() {
        return column_number;
    }

    /**
     * Get the original source column number.
     *
     * @return The zero-based source column number, or -1 if
     * unspecified.
     */
    public int getSourceColumnNumber() {
        return source_column_number;
    }

}
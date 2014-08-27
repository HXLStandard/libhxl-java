package org.hxlstandard;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

/**
 * Class to read HXL data from a CSV file.
 *
 * @author David Megginson
 */
public class HXLReader implements Iterable {

	private final Reader input;

	private final CSVReader csvReader;

	private ArrayList<HXLColumn> columns;

	private HashMap<Integer,HXLColumn> columnMap;

	private HXLIterator hxlIterator;

	private int rowNumber = -1;

	private int sourceRowNumber = -1;

	/**
	 * Create a new HXL CSV data reader.
	 */
	public HXLReader(final Reader input) {
		// TODO can add extra params for custom separators, etc.
		this.input = input;
		this.csvReader = new CSVReader(input);
	}

	/**
	 * Read the next row of HXL data.
	 *
	 * @return A row of HXL data, or null if the CSV file is finished.
	 * @exception IOException if there is an error reading or parsing
	 * the HXL data from the CSV source.
	 */
	public HXLRow read() throws IOException {
		if (this.columns == null) {
			this.findColumns();
		}

		final String fields[] = this.readRawRow();
		if (fields == null) {
			this.rowNumber = -1;
			return null;
		}

		final HXLRow row = new HXLRow(++this.rowNumber, this.sourceRowNumber);
		for (int i = 0; i < fields.length; i++) {
			final HXLColumn column = this.columnMap.get(i);
			if (column != null) {
				final HXLValue value = new HXLValue(column, fields[i], this.rowNumber, this.sourceRowNumber);
				row.getValuesModifiable().add(value);
			}
		}

		return row;
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
		if (this.hxlIterator == null) {
			this.hxlIterator = new HXLIterator();
		}
		return this.hxlIterator;
	}

	public List<HXLColumn> getColumns() throws IOException {
		if (this.columns == null) {
			this.findColumns();
		}
		return this.columns;
	}

	/**
	 * Seek forward to the row of HXL headers.
	 */
	private void findColumns() throws IOException {
		String fields[] = this.readRawRow();
		while (fields != null) {
			if (this.isHeaderRow(fields)) {
				this.makeColumns(fields);
				return;
			}
			fields = this.readRawRow();
		}
		throw new IOException("HXL header row not found.");
	}

	private void makeColumns(final String fields[]) {
		int n = 0;
		this.columns = new ArrayList<HXLColumn>();
		this.columnMap = new HashMap<Integer,HXLColumn>();
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].length() > 0) {
				final HXLColumn column = new HXLColumn(fields[i], null, n++, i);
				this.columns.add(column);
				this.columnMap.put(i, column);
			}
		}
	}

	/**
	 * Test if a raw CSV row consists of HXL headers.
	 */
	private boolean isHeaderRow(final String fields[]) {
		boolean seenTag = false;
		for (final String field : fields) {
			if (field.length() > 0) {
				if (this.isHxlTag(field)) {
					seenTag = true;
				} else {
					return false;
				}
			}
		}
		return seenTag;
	}

	/**
	 * Test if a string is a HXL tag.
	 */
	private boolean isHxlTag(final String field) {
		// TODO proper test
		return field.startsWith("#");
	}

	private String[] readRawRow() throws IOException {
		final String fields[] = this.csvReader.readNext();
		if (fields != null) {
			this.sourceRowNumber++;
			return fields;
		} else {
			this.sourceRowNumber = -1;
			return null;
		}
	}


	/**
	 * Inner class to implement an iterator for HXLRow objects.
	 *
	 * Exceptions will be wrapped in {@link RuntimeException}. The
	 * iterator may sometimes have to read one row ahead, if the
	 * client invokes {@link #hasNext()}.
	 */
	private class HXLIterator implements Iterator<HXLRow> {

		private HXLRow nextRow = null;

		@Override
		public boolean hasNext() {
			if (this.nextRow == null) {
				this.nextRow = this.next();
			}
			return (this.nextRow == null);
		}

		@Override
		public HXLRow next() {
			HXLRow row;
			if (this.nextRow != null) {
				row = this.nextRow;
				this.nextRow = null;
			} else {
				try {
					row = HXLReader.this.read();
				} catch (final IOException ex) {
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
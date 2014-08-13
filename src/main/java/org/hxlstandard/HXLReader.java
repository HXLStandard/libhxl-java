package org.hxlstandard;

import au.com.bytecode.opencsv.CSVParser;

public class HXLReader {

    private CSVParser csv_parser;

    public HXLReader() {
        // TODO can add extra params for custom separators, etc.
        csv_parser = new CSVParser();
    }

    public HXLRow read() {
        return null;
    }

}
package org.hxlstandard;

import java.util.ArrayList;

public class HXLRow {

    private int row_number;

    private int source_row_number;

    private ArrayList<HXLValue> values;

    public HXLRow(int row_number, int source_row_number) {
        this.row_number = row_number;
        this.source_row_number = source_row_number;
    }

}
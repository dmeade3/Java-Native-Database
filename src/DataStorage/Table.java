package DataStorage;

import Junit_Testing.TableTest;

import java.util.LinkedList;

/**
 * Created by David Meade on 8/5/2016.
 */
public class Table {

    ////// FIELDS //////////////////////////////////////////////////////////////////////////////////////////////////////

    // private int defaultInitCapacity    = 16;
    // private float defaultLoadFactor    = (float) 0.9;
    // private int defaultConcurrencyLevel = 1;

    private String tableName;

    // For Data
    private LinkedList<String[]> tableData;

    // Names of Columns
    private String[] colNames;

    // Types for Columns
    private String[] colTypes;

    /////// METHODS ////////////////////////////////////////////////////////////////////////////////////////////////////

    public Table(String tableName, String[] colNames, String[] colTypes) throws Exception {

        initTable(tableName, colNames, colTypes);
    }

    // This method is for if there is ever another constructor with different options
    // these will stay the same
    private void initTable(String name, String[] colNames, String[] colTypes) throws Exception {

        this.tableName = name;

        // Initialize objects
        if (colNames.length == colTypes.length) {
            this.colNames = colNames;

            this.colTypes = colTypes;
        } else {
            throw new Exception("Names and Types are not the same length arrays");
        }

        tableData = new LinkedList<>();
    }

    public void insertTuple(String[] tuple) throws Exception {

        // Check length is right
        if (tuple.length != getTableWidth()) {
            throw new Exception();
        }

        tableData.add(tuple);
    }

    public void deleteTuple() {


    }

    public int getTableWidth() {
        return colNames.length;
    }

    public void display() {
        System.out.println(tableName + ':');

        // Formatting so columns line up
        int[] maxRowColumnLengths = new int[getTableWidth()];

        // Check The type and name section
        for (int i = 0; i < getTableWidth(); i++) {

            if (maxRowColumnLengths[i] < colNames[i].length()) {

                maxRowColumnLengths[i] = colNames[i].length();
            }

            if (maxRowColumnLengths[i] < colTypes[i].length()) {

                maxRowColumnLengths[i] = colTypes[i].length();
            }
        }

        // Check data
        for (int i = 0; i < getTableWidth(); i++) {

            for (int j = 0; j < tableData.size(); j++) {

                if (maxRowColumnLengths[i] < tableData.get(j)[i].length()) {

                    maxRowColumnLengths[i] = tableData.get(j)[i].length();
                }
            }
        }

        // Build the string for printing
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\t");

        for (int i = 0; i < getTableWidth(); i++) {

            if (i != getTableWidth() - 1) {
                stringBuilder.append(colNames[i] + " ");
            }
            else {
                stringBuilder.append(colNames[i]);
            }

            for (int j = 0; j < maxRowColumnLengths[i] - colNames[i].length(); j++) {
                stringBuilder.append(" ");
            }
        }

        stringBuilder.append("\n\t");

        for (int i = 0; i < getTableWidth(); i++) {

            if (i != getTableWidth() - 1) {
                stringBuilder.append(colTypes[i] + " ");
            }
            else {
                stringBuilder.append(colTypes[i]);
            }

            for (int j = 0; j < maxRowColumnLengths[i] - colTypes[i].length(); j++) {
                stringBuilder.append(" ");
            }
        }

        stringBuilder.append("\n");

        stringBuilder.append("\t");

        for (int i = 0; i < getTableWidth(); i++) {

            for (int j = 0; j < maxRowColumnLengths[i]; j++) {
                stringBuilder.append("-");
            }

            if(i != 0) {
                stringBuilder.append("-");
            }
        }

        stringBuilder.append("\n");

        // Add Data
        for (int k = 0; k < tableData.size(); k++) {

            stringBuilder.append("\t");

            for (int i = 0; i < getTableWidth(); i++) {

                if (i != getTableWidth() - 1) {
                    stringBuilder.append(tableData.get(k)[i] + " ");
                }
                else {
                    stringBuilder.append(tableData.get(k)[i]);
                }

                for (int j = 0; j < maxRowColumnLengths[i] - tableData.get(k)[i].length(); j++) {
                    stringBuilder.append(" ");
                }
            }

            stringBuilder.append("\n");
        }

        System.out.println(stringBuilder.toString());
    }

    public static void main(String[] args) throws Exception {

        TableTest test1 = new TableTest();
        test1.run();

        System.out.println();


        String[] colNames = {"Name", "SSN", "Phone#"};
        String[] colTypes = {"String", "Int", "Int"};

        Table table = new Table("People", colNames, colTypes);

        String[] tuple1 = {"Cool Guy", "123456789123456", "7036969420"};
        String[] tuple2 = {"Cooliest Guy", "12345673456", "7034204209"};
        String[] tuple3 = {"Jimmy MacDonald", "123", "5555555555"};
        String[] tuple4 = {"Jim Jam MacDonald", "123", "5555555555"};

        table.insertTuple(tuple1);
        table.insertTuple(tuple2);
        table.insertTuple(tuple3);
        table.insertTuple(tuple4);

        table.display();
    }
}

package com.store.data.generator.generators;

import org.joda.time.DateTime;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

public class InventoryRestockingGenerator
{
    public void generateRestocks() throws IOException
    {
        File resFile = new File("RestockList.txt");
        FileWriter resWrite = new FileWriter(resFile, true);
        BufferedWriter bWrite = new BufferedWriter(resWrite);

        long resID =  id;

        String dateStr = dateOfRestocking.toString("MM/dd/yyyy", Locale.US);

        String resString = "insert into RESTOCKEVENT values(" +
                id + ", " +
                itemId + ", (TO_DATE('" +
                dateStr + "', 'mm/dd/yyyy')), " +
                quantity + "); \n";

        bWrite.write(resString);
        bWrite.close();

        resID++; // get next restock ID for later generations

        File nextResID = new File("ResID.txt");
        FileWriter resWriteNewID = new FileWriter(nextResID.getAbsoluteFile());
        BufferedWriter bWriteNewID = new BufferedWriter(resWriteNewID);
        bWriteNewID.write(Long.toString(resID));

        bWriteNewID.close();
    }

    public InventoryRestockingGenerator(final long id, final long itemId, final DateTime dateOfRestocking, final int quantity)
    {
        this.id = id;
        this.itemId = itemId;
        this.dateOfRestocking = dateOfRestocking;
        this.quantity = quantity;
    }

    private final long id;
    private final long itemId;
    private final DateTime dateOfRestocking;
    private final int quantity;
}
package com.store.data.generator.models;

import org.joda.time.DateTime;
import java.util.Locale;

public class Purchase
{
    public long getId()
    {
        return id;
    }

    public double getSellingPrice()
    {
        return sellingPrice;
    }

    public DateTime getDate()
    {
        return date;
    }

    public long getItemId()
    {
        return itemId;
    }

    public long getEmployeeId()
    {
        return employeeId;
    }

    @Override
    public String toString()
    {
        String dateStr = date.toString("MM/dd/yyyy", Locale.US);

        return "insert into PURCHASEEVENT values(" +
                id + ", " +
                employeeId + ", " +
                itemId + ", (TO_DATE('" +
                dateStr + "', 'mm/dd/yyyy')), " +
                sellingPrice+ "" +
                ");";
    }

    public Purchase(final long id,
                    final double sellingPrice,
                    final DateTime date,
                    final long itemId,
                    final long employeeId)
    {
        this.id = id;
        this.sellingPrice = sellingPrice;
        this.date = date;
        this.itemId = itemId;
        this.employeeId = employeeId;
    }

    private final long id;
    private final double sellingPrice;
    private final DateTime date;
    private final long itemId;
    private final long employeeId;
}

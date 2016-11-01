package com.store.data.generator.models;

public class Item
{
    public long getId()
    {
        return id;
    }

    public ItemType getItemType()
    {
        return itemType;
    }

    public ItemSize getItemSize()
    {
        return itemSize;
    }

    public double getCostOfStoragePerUnit()
    {
        return costOfStoragePerUnit;
    }

    public Department getDepartment()
    {
        return department;
    }

    @Override
    public String toString()
    {
        return "insert into ITEM values(" +
                id + ", '" +
                itemType + "', '" +
                itemSize + "', " +
                costOfStoragePerUnit + ", '" +
                department + "'" +
                ");";
    }

    public Item(
            final long id,
            final ItemType itemType,
            final ItemSize itemSize,
            final double costOfStoragePerUnit,
            final Department department)
    {
        this.id = id;
        this.itemType = itemType;
        this.itemSize = itemSize;
        this.costOfStoragePerUnit = costOfStoragePerUnit;
        this.department = department;
    }

    private final long id;
    private final ItemType itemType;
    private final ItemSize itemSize;
    private final double costOfStoragePerUnit;
    private final Department department;
}

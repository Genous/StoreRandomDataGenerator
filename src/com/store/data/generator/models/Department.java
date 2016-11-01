package com.store.data.generator.models;

public enum Department
{
    MEN,
    WOMEN,
    GIRLS,
    BOYS;

    public int getStaffSize()
        {

        switch (Department.valueOf(this.name()))
        {
            case MEN:
                return 15;

            case WOMEN:
                return 19;

            case GIRLS:
                return 8;

            case BOYS:
                return 9;

            default:
                throw new RuntimeException("Reached invalid location in Department.getStaffSize()");
        }
    }

    public int getTotalPurchases()
    {
        switch (Department.valueOf(this.name()))
        {
            case MEN:
                return 4021;

            case WOMEN:
                return 3953;

            case GIRLS:
                return 8;

            case BOYS:
                return 9;

            default:
                throw new RuntimeException("Reached invalid location in Department.getStaffSize()");
        }
    }
}

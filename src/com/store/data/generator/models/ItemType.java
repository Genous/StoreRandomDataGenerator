package com.store.data.generator.models;

public enum ItemType
{
    SHIRT,
    SHORTS,
    JEANS,
    HAT,
    BELT,
    SOCKS,
    JACKET,
    SHOES;

    public double getBaseStorageCostPerMonth()
    {
        switch (ItemType.valueOf(this.toString()))
        {
            case SHIRT:
                return 0.02;
            case SHORTS:
                return 0.018;
            case JEANS:
                return 0.022;
            case HAT:
                return 0.015;
            case BELT:
                return 0.012;
            case SOCKS:
                return 0.01;
            case JACKET:
                return 0.020;
            case SHOES:
                return 0.080;
            default:
                return 0.015;
        }
    }

    public double getStorageCostIncreasePerSizeIncrease()
    {
        switch (ItemType.valueOf(this.toString()))
        {
            case SHIRT:
                return 0.039;
            case SHORTS:
                return 0.01;
            case JEANS:
                return 0.075;
            case HAT:
                return 0.025;
            case BELT:
                return 0.05;
            case SOCKS:
                return 0.03;
            case JACKET:
                return 0.065;
            case SHOES:
                return 0.055;
            default:
                return 0.008;
        }
    }
}

package ru.yandex.practicum.delivery;

public class PerishableParcel extends Parcel{
    private int timeToLive;

    public PerishableParcel(String description, double weight, String deliveryAdress, int sendDay, int timeToLive) {
        super(description, weight, deliveryAdress, sendDay, 3);
        this.timeToLive = timeToLive;
    }

    public boolean isExpired(int currentDay) {
        return (this.sendDay + this.timeToLive) < currentDay;
    }

}

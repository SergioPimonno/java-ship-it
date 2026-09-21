package ru.yandex.practicum.delivery;

public class StandartParcel extends Parcel

{
    public StandartParcel(String description, double weight, String deliveryAdress, int sendDay) {
        super(description, weight, deliveryAdress, sendDay, 2);
    }

}

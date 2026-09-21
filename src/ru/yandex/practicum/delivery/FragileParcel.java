package ru.yandex.practicum.delivery;

public class FragileParcel extends Parcel implements Trackable{

    public FragileParcel(String description, double weight, String deliveryAdress, int sendDay) {
        super(description, weight, deliveryAdress, sendDay, 4);
    }

    @Override
    public void deliver() {
        System.out.printf("Посылка <<%s>> обёрнута в защитную плёнку%n", this.description);
        super.deliver();
    }

    @Override
    public void reportStatus(String newLocation) {
        System.out.printf("Хрупкая посылка <<%s>> изменила местоположение на %s%n", this.description, newLocation);
    }
}

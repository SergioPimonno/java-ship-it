package ru.yandex.practicum.delivery;

import java.util.ArrayList;

public class ParcelBox<T extends Parcel> {
    private ArrayList<T> box =  new ArrayList<>();
    private double maxWeight;
    private double currentWeight = 0;

    public ParcelBox(double maxWeight){
        this.maxWeight = maxWeight;
    }

    public void addParcel(T parcel){
        if(this.currentWeight + parcel.getWeight() > maxWeight){
            System.out.println("В коробку больше не поместится!");
            return;
        }
        box.add(parcel);
        currentWeight += parcel.getWeight();
    }

    public void getAllParcels(){
        int index = 0;
        if(box.isEmpty()) return;
        for(T parcel: box){
            System.out.printf("Посылка №%d: %s%n", index, parcel.getDescription());
        }
    }

    public int size(){
        return box.size();
    }
}

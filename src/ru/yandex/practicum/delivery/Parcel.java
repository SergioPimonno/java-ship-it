package ru.yandex.practicum.delivery;

public abstract class Parcel {
    protected String description;
    protected double weight;
    protected String deliveryAdress;
    protected int sendDay;
    protected double basicCost;

    public Parcel(String description, double weight, String deliveryAdress, int sendDay, double basicCost){
        this.description = description;
        this.weight = weight;
        this.deliveryAdress = deliveryAdress;
        this.sendDay = sendDay;
        this.basicCost = basicCost;
    }

    public void packageItem(){
        System.out.printf("Посылка <<%s>> упакована%n", this.description);
    };

    public void deliver(){
        System.out.println("Посылка отправлена");
    };

    //обложить тестами
    public double calculateDeliveryCost(){
        return this.basicCost * this.weight;
    };

    public String getDescription(){
        return this.description;
    }

    public double getWeight(){
        return this.weight;
    }
}

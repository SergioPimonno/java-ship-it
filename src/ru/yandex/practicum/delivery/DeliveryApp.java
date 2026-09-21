package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.Scanner;

public class DeliveryApp{

    private static final Scanner scanner = new Scanner(System.in);
    private static ArrayList<Parcel> allParcels = new ArrayList<>();

    private static ArrayList<Trackable> trackableParcels = new ArrayList<>();

    private static ParcelBox<StandartParcel> standartParcelBox = new ParcelBox<>(100);
    private static ParcelBox<FragileParcel> fragileParcelBox = new ParcelBox<>(40);
    private static ParcelBox<PerishableParcel> perishableParcelBox = new ParcelBox<>(80);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.next());

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 4:
                    reportStatusOfTrackables();
                    break;
                case 5:
                    showBox();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 — Определить статус отслеживаемых посылок");
        System.out.println("5 — Показать содержимое коробки");
        System.out.println("0 — Завершить");
    }

    public static void addParcel() {
        ParcelType parcelType = chooseType();

        System.out.println("Укажите описание посылки: ");
        String description = scanner.next();
        System.out.println("Укажите вес посылки: ");
        double weight = Double.parseDouble(scanner.next());
        if(weight <= 0){
            System.out.println("Вес не может быть равен нулю или меньше!");
            return;
        }
        System.out.println("Укажите адрес доставки: ");
        String address = scanner.next();
        System.out.println("Укажите день отправки: ");
        int sendDay = scanner.nextInt();
        if(sendDay <= 0){
            System.out.println("День отправки не может быть неположительным числом!");
            return;
        }

        switch(parcelType){
            case STANDART:
                StandartParcel standartParcel = new StandartParcel(description, weight, address, sendDay);
                allParcels.add(standartParcel);
                standartParcelBox.addParcel(standartParcel);
                System.out.println("Посылка успешно добавлена");
                break;
            case FRAGILE:
                FragileParcel fragileParcel = new FragileParcel(description, weight, address, sendDay);
                allParcels.add(fragileParcel);
                trackableParcels.add(fragileParcel);
                fragileParcelBox.addParcel(fragileParcel);
                System.out.println("Посылка успешно добавлена");
                break;
            case PERISHABLE:
                System.out.println("Укажите срок хранения (кол-во дней): ");
                int TTL = scanner.nextInt();
                PerishableParcel perishableParcel = new PerishableParcel(description, weight, address, sendDay, TTL);
                allParcels.add(perishableParcel);
                perishableParcelBox.addParcel(perishableParcel);
                System.out.println("Посылка успешно добавлена");
                break;
        }
    }

    private static void sendParcels() {
        for(Parcel parcel: allParcels){
            parcel.packageItem();
            parcel.deliver();
        }
    }

    private static void calculateCosts() {
        double sum = 0;
        for (Parcel parcel: allParcels){
            sum += parcel.calculateDeliveryCost();
        }
        System.out.printf("Общая сумма доставок составляет %f руб.%n", sum);
    }

    private static void reportStatusOfTrackables() {
        int index = 0;
        for(Trackable trackable: trackableParcels){
            index++;
            System.out.printf("Укажите newLocation для посылки №%d:%n", index);
            String newLocation = scanner.next();
            trackable.reportStatus(newLocation);
        }
    }

    private static void showBox(){
        ParcelType type = chooseType();
        switch(type){
            case STANDART -> standartParcelBox.getAllParcels();
            case FRAGILE -> fragileParcelBox.getAllParcels();
            case PERISHABLE -> perishableParcelBox.getAllParcels();
        }
    }

    private static ParcelType chooseType(){
        System.out.println("Укажите тип посылки: Стандартная, Хрупкая или Скоропортящаяся");
        String type = scanner.next().toLowerCase();
        ParcelType parcelType = (type.equals("стандартная"))? ParcelType.STANDART:
                (type.equals("хрупкая"))? ParcelType.FRAGILE:
                        (type.equals("скоропортящаяся"))? ParcelType.PERISHABLE: ParcelType.WRONG_TYPE;
        if(parcelType == ParcelType.WRONG_TYPE){
            System.out.println("Указан неверный тип посылки");
            parcelType = chooseType();
        }
        return parcelType;
    }
}


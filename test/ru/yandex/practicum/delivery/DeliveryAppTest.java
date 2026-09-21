package ru.yandex.practicum.delivery;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryAppTest {
    //abstract parcel
    private static String description = "candle ";
    private static double weight = 5.0;
    private static String address = "Hogwarts ";
    private static int sendDay = 12;
    private static int timeToLive = 3;

    private static int standartKoef = 2;
    private static int fragileKoef = 4;
    private static int perishableKoef = 3;

    private static ParcelBox<StandartParcel> standartParcelBox;

    @BeforeEach
    void beforeEach() {
          standartParcelBox = new ParcelBox<>(100);
    }

    @Test
    void calculteDeliveryCostForStandartParcel(){
        StandartParcel standartParcel = new StandartParcel(description, weight, address, sendDay);
        assertEquals(5 * standartKoef, standartParcel.calculateDeliveryCost(), "Рассчетная стоимость " +
                "стандартной посылки не совпала с ожидаемой");
    }

    @Test
    void calculteDeliveryCostForFragileParcel(){
        FragileParcel fragileParcel = new FragileParcel(description, weight, address, sendDay);
        assertEquals(5 * fragileKoef, fragileParcel.calculateDeliveryCost(), "Рассчетная стоимость " +
                "хрупкой посылки не совпала с ожидаемой");
    }

    @Test
    void calculteDeliveryCostForPerishableParcel(){
        PerishableParcel perishableParcel = new PerishableParcel(description, weight, address, sendDay, timeToLive);
        assertEquals(5 * perishableKoef, perishableParcel.calculateDeliveryCost(), "Рассчетная " +
                "стоимость скоропортящейся посылки не совпала с ожидаемой");
    }

    @Test
    void checkIsExpiredIfCurrentDayIs14AndExpirationAt16(){
        PerishableParcel perishableParcel = new PerishableParcel(description, weight, address, sendDay, timeToLive);
        assertFalse(perishableParcel.isExpired(14));
    }

    @Test
    void checkIsExpiredIfCurrentDayIs12AndExpirationAt16(){
        PerishableParcel perishableParcel = new PerishableParcel(description, weight, address, sendDay, timeToLive);
        assertFalse(perishableParcel.isExpired(12));
    }

    @Test
    void checkIsExpiredIfCurrentDayIs16AndExpirationAt16(){
        PerishableParcel perishableParcel = new PerishableParcel(description, weight, address, sendDay, timeToLive);
        assertTrue(perishableParcel.isExpired(16));
    }

    @Test
    void checkIsExpiredIfCurrentDayIs2AndExpirationAt16(){
        PerishableParcel perishableParcel = new PerishableParcel(description, weight, address, sendDay, timeToLive);
        assertFalse(perishableParcel.isExpired(2));
    }

    //Поскольку fragileParcelBox и perishableParcelBox также являются ParcelBox, то для них следующие тесты
    // покажут те же самые результаты
    @Test
    void checkStandartBoxMaxWeightIsNotReachedAndParcelFits(){
        StandartParcel standartParcel = new StandartParcel(description, weight, address, sendDay);
        standartParcelBox.addParcel(standartParcel);

        standartParcelBox.addParcel(new StandartParcel(description, 95, address, sendDay));
        assertEquals(2, standartParcelBox.size(), "Количество позиций в коробке не совпало " +
                "с ожидаемым");
    }

    @Test
    void checkStandartBoxMaxWeightIsNotReachedAndParcelDoesNotFit(){
        StandartParcel standartParcel = new StandartParcel(description, weight, address, sendDay);
        standartParcelBox.addParcel(standartParcel);

        standartParcelBox.addParcel(new StandartParcel(description, 100, address, sendDay));
        assertEquals(1, standartParcelBox.size(), "Количество позиций в коробке не совпало " +
                "с ожидаемым");
    }

    @Test
    void checkStandartBoxMaxWeightReachedAndParcelDoesNotFit(){
        StandartParcel standartParcel = new StandartParcel(description, 100, address, sendDay);
        standartParcelBox.addParcel(standartParcel);

        standartParcelBox.addParcel(new StandartParcel(description, weight, address, sendDay));
        assertEquals(1, standartParcelBox.size(), "Количество позиций в коробке не совпало " +
                "с ожидаемым");
    }

}
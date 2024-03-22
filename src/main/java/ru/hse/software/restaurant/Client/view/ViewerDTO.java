package ru.hse.software.restaurant.Client.view;

import ru.hse.software.restaurant.Server.view.dto.DishDTO;
import ru.hse.software.restaurant.Server.view.dto.OrderDTO;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

public class ViewerDTO {

    public static void viewMenu(List<DishDTO> dishes) {
        if(dishes == null) {
            System.out.println("Меню пока пусто!");
        } else {
            for(DishDTO dish : dishes) {
                System.out.println("-----------------------------");
                System.out.print(dish.toString());
            }
        }
    }

    public static void viewDish(Map<DishDTO, Integer> dishes) {
        for(var entry : dishes.entrySet()) {
            System.out.println("-----------------------");
            System.out.print(entry.getKey().toString());
            System.out.println("count: " + entry.getValue());
        }
        System.out.println();
    }

    public static void viewDishInOrder(Map<DishDTO, Integer> dishMap) {
        dishMap.forEach((dish, count) -> System.out.print("[" + dish.getTitle() + ", " + count + "]; "));
        System.out.println();
    }

    public static void viewOrders(List<AbstractMap.SimpleEntry<OrderDTO, Map<DishDTO, Integer>>> orders) {
        if(orders == null) {
            return;
        }
        for(var entry : orders) {
            System.out.println("---------------------");
            System.out.print(entry.getKey().toString() + " ");
            viewDishInOrder(entry.getValue());
        }
    }
}

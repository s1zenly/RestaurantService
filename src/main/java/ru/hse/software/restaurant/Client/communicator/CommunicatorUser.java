package ru.hse.software.restaurant.Client.communicator;

import ru.hse.software.restaurant.Client.communicator.interfaces.Communicator;
import ru.hse.software.restaurant.Client.controller.UserControllerClient;
import ru.hse.software.restaurant.Client.controller.ViewControllerClient;
import ru.hse.software.restaurant.Client.view.ViewerDTO;
import ru.hse.software.restaurant.Client.view.ViewerPanelAction;
import ru.hse.software.restaurant.Server.view.dto.DishDTO;
import ru.hse.software.restaurant.Server.view.dto.OrderDTO;
import ru.hse.software.restaurant.Server.view.dto.PersonaDTO;
import ru.hse.software.restaurant.Server.view.entity.Order;

import java.sql.SQLException;
import java.util.*;

import static java.lang.Thread.sleep;

public class CommunicatorUser implements Communicator {
    CommunicatorUser(long id) {
        this.id = id;
    }

    private final Scanner scanner = new Scanner(System.in);
    private final long id;
    private final int countCommand = 10;
    private final UserControllerClient userControllerClient = new UserControllerClient();
    private final ViewControllerClient viewControllerClient = new ViewControllerClient();

    @Override
    public void start() throws SQLException, InterruptedException {
        boolean isActiveSession = true;

        while(isActiveSession) {
            sleep(2000);
            System.out.println();

            try {
                ViewerPanelAction.viewPanelActionUser();

                System.out.println("Input command");
                int command = Integer.parseInt(scanner.nextLine());
                if (command < 1 || command > countCommand) {
                    throw new NumberFormatException();
                }

                switch (command) {
                    case 1 -> {
                        userControllerClient.createNewOrder(id);
                        System.out.println("We have created an order for you");
                    }

                    case 2 -> {
                        List<AbstractMap.SimpleEntry<OrderDTO, Map<DishDTO, Integer>>> orders = userControllerClient.getInfoAboutOrders(id);
                        ViewerDTO.viewOrders(orders);
                        if (orders == null) {
                            System.out.println("You don't have orders");
                            break;
                        }
                        System.out.println("Which order should I add to? Id: ");
                        String idStr = scanner.nextLine();

                        if(!idStr.matches("[0-9]++")) {
                            System.out.println("You're input incorrect id");
                            break;
                        }

                        int idOrder = Integer.parseInt(idStr);
                        Optional<AbstractMap.SimpleEntry<OrderDTO, Map<DishDTO, Integer>>> foundOrder = orders.stream()
                                .filter(orderDTO -> orderDTO.getKey().getId() == idOrder)
                                .findFirst();

                        if(foundOrder.isEmpty()) {
                            System.out.println("You don't have such an order");
                            break;
                        }

                        List<DishDTO> menu = viewControllerClient.getMenu();
                        ViewerDTO.viewMenu(menu);

                        if(menu == null) {
                            break;
                        }

                        System.out.println("Input title dish: ");
                        String title = scanner.nextLine();

                        if(title == null) {
                            System.out.println("You're input incorrect title");
                            break;
                        }

                        boolean success = userControllerClient.addDish(idOrder, title);

                        if(!success) {
                            System.out.println("Error! Check the dish, order id and order completion status");
                        } else {
                            System.out.println("The dish has been successfully added to the order");
                        }

                    }

                    case 3 -> {
                        List<AbstractMap.SimpleEntry<OrderDTO, Map<DishDTO, Integer>>> orders = userControllerClient.getInfoAboutOrders(id);
                        ViewerDTO.viewOrders(orders);

                        if(orders == null) {
                            System.out.println("You don't have order");
                            break;
                        }
                        System.out.println("Which order should I delete to? Id: ");
                        String idStr = scanner.nextLine();

                        if(!idStr.matches("[0-9]++")) {
                            System.out.println("You're input incorrect id");
                            break;
                        }

                        int idOrder = Integer.parseInt(idStr);
                        Optional<AbstractMap.SimpleEntry<OrderDTO, Map<DishDTO, Integer>>> foundOrder = orders.stream()
                                .filter(orderDTO -> orderDTO.getKey().getId() == idOrder)
                                .findFirst();

                        if(foundOrder.isEmpty()) {
                            System.out.println("You don't have such an order");
                            break;
                        }

                        System.out.println("Input title dish: ");
                        String title = scanner.nextLine();

                        if(title == null) {
                            System.out.println("You're input incorrect title");
                            break;
                        }

                        boolean success = userControllerClient.deleteDish(idOrder, title);

                        if(!success) {
                            System.out.println("Error! Check the dish, order id and order completion status");
                        } else {
                            System.out.println("The dish has been successfully removed from the order");
                        }
                    }

                    case 4 -> {
                        List<AbstractMap.SimpleEntry<OrderDTO, Map<DishDTO, Integer>>> orders = userControllerClient.getInfoAboutOrders(id);
                        ViewerDTO.viewOrders(orders);

                        if(orders == null) {
                            System.out.println("You don't have order");
                            break;
                        }

                        System.out.println("Which order do you want to send for execution? Id: ");
                        String idStr = scanner.nextLine();

                        if(!idStr.matches("[0-9]++")) {
                            System.out.println("You're input incorrect id");
                            break;
                        }

                        int idOrder = Integer.parseInt(idStr);
                        Optional<AbstractMap.SimpleEntry<OrderDTO, Map<DishDTO, Integer>>> foundOrder = orders.stream()
                                .filter(orderDTO -> orderDTO.getKey().getId() == idOrder)
                                .findFirst();

                        if(foundOrder.isEmpty()) {
                            System.out.println("You don't have such an order");
                            break;
                        }

                        boolean success = userControllerClient.startOrder(idOrder);

                        if(!success) {
                            System.out.println("Your order is empty or already active");
                        } else {
                            System.out.println("Your order has been added to the queue");
                        }
                    }

                    case 5 -> {
                        List<AbstractMap.SimpleEntry<OrderDTO, Map<DishDTO, Integer>>> orders = userControllerClient.getInfoAboutOrders(id);
                        ViewerDTO.viewOrders(orders);

                        if(orders == null) {
                            System.out.println("You don't have order");
                            break;
                        }

                        System.out.println("what order should I give information about? Id: ");
                        String idStr = scanner.nextLine();

                        if(!idStr.matches("[0-9]++")) {
                            System.out.println("You're input incorrect id");
                            break;
                        }

                        int idOrder = Integer.parseInt(idStr);
                        Optional<AbstractMap.SimpleEntry<OrderDTO, Map<DishDTO, Integer>>> foundOrder = orders.stream()
                                .filter(orderDTO -> orderDTO.getKey().getId() == idOrder)
                                .findFirst();

                        if(foundOrder.isEmpty()) {
                            System.out.println("You don't have such an order");
                            break;
                        }

                        AbstractMap.SimpleEntry<OrderDTO, Map<DishDTO, Integer>> order = userControllerClient.getInfoAboutOrder(idOrder);

                        System.out.println(order.getKey().toString());
                        ViewerDTO.viewDish(order.getValue());
                    }

                    case 6 -> {
                        List<AbstractMap.SimpleEntry<OrderDTO, Map<DishDTO, Integer>>> orders = userControllerClient.getInfoAboutOrders(id);
                        ViewerDTO.viewOrders(orders);

                        if(orders == null) {
                            System.out.println("You don't have order");
                        }
                    }

                    case 7 -> {
                        List<AbstractMap.SimpleEntry<OrderDTO, Map<DishDTO, Integer>>> orders = userControllerClient.getInfoAboutOrders(id);
                        ViewerDTO.viewOrders(orders);

                        if(orders == null) {
                            System.out.println("You don't have order");
                            break;
                        }

                        System.out.println("Select the order you want to pay for? Id: ");
                        String idStr = scanner.nextLine();

                        if(!idStr.matches("[0-9]++")) {
                            System.out.println("You're input incorrect id");
                            break;
                        }

                        int idOrder = Integer.parseInt(idStr);
                        Optional<AbstractMap.SimpleEntry<OrderDTO, Map<DishDTO, Integer>>> foundOrder = orders.stream()
                                .filter(orderDTO -> orderDTO.getKey().getId() == idOrder)
                                .findFirst();

                        if(foundOrder.isEmpty()) {
                            System.out.println("You don't have such an order");
                            break;
                        }

                        System.out.println("Enter the amount");
                        String amountStr = scanner.nextLine();

                        if(!amountStr.matches("[0-9]++")) {
                            System.out.println("You're input incorrect amount");
                            break;
                        }

                        int amount = Integer.parseInt(amountStr);

                        if(amount < 0) {
                            System.out.println("You're input negative amount");
                            break;
                        }

                        Integer remain = userControllerClient.paymentOrder(id, idOrder, amount);

                        if(remain == null) {
                            System.out.println("The order is not ready to be paid yet or has already been paid");
                        } else if(remain < 0) {
                            System.out.println("There was not enough money to pay for the order, the order was paid, and the balance of the debt was credited to your account");
                        } else {
                            System.out.println("The order has been paid, the balance is in your account");
                        }
                    }

                    case 8 -> {
                        System.out.println("Money: %s".formatted(userControllerClient.getMoneyAccount(id)));

                        System.out.println("Enter the amount");
                        String amountStr = scanner.nextLine();

                        if(!amountStr.matches("[0-9]++")) {
                            System.out.println("You're input incorrect amount");
                            break;
                        }

                        int amount = Integer.parseInt(amountStr);

                        if(amount < 0) {
                            System.out.println("You're input negative amount");
                            break;
                        }

                        int remain = userControllerClient.paymentAllOrder(id, amount);

                        if(remain >= 0) {
                            System.out.println("The order has been paid, the balance is in your account");
                        } else {
                            System.out.println("There was not enough money to close the debt or you don't have debt");
                        }
                    }

                    case 9 -> {
                        System.out.println("Money: %s".formatted(userControllerClient.getMoneyAccount(id)));
                    }

                    case 10 -> {
                        end();
                        isActiveSession = false;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("I don't know this command! Try again...");
            }


        }
    }

    public void end() {
        System.out.println("Goodbye!");
    }
}

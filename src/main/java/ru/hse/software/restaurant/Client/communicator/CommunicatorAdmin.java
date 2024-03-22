package ru.hse.software.restaurant.Client.communicator;

import ru.hse.software.restaurant.Client.communicator.interfaces.Communicator;
import ru.hse.software.restaurant.Client.controller.AdminControllerClient;
import ru.hse.software.restaurant.Client.controller.ViewControllerClient;
import ru.hse.software.restaurant.Client.mapper.DishMapper;
import ru.hse.software.restaurant.Client.view.ViewerDTO;
import ru.hse.software.restaurant.Client.view.ViewerPanelAction;
import ru.hse.software.restaurant.Server.view.dto.DishDTO;

import java.sql.SQLException;
import java.util.Scanner;

public class CommunicatorAdmin implements Communicator {

    CommunicatorAdmin(long id) {
        this.id = id;
    }
    private final long id;
    private final Scanner scanner = new Scanner(System.in);
    private final AdminControllerClient adminControllerClient = new AdminControllerClient();
    private final ViewControllerClient viewControllerClient = new ViewControllerClient();
    private final int countCommand = 4;

    public void start() throws SQLException {
        boolean isActiveSession = true;

        while(isActiveSession) {

            try {
                ViewerPanelAction.viewPanelActionAdmin();

                System.out.println("Input command");
                int command = Integer.parseInt(scanner.nextLine());
                if (command < 1 || command > countCommand) {
                    throw new NumberFormatException();
                }

                switch (command) {
                    case 1 -> {
                        ViewerDTO.viewMenu(viewControllerClient.getMenu());
                        System.out.println("Input title dish: ");
                        String title = scanner.nextLine();
                        System.out.println("Input price dish: ");
                        String priceStr =scanner.nextLine();
                        System.out.println("Input difficult dish: ");
                        String difficultStr = scanner.nextLine();

                        if(title == null || !priceStr.matches("[0-9]++") || !difficultStr.matches("[0-9]++")) {
                            System.out.println("Incorrect data!");
                            break;
                        }

                        int price = Integer.parseInt(priceStr);
                        int difficult = Integer.parseInt(difficultStr);

                        DishDTO dishDTO = DishMapper.map(-1,title, price, difficult);

                        if(!adminControllerClient.addDish(dishDTO)) {
                            System.out.println("There is already such a dish");
                        } else {
                            System.out.println("The dish has been added successfully");
                        }
                    }

                    case 2 -> {
                        ViewerDTO.viewMenu(viewControllerClient.getMenu());
                        System.out.println("Input title dish: ");
                        String title = scanner.nextLine();

                        if(title == null) {
                            System.out.println("Incorrect data!");
                            break;
                        }

                        if(!adminControllerClient.deleteDish(title)) {
                            System.out.println("There is no such dish");
                        } else {
                            System.out.println("The dish has been deleted successfully");
                        }
                    }

                    case 3 -> {
                        ViewerDTO.viewMenu(viewControllerClient.getMenu());
                        System.out.println("Input title dish: ");
                        String title = scanner.nextLine();
                        System.out.println("Input price dish: ");
                        String priceStr =scanner.nextLine();
                        System.out.println("Input difficult dish: ");
                        String difficultStr = scanner.nextLine();

                        if(title == null || !priceStr.matches("[0-9]++") || !difficultStr.matches("[0-9]++")) {
                            System.out.println("Incorrect data!");
                            break;
                        }

                        int price = Integer.parseInt(priceStr);
                        int difficult = Integer.parseInt(difficultStr);

                        DishDTO dishDTO = DishMapper.map(-1,title, price, difficult);

                        if(!adminControllerClient.changeDish(title, dishDTO)) {
                            System.out.println("There is no such dish it is still in the orders");
                        } else {
                            System.out.println("The dish has been changed successfully");
                        }
                    }

                    case 4 -> {
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
        System.out.println("Goodbay!");
    }
}

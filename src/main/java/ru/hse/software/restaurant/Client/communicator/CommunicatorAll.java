package ru.hse.software.restaurant.Client.communicator;

import ru.hse.software.restaurant.Client.communicator.interfaces.Communicator;
import ru.hse.software.restaurant.Client.controller.AuthControllerClient;
import ru.hse.software.restaurant.Client.view.ViewerPanelAction;
import ru.hse.software.restaurant.Server.view.dto.PersonaDTO;

import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.Scanner;

public class CommunicatorAll implements Communicator {

    private final Scanner scanner = new Scanner(System.in);
    private final AuthControllerClient authControllerClient = new AuthControllerClient();
    private final int countCommand = 2;

    public void start() throws Exception {

        while(true) {
            try {
                ViewerPanelAction.viewPanelActionAll();

                System.out.println("Введите команду");
                int command = Integer.parseInt(scanner.nextLine());
                if(command < 1 || command > countCommand) {
                    throw new NumberFormatException();
                }

                switch (command) {
                    case 1:
                    case 2: {
                        System.out.println("Input email");
                        String email = scanner.nextLine();
                        System.out.println("Input password");
                        String password = scanner.nextLine();

                        if(email == null || password == null) {
                            System.out.println("Email or password is empty!");
                            break;
                        }

                        if(command == 1) {
                            AbstractMap.SimpleEntry<String, PersonaDTO> data = authControllerClient.logIn(email, password);

                            if(data == null) {
                                System.out.println("You are not registered, check your data");
                                break;
                            }

                            String role = data.getKey();
                            Long id = data.getValue().getId();

                            if(role.equals("admin")) {
                                CommunicatorAdmin communicatorAdmin = new CommunicatorAdmin(id);
                                communicatorAdmin.start();
                            } else if(role.equals("user")) {
                                CommunicatorUser communicatorUser = new CommunicatorUser(id);
                                communicatorUser.start();
                            }
                        } else {
                            if(authControllerClient.register(email, password)) {
                                System.out.println("You have been successfully registered");
                            } else {
                                System.out.println("This email or password is already in the system");
                            }
                        }
                        break;
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

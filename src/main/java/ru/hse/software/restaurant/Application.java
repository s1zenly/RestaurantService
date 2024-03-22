package ru.hse.software.restaurant;

import ru.hse.software.restaurant.Client.communicator.CommunicatorAll;

import java.sql.SQLException;

public class Application {
    public static void main(String[] args) throws Exception {
        StartInitialization.initialization();
        CommunicatorAll communicator = new CommunicatorAll();
        communicator.start();
    }
}
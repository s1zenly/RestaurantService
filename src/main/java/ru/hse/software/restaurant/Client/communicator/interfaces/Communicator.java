package ru.hse.software.restaurant.Client.communicator.interfaces;

import java.sql.SQLException;

public interface Communicator {
    public void start() throws Exception;

    public void end() throws Exception;
}

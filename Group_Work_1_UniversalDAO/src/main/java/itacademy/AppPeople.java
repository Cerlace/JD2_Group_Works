package itacademy;

import itacademy.api.CommandConsole;
import itacademy.api.DAO;
import itacademy.commands_console.IdInputCommand;
import itacademy.commands_console.PeopleInputCommand;
import itacademy.commands_dao.people.*;
import itacademy.dao.impl.PeopleDAOImpl;
import itacademy.dto.People;
import itacademy.exceptions.checked.InvalidInputException;
import itacademy.utils.ConsoleUtils;
import itacademy.utils.MenuUtils;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Scanner;

public class AppPeople {
    public static void main(String[] args) {
        boolean isExit = false;
        Scanner console = new Scanner(System.in);
        DAO<People> dao = new PeopleDAOImpl();

        CommandConsole<Serializable> inputId = new IdInputCommand(console);
        CommandConsole<People> inputPeopleData = new PeopleInputCommand(console);

        try {
            dao.createTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        while (!isExit) {
            try {
                System.out.print(MenuUtils.getPeopleMenu());
                int choice = ConsoleUtils.inputInt(console);

                switch (choice) {
                    case 1: {
                        People people = inputPeopleData.execute();
                        new PeopleSaveCommand(dao, people).execute();
                        break;
                    }
                    case 2: {
                        Serializable id = inputId.execute();
                        People people = inputPeopleData.execute();
                        new PeopleUpdateCommand(dao, people, id).execute();
                        break;
                    }
                    case 3: {
                        Serializable id = inputId.execute();
                        new PeopleDeleteCommand(dao, id).execute();
                        break;
                    }
                    case 4: {
                        Serializable id = inputId.execute();
                        new PeopleGetCommand(id, dao).execute();
                        break;
                    }
                    case 5: {
                        new PeopleGetAllCommand(dao).execute();
                        break;
                    }
                    case 0: {
                        isExit = true;
                        break;
                    }
                    default: {
                        System.out.println("Такого пункта меню не существует");
                    }
                }
            } catch (InvalidInputException | SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

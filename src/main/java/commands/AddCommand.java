package commands;

import service.CommandService;
import commands.utils.CommandInterface;
import model.Status;
import model.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class AddCommand implements CommandInterface {
    private final CommandService commandService;
    private final Scanner scanner;
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public AddCommand(CommandService commandService, Scanner scanner) {
        this.commandService = commandService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("\nВведите название задачи: ");
        String taskName = scanner.nextLine();
        while (taskName == null || taskName.isEmpty()){
            System.out.print("Название задачи не должно быть пустым!\n" +
                    "Введите название задачи: ");
            taskName = scanner.nextLine();
        }

        System.out.print("Введите описание задачи: ");
        String taskDescription = scanner.nextLine();
        while (taskDescription == null || taskDescription.isEmpty()){
            System.out.print("Описание задачи не должно быть пустым!\n" +
                    "Введите описание задачи: ");
            taskDescription = scanner.nextLine();
        }

        System.out.print("Введите срок выполнения задачи (дд.мм.гггг): ");
        LocalDate taskDate = null;
        while (taskDate == null) {
            try {
                String dateString = scanner.nextLine();
                taskDate = LocalDate.parse(dateString, dateFormatter);
            } catch (DateTimeParseException e) {
                System.out.print("Некорректный формат даты! Используйте дд.мм.гггг: ");
            }
        }

        Status taskStatus = null;
        while (taskStatus == null) {
            System.out.print("Выберите статус задачи (1 - TODO; 2 - IN_PROGRESS; 3 - DONE) : ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    taskStatus = Status.TODO;
                    break;
                case "2":
                    taskStatus = Status.IN_PROGRESS;
                    break;
                case "3":
                    taskStatus = Status.DONE;
                    break;
                default:
                    System.out.println("Некорректный выбор! Введите число от 1 до 3");
            }
        }

        commandService.add(new Task(taskName, taskDescription, taskDate, taskStatus));
        System.out.println("Задача добавлена!");
    }

    @Override
    public String getDescription() {
        return "add: Добавляет задачу в ваш список задач";
    }
}

package commands;

import Service.CommandService;
import commands.utils.CommandInterface;
import model.Status;
import model.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class EditCommand implements CommandInterface {
    private final CommandService commandService;
    Scanner input = new Scanner(System.in);

    public EditCommand(CommandService commandService) {
        this.commandService = commandService;
    }

    @Override
    public void execute() {
        List<Task> tasks = commandService.getTasks();
        if (tasks.isEmpty()) {
            System.out.println("Нет задач для редактирования.");
            return;
        }

        System.out.println("Список задач:");
        IntStream.range(0, tasks.size())
                .forEach(i -> System.out.println((i + 1) + ". " + tasks.get(i)));

        System.out.print("Выберите номер задачи для редактирования: ");
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(input.nextLine()) - 1;
            if (taskNumber < 0 || taskNumber >= tasks.size()) {
                System.out.println("Неверный номер задачи!");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: введите число!");
            return;
        }

        Task taskToEdit = tasks.remove(taskNumber);

        System.out.println("\nВыберите поле для редактирования:");
        System.out.println("1. Название");
        System.out.println("2. Описание");
        System.out.println("3. Дата выполнения");
        System.out.println("4. Статус");
        System.out.print("Ваш выбор (1-4): ");

        int fieldChoice;
        try {
            fieldChoice = Integer.parseInt(input.nextLine());
            if (fieldChoice < 1 || fieldChoice > 4) {
                System.out.println("Неверный выбор!");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: введите число от 1 до 4!");
            return;
        }

        switch (fieldChoice) {
            case 1 -> editName(taskToEdit);
            case 2 -> editDescription(taskToEdit);
            case 3 -> editDate(taskToEdit);
            case 4 -> editStatus(taskToEdit);
        }
        commandService.add(taskToEdit);
        System.out.println("Задача успешно обновлена!");
    }

    private void editName(Task task) {
        System.out.print("Введите новое название: ");
        String newName = input.nextLine();
        while (newName.isBlank()) {
            System.out.print("Название не может быть пустым! Введите снова: ");
            newName = input.nextLine();
        }
        task.setName(newName);
    }

    private void editDescription(Task task) {
        System.out.print("Введите новое описание (Enter чтобы оставить пустым): ");
        String newDesc = input.nextLine();
        task.setDescription(newDesc.isEmpty() ? null : newDesc);
    }

    private void editDate(Task task) {
        System.out.print("Введите новую дату (дд.мм.гггг): ");
        while (true) {
            try {
                String dateStr = input.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                LocalDate newDate = LocalDate.parse(dateStr, formatter);
                task.setDate(newDate);
                break;
            } catch (DateTimeParseException e) {
                System.out.print("Неверный формат даты! Используйте дд.мм.гггг: ");
            }
        }
    }

    private void editStatus(Task task) {
        System.out.println("Доступные статусы:");
        Arrays.stream(Status.values())
                .forEach(status -> System.out.println("- " + status));

        System.out.print("Введите новый статус: ");
        while (true) {
            try {
                String statusStr = input.nextLine().toUpperCase().replace(" ", "_");
                Status newStatus = Status.valueOf(statusStr);
                task.setStatus(newStatus);
                break;
            } catch (IllegalArgumentException e) {
                System.out.print("Неверный статус! Введите один из доступных: ");
            }
        }
    }

    @Override
    public String getDescription() {
        return "edit: Редактирование поля выбранной задачи";
    }
}

package commands;

import service.CommandService;
import commands.utils.CommandInterface;
import model.Status;
import model.Task;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class FilterCommand implements CommandInterface {
    private final CommandService commandService;
    private final Scanner scanner;

    public FilterCommand(CommandService commandService, Scanner scanner) {
        this.commandService = commandService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println("Доступные статусы задач:");
        Arrays.stream(Status.values())
                .forEach(status -> System.out.println("- " + status));

        System.out.print("Введите статус для фильтрации: ");
        String statusInput = scanner.nextLine().toUpperCase();

        try {
            Status status = Status.valueOf(statusInput.replace(" ", "_"));

            List<Task> filteredTasks = commandService.getTasks().stream()
                    .filter(task -> task.getStatus() == status)
                    .toList();

            if (filteredTasks.isEmpty()) {
                System.out.println("Задачи со статусом " + status + " не найдены.");
            } else {
                System.out.println("Найдено " + filteredTasks.size() + " задач со статусом " + status + ":");
                IntStream.range(0, filteredTasks.size())
                        .forEach(i -> System.out.println((i + 1) + ". " + filteredTasks.get(i)));
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: некорректный статус!");
        }
    }

    @Override
    public String getDescription() {
        return "filter: Фильтрует задачи по заданному условию";
    }
}

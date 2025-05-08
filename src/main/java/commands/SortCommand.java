package commands;

import Service.CommandService;
import commands.utils.CommandInterface;
import model.Task;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SortCommand implements CommandInterface {
    private final CommandService commandService;
    Scanner input = new Scanner(System.in);

    public SortCommand(CommandService commandService) {
        this.commandService = commandService;
    }

    @Override
    public void execute() {
        System.out.println("Варианты сортировки:");
        System.out.println("1. По названию (А-Я)");
        System.out.println("2. По названию (Я-А)");
        System.out.println("3. По дате (сначала старые)");
        System.out.println("4. По дате (сначала новые)");
        System.out.println("5. По статусу (TODO -> IN_PROGRESS -> DONE)");
        System.out.println("6. По статусу (DONE -> IN_PROGRESS -> TODO)");

        System.out.print("Выберите тип сортировки (1-6): ");
        int choice;
        try {
            choice = Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: введите число от 1 до 6!");
            return;
        }

        List<Task> tasks = commandService.getTasks();
        List<Task> sortedTasks = sortTasks(tasks, choice);

        if (sortedTasks.isEmpty()) {
            System.out.println("Нет задач для отображения.");
        } else {
            System.out.println("\nРезультаты сортировки:");
            IntStream.range(0, sortedTasks.size())
                    .forEach(i -> System.out.println((i + 1) + ". " + sortedTasks.get(i)));
        }
    }

    private List<Task> sortTasks(List<Task> tasks, int sortOption) {
        return tasks.stream()
                .sorted(getComparatorForOption(sortOption))
                .collect(Collectors.toList());
    }

    private Comparator<Task> getComparatorForOption(int option) {
        switch (option) {
            case 1:
                return Comparator.comparing(Task::getName);
            case 2:
                return Comparator.comparing(Task::getName).reversed();
            case 3:
                return Comparator.comparing(Task::getDate);
            case 4:
                return Comparator.comparing(Task::getDate).reversed();
            case 5:
                return Comparator.comparing(Task::getStatus);
            case 6:
                return Comparator.comparing(Task::getStatus).reversed();
            default:
                System.out.println("Неверный вариант сортировки. Используется сортировка по умолчанию (по дате).");
                return Comparator.comparing(Task::getDate);
        }
    }

    @Override
    public String getDescription() {
        return "sort: Сортирует список задач по выбранному условию";
    }
}

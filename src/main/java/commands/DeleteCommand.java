package commands;

import service.CommandService;
import commands.utils.CommandInterface;
import model.Task;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class DeleteCommand implements CommandInterface {
    private final CommandService commandService;
    private final Scanner scanner;

    public DeleteCommand(CommandService commandService, Scanner scanner) {
        this.commandService = commandService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        List<Task> taskList = commandService.getTasks();
        AtomicInteger index = new AtomicInteger(1); // Начинаем с 1
        System.out.println("Список всех задач:");
        taskList.stream()
                .forEach(task -> System.out.println(index.getAndIncrement() + ". " + task));

        int selectedTask = 0;
        while (selectedTask == 0) {
            try {
                System.out.print("Выберите номер задачи: ");
                selectedTask = scanner.nextInt();
                if (selectedTask >= 1 && selectedTask <= taskList.size()) {
                    commandService.delete(selectedTask-1);
                    System.out.println("Задача удалена!");
                } else {
                    System.out.println("Неверный номер задачи!");
                }
            }catch (NumberFormatException e) {
                System.out.println("Ошибка: нужно ввести число!");
            }
        }
    }

    @Override
    public String getDescription() {
        return "delete: Удаляет задачу по выбранному индексу";
    }
}

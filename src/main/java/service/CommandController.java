package service;

import commands.*;
import commands.utils.CommandInterface;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandController {
    private final CommandService commandService;
    Map<String, CommandInterface> commands = new HashMap<>();
    Scanner scanner = new Scanner(System.in);

    public CommandController(CommandService commandService) {
        this.commandService = commandService;
        commands.put("add", new AddCommand(commandService, scanner));
        commands.put("list", new ListCommand(commandService));
        commands.put("edit", new EditCommand(commandService, scanner));
        commands.put("delete", new DeleteCommand(commandService, scanner));
        commands.put("filter", new FilterCommand(commandService, scanner));
        commands.put("sort", new SortCommand(commandService, scanner));
        commands.put("exit", new ExitCommand(commandService));
    }

    public void start() {
        System.out.println("Список доступных команд:");
        commands.values().stream()
                .map(CommandInterface::getDescription)
                .forEach(System.out::println);
        while (true) {
            System.out.print("\nВведите команду -> ");
            CommandInterface command = commands.get(scanner.next());
            if (command != null) {
                command.execute();
            } else {
                System.out.println("Команды не существует!");
            }
        }
    }
}

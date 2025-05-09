package commands;

import service.CommandService;
import commands.utils.CommandInterface;
import model.Task;

import java.util.List;

public class ListCommand implements CommandInterface {
    private final CommandService commandService;

    public ListCommand(CommandService commandService) {
        this.commandService = commandService;
    }

    @Override
    public void execute() {
        List<Task> taskList = commandService.getTasks();
        taskList
                .forEach(System.out::println);
    }

    @Override
    public String getDescription() {
        return "list: Выводит список всех задач";
    }
}

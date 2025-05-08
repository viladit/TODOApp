package commands;

import Service.CommandService;
import commands.utils.CommandInterface;

public class ExitCommand implements CommandInterface {
    private final CommandService commandService;

    public ExitCommand(CommandService commandService) {
        this.commandService = commandService;
    }

    @Override
    public void execute() {
        System.out.println("До скорых встреч!");
        System.exit(0);
    }

    @Override
    public String getDescription() {
        return "exit: Остановка программы   ";
    }
}

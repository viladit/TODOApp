import Service.CommandController;
import Service.CommandService;
import Service.DataRepository;

public class AppMain {
    public static void main(String[] args) {
        DataRepository dataRepository = new DataRepository();
        CommandService commandService = new CommandService(dataRepository);
        CommandController commandController = new CommandController(commandService);
        commandController.start();
    }
}
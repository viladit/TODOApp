import service.CommandController;
import service.CommandService;
import service.DataRepository;

public class AppMain {
    public static void main(String[] args) {
        DataRepository dataRepository = new DataRepository();
        CommandService commandService = new CommandService(dataRepository);
        CommandController commandController = new CommandController(commandService);
        commandController.start();
    }
}
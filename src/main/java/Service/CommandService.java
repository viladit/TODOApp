package Service;

import model.Task;

import java.util.List;

public class CommandService {
    public final DataRepository dataRepository;

    public CommandService(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public void add(Task task) {
        DataRepository.save(task);
    }

    public List<Task> getTasks() {
        return DataRepository.getTaskList();
    }

    public void delete(int selectedTask) {
        DataRepository.remove(selectedTask);
    }
}

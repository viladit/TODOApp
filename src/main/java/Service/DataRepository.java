package Service;

import lombok.Getter;
import model.Status;
import model.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataRepository {
    @Getter
    private static List<Task> taskList = new ArrayList<>();

    // Задаем базовые значения в коллекцию во время инициализации
    public DataRepository() {
        taskList.add(new Task("Пробежка", "Сделать пробежку", LocalDate.now(), Status.TODO));
        taskList.add(new Task("Обед", "Приготовить курицу в кляре", LocalDate.now(), Status.IN_PROGRESS));
        taskList.add(new Task("JAVA Daddy", "Сделать задачи по курсу", LocalDate.now(), Status.DONE));
    }

    public static void save(Task task) {
        taskList.add(task);
    }

    public static void remove(int selectedTask) {
        taskList.remove(selectedTask);
    }
}

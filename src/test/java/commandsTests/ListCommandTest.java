package commandsTests;

// КАК ЭТО СДЕЛАТЬ КРАСИВЕЕ?
import commands.ListCommand;
import model.Status;
import model.Task;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.CommandService;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ListCommandTest {

    @Mock
    private CommandService commandService;

    @InjectMocks
    private ListCommand listCommand;

    @Test
    void execute_ShouldPrintAllTasks() {
        List<Task> mockTasks = List.of(
                new Task("Task 1", "Description 1", LocalDate.now(), Status.TODO),
                new Task("Task 2", "Description 2", LocalDate.now().plusDays(1), Status.IN_PROGRESS)
        );

        when(commandService.getTasks()).thenReturn(mockTasks);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        listCommand.execute();

        verify(commandService, times(1)).getTasks();
        assertTrue(outContent.toString().contains("Task 1"));
        assertTrue(outContent.toString().contains("Task 2"));

        System.setOut(System.out); // Восстановление стандартного вывода
    }

    @Test
    void execute_ShouldHandleEmptyList() {
        when(commandService.getTasks()).thenReturn(List.of());

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        listCommand.execute();

        verify(commandService, times(1)).getTasks();
        assertEquals("Задач не найдено!", outContent.toString().trim());

        System.setOut(System.out);
    }
}

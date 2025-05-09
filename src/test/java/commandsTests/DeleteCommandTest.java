package commandsTests;

import commands.DeleteCommand;
import model.Status;
import model.Task;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.CommandService;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeleteCommandTest {

    @Mock
    private CommandService commandService;

    @Mock
    private Scanner scanner;

    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(System.out);
    }

    @Test
    void testExecute_validTaskNumber_deletesTaskAndPrintsTasks() {
        Task task1 = new Task("Название 1", "Описание 1", LocalDate.of(2025, 5, 10), Status.TODO);
        Task task2 = new Task("Название 2", "Описание 2", LocalDate.of(2025, 6, 1), Status.IN_PROGRESS);

        List<Task> tasks = List.of(task1, task2);
        when(commandService.getTasks()).thenReturn(tasks);
        when(scanner.nextInt()).thenReturn(2);

        DeleteCommand command = new DeleteCommand(commandService, scanner);
        command.execute();

        verify(commandService).delete(1);

        String output = outContent.toString();

        assertTrue(output.contains("1. Название: Название 1 | Описание: Описание 1 | Дедлайн: 2025-05-10 | Статус: TODO"));
        assertTrue(output.contains("2. Название: Название 2 | Описание: Описание 2 | Дедлайн: 2025-06-01 | Статус: IN_PROGRESS"));
    }
}


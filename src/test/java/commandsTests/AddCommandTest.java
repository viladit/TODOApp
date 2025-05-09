package commandsTests;

import commands.AddCommand;
import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.CommandService;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AddCommandTest {

    @Mock
    private CommandService commandService;

    private AddCommand addCommand;

    @BeforeEach
    void setUp() {
        addCommand = new AddCommand(commandService, new Scanner(System.in));
    }

    @Test
    void execute_ShouldAddTask_WhenValidInputProvided() {
        // Arrange
        String input = "Test Task\nTest Description\n01.01.2023\n1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner testScanner = new Scanner(in);

        AddCommand command = new AddCommand(commandService, testScanner);

        command.execute();

        verify(commandService).add(any(Task.class));
    }

//    @Test
//    void execute_ShouldHandleMinimalInput() {
//        String input = "A\nB\n01.01.2000\n1\n"; // Минимально допустимые значения
//        addCommand.execute();
//        verify(service).add(any(Task.class)); // Убеждаемся, что задача создана
//    }
}

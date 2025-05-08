package model;

import lombok.*;

import java.time.LocalDate;

@Getter @Setter
@AllArgsConstructor
@EqualsAndHashCode
public class Task {
    private String name;
    private String description;
    private LocalDate date;
    private Status status;

    @Override
    public String toString() {
        return "Название: " + name + " | " +
                "Описание: " + description + " | " +
                "Дедлайн: " + date.toString() + " | " +
                "Статус: " + status.name();
    }
}

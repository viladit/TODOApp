# ToDo List Application

A simple console-based task management application built with Java.

## Features

- **View all tasks** in a list
- **Create tasks** with title, description, and due date
- **Edit tasks** (modify title, description, due date, or status)
- **Delete tasks**
- **Filter tasks** by status (todo/in progress/done)
- **Sort tasks** by due name, date or status

## Tech Stack

- Java 17+ (Collections + Streams API)
- JUnit 5 (for unit testing)
- Lombok (for reducing boilerplate code)
- Gradle (build system)
- Git (version control)

## Project Structure
src/
├── main/
│ ├── java/
│ │ ├── commands/ # Commands pakage
│ │ ├── service/ # All of the services
│ │ ├── model/ # Task DTO
│ │ └── AppMain.java # Entry point
│ 
├── test/
│ ├── java/
│ │ ├── commandsTests/ # CommandsTests pakage

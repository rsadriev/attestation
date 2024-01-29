package org.example;

import org.example.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    @DisplayName("Ошибка длинны логина пользователя")
    void loginLengthException() {
        User user = new User("f5a8a3cb-4ac9-4b3b-8a65-c424e129b9d6", LocalDateTime.now(), "noisemc_992123112323123213321123231123",
                "789ghs", "789ghs",
                "Крылов", "Виктор", "Павлович", 25, true);

        assertAll(
                () -> assertEquals(user, App.createUser(user)),
                () -> assertNull(App.deleteById(user.getId()))
        );
    }

    @Test
    @DisplayName("Создание пользователя")
    void passwordMissmatch() {
        User user = new User("f5a8a3cb-4ac9-4b3b-8a65-c424e129b9d6", LocalDateTime.now(), "noisemc_99",
                "789ghs", "987ghs",
                "Крылов", "Виктор", "Павлович", 25, true);

        assertAll(
                () -> assertEquals(user, App.createUser(user)),
                () -> assertNull(App.deleteById(user.getId()))
        );
    }

    @Test
    @DisplayName("Создание пользователя")
    void createUser() {
        User user = new User("f5a8a3cb-4ac9-4b3b-8a65-c424e129b9d6", LocalDateTime.now(), "noisemc_99",
                "789ghs", "789ghs",
                "Крылов", "Виктор", "Павлович", 25, true);

        assertAll(
                () -> assertEquals(user, App.createUser(user)),
                () -> assertNull(App.deleteById(user.getId()))
        );
    }

    @Test
    @DisplayName("Поиск пользователя")
    void findById() {
        User user = new User("f5a8a3cb-4ac9-4b3b-8a65-c424e129b9d6", LocalDateTime.now(), "noisemc_99",
                "789ghs", "789ghs",
                "Крылов", "Виктор", "Павлович", 25, true);
        assertAll(
                () -> assertEquals(user, App.createUser(user)),
                () -> assertEquals(user.toString(), App.findById(user.getId()).toString()),
                () -> assertNull(App.deleteById(user.getId()))
        );
    }

    @Test
    @DisplayName("Выгрузка пользователей")
    void findAll() {
        User user = new User("f5a8a3cb-4ac9-4b3b-8a65-c424e129b9d6", LocalDateTime.now(), "noisemc_99",
                "789ghs", "789ghs",
                "Крылов", "Виктор", "Павлович", 25, true);
        int userListNum = App.findAll();
        assertAll(
                () -> assertEquals(user, App.createUser(user)),
                () -> assertNotEquals(userListNum, App.findAll()),
                () -> assertNull(App.deleteById(user.getId()))
        );
    }

    @Test
    @DisplayName("Обновление пользователя")
    void updateUser() {
        User user1 = new User("f5a8a3cb-4ac9-4b3b-8a65-c424e129b9d6", LocalDateTime.now(), "noisemc_99",
                "789ghs", "789ghs",
                "Крылов", "Виктор", "Павлович", 25, true);

        User user2 = new User("f5a8a3cb-4ac9-4b3b-8a65-c424e129b9d6", LocalDateTime.now(), "noisemc_99",
                "789ghs", "789ghs",
                "Крылов", "Виктор", "Павлович", 26, true);

        assertAll(
                () -> assertEquals(user1, App.createUser(user1)),
                () -> assertEquals(user2, App.updateUser(user2)),
                () -> assertNull(App.deleteById(user1.getId()))
        );
    }

    @Test
    @DisplayName("Поиск по возрасту")
    void findByAge() {
        User user = new User("f5a8a3cb-4ac9-4b3b-8a65-c424e129b9d6", LocalDateTime.now(), "noisemc_99",
                "789ghs", "789ghs",
                "Крылов", "Виктор", "Павлович", 25, true);
        assertAll(
                () -> assertEquals(user, App.createUser(user)),
                () -> assertNotEquals(0, App.findByAge(25)),
                () -> assertNull(App.deleteById(user.getId()))
        );
    }

    @Test
    @DisplayName("Поиск по работе")
    void findByIsWorker() {
        User user = new User("f5a8a3cb-4ac9-4b3b-8a65-c424e129b9d6", LocalDateTime.now(), "noisemc_99",
                "789ghs", "789ghs",
                "Крылов", "Виктор", "Павлович", 25, true);
        assertAll(
                () -> assertEquals(user, App.createUser(user)),
                () -> assertNotEquals(0, App.findByIsWorker(true)),
                () -> assertNull(App.deleteById(user.getId()))
        );
    }

    @Test
    @DisplayName("Удаление пользователя")
    void deleteById() {
        User user = new User("f5a8a3cb-4ac9-4b3b-8a65-c424e129b9d6", LocalDateTime.now(), "noisemc_99",
                "789ghs", "789ghs",
                "Крылов", "Виктор", "Павлович", 25, true);
        assertAll(
                () -> assertEquals(user, App.createUser(user)),
                () -> assertNull(App.deleteById(user.getId()))
        );
    }

    @Test
    @DisplayName("Удаление всех пользователей")
    void deleteAll() {
        assertEquals(0, App.deleteAll());
    }
}
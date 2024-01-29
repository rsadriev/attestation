package org.example;

import org.example.repositories.UsersRepositoryFileImpl;
import org.example.model.User;

import java.time.LocalDateTime;

public class App {
    public static void main(String[] args) {
        UsersRepositoryFileImpl repo = new UsersRepositoryFileImpl();

        //создание
        User newUser = new User("f5a8a3cb-4ac9-4b3b-8a65-c424e129b9d6", LocalDateTime.now(), "noisemc_99",
                "789ghs", "789ghs",
                "Крылов", "Виктор", "Павлович", 25, true);

        System.out.println("Создание пользователя\n" + newUser);
        try {
            repo.create(newUser);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Пользователь успешно создан\n");
        }

        //поиск по id
        System.out.println("Поиск пользователя по id: " + newUser.getId());
        User findUser = null;
        try {
            findUser = repo.findById(newUser.getId());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Пользователь найден:\n" + findUser + "\n");
        }

        //выгрузка всех пользователей.
        System.out.println("Выгрузка всех пользователей");
        var userList = repo.findAll();
        System.out.println("Найдено " + userList.size() + " пользователей\n");

        //обновление
        System.out.println("Обновление возраста пользователя с id: " + newUser.getId() + " на 26");
        newUser.setAge(26);
        repo.update(newUser);
        System.out.println("Обновленный пользователь:\n" + newUser + "\n");

        //поиск по возрасту
        System.out.println("Поиск пользователей с возрастом 25");
        System.out.println("Найдено " + repo.findByAge(25).size() + " пользователей\n");

        //поиск работников предприятия
        System.out.println("Поиск работников предприятия");
        System.out.println("Найдено " + repo.findByIsWorker(true).size() + " пользователей\n");

        //удаление по id
        System.out.println("Удаление пользователя с id: " + newUser.getId());
        repo.deleteById(newUser.getId());
        System.out.println("Поиск пользователя с id: " + newUser.getId());
        try {
            System.out.println(repo.findById(newUser.getId()));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        //удаление всех
        System.out.println("Удаление всех пользователей");
        repo.deleteAll();
        System.out.println("Выгрузка пользователей");
        System.out.println("Найдено " + repo.findAll().size() + " пользователей");
    }

    static User createUser(User user) {
        UsersRepositoryFileImpl repo = new UsersRepositoryFileImpl();

        try {
            repo.create(user);
        } catch (RuntimeException e) {
            System.err.println(e.toString());
            return null;
        }

        return user;
    }

    static User findById(String id) {
        UsersRepositoryFileImpl repo = new UsersRepositoryFileImpl();
        return repo.findById(id);
    }

    static int findAll() {
        UsersRepositoryFileImpl repo = new UsersRepositoryFileImpl();
        return repo.findAll().size();
    }

    static User updateUser(User user) {
        UsersRepositoryFileImpl repo = new UsersRepositoryFileImpl();
        repo.update(user);

        return repo.findById(user.getId());
    }

    static int findByAge(int age) {
        UsersRepositoryFileImpl repo = new UsersRepositoryFileImpl();

        return repo.findByAge(age).size();
    }

    static int findByIsWorker(boolean isWorker) {
        UsersRepositoryFileImpl repo = new UsersRepositoryFileImpl();

        return repo.findByIsWorker(isWorker).size();
    }

    static User deleteById(String id) {
        UsersRepositoryFileImpl repo = new UsersRepositoryFileImpl();

        repo.deleteById(id);

        User user = null;
        try {
            user = repo.findById(id);
        } catch (RuntimeException e) {
            user = null;
        }

        return user;
    }

    static int deleteAll() {
        UsersRepositoryFileImpl repo = new UsersRepositoryFileImpl();

        repo.deleteAll();

        return repo.findAll().size();
    }
}
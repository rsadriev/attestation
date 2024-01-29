package org.example.repositories;

import org.example.model.User;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class UsersRepositoryFileImpl implements UsersRepository {
    @Override
    public void create(User user) {
        user.checkFields();

        if (findUserId(user.getId()) == null) {
            userList.add(user);

            try {
                BufferedWriter w = new BufferedWriter(new FileWriter(filename, true));
                w.append(user.toString()).append("\n");
                w.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new RuntimeException("Пользователь существует");
        }
    }

    @Override
    public User findById(String id) {
        Integer idx = findUserId(id);

        if (idx == null) {
            throw new RuntimeException("Пользователя с заданным идентификатором не существует");
        }

        return userList.get(idx);
    }

    @Override
    public List<User> findAll() {
        return userList;
    }

    @Override
    public void update(User user) {
        user.checkFields();
        Integer idx = findUserId(user.getId());

        if (idx == null) {
            System.out.println("Пользователь отсутствует");
            userList.add(user);
        } else {
            userList.set(idx, user);
        }

        flushList();
    }

    @Override
    public void deleteById(String id) {
        Integer idx = findUserId(id);

        if (idx == null)
        {
            throw new RuntimeException("Пользователя с заданным идентификатором не существует");
        } else {
            userList.remove(idx.intValue());
        }

        flushList();
    }

    @Override
    public void deleteAll() {
        userList.clear();
        flushList();
    }

    public List<User> findByAge(int age) {
        return userList.stream().filter(user -> (user.getAge() != null && user.getAge() == age)).toList();
    }

    public List<User> findByIsWorker(boolean isWorker) {
        return userList.stream().filter(user -> user.getWorker() == isWorker).toList();
    }

    private void flushList() {
        try (FileOutputStream os = new FileOutputStream(filename)) {
            for (User user : userList) {
                os.write((user + "\n").getBytes());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Integer findUserId(String id) {
        for (int i = 0; i < userList.size(); i++)
        {
            if (userList.get(i).getId().equals(id)) {
                return i;
            }
        }

        return null;
    }

    private void initFromFile() {
        userList = new ArrayList<>();

        try {
            File f = new File(filename);
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String l = s.nextLine();

                long sepCount = l.chars().filter(ch -> ch == '|').count();
                if (sepCount != 9)
                {
                    System.err.println("Ошибка разбора строки: " + l);
                    continue;
                }

                String[] args = l.split("\\|");

                String userId = args[0];
                LocalDateTime userDate = LocalDateTime.parse(args[1]);

                String login = args[2];
                if (login.length() > 20) {
                    System.err.println("Длинна логина больше 20 символов: " + login);
                    continue;
                }

                String pass = args[3];
                String confirmPass = args[4];

                if (!pass.equals(confirmPass)) {
                    System.err.println("Пароли не совпадают: " + pass + " " + confirmPass);
                    continue;
                }

                if (pass.length() > 20) {
                    System.err.println("Длинна пароля больше 20 символов: " + pass);
                    continue;
                }

                String lastName = args[5];
                String name = args[6];

                String middleName = args[7];
                if (middleName.isEmpty()) {
                    middleName = null;
                }

                String age = args[8];
                Integer iage;
                if (age.isEmpty()) {
                    iage = null;
                } else {
                    iage = Integer.parseInt(age);
                }

                Boolean isWorker = Boolean.parseBoolean(args[9]);

                User user = new User(userId, userDate, login, pass, confirmPass, lastName, name, middleName, iage, isWorker);

                userList.add(user);

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private List<User> userList;
    private final String filename = "data.csv";

    public UsersRepositoryFileImpl() {
        initFromFile();
    }
}

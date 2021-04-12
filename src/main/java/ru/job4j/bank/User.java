package ru.job4j.bank;

import java.util.Objects;

/**
 * Модель данных представляющая клиента банка.
 * Класс инкапсулирует поля passport и username и имеет открытые методы получения
 * и изменения этих полей.
 *
 * @author  Gromov Anatoliy
 * @version 1.0
 */

public class User {

    private String passport;
    private String username;

    /**
     * Конструктор инициализирующий поля passport и username
     *
     * @param passport an String
     * @param username an String
     */

    public User(String passport, String username) {
        this.passport = passport;
        this.username = username;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(passport, user.passport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passport);
    }
}

package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс реализует банковскую операцию по переводу средств между счетами.
 *
 * @author  Gromov Anatoliy
 * @version 1.0
 */

public class BankService {
    /**
     * Хранилище для клиентов и списков связанных с ними счетов.
     */
    private Map<User, List<Account>> users = new HashMap<>();

    /**
     * Регистрация нового клиента.
     * Метод принимает на вход нового клиента. Если такой клиент отсутствует в базе,
     * то он туда добавляется.
     *
     * @param user новый клиент
     */
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Регистрация нового счета для клиента по паспорту
     * Метод принимает на вход серию и номер паспорта и счет клиента. Если клиент с
     * таким паспортом найден и у него еще нет счета переданного на вход, то счет добавляется
     * в список счетов клиента, если нет счет не добавляется.
     *
     * @param passport серия и номер паспорта
     * @param account новый счет
     */
    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> accounts = users.get(user);
            if (!accounts.contains(account)) {
                accounts.add(account);
            }
        }
    }

    /**
     * Поиск клиента в базе по паспорту.
     * Метод принимает серию и номер паспорта клиента на вход и если клиент с таким
     * паспортом есть в хранилище вернет такого клиента.
     *
     * @param passport  серия и номер паспорта
     * @return User возвращает найденного клиента
     */
    public User findByPassport(String passport) {
        for (User user : users.keySet()) {
            if (user.getPassport().equals(passport)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Поиск счета клиента по паспорту и реквизитам счета.
     * Метод принимает серию и номер паспорта клиента и реквизиты счета который необходимо
     * найти у данного клиента. Если клиент найден и у него имеется счет с данными реквизитами
     * то метод вернёт счёт клиента, иначе вернётся null.
     *
     * @param passport серия и номер паспорта
     * @param requisite реквизиты счета
     * @return Account возвращает найденный счет
     */
    public Account findByRequisite(String passport, String requisite) {
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> accounts = users.get(user);
            for (Account account : accounts) {
                if (account.getRequisite().equals(requisite)) {
                    return account;
                }
            }
        }
        return null;
    }

    /**
     * Перевод средств между счетами.
     * Метод принимает на вход первого клиента и его счет с которого планируется перевод средств,
     * второго клиента и его счет на который нужно сделать перевод и сумму перевода.
     * Если переданные счета найдены и на счету первого достаточно средств, то выполняется
     * перевод и метод возвращает true, если нет перевод не выполняется и метод вернёт false.
     *
     * @param srcPassport серия и номер паспорта первого клиента
     * @param srcRequisite реквизиты счета первого клиента
     * @param destPassport серия и номер паспорта второго клиента
     * @param destRequisite реквизиты счета второго клиента
     * @param amount сумма перевода
     * @return boolean успешность совершения перевода
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite,
                                 double amount) {
        boolean rsl = false;
        Account accountFrom = findByRequisite(srcPassport, srcRequisite);
        Account accountTo = findByRequisite(destPassport, destRequisite);
        if (accountFrom == null && accountTo == null) {
            return false;
        } else if (accountFrom.getBalance() >= amount) {
            accountFrom.setBalance(accountFrom.getBalance() - amount);
            accountTo.setBalance(accountTo.getBalance() + amount);
            rsl = true;
        }
        return rsl;
    }

    public static void main(String[] args) {
        List<Account> accounts = new ArrayList<>();
        String requisite = "3fdsbb9";
        accounts.add(new Account("3fdsbb9", 140));
        int index = accounts.indexOf(new Account(requisite, -1));
        Account find = accounts.get(index);
        System.out.println(find.getRequisite() + " -> " + find.getBalance());
    }
}

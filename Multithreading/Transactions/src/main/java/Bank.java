import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

public class Bank {

    private static final ConcurrentMap<String, Account> accounts = new ConcurrentHashMap<>();
    private final Random random = new Random();
    private static final int MAX_AMOUNT = 50_000;


    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
            throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    public void addAccountToBank(Account account) {
        accounts.put(account.getAccNumber(), account);
    }

    public synchronized void transfer(String fromAccountNum, String toAccountNum, long amount) throws InterruptedException {
        Account fromAccount = accounts.get(fromAccountNum);
        Account toAccount = accounts.get(toAccountNum);
        boolean chek = false;

        if(isEnoughMoney(fromAccount.getMoney(), amount)) {
            if(amount > MAX_AMOUNT) {
                System.out.println("Сумма перевода боольше " + MAX_AMOUNT + "."
                + "\n\tПодождите несколько секунд идет проверка счета.");
                try {
                    chek = isFraud(fromAccountNum, toAccountNum, amount);
                } catch(InterruptedException e) {
                    e.printStackTrace(System.out);
                }
                if(chek) {
                    fromAccount.blockedAccount();
                    toAccount.blockedAccount();
                    System.out.println("Перевод не выполнен. Счета " + fromAccount.getAccNumber() + " и " + toAccount.getAccNumber() + " забокированы");
                }
                else {
                    toAccount.setMoney(toAccount.getMoney() + amount);
                    fromAccount.setMoney(fromAccount.getMoney() - amount);
                }
            }
            else {
                toAccount.setMoney(toAccount.getMoney() + amount);
                fromAccount.setMoney(fromAccount.getMoney() - amount);
            }
        }
    }

    public synchronized long getBalance(String accountNum) {
        return accounts.get(accountNum).getMoney();
    }

    public synchronized void getAccounts() {
        System.out.println(accounts.keySet());
    }

    public boolean isEnoughMoney(long fromAccount, long amount) {
        return fromAccount >= amount;
    }

    public synchronized long getSumAllAccounts() {
        long sumMoney = 0;
        for(Map.Entry<String, Account> item : accounts.entrySet()) {
            sumMoney += item.getValue().getMoney();
        }
        System.out.println("Общая сумма средств на всех счетах: " + sumMoney + "руб.");
        return sumMoney;
    }
}

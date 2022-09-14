import java.util.concurrent.atomic.AtomicLong;

public class Account {

    private long money;
    private String accNumber;
    private static boolean isBlock;

    public Account(String accNumber, long money) {
        this.money = money;
        this.accNumber = accNumber;
        isBlock = false;
    }


    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }

    public boolean getStatus() {
        return isBlock;
    }

    public void blockedAccount() {
        isBlock = true;
        setMoney(0);
    }

    @Override
    public String toString() {
        return "Номер счета: " + getAccNumber()
                + "\n\t, Средства: " + getMoney() + " руб.";
    }
}

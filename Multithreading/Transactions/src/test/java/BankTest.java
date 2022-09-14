import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BankTest {

    Bank bank;
    Account client1;
    Account client2;
    Account client3;

    @Before
    public void setUp() {
        bank = new Bank();
        client1 = new Account("0001", 1_000_000);
        client2 = new Account("0002", 10_000_000);
        client3 = new Account("0003", 100_000_000);

        bank.addAccountToBank(client1);
        bank.addAccountToBank(client2);
        bank.addAccountToBank(client3);
    }

    @Test
    public void testAllSumAllAccounts() {
        long expected = client1.getMoney() + client2.getMoney() + client3.getMoney();
        long actual = bank.getSumAllAccounts();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testTransferBeforeVerification() throws InterruptedException {
        bank.transfer(client1.getAccNumber(), client2.getAccNumber(), 100_000);
        long expectedFirstScore = 900_000;
        long actualFirstScore = bank.getBalance(client1.getAccNumber());
        Assert.assertEquals(expectedFirstScore, actualFirstScore);

        long expectedSecondScore = 10_100_000;
        long actualSecondScore = bank.getBalance(client2.getAccNumber());
        Assert.assertEquals(expectedSecondScore, actualSecondScore);
    }

    @Test
    public void testTransferAfterVerificationLimit() throws InterruptedException {
        bank.transfer(client2.getAccNumber(), client3.getAccNumber(), 1_000_000);
        long expectedSecondScore;
        long expectedThirdScore;
        if(client2.getStatus()) {
            expectedSecondScore = 0;
            expectedThirdScore = 0;
        }
        else {
            expectedSecondScore = 9_000_000;
            expectedThirdScore = 101_000_000;
        }
        long actualSecondScore = bank.getBalance(client2.getAccNumber());
        long actualThirdScore = bank.getBalance(client3.getAccNumber());

        Assert.assertEquals(expectedSecondScore, actualSecondScore);
        Assert.assertEquals(expectedThirdScore, actualThirdScore);
    }

    @Test
    public void testSumAllAccWithBlockedAcc() throws InterruptedException {
        bank.transfer(client3.getAccNumber(), client1.getAccNumber(), 1_000_000);
        long expectedSum;
        if(client1.getStatus()) {
            expectedSum = bank.getBalance(client2.getAccNumber());
        }
        else {
            expectedSum = bank.getBalance(client1.getAccNumber())
                    + bank.getBalance(client2.getAccNumber())
                    + bank.getBalance(client3.getAccNumber());
        }
        long actualSum = bank.getSumAllAccounts();

        Assert.assertEquals(expectedSum, actualSum);
    }

    @Test
    public void testSumAfterTransfer() throws InterruptedException {
        bank.transfer(client1.getAccNumber(), client2.getAccNumber(), 10_000);
        long expectedSum = 111_000_000;
        long actualSum = bank.getSumAllAccounts();

        Assert.assertEquals(expectedSum, actualSum);
    }

}

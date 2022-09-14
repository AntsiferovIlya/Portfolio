public class Main {

    public static void main(String[] args) throws InterruptedException {
        Bank bank = new Bank();
        Account client1 = new Account("0001", 1_000_000);
        Account client2 = new Account("0002", 10_000_000);

        bank.addAccountToBank(client1);
        bank.addAccountToBank(client2);

        Thread thread1 = new Thread(() -> {
            try {
                bank.transfer(client1.getAccNumber(), client2.getAccNumber(), 51_000);
                bank.transfer(client2.getAccNumber(), client1.getAccNumber(), 1_000);
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(1000);
                bank.transfer(client2.getAccNumber(), client1.getAccNumber(), 1_000);
                bank.transfer(client1.getAccNumber(), client2.getAccNumber(), 1_000);
                System.out.println(Thread.currentThread().getName());
            } catch(InterruptedException e) {
                e.printStackTrace(System.out);
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

    }
}

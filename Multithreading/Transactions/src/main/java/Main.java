public class Main {

    public static void main(String[] args) throws InterruptedException {
        Bank bank = new Bank();
        Account client1 = new Account("0001", 1_000_000);
        Account client2 = new Account("0002", 10_000_000);
        Account client3 = new Account("0003", 10_000);

        bank.addAccountToBank(client1);
        bank.addAccountToBank(client2);
        bank.addAccountToBank(client3);

        Thread thread1 = new Thread(() -> {
            try {
                bank.transfer(client1.getAccNumber(), client2.getAccNumber(), 51_000);
                bank.transfer(client2.getAccNumber(), client1.getAccNumber(), 1_000);
                System.out.println("Нулевой поток (" + Thread.currentThread().getName() + ")");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(1000);
                bank.transfer(client2.getAccNumber(), client1.getAccNumber(), 1_000);
                bank.transfer(client1.getAccNumber(), client2.getAccNumber(), 1_000);
                System.out.println("Первый поток (" + Thread.currentThread().getName() + ")");
            } catch(InterruptedException e) {
                e.printStackTrace(System.out);
            }
        });

        Thread thread3 = new Thread(() -> {
            try {
                bank.transfer(client3.getAccNumber(), client2.getAccNumber(), 1_000);
                bank.transfer(client2.getAccNumber(), client3.getAccNumber(), 1_000);
                System.out.println("Второй поток (" + Thread.currentThread().getName() + ")");
            } catch (InterruptedException e) {
                e.printStackTrace(System.out);
            }
        });

        Thread thread4 = new Thread(() -> {
            try {
                bank.transfer(client2.getAccNumber(), client3.getAccNumber(), 1_000);
                bank.transfer(client3.getAccNumber(), client2.getAccNumber(), 1_000);
            } catch(InterruptedException e) {
                e.printStackTrace(System.out);
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();

    }
}

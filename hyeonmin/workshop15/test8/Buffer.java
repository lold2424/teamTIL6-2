package workshop15.test8;

public class Buffer {
    private Integer data = null;

    public synchronized void produce(int value) {
        try {
            while (data != null) {
                wait();
            }
            data = value;
            System.out.println("생산됨: " + value);
            notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void consume() {
        try {
            while (data == null) {
                wait();
            }
            System.out.println("소비됨: " + data);
            data = null;
            notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

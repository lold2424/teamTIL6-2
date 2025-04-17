package workshop15.test4;

public class SleepThread extends Thread{

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("출력: " + i);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

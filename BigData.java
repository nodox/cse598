import java.util.*;

class RunnableProcess implements Runnable {
    private int[] data;

    public RunnableProcess(int[] data) {
        this.data = data;
    }

    public void run() {
        try {

            long startTime = System.nanoTime();

            for (int i = 0; i < data.length; i++) {
              Arrays.sort(data[i]);
            }

            long endTime = System.nanoTime();
            // milliseconds?
            double  elapsedTime = (double) (endTime - startTime) / 1000000;
            //System.out.println("thread time: " +  elapsedTime);


        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}

class BigData {

  static int[][] data = new int[1000][1000];

  public static void main(String [] args) {


    // Create records 
    for (int i = 0; i < data.length; i++) {
      for (int j =0; j < data[0].length; j++) {
        data[i][j] = new Random().nextInt(999);
      }
    }

    // maybe I need this?
    // ExecutorService executor = Executors.newFixedThreadPool(2);

    

    long startTime = System.nanoTime();


     // Thread thread = new Thread(new RunnableProcess(data[]));
     // thread.start();

     // Thread thread = new Thread(new RunnableProcess(data[i]));
     // thread.start();



    long endTime = System.nanoTime();
    double  elapsedTime = (double) (endTime - startTime) / 1000000;
    System.out.println("time: " + elapsedTime + " ms");


  }


}
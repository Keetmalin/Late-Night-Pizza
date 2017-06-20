/**
 * Created by Keetmalin on 6/18/2017
 * Project - Late-Night-Pizza
 * Thread Class for KamalPizzaDelivery Thread
 */
public class KamalPizzaDelivery implements Runnable {
    @Override
    public void run() {

        try {
            while (true) {

                //waiting for a student to call and order
                Variables.wantPizza.acquire();

                //Produce and deliver Pizza
                deliver();

                //Grab lock and update the sliceCount variable
                Variables.lock.lock();

                //add s number of slices to Pizza
                Variables.sliceCount = Variables.s;


                wakeUpStudents();

                //wake up the sleeping students
                Variables.sleepStudents.signalAll();

                //release lock
                Variables.lock.unlock();

            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    //Delivering Pizza
    private void deliver() throws InterruptedException {
        System.out.println("---------Delivering Pizza----------");
        Thread.sleep(500);
    }

    //waking up all students
    private void wakeUpStudents() {
        System.out.println("-----------------wake up all students----------------\n");
    }
}

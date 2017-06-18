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
                deliever();

                //Grab lock and update the sliceCount variable
                Variables.mutex.acquire();
                Variables.sliceCount = Variables.s;
                Variables.mutex.release();

                wakeUpStudents();
                //wake up the sleeping students
                Variables.students.release(Variables.s);


            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void deliever() throws InterruptedException {
        System.out.println("---------Delivering Pizza----------");
        Thread.sleep(500);
    }

    private void wakeUpStudents() {
        System.out.println("-----------------wake up all students----------------\n");
    }
}

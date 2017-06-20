/**
 * Created by Keetmalin on 6/18/2017
 * Project - Late-Night-Pizza
 * Thread Class for Students
 */
public class Student implements Runnable {

    private int studentIndex;
    private boolean calledPizza = false; // this is to check whether a student called the Pizza Guy or not

    //intialize student with a ThreadID
    Student(int index) {
        this.studentIndex = index;
    }

    @Override
    public void run() {
        try {

            while (true) {

                //grab lock to update and access the sliceCount variable
                Variables.sleep.lock();

                //if the pizza is gone
                if (Variables.sliceCount == 0 && !calledPizza){
                    //the first person to see that the pizza is missing will make thecall
                    System.out.println("Pizza Ordering call made by Student: " + this.studentIndex);
                    //make the call
                    Variables.wantPizza.release();
                    calledPizza = true;

                }
                else if (Variables.sliceCount == 0){
                    Variables.sleepStudents.await();
                }

                //one slice picked by a student
                else{
                    Variables.sliceCount--;
                    calledPizza = false;
                    grabPizzaSlice();
                    eatPizzaAndStudy();
                }


                //let go of the lock and exit critical section
                Variables.sleep.unlock();

                Thread.sleep(3000);

            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void grabPizzaSlice() throws InterruptedException {
        System.out.println("student: " + this.studentIndex + " - Grabbed Pizza Slice");
        System.out.println("Only " + Variables.sliceCount + " / " + Variables.s + " Remaining");
    }

    private void eatPizzaAndStudy() throws InterruptedException {
        System.out.println("student: " + this.studentIndex + " - is eating and studying\n------------\n");
        Thread.sleep(500);
    }

}

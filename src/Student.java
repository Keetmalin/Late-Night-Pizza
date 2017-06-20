/**
 * Created by Keetmalin on 6/18/2017
 * Project - Late-Night-Pizza
 * Thread Class for Students
 */
public class Student implements Runnable {

    //to assign an ID to each student
    private int studentIndex;
    // this is to check whether a student called the Pizza Guy or not
    private static boolean calledPizza = false;

    //initialize student with a ThreadID
    Student(int index) {
        this.studentIndex = index;
    }

    @Override
    public void run() {
        try {

            while (true) {

                //grab lock to update and access the sliceCount variable
                Variables.lock.lock();

                //if the pizza is gone and no one has called yet
                if (Variables.sliceCount == 0 && !calledPizza){
                    //the first person to see that the pizza is missing will make the call
                    System.out.println("Pizza Ordering call made by Student: " + this.studentIndex);
                    //make the call
                    Variables.wantPizza.release();
                    calledPizza = true;
                }

                //if pizza is empty, but someone has already made a call (includes the person who made the call
                if (Variables.sliceCount == 0){
                    //Student will Go to sleep
                    Variables.sleepStudents.await();
                }

                //one slice picked by a student
                else{
                    //reduce slice count
                    Variables.sliceCount--;
                    calledPizza = false;
                    //grab pizza slice
                    grabPizzaSlice();
                    //eat and study pizza slice
                    eatPizzaAndStudy();
                }


                //let go of the lock and exit critical section
                Variables.lock.unlock();

                //take some time and repeat
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

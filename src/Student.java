/**
 * Created by Keetmalin on 6/18/2017
 * Project - Late-Night-Pizza
 * Thread Class for Students
 */
public class Student implements Runnable {

    private int studentIndex;

    //intialize student with a ThreadID
    Student(int index) {
        this.studentIndex = index;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);

            while (true) {

                //grab lock to update and access the sliceCount variable
                Variables.mutex.acquire();

                //if the pizza is gone
                if (Variables.sliceCount == 0 && !Variables.calledPizza){
                    //the first person to see that the pizza is missing will make thecall
                    System.out.println("Pizza Ordering call made by Student: " + this.studentIndex);
                    //make the call
                    Variables.wantPizza.release();
                    Variables.calledPizza = true;

                }
                //let go of the lock and exit critical section
                Variables.mutex.release();

                //students approach the pizza, if no pizzas, go to sleep, else procees
                Variables.students.acquire();
                Variables.calledPizza = false;

                //acquire lock so that students can eat one slice at a time and decrement sliceCount
                Variables.mutex.acquire();
                //one slice picked by a student
                Variables.sliceCount--;
                grabPizzaSlice();

                eatPizzaAndStudy();
                //let go of the lock and exit critical section
                Variables.mutex.release();



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

/**
 * Created by Keetmalin on 6/18/2017
 * Project - Late-Night-Pizza
 * Main class for Thread Invoking
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {

        //create KamalPizzaDelivery Thread and Start
        KamalPizzaDelivery delivery = new KamalPizzaDelivery();
        Thread pizzaDelivery = new Thread(delivery);
        pizzaDelivery.start();

        //create Student Threads and store in a thread array
        Thread[] students = new Thread[Variables.allStudents];

        //assign a ThreadID for students and start each of them
        for (int i = 0; i < Variables.allStudents; i++) {
            Student student = new Student(i);
            students[i] = new Thread(student);
            students[i].start();
        }

    }
}

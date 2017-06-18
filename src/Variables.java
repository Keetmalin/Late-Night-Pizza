import java.util.concurrent.Semaphore;

/**
 * Created by Keetmalin on 6/18/2017
 * Project - Late-Night-Pizza
 * Thread Class for Shared Variables
 */
class Variables {

    static int allStudents = 15; // 15 students are in the room studying
    static int sliceCount = 0; //number of slices : initially 0
    static int sleepCount = 0; //number of student sleeping
    static int s = 8;   //user defined values, for maximum number of slices in a pizza

    static Semaphore wantPizza = new Semaphore(0);  //set to 0, assuming that initially no delivery
    static Semaphore mutex = new Semaphore(1);  //mutex lock to access slice count
    static Semaphore students = new Semaphore(0);  //A semaphore to represent children

    static boolean calledPizza = false;


}

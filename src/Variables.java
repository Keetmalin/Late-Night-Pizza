import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Keetmalin on 6/18/2017
 * Project - Late-Night-Pizza
 * Thread Class for Shared Variables
 */
class Variables {

    static int allStudents = 15; // 15 students are in the room studying
    static int sliceCount = 0; //number of slices : initially 0
    static int s = 8;   //user defined values, for maximum number of slices in a pizza

    //this Semaphore represents the call made by students signalling that they want pizza
    static Semaphore wantPizza = new Semaphore(0);  //set to 0, assuming that initially no delivery



    static Lock lock = new ReentrantLock(); //This lock will ensure Mutual Exclusion to
    static Condition sleepStudents = lock.newCondition(); //This is the conditional Variable that will put students to lock

}



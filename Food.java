import java.util.PriorityQueue;
import java.util.Scanner;

/*
Name: Joseph Nied
Date: 2/12/20
Class: CSC-261
Question: 4
Description: Algorithm to see if there is a way to use all food without expiring
 */

public class Food {
    //Global Variable needed for this problem
    //Keeps track of how many days pass
    static int day = 0;

    public static class item implements Comparable{
        private int arrival, time, priority;

        public item(int arrival, int time){
            this.arrival = arrival;
            this.time =time;
            this.priority = time + arrival;
        }

        //Getters
        public int getArrival() { return arrival; }
        public int getTime() { return time; }
        public int getPriority() { return priority; }

        @Override
        public int compareTo(Object o) {
            if(o instanceof item) {
                if(((item) o).arrival==day){
                    return -1;
                }
                if(((item) o).arrival < this.arrival){
                    return 1;
                }
                else{
                    if (((item) o).arrival == this.arrival) {
                        if (((item) o).priority < this.priority) {
                            return 1;
                        } else if (((item) o).priority > this.priority) {
                            return -1;
                        }
                    }
                }
            }

            return 0;
        }
    }
    public static void main(String[] args) {
        PriorityQueue<item> minHeap = new PriorityQueue<item>();
        //Create a scanner for input
        Scanner scanner = new Scanner(System.in);
        //Retrieves the size of the input data
        int SIZE = scanner.nextInt();

        for(int i = 0; i < SIZE; i++){
            int arr = scanner.nextInt();
            int time = scanner.nextInt();
            minHeap.add(new item(arr,time));
        }

        scanner.close();

        //Boolean variable to keep track if any food will expire no matter what
        boolean isValid = true;

        //Adds another item that has the priotirty of the Integer.MAX_VALUE, since all other entries have to be
        //less than this. Allows us to keep track if the heap is empty or not
        item last = new item(Integer.MAX_VALUE,0);
        minHeap.add(last);

        //Not O(nlogn) but close
        while(isValid&&minHeap.contains(last)){
            item x = minHeap.remove();
            if(x.getPriority() == last.getPriority())
                break;
            else {
                if (x.getPriority() <= day) {
                    isValid = false;
                }
            }
            //Wanted to implement this:
            //day += x.getArrival() - day;
            //Sadly could not figure out how to make this fit in with the rest of my code
            //there for the algorithm does not run in O(nlogn)
            day++;
        }
        if(isValid)
            System.out.println("YES");
        else
            System.out.println("NO");

    }


}

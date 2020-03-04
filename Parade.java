import java.util.PriorityQueue;
/*
Name: Joseph Nied
Date: 2/12/20
Class: CSC-261
Question: 5
Description: Algorithm to see if people in a parade will lose their patience while getting organized
 */
import java.util.Scanner;

public class Parade {
    public static class Person{
        private int place, patience;

        public Person(int place, int patience) {
            this.place = place;
            this.patience = patience;
        }

        //Getters
        public int getPlace() {
            return place;
        }

        public int getPat() {
            return patience;
        }

        //Decrements the person's patience by X
        public void decPat(int x) {
            patience = patience - x;
        }

    }



        public static void main(String[] args) {
            //Create a scanner for input
            Scanner scanner = new Scanner(System.in);
            //Retrieves the size of the input data
            int SIZE = scanner.nextInt();
            //Creates an array with size equalling SIZE and members of the array being people objects
            Person[] people = new Person[SIZE];
            for (int i = 0; i < SIZE; i++) {
                int place = scanner.nextInt();
                int pat = scanner.nextInt();
                people[i] = new Person(place, pat);
            }
            //Closes the scanner
            scanner.close();
            //prints the answer
            System.out.println(getInOrder(people));
        }


        public static int getInOrder(Person[] participants) {
            //Variable to count how many people run out of patience
            int answer = 0;
            Person[] endResult;
            //Sort the participants
            //This merge sort is similiar to HW1 but has edits to use Persons in stead and to try and calculate if they
            //will run out of patience
            endResult = MergeSort(participants);

            //increment answer if the person lost patience
            for (int i = 0; i < endResult.length; i++) {
                if (endResult[i].getPat() < 0) {
                    answer++;
                }
            }
            return answer;
        }

        private static Person[] MergeSort(Person data[]) {
            //Base Case of size of array = 1
            if (data.length == 1) {
                return data;
            } else {
                //Sets the Middle index
                int middle = data.length / 2;
                //Splits the array into two almost equal halves
                //A half
                Person A[] = new Person[middle];
                for (int i = 0; i < middle; i++)
                    A[i] = data[i];
                A = MergeSort(A);   //Recursive call to split the array until it is just single elements
                //B half
                Person B[] = new Person[data.length - middle];
                for (int k = middle; k < data.length; k++)
                    B[k - middle] = data[k];
                B = MergeSort(B);   //Recursive call to split the array until it is just single elements
                //Merge the two sorted arrays
                return Merge(A, B);
            }
        }

        //Is re-implemented merge from HW 1 but edited to use people's patience and place instead
        private static Person[] Merge(Person A[], Person B[]) {
            int move = 0;
            //Temp variables for each array to parse through
            int tempA = 0, tempB = 0;
            //Variable to parse through the new sorted array
            int pointer = 0;

            //Array used to store values of A and B so that this array is now sorted
            Person Merged[] = new Person[A.length + B.length];

            //Runs until A or B is empty
            while (tempA < A.length && tempB < B.length) {
                //Inserts A if A is smaller than B
                if (A[tempA].getPlace() < B[tempB].getPlace()) {
                    Merged[pointer] = A[tempA];
                    //Decrement the patience:: a way of implementing inversions
                    Merged[pointer].decPat((tempB));
                    tempA++;
                }
                //Insert B if B is smaller than A
                else {
                    Merged[pointer] = B[tempB];
                    //Decrement the patience:: a way of implementing inversions
                    Merged[pointer].decPat((A.length - tempA));
                    tempB++;
                }
                pointer++;
            }
            //If B is not empty, add remaining elements of B to the sorted array(Merged)
            while (tempB < B.length) {
                Merged[pointer] = B[tempB];
                pointer++;
                tempB++;
            }
            //If A is not empty, add remaining elements of A to the sorted array(Merged)
            while (tempA < A.length) {
                Merged[pointer] = A[tempA];
                //Decrement the patience:: a way of implementing inversions
                Merged[pointer].decPat((B.length));
                pointer++;
                tempA++;
            }
            return Merged;
        }


    }


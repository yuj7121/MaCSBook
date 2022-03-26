/**
 * This program is the macsbook
 *
 * @author  Yujin Bae
 * @version 1.0
 * @since   2022-03-01
 */

import java.util.*;
import java.io.*;

public class MaCSBook {
    //variable declarations
    private Scanner s = new Scanner(System.in);  //scanner that gets input from the keyboard
    private String fileName;   //the name of the file we will read and write
    private ArrayList<String[]> students = new ArrayList<String[]>(); //the arraylist that will store students as arrays

    public static void main(String[] args) {
        MaCSBook a = new MaCSBook();
        a.menu();
    }

    /**
     * This method will save me the trouble of converting a
     * string into an int every time. It takes in a string and
     * it will return the string as an int. If the value is not
     * whithin the range, it will automatically manipulate it
     * to be in range. If value is not an int, it's zero.
     * @param m This paramter is the string we want to convert
     * @return int This returns the m as an integer.
     */
    private int convert(String m){ //takes in the string
        int mark;   //this is the mark value this method will return
        try{
            mark = (int) (Double.parseDouble(m) + 0.5); //rounds and assigns the rating from 2d array into rate
            if (mark < 0){ //if the value is less than 0
                System.out.println("*Value less than range, value set to 0. ");
                mark = 0;
            } else if (mark > 100){  //is the value is greater than 100
                System.out.println("*Value greater than range, value set to 100. ");
                mark = 100;
            } //end of if
        } catch (Exception e) {   //if the value is not a number
            System.out.println("*Value is not a number. Therfore, value of 0 was automatically assigned to it. ");
            mark = 0;
        }  //end of try catch block
        return mark;      //returns the mark
    }  //end of the convert method

    /**
     * this method will open a file based on the file name given
     * by the user and load the data into arrays.
     */
    private void loadFile ()
    {
        Scanner file;
        ArrayList<String> temp = new ArrayList<String>();  //a temporary arraylist to stores the whole file


        //get the file name from the user
        System.out.println ("Please enter the file name. ");
        fileName = s.nextLine ();
        System.out.println ();

        //tries to open the file
        try
        {
            file = new Scanner(new File(fileName));
            String student[] = new String[6];
            // this loop continues until it has read all the data in the file
            for (int i = 0; file.hasNext(); i++)
            {
                switch (i%6)
                {
                    case 0:
                        student[0] = file.nextLine();   //reads in the student's name
                        break;
                    case 1:
                        student[1] = file.nextLine();   //reads in the student's number
                        break;
                    case 2:
                        student[2] = convert(file.nextLine())+"";   //reads in the student's assignment mark, convert it to be within the range, adn store it as a string
                        break;
                    case 3:
                        student[3] = convert(file.nextLine())+"";   //reads in the student's text mark, convert it to be within the range, adn store it as a string
                        break;
                    case 4:
                        student[4] = convert(file.nextLine())+"";   //reads in the student's final project mark, convert it to be within the range, adn store it as a string
                        break;
                    case 5:
                        file.nextLine();
                        //we're not reading in the average
                        //instead, we calculate the student's average ourselves and store it in as a string
                        student[5] = (int) ( ( convert(student[2]) + convert(student[3]) + convert(student[4]) ) /3.0 + 0.5 ) + "";
                        //add this student array to the students arraylsit
                        students.add(student);
                        //create a new string object for student to avoid persistant destruction of data
                        student = new String[6];
                        break;
                } //end of switch
            }  //end of while
        }  //end of try
        catch (IOException e)
        {
            System.out.println ("*File cannot be found! A new file was created. ");
        }  //end of catch
    } //end of load file method

    /**
     * This method will allow the user to input a student's information
     */
    private void input(){
        String input;
        String[] student = new String[6];
        boolean incorrect = true;

        System.out.println();
        //take in the name
        System.out.println("Please Type in the name of the student. ");
        student[0] = s.nextLine();
        //take in the student number
        System.out.println("Please type in the student number. ");
        student[1] = s.nextLine();
        //take in the assignment mark
        System.out.println("Please type in the assignment mark. ");
        student[2] = convert(s.nextLine())+""; //takes input, convert it to be within range, then store is as a string
        //takes in the test mark
        System.out.println("Please type in the test mark. ");
        student[3] = convert(s.nextLine())+""; //takes input, convert it to be within range, then store is as a string
        //takes in the final project mark
        System.out.println("Please type in the final project mark. ");
        student[4] = convert(s.nextLine())+""; //takes input, convert it to be within range, then store is as a string
        //calculate the average and store it in a as a string
        student[5] = (int) ( (convert(student[2]) + convert(student[3]) + convert(student[4])) /3.0 + 0.5) + "";
        students.add(student);  //add this student to the students
        //ask if the user wants to input a new student or not
    }  //end of input method

    /**
     * This method will display all students and the class average
     */
    private void createData(){
        boolean go  = true;  //bolean variable that determines whther the loop should keep going or not
        String str;    //temporary variable storing the user's input
        int choice = 0;   //the choice the user made

        //ask user if they want to load data from a file or input the data themselves
        System.out.println("How would you like to add data?");
        System.out.println("1 - Add students from a file");
        System.out.println("2 - Add a student manually");

        while (go){   //continues taking user input until the user inputs either 1 or 2
            System.out.println("Please type in your choice");
            str = s.nextLine();   //takes in an integer from keyboard input
            choice = convert(str);  //convert the string into an int
            if (choice==1 || choice==2){     //if it's whithin 1 to 2
                go = false;   //go is false and loop ends
            } else {    //if the input is not whithin range
                System.out.println("*That it not an option! Please Try again. ");
            }  //end of else
        }  //end of while go

        if (choice == 1){    //if the user wanted to load a file
            loadFile();    //call the loadfile method to load in a file
        } else { //if the user wanted to input a student themselves
            go = true;  //go is set to true so that the loop can start to run
            while (go){
                input();    //takes user input
                System.out.println("\n1 - Input another Student");   //ask user if they wanna inpt another student
                System.out.println("2 - Back to the main menu");   //or if they wanna go back to the main menu
                if (convert(s.nextLine()) != 1){ //if the input is either 1 or 2
                    go = false;
                } else {
                    System.out.println("*You will be taken back to the main menu. ");
                } //end of else
            }  //end of while go
        }  //end of else
    } //end of create data method

    /**
     * This method will display all students and the class average
     */
    private void viewStudents(){
        int sum = 0; //the sum of avergaes of all students in this class

        System.out.println ("Let's see all the students! ");
        System.out.println();
        System.out.println("The size of this class is: " + students.size());
        System.out.println();
        System.out.println("Below are the informations of students in the following order");
        System.out.println("Name");
        System.out.println("Student Number");
        System.out.println("Assignment Mark");
        System.out.println("Test Mark");
        System.out.println("Final Project Mark");
        System.out.println("Student Average");
        System.out.println();
        for (String[] student : students){  //for each student
            for (String info : student){   //for each info of that student
                System.out.println(info);   //print it out
            }  //end of student info for each loop
            sum += convert(student[5]);   //add that student's average to the sum
            System.out.println();
        }  //end of the big for each loop
        System.out.println();
        System.out.println("The Class Average is: " + sum / students.size() );
    }  //end of view students method

    /**
     * This method will allow the user to select a specific student by either their name of student number
     * @return int This returns the index number of the student the user selected.
     */
    private int selectStudent(){
        int index = 0;  //the index numner of the student user selected.
        boolean go = true; //whether to continue the loop or not;
        int choice = 0; //the choice the user made(whether to choose by name or student number
        String str; //the name or the student number taken in from the user

        while (go){
            System.out.println("1 - Select the student by their name. ");
            System.out.println("2 - Select the student by their student number. ");
            System.out.println("Please type in your choice");
            choice = convert(s.nextLine());   //takes in an integer from keyboard input and convert to an int
            if (choice>=1 && choice<=2){     //if it's whithin 1 to 2
                go = false;   //go is false and loop ends
            } else {    //if the input is wrong
                System.out.println("*That it not an option! Please Try again. ");
            }
        } //end of while go loop

        go = true;  //set go to true so the loops below can run
        if (choice == 1){  //if the user wants to find student by name
            while (go){
                System.out.println("Input the name of the student. ");
                str = s.nextLine(); //take in the name
                for (String[] student : students){   //for each student in the arraylist,
                    if (str.equals( student[0] )){ //check if the str equals their name
                        index = students.indexOf(student); //if so, return the index of that student
                        return index;
                    } //end of if
                }  //end of for each loop
                System.out.println("*Couldn't find the student! Try again. ");
            } //end of while go loop
        } else { //if the user wants to find student by student number
            while (go){
                System.out.println("Input the student number of the student. ");
                str = s.nextLine(); //take in the student number
                for (String[] student : students){   //for each student in the arraylist,
                    if (str.equals( student[1] )){    //check if str equals the student's student number
                        index = students.indexOf(student);
                        return index;
                    }  //end of if
                }  //end of for each loop
                System.out.println("*Couldn't find the student! Try again. ");
            } //end of while go loop
        }  //end of else
        return index;
    } //end of select student method

    /**
     * This method will allow the user to view a single student
     * @param index This paramter is the index of the specific student we want to view
     */
    private void viewStudent(int index){
        System.out.print("\nBelow are the informations of the student in the following order");
        System.out.println("Name");
        System.out.println("Student Number");
        System.out.println("Assignment Mark");
        System.out.println("Test Mark");
        System.out.println("Final Project Mark");
        System.out.println("Student Average");
        System.out.println();
        for (String info : students.get(index)){  //for each info of the student at index
            System.out.println(info);  //print out that info
        }
    } //end of view student method

    /**
     * This method will save all the data in thte arraylist students into the file
     */
    private void save(){
        PrintWriter p; //the pritnwriter we will use to write to the file
        //tries to open the file
        try{
            p = new PrintWriter (new FileWriter (fileName) );
            for (String[] student : students){
                for (String str : student){
                    p.println(str);
                }  //end of for each str in student
            }  //end of for each student in students
            p.close();
        } catch (Exception e){
            System.out.println("*There is no file to save");
        }  //end of try catch block
    }

    /**
     * This method will allow the user to correct the mark of a single student
     * @param index This paramter is the index of the specific student we want to change a mark
     */
    private void correctStudent(int index){
        boolean go = true;   //boolena variable to make the loop going
        int choice = 0;          //the choice the user inputs (which mark to change)
        String[] student = students.get(index);   //refer to the student we will update
        System.out.println("\nWhich mark would you like to change?");
        System.out.println("1 - Test Mark");
        System.out.println("2 - Assignment Mark");
        System.out.println("3 - Final Project Mark");
        while (go){
            System.out.println("Please type in your choice");
            choice = convert(s.nextLine());  //take in the input, convert to an int and store it to choice
            if (choice >= 1 && choice <= 3){ //if the input is whithin range;
                go = false; //end the loop
            } else { //if the input in wrong
                System.out.println("*That it not an option! Please Try again. ");
            }  //end of else
        }  //end of while go loop

        System.out.println("Please type in the new mark to be corrected");
        //takes in a mark, convert it to an int whithin range, then update it as a string to the newstudent
        student[choice+1] = convert(s.nextLine())+"";
        //calculate the updated average and sotre it as a string
        student[5] = (int) ( (convert(student[2]) + convert(student[3]) + convert(student[4])) /3.0 + 0.5) + "";
    } //end of view student method

    public void menu(){
        boolean exit = false;   //whether to exit the program or not
        int choice = 0;   //the choice the user made
        String str;    //temporary variable storing the user's input
        boolean go; //whether the user inputted a correct int whithin 1 to 5
        do {
            System.out.println ("Program MaCSBook");
            System.out.println ("Menu Options: ");
            System.out.println ("1 - Create a Data");
            System.out.println ("2 - View All Students");
            System.out.println ("3 - View a Specific Student");
            System.out.println ("4 - Correct a Student's Mark");
            System.out.println ("5 - Exit the program");
            System.out.println ();

            go = true;  //go is set to true so the loop will start to run
            while (go){   //continues taking input unless the user inputs an int between 1 to 5
                System.out.println("Please type in your choice");
                str = s.nextLine();   //takes in an integer from keyboard input
                choice = convert(str);  //convert the string into an int
                if (choice>=1 && choice<=5){     //if it's whithin 1 to 5
                    go = false;   //go is false and loop ends
                } else {    //if the input is not whithin range
                    System.out.println("*That it not an option! Please Try again. "); //print out error message
                }
            }

            switch (choice)   //for each choice the user made
            {
                case 1:
                    createData();
                    break;
                case 2:
                    if (students.size() == 0) {   //if there is no data in the arralist students
                        System.out.println("*There is no student to view. Please create data and try again. ");
                        break;
                    }
                    viewStudents ();
                    break;

                case 3:
                    if (students.size() == 0) {   //if there is no data in the arralist students
                        System.out.println("*There is no student to view. Please create data and try again. ");
                        break;
                    }
                    //call select student method and use the returned index as parameter for calling viewstudent method
                    viewStudent ( selectStudent() );
                    break;

                case 4:
                    if (students.size() == 0) {   //if there is no data in the arralist students
                        System.out.println("*There is no student to view. Please create data and try again. ");
                        break;
                    }
                    //call select student method and use the returned index as the parameter for calling correct student method
                    correctStudent ( selectStudent() );
                    break;

                case 5:
                    //exits the program
                    System.exit (0);
                    break;

                default:
            }  //end of switch
            save();  //every time the user does soemthing, we are saving our data to the file.

            System.out.println("\nPress the enter key to continue");
            s.nextLine();
        } // end of do
        while (!exit);
    }  //end of menu method
}  //end of class
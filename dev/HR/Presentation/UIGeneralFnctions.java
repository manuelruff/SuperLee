package HR.Presentation;

import HR.Domain.GeneralController;

import java.util.Scanner;

import static Presentation.GeneralUI.scanner;

public class UIGeneralFnctions {
    //private static ReshetInfo info=ReshetInfo.getInstance();
    private static GeneralController generalController = GeneralController.getInstance();
    // function to ask the user for an ID input
    /**
     * a function that asks for an id until its a valid one
     * @return a valid ID of a worker
     */

    public static String AskForWorkerID(){
        boolean IdCheck = false;
        String ID="";
        while(!IdCheck){
            System.out.println("please enter the worker's ID: ");
            // get the new id from the manager
            ID = scanner.nextLine();
            if(!GeneralController.isExistWorker(ID)){
                System.out.println("this worker is not working at our markets! try again");
                continue;
            }
            IdCheck = true;
        }
        return ID;
    }

    /**
     * a function that asks for a branch until its a valid one
     * @return a valid name \ id of a branch
     */
    // function to ask the user for a branch name input
    public static String AskForBranch(){
        boolean BranchCheck=false;
        String BranchName="";
        while (!BranchCheck){
            System.out.println("please enter the branch you want to work on: ");
            //in the beginning there will be a few options, he is supposed to know them
            BranchName = scanner.nextLine();
            BranchCheck= GeneralController.isExistSuper(BranchName);
            if (!BranchCheck){
                System.out.println("this branch doesn't exists, try again!");
                continue;
            }
            BranchCheck = true;
        }
        return BranchName;
    }


    /**
     * a function that asks for a number until its int the given range
     * @param s start of range
     * @param e end of range
     * @return a number in the range
     */
    // function to ask the user for a number in given range input
    public static int AskForNumber(int s,int e){
        int num=0;
        while(true){
            String input= scanner.nextLine();
            try {
                num = Integer.parseInt(input);
                if (num<=e && num>=s){
                    return num;
                }
                else{
                    System.out.println("your input is not in range of: " + s + " - " + e);
                }
            }
            //if he entered something not suitable we will repeat
            catch (Exception e1) {
                System.out.println("invalid input, please try again");
            }
        }
    }

    // the function creates Domain.Worker by given inputs from the user
    /**
     *  a function that asks for a number (int) until we get one
     * @return a number
     */
    // the function ask the user for a number
    public static int AskForIntNumber(){
        boolean flag = true;
        int num=-999;
        while (flag){
            String input = scanner.nextLine();  // Read user input
            //try to change the input to a string
            try{
                num=Integer.parseInt(input);
                flag=false;
                return num;
            }
            //if he entered something not suitable we will repeat
            catch (Exception e){
                System.out.println("invalid input - try again!");
                continue;
            }
        }
        return num;
    }

    /**
     * a function that asks for a number (double) until we get one
     * @return a number
     */
    // the function ask the user for a number
    public static double AskForDoubleNumber(){
        boolean flag = true;
        double num=-999;
        while (flag){
            String input = scanner.nextLine();  // Read user input
            //try to change the input to a string
            try{
                num=Double.parseDouble(input);
                flag=false;
                return num;
            }
            //if he entered something not suitable we will repeat
            catch (Exception e){
                System.out.println("invalid input - try again!");
                continue;
            }
        }
        return num;
    }

    //check that a given number is in a time structure (hours and minuts)
    public static boolean CheckTimeValidate(double start, double end) {
        double start_dec = start - Math.floor(start);
        double end_dec = end - Math.floor(end);
        return (!(start < 0 || start > end || end > 24 || start_dec >= 0.591 || end_dec >= 0.591));
    }
}

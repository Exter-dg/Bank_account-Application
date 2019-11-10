package bankaccountapp;
import utilities.*;

import java.util.Scanner;
import java.io.File;
import java.util.List;
import java.util.LinkedList;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

class BankAccountApp
{
  public static void main(String[] args)
  {
    List<Account> accounts = new LinkedList<Account>(); // list named accounts of object references of Account


    String file = "NewBankAccounts.csv";
    List<String[]> newAccountHolders = CSV.read(file);

    //-----------------------------------------Declaring Variables
    Scanner in = new Scanner(System.in);
    String name = new String();
    String sSN = new String();
    String accountType = new String();
    String temp = new String();
    double initDeposit;
    int choice;
    char ch = 'y';
    //------------------------------------------

    for(String[] accountHolder : newAccountHolders)
    {
       name = accountHolder[0];
       sSN = accountHolder[1];
       accountType = accountHolder[2];
       initDeposit = Double.parseDouble(accountHolder[3]); // to convert string to Double

      if(accountType.equals("Savings"))
      {
        accounts.add(new Savings(name,sSN,initDeposit));
      }
      else if(accountType.equals("Checking"))
      {
        accounts.add(new Checking(name,sSN,initDeposit));
      }
      else
        System.out.println("Error Reading Account Type");
    }


// Writing to new file
    for(Account obj : accounts)
    {
      temp = obj.writeToFile();
      try
      {
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File("AccountInfo.csv"),true));
        bw.write(temp);
        bw.newLine();
        bw.close();
      }
      catch(IOException e)
      {
        e.printStackTrace();
      }
    }

//---------------------_ADDing accountType-------------------------

while(ch == 'y' || ch == 'Y')
{
  System.out.println("Enter 1 to Add Account : ");
  choice = in.nextInt();

  switch(choice)
  {
    case 1:

        System.out.println("Enter Name, Social Security Number, Account type and Initial Deposit : ");
        in.nextLine();
        name = in.nextLine();
        sSN = in.nextLine();//yet to create own exeption if number is not 9 digit
        accountType = in.nextLine();
        initDeposit = in.nextDouble();//must be >=0
        Account obj;

        if(accountType.equals("Savings"))
        {
          obj = new Savings(name,sSN,initDeposit);
        }
        else//add checking
        {
          obj = new Checking(name,sSN,initDeposit);
        }
            temp = obj.writeToFile(); // generate exception of variable not initialize incase of 2 else if




        try
        {
          BufferedWriter bw = new BufferedWriter(new FileWriter(new File("AccountInfo.csv"),true));
          bw.write(temp);
          bw.newLine();
          bw.close();
        }
        catch(IOException e)
        {
          e.printStackTrace();
        }

        break;

    default:
      System.out.println("Invalid Input !");

  }

  System.out.println("Enter Y to continue : ");
  ch = in.next().charAt(0);
}




//------------------------------------------------------------------

//Displaying the account info
    /*for(Account acc : accounts)
    {
      System.out.println("\n*************\n");
      acc.showInfo();
    }*/

}//end of main
}

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
    try
    {
      BufferedWriter b = new BufferedWriter(new FileWriter(new File("AccountInfo.csv"),false));
      b.close();
    }
    catch(IOException e)
    {
      e.printStackTrace();
    }
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
    String accountno;


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
      System.out.println("\n1.Add Account\n2.Transactions\n3.View Account\n4.Display All Accounts\n5.Delete Account\n");
      choice = in.nextInt();

      switch(choice)
      {
        case 1:
            System.out.println("Enter Name, Social Security Number, Account Type, Initial Deposit ");
            in.nextLine();
            name = in.nextLine();
            sSN = in.nextLine();//yet to create own exeption if number is not 9 digit
            accountType = in.nextLine();
            initDeposit = in.nextDouble();//must be >=0
            Account obj;

            if(accountType.equalsIgnoreCase("Savings"))
            {
              obj = new Savings(name,sSN,initDeposit);
              accounts.add(obj);
              temp = obj.writeToFile();
            }
            else if(accountType.equalsIgnoreCase("Checking"))
            {
              obj = new Checking(name,sSN,initDeposit);
              accounts.add(obj);
              temp = obj.writeToFile();
            }
            //write code to handle when account type is none of these

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

        case 2:
            System.out.println("Enter your Account Number ");
            in.nextLine();
            accountno=in.nextLine();
            int amount;
            int flag1=0;
            for(Account acc : accounts)
            {
              if(accountno.equals(acc.accountNumber))
              {
                flag1=1;
                System.out.println("1.Deposit Money\n2.Withdraw Money\n3.Transfer Money");
                choice=in.nextInt();

                switch(choice)
                {
                  case 1:
                    System.out.println("\nEnter the amount you want to deposit ");
                    amount=in.nextInt();
                    acc.deposit(amount);
                    acc.printBalance();
                    updateFile(accounts,temp);
                    break;

                  case 2:
                    System.out.println("\nEnter the amount you want to withdraw ");
                    amount=in.nextInt();
                    acc.withdraw(amount);
                    acc.printBalance();
                    updateFile(accounts,temp);
                    break;

                  case 3:
                    System.out.println("\nEnter the name of the receiver you want to transfer money to ");
                    in.nextLine();
                    String receiver=new String();
                    receiver=in.nextLine();
                    int flag=0;
                    for(Account Acc : accounts)
                    {
                      if(receiver.equalsIgnoreCase(Acc.getName()))
                      {
                        System.out.println("\nEnter the amount you want to transfer ");
                        amount=in.nextInt();
                        acc.transfer(receiver,amount);
                        acc.printBalance();
                        updateFile(accounts,temp);
                        flag=1;
                        break;
                      }
                      if(flag==0)
                      System.out.println("Account with the name "+receiver+" could not be found in the System ");
                    }
                    break;  // to exit from the case 3 of inner switch case
                }
                break;  // to exit from the for each loop once the account number has been found
              }
            }
              if(flag1==0)
              {
                System.out.println("Account with the Account Number "+accountno +" could not be found in the System ");
              }
            break;  // to exit from the outer switch case

        case 3:
            System.out.println("\nEnter your Account Number ");
            in.nextLine();
            accountno=in.nextLine();
            for(Account acc : accounts)
            {
              if(accountno.equals(acc.accountNumber))
              {
                acc.showInfo();
                System.out.println();
              }
            }
            break;

        case 4:
            for(Account acc : accounts)
            {
              System.out.println("\n*************\n");
              acc.showInfo();
            }
            break;

        default:
        System.out.println("Invalid Input !");
      }

      System.out.println("Enter Y to continue : ");
      ch = in.next().charAt(0);
    }
  }//end of main

static void updateFile(List<Account> accounts,String temp)
  {
    try
    {
      BufferedWriter b = new BufferedWriter(new FileWriter(new File("AccountInfo.csv"),false));
      b.close();
    }
    catch(IOException e)
    {
      e.printStackTrace();
    }
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
  }
}

package bankaccountapp;
import utilities.*;

import java.util.Scanner;
import java.io.File;
import java.util.List;
import java.util.LinkedList;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

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


    updateFile(accounts);


    while(ch == 'y' || ch == 'Y')
    {
      //To clear the screen in Linux
      System.out.print("\033\143");
      System.out.println("____________________________________________________________________________________________");
      System.out.println("\n1.  Add Account\n2.  Transactions\n3.  View Account\n4.  Delete Account\n5.  Calculate Interest\n6.  Display All Accounts\n");
      System.out.println("____________________________________________________________________________________________");
      System.out.print("\n\nEnter your Choice : ");
      choice = in.nextInt();


      switch(choice)
      {
          case 1:
                System.out.print("\033\143");

                System.out.println("____________________________________________________________________________________________");
                System.out.print("\nEnter Name : ");
                in.nextLine();
                name = in.nextLine();

                System.out.println("\n____________________________________________________________________________________________");
                System.out.print("\nEnter Social Security Number : ");
                sSN = in.nextLine();//yet to create own exeption if number is not 9 digit

                System.out.println("\n____________________________________________________________________________________________");
                System.out.print("\nEnter Account Type : ");
                accountType = in.nextLine();

                System.out.println("\n____________________________________________________________________________________________");
                System.out.print("\nEnter Intial Deposit Amount : ");
                initDeposit = in.nextDouble();//must be >=0

                System.out.println("\n____________________________________________________________________________________________");
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
                System.out.print("\033\143");
                System.out.println("____________________________________________________________________________________________");
                System.out.print("\nEnter your Account Number : ");
                in.nextLine();
                accountno=in.nextLine();
                int amount;
                int flag1=0;
                for(Account acc : accounts)
                {
                  if(accountno.equals(acc.accountNumber))
                  {
                      flag1=1;
                      System.out.println("\n____________________________________________________________________________________________");
                      System.out.println("\n1.  Deposit Money\n2.  Withdraw Money\n3.  Transfer Money");
                      System.out.println("\n____________________________________________________________________________________________");
                      System.out.print("\nEnter your Choice : ");
                      choice=in.nextInt();



                      switch(choice)
                      {
                          case 1:
                            System.out.println("\n____________________________________________________________________________________________");
                            System.out.print("\nEnter the amount you want to deposit : ");
                            amount=in.nextInt();
                            acc.deposit(amount);
                            acc.printBalance();
                            updateFile(accounts);
                            break;

                          case 2:
                            System.out.println("\n____________________________________________________________________________________________");
                            System.out.print("\nEnter the amount you want to withdraw : ");
                            amount=in.nextInt();
                            acc.withdraw(amount);
                            acc.printBalance();
                            updateFile(accounts);
                            break;

                          case 3:
                            System.out.println("\n____________________________________________________________________________________________");
                            System.out.print("\nEnter the Account Number of the receiver you want to transfer money to : ");
                            in.nextLine();
                            String receiver=new String();
                            receiver=in.nextLine();
                            int flag=0;
                            for(Account Acc : accounts)
                            {
                              if(receiver.equalsIgnoreCase(Acc.getAccountNumber()))
                              {
                                  System.out.println("\n____________________________________________________________________________________________");
                                  System.out.print("\nEnter the amount you want to transfer : ");
                                  amount=in.nextInt();
                                  acc.withdraw(amount);
                                  acc.printBalance();
                                  for(Account Ac : accounts)
                                  {
                                    if(receiver.equals(Ac.getAccountNumber()))
                                    {
                                        Ac.deposit(amount);
                                        Ac.printBalance();
                                        break;
                                    }
                                  }
                                  flag=1;
                                  break;
                              }
                            }
                            if(flag==0)
                            {
                                System.out.println("\n____________________________________________________________________________________________");
                                System.out.println("\nAccount with Account Number "+receiver+" could not be found in the System ");
                            }
                            break;  // to exit from the case 3 of inner switch case

                          default:
                            System.out.println("Invalid Choice !!!");
                      }
                      break;  // to exit from the for each loop once the account number has been found
                  }
                }
                  if(flag1==0)
                  {
                    System.out.println("\n____________________________________________________________________________________________");
                    System.out.println("\nAccount with the Account Number "+accountno +" could not be found in the System ");
                  }
                break;  // to exit from the outer switch case

          case 3:
                System.out.print("\033\143");
                System.out.println("\n____________________________________________________________________________________________");
                System.out.print("\nEnter your Account Number : ");
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
                System.out.print("\033\143");
                // To delete a account from the AccountInfo file
                int flag =0;
                System.out.println("\n____________________________________________________________________________________________");
                System.out.print("\nEnter the account number to be deleted : ");
                in.nextLine();
                accountno=in.nextLine();
                for(Account acc : accounts)
                {
                  if(accountno.equals(acc.accountNumber))
                  {
                      flag = 1;
                      accounts.remove(acc);
                      updateFile(accounts);
                      break;
                  }
                }

                //If account does not exists in the file
                if(flag == 0)
                {
                    System.out.println("\nAccount Does not Exists");
                }
                break;

          case 5:
                System.out.print("\033\143");
                for(Account Acc : accounts)
                {
                  Acc.compound();
                }
                System.out.println("\n____________________________________________________________________________________________");
                System.out.println("\nInterest is Calculated !!!");
                break;

          case 6:
                System.out.print("\033\143");
                for(Account acc : accounts)
                {
                  acc.showInfo();
                }
                break;

          default:
                System.out.println("\nInvalid Input !");
      }

      System.out.println("\nEnter Y to continue : ");
      ch = in.next().charAt(0);
    }
  }//end of main



static void updateFile(List<Account> accounts)
  {
    String temp = new String();
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

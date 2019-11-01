package bankaccountapp;
import utilities.*;

import java.io.File;
import java.util.List;
import java.util.LinkedList;

class BankAccountApp
{
  public static void main(String[] args)
  {
    List<Account> accounts = new LinkedList<Account>(); // list named accounts of object references of Account


    String file = "NewBankAccounts.csv";
    List<String[]> newAccountHolders = CSV.read(file);

    for(String[] accountHolder : newAccountHolders)
    {
      String name = accountHolder[0];
      String sSN = accountHolder[1];
      String accountType = accountHolder[2];
      double initDeposit = Double.parseDouble(accountHolder[3]); // to convert string to Double

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

    for(Account acc : accounts)
    {
      System.out.println("\n*************\n");
      acc.showInfo();
    }

}//end of main
}

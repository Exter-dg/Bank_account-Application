package bankaccountapp;

public class Savings extends Account
{
  private int safetyDepositBoxID;
  private int safetyDepositBoxKey;
  public String accountType;

  public Savings(String name, String sSN , double initDeposit)
  {
    super(name , sSN , initDeposit);
    accountNumber = "1" + accountNumber;
    accountType = new String("Savings");
    setSafetyDepositBox();
  }

  public void setRate()
  {
    rate = getBaseRate() - .25;
  }

  private void setSafetyDepositBox()
  {
    safetyDepositBoxID = (int)(Math.random() * Math.pow(10,3));
    safetyDepositBoxKey = (int)(Math.random() * Math.pow(10,4));
  }

  public void showInfo()
  {
    System.out.println("\n____________________________________________________________________________________________");
    System.out.println("\nAccount Type : Savings");
    super.showInfo();
    System.out.println("Features" +
                      "\nSafety Deposit Box ID : "+safetyDepositBoxID+
                      "\nSafety Deposit Box Key : "+safetyDepositBoxKey);
    System.out.println("\n____________________________________________________________________________________________");

  }

  public String writeToFile()
  {
    String temp = new String();
    temp = getName() + ',' + accountType + ',' + accountNumber + ',' +
           getsSN() + ',' + getBalance() + ',' + safetyDepositBoxID +
           ',' + safetyDepositBoxKey;
    return temp;
  }
}

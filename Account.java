package bankaccountapp;

public abstract class Account implements IBaseRate
{
  private String name;
  private String sSN; //Social Security Number

  protected String accountNumber;//both inherited by savings and checkings
  protected double rate;

  private double balance;
  private static int index = 10000;

  public Account(String name , String sSN , double initDeposit)
  {
    this.name = name;
    this.sSN = sSN;
    balance = initDeposit;
    index++;
    this.accountNumber = setAccountNumber();
    setRate();
  }

  public abstract void setRate();

  // Set accountNumber
  //precedes with 1 in case of Savings and 2 in case of Checking
  //Generated using Last 2 digits of sSN , 5-digit unique Number
  // and random 3 digit Number
  private String setAccountNumber()
  {
    String lastTwoSSN = sSN.substring(sSN.length()-2 , sSN.length());
    int uniqueID = index;
    int rand = (int)(Math.random() * Math.pow(10,3));
    return lastTwoSSN + uniqueID + rand;
  }

  public void showInfo()
  {
    Systemm.out.println("Name : " + name +
                          "\nAccount Number" + accountNumber +
                          "\nBalance" + balance +
                          "\nRate : "+rate
                        );
  }

  public void compound()
  {
    double interest = balance * (rate/100);
    balance+=interest;
  }

  public void printBalance()
  {
    System.out.println("The Balance is : " + balance);
  }

  public void deposit(double amount)
  {
    balance += amount;
  }

  public void withdraw(double amount)
  {
    balance-+amount;
  }

  public void transfer(String receiver , double amount)
  {
    balance-=amount;
  }

}

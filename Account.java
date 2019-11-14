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
    if(rand <100)
      rand+=100;
    return lastTwoSSN + uniqueID + rand;
  }

  public void showInfo()
  {
    System.out.println("\nName                   :   " + name +
                          "\nAccount Number         :   " + accountNumber +
                          "\nSocial Security Number :   " + sSN +
                          "\nBalance                :   " + balance +
                          "\nRate                   :   "+rate
                        );
  }

//------------------Getter methods-----------------//

  public String getName()
  {
    return name;
  }

  public String getsSN()
  {
    return sSN;
  }

  public double getBalance()
  {
    return balance;
  }

//------------------------------------------------//
  //Pseudo method just to call the writeToFile method in child classes using
  //Account class reference to child class object
  public String writeToFile()
  {
    return name;
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
    balance-=amount;
  }

  public void transfer(String receiver , double amount)
  {
    balance-=amount;
  }

}

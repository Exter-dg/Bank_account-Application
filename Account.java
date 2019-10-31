package bankaccountapp;

public abstract class Account implements IBaseRate
{
  String name;
  String sSN; //Social Security Number
  String accountNumber;
  double balance;
  double rate;
  static int index = 10000;

  public Account(String name , String sSN , double initDeposit)
  {
    this.name = name;
    this.sSN = sSN;
    balance = initDeposit;
    index++;
    this.accountNumber = setAccountNumber();
  }


  // Set accountNumber
  //Generated using Last 2 digits of sSN , 5-digit unique Number
  // and random 3 digit Number
  private String setAccountNumber()
  {
    String lastTwoSSN = sSN.substring(sSN.length()-2 , sSN.length());
    int uniqueID = index;
    int rand = (int)(Math.random() * Math.pow(10,3));
    return lastTwoSSN + uniqueID + rand;
  }
}

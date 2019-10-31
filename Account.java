package bankaccountapp;

public abstract class Account implements IBaseRate
{
  String name;
  String sSN; //Social Security Number
  String accountNumber;
  double balance;
  double rate;

  public Account(String name)
  {

  }
}

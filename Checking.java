package bankaccountapp;

public class Checking extends Account
{
  private int debitCardNumber;
  private int debitCardPIN;
 
  Checking(String name, String sSn, double initDeposit)
  {
    System.out.println("ACCOUNT TYPE: CHECKING");
    super(name,ssn,balance);
    accountNumber="2"+accountNumber;
    setDebitCard();
  }

  public void setRate()
  {
    rate=0.15*getBaseRate();
  }
	
  public void showInfo()
  {
    System.out.println("ACCOUNT TYPE:CHECKING");
    super.showInfo();
    System.out.println("DEBIT CARD NUMBER:"+debitCardNumber+"\nDEBIT CARD PIN:"+debitCardPin); 
  }
	
  private void setDebitCard()
  {
    debitCardNumber=(int)(Math.random()*Math.pow(10,12));
    debitCardPin=(int)(Math.random()*Math.pow(10,4));
  }

}

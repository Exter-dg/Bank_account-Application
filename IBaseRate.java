package bankaccountapp;

public interface IBaseRate
{
  default double getBaseRate()
  {
    return 3.6;
  }
}

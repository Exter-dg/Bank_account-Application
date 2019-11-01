package utilities;

import java.util.List;
import java.util.LinkedList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CSV
{
  public static List<String[]> read(String file) // to scan unknown number of accounts
  {
    List<String[]> data = new LinkedList<String[]>();
    String row;

    try
    {
      BufferedReader br = new BufferedReader(new FileReader(file));
      while((row = br.readLine()) != null)
      {
        String records[] = row.split(",");
        data.add(records);
      }
    }
    catch(FileNotFoundException e)
    {
      System.out.println("Could not find File");
      e.printStackTrace();
    }
    catch(IOException e)
    {
      e.printStackTrace();
    }
    return data;
  }
}

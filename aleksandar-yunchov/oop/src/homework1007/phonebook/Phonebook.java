package homework1007.phonebook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Phonebook
{

  //private ArrayList<String> data = new ArrayList<>();
  private Map<String,String>  data = new HashMap<>();


  public String insert(String number, String name)
  {
   // String input = name.concat("-").concat(number);
     return this.data.putIfAbsent(number,name);
  }

//  public void deleteByPhoneNumber(String number)
//  {
//    for (String record : this.data) {
//      String[] splitRecord = record.split("-");
//      if (number.equals(splitRecord[1])) {
//        this.data.remove(record);
//        return;
//      }
//    }
//  }

  public ArrayList<String> getAllNamesStartingWith(String string)
  {
    ArrayList<String> output = new ArrayList<>();
   /* for (String record : this.data) {
      String[] splitRecord = record.split("-");
      if (splitRecord[0].startsWith(string)) {
        output.add(record);
      }
    }*/

    for (String value : this.data.values()) {
      if (value.startsWith(string)){
        output.add(value);
      }
    }

    return output;
  }

  @Override
  public String toString()
  {
    StringBuilder sb = new StringBuilder();
    //this.data.forEach(e -> sb.append(e).append(System.getProperty("line.separator")));
    for (Map.Entry<String, String> entry : this.data.entrySet()) {
      sb.append(entry.getValue()).append(" - ").append(entry.getKey()).append("\n");
    }


    return sb.toString();
  }

  public Map<String,String> getData()
  {
    return data;
  }
}
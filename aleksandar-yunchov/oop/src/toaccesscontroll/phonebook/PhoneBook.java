package toaccesscontroll.phonebook;

import java.util.ArrayList;
import java.util.List;

public class PhoneBook
{
  private List<String> data;

  public PhoneBook()
  {
    this.data = new ArrayList<>();
  }

  public void insert(String name, String number, boolean isExist){
    StringBuilder builder = new StringBuilder();
    if (isExist) {
      for (int i = 0; i < data.size(); i++) {
        String existName = this.data.get(i);
        if (existName.contains(number)) {
          builder.append(name).append("-").append(number);
          this.data.set(i,builder.toString());
          break;
        }
      }
    }else {
      builder.append(name).append("-").append(number);
      data.add(builder.toString());
    }
  }

  public boolean deleteByPhoneNumber(String number){
    boolean isExist = false;
    for (int i = 0; i < data.size(); i++) {
      String[] contact = data.get(i).split("-");
      if (number.equals(contact[1])){
        this.data.remove(this.data.get(i));
        isExist=true;
        break;
      }
    }
    return isExist;
  }

  public List<String> getAllNamesStartingWith(String startWord){
    List<String>saved = new ArrayList<>();
    for (int i = 0; i < this.data.size(); i++) {
      if (this.data.get(i).startsWith(startWord)){
        saved.add(this.data.get(i));
      }
    }
    return saved;
  }

  public List<String> getData()
  {
    return data;
  }

  public void setData(List<String> data)
  {
    this.data = data;
  }

  @Override
  public String toString()
  {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < this.data.size(); i++) {
      builder.append(this.data.get(i)).append("\n");
    }
    return builder.toString();

  }
}

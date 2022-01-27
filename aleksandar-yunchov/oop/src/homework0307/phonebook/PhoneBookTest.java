package homework0307.phonebook;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneBookTest
{
  public static void main(String[] args) throws PhoneBookFullException
  {
    Scanner scanner = new Scanner(System.in);
    String reg = "^(\\+359|0{2}359|0{1})(8[7-9]{1})([2-9]{1})(\\d){6}\\b";
    PhoneBook phoneBook = new PhoneBook();

    while (true) {
      System.out.println("i. Добави нов запис");
      System.out.println("ii. Изтрий запис по номер");
      System.out.println("iii. Покажи всички записи които започват с низ");
      System.out.println("iv. Принтирай toString");
      System.out.println("v. Изход");
      System.out.print("Изберете опция: ");
      String option = scanner.nextLine();

      if (option.equals("i")) {
        System.out.print("Въведете име с най-много 26 символа: ");
        String name = scanner.nextLine();
        System.out.print("Въведете телефонен номер: ");
        String number = scanner.nextLine();
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(number);

        try {
          validName(name);
          validTelephoneNumber(matcher);
        }
        catch (IllegalArgumentException iae) {
          System.out.println(iae.getMessage());
          continue;
        }
        boolean numberExist = false;

        try {

          if (phoneBook.getData().size() == 100) {
            throw new PhoneBookFullException("Контактите не могат да бъдат повече от 100");
          }

        }
        catch (PhoneBookFullException pfe) {
          System.out.println(pfe.getMessage());
          continue;
        }

        if (phoneBook.getData().size() != 0) {
          for (int i = 0; i < phoneBook.getData().size(); i++) {
            if (phoneBook.getData().get(i).contains(number)) {
              numberExist = true;
              break;
            }
          }
        }
        if (numberExist) {
          System.out.print("Запис за този номер съществува вече." +
              " Изберете 1, за да бъде презаписан или 0, за да се върнете към началното меню: ");
          int num = Integer.parseInt(scanner.nextLine());

          if (num == 1) {
            phoneBook.insert(name, number, true);
          }
          else {
            if (num != 0) {
              System.out.println("Невалидна опция. Ще бъдете върнат до главното меню");
            }
          }
        }
        else {
          phoneBook.insert(name, number, false);
        }

      }
      else if (option.equals("ii")) {
        System.out.print("Въведете телефонния номер за който искате да бъде изтрит записът: ");
        String number = scanner.nextLine();

        try {
          if (!phoneBook.deleteByPhoneNumber(number)) {
            throw new EntryNotFoundException("Този запис не съществува!");
          }
        }
        catch (EntryNotFoundException e) {
          System.out.println(e.getMessage());
        }
      }
      else if (option.equals("iii")) {
        System.out.print("Въведете низ: ");
        String str = scanner.nextLine();
        List<String> data = phoneBook.getAllNamesStartingWith(str);
        try {
          if (data.size() <= 0){
            throw new EntryNotFoundException("Не съществуват записи започващи с дадения низ.");
          }
        }
        catch (EntryNotFoundException e) {
          System.out.println(e.getMessage());
        }
        System.out.printf("Всички записи, които започват с %s са следните: %d%n", str, data.size());
        System.out.println("Изберете следваща опция");
      }
      else if (option.equals("iv")) {
        System.out.println(phoneBook.toString());
        System.out.println("Изберете следваща опция");
      }
      else if (option.equals("v")) {
        break;
      }
    }
  }

  private static void validName(String name)
  {

    if (name.length() > 26) {
      throw new IllegalArgumentException("This name is too long!");
    }
  }

  private static void validTelephoneNumber(Matcher matcher)
  {
    if (!matcher.matches()) {
      throw new IllegalArgumentException("Invalid telephone number");
    }
  }
}

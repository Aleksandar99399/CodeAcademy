package toaccesscontroll.phonebook;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneBookTest
{
  public static void main(String[] args)
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
        System.out.print("Въведете име: ");
        String name = scanner.nextLine();
        System.out.print("Въведете телефонен номер: ");
        String number = scanner.nextLine();
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(number);
        if (!matcher.matches()) {
          System.out.println("Incorrect number. Try again!!!");
          number = scanner.nextLine();
        }
        boolean numberExist = false;

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

        if (phoneBook.deleteByPhoneNumber(number)) {
          System.out.println("Записът беше успешно изтрит.");
        }else {
          System.out.println("Запис за този номер не беше намерен.");
        }
        System.out.println("Изберете следваща опция.");
      }
      else if (option.equals("iii")) {
        System.out.print("Въведете низ: ");
        String str = scanner.nextLine();
        List<String> data = phoneBook.getAllNamesStartingWith(str);
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

}

package bank;

import java.util.Scanner;

import javax.security.auth.login.LoginException;

public class Menu {
  
  private Scanner scanner;

  public static void main(String[] args) {
    System.out.println("Welcome to Globe Bank International!");

    Menu menu = new Menu();
    menu.scanner = new Scanner(System.in);

    Customer customer = menu.authenticateUser();

    if (customer != null) {
      Account account = DataSource.getAccount(customer.getAccountId());

      menu.showMenu(customer, account);

    }


    menu.scanner.close();
  }

  private void showMenu(Customer customer, Account account) {
    int selection = 0;
    double amount = 0;

    while(selection != 4 && customer.isAuthenticated()) {
      System.out.println("===================================================");
      System.out.println("Please selection one fo the following options: ");
      System.out.println("1: Deposit");
      System.out.println("2: Withdraw");
      System.out.println("3: Check Balance");
      System.out.println("4: Exit");
      System.out.println("===================================================");

      selection = scanner.nextInt();

      switch(selection) {
        case 1:
          System.out.println("How much would you like to deposit?");
          amount = scanner.nextDouble();
          account.deposit(amount);
          break;
        case 2:
          System.out.println("How much would you like to withdraw?");
          amount = scanner.nextDouble();
          account.withdraw(amount);
          break;
        case 3: 
          System.out.println("Current balance: " + account.getBalance());
          break;
        case 4:
          Authenticator.logout(customer);
          System.out.println("Thanks for banking at Globe Bank International!");
          break; 
        default:
          System.out.println("Invalid option. Please try again");
          break;         
      }
    }
  }

  private Customer authenticateUser() {
    Customer customer = null;
    String username;
    String password;
    
    System.out.println("Please enter your username");
    username = scanner.next();
    System.out.println("Please enter your password");
    password = scanner.next();

    try{
      customer = Authenticator.login(username, password);
    } catch(LoginException e) {
      System.out.println(e.getMessage());
    }

    return customer;

  }
}

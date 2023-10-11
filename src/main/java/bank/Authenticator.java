package bank;

import javax.security.auth.login.LoginException;

public class Authenticator {

  public static Customer login(String username, String password) throws LoginException {
    Customer customer = DataSource.getCustomer(username);

    if (customer == null) {
      throw new LoginException("User not found");
    }

    if (!password.equals(customer.getPassword())) {
      throw new LoginException("User not found");
    }

    customer.setAuthenticated(true);

    return customer;
  }

  public static void logout(Customer customer) {
    customer.setAuthenticated(false);
  }
}

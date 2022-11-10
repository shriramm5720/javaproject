package bank;

import java.util.ArrayList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class BankController {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		ArrayList<User> table = new ArrayList<>();// Storing user Object

		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateobj = new Date();
		try {
			

			System.out.println("\n******************************");
			System.out.println("WELCOME TO UNION BANK OF INDIA");
			while (true) {
				System.out.println("******************************");
				System.out.println("for new user >>Press 1-Open new Account");
				System.out.println("Existing User >>press 2- go to Login Page\n");
				System.out.println("************************************");

				int acc, c, found = 0, pin;
				double amt;

				User active = null;// store active user object
				c = sc.nextInt();

				switch (c) {
				case 1:
					System.out.println(">> SIGN UP  <<");
					System.out.println("Dont Use space in name");
					System.out.println("Enter the first name\n");
					String new_name_f = sc.next();
					System.out.println("Enter the last name\n");
					String new_name_l = sc.next();

					int random_ac = (int) (Math.random() * (99999 - 1000 - 1) * 1000);
					int random_pin = (int) (Math.random() * (99 - 10 - 1) * 10);

					table.add(new User(random_ac, (new_name_f + " " + new_name_l), random_pin, 0));

					System.out.println("\n New Account Create Succussfully");
					System.out.println("\n New Account No:- " + random_ac);
					System.out.println("\n new pin :" + random_pin);
					System.out.println("\n Login to access to give new account  and You can change pin after login");
					break;

				case 2:
					System.out.println(">> Login <<");
					System.out.println("Enter the Account Nunmber");
					acc = sc.nextInt();
					// Iterator through the databse to find the user
					Iterator<User> it = table.iterator();
					while (it.hasNext()) {
						User ur = (User) it.next();
						if (ur.getAccount_id() == acc) {
							found = 1;

							System.out.println("Enter pin");
							pin = sc.nextInt();

							if (ur.getPin() == pin) {

								active = ur;
								System.out.println("\n <Successfully logged in at >" + df.format(dateobj));
								System.out.println("\n **Welcome\t" + active.getName() + "**");
								found = 2;
								break;
							}

						}
					}
					if (found == 0) {
						System.out.println("\n !! Invalid Account Number");
						System.out.println("Please try again later !!!");
						System.out.println("\n session Ended  at" + df.format(dateobj));
						System.exit(0);
					}

					if (found == 1) {
						System.out.println("\n !! Invalid pin");
						System.out.println("Please try again later !!!");
						System.out.println("\n session Ended  at" + df.format(dateobj));
						System.exit(0);
					}
					while (true) {
						System.out.println("\n_________________");
						System.out.println("SELECT OPTION ");
						System.out.println("1-Update Pin");
						System.out.println("2-Balance Enquiry");
						System.out.println("3-Withdral Money");
						System.out.println("4-Deposite Money");
						System.out.println("5-Logout");

						System.out.println("\n________________");
						int ch = sc.nextInt();

						switch (ch) {

						case 1:
							System.out.println("Enter the Old Pin");
							int op = sc.nextInt();

							if (op == active.getPin()) {

								System.out.println("Enter the New Pin");
								active.setPin(sc.nextInt());
								System.out.println(">>>>Pin Updated Successfully<<<");
							} else {
								System.out.println("\n    !! INVALID PIN !");
							}
							break;

						case 2:
							System.out.println("AVAILABLE BALANCE = RS." + active.getBalance());
							break;

						case 3:
							System.out.print("Enter the Amount to be Withdraw : Rs");
							amt = sc.nextDouble();
							if (amt > active.getBalance()) {
								System.out.println("  !! INSUFFICIENT BALANCE");

							} else {
								active.setBalance(active.getBalance() - amt);
								System.out.println(">> Whithdral Successful");
								System.out.println("AVAILABLE BALANCE :RS." + active.getBalance());

							}
							break;

						case 4:
							System.out.println("\n Enter the amount to be Deposited :Rs.");
							amt = sc.nextDouble();
							if (amt > 1000000) {
								System.out.println("Your Amount Exceeds Limit");
								System.out.println("Maximum amount deposted :Rs.100000");
							} else {
								active.setBalance(active.getBalance() + amt);
								System.out.println(">> DEPOSITE Successful");
								System.out.println("AVAILABLE BALANCE :RS." + active.getBalance());

							}
							break;

						case 5:
							System.out.println("\n Thanks for using UBI ,visit again");
							System.out.println("\nsession ended" + df.format(dateobj) + ">>>\n");
							System.exit(0);

						default:
							System.out.println("Wrong Choice !! \n Press Between 1 to 5");

						}// end of inner switch block
					} // end of inner while loop

				default:
					System.out.println("Wrong Choice !! \\n Press Between 1 to 2");
				}// end of outer switch
			} // end of outer while loop

		} // end try block
		catch (Exception e) {
			System.out.println("!! WORNING \n please use correct input format");
			System.out.println("!! SESSION ENDED "+df.format(dateobj)+">>\n");

		}//end of catch block

	}

}

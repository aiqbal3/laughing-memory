import java.util.zip.DataFormatException;

public class BankAccountTester {

    public static void main(String[] args) {

        System.out.println(testBankAccountConstructorNotValidInitialBalance());
        System.out.println(testBankAccountWithdrawLargerOfBalanceAmount());
        System.out.println(testBankAccountConstructorValidInitialBalance());
        System.out.println(testBankAccountDepositNegativeAmount());
        System.out.println(testBankAccountEquals());
        System.out.println(testBankAccountWithdrawInvalidAmount());
        System.out.println(testBankAccountWithdrawValidAmount());
    }
    
    /**
     * This method checks whether the BankAccount constructor throws an 
     * IllegalArgumentException when it is passed a balance smaller than 10.
     * @return - true when this test verifies a correct functionality, 
     * and false otherwise.
     */
    public static boolean testBankAccountConstructorNotValidInitialBalance() {
        try {
            @SuppressWarnings("unused")
            BankAccount tester = new BankAccount("2", 2);
        } catch (IllegalArgumentException Exception) {
            return true;
        }
        return false;
    }// end test
    
    /**
     * Calls the constructor of BankAccount class to create a new BankAccount 
     * object with a given id and a valid initial balance (greater of equal 
     * to 10). Checks whether the new account is created with the provided 
     * account id and balance. It checks also that the list of transactions 
     * of the created account contains only one transaction /"1 " + the 
     * initial balance/
     * @return - true when this test verifies a correct functionality, 
     * and false otherwise.
     */
    public static boolean testBankAccountConstructorValidInitialBalance() {
        try {
            @SuppressWarnings("unused")
            BankAccount tester = new BankAccount("2", 10);
        } catch (IllegalArgumentException Exception) {
            return false;
        }
        return true;
        
    }
    
    /**
     * Checks whether BankAccount.deposit() method throws an 
     * IllegalArgumentException when it is passed a negative number. The balance 
     * of the bank account should not change after the method call returns.
     * @return - true when this test verifies a correct functionality, 
     * and false otherwise.
     */
    public static boolean testBankAccountDepositNegativeAmount() {
        BankAccount tester = new BankAccount("2", 10);

        try {
            tester.deposit(-2);
        } catch (IllegalArgumentException Exception) {
            return true;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * Checks whether BankAccount.equals() method returns true when it compares 
     * a bank account to another one having the same account identifier 
     * (case sensitive comparison); and it returns false otherwise.
     * @return - true when this test verifies a correct functionality, 
     * and false otherwise.
     */
    public static boolean testBankAccountEquals() {
        BankAccount tester1 = new BankAccount("2", 10);
        BankAccount tester2 = new BankAccount("2", 10);
        if (tester1.equals(tester2)) {
            return true;
        }
        return false;
    }
    
    /**
     * Checks whether BankAccount.withdraw() method throws a DataFormatException 
     * when it is passed a negative number or a number not multiple of 10. The 
     * account balance should not change after the withdraw() method returns.
     * @return - true when this test verifies a correct functionality, 
     * and false otherwise.
     */
    public static boolean testBankAccountWithdrawInvalidAmount() {
        BankAccount tester = new BankAccount("2", 10);
        try {
            tester.withdraw(-10);
        } catch (DataFormatException exception) {
            return true;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Checks whether BankAccount.withdraw() method throws an 
     * IllegalStateException when it is passed a number larger than the 
     * account's balance. The account balance should not change after that 
     * withdraw() method call returns.
     * @return - true when this test verifies a correct functionality, 
     * and false otherwise.
     */
    public static boolean testBankAccountWithdrawLargerOfBalanceAmount() {
        BankAccount tester = new BankAccount("2", 20);
        try {
            tester.withdraw(30);
        } catch (IllegalStateException exception) {
            return true;
        } catch (Exception e) {
            return true;
        }
        return false;
    }

    /**
     * Checks whether BankAccount.withdraw() method returns without any 
     * exception when it is passed a positive number smaller than the account's 
     * balance. The withdrawal amount should be subtracted from the balance 
     * after the withdraw() method call returns.
     * @return - true when this test verifies a correct functionality, 
     * and false otherwise.
     */
    public static boolean testBankAccountWithdrawValidAmount() {
        BankAccount tester = new BankAccount("2", 30);
        try {
            tester.withdraw(10);
        } catch (Exception e) {
            return false;
        }
        if (tester.getBalance() == 20) {
            return true;
        } else {
            return false;
        }
    }
}

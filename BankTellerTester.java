import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BankTellerTester {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println(testBankTellerConstructor());
        System.out.println(testBankTellerAddBankAccountUsedIdentifier());
        System.out.println(testBankTellerLoadTransactionsFileNotFound());
    }

    /**
     * Checks whether the constructor of BankTeller class creates a new 
     * BankTeller object with an empty ArrayList of bank accounts.
     * @return - true when this test verifies a correct functionality, and 
     * false otherwise.
     */
    public static boolean testBankTellerConstructor() {
        try {
            @SuppressWarnings("unused")
            BankTeller tester = new BankTeller();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Checks whether the BankTeller.addBankAccount() method throws an 
     * IllegalStateException when it is passed a bank account with an identifier 
     * already used.
     * @return - true when this test verifies a correct functionality, and 
     * false otherwise.
     */
    public static boolean testBankTellerAddBankAccountUsedIdentifier() {
        BankAccount tester1 = new BankAccount("2", 15);
        BankAccount tester2 = new BankAccount("2", 14);
        BankTeller bank = new BankTeller();
        try {
            bank.addBankAccount(tester1);
            bank.addBankAccount(tester2);

        } catch (IllegalStateException exception) {
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * This method checks whether the BankTeller.loadTransactions() method that 
     * takes a File parameter throws a FileNotFoundException, when it is passed 
     * a File object that does not correspond to an actual file within the file 
     * system, and a non null reference of type BankAccount.
     * @return - true when this test verifies a correct functionality, and 
     * false otherwise.
     */
    public static boolean testBankTellerLoadTransactionsFileNotFound() {
        BankAccount tester = new BankAccount("2", 15);
        BankTeller bank = new BankTeller();
        
        try {
            File file = new File("tester");
            bank.loadTransactions(file, tester);

        } catch (FileNotFoundException exception) {
            return true;
        } catch (IOException except) {
            except.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}

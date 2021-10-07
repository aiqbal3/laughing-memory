import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.zip.DataFormatException;
public class BankTeller {
    private ArrayList<BankAccount> accounts;
    /**
     * Constructor
     * Creates a new BankTeller object with an empty list of accounts
     */
    public BankTeller() {
        accounts = new ArrayList<BankAccount>();       
    }
    /**
     * Adds newAccount to the list of accounts of this BankTeller
     * @param newAccount - a new account to add
     */
    public void addBankAccount(BankAccount newAccount) throws Exception{
        
        if (newAccount == null) {
            throw new IllegalArgumentException("Enter a valid account.");
        }//iterates through accounts to check if account is valid to be added
        for (int i = 0; i < accounts.size(); i++) {
            if(accounts.get(i).getID().equals(newAccount.getID())) {
                throw new IllegalStateException("This account already exists.");
            }//end if 
        }//end for 
       
        accounts.add(newAccount);                
    }
    /**
     * Returns the bank account that has exactly the provided identifier. 
     * Case sensitive comparison must be considered.
     * @param id - a string that represents an identifier of a bank account
     * @return a reference to the bank account whose account identifier has 
     * an exact match with the provided string
     * @throws Exception - NoSuchElementException - with a descriptive error 
     * message if no account in this bankTeller's accounts list has the 
     * provided id
     */
    public BankAccount findAccount (String id) throws Exception {
        boolean accountMatches = false;
        BankAccount foundAccount = null;
        //iterates through accounts to look for matching ID
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getID().equals(id)) {
                foundAccount = accounts.get(i);
                accountMatches = true;
            }
        }
        if (!accountMatches) {
            throw new NoSuchElementException();
        }
        return foundAccount;
    }// end find account
    /**
     * Adds a new transaction to the account's list of transactions. 
     * When added, a withdrawal or deposit transaction should change the 
     * account's balance
     * @param transaction - to add
     * @param account - bank account
     * @throws Exception java.util.zip.DataFormatException - if the format of 
     * the transaction is not correct 
     * java.lang.NullPointerException - if account is null
     */
    public void addTransaction(String transaction, BankAccount account) 
        throws Exception{
        if (account == null) { //checks if account is null
            throw new NullPointerException("Please enter appropriate account");
        }
        //removes leading and trailing whitespace in transaction passed in
        transaction = transaction.trim();
        String [] transactionToAdd = transaction.split("//s+"); //adds to array
        if (transactionToAdd[0].equals("0")) {
            try { //tries to withdraw while checking exception
                account.withdraw(Integer.parseInt(transactionToAdd[1]));
            }
            catch (DataFormatException e) {
                System.out.print("You can't withdraw that amount. ");
            }           
        }
        else if(transactionToAdd[0].equals("1")) { //if it is a deposit trans
            account.deposit(Integer.parseInt(transactionToAdd[1]));
        }
        else {
            throw new DataFormatException("Format is not accepted.");
        }
    }//end addTransaction
    /**
     * Loads a set of transactions from a provided file object. Each transaction 
     * is in a separate line. Each transaction line should contain two items: 
     * the transaction code "0" or "1" followed by the transaction amount, 
     * separated by spaces. Extra spaces at the beginning and at the end of a 
     * transaction line should be ignored. Not correctly formatted lines must be 
     * skipped. Valid transactions must change the balance of the bank account. 
     * If java.util.Scanner object is used to read from the file object, make 
     * sure to close the scanner object whenever a FileNotFoundException 
     * was thrown or not.
     * @param file - a java.io.File object referring to a file that contains a 
     * set of transactions, each in one line
     * @param account - a reference to a BankAccount object
     * @throws - NullPointerException - if account is null
     * @throws - FileNotFoundException - if the file object does not correspond 
     * to an actual file within the file system.
     */
    public void loadTransactions(File file, BankAccount account) throws Exception{
        if (account == null) { //checks if account is null
            throw new NullPointerException("Enter valid account");
        }
        
        String lineToRead = "";
        Scanner scnr = new Scanner(file);
        
        try {
            //reads file
            while(scnr.hasNextLine()) {
                lineToRead = scnr.nextLine().trim(); //removes leading and 
                                                     //trailing whitespace  
                if (lineToRead.charAt(0) != '0' || lineToRead.charAt(0) != '1') {
                  //jumps back to the beginning of while loop  because it was 
                  //not a  valid transaction format
                    continue;
                }
                addTransaction(lineToRead, account);                
            }//end while            
        }
        catch(FileNotFoundException exception) {
            throw new FileNotFoundException("The file could not be found");
        }
        finally {
            scnr.close();
        }
    }//end loadTransactions
    
    /**
     * Returns the total number of accounts created so far 
     * (i.e., the size of the Arraylist of accounts
     * @return - the total number of accounts added to this BankTeller
     */
    public int getAccountsCount() {
        return accounts.size();
    }
}

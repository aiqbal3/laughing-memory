import java.util.ArrayList;
import java.util.zip.DataFormatException;

public class BankAccount {
    private String accountIdentifier;
    private int balance;
    private ArrayList<String>transactions;

    // constructor
    // Creates a new bank account with a given account identifier and an initial balance.
    public BankAccount(String accountID, int initialBalance) {

        if (initialBalance < 10) {
            throw new IllegalArgumentException("Initial balance cannot be less " 
        + "than 10.");
        }

        transactions = new ArrayList<String>();
        balance = initialBalance;
        accountIdentifier = accountID;
        transactions.add("1 " + initialBalance);

    }

    // Gets the unique identifier of this account
    public String getID() {
        return accountIdentifier;
    }
    
    /**Gets the current balance of this bank account
     * @return the unique identifier of this account
     */
    public int getBalance() {
        return balance;
    }// end getBalance
    
    /**Checks if an other bank account is equal to this one 
     * @param other - another BankAccount object
     * @return true if this bankAccount's identifier equals the other 
     * bankAccount's identifier. The comparison is case sensitive
     */
    public boolean equals(BankAccount other) {
        //getsID and sets it to string to be compared
        String otherBankAccountID = other.getID();
        if (accountIdentifier.equals(otherBankAccountID)) {
            return true;
        } else {
            return false;
        }
    }// end equals method

    /**
     * This method deposits an amount to this bank account. It also adds 
     * the transaction /"1 " + depositAmount/ to this account list of 
     * transactions and updates this bank account's balance.
     * @param depositAmount - the amount of money to deposit to this bank account
     * 
     */
    public void deposit(int depositAmount) throws Exception {
        // throws exception if deposit amount is negative
        if (depositAmount < 0) {
            throw new IllegalArgumentException("Deposit amount cannot be "
                + "negative.");
        }
        if (depositAmount > 0) {// as long as deposit amount is positive
            // updates balance while adding deposit amount
            balance += depositAmount;
            // adds transaction to this account, adding to array
            transactions.add("1 " + depositAmount);
        }

    }
    /**
     * This method withdraws a specific amount of money. It also adds the 
     * transaction /"0 " + withdrawalAmount/ to this accunt's list of 
     * transactions and updates this bank account's balance.
     * @param withdrawAmount - the amount of money to withdraw from this 
     * bank account
     * @throws Exception - DataFormatException - with a descriptive error 
     * message if withdrawalAmount is negative or is not a multiple of 10
     * @throws Exception - IllegalStateException - if the withdrawalAmount is
     * larger than this bank account's balance
     */
    public void withdraw(int withdrawAmount) throws Exception {

        if (withdrawAmount < 0 || (withdrawAmount % 10) != 0) {
            throw new DataFormatException(
                "You cannot withdraw a negative amount. " + 
            "Please withdraw a multiple of 10.");
        }
        if (withdrawAmount > balance) {
            throw new IllegalStateException(
                "Sorry. The account does not have enough money to "
                + "withdraw that amount.");
        }
        // makes sure withdraw amount is not negative that it is a multiple of 10
        if (withdrawAmount > 0 && (withdrawAmount % 10 == 0)) {
            // makes sure withdraw amount is less than account balance
            if (withdrawAmount < balance) {
                // updates accountBalance, subtracts withdrawAmount
                balance -= withdrawAmount;
                // adds transaction to this account
                transactions.add("0 " + withdrawAmount);
            } // end if
        } // end if
    }// end withdraw

    /**
     * Gets the most recent FIVE transactions in an array of length 5. 
     * This array may contain null references if the total number of 
     * transactions is less than 5. If getTransactionsCount() is less than 5, 
     * these transactions should be stored at the range of indices 
     * 0 .. getTransactionsCount()-1. For instance, if the total number of 
     * transactions is 0, this array will contain five null references. 
     * If the total number of transactions is 1, it will contain the transaction 
     * at index 0, followed by 4 null references, and so on.
     * @return the most recent transactions in an array that may contain up 
     * to 5 string references
     */
    public String[] getMostRecentTransactions() {
        String[] mostRecentTransactions = new String[5];
        int transSize = transactions.size();
        try {  //iterates through array and adds most recent transactions to it
            for (int i = 0; i < mostRecentTransactions.length; i++) {
                mostRecentTransactions[i] = transactions.get(transSize - 1 - i);
            } //catches indexoutofbounds exception if thrown
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("IndexOutOfBounds exception");
        }
        return mostRecentTransactions;
    }

    /**
     * Gets the total number of transactions performed on this bank account, 
     * meaning the size of the ArrayList of transactions of this bank account
     * 
     * @return - the total number of transactions performed on this account
     */
    public int getTransactionsCount() {
        int transactionCount = -1;
        //iterates through transactions and counts it if it is not null
        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i) != null) {
                transactionCount++;
            } // end if
        } // end for
        return transactionCount;
    }
}

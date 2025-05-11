package ua.pro.baynova.JUnitExamle;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {
    private BankAccount account;

    @Nested
    class DepositTests{

        @Test
        void testDepositPositiveAmount(){
            account = new BankAccount(100);
            account.deposit(50);
            assertEquals(150, account.getBalance());
        }

        @Test
        void testDepositNegativeAmount(){
            account = new BankAccount(100);
            account.deposit(-50);
            assertEquals(100, account.getBalance());
        }

        @Test
        void testDepositZeroAmount(){
            account = new BankAccount(100);
            account.deposit(0);
            assertEquals(100, account.getBalance());
        }
    }

    @Nested
    class WithdrawTests{

        @Test
        void testWithdrawValidAmount(){
            account = new BankAccount(200);
            assertTrue(account.withdraw(100));
            assertEquals(100, account.getBalance());
        }

        @Test
        void testWithdrawMoreThanBalance(){
            account = new BankAccount(100);
            assertFalse(account.withdraw(150));
            assertEquals(100, account.getBalance());
        }

        @Test
        void testWithdrawZeroAmount(){
            account = new BankAccount(100);
            assertTrue(account.withdraw(0));
            assertEquals(100, account.getBalance());
        }
    }

    @Nested
    class BalanceTests{

        @Test
        void testGetBalance(){
            account = new BankAccount(100);
            assertEquals(100, account.getBalance());
        }

        @Test
        void testBalanceAfterDeposit(){
            account = new BankAccount(100);
            account.deposit(50);
            assertEquals(150, account.getBalance());
        }

        @Test
        void testBalanceAfterWithdrawal(){
            account = new BankAccount(100);
            account.withdraw(50);
            assertEquals(50, account.getBalance());
        }
    }
}
package scratch;

import static org.junit.Assert.assertTrue;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.*;
import java.util.*;
import org.junit.*;
import org.junit.rules.*;

public class AssertTest {

  class InsufficientFundsException extends RuntimeException {

    public InsufficientFundsException(String message) {
      super(message);
    }

    private static final long serialVersionUID = 1L;
  }

  class Account {

    int balance;
    String name;

    Account(String name) {
      this.name = name;
    }

    void deposit(int dollars) {
      balance += dollars;
    }

    void withdraw(int dollars) {
      if (balance < dollars) {
        throw new InsufficientFundsException("balance only " + balance);
      }
      balance -= dollars;
    }

    public String getName() {
      return name;
    }

    public int getBalance() {
      return balance;
    }

    public boolean hasPositiveBalance() {
      return balance > 0;
    }
  }

  class Customer {

    List<Account> accounts = new ArrayList<>();

    void add(Account account) {
      accounts.add(account);
    }

    Iterator<Account> getAccounts() {
      return accounts.iterator();
    }
  }

  private Account account;

  @Before
  public void createAccount() {
    account = new Account("an account name");
  }

  @Test
  public void hasPositiveBalance() {
    account.deposit(50);
    assertTrue(account.hasPositiveBalance());
  }

  @Test
  public void depositIncreasesBalance() {
    int initialBalance = account.getBalance();
    account.deposit(100);
    assertTrue(account.getBalance() > initialBalance);
    assertThat(account.getBalance(), equalTo(100));
  }

  public void depositIncreasesBalance_hamcrestAssertTrue() {
    account.deposit(50);
    assertThat(account.getBalance() > 0, is(true));
  }

  @Ignore
  //@ExpectToFail
  @Test
  public void comparesArraysFailing() {
    assertThat(new String[]{"a", "b", "c"}, equalTo(new String[]{"a", "b"}));
  }

  @Test
  //@ExpectToFail
  @Ignore
  public void matchesFailure() {
    assertThat(account.getName(), startsWith("xyz"));
  }

  @Test
  public void comparesArraysPassing() {
    assertThat(new String[]{"a", "b"}, equalTo(new String[]{"a", "b"}));
  }

  @Test
  public void comparesCollectionsPassing() {
    assertThat(Arrays.asList(new String[]{"a"}),
        equalTo(Arrays.asList(new String[]{"a"})));
  }

  @Test
  public void variousMatcherTests() {
    Account account = new Account("my big fat acct");
    assertThat(account.getName(), is(equalTo("my big fat acct")));

    assertThat(account.getName(), not(equalTo("plunderings")));

    assertThat(account.getName(), is(notNullValue())); // not helpful
    assertThat(account.getName(), equalTo("my big fat acct"));
  }

  @Test
  @Ignore
  public void testWithWorthlessAssertionComment() {
    account.deposit(50);
    assertThat("account balance is 100", account.getBalance(), equalTo(50));
    // 주석문 보다는 테스트 메소드 이름을 이해할 수 있게 작성해야함.
  }

  @Test(expected = InsufficientFundsException.class)
  public void throwsWhenWithdrawingTooMuch() {
    account.withdraw(100);
    // 예외가 발생하면 테스트 통과, 그렇지 않으면 실패.
  }

  @Test
  public void throwsWhenWithdrawingTooMuchTry() {
    try {
      account.withdraw(100);
      fail();
    }
    catch (InsufficientFundsException expected) {
      assertThat(expected.getMessage(), equalTo("balance only 0"));
    }
  }

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void exceptionRule() {
    thrown.expect(InsufficientFundsException.class);
    thrown.expectMessage("balance only 0");

    account.withdraw(100);
  }
}

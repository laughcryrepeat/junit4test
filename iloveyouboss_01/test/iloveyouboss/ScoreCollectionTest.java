package iloveyouboss;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class ScoreCollectionTest {

  @Test
  public void add() {
  }

  @Test
  public void answerArithmeticMeanOfTwoNumbers() {
    // 준비
    ScoreCollection collection = new ScoreCollection();
    collection.add(() -> 5);
    collection.add(() -> 7);
    // 실행
    int actualResult = collection.arithmeticMean();

    // 단언
    assertEquals(actualResult, 6);
  }
}
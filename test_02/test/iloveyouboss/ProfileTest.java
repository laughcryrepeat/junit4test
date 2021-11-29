package iloveyouboss;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * ProfileTest 에 속한 각각의 테스트는 새로운 ProfileTest 인스턴스에서 @Before 메서드 호출 후 실행됨.
 */
public class ProfileTest {

  private Profile profile;
  private BooleanQuestion question;
  private Criteria criteria;

  @Before
  public void create() {
    profile = new Profile("Bull Hockey, Inc.");
    question = new BooleanQuestion(1, "Got bonuses?");
    criteria = new Criteria();
  }

  @Test
  public void matchAnswersFalseWhenMustMatchCriteriaNotMet() {
    profile.add(new Answer(question, Bool.FALSE));
    Criteria criteria = new Criteria();
    criteria.add(new Criterion(new Answer(question, Bool.TRUE), Weight.MustMatch));

    boolean matches = profile.matches(criteria);

    assertFalse(matches);
  }

  @Test
  public void matchAnswersTrueForAnyDontCareCriteria() {
    profile.add(new Answer(question, Bool.FALSE));
    Criteria criteria = new Criteria();
    criteria.add(new Criterion(new Answer(question, Bool.TRUE), Weight.DontCare));

    boolean matches = profile.matches(criteria);

    assertTrue(matches);
  }
}
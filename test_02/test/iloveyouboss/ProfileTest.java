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
//    Profile profile = new Profile("Bull Hockey, Inc.");
//    Question question = new BooleanQuestion(1, "Got bonuses?");
    Answer profileAnswer = new Answer(question, Bool.FALSE);
    profile.add(profileAnswer);
    Criteria criteria = new Criteria();
    Answer criteriaAnswer = new Answer(question, Bool.TRUE);
    Criterion criterion = new Criterion(criteriaAnswer, Weight.MustMatch);
    criteria.add(criterion);

    boolean matches = profile.matches(criteria);

    assertFalse(matches);
  }

  @Test
  public void matchAnswersTrueForAnyDontCareCriteria() {
//    Profile profile = new Profile("Bull Hockey, Inc.");
//    Question question = new BooleanQuestion(1, "Got milk?");
    Answer profileAnswer = new Answer(question, Bool.FALSE);
    profile.add(profileAnswer);
    Criteria criteria = new Criteria();
    Answer criteriaAnswer = new Answer(question, Bool.TRUE);
    Criterion criterion = new Criterion(criteriaAnswer, Weight.DontCare);

    criteria.add(criterion);

    boolean matches = profile.matches(criteria);

    assertTrue(matches);
  }
}
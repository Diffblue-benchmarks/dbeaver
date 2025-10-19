package org.jkiss.dbeaver.ext.mysql.model.session;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.ext.mysql.model.session.MySQLSession.PerformanceReadingValueValidator;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class MySQLSessionDiffblueTest {
  /**
   * Test PerformanceReadingValueValidator {@link
   * PerformanceReadingValueValidator#isValidValue(MySQLSession, Object)} with {@code MySQLSession},
   * {@code Object}.
   *
   * <p>Method under test: {@link PerformanceReadingValueValidator#isValidValue(MySQLSession,
   * Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PerformanceReadingValueValidator.isValidValue(MySQLSession, Object)"})
  public void testPerformanceReadingValueValidatorIsValidValueWithMySQLSessionObject()
      throws IllegalArgumentException {
    // Arrange, Act and Assert
    assertFalse(
        new PerformanceReadingValueValidator().isValidValue(mock(MySQLSession.class), "Value"));
  }
}

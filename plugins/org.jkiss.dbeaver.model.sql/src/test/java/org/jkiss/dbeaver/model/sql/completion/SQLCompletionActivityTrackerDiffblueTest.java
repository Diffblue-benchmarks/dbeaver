package org.jkiss.dbeaver.model.sql.completion;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLCompletionActivityTrackerDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SQLCompletionActivityTracker#SQLCompletionActivityTracker(boolean)}
   *   <li>{@link SQLCompletionActivityTracker#implicitlyTriggered()}
   *   <li>{@link SQLCompletionActivityTracker#isAdditionalInfoExpected()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLCompletionActivityTracker.<init>(boolean)",
    "void SQLCompletionActivityTracker.implicitlyTriggered()",
    "boolean SQLCompletionActivityTracker.isAdditionalInfoExpected()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    SQLCompletionActivityTracker actualSqlCompletionActivityTracker =
        new SQLCompletionActivityTracker(true);
    actualSqlCompletionActivityTracker.implicitlyTriggered();

    // Assert
    assertTrue(actualSqlCompletionActivityTracker.isAdditionalInfoExpected());
  }

  /**
   * Test {@link SQLCompletionActivityTracker#selectionChanged()}.
   *
   * <p>Method under test: {@link SQLCompletionActivityTracker#selectionChanged()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLCompletionActivityTracker.selectionChanged()"})
  public void testSelectionChanged() {
    // Arrange
    SQLCompletionActivityTracker sqlCompletionActivityTracker =
        new SQLCompletionActivityTracker(true);

    // Act
    sqlCompletionActivityTracker.selectionChanged();

    // Assert
    assertFalse(sqlCompletionActivityTracker.isAdditionalInfoExpected());
  }

  /**
   * Test {@link SQLCompletionActivityTracker#selectionChanged()}.
   *
   * <p>Method under test: {@link SQLCompletionActivityTracker#selectionChanged()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLCompletionActivityTracker.selectionChanged()"})
  public void testSelectionChanged2() {
    // Arrange
    SQLCompletionActivityTracker sqlCompletionActivityTracker =
        new SQLCompletionActivityTracker(false);

    // Act
    sqlCompletionActivityTracker.selectionChanged();

    // Assert that nothing has changed
    assertTrue(sqlCompletionActivityTracker.isAdditionalInfoExpected());
  }
}

package org.jkiss.dbeaver.model.ai.engine.copilot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CopilotCompletionEngineDiffblueTest {
  /**
   * Test {@link CopilotCompletionEngine#CopilotCompletionEngine(CopilotProperties)}.
   *
   * <p>Method under test: {@link
   * CopilotCompletionEngine#CopilotCompletionEngine(CopilotProperties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CopilotCompletionEngine.<init>(CopilotProperties)"})
  public void testNewCopilotCompletionEngine() throws DBException {
    // Arrange
    CopilotProperties properties = new CopilotProperties();

    // Act
    CopilotCompletionEngine actualCopilotCompletionEngine = new CopilotCompletionEngine(properties);

    // Assert
    assertEquals("gpt-4o", actualCopilotCompletionEngine.getModelName());
    assertSame(properties, actualCopilotCompletionEngine.getProperties());
  }

  /**
   * Test {@link CopilotCompletionEngine#getContextWindowSize(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then return three.
   * </ul>
   *
   * <p>Method under test: {@link CopilotCompletionEngine#getContextWindowSize(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int CopilotCompletionEngine.getContextWindowSize(DBRProgressMonitor)"})
  public void testGetContextWindowSize_thenReturnThree() throws DBException {
    // Arrange
    CopilotProperties properties = new CopilotProperties();
    properties.setContextWindowSize(3);
    CopilotCompletionEngine copilotCompletionEngine = new CopilotCompletionEngine(properties);

    // Act and Assert
    assertEquals(3, copilotCompletionEngine.getContextWindowSize(new LoggingProgressMonitor()));
  }

  /**
   * Test {@link CopilotCompletionEngine#getContextWindowSize(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link CopilotCompletionEngine#getContextWindowSize(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int CopilotCompletionEngine.getContextWindowSize(DBRProgressMonitor)"})
  public void testGetContextWindowSize_thenThrowDBException() throws DBException {
    // Arrange
    CopilotCompletionEngine copilotCompletionEngine =
        new CopilotCompletionEngine(new CopilotProperties());

    // Act and Assert
    assertThrows(
        DBException.class,
        () -> copilotCompletionEngine.getContextWindowSize(new LoggingProgressMonitor()));
  }

  /**
   * Test {@link CopilotCompletionEngine#getModelName()}.
   *
   * <p>Method under test: {@link CopilotCompletionEngine#getModelName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String CopilotCompletionEngine.getModelName()"})
  public void testGetModelName() throws DBException {
    // Arrange, Act and Assert
    assertEquals("gpt-4o", new CopilotCompletionEngine(new CopilotProperties()).getModelName());
  }

  /**
   * Test {@link CopilotCompletionEngine#getModelName()}.
   *
   * <ul>
   *   <li>Given {@link CopilotProperties} (default constructor) Model is {@code gpt-4o}.
   * </ul>
   *
   * <p>Method under test: {@link CopilotCompletionEngine#getModelName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String CopilotCompletionEngine.getModelName()"})
  public void testGetModelName_givenCopilotPropertiesModelIsGpt4o() throws DBException {
    // Arrange
    CopilotProperties properties = new CopilotProperties();
    properties.setModel("gpt-4o");

    // Act and Assert
    assertEquals("gpt-4o", new CopilotCompletionEngine(properties).getModelName());
  }
}

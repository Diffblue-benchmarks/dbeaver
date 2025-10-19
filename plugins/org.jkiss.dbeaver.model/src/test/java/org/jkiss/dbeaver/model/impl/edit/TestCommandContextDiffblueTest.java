package org.jkiss.dbeaver.model.impl.edit;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class TestCommandContextDiffblueTest {
  /**
   * Test {@link TestCommandContext#TestCommandContext(DBCExecutionContext, boolean)}.
   *
   * <p>Method under test: {@link TestCommandContext#TestCommandContext(DBCExecutionContext,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TestCommandContext.<init>(DBCExecutionContext, boolean)"})
  public void testNewTestCommandContext() {
    // Arrange
    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);

    // Act
    TestCommandContext actualTestCommandContext = new TestCommandContext(executionContext, true);

    // Assert
    assertTrue(actualTestCommandContext.getUserParams().isEmpty());
    assertSame(executionContext, actualTestCommandContext.getExecutionContext());
  }
}

package org.jkiss.dbeaver.model.ai.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SimpleSqlFormatterImplDiffblueTest {
  /**
   * Test {@link SimpleSqlFormatterImpl#formatGeneratedQuery(DBRProgressMonitor, DBPDataSource,
   * String)}.
   *
   * <p>Method under test: {@link SimpleSqlFormatterImpl#formatGeneratedQuery(DBRProgressMonitor,
   * DBPDataSource, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SimpleSqlFormatterImpl.formatGeneratedQuery(DBRProgressMonitor, DBPDataSource, String)"
  })
  public void testFormatGeneratedQuery() {
    // Arrange
    SimpleSqlFormatterImpl simpleSqlFormatterImpl = new SimpleSqlFormatterImpl();

    // Act and Assert
    assertEquals(
        "Completion Text",
        simpleSqlFormatterImpl.formatGeneratedQuery(
            new LoggingProgressMonitor(), mock(DBPDataSource.class), "Completion Text"));
  }
}

package org.jkiss.dbeaver.ext.cubrid.model.plan;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CubridStatementProxyDiffblueTest {
  /**
   * Test {@link CubridStatementProxy#getQueryplan(Statement, String)}.
   *
   * <p>Method under test: {@link CubridStatementProxy#getQueryplan(Statement, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String CubridStatementProxy.getQueryplan(Statement, String)"})
  public void testGetQueryplan() throws SQLException {
    // Arrange, Act and Assert
    assertEquals("", CubridStatementProxy.getQueryplan(mock(Statement.class), "Query"));
  }
}

package org.jkiss.dbeaver.ext.cubrid.model;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class QueryTransformerLimitCubridDiffblueTest {
  /**
   * Test {@link QueryTransformerLimitCubrid#isLimitApplicable(Statement)}.
   *
   * <ul>
   *   <li>When {@link PlainSelect#PlainSelect()}.
   * </ul>
   *
   * <p>Method under test: {@link QueryTransformerLimitCubrid#isLimitApplicable(Statement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean QueryTransformerLimitCubrid.isLimitApplicable(Statement)"})
  public void testIsLimitApplicable_whenPlainSelect() {
    // Arrange
    QueryTransformerLimitCubrid queryTransformerLimitCubrid = new QueryTransformerLimitCubrid();

    // Act and Assert
    assertTrue(queryTransformerLimitCubrid.isLimitApplicable(new PlainSelect()));
  }

  /**
   * Test {@link QueryTransformerLimitCubrid#isLimitApplicable(Statement)}.
   *
   * <ul>
   *   <li>When {@link Statement}.
   * </ul>
   *
   * <p>Method under test: {@link QueryTransformerLimitCubrid#isLimitApplicable(Statement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean QueryTransformerLimitCubrid.isLimitApplicable(Statement)"})
  public void testIsLimitApplicable_whenStatement() {
    // Arrange, Act and Assert
    assertTrue(new QueryTransformerLimitCubrid().isLimitApplicable(mock(Statement.class)));
  }
}

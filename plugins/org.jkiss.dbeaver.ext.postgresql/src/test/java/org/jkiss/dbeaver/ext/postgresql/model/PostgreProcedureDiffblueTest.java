package org.jkiss.dbeaver.ext.postgresql.model;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.ext.postgresql.model.PostgreProcedure.ProcedureVolatile;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PostgreProcedureDiffblueTest {
  /**
   * Test ProcedureVolatile {@link ProcedureVolatile#getCreateClause()}.
   *
   * <p>Method under test: {@link ProcedureVolatile#getCreateClause()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String ProcedureVolatile.getCreateClause()"})
  public void testProcedureVolatileGetCreateClause() {
    // Arrange, Act and Assert
    assertEquals("IMMUTABLE", ProcedureVolatile.valueOf("i").getCreateClause());
  }
}

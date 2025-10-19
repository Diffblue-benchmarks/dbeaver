package org.jkiss.dbeaver.model.struct.rdb;

import static org.junit.Assert.assertFalse;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBSProcedureTypeDiffblueTest {
  /**
   * Test {@link DBSProcedureType#hasReturnValue()}.
   *
   * <p>Method under test: {@link DBSProcedureType#hasReturnValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSProcedureType.hasReturnValue()"})
  public void testHasReturnValue() {
    // Arrange, Act and Assert
    assertFalse(DBSProcedureType.valueOf("UNKNOWN").hasReturnValue());
  }
}

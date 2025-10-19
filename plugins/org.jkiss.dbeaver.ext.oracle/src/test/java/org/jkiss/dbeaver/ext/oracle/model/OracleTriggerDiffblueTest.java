package org.jkiss.dbeaver.ext.oracle.model;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.ext.oracle.model.OracleTrigger.ActionType;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class OracleTriggerDiffblueTest {
  /**
   * Test ActionType {@link ActionType#getName()}.
   *
   * <p>Method under test: {@link ActionType#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String ActionType.getName()"})
  public void testActionTypeGetName() {
    // Arrange, Act and Assert
    assertEquals("PL/SQL", ActionType.valueOf("PLSQL").getName());
  }
}

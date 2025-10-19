package org.jkiss.dbeaver.ext.db2.model.plan;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DB2PlanOperatorTypeDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DB2PlanOperatorType#getName()}
   *   <li>{@link DB2PlanOperatorType#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DB2PlanOperatorType.getName()",
    "String DB2PlanOperatorType.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    DB2PlanOperatorType valueOfResult = DB2PlanOperatorType.valueOf("DELETE");

    // Act
    String actualName = valueOfResult.getName();

    // Assert
    assertEquals("Delete", actualName);
    assertEquals("Delete", valueOfResult.toString());
  }
}

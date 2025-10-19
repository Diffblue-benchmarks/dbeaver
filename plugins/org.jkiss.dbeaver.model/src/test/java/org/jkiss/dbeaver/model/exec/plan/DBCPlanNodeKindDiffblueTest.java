package org.jkiss.dbeaver.model.exec.plan;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.struct.DBSObjectType;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBCPlanNodeKindDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBCPlanNodeKind#getObjectType()}
   *   <li>{@link DBCPlanNodeKind#getTitle()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSObjectType DBCPlanNodeKind.getObjectType()",
    "java.lang.String DBCPlanNodeKind.getTitle()"
  })
  public void testGettersAndSetters() {
    // Arrange
    DBCPlanNodeKind valueOfResult = DBCPlanNodeKind.valueOf("DEFAULT");

    // Act
    DBSObjectType actualObjectType = valueOfResult.getObjectType();

    // Assert
    assertEquals("Node", valueOfResult.getTitle());
    assertNull(actualObjectType);
  }
}

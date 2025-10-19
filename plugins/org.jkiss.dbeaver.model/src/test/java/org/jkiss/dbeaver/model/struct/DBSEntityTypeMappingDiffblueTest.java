package org.jkiss.dbeaver.model.struct;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBSEntityTypeMappingDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBSEntityTypeMapping#DBSEntityTypeMapping(DBSEntityType, Class)}
   *   <li>{@link DBSEntityTypeMapping#getEntityClass()}
   *   <li>{@link DBSEntityTypeMapping#getEntityType()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBSEntityTypeMapping.<init>(DBSEntityType, Class)",
    "Class DBSEntityTypeMapping.getEntityClass()",
    "DBSEntityType DBSEntityTypeMapping.getEntityType()"
  })
  public void testGettersAndSetters() {
    // Arrange
    Class<DBSObject> entityClass = DBSObject.class;

    // Act
    DBSEntityTypeMapping actualDbsEntityTypeMapping =
        new DBSEntityTypeMapping(DBSEntityType.ASSOCIATION, entityClass);
    Class<? extends DBSObject> actualEntityClass = actualDbsEntityTypeMapping.getEntityClass();
    DBSEntityType actualEntityType = actualDbsEntityTypeMapping.getEntityType();

    // Assert
    Class<DBSObject> expectedEntityClass = DBSObject.class;
    assertEquals(expectedEntityClass, actualEntityClass);
    assertSame(DBSEntityType.ASSOCIATION, actualEntityType);
  }
}

package org.jkiss.dbeaver.model.struct;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBSEntityConstraintInfoDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBSEntityConstraintInfo#getImplClass()}
   *   <li>{@link DBSEntityConstraintInfo#getType()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Class DBSEntityConstraintInfo.getImplClass()",
    "DBSEntityConstraintType DBSEntityConstraintInfo.getType()"
  })
  public void testGettersAndSetters() {
    // Arrange
    Class<DBSEntityConstraint> implClass = DBSEntityConstraint.class;
    DBSEntityConstraintInfo ofResult =
        DBSEntityConstraintInfo.of(DBSEntityConstraintType.ASSOCIATION, implClass);

    // Act
    Class<? extends DBSEntityConstraint> actualImplClass = ofResult.getImplClass();
    DBSEntityConstraintType actualType = ofResult.getType();

    // Assert
    Class<DBSEntityConstraint> expectedImplClass = DBSEntityConstraint.class;
    assertEquals(expectedImplClass, actualImplClass);
    assertSame(DBSEntityConstraintType.ASSOCIATION, actualType);
  }

  /**
   * Test {@link DBSEntityConstraintInfo#of(DBSEntityConstraintType, Class)}.
   *
   * <p>Method under test: {@link DBSEntityConstraintInfo#of(DBSEntityConstraintType, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSEntityConstraintInfo DBSEntityConstraintInfo.of(DBSEntityConstraintType, Class)"
  })
  public void testOf() {
    // Arrange
    Class<DBSEntityConstraint> implClass = DBSEntityConstraint.class;

    // Act
    DBSEntityConstraintInfo actualOfResult =
        DBSEntityConstraintInfo.of(DBSEntityConstraintType.ASSOCIATION, implClass);

    // Assert
    Class<DBSEntityConstraint> expectedImplClass = DBSEntityConstraint.class;
    assertEquals(expectedImplClass, actualOfResult.getImplClass());
    assertSame(DBSEntityConstraintType.ASSOCIATION, actualOfResult.getType());
  }
}

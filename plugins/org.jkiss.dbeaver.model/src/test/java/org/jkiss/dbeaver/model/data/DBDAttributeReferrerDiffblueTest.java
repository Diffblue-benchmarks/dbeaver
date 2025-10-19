package org.jkiss.dbeaver.model.data;

import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.struct.DBSEntityAttribute;
import org.jkiss.dbeaver.model.virtual.DBVContainer;
import org.jkiss.dbeaver.model.virtual.DBVEntity;
import org.jkiss.dbeaver.model.virtual.DBVEntityAttribute;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBDAttributeReferrerDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBDAttributeReferrer#DBDAttributeReferrer(DBSEntityAttribute)}
   *   <li>{@link DBDAttributeReferrer#getAttribute()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBDAttributeReferrer.<init>(DBSEntityAttribute)",
    "DBSEntityAttribute DBDAttributeReferrer.getAttribute()"
  })
  public void testGettersAndSetters() {
    // Arrange
    DBVEntity entity =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    DBVEntityAttribute attribute = new DBVEntityAttribute(entity, null, "Name");

    // Act and Assert
    assertSame(attribute, new DBDAttributeReferrer(attribute).getAttribute());
  }
}

package org.jkiss.dbeaver.model.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBDNullDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBDNull#release()}
   *   <li>{@link DBDNull#toString()}
   *   <li>{@link DBDNull#getRawValue()}
   *   <li>{@link DBDNull#isModified()}
   *   <li>{@link DBDNull#isNull()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object DBDNull.getRawValue()",
    "boolean DBDNull.isModified()",
    "boolean DBDNull.isNull()",
    "void DBDNull.release()",
    "String DBDNull.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    DBDNull dbdNull = DBDNull.INSTANCE;

    // Act
    dbdNull.release();
    String actualToStringResult = dbdNull.toString();
    Object actualRawValue = dbdNull.getRawValue();
    boolean actualIsModifiedResult = dbdNull.isModified();

    // Assert
    assertEquals("NULL", actualToStringResult);
    assertNull(actualRawValue);
    assertFalse(actualIsModifiedResult);
    assertTrue(dbdNull.isNull());
  }
}

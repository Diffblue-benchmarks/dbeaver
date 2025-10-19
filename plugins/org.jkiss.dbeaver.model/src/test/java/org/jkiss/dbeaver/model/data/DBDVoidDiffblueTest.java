package org.jkiss.dbeaver.model.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBDVoidDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBDVoid#release()}
   *   <li>{@link DBDVoid#toString()}
   *   <li>{@link DBDVoid#getRawValue()}
   *   <li>{@link DBDVoid#isModified()}
   *   <li>{@link DBDVoid#isNull()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object DBDVoid.getRawValue()",
    "boolean DBDVoid.isModified()",
    "boolean DBDVoid.isNull()",
    "void DBDVoid.release()",
    "String DBDVoid.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    DBDVoid dbdVoid = DBDVoid.INSTANCE;

    // Act
    dbdVoid.release();
    String actualToStringResult = dbdVoid.toString();
    Object actualRawValue = dbdVoid.getRawValue();
    boolean actualIsModifiedResult = dbdVoid.isModified();

    // Assert
    assertEquals("", actualToStringResult);
    assertNull(actualRawValue);
    assertFalse(actualIsModifiedResult);
    assertFalse(dbdVoid.isNull());
  }
}

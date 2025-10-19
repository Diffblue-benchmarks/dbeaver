package org.jkiss.dbeaver.ext.oracle.data;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPEvent;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class OracleObjectValueDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link OracleObjectValue#OracleObjectValue(Object)}
   *   <li>{@link OracleObjectValue#release()}
   *   <li>{@link OracleObjectValue#getRawValue()}
   *   <li>{@link OracleObjectValue#getValue()}
   *   <li>{@link OracleObjectValue#isModified()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void OracleObjectValue.<init>(Object)",
    "Object OracleObjectValue.getRawValue()",
    "Object OracleObjectValue.getValue()",
    "boolean OracleObjectValue.isModified()",
    "void OracleObjectValue.release()"
  })
  public void testGettersAndSetters() {
    // Arrange
    Object object = DBPEvent.RENAME;

    // Act
    OracleObjectValue actualOracleObjectValue = new OracleObjectValue(object);
    actualOracleObjectValue.release();
    Object actualRawValue = actualOracleObjectValue.getRawValue();
    Object actualValue = actualOracleObjectValue.getValue();

    // Assert
    assertFalse(actualOracleObjectValue.isModified());
    assertSame(object, actualRawValue);
    assertSame(object, actualValue);
  }

  /**
   * Test {@link OracleObjectValue#isNull()}.
   *
   * <ul>
   *   <li>Given {@link OracleObjectValue#OracleObjectValue(Object)} with value is {@code null}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link OracleObjectValue#isNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OracleObjectValue.isNull()"})
  public void testIsNull_givenOracleObjectValueWithValueIsNull_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(new OracleObjectValue(null).isNull());
  }

  /**
   * Test {@link OracleObjectValue#isNull()}.
   *
   * <ul>
   *   <li>Given {@link OracleObjectValue#OracleObjectValue(Object)} with value is {@link
   *       DBPEvent#RENAME}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link OracleObjectValue#isNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OracleObjectValue.isNull()"})
  public void testIsNull_givenOracleObjectValueWithValueIsRename_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new OracleObjectValue(DBPEvent.RENAME).isNull());
  }
}

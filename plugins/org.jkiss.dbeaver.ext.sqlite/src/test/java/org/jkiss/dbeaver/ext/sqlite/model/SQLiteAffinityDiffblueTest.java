package org.jkiss.dbeaver.ext.sqlite.model;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPDataKind;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLiteAffinityDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SQLiteAffinity#getDataKind()}
   *   <li>{@link SQLiteAffinity#getPrecision()}
   *   <li>{@link SQLiteAffinity#getScale()}
   *   <li>{@link SQLiteAffinity#getValueType()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBPDataKind SQLiteAffinity.getDataKind()",
    "int SQLiteAffinity.getPrecision()",
    "int SQLiteAffinity.getScale()",
    "int SQLiteAffinity.getValueType()"
  })
  public void testGettersAndSetters() {
    // Arrange
    SQLiteAffinity valueOfResult = SQLiteAffinity.valueOf("INTEGER");

    // Act
    DBPDataKind actualDataKind = valueOfResult.getDataKind();
    int actualPrecision = valueOfResult.getPrecision();
    int actualScale = valueOfResult.getScale();

    // Assert
    assertEquals(-5, valueOfResult.getValueType());
    assertEquals(0, actualScale);
    assertEquals(19, actualPrecision);
    assertEquals(DBPDataKind.NUMERIC, actualDataKind);
  }
}

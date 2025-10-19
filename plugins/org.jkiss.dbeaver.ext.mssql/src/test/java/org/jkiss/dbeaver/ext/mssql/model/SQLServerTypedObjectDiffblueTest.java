package org.jkiss.dbeaver.ext.mssql.model;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPDataKind;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLServerTypedObjectDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SQLServerTypedObject#SQLServerTypedObject(String, int, DBPDataKind, int, int,
   *       int)}
   *   <li>{@link SQLServerTypedObject#getDataKind()}
   *   <li>{@link SQLServerTypedObject#getFullTypeName()}
   *   <li>{@link SQLServerTypedObject#getTypeID()}
   *   <li>{@link SQLServerTypedObject#getTypeModifiers()}
   *   <li>{@link SQLServerTypedObject#getTypeName()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLServerTypedObject.<init>(String, int, DBPDataKind, int, int, int)",
    "DBPDataKind SQLServerTypedObject.getDataKind()",
    "String SQLServerTypedObject.getFullTypeName()",
    "int SQLServerTypedObject.getTypeID()",
    "long SQLServerTypedObject.getTypeModifiers()",
    "String SQLServerTypedObject.getTypeName()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    SQLServerTypedObject actualSqlServerTypedObject =
        new SQLServerTypedObject("Type Name", 1, DBPDataKind.BOOLEAN, 1, 1, 3);
    DBPDataKind actualDataKind = actualSqlServerTypedObject.getDataKind();
    String actualFullTypeName = actualSqlServerTypedObject.getFullTypeName();
    int actualTypeID = actualSqlServerTypedObject.getTypeID();
    long actualTypeModifiers = actualSqlServerTypedObject.getTypeModifiers();

    // Assert
    assertEquals("Type Name", actualFullTypeName);
    assertEquals("Type Name", actualSqlServerTypedObject.getTypeName());
    assertEquals(0L, actualTypeModifiers);
    assertEquals(1, actualTypeID);
    assertEquals(DBPDataKind.BOOLEAN, actualDataKind);
  }

  /**
   * Test {@link SQLServerTypedObject#getScale()}.
   *
   * <p>Method under test: {@link SQLServerTypedObject#getScale()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.Integer SQLServerTypedObject.getScale()"})
  public void testGetScale() {
    // Arrange
    SQLServerTypedObject sqlServerTypedObject =
        new SQLServerTypedObject("Type Name", 1, DBPDataKind.BOOLEAN, 1, 1, 3);

    // Act and Assert
    assertEquals(1, sqlServerTypedObject.getScale().intValue());
  }

  /**
   * Test {@link SQLServerTypedObject#getPrecision()}.
   *
   * <p>Method under test: {@link SQLServerTypedObject#getPrecision()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.Integer SQLServerTypedObject.getPrecision()"})
  public void testGetPrecision() {
    // Arrange
    SQLServerTypedObject sqlServerTypedObject =
        new SQLServerTypedObject("Type Name", 1, DBPDataKind.BOOLEAN, 1, 1, 3);

    // Act and Assert
    assertEquals(1, sqlServerTypedObject.getPrecision().intValue());
  }

  /**
   * Test {@link SQLServerTypedObject#getMaxLength()}.
   *
   * <p>Method under test: {@link SQLServerTypedObject#getMaxLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long SQLServerTypedObject.getMaxLength()"})
  public void testGetMaxLength() {
    // Arrange
    SQLServerTypedObject sqlServerTypedObject =
        new SQLServerTypedObject("Type Name", 1, DBPDataKind.BOOLEAN, 1, 1, 3);

    // Act and Assert
    assertEquals(3L, sqlServerTypedObject.getMaxLength());
  }
}

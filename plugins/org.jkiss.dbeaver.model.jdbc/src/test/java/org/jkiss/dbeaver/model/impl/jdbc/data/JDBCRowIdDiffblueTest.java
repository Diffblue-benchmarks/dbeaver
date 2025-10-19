package org.jkiss.dbeaver.model.impl.jdbc.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.sql.RowId;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class JDBCRowIdDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link JDBCRowId#JDBCRowId(RowId)}
   *   <li>{@link JDBCRowId#release()}
   *   <li>{@link JDBCRowId#getRawValue()}
   *   <li>{@link JDBCRowId#getValue()}
   *   <li>{@link JDBCRowId#isModified()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCRowId.<init>(RowId)",
    "Object JDBCRowId.getRawValue()",
    "RowId JDBCRowId.getValue()",
    "boolean JDBCRowId.isModified()",
    "void JDBCRowId.release()"
  })
  public void testGettersAndSetters() throws DBCException {
    // Arrange and Act
    JDBCRowId actualJdbcRowId = new JDBCRowId(null);
    actualJdbcRowId.release();
    Object actualRawValue = actualJdbcRowId.getRawValue();
    RowId actualValue = actualJdbcRowId.getValue();

    // Assert
    assertNull(actualRawValue);
    assertNull(actualValue);
    assertFalse(actualJdbcRowId.isModified());
  }

  /**
   * Test {@link JDBCRowId#isNull()}.
   *
   * <p>Method under test: {@link JDBCRowId#isNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCRowId.isNull()"})
  public void testIsNull() {
    // Arrange, Act and Assert
    assertTrue(new JDBCRowId(null).isNull());
  }

  /**
   * Test {@link JDBCRowId#toString()}.
   *
   * <p>Method under test: {@link JDBCRowId#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JDBCRowId.toString()"})
  public void testToString() {
    // Arrange, Act and Assert
    assertEquals("null", new JDBCRowId(null).toString());
  }
}

package org.jkiss.dbeaver.model.sql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPEvent;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBQuotaExceptionDiffblueTest {
  /**
   * Test {@link DBQuotaException#DBQuotaException(String, String, Object, Object)}.
   *
   * <p>Method under test: {@link DBQuotaException#DBQuotaException(String, String, Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBQuotaException.<init>(String, String, Object, Object)"})
  public void testNewDBQuotaException() {
    // Arrange
    Object object = DBPEvent.RENAME;

    // Act
    DBQuotaException actualDbQuotaException =
        new DBQuotaException("An error occurred", "42", DBPEvent.RENAME, object);

    // Assert
    assertEquals("42", actualDbQuotaException.getQuotaId());
    assertNull(actualDbQuotaException.getCause());
    assertNull(actualDbQuotaException.getDataSource());
    assertNull(actualDbQuotaException.getExecutionContext());
    assertEquals(0, actualDbQuotaException.getSuppressed().length);
    assertTrue(actualDbQuotaException.hasMessage());
    assertSame(object, actualDbQuotaException.getExceededValue());
    assertSame(object, actualDbQuotaException.getQuotaValue());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBQuotaException#getExceededValue()}
   *   <li>{@link DBQuotaException#getQuotaId()}
   *   <li>{@link DBQuotaException#getQuotaValue()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object DBQuotaException.getExceededValue()",
    "String DBQuotaException.getQuotaId()",
    "Object DBQuotaException.getQuotaValue()"
  })
  public void testGettersAndSetters() {
    // Arrange
    DBQuotaException dbQuotaException =
        new DBQuotaException("An error occurred", "42", DBPEvent.RENAME, DBPEvent.RENAME);

    // Act
    Object actualExceededValue = dbQuotaException.getExceededValue();
    String actualQuotaId = dbQuotaException.getQuotaId();

    // Assert
    assertEquals("42", actualQuotaId);
    assertSame(actualExceededValue, dbQuotaException.getQuotaValue());
  }
}

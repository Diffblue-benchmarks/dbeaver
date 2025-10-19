package org.jkiss.dbeaver.model.exec;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBCMessageExceptionDiffblueTest {
  /**
   * Test {@link DBCMessageException#DBCMessageException(String)}.
   *
   * <p>Method under test: {@link DBCMessageException#DBCMessageException(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBCMessageException.<init>(String)"})
  public void testNewDBCMessageException() {
    // Arrange and Act
    DBCMessageException actualDbcMessageException = new DBCMessageException("An error occurred");

    // Assert
    assertEquals("An error occurred", actualDbcMessageException.getMessage());
    assertNull(actualDbcMessageException.getCause());
    assertNull(actualDbcMessageException.getExecutionContext());
    assertEquals(0, actualDbcMessageException.getSuppressed().length);
    assertTrue(actualDbcMessageException.hasMessage());
  }
}

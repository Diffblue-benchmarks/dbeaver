package org.jkiss.dbeaver.model.exec;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBCFeatureNotSupportedExceptionDiffblueTest {
  /**
   * Test {@link DBCFeatureNotSupportedException#DBCFeatureNotSupportedException(String)}.
   *
   * <ul>
   *   <li>Then return Message is {@code An error occurred}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DBCFeatureNotSupportedException#DBCFeatureNotSupportedException(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBCFeatureNotSupportedException.<init>()",
    "void DBCFeatureNotSupportedException.<init>(String)"
  })
  public void testNewDBCFeatureNotSupportedException_thenReturnMessageIsAnErrorOccurred() {
    // Arrange and Act
    DBCFeatureNotSupportedException actualDbcFeatureNotSupportedException =
        new DBCFeatureNotSupportedException("An error occurred");

    // Assert
    assertEquals("An error occurred", actualDbcFeatureNotSupportedException.getMessage());
    assertNull(actualDbcFeatureNotSupportedException.getCause());
    assertNull(actualDbcFeatureNotSupportedException.getExecutionContext());
    assertEquals(0, actualDbcFeatureNotSupportedException.getSuppressed().length);
    assertTrue(actualDbcFeatureNotSupportedException.hasMessage());
  }

  /**
   * Test {@link DBCFeatureNotSupportedException#DBCFeatureNotSupportedException()}.
   *
   * <ul>
   *   <li>Then return Message is {@code Not supported}.
   * </ul>
   *
   * <p>Method under test: {@link DBCFeatureNotSupportedException#DBCFeatureNotSupportedException()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBCFeatureNotSupportedException.<init>()",
    "void DBCFeatureNotSupportedException.<init>(String)"
  })
  public void testNewDBCFeatureNotSupportedException_thenReturnMessageIsNotSupported() {
    // Arrange and Act
    DBCFeatureNotSupportedException actualDbcFeatureNotSupportedException =
        new DBCFeatureNotSupportedException();

    // Assert
    assertEquals("Not supported", actualDbcFeatureNotSupportedException.getMessage());
    assertNull(actualDbcFeatureNotSupportedException.getCause());
    assertNull(actualDbcFeatureNotSupportedException.getExecutionContext());
    assertEquals(0, actualDbcFeatureNotSupportedException.getSuppressed().length);
    assertTrue(actualDbcFeatureNotSupportedException.hasMessage());
  }
}

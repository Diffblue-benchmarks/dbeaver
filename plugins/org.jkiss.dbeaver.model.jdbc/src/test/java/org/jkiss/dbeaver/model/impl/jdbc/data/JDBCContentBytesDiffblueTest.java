package org.jkiss.dbeaver.model.impl.jdbc.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.app.DBPPlatform;
import org.jkiss.dbeaver.model.data.DBDContentStorage;
import org.jkiss.dbeaver.model.data.DBDDisplayFormat;
import org.jkiss.dbeaver.model.data.storage.ExternalContentStorage;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class JDBCContentBytesDiffblueTest {
  /**
   * Test {@link JDBCContentBytes#JDBCContentBytes(DBCExecutionContext)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return ContentType is {@code application/octet-stream}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentBytes#JDBCContentBytes(DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCContentBytes.<init>(DBCExecutionContext)"})
  public void testNewJDBCContentBytes_whenNull_thenReturnContentTypeIsApplicationOctetStream()
      throws IOException {
    // Arrange and Act
    JDBCContentBytes actualJdbcContentBytes = new JDBCContentBytes(null);

    // Assert
    assertEquals("application/octet-stream", actualJdbcContentBytes.getContentType());
    assertNull(actualJdbcContentBytes.getRawValue());
    assertNull(actualJdbcContentBytes.getCachedValue());
    int actualReadResult = actualJdbcContentBytes.getContentStream().read(new byte[] {});
    assertEquals(-1, actualReadResult);
    assertEquals(0L, actualJdbcContentBytes.getContentLength());
    assertFalse(actualJdbcContentBytes.isModified());
    assertTrue(actualJdbcContentBytes.isNull());
  }

  /**
   * Test {@link JDBCContentBytes#getContentStream()}.
   *
   * <ul>
   *   <li>Then return read is minus one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentBytes#getContentStream()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.io.InputStream JDBCContentBytes.getContentStream()"})
  public void testGetContentStream_thenReturnReadIsMinusOne() throws IOException {
    // Arrange, Act and Assert
    int actualReadResult = new JDBCContentBytes(null).getContentStream().read(new byte[] {});
    assertEquals(-1, actualReadResult);
  }

  /**
   * Test {@link JDBCContentBytes#getContentLength()}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentBytes#getContentLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JDBCContentBytes.getContentLength()"})
  public void testGetContentLength_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(0L, new JDBCContentBytes(null).getContentLength());
  }

  /**
   * Test {@link JDBCContentBytes#cloneStorage(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then return {@link JDBCContentBytes#JDBCContentBytes(DBCExecutionContext)} with
   *       executionContext is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentBytes#cloneStorage(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JDBCContentBytes JDBCContentBytes.cloneStorage(DBRProgressMonitor)"})
  public void testCloneStorage_thenReturnJDBCContentBytesWithExecutionContextIsNull()
      throws IOException {
    // Arrange
    JDBCContentBytes jdbcContentBytes = new JDBCContentBytes(null);

    // Act
    JDBCContentBytes actualCloneStorageResult =
        jdbcContentBytes.cloneStorage(new LoggingProgressMonitor());

    // Assert
    assertEquals(jdbcContentBytes, actualCloneStorageResult);
  }

  /**
   * Test {@link JDBCContentBytes#getContents(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then return {@link JDBCContentBytes#JDBCContentBytes(DBCExecutionContext)} with
   *       executionContext is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentBytes#getContents(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBDContentStorage JDBCContentBytes.getContents(DBRProgressMonitor)"})
  public void testGetContents_thenReturnJDBCContentBytesWithExecutionContextIsNull()
      throws DBCException {
    // Arrange
    JDBCContentBytes jdbcContentBytes = new JDBCContentBytes(null);

    // Act
    DBDContentStorage actualContents = jdbcContentBytes.getContents(new LoggingProgressMonitor());

    // Assert
    assertSame(jdbcContentBytes, actualContents);
  }

  /**
   * Test {@link JDBCContentBytes#updateContents(DBRProgressMonitor, DBDContentStorage)}.
   *
   * <p>Method under test: {@link JDBCContentBytes#updateContents(DBRProgressMonitor,
   * DBDContentStorage)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean JDBCContentBytes.updateContents(DBRProgressMonitor, DBDContentStorage)"
  })
  public void testUpdateContents() throws IOException, DBException {
    // Arrange
    JDBCContentBytes jdbcContentBytes = new JDBCContentBytes(null);

    // Act
    jdbcContentBytes.updateContents(new LoggingProgressMonitor(), null);

    // Assert that nothing has changed
    int actualReadResult = jdbcContentBytes.getContentStream().read(new byte[] {});
    assertEquals(-1, actualReadResult);
    assertEquals(0L, jdbcContentBytes.getContentLength());
  }

  /**
   * Test {@link JDBCContentBytes#updateContents(DBRProgressMonitor, DBDContentStorage)}.
   *
   * <p>Method under test: {@link JDBCContentBytes#updateContents(DBRProgressMonitor,
   * DBDContentStorage)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean JDBCContentBytes.updateContents(DBRProgressMonitor, DBDContentStorage)"
  })
  public void testUpdateContents2() throws DBException {
    // Arrange
    JDBCContentBytes jdbcContentBytes = new JDBCContentBytes(null);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    Path file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    ExternalContentStorage storage = new ExternalContentStorage(mock(DBPPlatform.class), file);

    // Act and Assert
    assertThrows(DBCException.class, () -> jdbcContentBytes.updateContents(monitor, storage));
  }

  /**
   * Test {@link JDBCContentBytes#updateContents(DBRProgressMonitor, DBDContentStorage)}.
   *
   * <p>Method under test: {@link JDBCContentBytes#updateContents(DBRProgressMonitor,
   * DBDContentStorage)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean JDBCContentBytes.updateContents(DBRProgressMonitor, DBDContentStorage)"
  })
  public void testUpdateContents3() throws DBException {
    // Arrange
    JDBCContentBytes jdbcContentBytes = new JDBCContentBytes(null);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    Path file = Paths.get(System.getProperty("java.io.tmpdir"), "IO error while reading content");
    ExternalContentStorage storage = new ExternalContentStorage(mock(DBPPlatform.class), file);

    // Act and Assert
    assertThrows(DBCException.class, () -> jdbcContentBytes.updateContents(monitor, storage));
  }

  /**
   * Test {@link JDBCContentBytes#getRawValue()}.
   *
   * <ul>
   *   <li>Given {@link JDBCContentBytes#JDBCContentBytes(DBCExecutionContext)} with
   *       executionContext is {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentBytes#getRawValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] JDBCContentBytes.getRawValue()"})
  public void testGetRawValue_givenJDBCContentBytesWithExecutionContextIsNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new JDBCContentBytes(null).getRawValue());
  }

  /**
   * Test {@link JDBCContentBytes#isNull()}.
   *
   * <ul>
   *   <li>Given {@link JDBCContentBytes#JDBCContentBytes(DBCExecutionContext)} with
   *       executionContext is {@code null}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentBytes#isNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCContentBytes.isNull()"})
  public void testIsNull_givenJDBCContentBytesWithExecutionContextIsNull_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(new JDBCContentBytes(null).isNull());
  }

  /**
   * Test {@link JDBCContentBytes#getDisplayString(DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentBytes#getDisplayString(DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JDBCContentBytes.getDisplayString(DBDDisplayFormat)"})
  public void testGetDisplayString_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new JDBCContentBytes(null).getDisplayString(DBDDisplayFormat.UI));
  }

  /**
   * Test {@link JDBCContentBytes#cloneValue(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then return {@link JDBCContentBytes#JDBCContentBytes(DBCExecutionContext)} with
   *       executionContext is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentBytes#cloneValue(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JDBCContentBytes JDBCContentBytes.cloneValue(DBRProgressMonitor)"})
  public void testCloneValue_thenReturnJDBCContentBytesWithExecutionContextIsNull() {
    // Arrange
    JDBCContentBytes jdbcContentBytes = new JDBCContentBytes(null);

    // Act
    JDBCContentBytes actualCloneValueResult =
        jdbcContentBytes.cloneValue(new LoggingProgressMonitor());

    // Assert
    assertEquals(jdbcContentBytes, actualCloneValueResult);
  }

  /**
   * Test {@link JDBCContentBytes#equals(Object)}, and {@link JDBCContentBytes#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link JDBCContentBytes#equals(Object)}
   *   <li>{@link JDBCContentBytes#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCContentBytes.equals(Object)", "int JDBCContentBytes.hashCode()"})
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    JDBCContentBytes jdbcContentBytes = new JDBCContentBytes(null);
    JDBCContentBytes jdbcContentBytes2 = new JDBCContentBytes(null);

    // Act and Assert
    assertEquals(jdbcContentBytes, jdbcContentBytes2);
    assertEquals(jdbcContentBytes.hashCode(), jdbcContentBytes2.hashCode());
  }

  /**
   * Test {@link JDBCContentBytes#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentBytes#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCContentBytes.equals(Object)", "int JDBCContentBytes.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new JDBCContentBytes(null), 1);
  }
}

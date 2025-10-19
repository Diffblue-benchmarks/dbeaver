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
import org.jkiss.dbeaver.model.data.storage.TemporaryContentStorage;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class JDBCContentCharsDiffblueTest {
  /**
   * Test {@link JDBCContentChars#JDBCContentChars(JDBCContentChars)}.
   *
   * <p>Method under test: {@link JDBCContentChars#JDBCContentChars(JDBCContentChars)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCContentChars.<init>(JDBCContentChars)"})
  public void testNewJDBCContentChars() {
    // Arrange
    JDBCContentChars copyFrom = new JDBCContentChars(null, "Data");

    // Act
    JDBCContentChars actualJdbcContentChars = new JDBCContentChars(copyFrom);

    // Assert
    assertEquals(copyFrom, actualJdbcContentChars);
  }

  /**
   * Test {@link JDBCContentChars#JDBCContentChars(DBCExecutionContext, String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return CachedValue is {@code Data}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentChars#JDBCContentChars(DBCExecutionContext, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCContentChars.<init>(DBCExecutionContext, String)"})
  public void testNewJDBCContentChars_whenNull_thenReturnCachedValueIsData() throws IOException {
    // Arrange and Act
    JDBCContentChars actualJdbcContentChars = new JDBCContentChars(null, "Data");

    // Assert
    assertEquals("Data", actualJdbcContentChars.getCachedValue());
    assertEquals("Data", actualJdbcContentChars.getRawValue());
    assertEquals("text/plain", actualJdbcContentChars.getContentType());
    assertEquals(4L, actualJdbcContentChars.getContentLength());
    assertFalse(actualJdbcContentChars.isModified());
    assertFalse(actualJdbcContentChars.isNull());
    assertTrue(actualJdbcContentChars.getContentReader().ready());
  }

  /**
   * Test {@link JDBCContentChars#getContentStream()}.
   *
   * <ul>
   *   <li>Then return read is minus one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentChars#getContentStream()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.io.InputStream JDBCContentChars.getContentStream()"})
  public void testGetContentStream_thenReturnReadIsMinusOne() throws IOException {
    // Arrange
    JDBCContentChars jdbcContentChars = new JDBCContentChars(null, null);

    // Act and Assert
    int actualReadResult = jdbcContentChars.getContentStream().read(new byte[] {});
    assertEquals(-1, actualReadResult);
  }

  /**
   * Test {@link JDBCContentChars#getContentReader()}.
   *
   * <p>Method under test: {@link JDBCContentChars#getContentReader()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.io.Reader JDBCContentChars.getContentReader()"})
  public void testGetContentReader() throws IOException {
    // Arrange
    JDBCContentChars jdbcContentChars = new JDBCContentChars(null, null);

    // Act and Assert
    assertTrue(jdbcContentChars.getContentReader().ready());
  }

  /**
   * Test {@link JDBCContentChars#getContentReader()}.
   *
   * <ul>
   *   <li>Given {@link JDBCContentChars#JDBCContentChars(DBCExecutionContext, String)} with
   *       executionContext is {@code null} and {@code Data}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentChars#getContentReader()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.io.Reader JDBCContentChars.getContentReader()"})
  public void testGetContentReader_givenJDBCContentCharsWithExecutionContextIsNullAndData()
      throws IOException {
    // Arrange, Act and Assert
    assertTrue(new JDBCContentChars(null, "Data").getContentReader().ready());
  }

  /**
   * Test {@link JDBCContentChars#getContentLength()}.
   *
   * <ul>
   *   <li>Then return four.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentChars#getContentLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JDBCContentChars.getContentLength()"})
  public void testGetContentLength_thenReturnFour() {
    // Arrange, Act and Assert
    assertEquals(4L, new JDBCContentChars(null, "Data").getContentLength());
  }

  /**
   * Test {@link JDBCContentChars#getContentLength()}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentChars#getContentLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JDBCContentChars.getContentLength()"})
  public void testGetContentLength_thenReturnZero() {
    // Arrange
    JDBCContentChars jdbcContentChars = new JDBCContentChars(null, null);

    // Act and Assert
    assertEquals(0L, jdbcContentChars.getContentLength());
  }

  /**
   * Test {@link JDBCContentChars#getContents(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then return {@link JDBCContentChars#JDBCContentChars(DBCExecutionContext, String)} with
   *       executionContext is {@code null} and {@code Data}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentChars#getContents(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBDContentStorage JDBCContentChars.getContents(DBRProgressMonitor)"})
  public void testGetContents_thenReturnJDBCContentCharsWithExecutionContextIsNullAndData()
      throws DBCException {
    // Arrange
    JDBCContentChars jdbcContentChars = new JDBCContentChars(null, "Data");

    // Act
    DBDContentStorage actualContents = jdbcContentChars.getContents(new LoggingProgressMonitor());

    // Assert
    assertSame(jdbcContentChars, actualContents);
  }

  /**
   * Test {@link JDBCContentChars#updateContents(DBRProgressMonitor, DBDContentStorage)}.
   *
   * <p>Method under test: {@link JDBCContentChars#updateContents(DBRProgressMonitor,
   * DBDContentStorage)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean JDBCContentChars.updateContents(DBRProgressMonitor, DBDContentStorage)"
  })
  public void testUpdateContents() throws IOException, DBException {
    // Arrange
    JDBCContentChars jdbcContentChars = new JDBCContentChars(null, "Data");

    // Act
    boolean actualUpdateContentsResult =
        jdbcContentChars.updateContents(new LoggingProgressMonitor(), null);

    // Assert
    assertNull(jdbcContentChars.getCachedValue());
    assertNull(jdbcContentChars.getRawValue());
    int actualReadResult = jdbcContentChars.getContentStream().read(new byte[] {});
    assertEquals(-1, actualReadResult);
    assertEquals(0L, jdbcContentChars.getContentLength());
    assertFalse(actualUpdateContentsResult);
    assertTrue(jdbcContentChars.isModified());
    assertTrue(jdbcContentChars.isNull());
  }

  /**
   * Test {@link JDBCContentChars#updateContents(DBRProgressMonitor, DBDContentStorage)}.
   *
   * <p>Method under test: {@link JDBCContentChars#updateContents(DBRProgressMonitor,
   * DBDContentStorage)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean JDBCContentChars.updateContents(DBRProgressMonitor, DBDContentStorage)"
  })
  public void testUpdateContents2() throws DBException {
    // Arrange
    JDBCContentChars jdbcContentChars = new JDBCContentChars(null, "Data");
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    Path file =
        Paths.get(
            System.getProperty("java.io.tmpdir"),
            "Copy character content",
            "org.jkiss.dbeaver.application");
    TemporaryContentStorage storage =
        new TemporaryContentStorage(mock(DBPPlatform.class), file, "UTF-8", true);

    // Act and Assert
    assertThrows(DBCException.class, () -> jdbcContentChars.updateContents(monitor, storage));
  }

  /**
   * Test {@link JDBCContentChars#updateContents(DBRProgressMonitor, DBDContentStorage)}.
   *
   * <p>Method under test: {@link JDBCContentChars#updateContents(DBRProgressMonitor,
   * DBDContentStorage)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean JDBCContentChars.updateContents(DBRProgressMonitor, DBDContentStorage)"
  })
  public void testUpdateContents3() throws DBException {
    // Arrange
    JDBCContentChars jdbcContentChars = new JDBCContentChars(null, "Data");
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    Path file =
        Paths.get(
            System.getProperty("java.io.tmpdir"),
            "Copy character content",
            "org.jkiss.dbeaver.application");
    TemporaryContentStorage storage =
        new TemporaryContentStorage(mock(DBPPlatform.class), file, "", true);

    // Act and Assert
    assertThrows(DBCException.class, () -> jdbcContentChars.updateContents(monitor, storage));
  }

  /**
   * Test {@link JDBCContentChars#cloneStorage(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then return {@link JDBCContentChars#JDBCContentChars(DBCExecutionContext, String)} with
   *       executionContext is {@code null} and {@code Data}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentChars#cloneStorage(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JDBCContentChars JDBCContentChars.cloneStorage(DBRProgressMonitor)"})
  public void testCloneStorage_thenReturnJDBCContentCharsWithExecutionContextIsNullAndData() {
    // Arrange
    JDBCContentChars jdbcContentChars = new JDBCContentChars(null, "Data");

    // Act
    JDBCContentChars actualCloneStorageResult =
        jdbcContentChars.cloneStorage(new LoggingProgressMonitor());

    // Assert
    assertEquals(jdbcContentChars, actualCloneStorageResult);
  }

  /**
   * Test {@link JDBCContentChars#isNull()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentChars#isNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCContentChars.isNull()"})
  public void testIsNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new JDBCContentChars(null, "Data").isNull());
  }

  /**
   * Test {@link JDBCContentChars#isNull()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentChars#isNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCContentChars.isNull()"})
  public void testIsNull_thenReturnTrue() {
    // Arrange
    JDBCContentChars jdbcContentChars = new JDBCContentChars(null, null);

    // Act and Assert
    assertTrue(jdbcContentChars.isNull());
  }

  /**
   * Test {@link JDBCContentChars#equals(Object)}, and {@link JDBCContentChars#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link JDBCContentChars#equals(Object)}
   *   <li>{@link JDBCContentChars#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCContentChars.equals(Object)", "int JDBCContentChars.hashCode()"})
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    JDBCContentChars jdbcContentChars = new JDBCContentChars(null, "Data");
    JDBCContentChars jdbcContentChars2 = new JDBCContentChars(null, "Data");

    // Act and Assert
    assertEquals(jdbcContentChars, jdbcContentChars2);
    assertEquals(jdbcContentChars.hashCode(), jdbcContentChars2.hashCode());
  }

  /**
   * Test {@link JDBCContentChars#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentChars#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCContentChars.equals(Object)", "int JDBCContentChars.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    JDBCContentChars jdbcContentChars = new JDBCContentChars(null, "");

    // Act and Assert
    assertNotEquals(jdbcContentChars, new JDBCContentChars(null, "Data"));
  }

  /**
   * Test {@link JDBCContentChars#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentChars#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCContentChars.equals(Object)", "int JDBCContentChars.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange, Act and Assert
    assertNotEquals(new JDBCContentChars(null, "Data"), 1);
  }

  /**
   * Test {@link JDBCContentChars#getDisplayString(DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>Then return {@code Data}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentChars#getDisplayString(DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCContentChars.getDisplayString(DBDDisplayFormat)"})
  public void testGetDisplayString_thenReturnData() {
    // Arrange, Act and Assert
    assertEquals("Data", new JDBCContentChars(null, "Data").getDisplayString(DBDDisplayFormat.UI));
  }

  /**
   * Test {@link JDBCContentChars#cloneValue(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then return {@link JDBCContentChars#JDBCContentChars(DBCExecutionContext, String)} with
   *       executionContext is {@code null} and {@code Data}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentChars#cloneValue(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JDBCContentChars JDBCContentChars.cloneValue(DBRProgressMonitor)"})
  public void testCloneValue_thenReturnJDBCContentCharsWithExecutionContextIsNullAndData() {
    // Arrange
    JDBCContentChars jdbcContentChars = new JDBCContentChars(null, "Data");

    // Act
    JDBCContentChars actualCloneValueResult =
        jdbcContentChars.cloneValue(new LoggingProgressMonitor());

    // Assert
    assertEquals(jdbcContentChars, actualCloneValueResult);
  }
}

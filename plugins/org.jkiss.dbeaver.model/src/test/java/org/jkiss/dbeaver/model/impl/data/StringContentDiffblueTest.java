package org.jkiss.dbeaver.model.impl.data;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
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
import org.jkiss.dbeaver.model.data.storage.StringContentStorage;
import org.jkiss.dbeaver.model.data.storage.TemporaryContentStorage;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class StringContentDiffblueTest {
  /**
   * Test {@link StringContent#StringContent(DBCExecutionContext, String)}.
   *
   * <p>Method under test: {@link StringContent#StringContent(DBCExecutionContext, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void StringContent.<init>(DBCExecutionContext, String)"})
  public void testNewStringContent() throws DBCException {
    // Arrange and Act
    StringContent actualStringContent = new StringContent(mock(DBCExecutionContext.class), "Data");

    // Assert
    assertEquals("Data", actualStringContent.getRawValue());
    assertEquals("text/plain", actualStringContent.getContentType());
    assertNull(actualStringContent.getDataSource());
    assertEquals(4L, actualStringContent.getContentLength());
    assertFalse(actualStringContent.isModified());
    assertFalse(actualStringContent.isNull());
  }

  /**
   * Test {@link StringContent#getContentLength()}.
   *
   * <p>Method under test: {@link StringContent#getContentLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long StringContent.getContentLength()"})
  public void testGetContentLength() throws DBCException {
    // Arrange, Act and Assert
    assertEquals(4L, new StringContent(mock(DBCExecutionContext.class), "Data").getContentLength());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link StringContent#release()}
   *   <li>{@link StringContent#getContentType()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String StringContent.getContentType()", "void StringContent.release()"})
  public void testGettersAndSetters() {
    // Arrange
    StringContent stringContent = new StringContent(mock(DBCExecutionContext.class), "Data");

    // Act
    stringContent.release();

    // Assert
    assertEquals("text/plain", stringContent.getContentType());
  }

  /**
   * Test {@link StringContent#getDisplayString(DBDDisplayFormat)}.
   *
   * <p>Method under test: {@link StringContent#getDisplayString(DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String StringContent.getDisplayString(DBDDisplayFormat)"})
  public void testGetDisplayString() {
    // Arrange, Act and Assert
    assertEquals(
        "Data",
        new StringContent(mock(DBCExecutionContext.class), "Data")
            .getDisplayString(DBDDisplayFormat.UI));
  }

  /**
   * Test {@link StringContent#getContents(DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link StringContent#getContents(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBDContentStorage StringContent.getContents(DBRProgressMonitor)"})
  public void testGetContents() throws IOException, DBCException {
    // Arrange
    StringContent stringContent = new StringContent(mock(DBCExecutionContext.class), "Data");

    // Act
    DBDContentStorage actualContents = stringContent.getContents(new LoggingProgressMonitor());

    // Assert
    assertTrue(actualContents instanceof StringContentStorage);
    assertEquals("Data", ((StringContentStorage) actualContents).getCachedValue());
    assertEquals("UTF-8", actualContents.getCharset());
    byte[] byteArray = new byte[4];
    assertEquals(4, actualContents.getContentStream().read(byteArray));
    assertEquals(4L, actualContents.getContentLength());
    assertTrue(actualContents.getContentReader().ready());
    assertArrayEquals("Data".getBytes("UTF-8"), byteArray);
  }

  /**
   * Test {@link StringContent#updateContents(DBRProgressMonitor, DBDContentStorage)}.
   *
   * <ul>
   *   <li>Then throw {@link DBCException}.
   * </ul>
   *
   * <p>Method under test: {@link StringContent#updateContents(DBRProgressMonitor,
   * DBDContentStorage)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean StringContent.updateContents(DBRProgressMonitor, DBDContentStorage)"})
  public void testUpdateContents_thenThrowDBCException() throws DBException {
    // Arrange
    StringContent stringContent = new StringContent(mock(DBCExecutionContext.class), "42");
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    Path file = Paths.get(System.getProperty("java.io.tmpdir"), "Copy character content");
    TemporaryContentStorage storage =
        new TemporaryContentStorage(mock(DBPPlatform.class), file, "UTF-8", true);

    // Act and Assert
    assertThrows(DBCException.class, () -> stringContent.updateContents(monitor, storage));
  }

  /**
   * Test {@link StringContent#getRawValue()}.
   *
   * <p>Method under test: {@link StringContent#getRawValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String StringContent.getRawValue()"})
  public void testGetRawValue() {
    // Arrange, Act and Assert
    assertEquals("Data", new StringContent(mock(DBCExecutionContext.class), "Data").getRawValue());
  }

  /**
   * Test {@link StringContent#isNull()}.
   *
   * <p>Method under test: {@link StringContent#isNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean StringContent.isNull()"})
  public void testIsNull() {
    // Arrange, Act and Assert
    assertFalse(new StringContent(mock(DBCExecutionContext.class), "Data").isNull());
  }
}

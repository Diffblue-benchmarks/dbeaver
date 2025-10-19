package org.jkiss.dbeaver.model.data.storage;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import org.jkiss.dbeaver.model.data.DBDContentStorage;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class StringContentStorageDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link StringContentStorage#StringContentStorage(String)}
   *   <li>{@link StringContentStorage#release()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StringContentStorage.<init>(String)",
    "void StringContentStorage.release()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    StringContentStorage actualStringContentStorage = new StringContentStorage("Data");
    actualStringContentStorage.release();

    // Assert
    assertNull(actualStringContentStorage.getCachedValue());
  }

  /**
   * Test {@link StringContentStorage#getContentStream()}.
   *
   * <p>Method under test: {@link StringContentStorage#getContentStream()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.io.InputStream StringContentStorage.getContentStream()"})
  public void testGetContentStream() throws IOException {
    // Arrange, Act and Assert
    byte[] byteArray = new byte[4];
    assertEquals(4, new StringContentStorage("Data").getContentStream().read(byteArray));
    assertArrayEquals("Data".getBytes("UTF-8"), byteArray);
  }

  /**
   * Test {@link StringContentStorage#getContentReader()}.
   *
   * <ul>
   *   <li>Given {@link StringContentStorage#StringContentStorage(String)} with {@code Data}.
   * </ul>
   *
   * <p>Method under test: {@link StringContentStorage#getContentReader()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Reader StringContentStorage.getContentReader()"})
  public void testGetContentReader_givenStringContentStorageWithData() throws IOException {
    // Arrange, Act and Assert
    assertTrue(new StringContentStorage("Data").getContentReader().ready());
  }

  /**
   * Test {@link StringContentStorage#getContentReader()}.
   *
   * <ul>
   *   <li>Given {@link StringContentStorage#StringContentStorage(String)} with data is {@code
   *       null}.
   * </ul>
   *
   * <p>Method under test: {@link StringContentStorage#getContentReader()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Reader StringContentStorage.getContentReader()"})
  public void testGetContentReader_givenStringContentStorageWithDataIsNull() throws IOException {
    // Arrange, Act and Assert
    assertTrue(new StringContentStorage(null).getContentReader().ready());
  }

  /**
   * Test {@link StringContentStorage#getContentLength()}.
   *
   * <ul>
   *   <li>Given {@link StringContentStorage#StringContentStorage(String)} with data is {@code
   *       null}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link StringContentStorage#getContentLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long StringContentStorage.getContentLength()"})
  public void testGetContentLength_givenStringContentStorageWithDataIsNull_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(0L, new StringContentStorage(null).getContentLength());
  }

  /**
   * Test {@link StringContentStorage#getContentLength()}.
   *
   * <ul>
   *   <li>Given {@link StringContentStorage#StringContentStorage(String)} with {@code Data}.
   *   <li>Then return four.
   * </ul>
   *
   * <p>Method under test: {@link StringContentStorage#getContentLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long StringContentStorage.getContentLength()"})
  public void testGetContentLength_givenStringContentStorageWithData_thenReturnFour() {
    // Arrange, Act and Assert
    assertEquals(4L, new StringContentStorage("Data").getContentLength());
  }

  /**
   * Test {@link StringContentStorage#getCharset()}.
   *
   * <p>Method under test: {@link StringContentStorage#getCharset()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String StringContentStorage.getCharset()"})
  public void testGetCharset() {
    // Arrange, Act and Assert
    assertEquals("UTF-8", new StringContentStorage("Data").getCharset());
  }

  /**
   * Test {@link StringContentStorage#cloneStorage(DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link StringContentStorage#cloneStorage(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBDContentStorage StringContentStorage.cloneStorage(DBRProgressMonitor)"})
  public void testCloneStorage() throws IOException {
    // Arrange
    StringContentStorage stringContentStorage = new StringContentStorage("Data");

    // Act
    DBDContentStorage actualCloneStorageResult =
        stringContentStorage.cloneStorage(new LoggingProgressMonitor());

    // Assert
    assertTrue(actualCloneStorageResult instanceof StringContentStorage);
    assertEquals("Data", ((StringContentStorage) actualCloneStorageResult).getCachedValue());
    assertEquals("UTF-8", actualCloneStorageResult.getCharset());
    byte[] byteArray = new byte[4];
    assertEquals(4, actualCloneStorageResult.getContentStream().read(byteArray));
    assertEquals(4L, actualCloneStorageResult.getContentLength());
    assertTrue(actualCloneStorageResult.getContentReader().ready());
    assertArrayEquals("Data".getBytes("UTF-8"), byteArray);
  }

  /**
   * Test {@link StringContentStorage#createFromReader(Reader, long)} with {@code stream}, {@code
   * contentLength}.
   *
   * <ul>
   *   <li>When {@code 1073741823}.
   * </ul>
   *
   * <p>Method under test: {@link StringContentStorage#createFromReader(Reader, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"StringContentStorage StringContentStorage.createFromReader(Reader, long)"})
  public void testCreateFromReaderWithStreamContentLength_when1073741823() throws IOException {
    // Arrange and Act
    StringContentStorage actualCreateFromReaderResult =
        StringContentStorage.createFromReader(new StringReader("foo"), 1073741823L);

    // Assert
    assertEquals("UTF-8", actualCreateFromReaderResult.getCharset());
    assertEquals("foo", actualCreateFromReaderResult.getCachedValue());
    byte[] byteArray = new byte[3];
    assertEquals(3, actualCreateFromReaderResult.getContentStream().read(byteArray));
    assertEquals(3L, actualCreateFromReaderResult.getContentLength());
    assertTrue(actualCreateFromReaderResult.getContentReader().ready());
    assertArrayEquals("foo".getBytes("UTF-8"), byteArray);
  }

  /**
   * Test {@link StringContentStorage#createFromReader(Reader, long)} with {@code stream}, {@code
   * contentLength}.
   *
   * <ul>
   *   <li>When {@code 1073741824}.
   *   <li>Then throw {@link IOException}.
   * </ul>
   *
   * <p>Method under test: {@link StringContentStorage#createFromReader(Reader, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"StringContentStorage StringContentStorage.createFromReader(Reader, long)"})
  public void testCreateFromReaderWithStreamContentLength_when1073741824_thenThrowIOException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IOException.class,
        () -> StringContentStorage.createFromReader(new StringReader("foo"), 1073741824L));
  }

  /**
   * Test {@link StringContentStorage#createFromReader(Reader, long)} with {@code stream}, {@code
   * contentLength}.
   *
   * <ul>
   *   <li>When three.
   *   <li>Then return Charset is {@code UTF-8}.
   * </ul>
   *
   * <p>Method under test: {@link StringContentStorage#createFromReader(Reader, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"StringContentStorage StringContentStorage.createFromReader(Reader, long)"})
  public void testCreateFromReaderWithStreamContentLength_whenThree_thenReturnCharsetIsUtf8()
      throws IOException {
    // Arrange and Act
    StringContentStorage actualCreateFromReaderResult =
        StringContentStorage.createFromReader(new StringReader("foo"), 3L);

    // Assert
    assertEquals("UTF-8", actualCreateFromReaderResult.getCharset());
    assertEquals("foo", actualCreateFromReaderResult.getCachedValue());
    byte[] byteArray = new byte[3];
    assertEquals(3, actualCreateFromReaderResult.getContentStream().read(byteArray));
    assertEquals(3L, actualCreateFromReaderResult.getContentLength());
    assertTrue(actualCreateFromReaderResult.getContentReader().ready());
    assertArrayEquals("foo".getBytes("UTF-8"), byteArray);
  }

  /**
   * Test {@link StringContentStorage#createFromReader(Reader)} with {@code stream}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then return Charset is {@code UTF-8}.
   * </ul>
   *
   * <p>Method under test: {@link StringContentStorage#createFromReader(Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"StringContentStorage StringContentStorage.createFromReader(Reader)"})
  public void testCreateFromReaderWithStream_whenStringReaderWithFoo_thenReturnCharsetIsUtf8()
      throws IOException {
    // Arrange and Act
    StringContentStorage actualCreateFromReaderResult =
        StringContentStorage.createFromReader(new StringReader("foo"));

    // Assert
    assertEquals("UTF-8", actualCreateFromReaderResult.getCharset());
    assertEquals("foo", actualCreateFromReaderResult.getCachedValue());
    byte[] byteArray = new byte[3];
    assertEquals(3, actualCreateFromReaderResult.getContentStream().read(byteArray));
    assertEquals(3L, actualCreateFromReaderResult.getContentLength());
    assertTrue(actualCreateFromReaderResult.getContentReader().ready());
    assertArrayEquals("foo".getBytes("UTF-8"), byteArray);
  }

  /**
   * Test {@link StringContentStorage#getCachedValue()}.
   *
   * <p>Method under test: {@link StringContentStorage#getCachedValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String StringContentStorage.getCachedValue()"})
  public void testGetCachedValue() {
    // Arrange, Act and Assert
    assertEquals("Data", new StringContentStorage("Data").getCachedValue());
  }
}

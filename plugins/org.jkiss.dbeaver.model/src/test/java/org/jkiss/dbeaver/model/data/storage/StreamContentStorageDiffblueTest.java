package org.jkiss.dbeaver.model.data.storage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.jkiss.dbeaver.model.data.DBDContentStorage;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class StreamContentStorageDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link StreamContentStorage#StreamContentStorage(InputStream)}
   *   <li>{@link StreamContentStorage#getCharset()}
   *   <li>{@link StreamContentStorage#getContentLength()}
   *   <li>{@link StreamContentStorage#getContentStream()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamContentStorage.<init>(InputStream)",
    "String StreamContentStorage.getCharset()",
    "long StreamContentStorage.getContentLength()",
    "InputStream StreamContentStorage.getContentStream()"
  })
  public void testGettersAndSetters() throws IOException {
    // Arrange
    ByteArrayInputStream stream = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));

    // Act
    StreamContentStorage actualStreamContentStorage = new StreamContentStorage(stream);
    String actualCharset = actualStreamContentStorage.getCharset();
    long actualContentLength = actualStreamContentStorage.getContentLength();

    // Assert
    assertEquals("UTF-8", actualCharset);
    assertEquals(-1L, actualContentLength);
    assertSame(stream, actualStreamContentStorage.getContentStream());
  }

  /**
   * Test {@link StreamContentStorage#cloneStorage(DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link StreamContentStorage#cloneStorage(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBDContentStorage StreamContentStorage.cloneStorage(DBRProgressMonitor)"})
  public void testCloneStorage() throws IOException {
    // Arrange
    StreamContentStorage streamContentStorage =
        new StreamContentStorage(new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));

    // Act
    DBDContentStorage actualCloneStorageResult =
        streamContentStorage.cloneStorage(new LoggingProgressMonitor());

    // Assert
    assertTrue(actualCloneStorageResult instanceof StreamContentStorage);
    assertEquals("UTF-8", actualCloneStorageResult.getCharset());
    assertEquals(-1L, actualCloneStorageResult.getContentLength());
  }

  /**
   * Test {@link StreamContentStorage#release()}.
   *
   * <ul>
   *   <li>Given {@link DataInputStream} {@link DataInputStream#close()} throw {@link
   *       IOException#IOException()}.
   *   <li>Then calls {@link DataInputStream#close()}.
   * </ul>
   *
   * <p>Method under test: {@link StreamContentStorage#release()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void StreamContentStorage.release()"})
  public void testRelease_givenDataInputStreamCloseThrowIOException_thenCallsClose()
      throws IOException {
    // Arrange
    DataInputStream stream = mock(DataInputStream.class);
    doThrow(new IOException()).when(stream).close();

    // Act
    new StreamContentStorage(stream).release();

    // Assert
    verify(stream).close();
  }
}

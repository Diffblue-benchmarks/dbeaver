package org.jkiss.dbeaver.runtime;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ProgressStreamReaderDiffblueTest {
  /**
   * Test {@link ProgressStreamReader#ProgressStreamReader(DBRProgressMonitor, String, InputStream,
   * long)}.
   *
   * <p>Method under test: {@link ProgressStreamReader#ProgressStreamReader(DBRProgressMonitor,
   * String, InputStream, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ProgressStreamReader.<init>(DBRProgressMonitor, String, InputStream, long)"
  })
  public void testNewProgressStreamReader() throws IOException {
    // Arrange
    ByteArrayInputStream original = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));

    // Act
    ProgressStreamReader actualProgressStreamReader =
        new ProgressStreamReader(new LoggingProgressMonitor(), "Task", original, 3L);

    // Assert
    byte[] b = new byte[8];
    int actualReadResult = actualProgressStreamReader.read(b);
    int actualReadResult2 = original.read(new byte[] {});
    assertEquals(-1, actualReadResult2);
    assertEquals(8, actualReadResult);
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), b);
  }

  /**
   * Test {@link ProgressStreamReader#read(byte[])} with {@code b}.
   *
   * <p>Method under test: {@link ProgressStreamReader#read(byte[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int ProgressStreamReader.read(byte[])"})
  public void testReadWithB() throws IOException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    ProgressStreamReader progressStreamReader =
        new ProgressStreamReader(
            monitor, "Task", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), 3L);

    // Act
    int actualReadResult = progressStreamReader.read("AXAXAXAX".getBytes("UTF-8"));

    // Assert
    int actualReadResult2 = progressStreamReader.read(new byte[] {});
    assertEquals(-1, actualReadResult2);
    assertEquals(8, actualReadResult);
  }

  /**
   * Test {@link ProgressStreamReader#read(byte[], int, int)} with {@code b}, {@code off}, {@code
   * len}.
   *
   * <ul>
   *   <li>Then return three.
   * </ul>
   *
   * <p>Method under test: {@link ProgressStreamReader#read(byte[], int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int ProgressStreamReader.read(byte[], int, int)"})
  public void testReadWithBOffLen_thenReturnThree() throws IOException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    ProgressStreamReader progressStreamReader =
        new ProgressStreamReader(
            monitor, "Task", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), 3L);
    byte[] b = "AXAXAXAX".getBytes("UTF-8");

    // Act and Assert
    assertEquals(3, progressStreamReader.read(b, 1, 3));
    byte[] b2 = new byte[5];
    assertEquals(5, progressStreamReader.read(b2));
    assertArrayEquals("AAXAAXAX".getBytes("UTF-8"), b);
    assertArrayEquals("XAXAX".getBytes("UTF-8"), b2);
  }

  /**
   * Test {@link ProgressStreamReader#read()}.
   *
   * <ul>
   *   <li>Given {@link ByteArrayInputStream#ByteArrayInputStream(byte[])} with {@code AXAXAXAX}
   *       Bytes is {@code UTF-8}.
   *   <li>Then return sixty-five.
   * </ul>
   *
   * <p>Method under test: {@link ProgressStreamReader#read()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int ProgressStreamReader.read()"})
  public void testRead_givenByteArrayInputStreamWithAxaxaxaxBytesIsUtf8_thenReturnSixtyFive()
      throws IOException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    ProgressStreamReader progressStreamReader =
        new ProgressStreamReader(
            monitor, "Task", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), 3L);

    // Act and Assert
    assertEquals(65, progressStreamReader.read());
    byte[] b = new byte[7];
    assertEquals(7, progressStreamReader.read(b));
    assertArrayEquals("XAXAXAX".getBytes("UTF-8"), b);
  }

  /**
   * Test {@link ProgressStreamReader#skip(long)}.
   *
   * <ul>
   *   <li>Given {@link ByteArrayInputStream#ByteArrayInputStream(byte[])} with {@code AXAXAXAX}
   *       Bytes is {@code UTF-8}.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link ProgressStreamReader#skip(long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long ProgressStreamReader.skip(long)"})
  public void testSkip_givenByteArrayInputStreamWithAxaxaxaxBytesIsUtf8_thenReturnOne()
      throws IOException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    ProgressStreamReader progressStreamReader =
        new ProgressStreamReader(
            monitor, "Task", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), 3L);

    // Act and Assert
    assertEquals(1L, progressStreamReader.skip(1L));
    byte[] b = new byte[7];
    assertEquals(7, progressStreamReader.read(b));
    assertArrayEquals("XAXAXAX".getBytes("UTF-8"), b);
  }

  /**
   * Test {@link ProgressStreamReader#available()}.
   *
   * <ul>
   *   <li>Given {@link ByteArrayInputStream#ByteArrayInputStream(byte[])} with {@code AXAXAXAX}
   *       Bytes is {@code UTF-8}.
   *   <li>Then return eight.
   * </ul>
   *
   * <p>Method under test: {@link ProgressStreamReader#available()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int ProgressStreamReader.available()"})
  public void testAvailable_givenByteArrayInputStreamWithAxaxaxaxBytesIsUtf8_thenReturnEight()
      throws IOException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    ProgressStreamReader progressStreamReader =
        new ProgressStreamReader(
            monitor, "Task", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), 3L);

    // Act and Assert
    assertEquals(8, progressStreamReader.available());
  }

  /**
   * Test {@link ProgressStreamReader#available()}.
   *
   * <ul>
   *   <li>Given {@link DataInputStream} {@link DataInputStream#available()} throw {@link
   *       IOException#IOException()}.
   *   <li>Then throw {@link IOException}.
   * </ul>
   *
   * <p>Method under test: {@link ProgressStreamReader#available()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int ProgressStreamReader.available()"})
  public void testAvailable_givenDataInputStreamAvailableThrowIOException_thenThrowIOException()
      throws IOException {
    // Arrange
    DataInputStream original = mock(DataInputStream.class);
    when(original.available()).thenThrow(new IOException());
    ProgressStreamReader progressStreamReader =
        new ProgressStreamReader(new LoggingProgressMonitor(), "Task", original, 3L);

    // Act and Assert
    assertThrows(IOException.class, () -> progressStreamReader.available());
    verify(original).available();
  }

  /**
   * Test {@link ProgressStreamReader#close()}.
   *
   * <ul>
   *   <li>Given {@link ByteArrayInputStream#ByteArrayInputStream(byte[])} with {@code AXAXAXAX}
   *       Bytes is {@code UTF-8}.
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link ProgressStreamReader#close()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ProgressStreamReader.close()"})
  public void testClose_givenByteArrayInputStreamWithAxaxaxaxBytesIsUtf8_thenDoesNotThrow()
      throws IOException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    try (ProgressStreamReader progressStreamReader =
        new ProgressStreamReader(
            monitor, "Task", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), 3L)) {}

    // Act and Assert
  }
}

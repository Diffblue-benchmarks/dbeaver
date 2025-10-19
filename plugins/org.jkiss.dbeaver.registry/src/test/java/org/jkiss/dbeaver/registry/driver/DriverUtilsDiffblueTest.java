package org.jkiss.dbeaver.registry.driver;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class DriverUtilsDiffblueTest {
  /**
   * Test {@link DriverUtils#copyZipStream(InputStream, OutputStream)}.
   *
   * <ul>
   *   <li>Given {@link IOException#IOException()}.
   *   <li>Then throw {@link IOException}.
   * </ul>
   *
   * <p>Method under test: {@link DriverUtils#copyZipStream(InputStream, OutputStream)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DriverUtils.copyZipStream(InputStream, OutputStream)"})
  public void testCopyZipStream_givenIOException_thenThrowIOException() throws IOException {
    // Arrange
    DataInputStream inputStream = mock(DataInputStream.class);
    when(inputStream.read(Mockito.<byte[]>any())).thenThrow(new IOException());

    // Act and Assert
    assertThrows(
        IOException.class,
        () -> DriverUtils.copyZipStream(inputStream, new ByteArrayOutputStream()));
    verify(inputStream).read(isA(byte[].class));
  }

  /**
   * Test {@link DriverUtils#copyZipStream(InputStream, OutputStream)}.
   *
   * <ul>
   *   <li>Then {@link ByteArrayInputStream#ByteArrayInputStream(byte[])} with {@code AXAXAXAX}
   *       Bytes is {@code UTF-8} read is minus one.
   * </ul>
   *
   * <p>Method under test: {@link DriverUtils#copyZipStream(InputStream, OutputStream)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DriverUtils.copyZipStream(InputStream, OutputStream)"})
  public void testCopyZipStream_thenByteArrayInputStreamWithAxaxaxaxBytesIsUtf8ReadIsMinusOne()
      throws IOException {
    // Arrange
    ByteArrayInputStream inputStream = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    // Act
    DriverUtils.copyZipStream(inputStream, outputStream);

    // Assert
    int actualReadResult = inputStream.read(new byte[] {});
    assertEquals(-1, actualReadResult);
    byte[] expectedToByteArrayResult = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedToByteArrayResult, outputStream.toByteArray());
  }

  /**
   * Test {@link DriverUtils#calculateBytesCRC(byte[])}.
   *
   * <ul>
   *   <li>When {@code AXAXAXAX} Bytes is {@code UTF-8}.
   *   <li>Then return {@code 2442985691}.
   * </ul>
   *
   * <p>Method under test: {@link DriverUtils#calculateBytesCRC(byte[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long DriverUtils.calculateBytesCRC(byte[])"})
  public void testCalculateBytesCRC_whenAxaxaxaxBytesIsUtf8_thenReturn2442985691()
      throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(2442985691L, DriverUtils.calculateBytesCRC("AXAXAXAX".getBytes("UTF-8")));
  }
}

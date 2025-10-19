package org.jkiss.dbeaver.utils;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
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
import java.nio.charset.Charset;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class UnicodeReaderDiffblueTest {
  /**
   * Test {@link UnicodeReader#UnicodeReader(InputStream, Charset)}.
   *
   * <p>Method under test: {@link UnicodeReader#UnicodeReader(InputStream, Charset)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void UnicodeReader.<init>(InputStream, Charset)"})
  public void testNewUnicodeReader() throws IOException {
    // Arrange
    ByteArrayInputStream in =
        new ByteArrayInputStream(new byte[] {-17, 'X', 'A', 'X', 'A', 'X', 'A', 'X'});

    // Act
    UnicodeReader actualUnicodeReader =
        new UnicodeReader(in, Charset.forName(GeneralUtils.DEFAULT_ENCODING));

    // Assert
    assertEquals("UTF8", actualUnicodeReader.getEncoding());
    byte[] byteArray = new byte[4];
    assertEquals(4, in.read(byteArray));
    assertFalse(actualUnicodeReader.ready());
    assertArrayEquals("AXAX".getBytes("UTF-8"), byteArray);
  }

  /**
   * Test {@link UnicodeReader#UnicodeReader(InputStream, Charset)}.
   *
   * <p>Method under test: {@link UnicodeReader#UnicodeReader(InputStream, Charset)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void UnicodeReader.<init>(InputStream, Charset)"})
  public void testNewUnicodeReader2() throws IOException {
    // Arrange
    ByteArrayInputStream in =
        new ByteArrayInputStream(new byte[] {-2, 'X', 'A', 'X', 'A', 'X', 'A', 'X'});

    // Act
    UnicodeReader actualUnicodeReader =
        new UnicodeReader(in, Charset.forName(GeneralUtils.DEFAULT_ENCODING));

    // Assert
    assertEquals("UTF8", actualUnicodeReader.getEncoding());
    byte[] byteArray = new byte[4];
    assertEquals(4, in.read(byteArray));
    assertFalse(actualUnicodeReader.ready());
    assertArrayEquals("AXAX".getBytes("UTF-8"), byteArray);
  }

  /**
   * Test {@link UnicodeReader#UnicodeReader(InputStream, Charset)}.
   *
   * <p>Method under test: {@link UnicodeReader#UnicodeReader(InputStream, Charset)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void UnicodeReader.<init>(InputStream, Charset)"})
  public void testNewUnicodeReader3() throws IOException {
    // Arrange
    ByteArrayInputStream in =
        new ByteArrayInputStream(new byte[] {-1, 'X', 'A', 'X', 'A', 'X', 'A', 'X'});

    // Act
    UnicodeReader actualUnicodeReader =
        new UnicodeReader(in, Charset.forName(GeneralUtils.DEFAULT_ENCODING));

    // Assert
    assertEquals("UTF8", actualUnicodeReader.getEncoding());
    byte[] byteArray = new byte[4];
    assertEquals(4, in.read(byteArray));
    assertFalse(actualUnicodeReader.ready());
    assertArrayEquals("AXAX".getBytes("UTF-8"), byteArray);
  }

  /**
   * Test {@link UnicodeReader#UnicodeReader(InputStream, Charset)}.
   *
   * <p>Method under test: {@link UnicodeReader#UnicodeReader(InputStream, Charset)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void UnicodeReader.<init>(InputStream, Charset)"})
  public void testNewUnicodeReader4() throws IOException {
    // Arrange
    ByteArrayInputStream in =
        new ByteArrayInputStream(new byte[] {0, 'X', 'A', 'X', 'A', 'X', 'A', 'X'});

    // Act
    UnicodeReader actualUnicodeReader =
        new UnicodeReader(in, Charset.forName(GeneralUtils.DEFAULT_ENCODING));

    // Assert
    assertEquals("UTF8", actualUnicodeReader.getEncoding());
    byte[] byteArray = new byte[4];
    assertEquals(4, in.read(byteArray));
    assertFalse(actualUnicodeReader.ready());
    assertArrayEquals("AXAX".getBytes("UTF-8"), byteArray);
  }

  /**
   * Test {@link UnicodeReader#UnicodeReader(InputStream, Charset)}.
   *
   * <p>Method under test: {@link UnicodeReader#UnicodeReader(InputStream, Charset)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void UnicodeReader.<init>(InputStream, Charset)"})
  public void testNewUnicodeReader5() throws IOException {
    // Arrange
    ByteArrayInputStream in =
        new ByteArrayInputStream(new byte[] {-17, -69, 'A', 'X', 'A', 'X', 'A', 'X'});

    // Act
    UnicodeReader actualUnicodeReader =
        new UnicodeReader(in, Charset.forName(GeneralUtils.DEFAULT_ENCODING));

    // Assert
    assertEquals("UTF8", actualUnicodeReader.getEncoding());
    byte[] byteArray = new byte[4];
    assertEquals(4, in.read(byteArray));
    assertFalse(actualUnicodeReader.ready());
    assertArrayEquals("AXAX".getBytes("UTF-8"), byteArray);
  }

  /**
   * Test {@link UnicodeReader#UnicodeReader(InputStream, Charset)}.
   *
   * <p>Method under test: {@link UnicodeReader#UnicodeReader(InputStream, Charset)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void UnicodeReader.<init>(InputStream, Charset)"})
  public void testNewUnicodeReader6() throws IOException {
    // Arrange
    ByteArrayInputStream in =
        new ByteArrayInputStream(new byte[] {-2, -1, 'A', 'X', 'A', 'X', 'A', 'X'});

    // Act
    UnicodeReader actualUnicodeReader =
        new UnicodeReader(in, Charset.forName(GeneralUtils.DEFAULT_ENCODING));

    // Assert
    byte[] byteArray = new byte[4];
    assertEquals(4, in.read(byteArray));
    assertFalse(actualUnicodeReader.ready());
    String expectedEncoding =
        String.join("", System.getProperty("sun.io.unicode.encoding"), "Unmarked");
    assertEquals(expectedEncoding, actualUnicodeReader.getEncoding());
    assertArrayEquals("AXAX".getBytes("UTF-8"), byteArray);
  }

  /**
   * Test {@link UnicodeReader#UnicodeReader(InputStream, Charset)}.
   *
   * <p>Method under test: {@link UnicodeReader#UnicodeReader(InputStream, Charset)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void UnicodeReader.<init>(InputStream, Charset)"})
  public void testNewUnicodeReader7() throws IOException {
    // Arrange
    ByteArrayInputStream in =
        new ByteArrayInputStream(new byte[] {-17, -69, -65, 'X', 'A', 'X', 'A', 'X'});

    // Act
    UnicodeReader actualUnicodeReader =
        new UnicodeReader(in, Charset.forName(GeneralUtils.DEFAULT_ENCODING));

    // Assert
    assertEquals("UTF8", actualUnicodeReader.getEncoding());
    byte[] byteArray = new byte[4];
    assertEquals(4, in.read(byteArray));
    assertFalse(actualUnicodeReader.ready());
    assertArrayEquals("AXAX".getBytes("UTF-8"), byteArray);
  }

  /**
   * Test {@link UnicodeReader#UnicodeReader(InputStream, Charset)}.
   *
   * <ul>
   *   <li>Given {@link IOException#IOException()}.
   *   <li>Then throw {@link IOException}.
   * </ul>
   *
   * <p>Method under test: {@link UnicodeReader#UnicodeReader(InputStream, Charset)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void UnicodeReader.<init>(InputStream, Charset)"})
  public void testNewUnicodeReader_givenIOException_thenThrowIOException() throws IOException {
    // Arrange
    DataInputStream in = mock(DataInputStream.class);
    when(in.read(Mockito.<byte[]>any(), anyInt(), anyInt())).thenThrow(new IOException());

    // Act and Assert
    assertThrows(
        IOException.class,
        () -> new UnicodeReader(in, Charset.forName(GeneralUtils.DEFAULT_ENCODING)));
    verify(in).read(isA(byte[].class), eq(0), eq(4));
  }

  /**
   * Test {@link UnicodeReader#UnicodeReader(InputStream, Charset)}.
   *
   * <ul>
   *   <li>Then {@link ByteArrayInputStream#ByteArrayInputStream(byte[])} with {@code AXAXAXAX}
   *       Bytes is {@code UTF-8} read is four.
   * </ul>
   *
   * <p>Method under test: {@link UnicodeReader#UnicodeReader(InputStream, Charset)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void UnicodeReader.<init>(InputStream, Charset)"})
  public void testNewUnicodeReader_thenByteArrayInputStreamWithAxaxaxaxBytesIsUtf8ReadIsFour()
      throws IOException {
    // Arrange
    ByteArrayInputStream in = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));

    // Act
    UnicodeReader actualUnicodeReader =
        new UnicodeReader(in, Charset.forName(GeneralUtils.DEFAULT_ENCODING));

    // Assert
    assertEquals("UTF8", actualUnicodeReader.getEncoding());
    byte[] byteArray = new byte[4];
    assertEquals(4, in.read(byteArray));
    assertFalse(actualUnicodeReader.ready());
    assertArrayEquals("AXAX".getBytes("UTF-8"), byteArray);
  }

  /**
   * Test {@link UnicodeReader#UnicodeReader(InputStream, Charset)}.
   *
   * <ul>
   *   <li>Then {@link ByteArrayInputStream#ByteArrayInputStream(byte[])} with empty array of {@code
   *       byte} read is minus one.
   * </ul>
   *
   * <p>Method under test: {@link UnicodeReader#UnicodeReader(InputStream, Charset)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void UnicodeReader.<init>(InputStream, Charset)"})
  public void testNewUnicodeReader_thenByteArrayInputStreamWithEmptyArrayOfByteReadIsMinusOne()
      throws IOException {
    // Arrange
    ByteArrayInputStream in = new ByteArrayInputStream(new byte[] {});

    // Act
    UnicodeReader actualUnicodeReader =
        new UnicodeReader(in, Charset.forName(GeneralUtils.DEFAULT_ENCODING));

    // Assert
    assertEquals("UTF8", actualUnicodeReader.getEncoding());
    int actualReadResult = in.read(new byte[] {});
    assertEquals(-1, actualReadResult);
    assertFalse(actualUnicodeReader.ready());
  }

  /**
   * Test {@link UnicodeReader#UnicodeReader(InputStream, Charset)}.
   *
   * <ul>
   *   <li>Then return Encoding is {@code UnicodeLittleUnmarked}.
   * </ul>
   *
   * <p>Method under test: {@link UnicodeReader#UnicodeReader(InputStream, Charset)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void UnicodeReader.<init>(InputStream, Charset)"})
  public void testNewUnicodeReader_thenReturnEncodingIsUnicodeLittleUnmarked() throws IOException {
    // Arrange
    ByteArrayInputStream in =
        new ByteArrayInputStream(new byte[] {-1, -2, 'A', 'X', 'A', 'X', 'A', 'X'});

    // Act
    UnicodeReader actualUnicodeReader =
        new UnicodeReader(in, Charset.forName(GeneralUtils.DEFAULT_ENCODING));

    // Assert
    assertEquals("UnicodeLittleUnmarked", actualUnicodeReader.getEncoding());
    byte[] byteArray = new byte[4];
    assertEquals(4, in.read(byteArray));
    assertFalse(actualUnicodeReader.ready());
    assertArrayEquals("AXAX".getBytes("UTF-8"), byteArray);
  }

  /**
   * Test {@link UnicodeReader#UnicodeReader(InputStream, Charset)}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link UnicodeReader#UnicodeReader(InputStream, Charset)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void UnicodeReader.<init>(InputStream, Charset)"})
  public void testNewUnicodeReader_whenNull() throws IOException {
    // Arrange
    ByteArrayInputStream in = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));

    // Act
    UnicodeReader actualUnicodeReader = new UnicodeReader(in, null);

    // Assert
    assertEquals("UTF8", actualUnicodeReader.getEncoding());
    byte[] byteArray = new byte[4];
    assertEquals(4, in.read(byteArray));
    assertFalse(actualUnicodeReader.ready());
    assertArrayEquals("AXAX".getBytes("UTF-8"), byteArray);
  }

  /**
   * Test {@link UnicodeReader#getEncoding()}.
   *
   * <p>Method under test: {@link UnicodeReader#getEncoding()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String UnicodeReader.getEncoding()"})
  public void testGetEncoding() throws IOException {
    // Arrange
    ByteArrayInputStream in = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    UnicodeReader unicodeReader =
        new UnicodeReader(in, Charset.forName(GeneralUtils.DEFAULT_ENCODING));

    // Act and Assert
    assertEquals("UTF8", unicodeReader.getEncoding());
  }

  /**
   * Test {@link UnicodeReader#read(char[], int, int)} with {@code char[]}, {@code int}, {@code
   * int}.
   *
   * <ul>
   *   <li>Then return three.
   * </ul>
   *
   * <p>Method under test: {@link UnicodeReader#read(char[], int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int UnicodeReader.read(char[], int, int)"})
  public void testReadWithCharIntInt_thenReturnThree() throws IOException {
    // Arrange
    ByteArrayInputStream in = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    UnicodeReader unicodeReader =
        new UnicodeReader(in, Charset.forName(GeneralUtils.DEFAULT_ENCODING));
    char[] cbuf = "AZAZ".toCharArray();

    // Act and Assert
    assertEquals(3, unicodeReader.read(cbuf, 1, 3));
    assertArrayEquals("AAXA".toCharArray(), cbuf);
  }

  /**
   * Test {@link UnicodeReader#close()}.
   *
   * <p>Method under test: {@link UnicodeReader#close()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void UnicodeReader.close()"})
  public void testClose() throws IOException {
    // Arrange
    ByteArrayInputStream in = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    UnicodeReader unicodeReader =
        new UnicodeReader(in, Charset.forName(GeneralUtils.DEFAULT_ENCODING));

    // Act
    unicodeReader.close();

    // Assert
    assertNull(unicodeReader.getEncoding());
  }
}

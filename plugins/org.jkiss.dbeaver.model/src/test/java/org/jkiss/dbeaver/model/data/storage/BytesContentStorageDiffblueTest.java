package org.jkiss.dbeaver.model.data.storage;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BytesContentStorageDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link BytesContentStorage#BytesContentStorage(byte[], String)}
   *   <li>{@link BytesContentStorage#release()}
   *   <li>{@link BytesContentStorage#getCachedValue()}
   *   <li>{@link BytesContentStorage#getCharset()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BytesContentStorage.<init>(byte[], String)",
    "Object BytesContentStorage.getCachedValue()",
    "String BytesContentStorage.getCharset()",
    "void BytesContentStorage.release()"
  })
  public void testGettersAndSetters() throws UnsupportedEncodingException {
    // Arrange and Act
    BytesContentStorage actualBytesContentStorage =
        new BytesContentStorage("AXAXAXAX".getBytes("UTF-8"), "UTF-8");
    actualBytesContentStorage.release();
    Object actualCachedValue = actualBytesContentStorage.getCachedValue();

    // Assert
    assertEquals("UTF-8", actualBytesContentStorage.getCharset());
    assertNull(actualCachedValue);
  }

  /**
   * Test {@link BytesContentStorage#getContentStream()}.
   *
   * <p>Method under test: {@link BytesContentStorage#getContentStream()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"InputStream BytesContentStorage.getContentStream()"})
  public void testGetContentStream() throws IOException {
    // Arrange, Act and Assert
    byte[] byteArray = new byte[8];
    assertEquals(
        8,
        BytesContentStorage.createFromStream(
                new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), 3L, "UTF-8")
            .getContentStream()
            .read(byteArray));
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), byteArray);
  }

  /**
   * Test {@link BytesContentStorage#getContentLength()}.
   *
   * <p>Method under test: {@link BytesContentStorage#getContentLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long BytesContentStorage.getContentLength()"})
  public void testGetContentLength() throws IOException {
    // Arrange, Act and Assert
    assertEquals(
        8L,
        BytesContentStorage.createFromStream(
                new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), 3L, "UTF-8")
            .getContentLength());
  }

  /**
   * Test {@link BytesContentStorage#createFromStream(InputStream, long, String)}.
   *
   * <ul>
   *   <li>When {@code 2147483648}.
   *   <li>Then throw {@link IOException}.
   * </ul>
   *
   * <p>Method under test: {@link BytesContentStorage#createFromStream(InputStream, long, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "BytesContentStorage BytesContentStorage.createFromStream(InputStream, long, String)"
  })
  public void testCreateFromStream_when2147483648_thenThrowIOException() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IOException.class,
        () ->
            BytesContentStorage.createFromStream(
                new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), 2147483648L, "UTF-8"));
  }
}

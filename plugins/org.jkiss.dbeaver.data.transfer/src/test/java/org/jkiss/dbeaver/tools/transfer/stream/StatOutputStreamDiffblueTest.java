package org.jkiss.dbeaver.tools.transfer.stream;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class StatOutputStreamDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link StatOutputStream#StatOutputStream(OutputStream)}
   *   <li>{@link StatOutputStream#getBytesWritten()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StatOutputStream.<init>(OutputStream)",
    "long StatOutputStream.getBytesWritten()"
  })
  public void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals(0L, new StatOutputStream(new ByteArrayOutputStream()).getBytesWritten());
  }

  /**
   * Test {@link StatOutputStream#write(byte[])} with {@code byte[]}.
   *
   * <p>Method under test: {@link StatOutputStream#write(byte[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void StatOutputStream.write(byte[])"})
  public void testWriteWithByte() throws IOException {
    // Arrange
    StatOutputStream statOutputStream = new StatOutputStream(new ByteArrayOutputStream());

    // Act
    statOutputStream.write("AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertEquals(8L, statOutputStream.getBytesWritten());
  }

  /**
   * Test {@link StatOutputStream#write(byte[])} with {@code byte[]}.
   *
   * <p>Method under test: {@link StatOutputStream#write(byte[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void StatOutputStream.write(byte[])"})
  public void testWriteWithByte2() throws IOException {
    // Arrange
    StatOutputStream statOutputStream =
        new StatOutputStream(new StatOutputStream(new ByteArrayOutputStream()));

    // Act
    statOutputStream.write("AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertEquals(8L, statOutputStream.getBytesWritten());
  }

  /**
   * Test {@link StatOutputStream#write(byte[], int, int)} with {@code byte[]}, {@code int}, {@code
   * int}.
   *
   * <p>Method under test: {@link StatOutputStream#write(byte[], int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void StatOutputStream.write(byte[], int, int)"})
  public void testWriteWithByteIntInt() throws IOException {
    // Arrange
    StatOutputStream statOutputStream = new StatOutputStream(new ByteArrayOutputStream());

    // Act
    statOutputStream.write("AXAXAXAX".getBytes("UTF-8"), 1, 3);

    // Assert
    assertEquals(3L, statOutputStream.getBytesWritten());
  }

  /**
   * Test {@link StatOutputStream#write(byte[], int, int)} with {@code byte[]}, {@code int}, {@code
   * int}.
   *
   * <p>Method under test: {@link StatOutputStream#write(byte[], int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void StatOutputStream.write(byte[], int, int)"})
  public void testWriteWithByteIntInt2() throws IOException {
    // Arrange
    StatOutputStream statOutputStream =
        new StatOutputStream(new StatOutputStream(new ByteArrayOutputStream()));

    // Act
    statOutputStream.write("AXAXAXAX".getBytes("UTF-8"), 1, 3);

    // Assert
    assertEquals(3L, statOutputStream.getBytesWritten());
  }

  /**
   * Test {@link StatOutputStream#write(int)} with {@code int}.
   *
   * <p>Method under test: {@link StatOutputStream#write(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void StatOutputStream.write(int)"})
  public void testWriteWithInt() throws IOException {
    // Arrange
    StatOutputStream statOutputStream = new StatOutputStream(new ByteArrayOutputStream());

    // Act
    statOutputStream.write(19088743);

    // Assert
    assertEquals(1L, statOutputStream.getBytesWritten());
  }

  /**
   * Test {@link StatOutputStream#write(int)} with {@code int}.
   *
   * <p>Method under test: {@link StatOutputStream#write(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void StatOutputStream.write(int)"})
  public void testWriteWithInt2() throws IOException {
    // Arrange
    StatOutputStream statOutputStream =
        new StatOutputStream(new StatOutputStream(new ByteArrayOutputStream()));

    // Act
    statOutputStream.write(19088743);

    // Assert
    assertEquals(1L, statOutputStream.getBytesWritten());
  }
}

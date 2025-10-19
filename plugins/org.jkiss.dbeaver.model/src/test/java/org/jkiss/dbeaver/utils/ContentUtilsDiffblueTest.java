package org.jkiss.dbeaver.utils;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.app.DBPPlatform;
import org.jkiss.dbeaver.model.data.DBDContent;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.jkiss.dbeaver.model.impl.data.StringContent;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.DefaultProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.runtime.SubTaskProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class ContentUtilsDiffblueTest {
  /**
   * Test {@link ContentUtils#getLobFolder(DBRProgressMonitor, DBPPlatform)}.
   *
   * <ul>
   *   <li>Given {@link IOException#IOException()}.
   *   <li>Then throw {@link IOException}.
   * </ul>
   *
   * <p>Method under test: {@link ContentUtils#getLobFolder(DBRProgressMonitor, DBPPlatform)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Path ContentUtils.getLobFolder(DBRProgressMonitor, DBPPlatform)"})
  public void testGetLobFolder_givenIOException_thenThrowIOException() throws IOException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPPlatform application = mock(DBPPlatform.class);
    when(application.getTempFolder(Mockito.<DBRProgressMonitor>any(), Mockito.<String>any()))
        .thenThrow(new IOException());

    // Act and Assert
    assertThrows(IOException.class, () -> ContentUtils.getLobFolder(monitor, application));
    verify(application).getTempFolder(isA(DBRProgressMonitor.class), eq(".lob"));
  }

  /**
   * Test {@link ContentUtils#getLobFolder(DBRProgressMonitor, DBPPlatform)}.
   *
   * <ul>
   *   <li>Then return toFile Name is {@code test.txt}.
   * </ul>
   *
   * <p>Method under test: {@link ContentUtils#getLobFolder(DBRProgressMonitor, DBPPlatform)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Path ContentUtils.getLobFolder(DBRProgressMonitor, DBPPlatform)"})
  public void testGetLobFolder_thenReturnToFileNameIsTestTxt() throws IOException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPPlatform application = mock(DBPPlatform.class);
    when(application.getTempFolder(Mockito.<DBRProgressMonitor>any(), Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

    // Act
    Path actualLobFolder = ContentUtils.getLobFolder(monitor, application);

    // Assert
    verify(application).getTempFolder(isA(DBRProgressMonitor.class), eq(".lob"));
    File toFileResult = actualLobFolder.toFile();
    assertEquals("test.txt", toFileResult.getName());
    assertTrue(toFileResult.isAbsolute());
  }

  /**
   * Test {@link ContentUtils#createTempContentFile(DBRProgressMonitor, DBPPlatform, String)}.
   *
   * <p>Method under test: {@link ContentUtils#createTempContentFile(DBRProgressMonitor,
   * DBPPlatform, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Path ContentUtils.createTempContentFile(DBRProgressMonitor, DBPPlatform, String)"
  })
  public void testCreateTempContentFile() throws IOException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPPlatform application = mock(DBPPlatform.class);
    when(application.getTempFolder(Mockito.<DBRProgressMonitor>any(), Mockito.<String>any()))
        .thenThrow(new IOException());

    // Act and Assert
    assertThrows(
        IOException.class,
        () -> ContentUtils.createTempContentFile(monitor, application, "foo.txt"));
    verify(application).getTempFolder(isA(DBRProgressMonitor.class), eq(".lob"));
  }

  /**
   * Test {@link ContentUtils#copyStreams(InputStream, long, OutputStream, DBRProgressMonitor)} with
   * {@code inputStream}, {@code contentLength}, {@code outputStream}, {@code monitor}.
   *
   * <p>Method under test: {@link ContentUtils#copyStreams(InputStream, long, OutputStream,
   * DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ContentUtils.copyStreams(InputStream, long, OutputStream, DBRProgressMonitor)"
  })
  public void testCopyStreamsWithInputStreamContentLengthOutputStreamMonitor() throws IOException {
    // Arrange
    ByteArrayInputStream inputStream = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    DBRProgressMonitor monitor = mock(DBRProgressMonitor.class);
    when(monitor.isCanceled()).thenReturn(true);
    doNothing().when(monitor).beginTask(Mockito.<String>any(), anyInt());
    doNothing().when(monitor).done();

    // Act
    ContentUtils.copyStreams(inputStream, 3L, outputStream, monitor);

    // Assert that nothing has changed
    verify(monitor).beginTask("Copy binary content", 3);
    verify(monitor).done();
    verify(monitor).isCanceled();
    byte[] byteArray = new byte[8];
    assertEquals(8, inputStream.read(byteArray));
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), byteArray);
    assertArrayEquals(new byte[] {}, outputStream.toByteArray());
  }

  /**
   * Test {@link ContentUtils#copyStreams(InputStream, long, OutputStream, DBRProgressMonitor)} with
   * {@code inputStream}, {@code contentLength}, {@code outputStream}, {@code monitor}.
   *
   * <ul>
   *   <li>Then calls {@link DBRProgressMonitor#subTask(String)}.
   * </ul>
   *
   * <p>Method under test: {@link ContentUtils#copyStreams(InputStream, long, OutputStream,
   * DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ContentUtils.copyStreams(InputStream, long, OutputStream, DBRProgressMonitor)"
  })
  public void testCopyStreamsWithInputStreamContentLengthOutputStreamMonitor_thenCallsSubTask()
      throws IOException {
    // Arrange
    ByteArrayInputStream inputStream = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    DBRProgressMonitor monitor = mock(DBRProgressMonitor.class);
    when(monitor.isCanceled()).thenReturn(false);
    doNothing().when(monitor).beginTask(Mockito.<String>any(), anyInt());
    doNothing().when(monitor).done();
    doNothing().when(monitor).subTask(Mockito.<String>any());
    doNothing().when(monitor).worked(anyInt());

    // Act
    ContentUtils.copyStreams(inputStream, 3L, outputStream, monitor);

    // Assert
    verify(monitor).beginTask("Copy binary content", 3);
    verify(monitor).done();
    verify(monitor, atLeast(1)).isCanceled();
    verify(monitor).subTask("8 / 3");
    verify(monitor).worked(10000);
    int actualReadResult = inputStream.read(new byte[] {});
    assertEquals(-1, actualReadResult);
    byte[] expectedToByteArrayResult = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedToByteArrayResult, outputStream.toByteArray());
  }

  /**
   * Test {@link ContentUtils#copyStreams(InputStream, long, OutputStream, DBRProgressMonitor)} with
   * {@code inputStream}, {@code contentLength}, {@code outputStream}, {@code monitor}.
   *
   * <ul>
   *   <li>When minus one.
   * </ul>
   *
   * <p>Method under test: {@link ContentUtils#copyStreams(InputStream, long, OutputStream,
   * DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ContentUtils.copyStreams(InputStream, long, OutputStream, DBRProgressMonitor)"
  })
  public void testCopyStreamsWithInputStreamContentLengthOutputStreamMonitor_whenMinusOne()
      throws IOException {
    // Arrange
    ByteArrayInputStream inputStream = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    DBRProgressMonitor monitor = mock(DBRProgressMonitor.class);
    when(monitor.isCanceled()).thenReturn(false);
    doNothing().when(monitor).beginTask(Mockito.<String>any(), anyInt());
    doNothing().when(monitor).done();
    doNothing().when(monitor).worked(anyInt());

    // Act
    ContentUtils.copyStreams(inputStream, -1L, outputStream, monitor);

    // Assert
    verify(monitor).beginTask("Copy binary content", 10000);
    verify(monitor).done();
    verify(monitor, atLeast(1)).isCanceled();
    verify(monitor).worked(10000);
    int actualReadResult = inputStream.read(new byte[] {});
    assertEquals(-1, actualReadResult);
    byte[] expectedToByteArrayResult = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedToByteArrayResult, outputStream.toByteArray());
  }

  /**
   * Test {@link ContentUtils#copyStreams(InputStream, long, OutputStream, DBRProgressMonitor)} with
   * {@code inputStream}, {@code contentLength}, {@code outputStream}, {@code monitor}.
   *
   * <ul>
   *   <li>When zero.
   * </ul>
   *
   * <p>Method under test: {@link ContentUtils#copyStreams(InputStream, long, OutputStream,
   * DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ContentUtils.copyStreams(InputStream, long, OutputStream, DBRProgressMonitor)"
  })
  public void testCopyStreamsWithInputStreamContentLengthOutputStreamMonitor_whenZero()
      throws IOException {
    // Arrange
    ByteArrayInputStream inputStream = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    DBRProgressMonitor monitor = mock(DBRProgressMonitor.class);
    when(monitor.isCanceled()).thenReturn(false);
    doNothing().when(monitor).beginTask(Mockito.<String>any(), anyInt());
    doNothing().when(monitor).done();
    doNothing().when(monitor).worked(anyInt());

    // Act
    ContentUtils.copyStreams(inputStream, 0L, outputStream, monitor);

    // Assert
    verify(monitor).beginTask("Copy binary content", 0);
    verify(monitor).done();
    verify(monitor, atLeast(1)).isCanceled();
    verify(monitor).worked(10000);
    int actualReadResult = inputStream.read(new byte[] {});
    assertEquals(-1, actualReadResult);
    byte[] expectedToByteArrayResult = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedToByteArrayResult, outputStream.toByteArray());
  }

  /**
   * Test {@link ContentUtils#copyStreams(Reader, long, Writer, DBRProgressMonitor)} with {@code
   * reader}, {@code contentLength}, {@code writer}, {@code monitor}.
   *
   * <p>Method under test: {@link ContentUtils#copyStreams(Reader, long, Writer,
   * DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ContentUtils.copyStreams(Reader, long, Writer, DBRProgressMonitor)"})
  public void testCopyStreamsWithReaderContentLengthWriterMonitor() throws IOException {
    // Arrange
    StringReader reader = new StringReader("foo");
    StringWriter writer = new StringWriter();

    DBRProgressMonitor monitor = mock(DBRProgressMonitor.class);
    when(monitor.isCanceled()).thenReturn(false);
    doNothing().when(monitor).beginTask(Mockito.<String>any(), anyInt());
    doNothing().when(monitor).done();
    doNothing().when(monitor).worked(anyInt());

    // Act
    ContentUtils.copyStreams(reader, 3L, writer, monitor);

    // Assert
    verify(monitor).beginTask("Copy character content", 3);
    verify(monitor).done();
    verify(monitor, atLeast(1)).isCanceled();
    verify(monitor).worked(10000);
    assertEquals("foo", writer.toString());
  }

  /**
   * Test {@link ContentUtils#copyStreams(Reader, long, Writer, DBRProgressMonitor)} with {@code
   * reader}, {@code contentLength}, {@code writer}, {@code monitor}.
   *
   * <p>Method under test: {@link ContentUtils#copyStreams(Reader, long, Writer,
   * DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ContentUtils.copyStreams(Reader, long, Writer, DBRProgressMonitor)"})
  public void testCopyStreamsWithReaderContentLengthWriterMonitor2() throws IOException {
    // Arrange
    StringReader reader = new StringReader("foo");
    StringWriter writer = new StringWriter();

    DBRProgressMonitor monitor = mock(DBRProgressMonitor.class);
    when(monitor.isCanceled()).thenReturn(true);
    doNothing().when(monitor).beginTask(Mockito.<String>any(), anyInt());
    doNothing().when(monitor).done();

    // Act
    ContentUtils.copyStreams(reader, 3L, writer, monitor);

    // Assert that nothing has changed
    verify(monitor).beginTask("Copy character content", 3);
    verify(monitor).done();
    verify(monitor).isCanceled();
    assertEquals("", writer.toString());
  }

  /**
   * Test {@link ContentUtils#copyStreams(Reader, long, Writer, DBRProgressMonitor)} with {@code
   * reader}, {@code contentLength}, {@code writer}, {@code monitor}.
   *
   * <p>Method under test: {@link ContentUtils#copyStreams(Reader, long, Writer,
   * DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ContentUtils.copyStreams(Reader, long, Writer, DBRProgressMonitor)"})
  public void testCopyStreamsWithReaderContentLengthWriterMonitor3() throws IOException {
    // Arrange
    StringReader reader = new StringReader("foo");
    StringWriter writer = new StringWriter();

    DBRProgressMonitor monitor = mock(DBRProgressMonitor.class);
    when(monitor.isCanceled()).thenReturn(false);
    doNothing().when(monitor).beginTask(Mockito.<String>any(), anyInt());
    doNothing().when(monitor).done();
    doNothing().when(monitor).worked(anyInt());

    // Act
    ContentUtils.copyStreams(reader, -1L, writer, monitor);

    // Assert
    verify(monitor).beginTask("Copy character content", 10000);
    verify(monitor).done();
    verify(monitor, atLeast(1)).isCanceled();
    verify(monitor).worked(10000);
    assertEquals("foo", writer.toString());
  }

  /**
   * Test {@link ContentUtils#calculateContentLength(Reader)} with {@code reader}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then return three.
   * </ul>
   *
   * <p>Method under test: {@link ContentUtils#calculateContentLength(Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long ContentUtils.calculateContentLength(Reader)"})
  public void testCalculateContentLengthWithReader_whenStringReaderWithFoo_thenReturnThree()
      throws IOException {
    // Arrange, Act and Assert
    assertEquals(3L, ContentUtils.calculateContentLength(new StringReader("foo")));
  }

  /**
   * Test {@link ContentUtils#calculateContentLength(InputStream, String)} with {@code stream},
   * {@code charset}.
   *
   * <p>Method under test: {@link ContentUtils#calculateContentLength(InputStream, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long ContentUtils.calculateContentLength(InputStream, String)"})
  public void testCalculateContentLengthWithStreamCharset() throws IOException {
    // Arrange
    ByteArrayInputStream stream = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));

    // Act
    long actualCalculateContentLengthResult =
        ContentUtils.calculateContentLength(stream, GeneralUtils.DEFAULT_ENCODING);

    // Assert
    int actualReadResult = stream.read(new byte[] {});
    assertEquals(-1, actualReadResult);
    assertEquals(8L, actualCalculateContentLengthResult);
  }

  /**
   * Test {@link ContentUtils#calculateContentLength(InputStream, String)} with {@code stream},
   * {@code charset}.
   *
   * <ul>
   *   <li>Then throw {@link IOException}.
   * </ul>
   *
   * <p>Method under test: {@link ContentUtils#calculateContentLength(InputStream, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long ContentUtils.calculateContentLength(InputStream, String)"})
  public void testCalculateContentLengthWithStreamCharset_thenThrowIOException()
      throws IOException {
    // Arrange
    DataInputStream stream = mock(DataInputStream.class);
    when(stream.read(Mockito.<byte[]>any(), anyInt(), anyInt())).thenThrow(new IOException());
    doThrow(new IOException()).when(stream).close();

    // Act and Assert
    assertThrows(
        IOException.class,
        () -> ContentUtils.calculateContentLength(stream, GeneralUtils.DEFAULT_ENCODING));
    verify(stream).read(isA(byte[].class), eq(0), eq(8192));
    verify(stream).close();
  }

  /**
   * Test {@link ContentUtils#close(Closeable)}.
   *
   * <ul>
   *   <li>Given {@link IOException#IOException()}.
   *   <li>When {@link Closeable} {@link Closeable#close()} throw {@link IOException#IOException()}.
   *   <li>Then calls {@link Closeable#close()}.
   * </ul>
   *
   * <p>Method under test: {@link ContentUtils#close(Closeable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ContentUtils.close(Closeable)"})
  public void testClose_givenIOException_whenCloseableCloseThrowIOException_thenCallsClose()
      throws IOException {
    // Arrange
    Closeable closeable = mock(Closeable.class);
    doThrow(new IOException()).when(closeable).close();

    // Act
    ContentUtils.close(closeable);

    // Assert
    verify(closeable).close();
  }

  /**
   * Test {@link ContentUtils#close(Closeable)}.
   *
   * <ul>
   *   <li>When {@link Closeable} {@link Closeable#close()} does nothing.
   *   <li>Then calls {@link Closeable#close()}.
   * </ul>
   *
   * <p>Method under test: {@link ContentUtils#close(Closeable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ContentUtils.close(Closeable)"})
  public void testClose_whenCloseableCloseDoesNothing_thenCallsClose() throws IOException {
    // Arrange
    Closeable closeable = mock(Closeable.class);
    doNothing().when(closeable).close();

    // Act
    ContentUtils.close(closeable);

    // Assert
    verify(closeable).close();
  }

  /**
   * Test {@link ContentUtils#readToString(InputStream, Charset)}.
   *
   * <p>Method under test: {@link ContentUtils#readToString(InputStream, Charset)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String ContentUtils.readToString(InputStream, Charset)"})
  public void testReadToString() throws IOException {
    // Arrange
    ByteArrayInputStream is =
        new ByteArrayInputStream(new byte[] {-17, 'X', 'A', 'X', 'A', 'X', 'A', 'X'});

    // Act and Assert
    assertEquals(
        "�XAXAXAX", ContentUtils.readToString(is, Charset.forName(GeneralUtils.DEFAULT_ENCODING)));
    int actualReadResult = is.read(new byte[] {});
    assertEquals(-1, actualReadResult);
  }

  /**
   * Test {@link ContentUtils#readToString(InputStream, Charset)}.
   *
   * <p>Method under test: {@link ContentUtils#readToString(InputStream, Charset)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String ContentUtils.readToString(InputStream, Charset)"})
  public void testReadToString2() throws IOException {
    // Arrange
    ByteArrayInputStream is =
        new ByteArrayInputStream(new byte[] {-2, 'X', 'A', 'X', 'A', 'X', 'A', 'X'});

    // Act and Assert
    assertEquals(
        "�XAXAXAX", ContentUtils.readToString(is, Charset.forName(GeneralUtils.DEFAULT_ENCODING)));
    int actualReadResult = is.read(new byte[] {});
    assertEquals(-1, actualReadResult);
  }

  /**
   * Test {@link ContentUtils#readToString(InputStream, Charset)}.
   *
   * <p>Method under test: {@link ContentUtils#readToString(InputStream, Charset)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String ContentUtils.readToString(InputStream, Charset)"})
  public void testReadToString3() throws IOException {
    // Arrange
    ByteArrayInputStream is =
        new ByteArrayInputStream(new byte[] {-1, 'X', 'A', 'X', 'A', 'X', 'A', 'X'});

    // Act and Assert
    assertEquals(
        "�XAXAXAX", ContentUtils.readToString(is, Charset.forName(GeneralUtils.DEFAULT_ENCODING)));
    int actualReadResult = is.read(new byte[] {});
    assertEquals(-1, actualReadResult);
  }

  /**
   * Test {@link ContentUtils#readToString(InputStream, Charset)}.
   *
   * <ul>
   *   <li>Given {@link IOException#IOException()}.
   *   <li>Then throw {@link IOException}.
   * </ul>
   *
   * <p>Method under test: {@link ContentUtils#readToString(InputStream, Charset)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String ContentUtils.readToString(InputStream, Charset)"})
  public void testReadToString_givenIOException_thenThrowIOException() throws IOException {
    // Arrange
    DataInputStream is = mock(DataInputStream.class);
    when(is.read(Mockito.<byte[]>any(), anyInt(), anyInt())).thenThrow(new IOException());

    // Act and Assert
    assertThrows(
        IOException.class,
        () -> ContentUtils.readToString(is, Charset.forName(GeneralUtils.DEFAULT_ENCODING)));
    verify(is).read(isA(byte[].class), eq(0), eq(4));
  }

  /**
   * Test {@link ContentUtils#readToString(InputStream, Charset)}.
   *
   * <ul>
   *   <li>Then return {@code AXAXAXAX}.
   * </ul>
   *
   * <p>Method under test: {@link ContentUtils#readToString(InputStream, Charset)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String ContentUtils.readToString(InputStream, Charset)"})
  public void testReadToString_thenReturnAxaxaxax() throws IOException {
    // Arrange
    ByteArrayInputStream is = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(
        "AXAXAXAX", ContentUtils.readToString(is, Charset.forName(GeneralUtils.DEFAULT_ENCODING)));
    int actualReadResult = is.read(new byte[] {});
    assertEquals(-1, actualReadResult);
  }

  /**
   * Test {@link ContentUtils#readToString(InputStream, Charset)}.
   *
   * <ul>
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link ContentUtils#readToString(InputStream, Charset)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String ContentUtils.readToString(InputStream, Charset)"})
  public void testReadToString_thenReturnEmptyString() throws IOException {
    // Arrange
    ByteArrayInputStream is = new ByteArrayInputStream(new byte[] {});

    // Act and Assert
    assertEquals("", ContentUtils.readToString(is, Charset.forName(GeneralUtils.DEFAULT_ENCODING)));
    int actualReadResult = is.read(new byte[] {});
    assertEquals(-1, actualReadResult);
  }

  /**
   * Test {@link ContentUtils#readToString(InputStream, Charset)}.
   *
   * <ul>
   *   <li>Then return {@code XAXAXAX}.
   * </ul>
   *
   * <p>Method under test: {@link ContentUtils#readToString(InputStream, Charset)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String ContentUtils.readToString(InputStream, Charset)"})
  public void testReadToString_thenReturnXaxaxax() throws IOException {
    // Arrange
    ByteArrayInputStream is =
        new ByteArrayInputStream(new byte[] {0, 'X', 'A', 'X', 'A', 'X', 'A', 'X'});

    // Act and Assert
    assertEquals(
        "\u0000XAXAXAX",
        ContentUtils.readToString(is, Charset.forName(GeneralUtils.DEFAULT_ENCODING)));
    int actualReadResult = is.read(new byte[] {});
    assertEquals(-1, actualReadResult);
  }

  /**
   * Test {@link ContentUtils#readToString(InputStream, Charset)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code AXAXAXAX}.
   * </ul>
   *
   * <p>Method under test: {@link ContentUtils#readToString(InputStream, Charset)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String ContentUtils.readToString(InputStream, Charset)"})
  public void testReadToString_whenNull_thenReturnAxaxaxax() throws IOException {
    // Arrange
    ByteArrayInputStream is = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals("AXAXAXAX", ContentUtils.readToString(is, null));
    int actualReadResult = is.read(new byte[] {});
    assertEquals(-1, actualReadResult);
  }

  /**
   * Test {@link ContentUtils#isTextContent(DBDContent)}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ContentUtils#isTextContent(DBDContent)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ContentUtils.isTextContent(DBDContent)"})
  public void testIsTextContent_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(
        ContentUtils.isTextContent(new StringContent(mock(DBCExecutionContext.class), "Data")));
  }

  /**
   * Test {@link ContentUtils#isTextContent(DBDContent)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ContentUtils#isTextContent(DBDContent)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ContentUtils.isTextContent(DBDContent)"})
  public void testIsTextContent_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(ContentUtils.isTextContent(null));
  }

  /**
   * Test {@link ContentUtils#isTextMime(String)}.
   *
   * <ul>
   *   <li>When {@code application/xml}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ContentUtils#isTextMime(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ContentUtils.isTextMime(String)"})
  public void testIsTextMime_whenApplicationXml_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(ContentUtils.isTextMime("application/xml"));
  }

  /**
   * Test {@link ContentUtils#isTextMime(String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ContentUtils#isTextMime(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ContentUtils.isTextMime(String)"})
  public void testIsTextMime_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(ContentUtils.isTextMime(null));
  }

  /**
   * Test {@link ContentUtils#isTextMime(String)}.
   *
   * <ul>
   *   <li>When {@link MimeTypes#TEXT_PLAIN}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ContentUtils#isTextMime(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ContentUtils.isTextMime(String)"})
  public void testIsTextMime_whenText_plain_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(ContentUtils.isTextMime(MimeTypes.TEXT_PLAIN));
  }

  /**
   * Test {@link ContentUtils#isTextValue(Object)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ContentUtils#isTextValue(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ContentUtils.isTextValue(Object)"})
  public void testIsTextValue_when42_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(ContentUtils.isTextValue("42"));
  }

  /**
   * Test {@link ContentUtils#isTextValue(Object)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ContentUtils#isTextValue(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ContentUtils.isTextValue(Object)"})
  public void testIsTextValue_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(ContentUtils.isTextValue(null));
  }

  /**
   * Test {@link ContentUtils#isTextValue(Object)}.
   *
   * <ul>
   *   <li>When {@link DBPEvent#RENAME}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ContentUtils#isTextValue(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ContentUtils.isTextValue(Object)"})
  public void testIsTextValue_whenRename_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(ContentUtils.isTextValue(DBPEvent.RENAME));
  }

  /**
   * Test {@link ContentUtils#isAsciiText(byte[])}.
   *
   * <p>Method under test: {@link ContentUtils#isAsciiText(byte[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ContentUtils.isAsciiText(byte[])"})
  public void testIsAsciiText() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertFalse(ContentUtils.isAsciiText("A\bA\bA\bA\bA\bA\bA\bA\b".getBytes("UTF-8")));
  }

  /**
   * Test {@link ContentUtils#isAsciiText(byte[])}.
   *
   * <ul>
   *   <li>When {@code A}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ContentUtils#isAsciiText(byte[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ContentUtils.isAsciiText(byte[])"})
  public void testIsAsciiText_whenA_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(
        ContentUtils.isAsciiText(new byte[] {Byte.MAX_VALUE, 'X', 'A', 'X', 'A', 'X', 'A', 'X'}));
  }

  /**
   * Test {@link ContentUtils#isAsciiText(byte[])}.
   *
   * <ul>
   *   <li>When {@code AXAXAXAX} Bytes is {@code UTF-8}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ContentUtils#isAsciiText(byte[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ContentUtils.isAsciiText(byte[])"})
  public void testIsAsciiText_whenAxaxaxaxBytesIsUtf8_thenReturnTrue()
      throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertTrue(ContentUtils.isAsciiText("AXAXAXAX".getBytes("UTF-8")));
  }

  /**
   * Test {@link ContentUtils#isAsciiText(byte[])}.
   *
   * <ul>
   *   <li>When empty array of {@code byte}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ContentUtils#isAsciiText(byte[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ContentUtils.isAsciiText(byte[])"})
  public void testIsAsciiText_whenEmptyArrayOfByte_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(ContentUtils.isAsciiText(new byte[] {}));
  }

  /**
   * Test {@link ContentUtils#isAsciiText(byte[])}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ContentUtils#isAsciiText(byte[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ContentUtils.isAsciiText(byte[])"})
  public void testIsAsciiText_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(ContentUtils.isAsciiText(null));
  }

  /**
   * Test {@link ContentUtils#isXML(DBDContent)}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ContentUtils#isXML(DBDContent)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ContentUtils.isXML(DBDContent)"})
  public void testIsXML_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(ContentUtils.isXML(new StringContent(mock(DBCExecutionContext.class), "Data")));
  }

  /**
   * Test {@link ContentUtils#isJSON(DBDContent)}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ContentUtils#isJSON(DBDContent)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ContentUtils.isJSON(DBDContent)"})
  public void testIsJSON_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(ContentUtils.isJSON(new StringContent(mock(DBCExecutionContext.class), "Data")));
  }

  /**
   * Test {@link ContentUtils#getContentStringValue(DBRProgressMonitor, DBDContent)}.
   *
   * <ul>
   *   <li>Then return {@code Data}.
   * </ul>
   *
   * <p>Method under test: {@link ContentUtils#getContentStringValue(DBRProgressMonitor,
   * DBDContent)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String ContentUtils.getContentStringValue(DBRProgressMonitor, DBDContent)"})
  public void testGetContentStringValue_thenReturnData() throws DBCException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act
    String actualContentStringValue =
        ContentUtils.getContentStringValue(
            monitor, new StringContent(mock(DBCExecutionContext.class), "Data"));

    // Assert
    assertEquals("Data", actualContentStringValue);
  }

  /**
   * Test {@link ContentUtils#getContentStringValue(DBRProgressMonitor, DBDContent)}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ContentUtils#getContentStringValue(DBRProgressMonitor,
   * DBDContent)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String ContentUtils.getContentStringValue(DBRProgressMonitor, DBDContent)"})
  public void testGetContentStringValue_thenReturnNull() throws DBCException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    StringContent object = new StringContent(mock(DBCExecutionContext.class), null);

    // Act
    String actualContentStringValue = ContentUtils.getContentStringValue(monitor, object);

    // Assert
    assertNull(actualContentStringValue);
  }

  /**
   * Test {@link ContentUtils#getContentBinaryValue(DBRProgressMonitor, DBDContent)}.
   *
   * <ul>
   *   <li>Given {@code true}.
   *   <li>Then return empty array of {@code byte}.
   * </ul>
   *
   * <p>Method under test: {@link ContentUtils#getContentBinaryValue(DBRProgressMonitor,
   * DBDContent)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] ContentUtils.getContentBinaryValue(DBRProgressMonitor, DBDContent)"})
  public void testGetContentBinaryValue_givenTrue_thenReturnEmptyArrayOfByte() throws DBCException {
    // Arrange
    NullProgressMonitor nestedMonitor = new NullProgressMonitor();
    nestedMonitor.setCanceled(true);
    SubTaskProgressMonitor monitor =
        new SubTaskProgressMonitor(new DefaultProgressMonitor(nestedMonitor));

    // Act
    byte[] actualContentBinaryValue =
        ContentUtils.getContentBinaryValue(monitor, new StringContent(null, "Data"));

    // Assert
    assertArrayEquals(new byte[] {}, actualContentBinaryValue);
  }

  /**
   * Test {@link ContentUtils#deleteTempFile(Path)} with {@code Path}.
   *
   * <ul>
   *   <li>Given {@link Files} {@link Files#delete(Path)} does nothing.
   *   <li>Then calls {@link Files#delete(Path)}.
   * </ul>
   *
   * <p>Method under test: {@link ContentUtils#deleteTempFile(Path)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ContentUtils.deleteTempFile(Path)"})
  public void testDeleteTempFileWithPath_givenFilesDeleteDoesNothing_thenCallsDelete()
      throws IOException {
    // Arrange
    try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {
      mockFiles.when(() -> Files.delete(Mockito.<Path>any())).thenAnswer(invocation -> null);

      // Act
      ContentUtils.deleteTempFile(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

      // Assert
      mockFiles.verify(() -> Files.delete(Mockito.<Path>any()));
    }
  }

  /**
   * Test {@link ContentUtils#deleteTempFile(Path)} with {@code Path}.
   *
   * <ul>
   *   <li>Given {@link Files} {@link Files#delete(Path)} throw {@link IOException#IOException()}.
   *   <li>Then calls {@link Files#delete(Path)}.
   * </ul>
   *
   * <p>Method under test: {@link ContentUtils#deleteTempFile(Path)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ContentUtils.deleteTempFile(Path)"})
  public void testDeleteTempFileWithPath_givenFilesDeleteThrowIOException_thenCallsDelete()
      throws IOException {
    // Arrange
    try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {
      mockFiles.when(() -> Files.delete(Mockito.<Path>any())).thenThrow(new IOException());

      // Act
      ContentUtils.deleteTempFile(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

      // Assert
      mockFiles.verify(() -> Files.delete(Mockito.<Path>any()));
    }
  }

  /**
   * Test {@link ContentUtils#deleteFileRecursive(Path)} with {@code Path}.
   *
   * <ul>
   *   <li>Given {@link Files} {@link Files#isDirectory(Path, LinkOption[])} return {@code false}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ContentUtils#deleteFileRecursive(Path)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ContentUtils.deleteFileRecursive(Path)"})
  public void testDeleteFileRecursiveWithPath_givenFilesIsDirectoryReturnFalse_thenReturnTrue()
      throws IOException {
    // Arrange
    try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {

      ArrayList<Path> pathList = new ArrayList<>();
      Stream<Path> streamResult = pathList.stream();
      mockFiles.when(() -> Files.list(Mockito.<Path>any())).thenReturn(streamResult);
      mockFiles.when(() -> Files.deleteIfExists(Mockito.<Path>any())).thenReturn(true);
      mockFiles
          .when(() -> Files.isDirectory(Mockito.<Path>any(), isA(LinkOption[].class)))
          .thenReturn(false);

      // Act
      boolean actualDeleteFileRecursiveResult =
          ContentUtils.deleteFileRecursive(
              Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

      // Assert
      mockFiles.verify(() -> Files.deleteIfExists(Mockito.<Path>any()));
      mockFiles.verify(() -> Files.isDirectory(Mockito.<Path>any(), isA(LinkOption[].class)));
      assertTrue(actualDeleteFileRecursiveResult);
    }
  }

  /**
   * Test {@link ContentUtils#deleteFileRecursive(Path)} with {@code Path}.
   *
   * <ul>
   *   <li>Given {@link Files} {@link Files#list(Path)} throw {@link IOException#IOException()}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ContentUtils#deleteFileRecursive(Path)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ContentUtils.deleteFileRecursive(Path)"})
  public void testDeleteFileRecursiveWithPath_givenFilesListThrowIOException_thenReturnTrue()
      throws IOException {
    // Arrange
    try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {
      mockFiles.when(() -> Files.list(Mockito.<Path>any())).thenThrow(new IOException());
      mockFiles.when(() -> Files.deleteIfExists(Mockito.<Path>any())).thenReturn(true);
      mockFiles
          .when(() -> Files.isDirectory(Mockito.<Path>any(), isA(LinkOption[].class)))
          .thenReturn(true);

      // Act
      boolean actualDeleteFileRecursiveResult =
          ContentUtils.deleteFileRecursive(
              Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

      // Assert
      mockFiles.verify(() -> Files.deleteIfExists(Mockito.<Path>any()));
      mockFiles.verify(() -> Files.isDirectory(Mockito.<Path>any(), isA(LinkOption[].class)));
      mockFiles.verify(() -> Files.list(Mockito.<Path>any()));
      assertTrue(actualDeleteFileRecursiveResult);
    }
  }

  /**
   * Test {@link ContentUtils#deleteFileRecursive(Path)} with {@code Path}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ContentUtils#deleteFileRecursive(Path)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ContentUtils.deleteFileRecursive(Path)"})
  public void testDeleteFileRecursiveWithPath_thenReturnFalse() throws IOException {
    // Arrange
    try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {

      ArrayList<Path> pathList = new ArrayList<>();
      Stream<Path> streamResult = pathList.stream();
      mockFiles.when(() -> Files.list(Mockito.<Path>any())).thenReturn(streamResult);
      mockFiles.when(() -> Files.deleteIfExists(Mockito.<Path>any())).thenThrow(new IOException());
      mockFiles
          .when(() -> Files.isDirectory(Mockito.<Path>any(), isA(LinkOption[].class)))
          .thenReturn(true);

      // Act
      boolean actualDeleteFileRecursiveResult =
          ContentUtils.deleteFileRecursive(
              Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

      // Assert
      mockFiles.verify(() -> Files.deleteIfExists(Mockito.<Path>any()));
      mockFiles.verify(() -> Files.isDirectory(Mockito.<Path>any(), isA(LinkOption[].class)));
      mockFiles.verify(() -> Files.list(Mockito.<Path>any()));
      assertFalse(actualDeleteFileRecursiveResult);
    }
  }

  /**
   * Test {@link ContentUtils#deleteFileRecursive(Path)} with {@code Path}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ContentUtils#deleteFileRecursive(Path)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ContentUtils.deleteFileRecursive(Path)"})
  public void testDeleteFileRecursiveWithPath_thenReturnTrue() throws IOException {
    // Arrange
    try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {

      ArrayList<Path> pathList = new ArrayList<>();
      Stream<Path> streamResult = pathList.stream();
      mockFiles.when(() -> Files.list(Mockito.<Path>any())).thenReturn(streamResult);
      mockFiles.when(() -> Files.deleteIfExists(Mockito.<Path>any())).thenReturn(true);
      mockFiles
          .when(() -> Files.isDirectory(Mockito.<Path>any(), isA(LinkOption[].class)))
          .thenReturn(true);

      // Act
      boolean actualDeleteFileRecursiveResult =
          ContentUtils.deleteFileRecursive(
              Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

      // Assert
      mockFiles.verify(() -> Files.deleteIfExists(Mockito.<Path>any()));
      mockFiles.verify(() -> Files.isDirectory(Mockito.<Path>any(), isA(LinkOption[].class)));
      mockFiles.verify(() -> Files.list(Mockito.<Path>any()));
      assertTrue(actualDeleteFileRecursiveResult);
    }
  }
}

package org.jkiss.dbeaver.tools.sql.task;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.io.PipedWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.bouncycastle.openssl.PEMWriter;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.exec.DBCResultSet;
import org.jkiss.dbeaver.model.exec.DBCSession;
import org.jkiss.dbeaver.model.impl.local.LocalStatement;
import org.jkiss.dbeaver.tools.transfer.stream.StreamEntityMapping;
import org.jkiss.dbeaver.tools.transfer.stream.StreamTransferResultSet;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLScriptDataReceiverDiffblueTest {
  /**
   * Test {@link SQLScriptDataReceiver#fetchStart(DBCSession, DBCResultSet, long, long)}.
   *
   * <ul>
   *   <li>Given {@link SQLScriptDataReceiver} (default constructor).
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptDataReceiver#fetchStart(DBCSession, DBCResultSet, long,
   * long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLScriptDataReceiver.fetchStart(DBCSession, DBCResultSet, long, long)"})
  public void testFetchStart_givenSQLScriptDataReceiver_thenDoesNotThrow() throws DBCException {
    // Arrange
    SQLScriptDataReceiver sqlScriptDataReceiver = new SQLScriptDataReceiver();
    LocalStatement statement = new LocalStatement(null, "Text");
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamTransferResultSet resultSet =
        new StreamTransferResultSet(null, statement, new StreamEntityMapping(inputFile));

    // Act and Assert
    sqlScriptDataReceiver.fetchStart(null, resultSet, 1L, 1L);
  }

  /**
   * Test {@link SQLScriptDataReceiver#fetchStart(DBCSession, DBCResultSet, long, long)}.
   *
   * <ul>
   *   <li>Given {@link SQLScriptDataReceiver} (default constructor).
   *   <li>When {@code null}.
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptDataReceiver#fetchStart(DBCSession, DBCResultSet, long,
   * long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLScriptDataReceiver.fetchStart(DBCSession, DBCResultSet, long, long)"})
  public void testFetchStart_givenSQLScriptDataReceiver_whenNull_thenDoesNotThrow()
      throws DBCException {
    // Arrange, Act and Assert
    new SQLScriptDataReceiver().fetchStart(null, null, 1L, 1L);
  }

  /**
   * Test {@link SQLScriptDataReceiver#fetchStart(DBCSession, DBCResultSet, long, long)}.
   *
   * <ul>
   *   <li>Then {@link SQLScriptDataReceiver} (default constructor) DumpWriter toString is {@code
   *       Columns:}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptDataReceiver#fetchStart(DBCSession, DBCResultSet, long,
   * long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLScriptDataReceiver.fetchStart(DBCSession, DBCResultSet, long, long)"})
  public void testFetchStart_thenSQLScriptDataReceiverDumpWriterToStringIsColumns()
      throws DBCException {
    // Arrange
    SQLScriptDataReceiver sqlScriptDataReceiver = new SQLScriptDataReceiver();
    sqlScriptDataReceiver.setDumpWriter(new StringWriter());
    LocalStatement statement = new LocalStatement(null, "Text");
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamTransferResultSet resultSet =
        new StreamTransferResultSet(null, statement, new StreamEntityMapping(inputFile));

    // Act
    sqlScriptDataReceiver.fetchStart(null, resultSet, 1L, 1L);

    // Assert
    assertEquals("Columns:\t\n", sqlScriptDataReceiver.getDumpWriter().toString());
  }

  /**
   * Test {@link SQLScriptDataReceiver#fetchStart(DBCSession, DBCResultSet, long, long)}.
   *
   * <ul>
   *   <li>Then throw {@link DBCException}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptDataReceiver#fetchStart(DBCSession, DBCResultSet, long,
   * long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLScriptDataReceiver.fetchStart(DBCSession, DBCResultSet, long, long)"})
  public void testFetchStart_thenThrowDBCException() throws DBCException {
    // Arrange
    SQLScriptDataReceiver sqlScriptDataReceiver = new SQLScriptDataReceiver();
    sqlScriptDataReceiver.setDumpWriter(new PipedWriter());
    LocalStatement statement = new LocalStatement(null, "Text");
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamTransferResultSet resultSet =
        new StreamTransferResultSet(null, statement, new StreamEntityMapping(inputFile));

    // Act and Assert
    assertThrows(
        DBCException.class, () -> sqlScriptDataReceiver.fetchStart(null, resultSet, 1L, 1L));
  }

  /**
   * Test {@link SQLScriptDataReceiver#fetchEnd(DBCSession, DBCResultSet)}.
   *
   * <ul>
   *   <li>Given {@link PEMWriter} {@link PEMWriter#flush()} throw {@link
   *       IOException#IOException()}.
   *   <li>Then throw {@link DBCException}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptDataReceiver#fetchEnd(DBCSession, DBCResultSet)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLScriptDataReceiver.fetchEnd(DBCSession, DBCResultSet)"})
  public void testFetchEnd_givenPEMWriterFlushThrowIOException_thenThrowDBCException()
      throws IOException, DBCException {
    // Arrange
    PEMWriter writer = mock(PEMWriter.class);
    doThrow(new IOException()).when(writer).flush();

    SQLScriptDataReceiver sqlScriptDataReceiver = new SQLScriptDataReceiver();
    sqlScriptDataReceiver.setDumpWriter(writer);
    LocalStatement statement = new LocalStatement(null, "Text");
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamTransferResultSet resultSet =
        new StreamTransferResultSet(null, statement, new StreamEntityMapping(inputFile));

    // Act and Assert
    assertThrows(DBCException.class, () -> sqlScriptDataReceiver.fetchEnd(null, resultSet));
    verify(writer).flush();
  }

  /**
   * Test {@link SQLScriptDataReceiver#fetchEnd(DBCSession, DBCResultSet)}.
   *
   * <ul>
   *   <li>Given {@link SQLScriptDataReceiver} (default constructor) DumpWriter is {@link
   *       StringWriter#StringWriter()}.
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptDataReceiver#fetchEnd(DBCSession, DBCResultSet)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLScriptDataReceiver.fetchEnd(DBCSession, DBCResultSet)"})
  public void testFetchEnd_givenSQLScriptDataReceiverDumpWriterIsStringWriter_thenDoesNotThrow()
      throws DBCException {
    // Arrange
    SQLScriptDataReceiver sqlScriptDataReceiver = new SQLScriptDataReceiver();
    sqlScriptDataReceiver.setDumpWriter(new StringWriter());
    LocalStatement statement = new LocalStatement(null, "Text");
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamTransferResultSet resultSet =
        new StreamTransferResultSet(null, statement, new StreamEntityMapping(inputFile));

    // Act and Assert
    sqlScriptDataReceiver.fetchEnd(null, resultSet);
  }

  /**
   * Test {@link SQLScriptDataReceiver#fetchEnd(DBCSession, DBCResultSet)}.
   *
   * <ul>
   *   <li>Given {@link SQLScriptDataReceiver} (default constructor).
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptDataReceiver#fetchEnd(DBCSession, DBCResultSet)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLScriptDataReceiver.fetchEnd(DBCSession, DBCResultSet)"})
  public void testFetchEnd_givenSQLScriptDataReceiver_thenDoesNotThrow() throws DBCException {
    // Arrange
    SQLScriptDataReceiver sqlScriptDataReceiver = new SQLScriptDataReceiver();
    LocalStatement statement = new LocalStatement(null, "Text");
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamTransferResultSet resultSet =
        new StreamTransferResultSet(null, statement, new StreamEntityMapping(inputFile));

    // Act and Assert
    sqlScriptDataReceiver.fetchEnd(null, resultSet);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link SQLScriptDataReceiver}
   *   <li>{@link SQLScriptDataReceiver#setDumpWriter(Writer)}
   *   <li>{@link SQLScriptDataReceiver#close()}
   *   <li>{@link SQLScriptDataReceiver#getDumpWriter()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLScriptDataReceiver.<init>()",
    "void SQLScriptDataReceiver.close()",
    "Writer SQLScriptDataReceiver.getDumpWriter()",
    "void SQLScriptDataReceiver.setDumpWriter(Writer)"
  })
  public void testGettersAndSetters() {
    // Arrange
    StringWriter writer = new StringWriter();

    // Act
    SQLScriptDataReceiver actualSqlScriptDataReceiver = new SQLScriptDataReceiver();
    actualSqlScriptDataReceiver.setDumpWriter(writer);
    actualSqlScriptDataReceiver.close();
    Writer actualDumpWriter = actualSqlScriptDataReceiver.getDumpWriter();

    // Assert
    assertEquals("", actualDumpWriter.toString());
    assertSame(writer, actualDumpWriter);
  }
}

package org.jkiss.dbeaver.model.impl;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.jkiss.dbeaver.model.exec.DBCExecutionResult;
import org.jkiss.dbeaver.model.exec.DBCSession;
import org.jkiss.dbeaver.model.exec.DBCStatement;
import org.jkiss.dbeaver.model.exec.DBCStatistics;
import org.jkiss.dbeaver.model.exec.output.DBCOutputWriter;
import org.jkiss.dbeaver.model.impl.local.LocalStatement;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AsyncServerOutputReaderDiffblueTest {
  /**
   * Test {@link AsyncServerOutputReader#readServerOutput(DBRProgressMonitor, DBCExecutionContext,
   * DBCExecutionResult, DBCStatement, DBCOutputWriter)}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.
   *   <li>Then calls {@link DBCStatistics#getWarnings()}.
   * </ul>
   *
   * <p>Method under test: {@link AsyncServerOutputReader#readServerOutput(DBRProgressMonitor,
   * DBCExecutionContext, DBCExecutionResult, DBCStatement, DBCOutputWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AsyncServerOutputReader.readServerOutput(DBRProgressMonitor, DBCExecutionContext, DBCExecutionResult, DBCStatement, DBCOutputWriter)"
  })
  public void testReadServerOutput_givenArrayList_thenCallsGetWarnings() throws DBCException {
    // Arrange
    AsyncServerOutputReader asyncServerOutputReader = new AsyncServerOutputReader();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBCExecutionContext context = mock(DBCExecutionContext.class);

    DBCStatistics executionResult = mock(DBCStatistics.class);
    when(executionResult.getWarnings()).thenReturn(new ArrayList<>());

    // Act
    asyncServerOutputReader.readServerOutput(
        monitor,
        context,
        executionResult,
        null,
        new OutputWriterAdapter(new PrintWriter(new StringWriter())));

    // Assert
    verify(executionResult).getWarnings();
  }

  /**
   * Test {@link AsyncServerOutputReader#readServerOutput(DBRProgressMonitor, DBCExecutionContext,
   * DBCExecutionResult, DBCStatement, DBCOutputWriter)}.
   *
   * <ul>
   *   <li>Given array of {@link Throwable} with {@link Throwable#Throwable()}.
   * </ul>
   *
   * <p>Method under test: {@link AsyncServerOutputReader#readServerOutput(DBRProgressMonitor,
   * DBCExecutionContext, DBCExecutionResult, DBCStatement, DBCOutputWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AsyncServerOutputReader.readServerOutput(DBRProgressMonitor, DBCExecutionContext, DBCExecutionResult, DBCStatement, DBCOutputWriter)"
  })
  public void testReadServerOutput_givenArrayOfThrowableWithThrowable() throws DBCException {
    // Arrange
    AsyncServerOutputReader asyncServerOutputReader = new AsyncServerOutputReader();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBCExecutionContext context = mock(DBCExecutionContext.class);
    DBCStatistics executionResult = new DBCStatistics();

    LocalStatement statement = mock(LocalStatement.class);
    when(statement.getStatementWarnings()).thenReturn(new Throwable[] {new Throwable()});

    // Act
    asyncServerOutputReader.readServerOutput(
        monitor,
        context,
        executionResult,
        statement,
        new OutputWriterAdapter(new PrintWriter(new StringWriter())));

    // Assert
    verify(statement).getStatementWarnings();
  }

  /**
   * Test {@link AsyncServerOutputReader#readServerOutput(DBRProgressMonitor, DBCExecutionContext,
   * DBCExecutionResult, DBCStatement, DBCOutputWriter)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>When {@link LocalStatement} {@link LocalStatement#getStatementWarnings()} return {@code
   *       null}.
   * </ul>
   *
   * <p>Method under test: {@link AsyncServerOutputReader#readServerOutput(DBRProgressMonitor,
   * DBCExecutionContext, DBCExecutionResult, DBCStatement, DBCOutputWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AsyncServerOutputReader.readServerOutput(DBRProgressMonitor, DBCExecutionContext, DBCExecutionResult, DBCStatement, DBCOutputWriter)"
  })
  public void testReadServerOutput_givenNull_whenLocalStatementGetStatementWarningsReturnNull()
      throws DBCException {
    // Arrange
    AsyncServerOutputReader asyncServerOutputReader = new AsyncServerOutputReader();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBCExecutionContext context = mock(DBCExecutionContext.class);
    DBCStatistics executionResult = new DBCStatistics();

    LocalStatement statement = mock(LocalStatement.class);
    when(statement.getStatementWarnings()).thenReturn(null);

    // Act
    asyncServerOutputReader.readServerOutput(
        monitor,
        context,
        executionResult,
        statement,
        new OutputWriterAdapter(new PrintWriter(new StringWriter())));

    // Assert
    verify(statement).getStatementWarnings();
  }

  /**
   * Test {@link AsyncServerOutputReader#readServerOutput(DBRProgressMonitor, DBCExecutionContext,
   * DBCExecutionResult, DBCStatement, DBCOutputWriter)}.
   *
   * <ul>
   *   <li>Given {@link Throwable#Throwable()}.
   *   <li>When {@link DBCStatistics} (default constructor) addWarning {@link
   *       Throwable#Throwable()}.
   * </ul>
   *
   * <p>Method under test: {@link AsyncServerOutputReader#readServerOutput(DBRProgressMonitor,
   * DBCExecutionContext, DBCExecutionResult, DBCStatement, DBCOutputWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AsyncServerOutputReader.readServerOutput(DBRProgressMonitor, DBCExecutionContext, DBCExecutionResult, DBCStatement, DBCOutputWriter)"
  })
  public void testReadServerOutput_givenThrowable_whenDBCStatisticsAddWarningThrowable()
      throws DBCException {
    // Arrange
    AsyncServerOutputReader asyncServerOutputReader = new AsyncServerOutputReader();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBCExecutionContext context = mock(DBCExecutionContext.class);

    DBCStatistics executionResult = new DBCStatistics();
    executionResult.addWarning(new Throwable());

    // Act and Assert
    asyncServerOutputReader.readServerOutput(
        monitor,
        context,
        executionResult,
        null,
        new OutputWriterAdapter(new PrintWriter(new StringWriter())));
  }

  /**
   * Test {@link AsyncServerOutputReader#readServerOutput(DBRProgressMonitor, DBCExecutionContext,
   * DBCExecutionResult, DBCStatement, DBCOutputWriter)}.
   *
   * <ul>
   *   <li>Then throw {@link DBCException}.
   * </ul>
   *
   * <p>Method under test: {@link AsyncServerOutputReader#readServerOutput(DBRProgressMonitor,
   * DBCExecutionContext, DBCExecutionResult, DBCStatement, DBCOutputWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AsyncServerOutputReader.readServerOutput(DBRProgressMonitor, DBCExecutionContext, DBCExecutionResult, DBCStatement, DBCOutputWriter)"
  })
  public void testReadServerOutput_thenThrowDBCException() throws DBCException {
    // Arrange
    AsyncServerOutputReader asyncServerOutputReader = new AsyncServerOutputReader();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBCExecutionContext context = mock(DBCExecutionContext.class);
    DBCStatistics executionResult = new DBCStatistics();

    LocalStatement statement = mock(LocalStatement.class);
    when(statement.getStatementWarnings()).thenThrow(new DBCException("An error occurred"));

    // Act and Assert
    assertThrows(
        DBCException.class,
        () ->
            asyncServerOutputReader.readServerOutput(
                monitor,
                context,
                executionResult,
                statement,
                new OutputWriterAdapter(new PrintWriter(new StringWriter()))));
    verify(statement).getStatementWarnings();
  }

  /**
   * Test {@link AsyncServerOutputReader#readServerOutput(DBRProgressMonitor, DBCExecutionContext,
   * DBCExecutionResult, DBCStatement, DBCOutputWriter)}.
   *
   * <ul>
   *   <li>When {@link LocalStatement#LocalStatement(DBCSession, String)} with session is {@link
   *       DBCSession} and {@code Text}.
   * </ul>
   *
   * <p>Method under test: {@link AsyncServerOutputReader#readServerOutput(DBRProgressMonitor,
   * DBCExecutionContext, DBCExecutionResult, DBCStatement, DBCOutputWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AsyncServerOutputReader.readServerOutput(DBRProgressMonitor, DBCExecutionContext, DBCExecutionResult, DBCStatement, DBCOutputWriter)"
  })
  public void testReadServerOutput_whenLocalStatementWithSessionIsDBCSessionAndText()
      throws DBCException {
    // Arrange
    AsyncServerOutputReader asyncServerOutputReader = new AsyncServerOutputReader();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBCExecutionContext context = mock(DBCExecutionContext.class);
    DBCStatistics executionResult = new DBCStatistics();
    LocalStatement statement = new LocalStatement(mock(DBCSession.class), "Text");

    // Act and Assert
    asyncServerOutputReader.readServerOutput(
        monitor,
        context,
        executionResult,
        statement,
        new OutputWriterAdapter(new PrintWriter(new StringWriter())));
  }

  /**
   * Test {@link AsyncServerOutputReader#readServerOutput(DBRProgressMonitor, DBCExecutionContext,
   * DBCExecutionResult, DBCStatement, DBCOutputWriter)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link AsyncServerOutputReader#readServerOutput(DBRProgressMonitor,
   * DBCExecutionContext, DBCExecutionResult, DBCStatement, DBCOutputWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AsyncServerOutputReader.readServerOutput(DBRProgressMonitor, DBCExecutionContext, DBCExecutionResult, DBCStatement, DBCOutputWriter)"
  })
  public void testReadServerOutput_whenNull_thenDoesNotThrow() throws DBCException {
    // Arrange
    AsyncServerOutputReader asyncServerOutputReader = new AsyncServerOutputReader();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBCExecutionContext context = mock(DBCExecutionContext.class);

    // Act and Assert
    asyncServerOutputReader.readServerOutput(
        monitor, context, null, null, new OutputWriterAdapter(new PrintWriter(new StringWriter())));
  }

  /**
   * Test {@link AsyncServerOutputReader#readServerOutput(DBRProgressMonitor, DBCExecutionContext,
   * DBCExecutionResult, DBCStatement, DBCOutputWriter)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link AsyncServerOutputReader#readServerOutput(DBRProgressMonitor,
   * DBCExecutionContext, DBCExecutionResult, DBCStatement, DBCOutputWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AsyncServerOutputReader.readServerOutput(DBRProgressMonitor, DBCExecutionContext, DBCExecutionResult, DBCStatement, DBCOutputWriter)"
  })
  public void testReadServerOutput_whenNull_thenDoesNotThrow2() throws DBCException {
    // Arrange
    AsyncServerOutputReader asyncServerOutputReader = new AsyncServerOutputReader();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBCExecutionContext context = mock(DBCExecutionContext.class);
    DBCStatistics executionResult = new DBCStatistics();

    // Act and Assert
    asyncServerOutputReader.readServerOutput(
        monitor,
        context,
        executionResult,
        null,
        new OutputWriterAdapter(new PrintWriter(new StringWriter())));
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link AsyncServerOutputReader}
   *   <li>{@link AsyncServerOutputReader#isAsyncOutputReadSupported()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AsyncServerOutputReader.<init>()",
    "boolean AsyncServerOutputReader.isAsyncOutputReadSupported()"
  })
  public void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertTrue(new AsyncServerOutputReader().isAsyncOutputReadSupported());
  }
}

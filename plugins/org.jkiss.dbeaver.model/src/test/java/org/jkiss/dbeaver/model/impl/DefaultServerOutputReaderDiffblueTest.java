package org.jkiss.dbeaver.model.impl;

import static org.junit.Assert.assertFalse;
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

public class DefaultServerOutputReaderDiffblueTest {
  /**
   * Test {@link DefaultServerOutputReader#readServerOutput(DBRProgressMonitor, DBCExecutionContext,
   * DBCExecutionResult, DBCStatement, DBCOutputWriter)}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.
   *   <li>Then calls {@link DBCStatistics#getWarnings()}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultServerOutputReader#readServerOutput(DBRProgressMonitor,
   * DBCExecutionContext, DBCExecutionResult, DBCStatement, DBCOutputWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DefaultServerOutputReader.readServerOutput(DBRProgressMonitor, DBCExecutionContext, DBCExecutionResult, DBCStatement, DBCOutputWriter)"
  })
  public void testReadServerOutput_givenArrayList_thenCallsGetWarnings() throws DBCException {
    // Arrange
    DefaultServerOutputReader defaultServerOutputReader = new DefaultServerOutputReader();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBCExecutionContext context = mock(DBCExecutionContext.class);

    DBCStatistics executionResult = mock(DBCStatistics.class);
    when(executionResult.getWarnings()).thenReturn(new ArrayList<>());
    LocalStatement statement = new LocalStatement(mock(DBCSession.class), "Text");

    // Act
    defaultServerOutputReader.readServerOutput(
        monitor,
        context,
        executionResult,
        statement,
        new OutputWriterAdapter(new PrintWriter(new StringWriter())));

    // Assert
    verify(executionResult).getWarnings();
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link DefaultServerOutputReader}
   *   <li>{@link DefaultServerOutputReader#isAsyncOutputReadSupported()}
   *   <li>{@link DefaultServerOutputReader#isServerOutputEnabled()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DefaultServerOutputReader.<init>()",
    "boolean DefaultServerOutputReader.isAsyncOutputReadSupported()",
    "boolean DefaultServerOutputReader.isServerOutputEnabled()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    DefaultServerOutputReader actualDefaultServerOutputReader = new DefaultServerOutputReader();
    boolean actualIsAsyncOutputReadSupportedResult =
        actualDefaultServerOutputReader.isAsyncOutputReadSupported();

    // Assert
    assertFalse(actualIsAsyncOutputReadSupportedResult);
    assertTrue(actualDefaultServerOutputReader.isServerOutputEnabled());
  }
}

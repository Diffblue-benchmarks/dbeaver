package org.jkiss.dbeaver.model.sql.commands;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPContextProvider;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.connection.DBPConnectionConfiguration;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.jkiss.dbeaver.model.impl.OutputWriterAdapter;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.sql.SQLControlCommand;
import org.jkiss.dbeaver.model.sql.SQLControlResult;
import org.jkiss.dbeaver.model.sql.SQLDialect;
import org.jkiss.dbeaver.model.sql.SQLParametersProvider;
import org.jkiss.dbeaver.model.sql.SQLScriptContext;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class SQLCommandUnsetDiffblueTest {
  /**
   * Test {@link SQLCommandUnset#handleCommand(DBRProgressMonitor, SQLControlCommand,
   * SQLScriptContext)}.
   *
   * <ul>
   *   <li>Given empty string.
   * </ul>
   *
   * <p>Method under test: {@link SQLCommandUnset#handleCommand(DBRProgressMonitor,
   * SQLControlCommand, SQLScriptContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLControlResult SQLCommandUnset.handleCommand(DBRProgressMonitor, SQLControlCommand, SQLScriptContext)"
  })
  public void testHandleCommand_givenEmptyString() throws DBException {
    // Arrange
    SQLCommandUnset sqlCommandUnset = new SQLCommandUnset();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    SQLControlCommand command = mock(SQLControlCommand.class);
    when(command.getParameter()).thenReturn("");

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBCExecutionContext dbcExecutionContext = mock(DBCExecutionContext.class);
    when(dbcExecutionContext.getDataSource()).thenReturn(dbpDataSource);

    DBPContextProvider contextProvider = mock(DBPContextProvider.class);
    when(contextProvider.getExecutionContext()).thenReturn(dbcExecutionContext);
    Path sourceFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    OutputWriterAdapter outputWriter = new OutputWriterAdapter(new PrintWriter(new StringWriter()));

    SQLScriptContext parentContext =
        new SQLScriptContext(
            null, contextProvider, sourceFile, outputWriter, mock(SQLParametersProvider.class));

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer2.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getContainer()).thenReturn(dbpDataSourceContainer2);

    DBCExecutionContext dbcExecutionContext2 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext2.getDataSource()).thenReturn(dbpDataSource2);

    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    when(contextProvider2.getExecutionContext()).thenReturn(dbcExecutionContext2);
    Path sourceFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext parentContext2 =
        new SQLScriptContext(
            parentContext,
            contextProvider2,
            sourceFile2,
            new StringWriter(),
            mock(SQLParametersProvider.class));

    DBPDataSourceContainer dbpDataSourceContainer3 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer3.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource3 = mock(DBPDataSource.class);
    when(dbpDataSource3.getContainer()).thenReturn(dbpDataSourceContainer3);

    DBCExecutionContext dbcExecutionContext3 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext3.getDataSource()).thenReturn(dbpDataSource3);

    DBPContextProvider contextProvider3 = mock(DBPContextProvider.class);
    when(contextProvider3.getExecutionContext()).thenReturn(dbcExecutionContext3);
    Path sourceFile3 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    new SQLScriptContext(
        parentContext2,
        contextProvider3,
        sourceFile3,
        new OutputWriterAdapter(null),
        mock(SQLParametersProvider.class));
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DBPDataSourceContainer dbpDataSourceContainer4 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer4.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource4 = mock(DBPDataSource.class);
    when(dbpDataSource4.getContainer()).thenReturn(dbpDataSourceContainer4);

    DBCExecutionContext dbcExecutionContext4 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext4.getDataSource()).thenReturn(dbpDataSource4);

    DBPContextProvider contextProvider4 = mock(DBPContextProvider.class);
    when(contextProvider4.getExecutionContext()).thenReturn(dbcExecutionContext4);
    Path sourceFile4 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    OutputWriterAdapter outputWriter2 =
        new OutputWriterAdapter(new PrintWriter(new StringWriter()));

    new SQLScriptContext(
        null, contextProvider4, sourceFile4, outputWriter2, mock(SQLParametersProvider.class));
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DBPDataSourceContainer dbpDataSourceContainer5 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer5.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource5 = mock(DBPDataSource.class);
    when(dbpDataSource5.getContainer()).thenReturn(dbpDataSourceContainer5);

    DBCExecutionContext dbcExecutionContext5 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext5.getDataSource()).thenReturn(dbpDataSource5);

    DBPContextProvider contextProvider5 = mock(DBPContextProvider.class);
    when(contextProvider5.getExecutionContext()).thenReturn(dbcExecutionContext5);
    Path sourceFile5 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    new SQLScriptContext(
        null, contextProvider5, sourceFile5, new StringWriter(), mock(SQLParametersProvider.class));
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DBPDataSourceContainer dbpDataSourceContainer6 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer6.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource6 = mock(DBPDataSource.class);
    when(dbpDataSource6.getContainer()).thenReturn(dbpDataSourceContainer6);

    DBCExecutionContext dbcExecutionContext6 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext6.getDataSource()).thenReturn(dbpDataSource6);

    DBPContextProvider contextProvider6 = mock(DBPContextProvider.class);
    when(contextProvider6.getExecutionContext()).thenReturn(dbcExecutionContext6);
    Path sourceFile6 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    OutputWriterAdapter outputWriter3 =
        new OutputWriterAdapter(new PrintWriter(new StringWriter()));

    new SQLScriptContext(
        null, contextProvider6, sourceFile6, outputWriter3, mock(SQLParametersProvider.class));
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DBPDataSourceContainer dbpDataSourceContainer7 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer7.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource7 = mock(DBPDataSource.class);
    when(dbpDataSource7.getContainer()).thenReturn(dbpDataSourceContainer7);

    DBCExecutionContext dbcExecutionContext7 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext7.getDataSource()).thenReturn(dbpDataSource7);

    DBPContextProvider contextProvider7 = mock(DBPContextProvider.class);
    when(contextProvider7.getExecutionContext()).thenReturn(dbcExecutionContext7);
    Path sourceFile7 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    new SQLScriptContext(
        null, contextProvider7, sourceFile7, new StringWriter(), mock(SQLParametersProvider.class));
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DBPDataSourceContainer dbpDataSourceContainer8 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer8.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource8 = mock(DBPDataSource.class);
    when(dbpDataSource8.getContainer()).thenReturn(dbpDataSourceContainer8);

    DBCExecutionContext dbcExecutionContext8 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext8.getDataSource()).thenReturn(dbpDataSource8);

    DBPContextProvider contextProvider8 = mock(DBPContextProvider.class);
    when(contextProvider8.getExecutionContext()).thenReturn(dbcExecutionContext8);
    Path sourceFile8 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    OutputWriterAdapter outputWriter4 =
        new OutputWriterAdapter(new PrintWriter(new StringWriter()));

    new SQLScriptContext(
        null, contextProvider8, sourceFile8, outputWriter4, mock(SQLParametersProvider.class));
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DBPDataSourceContainer dbpDataSourceContainer9 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer9.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource9 = mock(DBPDataSource.class);
    when(dbpDataSource9.getContainer()).thenReturn(dbpDataSourceContainer9);

    DBCExecutionContext dbcExecutionContext9 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext9.getDataSource()).thenReturn(dbpDataSource9);

    DBPContextProvider contextProvider9 = mock(DBPContextProvider.class);
    when(contextProvider9.getExecutionContext()).thenReturn(dbcExecutionContext9);
    Path sourceFile9 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    new SQLScriptContext(
        null, contextProvider9, sourceFile9, new StringWriter(), mock(SQLParametersProvider.class));
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DBPDataSourceContainer dbpDataSourceContainer10 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer10.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource10 = mock(DBPDataSource.class);
    when(dbpDataSource10.getContainer()).thenReturn(dbpDataSourceContainer10);

    DBCExecutionContext dbcExecutionContext10 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext10.getDataSource()).thenReturn(dbpDataSource10);

    DBPContextProvider contextProvider10 = mock(DBPContextProvider.class);
    when(contextProvider10.getExecutionContext()).thenReturn(dbcExecutionContext10);
    Path sourceFile10 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    OutputWriterAdapter outputWriter5 =
        new OutputWriterAdapter(new PrintWriter(new StringWriter()));

    new SQLScriptContext(
        null, contextProvider10, sourceFile10, outputWriter5, mock(SQLParametersProvider.class));
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DBPDataSourceContainer dbpDataSourceContainer11 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer11.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource11 = mock(DBPDataSource.class);
    when(dbpDataSource11.getContainer()).thenReturn(dbpDataSourceContainer11);

    DBCExecutionContext dbcExecutionContext11 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext11.getDataSource()).thenReturn(dbpDataSource11);

    DBPContextProvider contextProvider11 = mock(DBPContextProvider.class);
    when(contextProvider11.getExecutionContext()).thenReturn(dbcExecutionContext11);
    Path sourceFile11 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    new SQLScriptContext(
        null,
        contextProvider11,
        sourceFile11,
        new StringWriter(),
        mock(SQLParametersProvider.class));
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DBPDataSourceContainer dbpDataSourceContainer12 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer12.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource12 = mock(DBPDataSource.class);
    when(dbpDataSource12.getContainer()).thenReturn(dbpDataSourceContainer12);

    DBCExecutionContext dbcExecutionContext12 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext12.getDataSource()).thenReturn(dbpDataSource12);

    DBPContextProvider contextProvider12 = mock(DBPContextProvider.class);
    when(contextProvider12.getExecutionContext()).thenReturn(dbcExecutionContext12);
    Path sourceFile12 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    OutputWriterAdapter outputWriter6 =
        new OutputWriterAdapter(new PrintWriter(new StringWriter()));

    new SQLScriptContext(
        null, contextProvider12, sourceFile12, outputWriter6, mock(SQLParametersProvider.class));
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DBPDataSourceContainer dbpDataSourceContainer13 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer13.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource13 = mock(DBPDataSource.class);
    when(dbpDataSource13.getContainer()).thenReturn(dbpDataSourceContainer13);

    DBCExecutionContext dbcExecutionContext13 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext13.getDataSource()).thenReturn(dbpDataSource13);

    DBPContextProvider contextProvider13 = mock(DBPContextProvider.class);
    when(contextProvider13.getExecutionContext()).thenReturn(dbcExecutionContext13);
    Path sourceFile13 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    new SQLScriptContext(
        null,
        contextProvider13,
        sourceFile13,
        new StringWriter(),
        mock(SQLParametersProvider.class));
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DBPDataSourceContainer dbpDataSourceContainer14 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer14.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource14 = mock(DBPDataSource.class);
    when(dbpDataSource14.getContainer()).thenReturn(dbpDataSourceContainer14);

    DBCExecutionContext dbcExecutionContext14 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext14.getDataSource()).thenReturn(dbpDataSource14);

    DBPContextProvider contextProvider14 = mock(DBPContextProvider.class);
    when(contextProvider14.getExecutionContext()).thenReturn(dbcExecutionContext14);
    Path sourceFile14 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    OutputWriterAdapter outputWriter7 =
        new OutputWriterAdapter(new PrintWriter(new StringWriter()));

    new SQLScriptContext(
        null, contextProvider14, sourceFile14, outputWriter7, mock(SQLParametersProvider.class));
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DBPDataSourceContainer dbpDataSourceContainer15 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer15.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource15 = mock(DBPDataSource.class);
    when(dbpDataSource15.getContainer()).thenReturn(dbpDataSourceContainer15);

    DBCExecutionContext dbcExecutionContext15 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext15.getDataSource()).thenReturn(dbpDataSource15);

    DBPContextProvider contextProvider15 = mock(DBPContextProvider.class);
    when(contextProvider15.getExecutionContext()).thenReturn(dbcExecutionContext15);
    Path sourceFile15 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    new SQLScriptContext(
        null,
        contextProvider15,
        sourceFile15,
        new StringWriter(),
        mock(SQLParametersProvider.class));
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});

    DBPDataSource dbpDataSource16 = mock(DBPDataSource.class);
    when(dbpDataSource16.getSQLDialect()).thenReturn(sqlDialect);

    DBCExecutionContext dbcExecutionContext16 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext16.getDataSource()).thenReturn(dbpDataSource16);

    SQLScriptContext scriptContext = mock(SQLScriptContext.class);
    when(scriptContext.getExecutionContext()).thenReturn(dbcExecutionContext16);

    // Act and Assert
    assertThrows(
        DBCException.class, () -> sqlCommandUnset.handleCommand(monitor, command, scriptContext));
    verify(contextProvider).getExecutionContext();
    verify(contextProvider2).getExecutionContext();
    verify(contextProvider3).getExecutionContext();
    verify(contextProvider4).getExecutionContext();
    verify(contextProvider5).getExecutionContext();
    verify(contextProvider6).getExecutionContext();
    verify(contextProvider7).getExecutionContext();
    verify(contextProvider8).getExecutionContext();
    verify(contextProvider9).getExecutionContext();
    verify(contextProvider10).getExecutionContext();
    verify(contextProvider11).getExecutionContext();
    verify(contextProvider12).getExecutionContext();
    verify(contextProvider13).getExecutionContext();
    verify(contextProvider14).getExecutionContext();
    verify(contextProvider15).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSource3).getContainer();
    verify(dbpDataSource4).getContainer();
    verify(dbpDataSource5).getContainer();
    verify(dbpDataSource6).getContainer();
    verify(dbpDataSource7).getContainer();
    verify(dbpDataSource8).getContainer();
    verify(dbpDataSource9).getContainer();
    verify(dbpDataSource10).getContainer();
    verify(dbpDataSource11).getContainer();
    verify(dbpDataSource12).getContainer();
    verify(dbpDataSource13).getContainer();
    verify(dbpDataSource14).getContainer();
    verify(dbpDataSource15).getContainer();
    verify(dbpDataSource16).getSQLDialect();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(dbpDataSourceContainer3).getConnectionConfiguration();
    verify(dbpDataSourceContainer4).getConnectionConfiguration();
    verify(dbpDataSourceContainer5).getConnectionConfiguration();
    verify(dbpDataSourceContainer6).getConnectionConfiguration();
    verify(dbpDataSourceContainer7).getConnectionConfiguration();
    verify(dbpDataSourceContainer8).getConnectionConfiguration();
    verify(dbpDataSourceContainer9).getConnectionConfiguration();
    verify(dbpDataSourceContainer10).getConnectionConfiguration();
    verify(dbpDataSourceContainer11).getConnectionConfiguration();
    verify(dbpDataSourceContainer12).getConnectionConfiguration();
    verify(dbpDataSourceContainer13).getConnectionConfiguration();
    verify(dbpDataSourceContainer14).getConnectionConfiguration();
    verify(dbpDataSourceContainer15).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(dbcExecutionContext2).getDataSource();
    verify(dbcExecutionContext3).getDataSource();
    verify(dbcExecutionContext4).getDataSource();
    verify(dbcExecutionContext5).getDataSource();
    verify(dbcExecutionContext6).getDataSource();
    verify(dbcExecutionContext7).getDataSource();
    verify(dbcExecutionContext8).getDataSource();
    verify(dbcExecutionContext9).getDataSource();
    verify(dbcExecutionContext10).getDataSource();
    verify(dbcExecutionContext11).getDataSource();
    verify(dbcExecutionContext12).getDataSource();
    verify(dbcExecutionContext13).getDataSource();
    verify(dbcExecutionContext14).getDataSource();
    verify(dbcExecutionContext15).getDataSource();
    verify(dbcExecutionContext16).getDataSource();
    verify(command).getParameter();
    verify(sqlDialect).getIdentifierQuoteStrings();
    verify(scriptContext).getExecutionContext();
  }

  /**
   * Test {@link SQLCommandUnset#handleCommand(DBRProgressMonitor, SQLControlCommand,
   * SQLScriptContext)}.
   *
   * <ul>
   *   <li>Given {@code Identifier Quote Strings}.
   * </ul>
   *
   * <p>Method under test: {@link SQLCommandUnset#handleCommand(DBRProgressMonitor,
   * SQLControlCommand, SQLScriptContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLControlResult SQLCommandUnset.handleCommand(DBRProgressMonitor, SQLControlCommand, SQLScriptContext)"
  })
  public void testHandleCommand_givenIdentifierQuoteStrings() throws DBException {
    // Arrange
    SQLCommandUnset sqlCommandUnset = new SQLCommandUnset();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    SQLControlCommand command = mock(SQLControlCommand.class);
    when(command.getParameter()).thenReturn("Identifier Quote Strings");

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBCExecutionContext dbcExecutionContext = mock(DBCExecutionContext.class);
    when(dbcExecutionContext.getDataSource()).thenReturn(dbpDataSource);

    DBPContextProvider contextProvider = mock(DBPContextProvider.class);
    when(contextProvider.getExecutionContext()).thenReturn(dbcExecutionContext);
    Path sourceFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    OutputWriterAdapter outputWriter = new OutputWriterAdapter(new PrintWriter(new StringWriter()));

    SQLScriptContext parentContext =
        new SQLScriptContext(
            null, contextProvider, sourceFile, outputWriter, mock(SQLParametersProvider.class));

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer2.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getContainer()).thenReturn(dbpDataSourceContainer2);

    DBCExecutionContext dbcExecutionContext2 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext2.getDataSource()).thenReturn(dbpDataSource2);

    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    when(contextProvider2.getExecutionContext()).thenReturn(dbcExecutionContext2);
    Path sourceFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext parentContext2 =
        new SQLScriptContext(
            parentContext,
            contextProvider2,
            sourceFile2,
            new StringWriter(),
            mock(SQLParametersProvider.class));

    DBPDataSourceContainer dbpDataSourceContainer3 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer3.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource3 = mock(DBPDataSource.class);
    when(dbpDataSource3.getContainer()).thenReturn(dbpDataSourceContainer3);

    DBCExecutionContext dbcExecutionContext3 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext3.getDataSource()).thenReturn(dbpDataSource3);

    DBPContextProvider contextProvider3 = mock(DBPContextProvider.class);
    when(contextProvider3.getExecutionContext()).thenReturn(dbcExecutionContext3);
    Path sourceFile3 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    new SQLScriptContext(
        parentContext2,
        contextProvider3,
        sourceFile3,
        new OutputWriterAdapter(null),
        mock(SQLParametersProvider.class));
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DBPDataSourceContainer dbpDataSourceContainer4 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer4.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource4 = mock(DBPDataSource.class);
    when(dbpDataSource4.getContainer()).thenReturn(dbpDataSourceContainer4);

    DBCExecutionContext dbcExecutionContext4 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext4.getDataSource()).thenReturn(dbpDataSource4);

    DBPContextProvider contextProvider4 = mock(DBPContextProvider.class);
    when(contextProvider4.getExecutionContext()).thenReturn(dbcExecutionContext4);
    Path sourceFile4 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    OutputWriterAdapter outputWriter2 =
        new OutputWriterAdapter(new PrintWriter(new StringWriter()));

    new SQLScriptContext(
        null, contextProvider4, sourceFile4, outputWriter2, mock(SQLParametersProvider.class));
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DBPDataSourceContainer dbpDataSourceContainer5 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer5.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource5 = mock(DBPDataSource.class);
    when(dbpDataSource5.getContainer()).thenReturn(dbpDataSourceContainer5);

    DBCExecutionContext dbcExecutionContext5 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext5.getDataSource()).thenReturn(dbpDataSource5);

    DBPContextProvider contextProvider5 = mock(DBPContextProvider.class);
    when(contextProvider5.getExecutionContext()).thenReturn(dbcExecutionContext5);
    Path sourceFile5 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    new SQLScriptContext(
        null, contextProvider5, sourceFile5, new StringWriter(), mock(SQLParametersProvider.class));
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DBPDataSourceContainer dbpDataSourceContainer6 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer6.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource6 = mock(DBPDataSource.class);
    when(dbpDataSource6.getContainer()).thenReturn(dbpDataSourceContainer6);

    DBCExecutionContext dbcExecutionContext6 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext6.getDataSource()).thenReturn(dbpDataSource6);

    DBPContextProvider contextProvider6 = mock(DBPContextProvider.class);
    when(contextProvider6.getExecutionContext()).thenReturn(dbcExecutionContext6);
    Path sourceFile6 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    OutputWriterAdapter outputWriter3 =
        new OutputWriterAdapter(new PrintWriter(new StringWriter()));

    new SQLScriptContext(
        null, contextProvider6, sourceFile6, outputWriter3, mock(SQLParametersProvider.class));
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DBPDataSourceContainer dbpDataSourceContainer7 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer7.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource7 = mock(DBPDataSource.class);
    when(dbpDataSource7.getContainer()).thenReturn(dbpDataSourceContainer7);

    DBCExecutionContext dbcExecutionContext7 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext7.getDataSource()).thenReturn(dbpDataSource7);

    DBPContextProvider contextProvider7 = mock(DBPContextProvider.class);
    when(contextProvider7.getExecutionContext()).thenReturn(dbcExecutionContext7);
    Path sourceFile7 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    new SQLScriptContext(
        null, contextProvider7, sourceFile7, new StringWriter(), mock(SQLParametersProvider.class));
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DBPDataSourceContainer dbpDataSourceContainer8 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer8.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource8 = mock(DBPDataSource.class);
    when(dbpDataSource8.getContainer()).thenReturn(dbpDataSourceContainer8);

    DBCExecutionContext dbcExecutionContext8 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext8.getDataSource()).thenReturn(dbpDataSource8);

    DBPContextProvider contextProvider8 = mock(DBPContextProvider.class);
    when(contextProvider8.getExecutionContext()).thenReturn(dbcExecutionContext8);
    Path sourceFile8 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    OutputWriterAdapter outputWriter4 =
        new OutputWriterAdapter(new PrintWriter(new StringWriter()));

    new SQLScriptContext(
        null, contextProvider8, sourceFile8, outputWriter4, mock(SQLParametersProvider.class));
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DBPDataSourceContainer dbpDataSourceContainer9 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer9.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource9 = mock(DBPDataSource.class);
    when(dbpDataSource9.getContainer()).thenReturn(dbpDataSourceContainer9);

    DBCExecutionContext dbcExecutionContext9 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext9.getDataSource()).thenReturn(dbpDataSource9);

    DBPContextProvider contextProvider9 = mock(DBPContextProvider.class);
    when(contextProvider9.getExecutionContext()).thenReturn(dbcExecutionContext9);
    Path sourceFile9 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    new SQLScriptContext(
        null, contextProvider9, sourceFile9, new StringWriter(), mock(SQLParametersProvider.class));
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DBPDataSourceContainer dbpDataSourceContainer10 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer10.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource10 = mock(DBPDataSource.class);
    when(dbpDataSource10.getContainer()).thenReturn(dbpDataSourceContainer10);

    DBCExecutionContext dbcExecutionContext10 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext10.getDataSource()).thenReturn(dbpDataSource10);

    DBPContextProvider contextProvider10 = mock(DBPContextProvider.class);
    when(contextProvider10.getExecutionContext()).thenReturn(dbcExecutionContext10);
    Path sourceFile10 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    OutputWriterAdapter outputWriter5 =
        new OutputWriterAdapter(new PrintWriter(new StringWriter()));

    new SQLScriptContext(
        null, contextProvider10, sourceFile10, outputWriter5, mock(SQLParametersProvider.class));
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DBPDataSourceContainer dbpDataSourceContainer11 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer11.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource11 = mock(DBPDataSource.class);
    when(dbpDataSource11.getContainer()).thenReturn(dbpDataSourceContainer11);

    DBCExecutionContext dbcExecutionContext11 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext11.getDataSource()).thenReturn(dbpDataSource11);

    DBPContextProvider contextProvider11 = mock(DBPContextProvider.class);
    when(contextProvider11.getExecutionContext()).thenReturn(dbcExecutionContext11);
    Path sourceFile11 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    new SQLScriptContext(
        null,
        contextProvider11,
        sourceFile11,
        new StringWriter(),
        mock(SQLParametersProvider.class));
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DBPDataSourceContainer dbpDataSourceContainer12 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer12.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource12 = mock(DBPDataSource.class);
    when(dbpDataSource12.getContainer()).thenReturn(dbpDataSourceContainer12);

    DBCExecutionContext dbcExecutionContext12 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext12.getDataSource()).thenReturn(dbpDataSource12);

    DBPContextProvider contextProvider12 = mock(DBPContextProvider.class);
    when(contextProvider12.getExecutionContext()).thenReturn(dbcExecutionContext12);
    Path sourceFile12 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    OutputWriterAdapter outputWriter6 =
        new OutputWriterAdapter(new PrintWriter(new StringWriter()));

    new SQLScriptContext(
        null, contextProvider12, sourceFile12, outputWriter6, mock(SQLParametersProvider.class));
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DBPDataSourceContainer dbpDataSourceContainer13 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer13.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource13 = mock(DBPDataSource.class);
    when(dbpDataSource13.getContainer()).thenReturn(dbpDataSourceContainer13);

    DBCExecutionContext dbcExecutionContext13 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext13.getDataSource()).thenReturn(dbpDataSource13);

    DBPContextProvider contextProvider13 = mock(DBPContextProvider.class);
    when(contextProvider13.getExecutionContext()).thenReturn(dbcExecutionContext13);
    Path sourceFile13 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    new SQLScriptContext(
        null,
        contextProvider13,
        sourceFile13,
        new StringWriter(),
        mock(SQLParametersProvider.class));
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DBPDataSourceContainer dbpDataSourceContainer14 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer14.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource14 = mock(DBPDataSource.class);
    when(dbpDataSource14.getContainer()).thenReturn(dbpDataSourceContainer14);

    DBCExecutionContext dbcExecutionContext14 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext14.getDataSource()).thenReturn(dbpDataSource14);

    DBPContextProvider contextProvider14 = mock(DBPContextProvider.class);
    when(contextProvider14.getExecutionContext()).thenReturn(dbcExecutionContext14);
    Path sourceFile14 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    OutputWriterAdapter outputWriter7 =
        new OutputWriterAdapter(new PrintWriter(new StringWriter()));

    new SQLScriptContext(
        null, contextProvider14, sourceFile14, outputWriter7, mock(SQLParametersProvider.class));
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DBPDataSourceContainer dbpDataSourceContainer15 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer15.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource15 = mock(DBPDataSource.class);
    when(dbpDataSource15.getContainer()).thenReturn(dbpDataSourceContainer15);

    DBCExecutionContext dbcExecutionContext15 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext15.getDataSource()).thenReturn(dbpDataSource15);

    DBPContextProvider contextProvider15 = mock(DBPContextProvider.class);
    when(contextProvider15.getExecutionContext()).thenReturn(dbcExecutionContext15);
    Path sourceFile15 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    new SQLScriptContext(
        null,
        contextProvider15,
        sourceFile15,
        new StringWriter(),
        mock(SQLParametersProvider.class));
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});

    DBPDataSource dbpDataSource16 = mock(DBPDataSource.class);
    when(dbpDataSource16.getSQLDialect()).thenReturn(sqlDialect);

    DBCExecutionContext dbcExecutionContext16 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext16.getDataSource()).thenReturn(dbpDataSource16);

    SQLScriptContext scriptContext = mock(SQLScriptContext.class);
    when(scriptContext.getExecutionContext()).thenReturn(dbcExecutionContext16);

    // Act and Assert
    assertThrows(
        DBCException.class, () -> sqlCommandUnset.handleCommand(monitor, command, scriptContext));
    verify(contextProvider).getExecutionContext();
    verify(contextProvider2).getExecutionContext();
    verify(contextProvider3).getExecutionContext();
    verify(contextProvider4).getExecutionContext();
    verify(contextProvider5).getExecutionContext();
    verify(contextProvider6).getExecutionContext();
    verify(contextProvider7).getExecutionContext();
    verify(contextProvider8).getExecutionContext();
    verify(contextProvider9).getExecutionContext();
    verify(contextProvider10).getExecutionContext();
    verify(contextProvider11).getExecutionContext();
    verify(contextProvider12).getExecutionContext();
    verify(contextProvider13).getExecutionContext();
    verify(contextProvider14).getExecutionContext();
    verify(contextProvider15).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSource3).getContainer();
    verify(dbpDataSource4).getContainer();
    verify(dbpDataSource5).getContainer();
    verify(dbpDataSource6).getContainer();
    verify(dbpDataSource7).getContainer();
    verify(dbpDataSource8).getContainer();
    verify(dbpDataSource9).getContainer();
    verify(dbpDataSource10).getContainer();
    verify(dbpDataSource11).getContainer();
    verify(dbpDataSource12).getContainer();
    verify(dbpDataSource13).getContainer();
    verify(dbpDataSource14).getContainer();
    verify(dbpDataSource15).getContainer();
    verify(dbpDataSource16).getSQLDialect();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(dbpDataSourceContainer3).getConnectionConfiguration();
    verify(dbpDataSourceContainer4).getConnectionConfiguration();
    verify(dbpDataSourceContainer5).getConnectionConfiguration();
    verify(dbpDataSourceContainer6).getConnectionConfiguration();
    verify(dbpDataSourceContainer7).getConnectionConfiguration();
    verify(dbpDataSourceContainer8).getConnectionConfiguration();
    verify(dbpDataSourceContainer9).getConnectionConfiguration();
    verify(dbpDataSourceContainer10).getConnectionConfiguration();
    verify(dbpDataSourceContainer11).getConnectionConfiguration();
    verify(dbpDataSourceContainer12).getConnectionConfiguration();
    verify(dbpDataSourceContainer13).getConnectionConfiguration();
    verify(dbpDataSourceContainer14).getConnectionConfiguration();
    verify(dbpDataSourceContainer15).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(dbcExecutionContext2).getDataSource();
    verify(dbcExecutionContext3).getDataSource();
    verify(dbcExecutionContext4).getDataSource();
    verify(dbcExecutionContext5).getDataSource();
    verify(dbcExecutionContext6).getDataSource();
    verify(dbcExecutionContext7).getDataSource();
    verify(dbcExecutionContext8).getDataSource();
    verify(dbcExecutionContext9).getDataSource();
    verify(dbcExecutionContext10).getDataSource();
    verify(dbcExecutionContext11).getDataSource();
    verify(dbcExecutionContext12).getDataSource();
    verify(dbcExecutionContext13).getDataSource();
    verify(dbcExecutionContext14).getDataSource();
    verify(dbcExecutionContext15).getDataSource();
    verify(dbcExecutionContext16).getDataSource();
    verify(command).getParameter();
    verify(sqlDialect).getIdentifierQuoteStrings();
    verify(scriptContext).getExecutionContext();
  }

  /**
   * Test {@link SQLCommandUnset#handleCommand(DBRProgressMonitor, SQLControlCommand,
   * SQLScriptContext)}.
   *
   * <ul>
   *   <li>Given {@code Parameter}.
   *   <li>Then return Transformed is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLCommandUnset#handleCommand(DBRProgressMonitor,
   * SQLControlCommand, SQLScriptContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLControlResult SQLCommandUnset.handleCommand(DBRProgressMonitor, SQLControlCommand, SQLScriptContext)"
  })
  public void testHandleCommand_givenParameter_thenReturnTransformedIsNull() throws DBException {
    // Arrange
    SQLCommandUnset sqlCommandUnset = new SQLCommandUnset();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    SQLControlCommand command = mock(SQLControlCommand.class);
    when(command.getParameter()).thenReturn("Parameter");

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBCExecutionContext dbcExecutionContext = mock(DBCExecutionContext.class);
    when(dbcExecutionContext.getDataSource()).thenReturn(dbpDataSource);

    DBPContextProvider contextProvider = mock(DBPContextProvider.class);
    when(contextProvider.getExecutionContext()).thenReturn(dbcExecutionContext);
    Path sourceFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    OutputWriterAdapter outputWriter = new OutputWriterAdapter(new PrintWriter(new StringWriter()));

    SQLScriptContext parentContext =
        new SQLScriptContext(
            null, contextProvider, sourceFile, outputWriter, mock(SQLParametersProvider.class));

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer2.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getContainer()).thenReturn(dbpDataSourceContainer2);

    DBCExecutionContext dbcExecutionContext2 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext2.getDataSource()).thenReturn(dbpDataSource2);

    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    when(contextProvider2.getExecutionContext()).thenReturn(dbcExecutionContext2);
    Path sourceFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext parentContext2 =
        new SQLScriptContext(
            parentContext,
            contextProvider2,
            sourceFile2,
            new StringWriter(),
            mock(SQLParametersProvider.class));

    DBPDataSourceContainer dbpDataSourceContainer3 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer3.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource3 = mock(DBPDataSource.class);
    when(dbpDataSource3.getContainer()).thenReturn(dbpDataSourceContainer3);

    DBCExecutionContext dbcExecutionContext3 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext3.getDataSource()).thenReturn(dbpDataSource3);

    DBPContextProvider contextProvider3 = mock(DBPContextProvider.class);
    when(contextProvider3.getExecutionContext()).thenReturn(dbcExecutionContext3);
    Path sourceFile3 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    new SQLScriptContext(
        parentContext2,
        contextProvider3,
        sourceFile3,
        new OutputWriterAdapter(null),
        mock(SQLParametersProvider.class));
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DBPDataSourceContainer dbpDataSourceContainer4 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer4.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource4 = mock(DBPDataSource.class);
    when(dbpDataSource4.getContainer()).thenReturn(dbpDataSourceContainer4);

    DBCExecutionContext dbcExecutionContext4 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext4.getDataSource()).thenReturn(dbpDataSource4);

    DBPContextProvider contextProvider4 = mock(DBPContextProvider.class);
    when(contextProvider4.getExecutionContext()).thenReturn(dbcExecutionContext4);
    Path sourceFile4 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    OutputWriterAdapter outputWriter2 =
        new OutputWriterAdapter(new PrintWriter(new StringWriter()));

    new SQLScriptContext(
        null, contextProvider4, sourceFile4, outputWriter2, mock(SQLParametersProvider.class));
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DBPDataSourceContainer dbpDataSourceContainer5 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer5.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource5 = mock(DBPDataSource.class);
    when(dbpDataSource5.getContainer()).thenReturn(dbpDataSourceContainer5);

    DBCExecutionContext dbcExecutionContext5 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext5.getDataSource()).thenReturn(dbpDataSource5);

    DBPContextProvider contextProvider5 = mock(DBPContextProvider.class);
    when(contextProvider5.getExecutionContext()).thenReturn(dbcExecutionContext5);
    Path sourceFile5 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    new SQLScriptContext(
        null, contextProvider5, sourceFile5, new StringWriter(), mock(SQLParametersProvider.class));
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DBPDataSourceContainer dbpDataSourceContainer6 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer6.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource6 = mock(DBPDataSource.class);
    when(dbpDataSource6.getContainer()).thenReturn(dbpDataSourceContainer6);

    DBCExecutionContext dbcExecutionContext6 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext6.getDataSource()).thenReturn(dbpDataSource6);

    DBPContextProvider contextProvider6 = mock(DBPContextProvider.class);
    when(contextProvider6.getExecutionContext()).thenReturn(dbcExecutionContext6);
    Path sourceFile6 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    OutputWriterAdapter outputWriter3 =
        new OutputWriterAdapter(new PrintWriter(new StringWriter()));

    new SQLScriptContext(
        null, contextProvider6, sourceFile6, outputWriter3, mock(SQLParametersProvider.class));
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DBPDataSourceContainer dbpDataSourceContainer7 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer7.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource7 = mock(DBPDataSource.class);
    when(dbpDataSource7.getContainer()).thenReturn(dbpDataSourceContainer7);

    DBCExecutionContext dbcExecutionContext7 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext7.getDataSource()).thenReturn(dbpDataSource7);

    DBPContextProvider contextProvider7 = mock(DBPContextProvider.class);
    when(contextProvider7.getExecutionContext()).thenReturn(dbcExecutionContext7);
    Path sourceFile7 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    new SQLScriptContext(
        null, contextProvider7, sourceFile7, new StringWriter(), mock(SQLParametersProvider.class));
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DBPDataSourceContainer dbpDataSourceContainer8 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer8.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource8 = mock(DBPDataSource.class);
    when(dbpDataSource8.getContainer()).thenReturn(dbpDataSourceContainer8);

    DBCExecutionContext dbcExecutionContext8 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext8.getDataSource()).thenReturn(dbpDataSource8);

    DBPContextProvider contextProvider8 = mock(DBPContextProvider.class);
    when(contextProvider8.getExecutionContext()).thenReturn(dbcExecutionContext8);
    Path sourceFile8 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    OutputWriterAdapter outputWriter4 =
        new OutputWriterAdapter(new PrintWriter(new StringWriter()));

    new SQLScriptContext(
        null, contextProvider8, sourceFile8, outputWriter4, mock(SQLParametersProvider.class));
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DBPDataSourceContainer dbpDataSourceContainer9 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer9.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource9 = mock(DBPDataSource.class);
    when(dbpDataSource9.getContainer()).thenReturn(dbpDataSourceContainer9);

    DBCExecutionContext dbcExecutionContext9 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext9.getDataSource()).thenReturn(dbpDataSource9);

    DBPContextProvider contextProvider9 = mock(DBPContextProvider.class);
    when(contextProvider9.getExecutionContext()).thenReturn(dbcExecutionContext9);
    Path sourceFile9 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    new SQLScriptContext(
        null, contextProvider9, sourceFile9, new StringWriter(), mock(SQLParametersProvider.class));
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DBPDataSourceContainer dbpDataSourceContainer10 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer10.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource10 = mock(DBPDataSource.class);
    when(dbpDataSource10.getContainer()).thenReturn(dbpDataSourceContainer10);

    DBCExecutionContext dbcExecutionContext10 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext10.getDataSource()).thenReturn(dbpDataSource10);

    DBPContextProvider contextProvider10 = mock(DBPContextProvider.class);
    when(contextProvider10.getExecutionContext()).thenReturn(dbcExecutionContext10);
    Path sourceFile10 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    OutputWriterAdapter outputWriter5 =
        new OutputWriterAdapter(new PrintWriter(new StringWriter()));

    new SQLScriptContext(
        null, contextProvider10, sourceFile10, outputWriter5, mock(SQLParametersProvider.class));
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DBPDataSourceContainer dbpDataSourceContainer11 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer11.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource11 = mock(DBPDataSource.class);
    when(dbpDataSource11.getContainer()).thenReturn(dbpDataSourceContainer11);

    DBCExecutionContext dbcExecutionContext11 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext11.getDataSource()).thenReturn(dbpDataSource11);

    DBPContextProvider contextProvider11 = mock(DBPContextProvider.class);
    when(contextProvider11.getExecutionContext()).thenReturn(dbcExecutionContext11);
    Path sourceFile11 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    new SQLScriptContext(
        null,
        contextProvider11,
        sourceFile11,
        new StringWriter(),
        mock(SQLParametersProvider.class));
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DBPDataSourceContainer dbpDataSourceContainer12 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer12.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource12 = mock(DBPDataSource.class);
    when(dbpDataSource12.getContainer()).thenReturn(dbpDataSourceContainer12);

    DBCExecutionContext dbcExecutionContext12 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext12.getDataSource()).thenReturn(dbpDataSource12);

    DBPContextProvider contextProvider12 = mock(DBPContextProvider.class);
    when(contextProvider12.getExecutionContext()).thenReturn(dbcExecutionContext12);
    Path sourceFile12 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    OutputWriterAdapter outputWriter6 =
        new OutputWriterAdapter(new PrintWriter(new StringWriter()));

    new SQLScriptContext(
        null, contextProvider12, sourceFile12, outputWriter6, mock(SQLParametersProvider.class));
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DBPDataSourceContainer dbpDataSourceContainer13 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer13.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource13 = mock(DBPDataSource.class);
    when(dbpDataSource13.getContainer()).thenReturn(dbpDataSourceContainer13);

    DBCExecutionContext dbcExecutionContext13 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext13.getDataSource()).thenReturn(dbpDataSource13);

    DBPContextProvider contextProvider13 = mock(DBPContextProvider.class);
    when(contextProvider13.getExecutionContext()).thenReturn(dbcExecutionContext13);
    Path sourceFile13 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    new SQLScriptContext(
        null,
        contextProvider13,
        sourceFile13,
        new StringWriter(),
        mock(SQLParametersProvider.class));
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DBPDataSourceContainer dbpDataSourceContainer14 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer14.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource14 = mock(DBPDataSource.class);
    when(dbpDataSource14.getContainer()).thenReturn(dbpDataSourceContainer14);

    DBCExecutionContext dbcExecutionContext14 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext14.getDataSource()).thenReturn(dbpDataSource14);

    DBPContextProvider contextProvider14 = mock(DBPContextProvider.class);
    when(contextProvider14.getExecutionContext()).thenReturn(dbcExecutionContext14);
    Path sourceFile14 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    OutputWriterAdapter outputWriter7 =
        new OutputWriterAdapter(new PrintWriter(new StringWriter()));

    new SQLScriptContext(
        null, contextProvider14, sourceFile14, outputWriter7, mock(SQLParametersProvider.class));
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DBPDataSourceContainer dbpDataSourceContainer15 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer15.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource15 = mock(DBPDataSource.class);
    when(dbpDataSource15.getContainer()).thenReturn(dbpDataSourceContainer15);

    DBCExecutionContext dbcExecutionContext15 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext15.getDataSource()).thenReturn(dbpDataSource15);

    DBPContextProvider contextProvider15 = mock(DBPContextProvider.class);
    when(contextProvider15.getExecutionContext()).thenReturn(dbcExecutionContext15);
    Path sourceFile15 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    new SQLScriptContext(
        null,
        contextProvider15,
        sourceFile15,
        new StringWriter(),
        mock(SQLParametersProvider.class));
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getUnquotedIdentifier(Mockito.<String>any(), anyBoolean())).thenReturn("42");
    when(sqlDialect.isQuotedIdentifier(Mockito.<String>any())).thenReturn(true);
    when(sqlDialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});

    DBPDataSource dbpDataSource16 = mock(DBPDataSource.class);
    when(dbpDataSource16.getSQLDialect()).thenReturn(sqlDialect);

    DBCExecutionContext dbcExecutionContext16 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext16.getDataSource()).thenReturn(dbpDataSource16);

    SQLScriptContext scriptContext = mock(SQLScriptContext.class);
    doNothing().when(scriptContext).removeVariable(Mockito.<String>any());
    when(scriptContext.getExecutionContext()).thenReturn(dbcExecutionContext16);

    // Act
    SQLControlResult actualHandleCommandResult =
        sqlCommandUnset.handleCommand(monitor, command, scriptContext);

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(contextProvider2).getExecutionContext();
    verify(contextProvider3).getExecutionContext();
    verify(contextProvider4).getExecutionContext();
    verify(contextProvider5).getExecutionContext();
    verify(contextProvider6).getExecutionContext();
    verify(contextProvider7).getExecutionContext();
    verify(contextProvider8).getExecutionContext();
    verify(contextProvider9).getExecutionContext();
    verify(contextProvider10).getExecutionContext();
    verify(contextProvider11).getExecutionContext();
    verify(contextProvider12).getExecutionContext();
    verify(contextProvider13).getExecutionContext();
    verify(contextProvider14).getExecutionContext();
    verify(contextProvider15).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSource3).getContainer();
    verify(dbpDataSource4).getContainer();
    verify(dbpDataSource5).getContainer();
    verify(dbpDataSource6).getContainer();
    verify(dbpDataSource7).getContainer();
    verify(dbpDataSource8).getContainer();
    verify(dbpDataSource9).getContainer();
    verify(dbpDataSource10).getContainer();
    verify(dbpDataSource11).getContainer();
    verify(dbpDataSource12).getContainer();
    verify(dbpDataSource13).getContainer();
    verify(dbpDataSource14).getContainer();
    verify(dbpDataSource15).getContainer();
    verify(dbpDataSource16).getSQLDialect();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(dbpDataSourceContainer3).getConnectionConfiguration();
    verify(dbpDataSourceContainer4).getConnectionConfiguration();
    verify(dbpDataSourceContainer5).getConnectionConfiguration();
    verify(dbpDataSourceContainer6).getConnectionConfiguration();
    verify(dbpDataSourceContainer7).getConnectionConfiguration();
    verify(dbpDataSourceContainer8).getConnectionConfiguration();
    verify(dbpDataSourceContainer9).getConnectionConfiguration();
    verify(dbpDataSourceContainer10).getConnectionConfiguration();
    verify(dbpDataSourceContainer11).getConnectionConfiguration();
    verify(dbpDataSourceContainer12).getConnectionConfiguration();
    verify(dbpDataSourceContainer13).getConnectionConfiguration();
    verify(dbpDataSourceContainer14).getConnectionConfiguration();
    verify(dbpDataSourceContainer15).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(dbcExecutionContext2).getDataSource();
    verify(dbcExecutionContext3).getDataSource();
    verify(dbcExecutionContext4).getDataSource();
    verify(dbcExecutionContext5).getDataSource();
    verify(dbcExecutionContext6).getDataSource();
    verify(dbcExecutionContext7).getDataSource();
    verify(dbcExecutionContext8).getDataSource();
    verify(dbcExecutionContext9).getDataSource();
    verify(dbcExecutionContext10).getDataSource();
    verify(dbcExecutionContext11).getDataSource();
    verify(dbcExecutionContext12).getDataSource();
    verify(dbcExecutionContext13).getDataSource();
    verify(dbcExecutionContext14).getDataSource();
    verify(dbcExecutionContext15).getDataSource();
    verify(dbcExecutionContext16).getDataSource();
    verify(command).getParameter();
    verify(sqlDialect).getIdentifierQuoteStrings();
    verify(sqlDialect).getUnquotedIdentifier("Parameter", true);
    verify(sqlDialect).isQuotedIdentifier("Parameter");
    verify(scriptContext).getExecutionContext();
    verify(scriptContext).removeVariable("42");
    assertNull(actualHandleCommandResult.getTransformed());
  }
}

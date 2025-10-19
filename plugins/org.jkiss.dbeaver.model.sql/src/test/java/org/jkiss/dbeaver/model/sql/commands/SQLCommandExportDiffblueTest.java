package org.jkiss.dbeaver.model.sql.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.StringWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPContextProvider;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.connection.DBPConnectionConfiguration;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.jkiss.dbeaver.model.impl.OutputWriterAdapter;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.DefaultProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.sql.SQLControlCommand;
import org.jkiss.dbeaver.model.sql.SQLControlResult;
import org.jkiss.dbeaver.model.sql.SQLParametersProvider;
import org.jkiss.dbeaver.model.sql.SQLScriptContext;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLCommandExportDiffblueTest {
  /**
   * Test {@link SQLCommandExport#handleCommand(DBRProgressMonitor, SQLControlCommand,
   * SQLScriptContext)}.
   *
   * <p>Method under test: {@link SQLCommandExport#handleCommand(DBRProgressMonitor,
   * SQLControlCommand, SQLScriptContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLControlResult SQLCommandExport.handleCommand(DBRProgressMonitor, SQLControlCommand, SQLScriptContext)"
  })
  public void testHandleCommand() throws DBException {
    // Arrange
    SQLCommandExport sqlCommandExport = new SQLCommandExport();
    DefaultProgressMonitor monitor = new DefaultProgressMonitor(new NullProgressMonitor());

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

    SQLScriptContext parentContext =
        new SQLScriptContext(
            null,
            contextProvider,
            sourceFile,
            new OutputWriterAdapter(null),
            mock(SQLParametersProvider.class));

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

    SQLScriptContext scriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider2,
            sourceFile2,
            new StringWriter(),
            mock(SQLParametersProvider.class));

    // Act and Assert
    assertThrows(
        DBException.class, () -> sqlCommandExport.handleCommand(monitor, command, scriptContext));
    verify(contextProvider).getExecutionContext();
    verify(contextProvider2).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(dbcExecutionContext2).getDataSource();
    verify(command).getParameter();
  }

  /**
   * Test {@link SQLCommandExport#handleCommand(DBRProgressMonitor, SQLControlCommand,
   * SQLScriptContext)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link SQLControlCommand} {@link SQLControlCommand#getParameter()} return {@code
   *       42}.
   * </ul>
   *
   * <p>Method under test: {@link SQLCommandExport#handleCommand(DBRProgressMonitor,
   * SQLControlCommand, SQLScriptContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLControlResult SQLCommandExport.handleCommand(DBRProgressMonitor, SQLControlCommand, SQLScriptContext)"
  })
  public void testHandleCommand_given42_whenSQLControlCommandGetParameterReturn42()
      throws DBException {
    // Arrange
    SQLCommandExport sqlCommandExport = new SQLCommandExport();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    SQLControlCommand command = mock(SQLControlCommand.class);
    when(command.getParameter()).thenReturn("42");

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

    SQLScriptContext parentContext =
        new SQLScriptContext(
            null,
            contextProvider,
            sourceFile,
            new OutputWriterAdapter(null),
            mock(SQLParametersProvider.class));

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

    SQLScriptContext scriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider2,
            sourceFile2,
            new StringWriter(),
            mock(SQLParametersProvider.class));

    // Act and Assert
    assertThrows(
        DBException.class, () -> sqlCommandExport.handleCommand(monitor, command, scriptContext));
    verify(contextProvider).getExecutionContext();
    verify(contextProvider2).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(dbcExecutionContext2).getDataSource();
    verify(command).getParameter();
  }

  /**
   * Test {@link SQLCommandExport#handleCommand(DBRProgressMonitor, SQLControlCommand,
   * SQLScriptContext)}.
   *
   * <ul>
   *   <li>Given empty string.
   *   <li>Then return Transformed is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLCommandExport#handleCommand(DBRProgressMonitor,
   * SQLControlCommand, SQLScriptContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLControlResult SQLCommandExport.handleCommand(DBRProgressMonitor, SQLControlCommand, SQLScriptContext)"
  })
  public void testHandleCommand_givenEmptyString_thenReturnTransformedIsNull() throws DBException {
    // Arrange
    SQLCommandExport sqlCommandExport = new SQLCommandExport();
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

    SQLScriptContext parentContext =
        new SQLScriptContext(
            null,
            contextProvider,
            sourceFile,
            new OutputWriterAdapter(null),
            mock(SQLParametersProvider.class));

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

    SQLScriptContext scriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider2,
            sourceFile2,
            new StringWriter(),
            mock(SQLParametersProvider.class));

    // Act
    SQLControlResult actualHandleCommandResult =
        sqlCommandExport.handleCommand(monitor, command, scriptContext);

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(contextProvider2).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(dbcExecutionContext2).getDataSource();
    verify(command).getParameter();
    assertNull(actualHandleCommandResult.getTransformed());
    Map<String, Map<String, Object>> pragmas = scriptContext.getPragmas();
    assertEquals(1, pragmas.size());
    assertTrue(pragmas.get("export").isEmpty());
  }

  /**
   * Test {@link SQLCommandExport#handleCommand(DBRProgressMonitor, SQLControlCommand,
   * SQLScriptContext)}.
   *
   * <ul>
   *   <li>Given {@code foo}.
   *   <li>When {@link SQLControlCommand} {@link SQLControlCommand#getParameter()} return {@code
   *       foo}.
   * </ul>
   *
   * <p>Method under test: {@link SQLCommandExport#handleCommand(DBRProgressMonitor,
   * SQLControlCommand, SQLScriptContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLControlResult SQLCommandExport.handleCommand(DBRProgressMonitor, SQLControlCommand, SQLScriptContext)"
  })
  public void testHandleCommand_givenFoo_whenSQLControlCommandGetParameterReturnFoo()
      throws DBException {
    // Arrange
    SQLCommandExport sqlCommandExport = new SQLCommandExport();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    SQLControlCommand command = mock(SQLControlCommand.class);
    when(command.getParameter()).thenReturn("foo");

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

    SQLScriptContext parentContext =
        new SQLScriptContext(
            null,
            contextProvider,
            sourceFile,
            new OutputWriterAdapter(null),
            mock(SQLParametersProvider.class));

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

    SQLScriptContext scriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider2,
            sourceFile2,
            new StringWriter(),
            mock(SQLParametersProvider.class));

    // Act and Assert
    assertThrows(
        DBException.class, () -> sqlCommandExport.handleCommand(monitor, command, scriptContext));
    verify(contextProvider).getExecutionContext();
    verify(contextProvider2).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(dbcExecutionContext2).getDataSource();
    verify(command).getParameter();
  }

  /**
   * Test {@link SQLCommandExport#handleCommand(DBRProgressMonitor, SQLControlCommand,
   * SQLScriptContext)}.
   *
   * <ul>
   *   <li>Given {@code Parameter}.
   * </ul>
   *
   * <p>Method under test: {@link SQLCommandExport#handleCommand(DBRProgressMonitor,
   * SQLControlCommand, SQLScriptContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLControlResult SQLCommandExport.handleCommand(DBRProgressMonitor, SQLControlCommand, SQLScriptContext)"
  })
  public void testHandleCommand_givenParameter() throws DBException {
    // Arrange
    SQLCommandExport sqlCommandExport = new SQLCommandExport();
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

    SQLScriptContext parentContext =
        new SQLScriptContext(
            null,
            contextProvider,
            sourceFile,
            new OutputWriterAdapter(null),
            mock(SQLParametersProvider.class));

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

    SQLScriptContext scriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider2,
            sourceFile2,
            new StringWriter(),
            mock(SQLParametersProvider.class));

    // Act and Assert
    assertThrows(
        DBException.class, () -> sqlCommandExport.handleCommand(monitor, command, scriptContext));
    verify(contextProvider).getExecutionContext();
    verify(contextProvider2).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(dbcExecutionContext2).getDataSource();
    verify(command).getParameter();
  }
}

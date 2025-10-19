package org.jkiss.dbeaver.model.sql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import org.jkiss.dbeaver.model.DBPContextProvider;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.connection.DBPConnectionConfiguration;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.jkiss.dbeaver.model.exec.DBCScriptContext;
import org.jkiss.dbeaver.model.exec.DBCScriptContext.VariableInfo;
import org.jkiss.dbeaver.model.exec.DBCScriptContext.VariableType;
import org.jkiss.dbeaver.model.exec.DBCScriptContextListener;
import org.jkiss.dbeaver.model.exec.output.DBCOutputWriter;
import org.jkiss.dbeaver.model.impl.OutputWriterAdapter;
import org.jkiss.dbeaver.model.impl.edit.TestCommandContext;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class SQLScriptContextDiffblueTest {
  /**
   * Test {@link SQLScriptContext#SQLScriptContext(SQLScriptContext, DBPContextProvider, Path,
   * Writer, SQLParametersProvider)}.
   *
   * <p>Method under test: {@link SQLScriptContext#SQLScriptContext(SQLScriptContext,
   * DBPContextProvider, Path, Writer, SQLParametersProvider)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLScriptContext.<init>(SQLScriptContext, DBPContextProvider, Path, Writer, SQLParametersProvider)"
  })
  public void testNewSQLScriptContext() {
    // Arrange
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

    // Act
    SQLScriptContext actualSqlScriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider2,
            sourceFile2,
            new StringWriter(),
            mock(SQLParametersProvider.class));

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(contextProvider2).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(dbcExecutionContext2).getDataSource();
    assertSame(parentContext, actualSqlScriptContext.getParentContext());
  }

  /**
   * Test {@link SQLScriptContext#SQLScriptContext(SQLScriptContext, DBPContextProvider, Path,
   * Writer, SQLParametersProvider)}.
   *
   * <p>Method under test: {@link SQLScriptContext#SQLScriptContext(SQLScriptContext,
   * DBPContextProvider, Path, Writer, SQLParametersProvider)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLScriptContext.<init>(SQLScriptContext, DBPContextProvider, Path, Writer, SQLParametersProvider)"
  })
  public void testNewSQLScriptContext2() {
    // Arrange
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

    SQLScriptContext parentContext2 =
        new SQLScriptContext(
            parentContext,
            contextProvider2,
            sourceFile2,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    TestCommandContext contextProvider3 = new TestCommandContext(null, true);
    Path sourceFile3 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act
    SQLScriptContext actualSqlScriptContext =
        new SQLScriptContext(
            parentContext2,
            contextProvider3,
            sourceFile3,
            new StringWriter(),
            mock(SQLParametersProvider.class));

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(contextProvider2).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(dbcExecutionContext2).getDataSource();
    assertSame(parentContext2, actualSqlScriptContext.getParentContext());
  }

  /**
   * Test {@link SQLScriptContext#SQLScriptContext(SQLScriptContext, DBPContextProvider, Path,
   * DBCOutputWriter, SQLParametersProvider)}.
   *
   * <p>Method under test: {@link SQLScriptContext#SQLScriptContext(SQLScriptContext,
   * DBPContextProvider, Path, DBCOutputWriter, SQLParametersProvider)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLScriptContext.<init>(SQLScriptContext, DBPContextProvider, Path, DBCOutputWriter, SQLParametersProvider)"
  })
  public void testNewSQLScriptContext3() {
    // Arrange
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

    SQLScriptContext parentContext2 =
        new SQLScriptContext(
            parentContext,
            contextProvider2,
            sourceFile2,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    TestCommandContext contextProvider3 = new TestCommandContext(null, true);
    Path sourceFile3 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    OutputWriterAdapter outputWriter = new OutputWriterAdapter(new PrintWriter(new StringWriter()));

    // Act
    SQLScriptContext actualSqlScriptContext =
        new SQLScriptContext(
            parentContext2,
            contextProvider3,
            sourceFile3,
            outputWriter,
            mock(SQLParametersProvider.class));

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(contextProvider2).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(dbcExecutionContext2).getDataSource();
    assertSame(parentContext2, actualSqlScriptContext.getParentContext());
  }

  /**
   * Test {@link SQLScriptContext#SQLScriptContext(SQLScriptContext, DBPContextProvider, Path,
   * Writer, SQLParametersProvider)}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSource} {@link DBPDataSource#getContainer()} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptContext#SQLScriptContext(SQLScriptContext,
   * DBPContextProvider, Path, Writer, SQLParametersProvider)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLScriptContext.<init>(SQLScriptContext, DBPContextProvider, Path, Writer, SQLParametersProvider)"
  })
  public void testNewSQLScriptContext_givenDBPDataSourceGetContainerReturnNull() {
    // Arrange
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

    SQLScriptContext parentContext2 =
        new SQLScriptContext(
            parentContext,
            contextProvider2,
            sourceFile2,
            new StringWriter(),
            mock(SQLParametersProvider.class));

    DBPDataSource dbpDataSource3 = mock(DBPDataSource.class);
    when(dbpDataSource3.getContainer()).thenReturn(null);

    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.getDataSource()).thenReturn(dbpDataSource3);
    TestCommandContext contextProvider3 = new TestCommandContext(executionContext, true);
    Path sourceFile3 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act
    SQLScriptContext actualSqlScriptContext =
        new SQLScriptContext(
            parentContext2,
            contextProvider3,
            sourceFile3,
            new StringWriter(),
            mock(SQLParametersProvider.class));

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(contextProvider2).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSource3).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(dbcExecutionContext2).getDataSource();
    verify(executionContext).getDataSource();
    assertSame(parentContext2, actualSqlScriptContext.getParentContext());
  }

  /**
   * Test {@link SQLScriptContext#SQLScriptContext(SQLScriptContext, DBPContextProvider, Path,
   * DBCOutputWriter, SQLParametersProvider)}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSource} {@link DBPDataSource#getContainer()} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptContext#SQLScriptContext(SQLScriptContext,
   * DBPContextProvider, Path, DBCOutputWriter, SQLParametersProvider)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLScriptContext.<init>(SQLScriptContext, DBPContextProvider, Path, DBCOutputWriter, SQLParametersProvider)"
  })
  public void testNewSQLScriptContext_givenDBPDataSourceGetContainerReturnNull2() {
    // Arrange
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

    SQLScriptContext parentContext2 =
        new SQLScriptContext(
            parentContext,
            contextProvider2,
            sourceFile2,
            new StringWriter(),
            mock(SQLParametersProvider.class));

    DBPDataSource dbpDataSource3 = mock(DBPDataSource.class);
    when(dbpDataSource3.getContainer()).thenReturn(null);

    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.getDataSource()).thenReturn(dbpDataSource3);
    TestCommandContext contextProvider3 = new TestCommandContext(executionContext, true);
    Path sourceFile3 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    OutputWriterAdapter outputWriter = new OutputWriterAdapter(new PrintWriter(new StringWriter()));

    // Act
    SQLScriptContext actualSqlScriptContext =
        new SQLScriptContext(
            parentContext2,
            contextProvider3,
            sourceFile3,
            outputWriter,
            mock(SQLParametersProvider.class));

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(contextProvider2).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSource3).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(dbcExecutionContext2).getDataSource();
    verify(executionContext).getDataSource();
    assertSame(parentContext2, actualSqlScriptContext.getParentContext());
  }

  /**
   * Test {@link SQLScriptContext#SQLScriptContext(SQLScriptContext, DBPContextProvider, Path,
   * DBCOutputWriter, SQLParametersProvider)}.
   *
   * <ul>
   *   <li>Then OutputWriter return {@link OutputWriterAdapter}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptContext#SQLScriptContext(SQLScriptContext,
   * DBPContextProvider, Path, DBCOutputWriter, SQLParametersProvider)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLScriptContext.<init>(SQLScriptContext, DBPContextProvider, Path, DBCOutputWriter, SQLParametersProvider)"
  })
  public void testNewSQLScriptContext_thenOutputWriterReturnOutputWriterAdapter() {
    // Arrange
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
            new StringWriter(),
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
    OutputWriterAdapter outputWriter = new OutputWriterAdapter(new PrintWriter(new StringWriter()));

    // Act
    SQLScriptContext actualSqlScriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider2,
            sourceFile2,
            outputWriter,
            mock(SQLParametersProvider.class));

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(contextProvider2).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(dbcExecutionContext2).getDataSource();
    DBCOutputWriter outputWriter2 = actualSqlScriptContext.getOutputWriter();
    assertTrue(outputWriter2 instanceof OutputWriterAdapter);
    assertFalse(actualSqlScriptContext.isIgnoreParameters());
    assertTrue(actualSqlScriptContext.getVariables().isEmpty());
    assertTrue(actualSqlScriptContext.getAllParameters().isEmpty());
    assertTrue(actualSqlScriptContext.getPragmas().isEmpty());
    assertSame(outputWriter, outputWriter2);
    assertSame(parentContext, actualSqlScriptContext.getParentContext());
    assertSame(sourceFile2, actualSqlScriptContext.getSourceFile());
  }

  /**
   * Test {@link SQLScriptContext#getExecutionContext()}.
   *
   * <p>Method under test: {@link SQLScriptContext#getExecutionContext()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCExecutionContext SQLScriptContext.getExecutionContext()"})
  public void testGetExecutionContext() {
    // Arrange
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

    SQLScriptContext sqlScriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider2,
            sourceFile2,
            new StringWriter(),
            mock(SQLParametersProvider.class));

    // Act
    sqlScriptContext.getExecutionContext();

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(contextProvider2, atLeast(1)).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(dbcExecutionContext2).getDataSource();
  }

  /**
   * Test {@link SQLScriptContext#hasDefaultParameterValue(String)}.
   *
   * <p>Method under test: {@link SQLScriptContext#hasDefaultParameterValue(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLScriptContext.hasDefaultParameterValue(String)"})
  public void testHasDefaultParameterValue() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getUnquotedIdentifier(Mockito.<String>any())).thenReturn("42");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

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

    SQLDialect sqlDialect2 = mock(SQLDialect.class);
    when(sqlDialect2.getUnquotedIdentifier(Mockito.<String>any())).thenReturn("42");

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getContainer()).thenReturn(dbpDataSourceContainer2);
    when(dbpDataSource2.getSQLDialect()).thenReturn(sqlDialect2);

    DBCExecutionContext dbcExecutionContext2 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext2.getDataSource()).thenReturn(dbpDataSource2);

    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    when(contextProvider2.getExecutionContext()).thenReturn(dbcExecutionContext2);
    Path sourceFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext sqlScriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider2,
            sourceFile2,
            new StringWriter(),
            mock(SQLParametersProvider.class));

    // Act
    boolean actualHasDefaultParameterValueResult =
        sqlScriptContext.hasDefaultParameterValue("Name");

    // Assert
    verify(contextProvider, atLeast(1)).getExecutionContext();
    verify(contextProvider2, atLeast(1)).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSource).getSQLDialect();
    verify(dbpDataSource2).getSQLDialect();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(dbcExecutionContext, atLeast(1)).getDataSource();
    verify(dbcExecutionContext2, atLeast(1)).getDataSource();
    verify(sqlDialect).getUnquotedIdentifier("Name");
    verify(sqlDialect2).getUnquotedIdentifier("Name");
    assertFalse(actualHasDefaultParameterValueResult);
  }

  /**
   * Test {@link SQLScriptContext#setVariable(String, Object)}.
   *
   * <p>Method under test: {@link SQLScriptContext#setVariable(String, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLScriptContext.setVariable(String, Object)"})
  public void testSetVariable() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getUnquotedIdentifier(Mockito.<String>any())).thenReturn("42");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

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

    SQLDialect sqlDialect2 = mock(SQLDialect.class);
    when(sqlDialect2.getUnquotedIdentifier(Mockito.<String>any())).thenReturn("42");

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getContainer()).thenReturn(dbpDataSourceContainer2);
    when(dbpDataSource2.getSQLDialect()).thenReturn(sqlDialect2);

    DBCExecutionContext dbcExecutionContext2 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext2.getDataSource()).thenReturn(dbpDataSource2);

    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    when(contextProvider2.getExecutionContext()).thenReturn(dbcExecutionContext2);
    Path sourceFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext sqlScriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider2,
            sourceFile2,
            new StringWriter(),
            mock(SQLParametersProvider.class));

    // Act
    sqlScriptContext.setVariable("Name", "Value");

    // Assert
    verify(contextProvider, atLeast(1)).getExecutionContext();
    verify(contextProvider2, atLeast(1)).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSource).getSQLDialect();
    verify(dbpDataSource2).getSQLDialect();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(dbcExecutionContext, atLeast(1)).getDataSource();
    verify(dbcExecutionContext2, atLeast(1)).getDataSource();
    verify(sqlDialect).getUnquotedIdentifier("Name");
    verify(sqlDialect2).getUnquotedIdentifier("Name");
    List<VariableInfo> variables = sqlScriptContext.getVariables();
    assertEquals(1, variables.size());
    VariableInfo getResult = variables.get(0);
    assertEquals("Name", getResult.name);
    List<VariableInfo> variables2 = sqlScriptContext.getParentContext().getVariables();
    assertEquals(1, variables2.size());
    VariableInfo getResult2 = variables2.get(0);
    assertEquals("Name", getResult2.name);
    Map<String, Object> allParameters = sqlScriptContext.getAllParameters();
    assertEquals(1, allParameters.size());
    assertEquals("Value", allParameters.get("Name"));
    assertEquals("Value", getResult.value);
    assertEquals("Value", getResult2.value);
    assertEquals(VariableType.VARIABLE, getResult.type);
    assertEquals(VariableType.VARIABLE, getResult2.type);
  }

  /**
   * Test {@link SQLScriptContext#removeVariable(String)}.
   *
   * <p>Method under test: {@link SQLScriptContext#removeVariable(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLScriptContext.removeVariable(String)"})
  public void testRemoveVariable() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getUnquotedIdentifier(Mockito.<String>any())).thenReturn("42");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

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

    SQLDialect sqlDialect2 = mock(SQLDialect.class);
    when(sqlDialect2.getUnquotedIdentifier(Mockito.<String>any())).thenReturn("42");

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getContainer()).thenReturn(dbpDataSourceContainer2);
    when(dbpDataSource2.getSQLDialect()).thenReturn(sqlDialect2);

    DBCExecutionContext dbcExecutionContext2 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext2.getDataSource()).thenReturn(dbpDataSource2);

    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    when(contextProvider2.getExecutionContext()).thenReturn(dbcExecutionContext2);
    Path sourceFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext sqlScriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider2,
            sourceFile2,
            new StringWriter(),
            mock(SQLParametersProvider.class));

    // Act
    sqlScriptContext.removeVariable("Name");

    // Assert
    verify(contextProvider, atLeast(1)).getExecutionContext();
    verify(contextProvider2, atLeast(1)).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSource).getSQLDialect();
    verify(dbpDataSource2).getSQLDialect();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(dbcExecutionContext, atLeast(1)).getDataSource();
    verify(dbcExecutionContext2, atLeast(1)).getDataSource();
    verify(sqlDialect).getUnquotedIdentifier("Name");
    verify(sqlDialect2).getUnquotedIdentifier("Name");
  }

  /**
   * Test {@link SQLScriptContext#getVariables()}.
   *
   * <p>Method under test: {@link SQLScriptContext#getVariables()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List SQLScriptContext.getVariables()"})
  public void testGetVariables() {
    // Arrange
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

    SQLScriptContext sqlScriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider2,
            sourceFile2,
            new StringWriter(),
            mock(SQLParametersProvider.class));

    // Act
    List<VariableInfo> actualVariables = sqlScriptContext.getVariables();

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(contextProvider2).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(dbcExecutionContext2).getDataSource();
    assertTrue(actualVariables.isEmpty());
  }

  /**
   * Test {@link SQLScriptContext#setVariables(Map)}.
   *
   * <p>Method under test: {@link SQLScriptContext#setVariables(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLScriptContext.setVariables(Map)"})
  public void testSetVariables() {
    // Arrange
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

    SQLScriptContext sqlScriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider2,
            sourceFile2,
            new StringWriter(),
            mock(SQLParametersProvider.class));

    // Act
    sqlScriptContext.setVariables(new HashMap<>());

    // Assert that nothing has changed
    verify(contextProvider).getExecutionContext();
    verify(contextProvider2).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(dbcExecutionContext2).getDataSource();
    assertTrue(sqlScriptContext.getVariables().isEmpty());
    SQLScriptContext parentContext2 = sqlScriptContext.getParentContext();
    assertTrue(parentContext2.getVariables().isEmpty());
    assertTrue(sqlScriptContext.getAllParameters().isEmpty());
    assertTrue(parentContext2.getAllParameters().isEmpty());
  }

  /**
   * Test {@link SQLScriptContext#setVariables(Map)}.
   *
   * <p>Method under test: {@link SQLScriptContext#setVariables(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLScriptContext.setVariables(Map)"})
  public void testSetVariables2() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getUnquotedIdentifier(Mockito.<String>any())).thenReturn("42");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);
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

    SQLDialect sqlDialect2 = mock(SQLDialect.class);
    when(sqlDialect2.getUnquotedIdentifier(Mockito.<String>any())).thenReturn("42");

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getSQLDialect()).thenReturn(sqlDialect2);
    when(dbpDataSource2.getContainer()).thenReturn(dbpDataSourceContainer2);

    DBCExecutionContext dbcExecutionContext2 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext2.getDataSource()).thenReturn(dbpDataSource2);

    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    when(contextProvider2.getExecutionContext()).thenReturn(dbcExecutionContext2);
    Path sourceFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext sqlScriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider2,
            sourceFile2,
            new StringWriter(),
            mock(SQLParametersProvider.class));

    HashMap<String, Object> variables = new HashMap<>();
    variables.put("foo", "42");

    // Act
    sqlScriptContext.setVariables(variables);

    // Assert
    verify(contextProvider, atLeast(1)).getExecutionContext();
    verify(contextProvider2, atLeast(1)).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSource).getSQLDialect();
    verify(dbpDataSource2).getSQLDialect();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(dbcExecutionContext, atLeast(1)).getDataSource();
    verify(dbcExecutionContext2, atLeast(1)).getDataSource();
    verify(sqlDialect).getUnquotedIdentifier("foo");
    verify(sqlDialect2).getUnquotedIdentifier("foo");
    List<VariableInfo> variables2 = sqlScriptContext.getVariables();
    assertEquals(1, variables2.size());
    VariableInfo getResult = variables2.get(0);
    assertEquals("42", getResult.value);
    SQLScriptContext parentContext2 = sqlScriptContext.getParentContext();
    List<VariableInfo> variables3 = parentContext2.getVariables();
    assertEquals(1, variables3.size());
    VariableInfo getResult2 = variables3.get(0);
    assertEquals("42", getResult2.value);
    assertEquals("foo", getResult.name);
    assertEquals("foo", getResult2.name);
    assertEquals(VariableType.VARIABLE, getResult.type);
    assertEquals(VariableType.VARIABLE, getResult2.type);
    assertEquals(variables, sqlScriptContext.getAllParameters());
    assertEquals(variables, parentContext2.getAllParameters());
  }

  /**
   * Test {@link SQLScriptContext#getParameterDefaultValue(String)}.
   *
   * <p>Method under test: {@link SQLScriptContext#getParameterDefaultValue(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object SQLScriptContext.getParameterDefaultValue(String)"})
  public void testGetParameterDefaultValue() {
    // Arrange
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

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getUnquotedIdentifier(Mockito.<String>any())).thenReturn("42");

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getContainer()).thenReturn(dbpDataSourceContainer2);
    when(dbpDataSource2.getSQLDialect()).thenReturn(sqlDialect);

    DBCExecutionContext dbcExecutionContext2 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext2.getDataSource()).thenReturn(dbpDataSource2);

    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    when(contextProvider2.getExecutionContext()).thenReturn(dbcExecutionContext2);
    Path sourceFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext sqlScriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider2,
            sourceFile2,
            new StringWriter(),
            mock(SQLParametersProvider.class));

    // Act
    Object actualParameterDefaultValue = sqlScriptContext.getParameterDefaultValue("Name");

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(contextProvider2, atLeast(1)).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSource2).getSQLDialect();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(dbcExecutionContext2, atLeast(1)).getDataSource();
    verify(sqlDialect).getUnquotedIdentifier("Name");
    assertNull(actualParameterDefaultValue);
  }

  /**
   * Test {@link SQLScriptContext#setParameterDefaultValue(String, Object)}.
   *
   * <p>Method under test: {@link SQLScriptContext#setParameterDefaultValue(String, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLScriptContext.setParameterDefaultValue(String, Object)"})
  public void testSetParameterDefaultValue() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getUnquotedIdentifier(Mockito.<String>any())).thenReturn("42");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

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

    SQLDialect sqlDialect2 = mock(SQLDialect.class);
    when(sqlDialect2.getUnquotedIdentifier(Mockito.<String>any())).thenReturn("42");

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getContainer()).thenReturn(dbpDataSourceContainer2);
    when(dbpDataSource2.getSQLDialect()).thenReturn(sqlDialect2);

    DBCExecutionContext dbcExecutionContext2 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext2.getDataSource()).thenReturn(dbpDataSource2);

    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    when(contextProvider2.getExecutionContext()).thenReturn(dbcExecutionContext2);
    Path sourceFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext sqlScriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider2,
            sourceFile2,
            new StringWriter(),
            mock(SQLParametersProvider.class));

    // Act
    sqlScriptContext.setParameterDefaultValue("Name", "Value");

    // Assert
    verify(contextProvider, atLeast(1)).getExecutionContext();
    verify(contextProvider2, atLeast(1)).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSource).getSQLDialect();
    verify(dbpDataSource2).getSQLDialect();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(dbcExecutionContext, atLeast(1)).getDataSource();
    verify(dbcExecutionContext2, atLeast(1)).getDataSource();
    verify(sqlDialect).getUnquotedIdentifier("Name");
    verify(sqlDialect2).getUnquotedIdentifier("Name");
    Map<String, Object> allParameters = sqlScriptContext.getAllParameters();
    assertEquals(1, allParameters.size());
    assertEquals("Value", allParameters.get("42"));
  }

  /**
   * Test {@link SQLScriptContext#getPragmas()}.
   *
   * <p>Method under test: {@link SQLScriptContext#getPragmas()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map SQLScriptContext.getPragmas()"})
  public void testGetPragmas() {
    // Arrange
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

    SQLScriptContext sqlScriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider2,
            sourceFile2,
            new StringWriter(),
            mock(SQLParametersProvider.class));

    // Act
    Map<String, Map<String, Object>> actualPragmas = sqlScriptContext.getPragmas();

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(contextProvider2).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(dbcExecutionContext2).getDataSource();
    assertTrue(actualPragmas.isEmpty());
  }

  /**
   * Test {@link SQLScriptContext#setPragma(String, Map)}.
   *
   * <p>Method under test: {@link SQLScriptContext#setPragma(String, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLScriptContext.setPragma(String, Map)"})
  public void testSetPragma() {
    // Arrange
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

    SQLScriptContext sqlScriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider2,
            sourceFile2,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    HashMap<String, Object> params = new HashMap<>();

    // Act
    sqlScriptContext.setPragma("42", params);

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(contextProvider2).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(dbcExecutionContext2).getDataSource();
    Map<String, Map<String, Object>> pragmas = sqlScriptContext.getPragmas();
    assertEquals(1, pragmas.size());
    assertSame(params, pragmas.get("42"));
  }

  /**
   * Test {@link SQLScriptContext#getData(String)}.
   *
   * <p>Method under test: {@link SQLScriptContext#getData(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object SQLScriptContext.getData(String)"})
  public void testGetData() {
    // Arrange
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

    SQLScriptContext sqlScriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider2,
            sourceFile2,
            new StringWriter(),
            mock(SQLParametersProvider.class));

    // Act
    Object actualData = sqlScriptContext.getData("Key");

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(contextProvider2).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(dbcExecutionContext2).getDataSource();
    assertNull(actualData);
  }

  /**
   * Test {@link SQLScriptContext#setData(String, Object)}.
   *
   * <p>Method under test: {@link SQLScriptContext#setData(String, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLScriptContext.setData(String, Object)"})
  public void testSetData() {
    // Arrange
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

    SQLScriptContext sqlScriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider2,
            sourceFile2,
            new StringWriter(),
            mock(SQLParametersProvider.class));

    // Act
    sqlScriptContext.setData("Key", "Value");

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(contextProvider2).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(dbcExecutionContext2).getDataSource();
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SQLScriptContext#setIgnoreParameters(boolean)}
   *   <li>{@link SQLScriptContext#getOutputWriter()}
   *   <li>{@link SQLScriptContext#getParentContext()}
   *   <li>{@link SQLScriptContext#getSourceFile()}
   *   <li>{@link SQLScriptContext#isIgnoreParameters()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBCOutputWriter SQLScriptContext.getOutputWriter()",
    "SQLScriptContext SQLScriptContext.getParentContext()",
    "Path SQLScriptContext.getSourceFile()",
    "boolean SQLScriptContext.isIgnoreParameters()",
    "void SQLScriptContext.setIgnoreParameters(boolean)"
  })
  public void testGettersAndSetters() {
    // Arrange
    DBPContextProvider contextProvider = mock(DBPContextProvider.class);
    Path sourceFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    OutputWriterAdapter outputWriter = new OutputWriterAdapter(new PrintWriter(new StringWriter()));

    SQLScriptContext parentContext =
        new SQLScriptContext(
            null, contextProvider, sourceFile, outputWriter, mock(SQLParametersProvider.class));
    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    Path sourceFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext sqlScriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider2,
            sourceFile2,
            new StringWriter(),
            mock(SQLParametersProvider.class));

    // Act
    sqlScriptContext.setIgnoreParameters(true);
    DBCOutputWriter actualOutputWriter = sqlScriptContext.getOutputWriter();
    SQLScriptContext actualParentContext = sqlScriptContext.getParentContext();
    Path actualSourceFile = sqlScriptContext.getSourceFile();

    // Assert
    assertTrue(actualOutputWriter instanceof OutputWriterAdapter);
    assertTrue(sqlScriptContext.isIgnoreParameters());
    assertSame(parentContext, actualParentContext);
    assertSame(sourceFile2, actualSourceFile);
  }

  /**
   * Test {@link SQLScriptContext#clearStatementContext()}.
   *
   * <p>Method under test: {@link SQLScriptContext#clearStatementContext()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLScriptContext.clearStatementContext()"})
  public void testClearStatementContext() {
    // Arrange
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

    SQLScriptContext sqlScriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider2,
            sourceFile2,
            new StringWriter(),
            mock(SQLParametersProvider.class));

    // Act
    sqlScriptContext.clearStatementContext();

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(contextProvider2).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(dbcExecutionContext2).getDataSource();
  }

  /**
   * Test {@link SQLScriptContext#copyFrom(SQLScriptContext)}.
   *
   * <ul>
   *   <li>Then calls {@link DBPContextProvider#getExecutionContext()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptContext#copyFrom(SQLScriptContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLScriptContext.copyFrom(SQLScriptContext)"})
  public void testCopyFrom_thenCallsGetExecutionContext() {
    // Arrange
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

    SQLScriptContext sqlScriptContext =
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

    SQLScriptContext parentContext2 =
        new SQLScriptContext(
            null,
            contextProvider3,
            sourceFile3,
            new OutputWriterAdapter(null),
            mock(SQLParametersProvider.class));

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

    SQLScriptContext context =
        new SQLScriptContext(
            parentContext2,
            contextProvider4,
            sourceFile4,
            new StringWriter(),
            mock(SQLParametersProvider.class));

    // Act
    sqlScriptContext.copyFrom(context);

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(contextProvider2).getExecutionContext();
    verify(contextProvider3).getExecutionContext();
    verify(contextProvider4).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSource3).getContainer();
    verify(dbpDataSource4).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(dbpDataSourceContainer3).getConnectionConfiguration();
    verify(dbpDataSourceContainer4).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(dbcExecutionContext2).getDataSource();
    verify(dbcExecutionContext3).getDataSource();
    verify(dbcExecutionContext4).getDataSource();
  }

  /**
   * Test {@link SQLScriptContext#fillQueryParameters(SQLQuery, Supplier, boolean)}.
   *
   * <p>Method under test: {@link SQLScriptContext#fillQueryParameters(SQLQuery, Supplier, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLScriptContext.fillQueryParameters(SQLQuery, Supplier, boolean)"})
  public void testFillQueryParameters() {
    // Arrange
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

    SQLScriptContext sqlScriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider2,
            sourceFile2,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    sqlScriptContext.setIgnoreParameters(true);

    // Act
    boolean actualFillQueryParametersResult =
        sqlScriptContext.fillQueryParameters(
            new SQLQuery(mock(DBPDataSource.class), "Text"), mock(Supplier.class), true);

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(contextProvider2).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(dbcExecutionContext2).getDataSource();
    assertTrue(actualFillQueryParametersResult);
  }

  /**
   * Test {@link SQLScriptContext#fillQueryParameters(SQLQuery, Supplier, boolean)}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptContext#fillQueryParameters(SQLQuery, Supplier, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLScriptContext.fillQueryParameters(SQLQuery, Supplier, boolean)"})
  public void testFillQueryParameters_givenArrayList() {
    // Arrange
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

    SQLScriptContext sqlScriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider2,
            sourceFile2,
            new StringWriter(),
            mock(SQLParametersProvider.class));

    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");
    query.setParameters(new ArrayList<>());

    // Act
    boolean actualFillQueryParametersResult =
        sqlScriptContext.fillQueryParameters(query, mock(Supplier.class), true);

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(contextProvider2).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(dbcExecutionContext2).getDataSource();
    assertTrue(actualFillQueryParametersResult);
  }

  /**
   * Test {@link SQLScriptContext#fillQueryParameters(SQLQuery, Supplier, boolean)}.
   *
   * <ul>
   *   <li>When {@link SQLQuery#SQLQuery(DBPDataSource, String)} with dataSource is {@link
   *       DBPDataSource} and {@code Text}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptContext#fillQueryParameters(SQLQuery, Supplier, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLScriptContext.fillQueryParameters(SQLQuery, Supplier, boolean)"})
  public void testFillQueryParameters_whenSQLQueryWithDataSourceIsDBPDataSourceAndText() {
    // Arrange
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

    SQLScriptContext sqlScriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider2,
            sourceFile2,
            new StringWriter(),
            mock(SQLParametersProvider.class));

    // Act
    boolean actualFillQueryParametersResult =
        sqlScriptContext.fillQueryParameters(
            new SQLQuery(mock(DBPDataSource.class), "Text"), mock(Supplier.class), true);

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(contextProvider2).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(dbcExecutionContext2).getDataSource();
    assertTrue(actualFillQueryParametersResult);
  }

  /**
   * Test {@link SQLScriptContext#getAllParameters()}.
   *
   * <ul>
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptContext#getAllParameters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map SQLScriptContext.getAllParameters()"})
  public void testGetAllParameters_thenReturnEmpty() {
    // Arrange
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

    SQLScriptContext sqlScriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider2,
            sourceFile2,
            new StringWriter(),
            mock(SQLParametersProvider.class));

    // Act
    Map<String, Object> actualAllParameters = sqlScriptContext.getAllParameters();

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(contextProvider2).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(dbcExecutionContext2).getDataSource();
    assertTrue(actualAllParameters.isEmpty());
  }

  /**
   * Test {@link SQLScriptContext#getAllParameters()}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptContext#getAllParameters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map SQLScriptContext.getAllParameters()"})
  public void testGetAllParameters_thenReturnSizeIsOne() {
    // Arrange
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

    new SQLScriptContext(
        null,
        contextProvider,
        sourceFile,
        new OutputWriterAdapter(null),
        mock(SQLParametersProvider.class));
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer2.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getUnquotedIdentifier(Mockito.<String>any())).thenReturn("42");

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getContainer()).thenReturn(dbpDataSourceContainer2);
    when(dbpDataSource2.getSQLDialect()).thenReturn(sqlDialect);

    DBCExecutionContext dbcExecutionContext2 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext2.getDataSource()).thenReturn(dbpDataSource2);

    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    when(contextProvider2.getExecutionContext()).thenReturn(dbcExecutionContext2);
    Path sourceFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext parentContext =
        new SQLScriptContext(
            null,
            contextProvider2,
            sourceFile2,
            new StringWriter(),
            mock(SQLParametersProvider.class));

    DBPDataSourceContainer dbpDataSourceContainer3 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer3.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    SQLDialect sqlDialect2 = mock(SQLDialect.class);
    when(sqlDialect2.getUnquotedIdentifier(Mockito.<String>any())).thenReturn("42");

    DBPDataSource dbpDataSource3 = mock(DBPDataSource.class);
    when(dbpDataSource3.getContainer()).thenReturn(dbpDataSourceContainer3);
    when(dbpDataSource3.getSQLDialect()).thenReturn(sqlDialect2);

    DBCExecutionContext dbcExecutionContext3 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext3.getDataSource()).thenReturn(dbpDataSource3);

    DBPContextProvider contextProvider3 = mock(DBPContextProvider.class);
    when(contextProvider3.getExecutionContext()).thenReturn(dbcExecutionContext3);
    Path sourceFile3 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    OutputWriterAdapter outputWriter = new OutputWriterAdapter(new PrintWriter(new StringWriter()));

    SQLScriptContext sqlScriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider3,
            sourceFile3,
            outputWriter,
            mock(SQLParametersProvider.class));
    sqlScriptContext.setVariable("Name", "Value");

    // Act
    Map<String, Object> actualAllParameters = sqlScriptContext.getAllParameters();

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(contextProvider2, atLeast(1)).getExecutionContext();
    verify(contextProvider3, atLeast(1)).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSource3).getContainer();
    verify(dbpDataSource2).getSQLDialect();
    verify(dbpDataSource3).getSQLDialect();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(dbpDataSourceContainer3).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(dbcExecutionContext2, atLeast(1)).getDataSource();
    verify(dbcExecutionContext3, atLeast(1)).getDataSource();
    verify(sqlDialect).getUnquotedIdentifier("Name");
    verify(sqlDialect2).getUnquotedIdentifier("Name");
    assertEquals(1, actualAllParameters.size());
    assertEquals("Value", actualAllParameters.get("Name"));
  }

  /**
   * Test {@link SQLScriptContext#clearVariables()}.
   *
   * <p>Method under test: {@link SQLScriptContext#clearVariables()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLScriptContext.clearVariables()"})
  public void testClearVariables() {
    // Arrange
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

    SQLScriptContext sqlScriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider2,
            sourceFile2,
            new StringWriter(),
            mock(SQLParametersProvider.class));

    // Act
    sqlScriptContext.clearVariables();

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(contextProvider2).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(dbcExecutionContext2).getDataSource();
  }

  /**
   * Test {@link SQLScriptContext#addListener(DBCScriptContextListener)}.
   *
   * <p>Method under test: {@link SQLScriptContext#addListener(DBCScriptContextListener)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLScriptContext.addListener(DBCScriptContextListener)"})
  public void testAddListener() {
    // Arrange
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

    SQLScriptContext sqlScriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider2,
            sourceFile2,
            new StringWriter(),
            mock(SQLParametersProvider.class));

    // Act
    sqlScriptContext.addListener(mock(DBCScriptContextListener.class));

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(contextProvider2).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(dbcExecutionContext2).getDataSource();
  }

  /**
   * Test {@link SQLScriptContext#addListener(DBCScriptContextListener)}.
   *
   * <p>Method under test: {@link SQLScriptContext#addListener(DBCScriptContextListener)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLScriptContext.addListener(DBCScriptContextListener)"})
  public void testAddListener2() {
    // Arrange
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

    SQLScriptContext sqlScriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider2,
            sourceFile2,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    sqlScriptContext.addListener(mock(DBCScriptContextListener.class));

    // Act
    sqlScriptContext.addListener(mock(DBCScriptContextListener.class));

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(contextProvider2).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(dbcExecutionContext2).getDataSource();
  }

  /**
   * Test {@link SQLScriptContext#removeListener(DBCScriptContextListener)}.
   *
   * <p>Method under test: {@link SQLScriptContext#removeListener(DBCScriptContextListener)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLScriptContext.removeListener(DBCScriptContextListener)"})
  public void testRemoveListener() {
    // Arrange
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

    SQLScriptContext sqlScriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider2,
            sourceFile2,
            new StringWriter(),
            mock(SQLParametersProvider.class));

    // Act
    sqlScriptContext.removeListener(mock(DBCScriptContextListener.class));

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(contextProvider2).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(dbcExecutionContext2).getDataSource();
  }

  /**
   * Test {@link SQLScriptContext#removeListener(DBCScriptContextListener)}.
   *
   * <p>Method under test: {@link SQLScriptContext#removeListener(DBCScriptContextListener)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLScriptContext.removeListener(DBCScriptContextListener)"})
  public void testRemoveListener2() {
    // Arrange
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

    SQLScriptContext sqlScriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider2,
            sourceFile2,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    sqlScriptContext.addListener(mock(DBCScriptContextListener.class));

    // Act
    sqlScriptContext.removeListener(mock(DBCScriptContextListener.class));

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(contextProvider2).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(dbcExecutionContext2).getDataSource();
  }
}

package org.jkiss.dbeaver.model.sql.eval;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.StringWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.jkiss.dbeaver.model.DBPContextProvider;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.connection.DBPConnectionConfiguration;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.jkiss.dbeaver.model.exec.output.DBCOutputWriter;
import org.jkiss.dbeaver.model.sql.SQLDialect;
import org.jkiss.dbeaver.model.sql.SQLParametersProvider;
import org.jkiss.dbeaver.model.sql.SQLScriptContext;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class ScriptVariablesResolverDiffblueTest {
  /**
   * Test {@link ScriptVariablesResolver#get(String)}.
   *
   * <p>Method under test: {@link ScriptVariablesResolver#get(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String ScriptVariablesResolver.get(String)"})
  public void testGet() {
    // Arrange
    SQLScriptContext parentContext = mock(SQLScriptContext.class);
    when(parentContext.getVariable(Mockito.<String>any())).thenReturn("Variable");

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

    SQLScriptContext parentContext2 =
        new SQLScriptContext(
            parentContext,
            contextProvider,
            sourceFile,
            mock(DBCOutputWriter.class),
            mock(SQLParametersProvider.class));

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer2.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    SQLDialect sqlDialect2 = mock(SQLDialect.class);
    when(sqlDialect2.getUnquotedIdentifier(Mockito.<String>any(), anyBoolean())).thenReturn("42");
    when(sqlDialect2.isQuotedIdentifier(Mockito.<String>any())).thenReturn(true);
    when(sqlDialect2.getUnquotedIdentifier(Mockito.<String>any())).thenReturn("42");

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getContainer()).thenReturn(dbpDataSourceContainer2);
    when(dbpDataSource2.getSQLDialect()).thenReturn(sqlDialect2);

    DBCExecutionContext dbcExecutionContext2 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext2.getDataSource()).thenReturn(dbpDataSource2);

    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    when(contextProvider2.getExecutionContext()).thenReturn(dbcExecutionContext2);
    Path sourceFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext scriptContext =
        new SQLScriptContext(
            parentContext2,
            contextProvider2,
            sourceFile2,
            new StringWriter(),
            mock(SQLParametersProvider.class));

    // Act
    String actualGetResult = new ScriptVariablesResolver(scriptContext).get("Name");

    // Assert
    verify(contextProvider, atLeast(1)).getExecutionContext();
    verify(contextProvider2, atLeast(1)).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSource).getSQLDialect();
    verify(dbpDataSource2, atLeast(1)).getSQLDialect();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(dbcExecutionContext, atLeast(1)).getDataSource();
    verify(dbcExecutionContext2, atLeast(1)).getDataSource();
    verify(sqlDialect).getUnquotedIdentifier("42");
    verify(sqlDialect2).getUnquotedIdentifier("42");
    verify(sqlDialect2).getUnquotedIdentifier("Name", true);
    verify(sqlDialect2).isQuotedIdentifier("Name");
    verify(parentContext).getVariable("42");
    assertEquals("Variable", actualGetResult);
  }
}

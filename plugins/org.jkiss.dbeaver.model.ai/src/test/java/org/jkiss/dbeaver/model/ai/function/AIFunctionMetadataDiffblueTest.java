package org.jkiss.dbeaver.model.ai.function;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.ai.AIDatabaseScope;
import org.jkiss.dbeaver.model.ai.AIFunctionContext;
import org.jkiss.dbeaver.model.ai.AIFunctionResult;
import org.jkiss.dbeaver.model.ai.AIFunctionResult.FunctionType;
import org.jkiss.dbeaver.model.ai.engine.AIDatabaseContext;
import org.jkiss.dbeaver.model.ai.engine.AIDatabaseContext.Builder;
import org.jkiss.dbeaver.model.ai.prompt.AIPromptGenerateSql;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.jkiss.dbeaver.model.impl.VoidExecutionContextDefaults;
import org.jkiss.dbeaver.model.logical.DBSLogicalDataSource;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AIFunctionMetadataDiffblueTest {
  /**
   * Test {@link AIFunctionMetadata#callFunction(AIFunctionContext, Map)}.
   *
   * <p>Method under test: {@link AIFunctionMetadata#callFunction(AIFunctionContext, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AIFunctionResult AIFunctionMetadata.callFunction(AIFunctionContext, Map)"})
  public void testCallFunction() throws DBException {
    // Arrange
    AIFunctionMetadata aiFunctionMetadata = new AIFunctionMetadata();

    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.getContextDefaults()).thenReturn(new VoidExecutionContextDefaults());
    DBSLogicalDataSource dataSource = new DBSLogicalDataSource(mock(DBPDataSourceContainer.class));

    Builder builder = new Builder(dataSource);
    AIDatabaseContext context =
        builder
            .setCustomEntities(new ArrayList<>())
            .setExecutionContext(executionContext)
            .setScope(AIDatabaseScope.CURRENT_SCHEMA)
            .build();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    AIPromptGenerateSql prompt = new AIPromptGenerateSql();

    AIFunctionContext context2 = new AIFunctionContext(monitor, context, prompt, new ArrayList<>());

    // Act
    AIFunctionResult actualCallFunctionResult =
        aiFunctionMetadata.callFunction(context2, new HashMap<>());

    // Assert
    verify(executionContext).getContextDefaults();
    assertEquals("N/A", actualCallFunctionResult.getValue());
    assertNull(actualCallFunctionResult.getCallback());
    assertEquals(FunctionType.INFORMATION, actualCallFunctionResult.getType());
  }
}

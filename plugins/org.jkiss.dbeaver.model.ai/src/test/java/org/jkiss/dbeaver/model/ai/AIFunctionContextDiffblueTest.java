package org.jkiss.dbeaver.model.ai;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.ai.engine.AIDatabaseContext;
import org.jkiss.dbeaver.model.ai.engine.AIDatabaseContext.Builder;
import org.jkiss.dbeaver.model.ai.engine.AIFunctionCall;
import org.jkiss.dbeaver.model.ai.prompt.AIPromptGenerateSql;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.jkiss.dbeaver.model.impl.VoidExecutionContextDefaults;
import org.jkiss.dbeaver.model.logical.DBSLogicalDataSource;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AIFunctionContextDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link AIFunctionContext#AIFunctionContext(DBRProgressMonitor, AIDatabaseContext,
   *       AIPromptGenerator, List)}
   *   <li>{@link AIFunctionContext#getContext()}
   *   <li>{@link AIFunctionContext#getFunctionCalls()}
   *   <li>{@link AIFunctionContext#getMonitor()}
   *   <li>{@link AIFunctionContext#getPrompt()}
   *   <li>{@link AIFunctionContext#getPromptMessages()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AIFunctionContext.<init>(DBRProgressMonitor, AIDatabaseContext, AIPromptGenerator, List)",
    "AIDatabaseContext AIFunctionContext.getContext()",
    "List AIFunctionContext.getFunctionCalls()",
    "DBRProgressMonitor AIFunctionContext.getMonitor()",
    "AIPromptGenerator AIFunctionContext.getPrompt()",
    "List AIFunctionContext.getPromptMessages()"
  })
  public void testGettersAndSetters() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBSLogicalDataSource dataSource = new DBSLogicalDataSource(mock(DBPDataSourceContainer.class));

    Builder builder = new Builder(dataSource);
    AIDatabaseContext context =
        builder
            .setCustomEntities(new ArrayList<>())
            .setExecutionContext(mock(DBCExecutionContext.class))
            .setScope(AIDatabaseScope.CURRENT_SCHEMA)
            .build();
    AIPromptGenerateSql prompt = new AIPromptGenerateSql();
    ArrayList<AIMessage> promptMessages = new ArrayList<>();

    // Act
    AIFunctionContext actualAiFunctionContext =
        new AIFunctionContext(monitor, context, prompt, promptMessages);
    AIDatabaseContext actualContext = actualAiFunctionContext.getContext();
    List<AIFunctionCall> actualFunctionCalls = actualAiFunctionContext.getFunctionCalls();
    DBRProgressMonitor actualMonitor = actualAiFunctionContext.getMonitor();
    AIPromptGenerator actualPrompt = actualAiFunctionContext.getPrompt();
    List<AIMessage> actualPromptMessages = actualAiFunctionContext.getPromptMessages();

    // Assert
    assertTrue(actualPrompt instanceof AIPromptGenerateSql);
    assertTrue(actualFunctionCalls.isEmpty());
    assertTrue(actualPromptMessages.isEmpty());
    assertSame(promptMessages, actualPromptMessages);
    assertSame(prompt, actualPrompt);
    assertSame(monitor, actualMonitor);
    assertSame(context, actualContext);
  }

  /**
   * Test {@link AIFunctionContext#addFunctionCall(AIFunctionCall)}.
   *
   * <p>Method under test: {@link AIFunctionContext#addFunctionCall(AIFunctionCall)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AIFunctionContext.addFunctionCall(AIFunctionCall)"})
  public void testAddFunctionCall() throws DBException {
    // Arrange
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

    AIFunctionContext aiFunctionContext =
        new AIFunctionContext(monitor, context, prompt, new ArrayList<>());
    AIFunctionCall functionCall = new AIFunctionCall();

    // Act
    aiFunctionContext.addFunctionCall(functionCall);

    // Assert
    verify(executionContext).getContextDefaults();
    List<AIFunctionCall> functionCalls = aiFunctionContext.getFunctionCalls();
    assertEquals(1, functionCalls.size());
    assertSame(functionCall, functionCalls.get(0));
  }
}

package org.jkiss.dbeaver.model.ai.impl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import org.jkiss.dbeaver.model.ai.AIPromptGenerator;
import org.jkiss.dbeaver.model.ai.engine.AIEngineRequest;
import org.jkiss.dbeaver.model.ai.prompt.AIPromptGenerateSql;
import org.jkiss.dbeaver.model.ai.registry.AIEngineDescriptor;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AIEngineRequestFactoryDiffblueTest {
  /**
   * Test {@link AIEngineRequestFactory#determineRequestTools(DBRProgressMonitor,
   * AIEngineDescriptor, AIPromptGenerator, AIEngineRequest)}.
   *
   * <ul>
   *   <li>Given {@code false}.
   *   <li>Then calls {@link AIEngineDescriptor#isSupportsFunctions()}.
   * </ul>
   *
   * <p>Method under test: {@link AIEngineRequestFactory#determineRequestTools(DBRProgressMonitor,
   * AIEngineDescriptor, AIPromptGenerator, AIEngineRequest)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AIEngineRequestFactory.determineRequestTools(DBRProgressMonitor, AIEngineDescriptor, AIPromptGenerator, AIEngineRequest)"
  })
  public void testDetermineRequestTools_givenFalse_thenCallsIsSupportsFunctions() {
    // Arrange
    AIEngineRequestFactory aiEngineRequestFactory =
        new AIEngineRequestFactory(new AIDatabaseSnapshotService(), mock(TokenCounter.class));
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    AIEngineDescriptor engineDescriptor = mock(AIEngineDescriptor.class);
    when(engineDescriptor.isSupportsFunctions()).thenReturn(false);
    AIPromptGenerateSql systemPromptGenerator = new AIPromptGenerateSql();

    // Act
    aiEngineRequestFactory.determineRequestTools(
        monitor, engineDescriptor, systemPromptGenerator, new AIEngineRequest(new ArrayList<>()));

    // Assert
    verify(engineDescriptor).isSupportsFunctions();
  }
}

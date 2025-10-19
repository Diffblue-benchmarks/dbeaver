package org.jkiss.dbeaver.model.ai;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.ai.engine.AIDatabaseContext;
import org.jkiss.dbeaver.model.ai.engine.AIDatabaseContext.Builder;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.jkiss.dbeaver.model.logical.DBSLogicalDataSource;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AITranslateRequestDiffblueTest {
  /**
   * Test {@link AITranslateRequest#AITranslateRequest(String, AIDatabaseContext)}.
   *
   * <p>Method under test: {@link AITranslateRequest#AITranslateRequest(String, AIDatabaseContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AITranslateRequest.<init>(String, AIDatabaseContext)"})
  public void testNewAITranslateRequest() throws DBException {
    // Arrange
    DBSLogicalDataSource dataSource = new DBSLogicalDataSource(mock(DBPDataSourceContainer.class));

    Builder builder = new Builder(dataSource);
    AIDatabaseContext context =
        builder
            .setCustomEntities(new ArrayList<>())
            .setExecutionContext(mock(DBCExecutionContext.class))
            .setScope(AIDatabaseScope.CURRENT_SCHEMA)
            .build();

    // Act
    AITranslateRequest actualAiTranslateRequest = new AITranslateRequest("Text", context);

    // Assert
    assertEquals("Text", actualAiTranslateRequest.text());
    assertNull(actualAiTranslateRequest.engine());
    assertSame(context, actualAiTranslateRequest.context());
  }
}

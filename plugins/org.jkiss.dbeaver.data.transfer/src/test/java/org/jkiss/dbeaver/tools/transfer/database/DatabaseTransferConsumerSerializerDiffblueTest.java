package org.jkiss.dbeaver.tools.transfer.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBIcon;
import org.jkiss.dbeaver.model.runtime.DBRRunnableContext;
import org.jkiss.dbeaver.model.task.DBTTask;
import org.jkiss.dbeaver.tools.transfer.serialize.SerializerContext;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DatabaseTransferConsumerSerializerDiffblueTest {
  /**
   * Test {@link DatabaseTransferConsumerSerializer#deserializeObject(DBRRunnableContext,
   * SerializerContext, DBTTask, Map)} with {@code DBRRunnableContext}, {@code SerializerContext},
   * {@code DBTTask}, {@code Map}.
   *
   * <p>Method under test: {@link
   * DatabaseTransferConsumerSerializer#deserializeObject(DBRRunnableContext, SerializerContext,
   * DBTTask, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DatabaseTransferConsumer DatabaseTransferConsumerSerializer.deserializeObject(DBRRunnableContext, SerializerContext, DBTTask, Map)"
  })
  public void testDeserializeObjectWithDBRRunnableContextSerializerContextDBTTaskMap()
      throws DBException {
    // Arrange
    DatabaseTransferConsumerSerializer databaseTransferConsumerSerializer =
        new DatabaseTransferConsumerSerializer();
    DBRRunnableContext runnableContext = mock(DBRRunnableContext.class);
    SerializerContext serializeContext = new SerializerContext();
    DBTTask objectContext = mock(DBTTask.class);

    // Act
    DatabaseTransferConsumer actualDeserializeObjectResult =
        databaseTransferConsumerSerializer.deserializeObject(
            runnableContext, serializeContext, objectContext, new HashMap<>());

    // Assert
    assertTrue(actualDeserializeObjectResult.getObjectIcon() instanceof DBIcon);
    assertEquals("?", actualDeserializeObjectResult.getObjectName());
    assertNull(actualDeserializeObjectResult.getPreviewRows());
    assertNull(actualDeserializeObjectResult.getTargetAttributes());
    assertNull(actualDeserializeObjectResult.getSourceObject());
    assertNull(actualDeserializeObjectResult.getTargetObject());
    assertNull(actualDeserializeObjectResult.getDatabaseObject());
    assertNull(actualDeserializeObjectResult.getContainer());
    assertNull(actualDeserializeObjectResult.getTargetObjectContainer());
    assertNull(actualDeserializeObjectResult.getSettings());
    assertNull(actualDeserializeObjectResult.getColumnMappings());
    assertFalse(actualDeserializeObjectResult.isConfigurationComplete());
    assertFalse(actualDeserializeObjectResult.isPreview());
  }
}

package org.jkiss.dbeaver.model.impl.plan;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.JsonObject;
import java.lang.reflect.InvocationTargetException;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceInfo;
import org.jkiss.dbeaver.model.exec.plan.DBCPlanNode;
import org.jkiss.dbeaver.model.exec.plan.DBCQueryPlannerDeSerialInfo;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ExecutionPlanDeserializerDiffblueTest {
  /**
   * Test {@link ExecutionPlanDeserializer#loadRoot(DBPDataSource, JsonObject,
   * DBCQueryPlannerDeSerialInfo)}.
   *
   * <ul>
   *   <li>Then throw {@link InvocationTargetException}.
   * </ul>
   *
   * <p>Method under test: {@link ExecutionPlanDeserializer#loadRoot(DBPDataSource, JsonObject,
   * DBCQueryPlannerDeSerialInfo)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.util.List ExecutionPlanDeserializer.loadRoot(DBPDataSource, JsonObject, DBCQueryPlannerDeSerialInfo)"
  })
  public void testLoadRoot_thenThrowInvocationTargetException() throws InvocationTargetException {
    // Arrange
    ExecutionPlanDeserializer<DBCPlanNode> executionPlanDeserializer =
        new ExecutionPlanDeserializer<>();

    DBPDataSourceInfo dbpDataSourceInfo = mock(DBPDataSourceInfo.class);
    when(dbpDataSourceInfo.getDriverName()).thenReturn("Driver Name");

    DBPDataSource datasource = mock(DBPDataSource.class);
    when(datasource.getInfo()).thenReturn(dbpDataSourceInfo);

    JsonObject plan = new JsonObject();
    plan.addProperty(AbstractExecutionPlanSerializer.PROP_SIGNATURE, "42");

    // Act and Assert
    assertThrows(
        InvocationTargetException.class,
        () ->
            executionPlanDeserializer.loadRoot(
                datasource, plan, mock(DBCQueryPlannerDeSerialInfo.class)));
    verify(datasource).getInfo();
    verify(dbpDataSourceInfo).getDriverName();
  }
}

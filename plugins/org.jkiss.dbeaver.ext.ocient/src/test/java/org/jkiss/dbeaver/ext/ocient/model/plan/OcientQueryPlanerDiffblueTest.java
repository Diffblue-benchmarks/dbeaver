package org.jkiss.dbeaver.ext.ocient.model.plan;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceInfo;
import org.jkiss.dbeaver.model.exec.plan.DBCPlan;
import org.jkiss.dbeaver.model.exec.plan.DBCPlanStyle;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class OcientQueryPlanerDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link OcientQueryPlaner#OcientQueryPlaner(DBPDataSource)}
   *   <li>{@link OcientQueryPlaner#getDataSource()}
   *   <li>{@link OcientQueryPlaner#getPlanStyle()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void OcientQueryPlaner.<init>(DBPDataSource)",
    "DBPDataSource OcientQueryPlaner.getDataSource()",
    "DBCPlanStyle OcientQueryPlaner.getPlanStyle()"
  })
  public void testGettersAndSetters() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);

    // Act
    OcientQueryPlaner actualOcientQueryPlaner = new OcientQueryPlaner(dataSource);
    DBPDataSource actualDataSource = actualOcientQueryPlaner.getDataSource();

    // Assert
    assertEquals(DBCPlanStyle.PLAN, actualOcientQueryPlaner.getPlanStyle());
    assertSame(dataSource, actualDataSource);
  }

  /**
   * Test {@link OcientQueryPlaner#serialize(Writer, DBCPlan)}.
   *
   * <p>Method under test: {@link OcientQueryPlaner#serialize(Writer, DBCPlan)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OcientQueryPlaner.serialize(Writer, DBCPlan)"})
  public void testSerialize() throws IOException, InvocationTargetException {
    // Arrange
    DBPDataSourceInfo dbpDataSourceInfo = mock(DBPDataSourceInfo.class);
    when(dbpDataSourceInfo.getDriverName()).thenReturn("Driver Name");

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getInfo()).thenReturn(dbpDataSourceInfo);
    OcientQueryPlaner ocientQueryPlaner = new OcientQueryPlaner(dataSource);
    StringWriter writer = new StringWriter();

    ArrayList<OcientPlanNodeJson> rootNodes = new ArrayList<>();
    OcientPlanNodeJson parent = mock(OcientPlanNodeJson.class);
    OcientPlanNodeJson parent2 = new OcientPlanNodeJson(parent, "version", new JsonObject());
    OcientPlanNodeJson ocientPlanNodeJson = new OcientPlanNodeJson(parent2, new HashMap<>());
    rootNodes.add(ocientPlanNodeJson);
    OcientExecutionPlan plan = new OcientExecutionPlan("version", rootNodes);

    // Act
    ocientQueryPlaner.serialize(writer, plan);

    // Assert
    verify(dataSource).getInfo();
    verify(dbpDataSourceInfo).getDriverName();
  }

  /**
   * Test {@link OcientQueryPlaner#serialize(Writer, DBCPlan)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code version} is {@code version}.
   * </ul>
   *
   * <p>Method under test: {@link OcientQueryPlaner#serialize(Writer, DBCPlan)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OcientQueryPlaner.serialize(Writer, DBCPlan)"})
  public void testSerialize_givenHashMapVersionIsVersion()
      throws IOException, InvocationTargetException {
    // Arrange
    DBPDataSourceInfo dbpDataSourceInfo = mock(DBPDataSourceInfo.class);
    when(dbpDataSourceInfo.getDriverName()).thenReturn("Driver Name");

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getInfo()).thenReturn(dbpDataSourceInfo);
    OcientQueryPlaner ocientQueryPlaner = new OcientQueryPlaner(dataSource);
    StringWriter writer = new StringWriter();

    HashMap<String, String> attributes = new HashMap<>();
    attributes.put("version", "version");
    OcientPlanNodeJson parent = mock(OcientPlanNodeJson.class);
    OcientPlanNodeJson parent2 = new OcientPlanNodeJson(parent, "version", new JsonObject());

    OcientPlanNodeJson ocientPlanNodeJson = new OcientPlanNodeJson(parent2, attributes);

    ArrayList<OcientPlanNodeJson> rootNodes = new ArrayList<>();
    rootNodes.add(ocientPlanNodeJson);
    OcientExecutionPlan plan = new OcientExecutionPlan("version", rootNodes);

    // Act
    ocientQueryPlaner.serialize(writer, plan);

    // Assert
    verify(dataSource).getInfo();
    verify(dbpDataSourceInfo).getDriverName();
  }

  /**
   * Test {@link OcientQueryPlaner#serialize(Writer, DBCPlan)}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSource#getInfo()}.
   * </ul>
   *
   * <p>Method under test: {@link OcientQueryPlaner#serialize(Writer, DBCPlan)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OcientQueryPlaner.serialize(Writer, DBCPlan)"})
  public void testSerialize_thenCallsGetInfo() throws IOException, InvocationTargetException {
    // Arrange
    DBPDataSourceInfo dbpDataSourceInfo = mock(DBPDataSourceInfo.class);
    when(dbpDataSourceInfo.getDriverName()).thenReturn("Driver Name");

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getInfo()).thenReturn(dbpDataSourceInfo);
    OcientQueryPlaner ocientQueryPlaner = new OcientQueryPlaner(dataSource);
    StringWriter writer = new StringWriter();
    OcientExecutionPlan plan = new OcientExecutionPlan("version", new ArrayList<>());

    // Act
    ocientQueryPlaner.serialize(writer, plan);

    // Assert
    verify(dataSource).getInfo();
    verify(dbpDataSourceInfo).getDriverName();
  }
}

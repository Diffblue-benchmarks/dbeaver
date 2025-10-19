package org.jkiss.dbeaver.model.virtual;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.stream.JsonWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.app.DBPProject;
import org.jkiss.dbeaver.model.exec.DBCLogicalOperator;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSEntity;
import org.jkiss.dbeaver.model.struct.DBSEntityConstraintType;
import org.jkiss.dbeaver.model.struct.DBSObjectContainer;
import org.jkiss.dbeaver.model.virtual.DBVModelSerializerLegacy.ModelParser;
import org.jkiss.utils.xml.SAXListener;
import org.jkiss.utils.xml.XMLBuilder;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class DBVModelDiffblueTest {
  /**
   * Test {@link DBVModel#DBVModel(DBPDataSourceContainer)}.
   *
   * <p>Method under test: {@link DBVModel#DBVModel(DBPDataSourceContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.<init>(DBPDataSourceContainer)"})
  public void testNewDBVModel() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    // Act
    DBVModel actualDbvModel = new DBVModel(dataSourceContainer);

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    assertTrue(actualDbvModel.getModelParser() instanceof ModelParser);
    assertEquals("42", actualDbvModel.getName());
    assertEquals("42", actualDbvModel.toString());
    assertEquals("42", actualDbvModel.getId());
    assertEquals("container", actualDbvModel.getType());
    assertNull(actualDbvModel.getDescription());
    assertNull(actualDbvModel.getDataSource());
    assertNull(actualDbvModel.getProject());
    assertNull(actualDbvModel.getParentObject());
    assertNull(actualDbvModel.getTransformSettings());
    assertFalse(actualDbvModel.hasValuableData());
    assertTrue(actualDbvModel.getContainers().isEmpty());
    assertTrue(actualDbvModel.getEntities().isEmpty());
    assertTrue(actualDbvModel.getProperties().isEmpty());
    assertTrue(actualDbvModel.isPersisted());
    assertSame(dataSourceContainer, actualDbvModel.getDataSourceContainer());
  }

  /**
   * Test {@link DBVModel#DBVModel(DBPDataSourceContainer, DBVModel)}.
   *
   * <p>Method under test: {@link DBVModel#DBVModel(DBPDataSourceContainer, DBVModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.<init>(DBPDataSourceContainer, DBVModel)"})
  public void testNewDBVModel2() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());

    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");
    entity.addForeignKey(new DBVEntityForeignKey(entity2));
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntity entity4 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity4, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity3, copy);
    entity.addConstraint(constraint);

    DBVModel source = new DBVModel(dataSourceContainer2);
    source.addEntity(entity);

    // Act
    DBVModel actualDbvModel = new DBVModel(dataSourceContainer, source);

    // Assert
    verify(dbpDataSource, atLeast(1)).getContainer();
    verify(dbpDataSourceContainer, atLeast(1)).fireEvent(Mockito.<DBPEvent>any());
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(parent, atLeast(1)).getDataSource();
    assertEquals(1, actualDbvModel.getEntities().size());
    assertTrue(actualDbvModel.getContainers().isEmpty());
    assertTrue(actualDbvModel.getProperties().isEmpty());
    assertTrue(actualDbvModel.hasValuableData());
  }

  /**
   * Test {@link DBVModel#DBVModel(DBPDataSourceContainer, DBVModel)}.
   *
   * <p>Method under test: {@link DBVModel#DBVModel(DBPDataSourceContainer, DBVModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.<init>(DBPDataSourceContainer, DBVModel)"})
  public void testNewDBVModel3() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());

    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    Object[] attributeValues = new Object[] {DBPEvent.RENAME};
    DBVColorOverride color =
        new DBVColorOverride(
            "Attribute Name",
            DBCLogicalOperator.EQUALS,
            attributeValues,
            "Color Foreground",
            "Color Background");
    entity.addColorOverride(color);
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntity entity3 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity3, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity2, copy);
    entity.addConstraint(constraint);

    DBVModel source = new DBVModel(dataSourceContainer2);
    source.addEntity(entity);

    // Act
    DBVModel actualDbvModel = new DBVModel(dataSourceContainer, source);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(parent).getDataSource();
    assertEquals(1, actualDbvModel.getEntities().size());
    assertTrue(actualDbvModel.getContainers().isEmpty());
    assertTrue(actualDbvModel.getProperties().isEmpty());
    assertTrue(actualDbvModel.hasValuableData());
  }

  /**
   * Test {@link DBVModel#DBVModel(DBPDataSourceContainer, DBVModel)}.
   *
   * <p>Method under test: {@link DBVModel#DBVModel(DBPDataSourceContainer, DBVModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.<init>(DBPDataSourceContainer, DBVModel)"})
  public void testNewDBVModel4() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVContainer container = new DBVContainer(parent2, "Name");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity, copy);
    constraint.addAttribute("Name");

    DBVEntity entity3 = new DBVEntity(container, "Name", "Description Column Names");
    entity3.addConstraint(constraint);

    DBVModel source = new DBVModel(dataSourceContainer2);
    source.addEntity(entity3);

    // Act
    DBVModel actualDbvModel = new DBVModel(dataSourceContainer, source);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(parent).getDataSource();
    assertEquals(1, actualDbvModel.getEntities().size());
    assertTrue(actualDbvModel.getContainers().isEmpty());
    assertTrue(actualDbvModel.getProperties().isEmpty());
    assertTrue(actualDbvModel.hasValuableData());
  }

  /**
   * Test {@link DBVModel#DBVModel(DBPDataSourceContainer, DBVModel)}.
   *
   * <p>Method under test: {@link DBVModel#DBVModel(DBPDataSourceContainer, DBVModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.<init>(DBPDataSourceContainer, DBVModel)"})
  public void testNewDBVModel5() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());

    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    entity.setColorOverrides(new ArrayList<>());
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntity entity3 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity3, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity2, copy);
    entity.addConstraint(constraint);

    DBVModel source = new DBVModel(dataSourceContainer2);
    source.addEntity(entity);

    // Act
    DBVModel actualDbvModel = new DBVModel(dataSourceContainer, source);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(parent).getDataSource();
    assertEquals(1, actualDbvModel.getEntities().size());
    assertTrue(actualDbvModel.getContainers().isEmpty());
    assertTrue(actualDbvModel.getProperties().isEmpty());
    assertTrue(actualDbvModel.hasValuableData());
  }

  /**
   * Test {@link DBVModel#DBVModel(DBPDataSourceContainer, DBVModel)}.
   *
   * <p>Method under test: {@link DBVModel#DBVModel(DBPDataSourceContainer, DBVModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.<init>(DBPDataSourceContainer, DBVModel)"})
  public void testNewDBVModel6() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVContainer container = new DBVContainer(parent2, "Name");
    DBVEntity entity = new DBVEntity(null, "Name", "Description Column Names");

    DBVEntityForeignKey foreignKey = new DBVEntityForeignKey(entity);
    foreignKey.setRefEntityId("42");

    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");
    entity2.addForeignKey(foreignKey);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntity entity4 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity4, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity3, copy);
    entity2.addConstraint(constraint);

    DBVModel source = new DBVModel(dataSourceContainer2);
    source.addEntity(entity2);

    // Act
    DBVModel actualDbvModel = new DBVModel(dataSourceContainer, source);

    // Assert
    verify(dbpDataSource, atLeast(1)).getContainer();
    verify(dbpDataSourceContainer, atLeast(1)).fireEvent(Mockito.<DBPEvent>any());
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(parent, atLeast(1)).getDataSource();
    assertEquals(1, actualDbvModel.getEntities().size());
    assertTrue(actualDbvModel.getContainers().isEmpty());
    assertTrue(actualDbvModel.getProperties().isEmpty());
    assertTrue(actualDbvModel.hasValuableData());
  }

  /**
   * Test {@link DBVModel#DBVModel(String, Map)}.
   *
   * <ul>
   *   <li>Given {@code @}.
   *   <li>When {@link HashMap#HashMap()} {@code @} is {@link DBPEvent#RENAME}.
   * </ul>
   *
   * <p>Method under test: {@link DBVModel#DBVModel(String, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.<init>(String, Map)"})
  public void testNewDBVModel_givenCommercialAt_whenHashMapCommercialAtIsRename() {
    // Arrange
    HashMap<String, Object> map = new HashMap<>();
    map.put("@", DBPEvent.RENAME);
    map.put("foo", DBPEvent.RENAME);

    // Act
    DBVModel actualDbvModel = new DBVModel("42", map);

    // Assert
    assertTrue(actualDbvModel.getModelParser() instanceof ModelParser);
    assertEquals("42", actualDbvModel.getName());
    assertEquals("42", actualDbvModel.toString());
    assertEquals("42", actualDbvModel.getId());
    assertEquals("container", actualDbvModel.getType());
    assertNull(actualDbvModel.getDescription());
    assertNull(actualDbvModel.getDataSource());
    assertNull(actualDbvModel.getDataSourceContainer());
    assertNull(actualDbvModel.getProject());
    assertNull(actualDbvModel.getParentObject());
    assertNull(actualDbvModel.getTransformSettings());
    assertFalse(actualDbvModel.hasValuableData());
    assertTrue(actualDbvModel.getContainers().isEmpty());
    assertTrue(actualDbvModel.getEntities().isEmpty());
    assertTrue(actualDbvModel.getProperties().isEmpty());
    assertTrue(actualDbvModel.isPersisted());
  }

  /**
   * Test {@link DBVModel#DBVModel(DBPDataSourceContainer, DBVModel)}.
   *
   * <ul>
   *   <li>Given {@link DBPProject} {@link DBPProject#getNavigatorModel()} return {@code null}.
   *   <li>Then calls {@link DBPDataSourceContainer#getProject()}.
   * </ul>
   *
   * <p>Method under test: {@link DBVModel#DBVModel(DBPDataSourceContainer, DBVModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.<init>(DBPDataSourceContainer, DBVModel)"})
  public void testNewDBVModel_givenDBPProjectGetNavigatorModelReturnNull_thenCallsGetProject() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVContainer container = new DBVContainer(parent2, "Name");

    DBPProject dbpProject = mock(DBPProject.class);
    when(dbpProject.getNavigatorModel()).thenReturn(null);

    DBPDataSourceContainer dataSourceContainer3 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer3.getId()).thenReturn("42");
    when(dataSourceContainer3.getProject()).thenReturn(dbpProject);
    DBVModel container2 = new DBVModel(dataSourceContainer3);
    DBVEntity entity = new DBVEntity(container2, "Name", "Description Column Names");

    DBVEntityForeignKey foreignKey = new DBVEntityForeignKey(entity);
    foreignKey.setRefEntityId("42");

    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");
    entity2.addForeignKey(foreignKey);
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntity entity4 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity4, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity3, copy);
    entity2.addConstraint(constraint);

    DBVModel source = new DBVModel(dataSourceContainer2);
    source.addEntity(entity2);

    // Act
    DBVModel actualDbvModel = new DBVModel(dataSourceContainer, source);

    // Assert
    verify(dbpDataSource, atLeast(1)).getContainer();
    verify(dbpDataSourceContainer, atLeast(1)).fireEvent(Mockito.<DBPEvent>any());
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(dataSourceContainer3, atLeast(1)).getId();
    verify(dataSourceContainer3).getProject();
    verify(dbpProject).getNavigatorModel();
    verify(parent, atLeast(1)).getDataSource();
    assertEquals(1, actualDbvModel.getEntities().size());
    assertTrue(actualDbvModel.getContainers().isEmpty());
    assertTrue(actualDbvModel.getProperties().isEmpty());
    assertTrue(actualDbvModel.hasValuableData());
  }

  /**
   * Test {@link DBVModel#DBVModel(String, Map)}.
   *
   * <ul>
   *   <li>Given {@code foo}.
   *   <li>When {@link HashMap#HashMap()} {@code foo} is {@link DBPEvent#RENAME}.
   * </ul>
   *
   * <p>Method under test: {@link DBVModel#DBVModel(String, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.<init>(String, Map)"})
  public void testNewDBVModel_givenFoo_whenHashMapFooIsRename() {
    // Arrange
    HashMap<String, Object> map = new HashMap<>();
    map.put("foo", DBPEvent.RENAME);

    // Act
    DBVModel actualDbvModel = new DBVModel("42", map);

    // Assert
    assertTrue(actualDbvModel.getModelParser() instanceof ModelParser);
    assertEquals("42", actualDbvModel.getName());
    assertEquals("42", actualDbvModel.toString());
    assertEquals("42", actualDbvModel.getId());
    assertEquals("container", actualDbvModel.getType());
    assertNull(actualDbvModel.getDescription());
    assertNull(actualDbvModel.getDataSource());
    assertNull(actualDbvModel.getDataSourceContainer());
    assertNull(actualDbvModel.getProject());
    assertNull(actualDbvModel.getParentObject());
    assertNull(actualDbvModel.getTransformSettings());
    assertFalse(actualDbvModel.hasValuableData());
    assertTrue(actualDbvModel.getContainers().isEmpty());
    assertTrue(actualDbvModel.getEntities().isEmpty());
    assertTrue(actualDbvModel.getProperties().isEmpty());
    assertTrue(actualDbvModel.isPersisted());
  }

  /**
   * Test {@link DBVModel#DBVModel(DBPDataSourceContainer, DBVModel)}.
   *
   * <ul>
   *   <li>Given {@code Name}.
   *   <li>Then return Properties size is one.
   * </ul>
   *
   * <p>Method under test: {@link DBVModel#DBVModel(DBPDataSourceContainer, DBVModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.<init>(DBPDataSourceContainer, DBVModel)"})
  public void testNewDBVModel_givenName_thenReturnPropertiesSizeIsOne() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");

    DBVModel source = new DBVModel(dataSourceContainer2);
    source.setProperty("Name", DBPEvent.RENAME);
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    source.addContainer(new DBVContainer(parent2, "Name"));

    // Act
    DBVModel actualDbvModel = new DBVModel(dataSourceContainer, source);

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    assertEquals(1, actualDbvModel.getContainers().size());
    Map<String, Object> properties = actualDbvModel.getProperties();
    assertEquals(1, properties.size());
    assertTrue(actualDbvModel.getEntities().isEmpty());
    assertTrue(properties.containsKey("Name"));
    assertTrue(actualDbvModel.hasValuableData());
  }

  /**
   * Test {@link DBVModel#DBVModel(DBPDataSourceContainer, DBVModel)}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSource#getContainer()}.
   * </ul>
   *
   * <p>Method under test: {@link DBVModel#DBVModel(DBPDataSourceContainer, DBVModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.<init>(DBPDataSourceContainer, DBVModel)"})
  public void testNewDBVModel_thenCallsGetContainer() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());

    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntity entity3 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity3, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity2, copy);
    entity.addConstraint(constraint);

    DBVModel source = new DBVModel(dataSourceContainer2);
    source.addEntity(entity);

    // Act
    DBVModel actualDbvModel = new DBVModel(dataSourceContainer, source);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(parent).getDataSource();
    assertEquals(1, actualDbvModel.getEntities().size());
    assertTrue(actualDbvModel.getContainers().isEmpty());
    assertTrue(actualDbvModel.getProperties().isEmpty());
    assertTrue(actualDbvModel.hasValuableData());
  }

  /**
   * Test {@link DBVModel#DBVModel(DBPDataSourceContainer, DBVModel)}.
   *
   * <ul>
   *   <li>Then calls {@link DBVContainer#getDataSourceContainer()}.
   * </ul>
   *
   * <p>Method under test: {@link DBVModel#DBVModel(DBPDataSourceContainer, DBVModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.<init>(DBPDataSourceContainer, DBVModel)"})
  public void testNewDBVModel_thenCallsGetDataSourceContainer() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVContainer container = new DBVContainer(parent2, "Name");

    DBPProject dbpProject = mock(DBPProject.class);
    when(dbpProject.getNavigatorModel()).thenReturn(null);

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer2.getProject()).thenReturn(dbpProject);

    DBVContainer parent3 = mock(DBVContainer.class);
    when(parent3.getDataSourceContainer()).thenReturn(dbpDataSourceContainer2);
    DBVContainer container2 = new DBVContainer(parent3, "Name");
    DBVEntity entity = new DBVEntity(container2, "Name", "Description Column Names");

    DBVEntityForeignKey foreignKey = new DBVEntityForeignKey(entity);
    foreignKey.setRefEntityId("42");

    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");
    entity2.addForeignKey(foreignKey);
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntity entity4 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity4, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity3, copy);
    entity2.addConstraint(constraint);

    DBVModel source = new DBVModel(dataSourceContainer2);
    source.addEntity(entity2);

    // Act
    DBVModel actualDbvModel = new DBVModel(dataSourceContainer, source);

    // Assert
    verify(dbpDataSource, atLeast(1)).getContainer();
    verify(dbpDataSourceContainer, atLeast(1)).fireEvent(Mockito.<DBPEvent>any());
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(dbpDataSourceContainer2).getProject();
    verify(dbpProject).getNavigatorModel();
    verify(parent, atLeast(1)).getDataSource();
    verify(parent3).getDataSourceContainer();
    assertEquals(1, actualDbvModel.getEntities().size());
    assertTrue(actualDbvModel.getContainers().isEmpty());
    assertTrue(actualDbvModel.getProperties().isEmpty());
    assertTrue(actualDbvModel.hasValuableData());
  }

  /**
   * Test {@link DBVModel#DBVModel(DBPDataSourceContainer, DBVModel)}.
   *
   * <ul>
   *   <li>Then return Containers size is one.
   * </ul>
   *
   * <p>Method under test: {@link DBVModel#DBVModel(DBPDataSourceContainer, DBVModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.<init>(DBPDataSourceContainer, DBVModel)"})
  public void testNewDBVModel_thenReturnContainersSizeIsOne() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");

    DBVModel source = new DBVModel(dataSourceContainer2);
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    source.addContainer(new DBVContainer(parent2, "Name"));

    // Act
    DBVModel actualDbvModel = new DBVModel(dataSourceContainer, source);

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    assertEquals(1, actualDbvModel.getContainers().size());
    assertFalse(actualDbvModel.hasValuableData());
    assertTrue(actualDbvModel.getEntities().isEmpty());
    assertTrue(actualDbvModel.getProperties().isEmpty());
  }

  /**
   * Test {@link DBVModel#DBVModel(DBPDataSourceContainer, DBVModel)}.
   *
   * <ul>
   *   <li>Then return Entities size is one.
   * </ul>
   *
   * <p>Method under test: {@link DBVModel#DBVModel(DBPDataSourceContainer, DBVModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.<init>(DBPDataSourceContainer, DBVModel)"})
  public void testNewDBVModel_thenReturnEntitiesSizeIsOne() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");

    DBVModel source = new DBVModel(dataSourceContainer2);
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    source.addEntity(entity);

    // Act
    DBVModel actualDbvModel = new DBVModel(dataSourceContainer, source);

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    assertEquals(1, actualDbvModel.getEntities().size());
    assertTrue(actualDbvModel.getContainers().isEmpty());
    assertTrue(actualDbvModel.getProperties().isEmpty());
    assertTrue(actualDbvModel.hasValuableData());
  }

  /**
   * Test {@link DBVModel#DBVModel(DBPDataSourceContainer, DBVModel)}.
   *
   * <ul>
   *   <li>When {@link DBVModel#DBVModel(DBPDataSourceContainer)} with dataSourceContainer is {@link
   *       DBPDataSourceContainer}.
   * </ul>
   *
   * <p>Method under test: {@link DBVModel#DBVModel(DBPDataSourceContainer, DBVModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.<init>(DBPDataSourceContainer, DBVModel)"})
  public void testNewDBVModel_whenDBVModelWithDataSourceContainerIsDBPDataSourceContainer() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    // Act
    DBVModel actualDbvModel = new DBVModel(dataSourceContainer, source);

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    assertFalse(actualDbvModel.hasValuableData());
    assertTrue(actualDbvModel.getContainers().isEmpty());
    assertTrue(actualDbvModel.getEntities().isEmpty());
    assertTrue(actualDbvModel.getProperties().isEmpty());
  }

  /**
   * Test {@link DBVModel#DBVModel(String, Map)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.
   * </ul>
   *
   * <p>Method under test: {@link DBVModel#DBVModel(String, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.<init>(String, Map)"})
  public void testNewDBVModel_whenHashMap() {
    // Arrange and Act
    DBVModel actualDbvModel = new DBVModel("42", new HashMap<>());

    // Assert
    assertTrue(actualDbvModel.getModelParser() instanceof ModelParser);
    assertEquals("42", actualDbvModel.getName());
    assertEquals("42", actualDbvModel.toString());
    assertEquals("42", actualDbvModel.getId());
    assertEquals("container", actualDbvModel.getType());
    assertNull(actualDbvModel.getDescription());
    assertNull(actualDbvModel.getDataSource());
    assertNull(actualDbvModel.getDataSourceContainer());
    assertNull(actualDbvModel.getProject());
    assertNull(actualDbvModel.getParentObject());
    assertNull(actualDbvModel.getTransformSettings());
    assertFalse(actualDbvModel.hasValuableData());
    assertTrue(actualDbvModel.getContainers().isEmpty());
    assertTrue(actualDbvModel.getEntities().isEmpty());
    assertTrue(actualDbvModel.getProperties().isEmpty());
    assertTrue(actualDbvModel.isPersisted());
  }

  /**
   * Test {@link DBVModel#dispose()}.
   *
   * <p>Method under test: {@link DBVModel#dispose()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.dispose()"})
  public void testDispose() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBVModel dbvModel = new DBVModel(dataSourceContainer);
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    dbvModel.addContainer(new DBVContainer(parent2, "Name"));

    // Act
    dbvModel.dispose();

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    assertFalse(dbvModel.hasValuableData());
    assertTrue(dbvModel.getContainers().isEmpty());
    assertTrue(dbvModel.getEntities().isEmpty());
  }

  /**
   * Test {@link DBVModel#dispose()}.
   *
   * <p>Method under test: {@link DBVModel#dispose()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.dispose()"})
  public void testDispose2() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel container = new DBVModel(dataSourceContainer2);

    DBVModel dbvModel = new DBVModel(dataSourceContainer);
    dbvModel.addContainer(container);

    // Act
    dbvModel.dispose();

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    assertFalse(dbvModel.hasValuableData());
    assertTrue(dbvModel.getContainers().isEmpty());
    assertTrue(dbvModel.getEntities().isEmpty());
  }

  /**
   * Test {@link DBVModel#dispose()}.
   *
   * <ul>
   *   <li>Given {@link DBVContainer#DBVContainer(DBVContainer, String)} with parent is {@link
   *       DBVContainer#DBVContainer(DBVContainer, String, Map)} and {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link DBVModel#dispose()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.dispose()"})
  public void testDispose_givenDBVContainerWithParentIsDBVContainerAndName() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBVModel dbvModel = new DBVModel(dataSourceContainer);
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    dbvModel.addEntity(entity);

    // Act
    dbvModel.dispose();

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    assertFalse(dbvModel.hasValuableData());
    assertTrue(dbvModel.getContainers().isEmpty());
    assertTrue(dbvModel.getEntities().isEmpty());
  }

  /**
   * Test {@link DBVModel#dispose()}.
   *
   * <ul>
   *   <li>Given {@link DBVEntityForeignKey#DBVEntityForeignKey(DBVEntity)} with entity is {@link
   *       DBVEntity#DBVEntity(DBVContainer, String, String)} RefEntityId is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link DBVModel#dispose()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.dispose()"})
  public void testDispose_givenDBVEntityForeignKeyWithEntityIsDBVEntityRefEntityIdIs42() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel container = new DBVModel(dataSourceContainer2);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVContainer container2 = new DBVContainer(parent2, "Name");
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container3, "Name", "Description Column Names");

    DBVEntityForeignKey foreignKey = new DBVEntityForeignKey(entity);
    foreignKey.setRefEntityId("42");

    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    entity2.addForeignKey(foreignKey);

    DBVModel dbvModel = new DBVModel(dataSourceContainer);
    dbvModel.addEntity(entity2);
    dbvModel.addContainer(container);

    // Act
    dbvModel.dispose();

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(parent).getDataSource();
    assertFalse(dbvModel.hasValuableData());
    assertTrue(dbvModel.getContainers().isEmpty());
    assertTrue(dbvModel.getEntities().isEmpty());
  }

  /**
   * Test {@link DBVModel#dispose()}.
   *
   * <ul>
   *   <li>Given {@link DBVModel#DBVModel(DBPDataSourceContainer)} with dataSourceContainer is
   *       {@link DBPDataSourceContainer}.
   * </ul>
   *
   * <p>Method under test: {@link DBVModel#dispose()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.dispose()"})
  public void testDispose_givenDBVModelWithDataSourceContainerIsDBPDataSourceContainer() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel dbvModel = new DBVModel(dataSourceContainer);

    // Act
    dbvModel.dispose();

    // Assert that nothing has changed
    verify(dataSourceContainer, atLeast(1)).getId();
    assertFalse(dbvModel.hasValuableData());
    assertTrue(dbvModel.getContainers().isEmpty());
    assertTrue(dbvModel.getEntities().isEmpty());
  }

  /**
   * Test {@link DBVModel#dispose()}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSource#getContainer()}.
   * </ul>
   *
   * <p>Method under test: {@link DBVModel#dispose()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.dispose()"})
  public void testDispose_thenCallsGetContainer() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel container = new DBVModel(dataSourceContainer2);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());

    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    entity.addForeignKey(new DBVEntityForeignKey(entity2));

    DBVModel dbvModel = new DBVModel(dataSourceContainer);
    dbvModel.addEntity(entity);
    dbvModel.addContainer(container);

    // Act
    dbvModel.dispose();

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(parent).getDataSource();
    assertFalse(dbvModel.hasValuableData());
    assertTrue(dbvModel.getContainers().isEmpty());
    assertTrue(dbvModel.getEntities().isEmpty());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBVModel#setDataSourceContainer(DBPDataSourceContainer)}
   *   <li>{@link DBVModel#setId(String)}
   *   <li>{@link DBVModel#getDataSourceContainer()}
   *   <li>{@link DBVModel#getId()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBPDataSourceContainer DBVModel.getDataSourceContainer()",
    "String DBVModel.getId()",
    "void DBVModel.setDataSourceContainer(DBPDataSourceContainer)",
    "void DBVModel.setId(String)"
  })
  public void testGettersAndSetters() {
    // Arrange
    DBVModel dbvModel = new DBVModel(mock(DBPDataSourceContainer.class));
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);

    // Act
    dbvModel.setDataSourceContainer(dataSourceContainer);
    dbvModel.setId("42");
    DBPDataSourceContainer actualDataSourceContainer = dbvModel.getDataSourceContainer();

    // Assert
    assertEquals("42", dbvModel.getId());
    assertSame(dataSourceContainer, actualDataSourceContainer);
  }

  /**
   * Test {@link DBVModel#getRealContainer(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Given {@link DBVModel#DBVModel(String, Map)} with id is {@code 42} and map is {@link
   *       HashMap#HashMap()}.
   * </ul>
   *
   * <p>Method under test: {@link DBVModel#getRealContainer(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSObjectContainer DBVModel.getRealContainer(DBRProgressMonitor)"})
  public void testGetRealContainer_givenDBVModelWithIdIs42AndMapIsHashMap() throws DBException {
    // Arrange
    DBVModel dbvModel = new DBVModel("42", new HashMap<>());

    // Act and Assert
    assertNull(dbvModel.getRealContainer(new LoggingProgressMonitor()));
  }

  /**
   * Test {@link DBVModel#getRealContainer(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBVModel#getRealContainer(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSObjectContainer DBVModel.getRealContainer(DBRProgressMonitor)"})
  public void testGetRealContainer_thenCallsGetDataSource() throws DBException {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(mock(DBPDataSource.class));
    DBVModel dbvModel = new DBVModel(dataSourceContainer);

    // Act
    DBSObjectContainer actualRealContainer =
        dbvModel.getRealContainer(new LoggingProgressMonitor());

    // Assert
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    assertNull(actualRealContainer);
  }

  /**
   * Test {@link DBVModel#getDataSource()}.
   *
   * <ul>
   *   <li>Given {@link DBVModel#DBVModel(String, Map)} with id is {@code 42} and map is {@link
   *       HashMap#HashMap()}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBVModel#getDataSource()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSource DBVModel.getDataSource()"})
  public void testGetDataSource_givenDBVModelWithIdIs42AndMapIsHashMap_thenReturnNull() {
    // Arrange
    DBVModel dbvModel = new DBVModel("42", new HashMap<>());

    // Act and Assert
    assertNull(dbvModel.getDataSource());
  }

  /**
   * Test {@link DBVModel#getDataSource()}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBVModel#getDataSource()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSource DBVModel.getDataSource()"})
  public void testGetDataSource_thenCallsGetDataSource() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(mock(DBPDataSource.class));

    // Act
    new DBVModel(dataSourceContainer).getDataSource();

    // Assert
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
  }

  /**
   * Test {@link DBVModel#findEntity(DBSEntity, boolean)} with {@code entity}, {@code createNew}.
   *
   * <p>Method under test: {@link DBVModel#findEntity(DBSEntity, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBVEntity DBVModel.findEntity(DBSEntity, boolean)"})
  public void testFindEntityWithEntityCreateNew() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel dbvModel = new DBVModel(dataSourceContainer);
    DBVEntity entity = new DBVEntity(null, "Name", "Description Column Names");

    // Act
    DBVEntity actualFindEntityResult = dbvModel.findEntity(entity, true);

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    assertNull(actualFindEntityResult);
  }

  /**
   * Test {@link DBVModel#findEntity(DBSEntity, boolean)} with {@code entity}, {@code createNew}.
   *
   * <ul>
   *   <li>Given {@link DBVModel#DBVModel(String, Map)} with id is {@code 42} and map is {@link
   *       HashMap#HashMap()}.
   * </ul>
   *
   * <p>Method under test: {@link DBVModel#findEntity(DBSEntity, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBVEntity DBVModel.findEntity(DBSEntity, boolean)"})
  public void testFindEntityWithEntityCreateNew_givenDBVModelWithIdIs42AndMapIsHashMap() {
    // Arrange
    DBVModel dbvModel = new DBVModel("42", new HashMap<>());
    DBVContainer parent = new DBVContainer(null, "Name", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent, "Name"), "Name", "Description Column Names");

    // Act and Assert
    assertNull(dbvModel.findEntity(entity, true));
  }

  /**
   * Test {@link DBVModel#findEntity(DBSEntity, boolean)} with {@code entity}, {@code createNew}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceContainer#getName()}.
   * </ul>
   *
   * <p>Method under test: {@link DBVModel#findEntity(DBSEntity, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBVEntity DBVModel.findEntity(DBSEntity, boolean)"})
  public void testFindEntityWithEntityCreateNew_thenCallsGetName() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getName()).thenReturn("Name");
    DBVModel dbvModel = new DBVModel(dataSourceContainer);
    DBVContainer parent = new DBVContainer(null, "Name", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent, "Name"), "Name", "Description Column Names");

    // Act
    DBVEntity actualFindEntityResult = dbvModel.findEntity(entity, true);

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer).getName();
    assertNull(actualFindEntityResult);
  }

  /**
   * Test {@link DBVModel#findEntity(DBSEntity, boolean)} with {@code entity}, {@code createNew}.
   *
   * <ul>
   *   <li>Then calls {@link DBVContainer#getParentObject()}.
   * </ul>
   *
   * <p>Method under test: {@link DBVModel#findEntity(DBSEntity, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBVEntity DBVModel.findEntity(DBSEntity, boolean)"})
  public void testFindEntityWithEntityCreateNew_thenCallsGetParentObject() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getName()).thenReturn("Name");
    DBVModel dbvModel = new DBVModel(dataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(null, "Name", new HashMap<>());
    when(parent.getParentObject()).thenReturn(new DBVContainer(parent2, "Name"));
    DBVContainer parent3 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent3, "Name"), "Name", "Description Column Names");

    // Act
    DBVEntity actualFindEntityResult = dbvModel.findEntity(entity, true);

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer).getName();
    verify(parent, atLeast(1)).getParentObject();
    assertNull(actualFindEntityResult);
  }

  /**
   * Test {@link DBVModel#findEntity(DBSEntity, String, boolean)} with {@code entity}, {@code
   * entityName}, {@code createNew}.
   *
   * <p>Method under test: {@link DBVModel#findEntity(DBSEntity, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBVEntity DBVModel.findEntity(DBSEntity, String, boolean)"})
  public void testFindEntityWithEntityEntityNameCreateNew() {
    // Arrange
    DBVModel dbvModel = new DBVModel("42", new HashMap<>());
    DBVContainer parent = new DBVContainer(null, "Name", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent, "Name"), "Name", "Description Column Names");

    // Act and Assert
    assertNull(dbvModel.findEntity(entity, "Entity Name", true));
  }

  /**
   * Test {@link DBVModel#findEntity(DBSEntity, String, boolean)} with {@code entity}, {@code
   * entityName}, {@code createNew}.
   *
   * <p>Method under test: {@link DBVModel#findEntity(DBSEntity, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBVEntity DBVModel.findEntity(DBSEntity, String, boolean)"})
  public void testFindEntityWithEntityEntityNameCreateNew2() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel dbvModel = new DBVModel(dataSourceContainer);
    DBVEntity entity = new DBVEntity(null, "Name", "Description Column Names");

    // Act
    DBVEntity actualFindEntityResult = dbvModel.findEntity(entity, "Entity Name", true);

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    assertNull(actualFindEntityResult);
  }

  /**
   * Test {@link DBVModel#findEntity(DBSEntity, String, boolean)} with {@code entity}, {@code
   * entityName}, {@code createNew}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceContainer#getName()}.
   * </ul>
   *
   * <p>Method under test: {@link DBVModel#findEntity(DBSEntity, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBVEntity DBVModel.findEntity(DBSEntity, String, boolean)"})
  public void testFindEntityWithEntityEntityNameCreateNew_thenCallsGetName() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getName()).thenReturn("Name");
    DBVModel dbvModel = new DBVModel(dataSourceContainer);
    DBVContainer parent = new DBVContainer(null, "Name", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent, "Name"), "Name", "Description Column Names");

    // Act
    DBVEntity actualFindEntityResult = dbvModel.findEntity(entity, "Entity Name", true);

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer).getName();
    assertNull(actualFindEntityResult);
  }

  /**
   * Test {@link DBVModel#findEntity(DBSEntity, String, boolean)} with {@code entity}, {@code
   * entityName}, {@code createNew}.
   *
   * <ul>
   *   <li>Then calls {@link DBVContainer#getParentObject()}.
   * </ul>
   *
   * <p>Method under test: {@link DBVModel#findEntity(DBSEntity, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBVEntity DBVModel.findEntity(DBSEntity, String, boolean)"})
  public void testFindEntityWithEntityEntityNameCreateNew_thenCallsGetParentObject() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getName()).thenReturn("Name");
    DBVModel dbvModel = new DBVModel(dataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(null, "Name", new HashMap<>());
    when(parent.getParentObject()).thenReturn(new DBVContainer(parent2, "Name"));
    DBVContainer parent3 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent3, "Name"), "Name", "Description Column Names");

    // Act
    DBVEntity actualFindEntityResult = dbvModel.findEntity(entity, "Entity Name", true);

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer).getName();
    verify(parent, atLeast(1)).getParentObject();
    assertNull(actualFindEntityResult);
  }

  /**
   * Test {@link DBVModel#serialize(DBRProgressMonitor, JsonWriter)} with {@code monitor}, {@code
   * json}.
   *
   * <p>Method under test: {@link DBVModel#serialize(DBRProgressMonitor, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.serialize(DBRProgressMonitor, JsonWriter)"})
  public void testSerializeWithMonitorJson() throws IOException, DBException {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBVModel dbvModel = new DBVModel(dataSourceContainer);
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    dbvModel.addContainer(new DBVContainer(parent2, "Name"));
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act
    dbvModel.serialize(monitor, new JsonWriter(new StringWriter()));

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
  }

  /**
   * Test {@link DBVModel#serialize(DBRProgressMonitor, JsonWriter)} with {@code monitor}, {@code
   * json}.
   *
   * <p>Method under test: {@link DBVModel#serialize(DBRProgressMonitor, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.serialize(DBRProgressMonitor, JsonWriter)"})
  public void testSerializeWithMonitorJson2() throws IOException, DBException {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBVModel dbvModel = new DBVModel(dataSourceContainer, source);
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVContainer container = new DBVContainer(parent2, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", new HashMap<>());
    dbvModel.addEntity(entity);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act
    dbvModel.serialize(monitor, new JsonWriter(new StringWriter()));

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
  }

  /**
   * Test {@link DBVModel#serialize(DBRProgressMonitor, JsonWriter)} with {@code monitor}, {@code
   * json}.
   *
   * <p>Method under test: {@link DBVModel#serialize(DBRProgressMonitor, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.serialize(DBRProgressMonitor, JsonWriter)"})
  public void testSerializeWithMonitorJson3() throws IOException, DBException {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());

    DBVEntity entity = new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "");
    entity.setColorOverrides(new ArrayList<>());
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntity entity3 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity3, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity2, copy);
    entity.addConstraint(constraint);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBVModel dbvModel = new DBVModel(dataSourceContainer, source);
    dbvModel.addEntity(entity);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act
    dbvModel.serialize(monitor, new JsonWriter(new StringWriter()));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBVModel#serialize(DBRProgressMonitor, JsonWriter)} with {@code monitor}, {@code
   * json}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSource#getContainer()}.
   * </ul>
   *
   * <p>Method under test: {@link DBVModel#serialize(DBRProgressMonitor, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.serialize(DBRProgressMonitor, JsonWriter)"})
  public void testSerializeWithMonitorJson_thenCallsGetContainer() throws IOException, DBException {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());

    DBVEntity entity = new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntity entity3 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity3, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity2, copy);
    entity.addConstraint(constraint);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBVModel dbvModel = new DBVModel(dataSourceContainer, source);
    dbvModel.addEntity(entity);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act
    dbvModel.serialize(monitor, new JsonWriter(new StringWriter()));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBVModel#serialize(DBRProgressMonitor, JsonWriter)} with {@code monitor}, {@code
   * json}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceContainer#getId()}.
   * </ul>
   *
   * <p>Method under test: {@link DBVModel#serialize(DBRProgressMonitor, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.serialize(DBRProgressMonitor, JsonWriter)"})
  public void testSerializeWithMonitorJson_thenCallsGetId() throws IOException, DBException {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel dbvModel = new DBVModel(dataSourceContainer);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act
    dbvModel.serialize(monitor, new JsonWriter(new StringWriter()));

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
  }

  /**
   * Test {@link DBVModel#serialize(DBRProgressMonitor, JsonWriter)} with {@code monitor}, {@code
   * json}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceContainer#getId()}.
   * </ul>
   *
   * <p>Method under test: {@link DBVModel#serialize(DBRProgressMonitor, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.serialize(DBRProgressMonitor, JsonWriter)"})
  public void testSerializeWithMonitorJson_thenCallsGetId2() throws IOException, DBException {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBVModel dbvModel = new DBVModel(dataSourceContainer, source);
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity entity = new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "");
    dbvModel.addEntity(entity);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act
    dbvModel.serialize(monitor, new JsonWriter(new StringWriter()));

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
  }

  /**
   * Test {@link DBVModel#serialize(XMLBuilder)} with {@code xml}.
   *
   * <p>Method under test: {@link DBVModel#serialize(XMLBuilder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.serialize(XMLBuilder)"})
  public void testSerializeWithXml() throws IOException {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel dbvModel = new DBVModel(dataSourceContainer);

    // Act
    dbvModel.serialize(new XMLBuilder(new ByteArrayOutputStream(), "UTF-8"));

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
  }

  /**
   * Test {@link DBVModel#serialize(XMLBuilder)} with {@code xml}.
   *
   * <p>Method under test: {@link DBVModel#serialize(XMLBuilder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.serialize(XMLBuilder)"})
  public void testSerializeWithXml2() throws IOException {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBVModel dbvModel = new DBVModel(dataSourceContainer);
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    dbvModel.addContainer(new DBVContainer(parent2, "Name"));

    // Act
    dbvModel.serialize(new XMLBuilder(new ByteArrayOutputStream(), "UTF-8"));

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
  }

  /**
   * Test {@link DBVModel#serialize(XMLBuilder)} with {@code xml}.
   *
   * <p>Method under test: {@link DBVModel#serialize(XMLBuilder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.serialize(XMLBuilder)"})
  public void testSerializeWithXml3() throws IOException {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBVModel dbvModel = new DBVModel(dataSourceContainer);
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    dbvModel.addEntity(entity);

    // Act
    dbvModel.serialize(new XMLBuilder(new ByteArrayOutputStream(), "UTF-8"));

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
  }

  /**
   * Test {@link DBVModel#serialize(XMLBuilder)} with {@code xml}.
   *
   * <p>Method under test: {@link DBVModel#serialize(XMLBuilder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.serialize(XMLBuilder)"})
  public void testSerializeWithXml4() throws IOException {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBVModel dbvModel = new DBVModel(dataSourceContainer);
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    dbvModel.addEntity(entity);
    DBVContainer parent3 = mock(DBVContainer.class);
    DBVContainer parent4 = new DBVContainer(parent3, "Name", new HashMap<>());
    dbvModel.addContainer(new DBVContainer(parent4, "Name"));

    // Act
    dbvModel.serialize(new XMLBuilder(new ByteArrayOutputStream(), "UTF-8"));

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
  }

  /**
   * Test {@link DBVModel#serialize(XMLBuilder)} with {@code xml}.
   *
   * <p>Method under test: {@link DBVModel#serialize(XMLBuilder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.serialize(XMLBuilder)"})
  public void testSerializeWithXml5() throws IOException {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBVModel dbvModel = new DBVModel(dataSourceContainer);
    dbvModel.setProperty("Name", DBPEvent.RENAME);
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    dbvModel.addContainer(new DBVContainer(parent2, "Name"));

    // Act
    dbvModel.serialize(new XMLBuilder(new ByteArrayOutputStream(), "UTF-8"));

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
  }

  /**
   * Test {@link DBVModel#serialize(XMLBuilder)} with {@code xml}.
   *
   * <p>Method under test: {@link DBVModel#serialize(XMLBuilder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.serialize(XMLBuilder)"})
  public void testSerializeWithXml6() throws IOException {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBVModel dbvModel = new DBVModel(dataSourceContainer, source);
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity entity = new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "");
    dbvModel.addEntity(entity);

    // Act
    dbvModel.serialize(new XMLBuilder(new ByteArrayOutputStream(), "UTF-8"));

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
  }

  /**
   * Test {@link DBVModel#serialize(XMLBuilder)} with {@code xml}.
   *
   * <p>Method under test: {@link DBVModel#serialize(XMLBuilder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.serialize(XMLBuilder)"})
  public void testSerializeWithXml7() throws IOException {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBVModel dbvModel = new DBVModel(dataSourceContainer, source);
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVContainer container = new DBVContainer(parent2, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", new HashMap<>());
    dbvModel.addEntity(entity);

    // Act
    dbvModel.serialize(new XMLBuilder(new ByteArrayOutputStream(), "UTF-8"));

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
  }

  /**
   * Test {@link DBVModel#serialize(XMLBuilder)} with {@code xml}.
   *
   * <p>Method under test: {@link DBVModel#serialize(XMLBuilder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.serialize(XMLBuilder)"})
  public void testSerializeWithXml8() throws IOException {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());

    DBVEntity entity = new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntity entity3 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity3, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity2, copy);
    entity.addConstraint(constraint);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBVModel dbvModel = new DBVModel(dataSourceContainer, source);
    dbvModel.addEntity(entity);

    // Act
    dbvModel.serialize(new XMLBuilder(new ByteArrayOutputStream(), "UTF-8"));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBVModel#serialize(XMLBuilder)} with {@code xml}.
   *
   * <p>Method under test: {@link DBVModel#serialize(XMLBuilder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.serialize(XMLBuilder)"})
  public void testSerializeWithXml9() throws IOException {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());

    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntity entity3 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity3, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity2, copy);
    entity.addConstraint(constraint);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBVModel dbvModel = new DBVModel(dataSourceContainer, source);
    dbvModel.addEntity(entity);

    // Act
    dbvModel.serialize(new XMLBuilder(new ByteArrayOutputStream(), "UTF-8"));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBVModel#serialize(XMLBuilder)} with {@code xml}.
   *
   * <p>Method under test: {@link DBVModel#serialize(XMLBuilder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.serialize(XMLBuilder)"})
  public void testSerializeWithXml10() throws IOException {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());

    DBVEntity entity = new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");
    entity.addForeignKey(new DBVEntityForeignKey(entity2));
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntity entity4 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity4, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity3, copy);
    entity.addConstraint(constraint);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBVModel dbvModel = new DBVModel(dataSourceContainer, source);
    dbvModel.addEntity(entity);

    // Act
    dbvModel.serialize(new XMLBuilder(new ByteArrayOutputStream(), "UTF-8"));

    // Assert
    verify(dbpDataSource, atLeast(1)).getContainer();
    verify(dbpDataSourceContainer, atLeast(1)).fireEvent(Mockito.<DBPEvent>any());
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(parent, atLeast(1)).getDataSource();
  }

  /**
   * Test {@link DBVModel#serialize(XMLBuilder)} with {@code xml}.
   *
   * <p>Method under test: {@link DBVModel#serialize(XMLBuilder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.serialize(XMLBuilder)"})
  public void testSerializeWithXml11() throws IOException {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());

    DBVEntity entity = new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "");
    Object[] attributeValues = new Object[] {DBPEvent.RENAME};
    DBVColorOverride color =
        new DBVColorOverride(
            "Attribute Name",
            DBCLogicalOperator.EQUALS,
            attributeValues,
            "Color Foreground",
            "Color Background");
    entity.addColorOverride(color);
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntity entity3 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity3, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity2, copy);
    entity.addConstraint(constraint);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBVModel dbvModel = new DBVModel(dataSourceContainer, source);
    dbvModel.addEntity(entity);

    // Act
    dbvModel.serialize(new XMLBuilder(new ByteArrayOutputStream(), "UTF-8"));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBVModel#serialize(XMLBuilder)} with {@code xml}.
   *
   * <p>Method under test: {@link DBVModel#serialize(XMLBuilder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.serialize(XMLBuilder)"})
  public void testSerializeWithXml12() throws IOException {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVContainer container = new DBVContainer(parent2, "Name");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity, copy);
    constraint.addAttribute("Name");

    DBVEntity entity3 = new DBVEntity(container, "Name", "");
    entity3.addConstraint(constraint);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBVModel dbvModel = new DBVModel(dataSourceContainer, source);
    dbvModel.addEntity(entity3);

    // Act
    dbvModel.serialize(new XMLBuilder(new ByteArrayOutputStream(), "UTF-8"));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBVModel#serialize(XMLBuilder)} with {@code xml}.
   *
   * <p>Method under test: {@link DBVModel#serialize(XMLBuilder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.serialize(XMLBuilder)"})
  public void testSerializeWithXml13() throws IOException {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVContainer container = new DBVContainer(parent2, "Name");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity, copy);
    constraint.addAttribute("name");
    constraint.addAttribute("Name");

    DBVEntity entity3 = new DBVEntity(container, "Name", "");
    entity3.addConstraint(constraint);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBVModel dbvModel = new DBVModel(dataSourceContainer, source);
    dbvModel.addEntity(entity3);

    // Act
    dbvModel.serialize(new XMLBuilder(new ByteArrayOutputStream(), "UTF-8"));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBVModel#serialize(XMLBuilder)} with {@code xml}.
   *
   * <p>Method under test: {@link DBVModel#serialize(XMLBuilder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.serialize(XMLBuilder)"})
  public void testSerializeWithXml14() throws IOException {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVContainer container = new DBVContainer(parent2, "Name");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity, copy);
    constraint.setUseAllColumns(true);
    constraint.addAttribute("Name");

    DBVEntity entity3 = new DBVEntity(container, "Name", "");
    entity3.addConstraint(constraint);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBVModel dbvModel = new DBVModel(dataSourceContainer, source);
    dbvModel.addEntity(entity3);

    // Act
    dbvModel.serialize(new XMLBuilder(new ByteArrayOutputStream(), "UTF-8"));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBVModel#serialize(XMLBuilder)} with {@code xml}.
   *
   * <p>Method under test: {@link DBVModel#serialize(XMLBuilder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.serialize(XMLBuilder)"})
  public void testSerializeWithXml15() throws IOException {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());

    DBVEntity entity = new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntity entity3 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity3, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity2, copy);
    entity.addConstraint(constraint);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBVModel dbvModel = new DBVModel(dataSourceContainer, source);
    dbvModel.setProperty("Name", DBPEvent.RENAME);
    dbvModel.addEntity(entity);

    // Act
    dbvModel.serialize(new XMLBuilder(new ByteArrayOutputStream(), "UTF-8"));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBVModel#serialize(XMLBuilder)} with {@code xml}.
   *
   * <p>Method under test: {@link DBVModel#serialize(XMLBuilder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.serialize(XMLBuilder)"})
  public void testSerializeWithXml16() throws IOException {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());

    DBVEntity entity = new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "");
    entity.setProperty("Name", DBPEvent.RENAME);
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntity entity3 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity3, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity2, copy);
    entity.addConstraint(constraint);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBVModel dbvModel = new DBVModel(dataSourceContainer, source);
    dbvModel.addEntity(entity);

    // Act
    dbvModel.serialize(new XMLBuilder(new ByteArrayOutputStream(), "UTF-8"));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBVModel#serialize(XMLBuilder)} with {@code xml}.
   *
   * <p>Method under test: {@link DBVModel#serialize(XMLBuilder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.serialize(XMLBuilder)"})
  public void testSerializeWithXml17() throws IOException {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());

    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntity entity3 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity3, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity2, copy);
    entity.addConstraint(constraint);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBVModel dbvModel = new DBVModel(dataSourceContainer, source);
    DBVContainer parent3 = mock(DBVContainer.class);
    DBVContainer parent4 = new DBVContainer(parent3, "Name", new HashMap<>());
    dbvModel.addContainer(new DBVContainer(parent4, "container"));
    dbvModel.addEntity(entity);

    XMLBuilder xml = new XMLBuilder(new StringWriter(), "UTF-8");
    xml.setBeautify(true);
    xml.addText(DBVContainer.CONFIG_PREFIX);

    // Act
    dbvModel.serialize(xml);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBVModel#serialize(XMLBuilder)} with {@code xml}.
   *
   * <p>Method under test: {@link DBVModel#serialize(XMLBuilder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.serialize(XMLBuilder)"})
  public void testSerializeWithXml18() throws IOException {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());

    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntity entity3 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity3, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity2, copy);
    entity.addConstraint(constraint);
    DBVContainer parent3 = mock(DBVContainer.class);
    DBVContainer parent4 = new DBVContainer(parent3, "Name", new HashMap<>());

    DBVContainer container2 = new DBVContainer(parent4, "container");
    DBVContainer parent5 = mock(DBVContainer.class);
    DBVContainer parent6 = new DBVContainer(parent5, "Name", new HashMap<>());
    DBVEntity entity4 =
        new DBVEntity(new DBVContainer(parent6, "Name"), "Name", "Description Column Names");
    container2.addEntity(entity4);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBVModel dbvModel = new DBVModel(dataSourceContainer, source);
    DBVContainer parent7 = mock(DBVContainer.class);
    DBVContainer parent8 = new DBVContainer(parent7, "Name", new HashMap<>());
    dbvModel.addContainer(new DBVContainer(parent8, "container"));
    dbvModel.addContainer(container2);
    dbvModel.addEntity(entity);

    XMLBuilder xml = new XMLBuilder(new StringWriter(), "UTF-8");
    xml.setBeautify(true);
    xml.addText(DBVContainer.CONFIG_PREFIX);

    // Act
    dbvModel.serialize(xml);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBVModel#serialize(XMLBuilder)} with {@code xml}.
   *
   * <p>Method under test: {@link DBVModel#serialize(XMLBuilder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.serialize(XMLBuilder)"})
  public void testSerializeWithXml19() throws IOException {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());

    DBVEntity entity = new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntity entity3 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity3, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity2, copy);
    entity.addConstraint(constraint);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBVModel container2 = new DBVModel(dataSourceContainer, source);
    DBVContainer parent3 = mock(DBVContainer.class);
    DBVContainer parent4 = new DBVContainer(parent3, "Name", new HashMap<>());
    DBVEntity entity4 =
        new DBVEntity(new DBVContainer(parent4, "Name"), "Name", "Description Column Names");
    container2.addEntity(entity4);

    DBPDataSourceContainer dataSourceContainer3 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer3.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer4 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer4.getId()).thenReturn("42");
    DBVModel source2 = new DBVModel(dataSourceContainer4);

    DBVModel dbvModel = new DBVModel(dataSourceContainer3, source2);
    DBVContainer parent5 = mock(DBVContainer.class);
    DBVContainer parent6 = new DBVContainer(parent5, "Name", new HashMap<>());
    dbvModel.addContainer(new DBVContainer(parent6, "container"));
    DBVContainer parent7 = mock(DBVContainer.class);
    DBVContainer parent8 = new DBVContainer(parent7, "Name", new HashMap<>());
    dbvModel.addContainer(new DBVContainer(parent8, "container"));
    dbvModel.addContainer(container2);
    dbvModel.addEntity(entity);

    XMLBuilder xml = new XMLBuilder(new StringWriter(), "UTF-8");
    xml.setBeautify(true);
    xml.addText(DBVContainer.CONFIG_PREFIX);

    // Act
    dbvModel.serialize(xml);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer3, atLeast(1)).getId();
    verify(dataSourceContainer4, atLeast(1)).getId();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBVModel#serialize(XMLBuilder)} with {@code xml}.
   *
   * <ul>
   *   <li>Given {@link DBVContainer#CONFIG_PREFIX}.
   * </ul>
   *
   * <p>Method under test: {@link DBVModel#serialize(XMLBuilder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.serialize(XMLBuilder)"})
  public void testSerializeWithXml_givenConfig_prefix() throws IOException {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());

    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntity entity3 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity3, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity2, copy);
    entity.addConstraint(constraint);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBVModel dbvModel = new DBVModel(dataSourceContainer, source);
    dbvModel.addEntity(entity);

    XMLBuilder xml = new XMLBuilder(new StringWriter(), "UTF-8");
    xml.addText(DBVContainer.CONFIG_PREFIX);

    // Act
    dbvModel.serialize(xml);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBVModel#getModelParser()}.
   *
   * <p>Method under test: {@link DBVModel#getModelParser()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SAXListener DBVModel.getModelParser()"})
  public void testGetModelParser() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    // Act
    SAXListener actualModelParser = new DBVModel(dataSourceContainer).getModelParser();

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    assertTrue(actualModelParser instanceof ModelParser);
  }

  /**
   * Test {@link DBVModel#copyFrom(DBVModel)} with {@code model}.
   *
   * <p>Method under test: {@link DBVModel#copyFrom(DBVModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.copyFrom(DBVModel)"})
  public void testCopyFromWithModel() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel dbvModel = new DBVModel(dataSourceContainer);

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");

    // Act
    dbvModel.copyFrom(new DBVModel(dataSourceContainer2));

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
  }

  /**
   * Test {@link DBVModel#copyFrom(DBVModel)} with {@code model}.
   *
   * <p>Method under test: {@link DBVModel#copyFrom(DBVModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.copyFrom(DBVModel)"})
  public void testCopyFromWithModel2() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel dbvModel = new DBVModel(dataSourceContainer);

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");

    DBVModel model = new DBVModel(dataSourceContainer2);
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    model.addContainer(new DBVContainer(parent2, "Name"));

    // Act
    dbvModel.copyFrom(model);

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
  }

  /**
   * Test {@link DBVModel#copyFrom(DBVModel)} with {@code model}.
   *
   * <p>Method under test: {@link DBVModel#copyFrom(DBVModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.copyFrom(DBVModel)"})
  public void testCopyFromWithModel3() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel dbvModel = new DBVModel(dataSourceContainer);

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());

    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");
    entity.addForeignKey(new DBVEntityForeignKey(entity2));
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntity entity4 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity4, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity3, copy);
    entity.addConstraint(constraint);

    DBVModel model = new DBVModel(dataSourceContainer2);
    model.addEntity(entity);

    // Act
    dbvModel.copyFrom(model);

    // Assert
    verify(dbpDataSource, atLeast(1)).getContainer();
    verify(dbpDataSourceContainer, atLeast(1)).fireEvent(Mockito.<DBPEvent>any());
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(parent, atLeast(1)).getDataSource();
  }

  /**
   * Test {@link DBVModel#copyFrom(DBVModel)} with {@code model}.
   *
   * <p>Method under test: {@link DBVModel#copyFrom(DBVModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.copyFrom(DBVModel)"})
  public void testCopyFromWithModel4() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel dbvModel = new DBVModel(dataSourceContainer);

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());

    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    Object[] attributeValues = new Object[] {DBPEvent.RENAME};
    DBVColorOverride color =
        new DBVColorOverride(
            "Attribute Name",
            DBCLogicalOperator.EQUALS,
            attributeValues,
            "Color Foreground",
            "Color Background");
    entity.addColorOverride(color);
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntity entity3 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity3, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity2, copy);
    entity.addConstraint(constraint);

    DBVModel model = new DBVModel(dataSourceContainer2);
    model.addEntity(entity);

    // Act
    dbvModel.copyFrom(model);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBVModel#copyFrom(DBVModel)} with {@code model}.
   *
   * <p>Method under test: {@link DBVModel#copyFrom(DBVModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.copyFrom(DBVModel)"})
  public void testCopyFromWithModel5() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel dbvModel = new DBVModel(dataSourceContainer);

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVContainer container = new DBVContainer(parent2, "Name");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity, copy);
    constraint.addAttribute("Name");

    DBVEntity entity3 = new DBVEntity(container, "Name", "Description Column Names");
    entity3.addConstraint(constraint);

    DBVModel model = new DBVModel(dataSourceContainer2);
    model.addEntity(entity3);

    // Act
    dbvModel.copyFrom(model);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBVModel#copyFrom(DBVModel)} with {@code model}.
   *
   * <p>Method under test: {@link DBVModel#copyFrom(DBVModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.copyFrom(DBVModel)"})
  public void testCopyFromWithModel6() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel dbvModel = new DBVModel(dataSourceContainer);

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());

    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    entity.setColorOverrides(new ArrayList<>());
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntity entity3 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity3, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity2, copy);
    entity.addConstraint(constraint);

    DBVModel model = new DBVModel(dataSourceContainer2);
    model.addEntity(entity);

    // Act
    dbvModel.copyFrom(model);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBVModel#copyFrom(DBVModel)} with {@code model}.
   *
   * <ul>
   *   <li>Given {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link DBVModel#copyFrom(DBVModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.copyFrom(DBVModel)"})
  public void testCopyFromWithModel_givenName() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel dbvModel = new DBVModel(dataSourceContainer);

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");

    DBVModel model = new DBVModel(dataSourceContainer2);
    model.setProperty("Name", DBPEvent.RENAME);
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    model.addContainer(new DBVContainer(parent2, "Name"));

    // Act
    dbvModel.copyFrom(model);

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
  }

  /**
   * Test {@link DBVModel#copyFrom(DBVModel)} with {@code model}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSource#getContainer()}.
   * </ul>
   *
   * <p>Method under test: {@link DBVModel#copyFrom(DBVModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.copyFrom(DBVModel)"})
  public void testCopyFromWithModel_thenCallsGetContainer() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel dbvModel = new DBVModel(dataSourceContainer);

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());

    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntity entity3 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity3, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity2, copy);
    entity.addConstraint(constraint);

    DBVModel model = new DBVModel(dataSourceContainer2);
    model.addEntity(entity);

    // Act
    dbvModel.copyFrom(model);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBVModel#copyFrom(DBVModel)} with {@code model}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceContainer#getId()}.
   * </ul>
   *
   * <p>Method under test: {@link DBVModel#copyFrom(DBVModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.copyFrom(DBVModel)"})
  public void testCopyFromWithModel_thenCallsGetId() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel dbvModel = new DBVModel(dataSourceContainer);

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");

    DBVModel model = new DBVModel(dataSourceContainer2);
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    model.addEntity(entity);

    // Act
    dbvModel.copyFrom(model);

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
  }

  /**
   * Test {@link DBVModel#addToCache(DBVEntityForeignKey)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link DBVEntityForeignKey} {@link DBVEntityForeignKey#getRefEntityId()} return
   *       {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link DBVModel#addToCache(DBVEntityForeignKey)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.addToCache(DBVEntityForeignKey)"})
  public void testAddToCache_given42_whenDBVEntityForeignKeyGetRefEntityIdReturn42() {
    // Arrange
    DBVEntityForeignKey foreignKey = mock(DBVEntityForeignKey.class);
    when(foreignKey.getRefEntityId()).thenReturn("42");

    // Act
    DBVModel.addToCache(foreignKey);

    // Assert
    verify(foreignKey).getRefEntityId();
  }

  /**
   * Test {@link DBVModel#addToCache(DBVEntityForeignKey)}.
   *
   * <ul>
   *   <li>Given {@code foo}.
   *   <li>When {@link DBVEntityForeignKey} {@link DBVEntityForeignKey#getRefEntityId()} return
   *       {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link DBVModel#addToCache(DBVEntityForeignKey)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.addToCache(DBVEntityForeignKey)"})
  public void testAddToCache_givenFoo_whenDBVEntityForeignKeyGetRefEntityIdReturnFoo() {
    // Arrange
    DBVEntityForeignKey foreignKey = mock(DBVEntityForeignKey.class);
    when(foreignKey.getRefEntityId()).thenReturn("foo");

    // Act
    DBVModel.addToCache(foreignKey);

    // Assert
    verify(foreignKey).getRefEntityId();
  }

  /**
   * Test {@link DBVModel#removeFromCache(DBVEntityForeignKey)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>Then calls {@link DBVEntityForeignKey#getRefEntityId()}.
   * </ul>
   *
   * <p>Method under test: {@link DBVModel#removeFromCache(DBVEntityForeignKey)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.removeFromCache(DBVEntityForeignKey)"})
  public void testRemoveFromCache_given42_thenCallsGetRefEntityId() {
    // Arrange
    DBVEntityForeignKey foreignKey = mock(DBVEntityForeignKey.class);
    when(foreignKey.getRefEntityId()).thenReturn("42");

    // Act
    DBVModel.removeFromCache(foreignKey);

    // Assert
    verify(foreignKey).getRefEntityId();
  }

  /**
   * Test {@link DBVModel#resetData()}.
   *
   * <p>Method under test: {@link DBVModel#resetData()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModel.resetData()"})
  public void testResetData() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    // Act
    new DBVModel(dataSourceContainer).resetData();

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
  }
}

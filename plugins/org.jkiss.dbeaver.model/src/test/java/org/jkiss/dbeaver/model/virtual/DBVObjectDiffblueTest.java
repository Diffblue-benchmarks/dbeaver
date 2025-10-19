package org.jkiss.dbeaver.model.virtual;

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
import java.util.HashMap;
import java.util.Map;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.app.DBPProject;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBVObjectDiffblueTest {
  /**
   * Test {@link DBVObject#isPersisted()}.
   *
   * <p>Method under test: {@link DBVObject#isPersisted()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBVObject.isPersisted()"})
  public void testIsPersisted() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    // Act
    boolean actualIsPersistedResult = new DBVModel(dataSourceContainer).isPersisted();

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    assertTrue(actualIsPersistedResult);
  }

  /**
   * Test {@link DBVObject#getTransformSettings()}.
   *
   * <p>Method under test: {@link DBVObject#getTransformSettings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBVTransformSettings DBVObject.getTransformSettings()"})
  public void testGetTransformSettings() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    // Act
    DBVTransformSettings actualTransformSettings =
        new DBVModel(dataSourceContainer).getTransformSettings();

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    assertNull(actualTransformSettings);
  }

  /**
   * Test {@link DBVObject#setTransformSettings(DBVTransformSettings)}.
   *
   * <p>Method under test: {@link DBVObject#setTransformSettings(DBVTransformSettings)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVObject.setTransformSettings(DBVTransformSettings)"})
  public void testSetTransformSettings() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel dbvModel = new DBVModel(dataSourceContainer);
    DBVTransformSettings transformSettings = new DBVTransformSettings();

    // Act
    dbvModel.setTransformSettings(transformSettings);

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    assertSame(transformSettings, dbvModel.getTransformSettings());
  }

  /**
   * Test {@link DBVObject#getProperty(String)}.
   *
   * <p>Method under test: {@link DBVObject#getProperty(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object DBVObject.getProperty(String)"})
  public void testGetProperty() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBVModel dbvModel = new DBVModel(dataSourceContainer);
    dbvModel.setProperty("Name", DBPEvent.RENAME);

    // Act
    dbvModel.getProperty("Name");

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
  }

  /**
   * Test {@link DBVObject#getProperty(String)}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBVObject#getProperty(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object DBVObject.getProperty(String)"})
  public void testGetProperty_thenReturnNull() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    // Act
    Object actualProperty = new DBVModel(dataSourceContainer).getProperty("Name");

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    assertNull(actualProperty);
  }

  /**
   * Test {@link DBVObject#setProperty(String, Object)}.
   *
   * <p>Method under test: {@link DBVObject#setProperty(String, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVObject.setProperty(String, Object)"})
  public void testSetProperty() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel dbvModel = new DBVModel(dataSourceContainer);
    Object object = DBPEvent.RENAME;

    // Act
    dbvModel.setProperty("Name", object);

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    Map<String, Object> properties = dbvModel.getProperties();
    assertEquals(1, properties.size());
    assertTrue(dbvModel.hasValuableData());
    assertSame(object, properties.get("Name"));
  }

  /**
   * Test {@link DBVObject#setProperty(String, Object)}.
   *
   * <p>Method under test: {@link DBVObject#setProperty(String, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVObject.setProperty(String, Object)"})
  public void testSetProperty2() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel dbvModel = new DBVModel(dataSourceContainer);

    // Act
    dbvModel.setProperty("Name", null);

    // Assert that nothing has changed
    verify(dataSourceContainer, atLeast(1)).getId();
    assertFalse(dbvModel.hasValuableData());
    assertTrue(dbvModel.getProperties().isEmpty());
  }

  /**
   * Test {@link DBVObject#setProperty(String, Object)}.
   *
   * <p>Method under test: {@link DBVObject#setProperty(String, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVObject.setProperty(String, Object)"})
  public void testSetProperty3() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");

    DBVModel src = new DBVModel(dataSourceContainer2);
    src.setProperty("Name", DBPEvent.RENAME);

    DBVModel dbvModel = new DBVModel(dataSourceContainer);
    dbvModel.copyFrom((DBVObject) src);

    // Act
    dbvModel.setProperty("Name", null);

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    assertFalse(dbvModel.hasValuableData());
    assertTrue(dbvModel.getProperties().isEmpty());
  }

  /**
   * Test {@link DBVObject#getProperties()}.
   *
   * <ul>
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link DBVObject#getProperties()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map DBVObject.getProperties()"})
  public void testGetProperties_thenReturnEmpty() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    // Act
    Map<String, Object> actualProperties = new DBVModel(dataSourceContainer).getProperties();

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    assertTrue(actualProperties.isEmpty());
  }

  /**
   * Test {@link DBVObject#getProperties()}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link DBVObject#getProperties()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map DBVObject.getProperties()"})
  public void testGetProperties_thenReturnSizeIsOne() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBVModel dbvModel = new DBVModel(dataSourceContainer);
    dbvModel.setProperty("Name", DBPEvent.RENAME);

    // Act
    Map<String, Object> actualProperties = dbvModel.getProperties();

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    assertEquals(1, actualProperties.size());
    assertTrue(actualProperties.containsKey("Name"));
  }

  /**
   * Test {@link DBVObject#clearProperties()}.
   *
   * <p>Method under test: {@link DBVObject#clearProperties()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVObject.clearProperties()"})
  public void testClearProperties() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    // Act
    new DBVModel(dataSourceContainer).clearProperties();

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
  }

  /**
   * Test {@link DBVObject#copyFrom(DBVObject)}.
   *
   * <ul>
   *   <li>Given {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link DBVObject#copyFrom(DBVObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVObject.copyFrom(DBVObject)"})
  public void testCopyFrom_givenName() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel dbvModel = new DBVModel(dataSourceContainer);

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");

    DBVModel src = new DBVModel(dataSourceContainer2);
    src.setProperty("Name", DBPEvent.RENAME);

    // Act
    dbvModel.copyFrom((DBVObject) src);

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
  }

  /**
   * Test {@link DBVObject#copyFrom(DBVObject)}.
   *
   * <ul>
   *   <li>When {@link DBVModel#DBVModel(DBPDataSourceContainer)} with dataSourceContainer is {@link
   *       DBPDataSourceContainer}.
   * </ul>
   *
   * <p>Method under test: {@link DBVObject#copyFrom(DBVObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVObject.copyFrom(DBVObject)"})
  public void testCopyFrom_whenDBVModelWithDataSourceContainerIsDBPDataSourceContainer() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel dbvModel = new DBVModel(dataSourceContainer);

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");

    // Act
    dbvModel.copyFrom((DBVObject) new DBVModel(dataSourceContainer2));

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
  }

  /**
   * Test {@link DBVObject#loadPropertiesFrom(Map, String)}.
   *
   * <p>Method under test: {@link DBVObject#loadPropertiesFrom(Map, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVObject.loadPropertiesFrom(Map, String)"})
  public void testLoadPropertiesFrom() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel dbvModel = new DBVModel(dataSourceContainer);

    // Act
    dbvModel.loadPropertiesFrom(new HashMap<>(), "Elem Name");

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
  }

  /**
   * Test {@link DBVObject#persistConfiguration()}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceContainer#getId()}.
   * </ul>
   *
   * <p>Method under test: {@link DBVObject#persistConfiguration()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVObject.persistConfiguration()"})
  public void testPersistConfiguration_thenCallsGetId() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.persistConfiguration()).thenReturn(true);
    when(dataSourceContainer.getId()).thenReturn("42");

    // Act
    new DBVModel(dataSourceContainer).persistConfiguration();

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer).persistConfiguration();
  }

  /**
   * Test {@link DBVObject#getDataSourceContainer()}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceContainer#getId()}.
   * </ul>
   *
   * <p>Method under test: {@link DBVObject#getDataSourceContainer()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSourceContainer DBVObject.getDataSourceContainer()"})
  public void testGetDataSourceContainer_thenCallsGetId() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel parent = new DBVModel(dataSourceContainer);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());

    // Act
    new DBVContainer(parent2, "Name").getDataSourceContainer();

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
  }

  /**
   * Test {@link DBVObject#getDataSourceContainer()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBVObject#getDataSourceContainer()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSourceContainer DBVObject.getDataSourceContainer()"})
  public void testGetDataSourceContainer_thenReturnNull() {
    // Arrange
    DBVContainer parent = new DBVContainer(null, "Name", new HashMap<>());

    // Act and Assert
    assertNull(new DBVContainer(parent, "Name").getDataSourceContainer());
  }

  /**
   * Test {@link DBVObject#getProject()}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSourceContainer} {@link DBPDataSourceContainer#getId()} return {@code
   *       42}.
   *   <li>Then calls {@link DBPDataSourceContainer#getId()}.
   * </ul>
   *
   * <p>Method under test: {@link DBVObject#getProject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPProject DBVObject.getProject()"})
  public void testGetProject_givenDBPDataSourceContainerGetIdReturn42_thenCallsGetId() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getProject()).thenReturn(mock(DBPProject.class));

    // Act
    new DBVModel(dataSourceContainer).getProject();

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer).getProject();
  }

  /**
   * Test {@link DBVObject#getProject()}.
   *
   * <ul>
   *   <li>Given {@link DBVModel#DBVModel(String, Map)} with id is {@code 42} and map is {@link
   *       HashMap#HashMap()}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBVObject#getProject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPProject DBVObject.getProject()"})
  public void testGetProject_givenDBVModelWithIdIs42AndMapIsHashMap_thenReturnNull() {
    // Arrange
    DBVModel dbvModel = new DBVModel("42", new HashMap<>());

    // Act and Assert
    assertNull(dbvModel.getProject());
  }
}

package org.jkiss.dbeaver.model.virtual;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.app.DBPDataSourceRegistry;
import org.jkiss.dbeaver.model.app.DBPProject;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSEntityAttribute;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class DBVEntityForeignKeyColumnDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBVEntityForeignKeyColumn#DBVEntityForeignKeyColumn(DBVEntityForeignKey, String,
   *       String)}
   *   <li>{@link DBVEntityForeignKeyColumn#toString()}
   *   <li>{@link DBVEntityForeignKeyColumn#getAttributeName()}
   *   <li>{@link DBVEntityForeignKeyColumn#getName()}
   *   <li>{@link DBVEntityForeignKeyColumn#getRefAttributeName()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBVEntityForeignKeyColumn.<init>(DBVEntityForeignKey, String, String)",
    "String DBVEntityForeignKeyColumn.getAttributeName()",
    "String DBVEntityForeignKeyColumn.getName()",
    "String DBVEntityForeignKeyColumn.getRefAttributeName()",
    "String DBVEntityForeignKeyColumn.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    DBVEntity entity =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");

    // Act
    DBVEntityForeignKeyColumn actualDbvEntityForeignKeyColumn =
        new DBVEntityForeignKeyColumn(
            new DBVEntityForeignKey(entity), "Attribute Name", "Ref Attribute Name");
    String actualToStringResult = actualDbvEntityForeignKeyColumn.toString();
    String actualAttributeName = actualDbvEntityForeignKeyColumn.getAttributeName();
    String actualName = actualDbvEntityForeignKeyColumn.getName();

    // Assert
    assertEquals("Attribute Name", actualAttributeName);
    assertEquals("Attribute Name", actualName);
    assertEquals("Attribute Name:Ref Attribute Name", actualToStringResult);
    assertEquals("Ref Attribute Name", actualDbvEntityForeignKeyColumn.getRefAttributeName());
  }

  /**
   * Test {@link DBVEntityForeignKeyColumn#DBVEntityForeignKeyColumn(DBVEntityForeignKey,
   * DBVEntityForeignKeyColumn)}.
   *
   * <ul>
   *   <li>Then return {@code Attribute Name}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DBVEntityForeignKeyColumn#DBVEntityForeignKeyColumn(DBVEntityForeignKey,
   * DBVEntityForeignKeyColumn)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBVEntityForeignKeyColumn.<init>(DBVEntityForeignKey, DBVEntityForeignKeyColumn)"
  })
  public void testNewDBVEntityForeignKeyColumn_thenReturnAttributeName() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityForeignKey foreignKey = new DBVEntityForeignKey(entity);
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityForeignKeyColumn copy =
        new DBVEntityForeignKeyColumn(
            new DBVEntityForeignKey(entity2), "Attribute Name", "Ref Attribute Name");

    // Act
    DBVEntityForeignKeyColumn actualDbvEntityForeignKeyColumn =
        new DBVEntityForeignKeyColumn(foreignKey, copy);

    // Assert
    assertEquals("Attribute Name", actualDbvEntityForeignKeyColumn.getAttributeName());
    assertEquals("Attribute Name", actualDbvEntityForeignKeyColumn.getName());
    assertEquals("Ref Attribute Name", actualDbvEntityForeignKeyColumn.getRefAttributeName());
    assertNull(actualDbvEntityForeignKeyColumn.getAttribute());
    assertNull(actualDbvEntityForeignKeyColumn.getReferencedColumn());
  }

  /**
   * Test {@link DBVEntityForeignKeyColumn#getAttribute()}.
   *
   * <p>Method under test: {@link DBVEntityForeignKeyColumn#getAttribute()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSEntityAttribute DBVEntityForeignKeyColumn.getAttribute()"})
  public void testGetAttribute() throws DBException {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getRealContainer(Mockito.<DBRProgressMonitor>any()))
        .thenThrow(new DBException("An error occurred"));
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityForeignKey foreignKey = new DBVEntityForeignKey(entity);
    DBVEntityForeignKey foreignKey2 = new DBVEntityForeignKey(mock(DBVEntity.class));
    DBVEntityForeignKeyColumn copy =
        new DBVEntityForeignKeyColumn(foreignKey2, "Attribute Name", "Ref Attribute Name");

    DBVEntityForeignKeyColumn dbvEntityForeignKeyColumn =
        new DBVEntityForeignKeyColumn(foreignKey, copy);

    // Act
    DBSEntityAttribute actualAttribute = dbvEntityForeignKeyColumn.getAttribute();

    // Assert
    verify(parent).getRealContainer(isA(DBRProgressMonitor.class));
    assertNull(actualAttribute);
  }

  /**
   * Test {@link DBVEntityForeignKeyColumn#getAttribute()}.
   *
   * <p>Method under test: {@link DBVEntityForeignKeyColumn#getAttribute()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSEntityAttribute DBVEntityForeignKeyColumn.getAttribute()"})
  public void testGetAttribute2() throws DBException {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBVModel dbvModel = new DBVModel(dataSourceContainer);
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "org.jkiss.dbeaver.model", new HashMap<>());
    dbvModel.addContainer(new DBVContainer(parent2, "org.jkiss.dbeaver.model"));

    DBVContainer parent3 = mock(DBVContainer.class);
    when(parent3.getRealContainer(Mockito.<DBRProgressMonitor>any())).thenReturn(dbvModel);
    DBVContainer container = new DBVContainer(parent3, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityForeignKey foreignKey = new DBVEntityForeignKey(entity);
    DBVEntityForeignKey foreignKey2 = new DBVEntityForeignKey(mock(DBVEntity.class));
    DBVEntityForeignKeyColumn copy =
        new DBVEntityForeignKeyColumn(foreignKey2, "Attribute Name", "Ref Attribute Name");

    DBVEntityForeignKeyColumn dbvEntityForeignKeyColumn =
        new DBVEntityForeignKeyColumn(foreignKey, copy);

    // Act
    DBSEntityAttribute actualAttribute = dbvEntityForeignKeyColumn.getAttribute();

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(parent3).getRealContainer(isA(DBRProgressMonitor.class));
    assertNull(actualAttribute);
  }

  /**
   * Test {@link DBVEntityForeignKeyColumn#getAttribute()}.
   *
   * <p>Method under test: {@link DBVEntityForeignKeyColumn#getAttribute()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSEntityAttribute DBVEntityForeignKeyColumn.getAttribute()"})
  public void testGetAttribute3() throws DBException {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getRealContainer(Mockito.<DBRProgressMonitor>any()))
        .thenThrow(new DBException("An error occurred"));
    DBVContainer parent2 = new DBVContainer(parent, "org.jkiss.dbeaver.model");
    DBVContainer container = new DBVContainer(parent2, "org.jkiss.dbeaver.model", new HashMap<>());
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityForeignKey foreignKey = new DBVEntityForeignKey(entity);
    DBVEntityForeignKey foreignKey2 = new DBVEntityForeignKey(mock(DBVEntity.class));
    DBVEntityForeignKeyColumn copy =
        new DBVEntityForeignKeyColumn(foreignKey2, "Attribute Name", "Ref Attribute Name");

    DBVEntityForeignKeyColumn dbvEntityForeignKeyColumn =
        new DBVEntityForeignKeyColumn(foreignKey, copy);

    // Act
    DBSEntityAttribute actualAttribute = dbvEntityForeignKeyColumn.getAttribute();

    // Assert
    verify(parent).getRealContainer(isA(DBRProgressMonitor.class));
    assertNull(actualAttribute);
  }

  /**
   * Test {@link DBVEntityForeignKeyColumn#getAttribute()}.
   *
   * <p>Method under test: {@link DBVEntityForeignKeyColumn#getAttribute()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSEntityAttribute DBVEntityForeignKeyColumn.getAttribute()"})
  public void testGetAttribute4() throws DBException {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBVModel dbvModel = new DBVModel(dataSourceContainer);
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "org.jkiss.dbeaver.model", new HashMap<>());
    dbvModel.addContainer(new DBVContainer(parent2, "org.jkiss.dbeaver.model"));

    DBVContainer parent3 = mock(DBVContainer.class);
    when(parent3.getRealContainer(Mockito.<DBRProgressMonitor>any())).thenReturn(dbvModel);
    DBVContainer parent4 = new DBVContainer(parent3, "org.jkiss.dbeaver.model");
    DBVContainer container = new DBVContainer(parent4, "org.jkiss.dbeaver.model", new HashMap<>());
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityForeignKey foreignKey = new DBVEntityForeignKey(entity);
    DBVEntityForeignKey foreignKey2 = new DBVEntityForeignKey(mock(DBVEntity.class));
    DBVEntityForeignKeyColumn copy =
        new DBVEntityForeignKeyColumn(foreignKey2, "Attribute Name", "Ref Attribute Name");

    DBVEntityForeignKeyColumn dbvEntityForeignKeyColumn =
        new DBVEntityForeignKeyColumn(foreignKey, copy);

    // Act
    DBSEntityAttribute actualAttribute = dbvEntityForeignKeyColumn.getAttribute();

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(parent3).getRealContainer(isA(DBRProgressMonitor.class));
    assertNull(actualAttribute);
  }

  /**
   * Test {@link DBVEntityForeignKeyColumn#getAttribute()}.
   *
   * <p>Method under test: {@link DBVEntityForeignKeyColumn#getAttribute()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSEntityAttribute DBVEntityForeignKeyColumn.getAttribute()"})
  public void testGetAttribute5() {
    // Arrange
    DBVContainer container = new DBVContainer(null, "org.jkiss.dbeaver.model", new HashMap<>());
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityForeignKey foreignKey = new DBVEntityForeignKey(entity);
    DBVEntityForeignKey foreignKey2 = new DBVEntityForeignKey(mock(DBVEntity.class));
    DBVEntityForeignKeyColumn copy =
        new DBVEntityForeignKeyColumn(foreignKey2, "Attribute Name", "Ref Attribute Name");

    DBVEntityForeignKeyColumn dbvEntityForeignKeyColumn =
        new DBVEntityForeignKeyColumn(foreignKey, copy);

    // Act and Assert
    assertNull(dbvEntityForeignKeyColumn.getAttribute());
  }

  /**
   * Test {@link DBVEntityForeignKeyColumn#getAttribute()}.
   *
   * <p>Method under test: {@link DBVEntityForeignKeyColumn#getAttribute()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSEntityAttribute DBVEntityForeignKeyColumn.getAttribute()"})
  public void testGetAttribute6() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(mock(DBPDataSource.class));
    DBVModel parent = new DBVModel(dataSourceContainer);
    DBVContainer container = new DBVContainer(parent, "org.jkiss.dbeaver.model", new HashMap<>());
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityForeignKey foreignKey = new DBVEntityForeignKey(entity);
    DBVEntityForeignKey foreignKey2 = new DBVEntityForeignKey(mock(DBVEntity.class));
    DBVEntityForeignKeyColumn copy =
        new DBVEntityForeignKeyColumn(foreignKey2, "Attribute Name", "Ref Attribute Name");

    DBVEntityForeignKeyColumn dbvEntityForeignKeyColumn =
        new DBVEntityForeignKeyColumn(foreignKey, copy);

    // Act
    DBSEntityAttribute actualAttribute = dbvEntityForeignKeyColumn.getAttribute();

    // Assert
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    assertNull(actualAttribute);
  }

  /**
   * Test {@link DBVEntityForeignKeyColumn#getAttribute()}.
   *
   * <p>Method under test: {@link DBVEntityForeignKeyColumn#getAttribute()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSEntityAttribute DBVEntityForeignKeyColumn.getAttribute()"})
  public void testGetAttribute7() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(mock(DBPDataSource.class));
    DBVModel container = new DBVModel(dataSourceContainer);
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityForeignKey foreignKey = new DBVEntityForeignKey(entity);
    DBVEntityForeignKey foreignKey2 = new DBVEntityForeignKey(mock(DBVEntity.class));
    DBVEntityForeignKeyColumn copy =
        new DBVEntityForeignKeyColumn(foreignKey2, "Attribute Name", "Ref Attribute Name");

    DBVEntityForeignKeyColumn dbvEntityForeignKeyColumn =
        new DBVEntityForeignKeyColumn(foreignKey, copy);

    // Act
    DBSEntityAttribute actualAttribute = dbvEntityForeignKeyColumn.getAttribute();

    // Assert
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    assertNull(actualAttribute);
  }

  /**
   * Test {@link DBVEntityForeignKeyColumn#getAttribute()}.
   *
   * <ul>
   *   <li>Given {@link DBVContainer} {@link DBVContainer#getRealContainer(DBRProgressMonitor)}
   *       return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntityForeignKeyColumn#getAttribute()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSEntityAttribute DBVEntityForeignKeyColumn.getAttribute()"})
  public void testGetAttribute_givenDBVContainerGetRealContainerReturnNull() throws DBException {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getRealContainer(Mockito.<DBRProgressMonitor>any())).thenReturn(null);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityForeignKey foreignKey = new DBVEntityForeignKey(entity);
    DBVEntityForeignKey foreignKey2 = new DBVEntityForeignKey(mock(DBVEntity.class));
    DBVEntityForeignKeyColumn copy =
        new DBVEntityForeignKeyColumn(foreignKey2, "Attribute Name", "Ref Attribute Name");

    DBVEntityForeignKeyColumn dbvEntityForeignKeyColumn =
        new DBVEntityForeignKeyColumn(foreignKey, copy);

    // Act
    DBSEntityAttribute actualAttribute = dbvEntityForeignKeyColumn.getAttribute();

    // Assert
    verify(parent).getRealContainer(isA(DBRProgressMonitor.class));
    assertNull(actualAttribute);
  }

  /**
   * Test {@link DBVEntityForeignKeyColumn#getAttribute()}.
   *
   * <ul>
   *   <li>Given {@link DBVModel#DBVModel(String, Map)} with id is {@code 42} and map is {@link
   *       HashMap#HashMap()}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntityForeignKeyColumn#getAttribute()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSEntityAttribute DBVEntityForeignKeyColumn.getAttribute()"})
  public void testGetAttribute_givenDBVModelWithIdIs42AndMapIsHashMap() {
    // Arrange
    DBVModel container = new DBVModel("42", new HashMap<>());
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityForeignKey foreignKey = new DBVEntityForeignKey(entity);
    DBVEntityForeignKey foreignKey2 = new DBVEntityForeignKey(mock(DBVEntity.class));
    DBVEntityForeignKeyColumn copy =
        new DBVEntityForeignKeyColumn(foreignKey2, "Attribute Name", "Ref Attribute Name");

    DBVEntityForeignKeyColumn dbvEntityForeignKeyColumn =
        new DBVEntityForeignKeyColumn(foreignKey, copy);

    // Act and Assert
    assertNull(dbvEntityForeignKeyColumn.getAttribute());
  }

  /**
   * Test {@link DBVEntityForeignKeyColumn#getReferencedColumn()}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSource#getContainer()}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntityForeignKeyColumn#getReferencedColumn()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSEntityAttribute DBVEntityForeignKeyColumn.getReferencedColumn()"})
  public void testGetReferencedColumn_thenCallsGetContainer() {
    // Arrange
    DBPProject dbpProject = mock(DBPProject.class);
    when(dbpProject.getNavigatorModel()).thenReturn(null);

    DBPDataSourceRegistry dbpDataSourceRegistry = mock(DBPDataSourceRegistry.class);
    when(dbpDataSourceRegistry.getProject()).thenReturn(dbpProject);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getRegistry()).thenReturn(dbpDataSourceRegistry);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    DBVEntityForeignKey foreignKey = new DBVEntityForeignKey(entity);
    foreignKey.setRefEntityId("Foreign Key");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityForeignKey foreignKey2 = new DBVEntityForeignKey(entity2);
    DBVEntityForeignKey foreignKey3 = new DBVEntityForeignKey(mock(DBVEntity.class));
    DBVEntityForeignKeyColumn copy =
        new DBVEntityForeignKeyColumn(foreignKey3, "Attribute Name", "Ref Attribute Name");

    DBVEntityForeignKeyColumn copy2 = new DBVEntityForeignKeyColumn(foreignKey2, copy);

    DBVEntityForeignKeyColumn dbvEntityForeignKeyColumn =
        new DBVEntityForeignKeyColumn(foreignKey, copy2);

    // Act
    DBSEntityAttribute actualReferencedColumn = dbvEntityForeignKeyColumn.getReferencedColumn();

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).getRegistry();
    verify(dbpDataSourceRegistry).getProject();
    verify(dbpProject).getNavigatorModel();
    verify(parent).getDataSource();
    assertNull(actualReferencedColumn);
  }

  /**
   * Test {@link DBVEntityForeignKeyColumn#getReferencedColumn()}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSource#getContainer()}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntityForeignKeyColumn#getReferencedColumn()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSEntityAttribute DBVEntityForeignKeyColumn.getReferencedColumn()"})
  public void testGetReferencedColumn_thenCallsGetContainer2() {
    // Arrange
    DBPProject dbpProject = mock(DBPProject.class);
    when(dbpProject.getNavigatorModel()).thenReturn(null);

    DBPDataSourceRegistry dbpDataSourceRegistry = mock(DBPDataSourceRegistry.class);
    when(dbpDataSourceRegistry.getProject()).thenReturn(dbpProject);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getRegistry()).thenReturn(dbpDataSourceRegistry);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "%2F");
    DBVContainer container = new DBVContainer(parent2, "%2F", new HashMap<>());
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    DBVEntityForeignKey foreignKey = new DBVEntityForeignKey(entity);
    foreignKey.setRefEntityId("Foreign Key");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityForeignKey foreignKey2 = new DBVEntityForeignKey(entity2);
    DBVEntityForeignKey foreignKey3 = new DBVEntityForeignKey(mock(DBVEntity.class));
    DBVEntityForeignKeyColumn copy =
        new DBVEntityForeignKeyColumn(foreignKey3, "Attribute Name", "Ref Attribute Name");

    DBVEntityForeignKeyColumn copy2 = new DBVEntityForeignKeyColumn(foreignKey2, copy);

    DBVEntityForeignKeyColumn dbvEntityForeignKeyColumn =
        new DBVEntityForeignKeyColumn(foreignKey, copy2);

    // Act
    DBSEntityAttribute actualReferencedColumn = dbvEntityForeignKeyColumn.getReferencedColumn();

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).getRegistry();
    verify(dbpDataSourceRegistry).getProject();
    verify(dbpProject).getNavigatorModel();
    verify(parent).getDataSource();
    assertNull(actualReferencedColumn);
  }

  /**
   * Test {@link DBVEntityForeignKeyColumn#getReferencedColumn()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntityForeignKeyColumn#getReferencedColumn()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSEntityAttribute DBVEntityForeignKeyColumn.getReferencedColumn()"})
  public void testGetReferencedColumn_thenReturnNull() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityForeignKey foreignKey = new DBVEntityForeignKey(entity);
    DBVEntityForeignKey foreignKey2 = new DBVEntityForeignKey(mock(DBVEntity.class));
    DBVEntityForeignKeyColumn copy =
        new DBVEntityForeignKeyColumn(foreignKey2, "Attribute Name", "Ref Attribute Name");

    DBVEntityForeignKeyColumn dbvEntityForeignKeyColumn =
        new DBVEntityForeignKeyColumn(foreignKey, copy);

    // Act and Assert
    assertNull(dbvEntityForeignKeyColumn.getReferencedColumn());
  }
}

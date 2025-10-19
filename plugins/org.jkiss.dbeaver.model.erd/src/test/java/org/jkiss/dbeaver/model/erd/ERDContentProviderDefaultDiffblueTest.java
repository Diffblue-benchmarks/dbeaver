package org.jkiss.dbeaver.model.erd;

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
import java.util.ArrayList;
import java.util.List;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.app.DBPDataSourceRegistry;
import org.jkiss.dbeaver.model.app.DBPProject;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSEntityAssociation;
import org.jkiss.dbeaver.model.struct.DBSEntityAttribute;
import org.jkiss.dbeaver.model.struct.DBSEntityConstraintType;
import org.jkiss.dbeaver.model.virtual.DBVContainer;
import org.jkiss.dbeaver.model.virtual.DBVEntity;
import org.jkiss.dbeaver.model.virtual.DBVEntityAttribute;
import org.jkiss.dbeaver.model.virtual.DBVEntityConstraint;
import org.jkiss.dbeaver.model.virtual.DBVEntityForeignKey;
import org.jkiss.dbeaver.model.virtual.DBVModel;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class ERDContentProviderDefaultDiffblueTest {
  /**
   * Test {@link ERDContentProviderDefault#allowEntityDuplicates()}.
   *
   * <p>Method under test: {@link ERDContentProviderDefault#allowEntityDuplicates()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ERDContentProviderDefault.allowEntityDuplicates()"})
  public void testAllowEntityDuplicates() {
    // Arrange, Act and Assert
    assertFalse(new ERDContentProviderDefault().allowEntityDuplicates());
  }

  /**
   * Test {@link ERDContentProviderDefault#fillEntityFromObject(DBRProgressMonitor, ERDDiagram,
   * List, ERDEntity)} with {@code monitor}, {@code diagram}, {@code otherEntities}, {@code
   * erdEntity}.
   *
   * <p>Method under test: {@link ERDContentProviderDefault#fillEntityFromObject(DBRProgressMonitor,
   * ERDDiagram, List, ERDEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ERDContentProviderDefault.fillEntityFromObject(DBRProgressMonitor, ERDDiagram, List, ERDEntity)"
  })
  public void testFillEntityFromObjectWithMonitorDiagramOtherEntitiesErdEntity()
      throws DBException {
    // Arrange
    ERDContentProviderDefault erdContentProviderDefault = new ERDContentProviderDefault();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram diagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());
    ArrayList<ERDEntity> otherEntities = new ArrayList<>();

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getRealContainer(Mockito.<DBRProgressMonitor>any()))
        .thenThrow(new DBCException("An error occurred"));
    DBVContainer container2 = new DBVContainer(parent, "Name");
    DBVEntity dbvEntity = new DBVEntity(container2, "Name", "Description Column Names");

    ERDEntity erdEntity = new ERDEntity(mock(DBPDataSource.class));
    erdEntity.setObject(dbvEntity);

    // Act
    erdContentProviderDefault.fillEntityFromObject(monitor, diagram, otherEntities, erdEntity);

    // Assert
    verify(parent, atLeast(1)).getRealContainer(isA(DBRProgressMonitor.class));
  }

  /**
   * Test {@link ERDContentProviderDefault#fillEntityFromObject(DBRProgressMonitor, ERDDiagram,
   * List, ERDEntity)} with {@code monitor}, {@code diagram}, {@code otherEntities}, {@code
   * erdEntity}.
   *
   * <p>Method under test: {@link ERDContentProviderDefault#fillEntityFromObject(DBRProgressMonitor,
   * ERDDiagram, List, ERDEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ERDContentProviderDefault.fillEntityFromObject(DBRProgressMonitor, ERDDiagram, List, ERDEntity)"
  })
  public void testFillEntityFromObjectWithMonitorDiagramOtherEntitiesErdEntity2()
      throws DBException {
    // Arrange
    ERDContentProviderDefault erdContentProviderDefault = new ERDContentProviderDefault();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram diagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());
    ArrayList<ERDEntity> otherEntities = new ArrayList<>();

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getRealContainer(Mockito.<DBRProgressMonitor>any())).thenReturn(null);
    DBVContainer container2 = new DBVContainer(parent, "Name");
    DBVEntity dbvEntity = new DBVEntity(container2, "Name", "Description Column Names");

    ERDEntity erdEntity = new ERDEntity(mock(DBPDataSource.class));
    erdEntity.setObject(dbvEntity);

    // Act
    erdContentProviderDefault.fillEntityFromObject(monitor, diagram, otherEntities, erdEntity);

    // Assert
    verify(parent, atLeast(1)).getRealContainer(isA(DBRProgressMonitor.class));
  }

  /**
   * Test {@link ERDContentProviderDefault#fillEntityFromObject(DBRProgressMonitor, ERDDiagram,
   * List, ERDEntity)} with {@code monitor}, {@code diagram}, {@code otherEntities}, {@code
   * erdEntity}.
   *
   * <p>Method under test: {@link ERDContentProviderDefault#fillEntityFromObject(DBRProgressMonitor,
   * ERDDiagram, List, ERDEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ERDContentProviderDefault.fillEntityFromObject(DBRProgressMonitor, ERDDiagram, List, ERDEntity)"
  })
  public void testFillEntityFromObjectWithMonitorDiagramOtherEntitiesErdEntity3()
      throws DBCException {
    // Arrange
    ERDContentProviderDefault erdContentProviderDefault = new ERDContentProviderDefault();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram diagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());
    ArrayList<ERDEntity> otherEntities = new ArrayList<>();

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(mock(DBPDataSource.class));
    DBVModel container2 = new DBVModel(dataSourceContainer);
    DBVEntity dbvEntity = new DBVEntity(container2, "Name", "Description Column Names");

    ERDEntity erdEntity = new ERDEntity(mock(DBPDataSource.class));
    erdEntity.setObject(dbvEntity);

    // Act
    erdContentProviderDefault.fillEntityFromObject(monitor, diagram, otherEntities, erdEntity);

    // Assert
    verify(dataSourceContainer, atLeast(1)).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
  }

  /**
   * Test {@link ERDContentProviderDefault#fillEntityFromObject(DBRProgressMonitor, ERDDiagram,
   * List, ERDEntity)} with {@code monitor}, {@code diagram}, {@code otherEntities}, {@code
   * erdEntity}.
   *
   * <p>Method under test: {@link ERDContentProviderDefault#fillEntityFromObject(DBRProgressMonitor,
   * ERDDiagram, List, ERDEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ERDContentProviderDefault.fillEntityFromObject(DBRProgressMonitor, ERDDiagram, List, ERDEntity)"
  })
  public void testFillEntityFromObjectWithMonitorDiagramOtherEntitiesErdEntity4()
      throws DBCException {
    // Arrange
    ERDContentProviderDefault erdContentProviderDefault = new ERDContentProviderDefault();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram diagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());
    ArrayList<ERDEntity> otherEntities = new ArrayList<>();

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource);

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBVModel container2 = new DBVModel(dataSourceContainer, source);

    DBVEntity dbvEntity = new DBVEntity(container2, "Name", "Description Column Names");
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Name");
    dbvEntity.addVirtualAttribute(attribute);

    ERDEntity erdEntity = new ERDEntity(mock(DBPDataSource.class));
    erdEntity.setObject(dbvEntity);

    // Act
    erdContentProviderDefault.fillEntityFromObject(monitor, diagram, otherEntities, erdEntity);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
  }

  /**
   * Test {@link ERDContentProviderDefault#fillEntityFromObject(DBRProgressMonitor, ERDDiagram,
   * List, ERDEntity)} with {@code monitor}, {@code diagram}, {@code otherEntities}, {@code
   * erdEntity}.
   *
   * <p>Method under test: {@link ERDContentProviderDefault#fillEntityFromObject(DBRProgressMonitor,
   * ERDDiagram, List, ERDEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ERDContentProviderDefault.fillEntityFromObject(DBRProgressMonitor, ERDDiagram, List, ERDEntity)"
  })
  public void testFillEntityFromObjectWithMonitorDiagramOtherEntitiesErdEntity5()
      throws DBCException {
    // Arrange
    ERDContentProviderDefault erdContentProviderDefault = new ERDContentProviderDefault();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram diagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());
    ArrayList<ERDEntity> otherEntities = new ArrayList<>();

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource);

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBVModel container2 = new DBVModel(dataSourceContainer, source);

    DBVEntity dbvEntity = new DBVEntity(container2, "Name", "Description Column Names");
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntity entity3 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBSEntityConstraintType type =
        new DBSEntityConstraintType("42", "Name", "Localized Name", true, true, true, true);

    DBVEntityConstraint copy = new DBVEntityConstraint(entity3, type, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity2, copy);
    dbvEntity.addConstraint(constraint);
    DBVContainer container4 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity4 = new DBVEntity(container4, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity4, mock(DBVEntityAttribute.class), "Name");
    dbvEntity.addVirtualAttribute(attribute);

    ERDEntity erdEntity = new ERDEntity(mock(DBPDataSource.class));
    erdEntity.setObject(dbvEntity);

    // Act
    erdContentProviderDefault.fillEntityFromObject(monitor, diagram, otherEntities, erdEntity);

    // Assert
    verify(dbpDataSource, atLeast(1)).getContainer();
    verify(dbpDataSourceContainer, atLeast(1)).fireEvent(Mockito.<DBPEvent>any());
    verify(dataSourceContainer, atLeast(1)).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
  }

  /**
   * Test {@link ERDContentProviderDefault#fillEntityFromObject(DBRProgressMonitor, ERDDiagram,
   * List, ERDEntity)} with {@code monitor}, {@code diagram}, {@code otherEntities}, {@code
   * erdEntity}.
   *
   * <p>Method under test: {@link ERDContentProviderDefault#fillEntityFromObject(DBRProgressMonitor,
   * ERDDiagram, List, ERDEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ERDContentProviderDefault.fillEntityFromObject(DBRProgressMonitor, ERDDiagram, List, ERDEntity)"
  })
  public void testFillEntityFromObjectWithMonitorDiagramOtherEntitiesErdEntity6()
      throws DBCException {
    // Arrange
    ERDContentProviderDefault erdContentProviderDefault = new ERDContentProviderDefault();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram diagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());
    ArrayList<ERDEntity> otherEntities = new ArrayList<>();

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource);

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBVModel container2 = new DBVModel(dataSourceContainer, source);

    DBVEntity dbvEntity = new DBVEntity(container2, "Name", "Description Column Names");
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container3, "Name", "Description Column Names");
    dbvEntity.addForeignKey(new DBVEntityForeignKey(entity2));
    DBVContainer container4 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container4, "Name", "Description Column Names");
    DBVEntity entity4 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBSEntityConstraintType type =
        new DBSEntityConstraintType("42", "Name", "Localized Name", true, true, true, true);

    DBVEntityConstraint copy = new DBVEntityConstraint(entity4, type, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity3, copy);
    dbvEntity.addConstraint(constraint);
    DBVContainer container5 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity5 = new DBVEntity(container5, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity5, mock(DBVEntityAttribute.class), "Name");
    dbvEntity.addVirtualAttribute(attribute);

    ERDEntity erdEntity = new ERDEntity(mock(DBPDataSource.class));
    erdEntity.setObject(dbvEntity);

    // Act
    erdContentProviderDefault.fillEntityFromObject(monitor, diagram, otherEntities, erdEntity);

    // Assert
    verify(dbpDataSource, atLeast(1)).getContainer();
    verify(dbpDataSourceContainer, atLeast(1)).fireEvent(Mockito.<DBPEvent>any());
    verify(dataSourceContainer, atLeast(1)).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
  }

  /**
   * Test {@link ERDContentProviderDefault#fillEntityFromObject(DBRProgressMonitor, ERDDiagram,
   * List, ERDEntity)} with {@code monitor}, {@code diagram}, {@code otherEntities}, {@code
   * erdEntity}.
   *
   * <p>Method under test: {@link ERDContentProviderDefault#fillEntityFromObject(DBRProgressMonitor,
   * ERDDiagram, List, ERDEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ERDContentProviderDefault.fillEntityFromObject(DBRProgressMonitor, ERDDiagram, List, ERDEntity)"
  })
  public void testFillEntityFromObjectWithMonitorDiagramOtherEntitiesErdEntity7()
      throws DBCException {
    // Arrange
    ERDContentProviderDefault erdContentProviderDefault = new ERDContentProviderDefault();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram diagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());
    ArrayList<ERDEntity> otherEntities = new ArrayList<>();

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBPDataSourceContainer dataSourceContainer3 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer3.getDataSource()).thenReturn(dbpDataSource);

    DBVModel container2 = new DBVModel(dataSourceContainer, source);
    container2.setDataSourceContainer(dataSourceContainer3);

    DBPProject dbpProject = mock(DBPProject.class);
    when(dbpProject.getNavigatorModel()).thenReturn(null);

    DBPDataSourceRegistry dbpDataSourceRegistry = mock(DBPDataSourceRegistry.class);
    when(dbpDataSourceRegistry.getProject()).thenReturn(dbpProject);

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer2.getRegistry()).thenReturn(dbpDataSourceRegistry);

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getContainer()).thenReturn(dbpDataSourceContainer2);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource2);
    DBVContainer container3 = new DBVContainer(parent, "Name");
    DBVEntity entity2 = new DBVEntity(container3, "Name", "Description Column Names");

    DBVEntityForeignKey foreignKey = new DBVEntityForeignKey(entity2);
    foreignKey.setRefEntityId("42");

    DBVEntity dbvEntity = new DBVEntity(container2, "Name", "Description Column Names");
    dbvEntity.addForeignKey(foreignKey);
    DBVContainer container4 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container4, "Name", "Description Column Names");
    DBVEntity entity4 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBSEntityConstraintType type =
        new DBSEntityConstraintType("42", "Name", "Localized Name", true, true, true, true);

    DBVEntityConstraint copy = new DBVEntityConstraint(entity4, type, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity3, copy);
    dbvEntity.addConstraint(constraint);
    DBVContainer container5 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity5 = new DBVEntity(container5, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity5, mock(DBVEntityAttribute.class), "Name");
    dbvEntity.addVirtualAttribute(attribute);

    ERDEntity erdEntity = new ERDEntity(mock(DBPDataSource.class));
    erdEntity.setObject(dbvEntity);

    // Act
    erdContentProviderDefault.fillEntityFromObject(monitor, diagram, otherEntities, erdEntity);

    // Assert
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSource, atLeast(1)).getContainer();
    verify(dbpDataSourceContainer, atLeast(1)).fireEvent(Mockito.<DBPEvent>any());
    verify(dataSourceContainer3, atLeast(1)).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(dbpDataSourceContainer2).getRegistry();
    verify(dbpDataSourceRegistry).getProject();
    verify(dbpProject).getNavigatorModel();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link ERDContentProviderDefault#fillEntityFromObject(DBRProgressMonitor, ERDDiagram,
   * List, ERDEntity)} with {@code monitor}, {@code diagram}, {@code otherEntities}, {@code
   * erdEntity}.
   *
   * <p>Method under test: {@link ERDContentProviderDefault#fillEntityFromObject(DBRProgressMonitor,
   * ERDDiagram, List, ERDEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ERDContentProviderDefault.fillEntityFromObject(DBRProgressMonitor, ERDDiagram, List, ERDEntity)"
  })
  public void testFillEntityFromObjectWithMonitorDiagramOtherEntitiesErdEntity8()
      throws DBCException {
    // Arrange
    ERDContentProviderDefault erdContentProviderDefault = new ERDContentProviderDefault();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram diagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());
    ArrayList<ERDEntity> otherEntities = new ArrayList<>();

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBPDataSourceContainer dataSourceContainer3 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer3.getDataSource()).thenReturn(dbpDataSource);

    DBVModel container2 = new DBVModel(dataSourceContainer, source);
    container2.setDataSourceContainer(dataSourceContainer3);

    DBPProject dbpProject = mock(DBPProject.class);
    when(dbpProject.getNavigatorModel()).thenReturn(null);

    DBPDataSourceRegistry dbpDataSourceRegistry = mock(DBPDataSourceRegistry.class);
    when(dbpDataSourceRegistry.getProject()).thenReturn(dbpProject);

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer2.getRegistry()).thenReturn(dbpDataSourceRegistry);

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getContainer()).thenReturn(dbpDataSourceContainer2);

    DBPDataSourceContainer dataSourceContainer4 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer4.getId()).thenReturn("42");
    when(dataSourceContainer4.getDataSource()).thenReturn(dbpDataSource2);
    DBVModel container3 = new DBVModel(dataSourceContainer4);

    DBPDataSourceContainer dataSourceContainer5 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer5.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer5);
    DBVContainer container4 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity copy = new DBVEntity(container4, "Name", "Description Column Names");

    DBVEntity entity2 = new DBVEntity(container3, copy, targetModel);

    DBVEntityForeignKey foreignKey = new DBVEntityForeignKey(entity2);
    foreignKey.setRefEntityId("42");

    DBVEntity dbvEntity = new DBVEntity(container2, "Name", "Description Column Names");
    dbvEntity.addForeignKey(foreignKey);
    DBVContainer container5 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container5, "Name", "Description Column Names");
    DBVEntity entity4 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBSEntityConstraintType type =
        new DBSEntityConstraintType("42", "Name", "Localized Name", true, true, true, true);

    DBVEntityConstraint copy2 = new DBVEntityConstraint(entity4, type, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity3, copy2);
    dbvEntity.addConstraint(constraint);
    DBVContainer container6 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity5 = new DBVEntity(container6, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity5, mock(DBVEntityAttribute.class), "Name");
    dbvEntity.addVirtualAttribute(attribute);

    ERDEntity erdEntity = new ERDEntity(mock(DBPDataSource.class));
    erdEntity.setObject(dbvEntity);

    // Act
    erdContentProviderDefault.fillEntityFromObject(monitor, diagram, otherEntities, erdEntity);

    // Assert
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSource, atLeast(1)).getContainer();
    verify(dbpDataSourceContainer, atLeast(1)).fireEvent(Mockito.<DBPEvent>any());
    verify(dataSourceContainer4).getDataSource();
    verify(dataSourceContainer3, atLeast(1)).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(dataSourceContainer4, atLeast(1)).getId();
    verify(dataSourceContainer5, atLeast(1)).getId();
    verify(dbpDataSourceContainer2).getRegistry();
    verify(dbpDataSourceRegistry).getProject();
    verify(dbpProject).getNavigatorModel();
  }

  /**
   * Test {@link ERDContentProviderDefault#fillEntityFromObject(DBRProgressMonitor, ERDDiagram,
   * List, ERDEntity, ERDAttributeSettings)} with {@code monitor}, {@code diagram}, {@code
   * otherEntities}, {@code erdEntity}, {@code settings}.
   *
   * <p>Method under test: {@link ERDContentProviderDefault#fillEntityFromObject(DBRProgressMonitor,
   * ERDDiagram, List, ERDEntity, ERDAttributeSettings)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ERDContentProviderDefault.fillEntityFromObject(DBRProgressMonitor, ERDDiagram, List, ERDEntity, ERDAttributeSettings)"
  })
  public void testFillEntityFromObjectWithMonitorDiagramOtherEntitiesErdEntitySettings()
      throws DBException {
    // Arrange
    ERDContentProviderDefault erdContentProviderDefault = new ERDContentProviderDefault();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram diagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());
    ArrayList<ERDEntity> otherEntities = new ArrayList<>();

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getRealContainer(Mockito.<DBRProgressMonitor>any()))
        .thenThrow(new DBCException("An error occurred"));
    DBVContainer container2 = new DBVContainer(parent, "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    ERDEntity erdEntity = new ERDEntity(entity2);

    // Act
    erdContentProviderDefault.fillEntityFromObject(
        monitor,
        diagram,
        otherEntities,
        erdEntity,
        new ERDAttributeSettings(ERDAttributeVisibility.ALL, true));

    // Assert
    verify(parent, atLeast(1)).getRealContainer(isA(DBRProgressMonitor.class));
  }

  /**
   * Test {@link ERDContentProviderDefault#fillEntityFromObject(DBRProgressMonitor, ERDDiagram,
   * List, ERDEntity, ERDAttributeSettings)} with {@code monitor}, {@code diagram}, {@code
   * otherEntities}, {@code erdEntity}, {@code settings}.
   *
   * <p>Method under test: {@link ERDContentProviderDefault#fillEntityFromObject(DBRProgressMonitor,
   * ERDDiagram, List, ERDEntity, ERDAttributeSettings)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ERDContentProviderDefault.fillEntityFromObject(DBRProgressMonitor, ERDDiagram, List, ERDEntity, ERDAttributeSettings)"
  })
  public void testFillEntityFromObjectWithMonitorDiagramOtherEntitiesErdEntitySettings2()
      throws DBException {
    // Arrange
    ERDContentProviderDefault erdContentProviderDefault = new ERDContentProviderDefault();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram diagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());
    ArrayList<ERDEntity> otherEntities = new ArrayList<>();

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getRealContainer(Mockito.<DBRProgressMonitor>any())).thenReturn(null);
    DBVContainer container2 = new DBVContainer(parent, "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    ERDEntity erdEntity = new ERDEntity(entity2);

    // Act
    erdContentProviderDefault.fillEntityFromObject(
        monitor,
        diagram,
        otherEntities,
        erdEntity,
        new ERDAttributeSettings(ERDAttributeVisibility.ALL, true));

    // Assert
    verify(parent, atLeast(1)).getRealContainer(isA(DBRProgressMonitor.class));
  }

  /**
   * Test {@link ERDContentProviderDefault#fillEntityFromObject(DBRProgressMonitor, ERDDiagram,
   * List, ERDEntity, ERDAttributeSettings)} with {@code monitor}, {@code diagram}, {@code
   * otherEntities}, {@code erdEntity}, {@code settings}.
   *
   * <p>Method under test: {@link ERDContentProviderDefault#fillEntityFromObject(DBRProgressMonitor,
   * ERDDiagram, List, ERDEntity, ERDAttributeSettings)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ERDContentProviderDefault.fillEntityFromObject(DBRProgressMonitor, ERDDiagram, List, ERDEntity, ERDAttributeSettings)"
  })
  public void testFillEntityFromObjectWithMonitorDiagramOtherEntitiesErdEntitySettings3() {
    // Arrange
    ERDContentProviderDefault erdContentProviderDefault = new ERDContentProviderDefault();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram diagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());
    ArrayList<ERDEntity> otherEntities = new ArrayList<>();

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(mock(DBPDataSource.class));
    DBVModel container2 = new DBVModel(dataSourceContainer);
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    ERDEntity erdEntity = new ERDEntity(entity2);

    // Act
    erdContentProviderDefault.fillEntityFromObject(
        monitor,
        diagram,
        otherEntities,
        erdEntity,
        new ERDAttributeSettings(ERDAttributeVisibility.ALL, true));

    // Assert
    verify(dataSourceContainer, atLeast(1)).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
  }

  /**
   * Test {@link ERDContentProviderDefault#fillEntityFromObject(DBRProgressMonitor, ERDDiagram,
   * List, ERDEntity, ERDAttributeSettings)} with {@code monitor}, {@code diagram}, {@code
   * otherEntities}, {@code erdEntity}, {@code settings}.
   *
   * <p>Method under test: {@link ERDContentProviderDefault#fillEntityFromObject(DBRProgressMonitor,
   * ERDDiagram, List, ERDEntity, ERDAttributeSettings)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ERDContentProviderDefault.fillEntityFromObject(DBRProgressMonitor, ERDDiagram, List, ERDEntity, ERDAttributeSettings)"
  })
  public void testFillEntityFromObjectWithMonitorDiagramOtherEntitiesErdEntitySettings4() {
    // Arrange
    ERDContentProviderDefault erdContentProviderDefault = new ERDContentProviderDefault();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram diagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());
    ArrayList<ERDEntity> otherEntities = new ArrayList<>();

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource);

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBVModel container2 = new DBVModel(dataSourceContainer, source);

    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity3, mock(DBVEntityAttribute.class), "Name");
    entity2.addVirtualAttribute(attribute);
    ERDEntity erdEntity = new ERDEntity(entity2);

    // Act
    erdContentProviderDefault.fillEntityFromObject(
        monitor,
        diagram,
        otherEntities,
        erdEntity,
        new ERDAttributeSettings(ERDAttributeVisibility.ALL, true));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
  }

  /**
   * Test {@link ERDContentProviderDefault#fillEntityFromObject(DBRProgressMonitor, ERDDiagram,
   * List, ERDEntity, ERDAttributeSettings)} with {@code monitor}, {@code diagram}, {@code
   * otherEntities}, {@code erdEntity}, {@code settings}.
   *
   * <p>Method under test: {@link ERDContentProviderDefault#fillEntityFromObject(DBRProgressMonitor,
   * ERDDiagram, List, ERDEntity, ERDAttributeSettings)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ERDContentProviderDefault.fillEntityFromObject(DBRProgressMonitor, ERDDiagram, List, ERDEntity, ERDAttributeSettings)"
  })
  public void testFillEntityFromObjectWithMonitorDiagramOtherEntitiesErdEntitySettings5() {
    // Arrange
    ERDContentProviderDefault erdContentProviderDefault = new ERDContentProviderDefault();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram diagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());
    ArrayList<ERDEntity> otherEntities = new ArrayList<>();

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource);

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBVModel container2 = new DBVModel(dataSourceContainer, source);

    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntity entity4 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBSEntityConstraintType type =
        new DBSEntityConstraintType("42", "Name", "Localized Name", true, true, true, true);

    DBVEntityConstraint copy = new DBVEntityConstraint(entity4, type, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity3, copy);
    entity2.addConstraint(constraint);
    DBVContainer container4 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity5 = new DBVEntity(container4, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity5, mock(DBVEntityAttribute.class), "Name");
    entity2.addVirtualAttribute(attribute);
    ERDEntity erdEntity = new ERDEntity(entity2);

    // Act
    erdContentProviderDefault.fillEntityFromObject(
        monitor,
        diagram,
        otherEntities,
        erdEntity,
        new ERDAttributeSettings(ERDAttributeVisibility.ALL, true));

    // Assert
    verify(dbpDataSource, atLeast(1)).getContainer();
    verify(dbpDataSourceContainer, atLeast(1)).fireEvent(Mockito.<DBPEvent>any());
    verify(dataSourceContainer, atLeast(1)).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
  }

  /**
   * Test {@link ERDContentProviderDefault#fillEntityFromObject(DBRProgressMonitor, ERDDiagram,
   * List, ERDEntity, ERDAttributeSettings)} with {@code monitor}, {@code diagram}, {@code
   * otherEntities}, {@code erdEntity}, {@code settings}.
   *
   * <p>Method under test: {@link ERDContentProviderDefault#fillEntityFromObject(DBRProgressMonitor,
   * ERDDiagram, List, ERDEntity, ERDAttributeSettings)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ERDContentProviderDefault.fillEntityFromObject(DBRProgressMonitor, ERDDiagram, List, ERDEntity, ERDAttributeSettings)"
  })
  public void testFillEntityFromObjectWithMonitorDiagramOtherEntitiesErdEntitySettings6() {
    // Arrange
    ERDContentProviderDefault erdContentProviderDefault = new ERDContentProviderDefault();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram diagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());
    ArrayList<ERDEntity> otherEntities = new ArrayList<>();

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource);

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBVModel container2 = new DBVModel(dataSourceContainer, source);

    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container3, "Name", "Description Column Names");
    entity2.addForeignKey(new DBVEntityForeignKey(entity3));
    DBVContainer container4 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity4 = new DBVEntity(container4, "Name", "Description Column Names");
    DBVEntity entity5 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBSEntityConstraintType type =
        new DBSEntityConstraintType("42", "Name", "Localized Name", true, true, true, true);

    DBVEntityConstraint copy = new DBVEntityConstraint(entity5, type, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity4, copy);
    entity2.addConstraint(constraint);
    DBVContainer container5 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity6 = new DBVEntity(container5, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity6, mock(DBVEntityAttribute.class), "Name");
    entity2.addVirtualAttribute(attribute);
    ERDEntity erdEntity = new ERDEntity(entity2);

    // Act
    erdContentProviderDefault.fillEntityFromObject(
        monitor,
        diagram,
        otherEntities,
        erdEntity,
        new ERDAttributeSettings(ERDAttributeVisibility.ALL, true));

    // Assert
    verify(dbpDataSource, atLeast(1)).getContainer();
    verify(dbpDataSourceContainer, atLeast(1)).fireEvent(Mockito.<DBPEvent>any());
    verify(dataSourceContainer, atLeast(1)).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
  }

  /**
   * Test {@link ERDContentProviderDefault#fillEntityFromObject(DBRProgressMonitor, ERDDiagram,
   * List, ERDEntity, ERDAttributeSettings)} with {@code monitor}, {@code diagram}, {@code
   * otherEntities}, {@code erdEntity}, {@code settings}.
   *
   * <p>Method under test: {@link ERDContentProviderDefault#fillEntityFromObject(DBRProgressMonitor,
   * ERDDiagram, List, ERDEntity, ERDAttributeSettings)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ERDContentProviderDefault.fillEntityFromObject(DBRProgressMonitor, ERDDiagram, List, ERDEntity, ERDAttributeSettings)"
  })
  public void testFillEntityFromObjectWithMonitorDiagramOtherEntitiesErdEntitySettings7() {
    // Arrange
    ERDContentProviderDefault erdContentProviderDefault = new ERDContentProviderDefault();
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram diagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());
    ArrayList<ERDEntity> otherEntities = new ArrayList<>();

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource);

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBVModel container2 = new DBVModel(dataSourceContainer, source);

    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container3, "Name", "Description Column Names");
    entity2.addForeignKey(new DBVEntityForeignKey(entity3));
    DBVContainer container4 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity4 = new DBVEntity(container4, "Name", "Description Column Names");
    DBVEntity entity5 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBSEntityConstraintType type =
        new DBSEntityConstraintType("42", "Name", "Localized Name", true, true, true, true);

    DBVEntityConstraint copy = new DBVEntityConstraint(entity5, type, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity4, copy);
    entity2.addConstraint(constraint);
    DBVContainer container5 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity6 = new DBVEntity(container5, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity6, mock(DBVEntityAttribute.class), "Name");
    entity2.addVirtualAttribute(attribute);
    ERDEntity erdEntity = new ERDEntity(entity2);

    // Act
    erdContentProviderDefault.fillEntityFromObject(
        null,
        diagram,
        otherEntities,
        erdEntity,
        new ERDAttributeSettings(ERDAttributeVisibility.ALL, true));

    // Assert
    verify(dbpDataSource, atLeast(1)).getContainer();
    verify(dbpDataSourceContainer, atLeast(1)).fireEvent(Mockito.<DBPEvent>any());
    verify(dataSourceContainer, atLeast(1)).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
  }

  /**
   * Test {@link ERDContentProviderDefault#fillEntityFromObject(DBRProgressMonitor, ERDDiagram,
   * List, ERDEntity, ERDAttributeSettings)} with {@code monitor}, {@code diagram}, {@code
   * otherEntities}, {@code erdEntity}, {@code settings}.
   *
   * <p>Method under test: {@link ERDContentProviderDefault#fillEntityFromObject(DBRProgressMonitor,
   * ERDDiagram, List, ERDEntity, ERDAttributeSettings)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ERDContentProviderDefault.fillEntityFromObject(DBRProgressMonitor, ERDDiagram, List, ERDEntity, ERDAttributeSettings)"
  })
  public void testFillEntityFromObjectWithMonitorDiagramOtherEntitiesErdEntitySettings8() {
    // Arrange
    ERDContentProviderDefault erdContentProviderDefault = new ERDContentProviderDefault();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram diagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());
    ArrayList<ERDEntity> otherEntities = new ArrayList<>();

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBPDataSourceContainer dataSourceContainer3 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer3.getDataSource()).thenReturn(dbpDataSource);

    DBVModel container2 = new DBVModel(dataSourceContainer, source);
    container2.setDataSourceContainer(dataSourceContainer3);

    DBPProject dbpProject = mock(DBPProject.class);
    when(dbpProject.getNavigatorModel()).thenReturn(null);

    DBPDataSourceRegistry dbpDataSourceRegistry = mock(DBPDataSourceRegistry.class);
    when(dbpDataSourceRegistry.getProject()).thenReturn(dbpProject);

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer2.getRegistry()).thenReturn(dbpDataSourceRegistry);

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getContainer()).thenReturn(dbpDataSourceContainer2);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource2);
    DBVContainer container3 = new DBVContainer(parent, "Name");
    DBVEntity entity2 = new DBVEntity(container3, "Name", "Description Column Names");

    DBVEntityForeignKey foreignKey = new DBVEntityForeignKey(entity2);
    foreignKey.setRefEntityId("42");

    DBVEntity entity3 = new DBVEntity(container2, "Name", "Description Column Names");
    entity3.addForeignKey(foreignKey);
    DBVContainer container4 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity4 = new DBVEntity(container4, "Name", "Description Column Names");
    DBVEntity entity5 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBSEntityConstraintType type =
        new DBSEntityConstraintType("42", "Name", "Localized Name", true, true, true, true);

    DBVEntityConstraint copy = new DBVEntityConstraint(entity5, type, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity4, copy);
    entity3.addConstraint(constraint);
    DBVContainer container5 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity6 = new DBVEntity(container5, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity6, mock(DBVEntityAttribute.class), "Name");
    entity3.addVirtualAttribute(attribute);
    ERDEntity erdEntity = new ERDEntity(entity3);

    // Act
    erdContentProviderDefault.fillEntityFromObject(
        monitor,
        diagram,
        otherEntities,
        erdEntity,
        new ERDAttributeSettings(ERDAttributeVisibility.ALL, true));

    // Assert
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSource, atLeast(1)).getContainer();
    verify(dbpDataSourceContainer, atLeast(1)).fireEvent(Mockito.<DBPEvent>any());
    verify(dataSourceContainer3, atLeast(1)).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(dbpDataSourceContainer2).getRegistry();
    verify(dbpDataSourceRegistry).getProject();
    verify(dbpProject).getNavigatorModel();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link ERDContentProviderDefault#fillEntityFromObject(DBRProgressMonitor, ERDDiagram,
   * List, ERDEntity, ERDAttributeSettings)} with {@code monitor}, {@code diagram}, {@code
   * otherEntities}, {@code erdEntity}, {@code settings}.
   *
   * <p>Method under test: {@link ERDContentProviderDefault#fillEntityFromObject(DBRProgressMonitor,
   * ERDDiagram, List, ERDEntity, ERDAttributeSettings)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ERDContentProviderDefault.fillEntityFromObject(DBRProgressMonitor, ERDDiagram, List, ERDEntity, ERDAttributeSettings)"
  })
  public void testFillEntityFromObjectWithMonitorDiagramOtherEntitiesErdEntitySettings9() {
    // Arrange
    ERDContentProviderDefault erdContentProviderDefault = new ERDContentProviderDefault();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram diagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());
    ArrayList<ERDEntity> otherEntities = new ArrayList<>();

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBPDataSourceContainer dataSourceContainer3 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer3.getDataSource()).thenReturn(dbpDataSource);

    DBVModel container2 = new DBVModel(dataSourceContainer, source);
    container2.setDataSourceContainer(dataSourceContainer3);

    DBPProject dbpProject = mock(DBPProject.class);
    when(dbpProject.getNavigatorModel()).thenReturn(null);

    DBPDataSourceRegistry dbpDataSourceRegistry = mock(DBPDataSourceRegistry.class);
    when(dbpDataSourceRegistry.getProject()).thenReturn(dbpProject);

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer2.getRegistry()).thenReturn(dbpDataSourceRegistry);

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getContainer()).thenReturn(dbpDataSourceContainer2);

    DBPDataSourceContainer dataSourceContainer4 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer4.getId()).thenReturn("42");
    when(dataSourceContainer4.getDataSource()).thenReturn(dbpDataSource2);
    DBVModel container3 = new DBVModel(dataSourceContainer4);

    DBPDataSourceContainer dataSourceContainer5 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer5.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer5);
    DBVContainer container4 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity copy = new DBVEntity(container4, "Name", "Description Column Names");

    DBVEntity entity2 = new DBVEntity(container3, copy, targetModel);

    DBVEntityForeignKey foreignKey = new DBVEntityForeignKey(entity2);
    foreignKey.setRefEntityId("42");

    DBVEntity entity3 = new DBVEntity(container2, "Name", "Description Column Names");
    entity3.addForeignKey(foreignKey);
    DBVContainer container5 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity4 = new DBVEntity(container5, "Name", "Description Column Names");
    DBVEntity entity5 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBSEntityConstraintType type =
        new DBSEntityConstraintType("42", "Name", "Localized Name", true, true, true, true);

    DBVEntityConstraint copy2 = new DBVEntityConstraint(entity5, type, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity4, copy2);
    entity3.addConstraint(constraint);
    DBVContainer container6 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity6 = new DBVEntity(container6, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity6, mock(DBVEntityAttribute.class), "Name");
    entity3.addVirtualAttribute(attribute);
    ERDEntity erdEntity = new ERDEntity(entity3);

    // Act
    erdContentProviderDefault.fillEntityFromObject(
        monitor,
        diagram,
        otherEntities,
        erdEntity,
        new ERDAttributeSettings(ERDAttributeVisibility.ALL, true));

    // Assert
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSource, atLeast(1)).getContainer();
    verify(dbpDataSourceContainer, atLeast(1)).fireEvent(Mockito.<DBPEvent>any());
    verify(dataSourceContainer4).getDataSource();
    verify(dataSourceContainer3, atLeast(1)).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(dataSourceContainer4, atLeast(1)).getId();
    verify(dataSourceContainer5, atLeast(1)).getId();
    verify(dbpDataSourceContainer2).getRegistry();
    verify(dbpDataSourceRegistry).getProject();
    verify(dbpProject).getNavigatorModel();
  }

  /**
   * Test {@link ERDContentProviderDefault#isAttributeVisible(ERDEntity, DBSEntityAttribute)}.
   *
   * <p>Method under test: {@link ERDContentProviderDefault#isAttributeVisible(ERDEntity,
   * DBSEntityAttribute)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean ERDContentProviderDefault.isAttributeVisible(ERDEntity, DBSEntityAttribute)"
  })
  public void testIsAttributeVisible() {
    // Arrange
    ERDContentProviderDefault erdContentProviderDefault = new ERDContentProviderDefault();
    ERDEntity erdEntity = new ERDEntity(mock(DBPDataSource.class));
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");

    // Act and Assert
    assertTrue(erdContentProviderDefault.isAttributeVisible(erdEntity, attribute));
  }

  /**
   * Test {@link ERDContentProviderDefault#createAutoAssociation(ERDContainer, DBSEntityAssociation,
   * ERDEntity, ERDEntity, boolean)}.
   *
   * <ul>
   *   <li>Then Object return {@link ERDLogicalAssociation}.
   * </ul>
   *
   * <p>Method under test: {@link ERDContentProviderDefault#createAutoAssociation(ERDContainer,
   * DBSEntityAssociation, ERDEntity, ERDEntity, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ERDAssociation ERDContentProviderDefault.createAutoAssociation(ERDContainer, DBSEntityAssociation, ERDEntity, ERDEntity, boolean)"
  })
  public void testCreateAutoAssociation_thenObjectReturnERDLogicalAssociation() {
    // Arrange
    ERDContentProviderDefault erdContentProviderDefault = new ERDContentProviderDefault();
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram diagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());
    ERDEntity entity2 = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity entity3 = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey pk =
        new ERDLogicalPrimaryKey(entity3, "Name", "The characteristics of someone or something");

    ERDLogicalAssociation association =
        new ERDLogicalAssociation(
            entity2, "Name", "The characteristics of someone or something", pk);
    ERDEntity sourceEntity = new ERDEntity(mock(DBPDataSource.class));

    // Act
    ERDAssociation actualCreateAutoAssociationResult =
        erdContentProviderDefault.createAutoAssociation(
            diagram, association, sourceEntity, new ERDEntity(mock(DBPDataSource.class)), true);

    // Assert
    DBSEntityAssociation object = actualCreateAutoAssociationResult.getObject();
    assertTrue(object instanceof ERDLogicalAssociation);
    assertEquals("Name", actualCreateAutoAssociationResult.getName());
    assertTrue(actualCreateAutoAssociationResult.isLogical());
    assertSame(association, object);
  }

  /**
   * Test {@link ERDContentProviderDefault#createAutoAssociation(ERDContainer, DBSEntityAssociation,
   * ERDEntity, ERDEntity, boolean)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then SourceEntity return {@link ERDEntity}.
   * </ul>
   *
   * <p>Method under test: {@link ERDContentProviderDefault#createAutoAssociation(ERDContainer,
   * DBSEntityAssociation, ERDEntity, ERDEntity, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ERDAssociation ERDContentProviderDefault.createAutoAssociation(ERDContainer, DBSEntityAssociation, ERDEntity, ERDEntity, boolean)"
  })
  public void testCreateAutoAssociation_whenNull_thenSourceEntityReturnERDEntity() {
    // Arrange
    ERDContentProviderDefault erdContentProviderDefault = new ERDContentProviderDefault();
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram diagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());
    ERDEntity sourceEntity = new ERDEntity(mock(DBPDataSource.class));

    // Act
    ERDAssociation actualCreateAutoAssociationResult =
        erdContentProviderDefault.createAutoAssociation(
            diagram, null, sourceEntity, new ERDEntity(mock(DBPDataSource.class)), true);

    // Assert
    ERDElement<?> sourceEntity2 = actualCreateAutoAssociationResult.getSourceEntity();
    assertTrue(sourceEntity2 instanceof ERDEntity);
    ERDElement<?> targetEntity = actualCreateAutoAssociationResult.getTargetEntity();
    assertTrue(targetEntity instanceof ERDEntity);
    assertNull(actualCreateAutoAssociationResult.getObject());
    List<ERDAssociation> associations = sourceEntity2.getAssociations();
    assertEquals(1, associations.size());
    assertFalse(actualCreateAutoAssociationResult.isLogical());
    assertEquals(associations, targetEntity.getReferences());
    assertSame(actualCreateAutoAssociationResult, associations.get(0));
  }

  /**
   * Test {@link ERDContentProviderDefault#createAssociation(ERDContainer, DBSEntityAssociation,
   * ERDEntity, ERDEntityAttribute, ERDEntity, ERDEntityAttribute, boolean)}.
   *
   * <ul>
   *   <li>Then return SourceAttributes size is one.
   * </ul>
   *
   * <p>Method under test: {@link ERDContentProviderDefault#createAssociation(ERDContainer,
   * DBSEntityAssociation, ERDEntity, ERDEntityAttribute, ERDEntity, ERDEntityAttribute, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ERDAssociation ERDContentProviderDefault.createAssociation(ERDContainer, DBSEntityAssociation, ERDEntity, ERDEntityAttribute, ERDEntity, ERDEntityAttribute, boolean)"
  })
  public void testCreateAssociation_thenReturnSourceAttributesSizeIsOne() {
    // Arrange
    ERDContentProviderDefault erdContentProviderDefault = new ERDContentProviderDefault();
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram diagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());
    ERDEntity entity2 = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity entity3 = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey pk =
        new ERDLogicalPrimaryKey(entity3, "Name", "The characteristics of someone or something");

    ERDLogicalAssociation association =
        new ERDLogicalAssociation(
            entity2, "Name", "The characteristics of someone or something", pk);
    ERDEntity sourceEntity = new ERDEntity(mock(DBPDataSource.class));
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity4 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity4, mock(DBVEntityAttribute.class), "Name");
    ERDEntityAttribute sourceAttribute = new ERDEntityAttribute(attribute, true);
    ERDEntity targetEntity = new ERDEntity(mock(DBPDataSource.class));
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity5 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntityAttribute attribute2 =
        new DBVEntityAttribute(entity5, mock(DBVEntityAttribute.class), "Name");
    ERDEntityAttribute targetAttribute = new ERDEntityAttribute(attribute2, true);

    // Act
    ERDAssociation actualCreateAssociationResult =
        erdContentProviderDefault.createAssociation(
            diagram,
            association,
            sourceEntity,
            sourceAttribute,
            targetEntity,
            targetAttribute,
            true);

    // Assert
    ERDElement<?> sourceEntity2 = actualCreateAssociationResult.getSourceEntity();
    assertTrue(sourceEntity2 instanceof ERDEntity);
    ERDElement<?> targetEntity2 = actualCreateAssociationResult.getTargetEntity();
    assertTrue(targetEntity2 instanceof ERDEntity);
    List<ERDEntityAttribute> sourceAttributes = actualCreateAssociationResult.getSourceAttributes();
    assertEquals(1, sourceAttributes.size());
    List<ERDEntityAttribute> targetAttributes = actualCreateAssociationResult.getTargetAttributes();
    assertEquals(1, targetAttributes.size());
    List<ERDEntityAttribute> attributes = ((ERDEntity) sourceEntity2).getAttributes();
    assertTrue(attributes.isEmpty());
    assertSame(sourceAttribute, sourceAttributes.get(0));
    assertSame(targetAttribute, targetAttributes.get(0));
    assertSame(attributes, targetEntity2.getAssociations());
    assertSame(attributes, sourceEntity2.getReferences());
    assertSame(attributes, ((ERDEntity) targetEntity2).getAttributes());
  }

  /**
   * Test {@link ERDContentProviderDefault#createAssociation(ERDContainer, DBSEntityAssociation,
   * ERDEntity, ERDEntityAttribute, ERDEntity, ERDEntityAttribute, boolean)}.
   *
   * <ul>
   *   <li>When {@code false}.
   *   <li>Then return SourceEntity Associations size is one.
   * </ul>
   *
   * <p>Method under test: {@link ERDContentProviderDefault#createAssociation(ERDContainer,
   * DBSEntityAssociation, ERDEntity, ERDEntityAttribute, ERDEntity, ERDEntityAttribute, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ERDAssociation ERDContentProviderDefault.createAssociation(ERDContainer, DBSEntityAssociation, ERDEntity, ERDEntityAttribute, ERDEntity, ERDEntityAttribute, boolean)"
  })
  public void testCreateAssociation_whenFalse_thenReturnSourceEntityAssociationsSizeIsOne() {
    // Arrange
    ERDContentProviderDefault erdContentProviderDefault = new ERDContentProviderDefault();
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram diagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());
    ERDEntity entity2 = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity entity3 = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey pk =
        new ERDLogicalPrimaryKey(entity3, "Name", "The characteristics of someone or something");

    ERDLogicalAssociation association =
        new ERDLogicalAssociation(
            entity2, "Name", "The characteristics of someone or something", pk);
    ERDEntity sourceEntity = new ERDEntity(mock(DBPDataSource.class));

    // Act
    ERDAssociation actualCreateAssociationResult =
        erdContentProviderDefault.createAssociation(
            diagram,
            association,
            sourceEntity,
            null,
            new ERDEntity(mock(DBPDataSource.class)),
            null,
            false);

    // Assert
    ERDElement<?> sourceEntity2 = actualCreateAssociationResult.getSourceEntity();
    assertTrue(sourceEntity2 instanceof ERDEntity);
    ERDElement<?> targetEntity = actualCreateAssociationResult.getTargetEntity();
    assertTrue(targetEntity instanceof ERDEntity);
    List<ERDAssociation> associations = sourceEntity2.getAssociations();
    assertEquals(1, associations.size());
    List<ERDEntityAttribute> sourceAttributes = actualCreateAssociationResult.getSourceAttributes();
    assertTrue(sourceAttributes.isEmpty());
    assertEquals(associations, targetEntity.getReferences());
    assertSame(sourceAttributes, actualCreateAssociationResult.getTargetAttributes());
    assertSame(sourceAttributes, targetEntity.getAssociations());
    assertSame(sourceAttributes, sourceEntity2.getReferences());
    assertSame(sourceAttributes, ((ERDEntity) sourceEntity2).getAttributes());
    assertSame(sourceAttributes, ((ERDEntity) targetEntity).getAttributes());
    assertSame(actualCreateAssociationResult, associations.get(0));
  }

  /**
   * Test {@link ERDContentProviderDefault#getAttribute(String)}.
   *
   * <p>Method under test: {@link ERDContentProviderDefault#getAttribute(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.Object ERDContentProviderDefault.getAttribute(String)"})
  public void testGetAttribute() {
    // Arrange, Act and Assert
    assertNull(new ERDContentProviderDefault().getAttribute("Name"));
  }
}

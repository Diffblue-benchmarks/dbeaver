package org.jkiss.dbeaver.model.impl.edit;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.edit.DBECommand;
import org.jkiss.dbeaver.model.edit.DBEObjectMaker;
import org.jkiss.dbeaver.model.edit.prop.DBECommandProperty;
import org.jkiss.dbeaver.model.edit.prop.DBEPropertyHandler;
import org.jkiss.dbeaver.model.impl.edit.AbstractObjectManager.AbstractObjectReflector;
import org.jkiss.dbeaver.model.impl.edit.AbstractObjectManager.CreateObjectReflector;
import org.jkiss.dbeaver.model.impl.edit.AbstractObjectManager.DeleteObjectReflector;
import org.jkiss.dbeaver.model.struct.DBSDocumentConstraint;
import org.jkiss.dbeaver.model.struct.DBSDocumentContainer;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.jkiss.dbeaver.model.struct.cache.DBSObjectCache;
import org.jkiss.dbeaver.model.virtual.DBVContainer;
import org.jkiss.dbeaver.model.virtual.DBVEntity;
import org.jkiss.dbeaver.model.virtual.DBVEntityForeignKey;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class AbstractObjectManagerDiffblueTest {
  /**
   * Test AbstractObjectReflector {@link AbstractObjectReflector#cacheModelObject(DBSObject)}.
   *
   * <p>Method under test: {@link AbstractObjectReflector#cacheModelObject(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractObjectReflector.cacheModelObject(DBSObject)"})
  public void testAbstractObjectReflectorCacheModelObject() {
    // Arrange
    DBSObjectCache<? extends DBSObject, DBSObject> dbsObjectCache = mock(DBSObjectCache.class);
    doNothing().when(dbsObjectCache).cacheObject(Mockito.<DBSObject>any());

    DBEObjectMaker<DBSObject, DBSObject> objectMaker = mock(DBEObjectMaker.class);
    Mockito.<DBSObjectCache<? extends DBSObject, DBSObject>>when(
            objectMaker.getObjectsCache(Mockito.<DBSObject>any()))
        .thenReturn(dbsObjectCache);
    CreateObjectReflector<DBSObject> createObjectReflector =
        new CreateObjectReflector<>(objectMaker);

    // Act
    createObjectReflector.cacheModelObject(
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class)));

    // Assert
    verify(objectMaker).getObjectsCache(isA(DBSObject.class));
    verify(dbsObjectCache).cacheObject(isA(DBSObject.class));
  }

  /**
   * Test AbstractObjectReflector {@link AbstractObjectReflector#removeModelObject(DBSObject)}.
   *
   * <p>Method under test: {@link AbstractObjectReflector#removeModelObject(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractObjectReflector.removeModelObject(DBSObject)"})
  public void testAbstractObjectReflectorRemoveModelObject() {
    // Arrange
    DBSObjectCache<? extends DBSObject, DBSObject> dbsObjectCache = mock(DBSObjectCache.class);
    doNothing().when(dbsObjectCache).removeObject(Mockito.<DBSObject>any(), anyBoolean());

    DBEObjectMaker<DBSObject, DBSObject> objectMaker = mock(DBEObjectMaker.class);
    Mockito.<DBSObjectCache<? extends DBSObject, DBSObject>>when(
            objectMaker.getObjectsCache(Mockito.<DBSObject>any()))
        .thenReturn(dbsObjectCache);
    CreateObjectReflector<DBSObject> createObjectReflector =
        new CreateObjectReflector<>(objectMaker);

    // Act
    createObjectReflector.removeModelObject(
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class)));

    // Assert
    verify(objectMaker).getObjectsCache(isA(DBSObject.class));
    verify(dbsObjectCache).removeObject(isA(DBSObject.class), eq(false));
  }

  /**
   * Test CreateObjectReflector {@link CreateObjectReflector#redoCommand(DBECommand)}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSource} {@link DBPDataSource#getContainer()} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link CreateObjectReflector#redoCommand(DBECommand)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CreateObjectReflector.redoCommand(DBECommand)"})
  public void testCreateObjectReflectorRedoCommand_givenDBPDataSourceGetContainerReturnNull() {
    // Arrange
    DBSObjectCache<? extends DBSObject, DBSObject> dbsObjectCache = mock(DBSObjectCache.class);
    doNothing().when(dbsObjectCache).cacheObject(Mockito.<DBSObject>any());

    DBEObjectMaker<DBSObject, DBSObject> objectMaker = mock(DBEObjectMaker.class);
    Mockito.<DBSObjectCache<? extends DBSObject, DBSObject>>when(
            objectMaker.getObjectsCache(Mockito.<DBSObject>any()))
        .thenReturn(dbsObjectCache);
    CreateObjectReflector<DBSObject> createObjectReflector =
        new CreateObjectReflector<>(objectMaker);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBSObject dbsObject = mock(DBSObject.class);
    when(dbsObject.getDataSource()).thenReturn(dbpDataSource);
    DBECommandProperty<DBSObject> command =
        new DBECommandProperty<>(dbsObject, mock(DBEPropertyHandler.class));

    // Act
    createObjectReflector.redoCommand(command);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(objectMaker).getObjectsCache(isA(DBSObject.class));
    verify(dbsObject).getDataSource();
    verify(dbsObjectCache).cacheObject(isA(DBSObject.class));
  }

  /**
   * Test CreateObjectReflector {@link CreateObjectReflector#redoCommand(DBECommand)}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceContainer#fireEvent(DBPEvent)}.
   * </ul>
   *
   * <p>Method under test: {@link CreateObjectReflector#redoCommand(DBECommand)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CreateObjectReflector.redoCommand(DBECommand)"})
  public void testCreateObjectReflectorRedoCommand_thenCallsFireEvent() {
    // Arrange
    DBSObjectCache<? extends DBSObject, DBSObject> dbsObjectCache = mock(DBSObjectCache.class);
    doNothing().when(dbsObjectCache).cacheObject(Mockito.<DBSObject>any());

    DBEObjectMaker<DBSObject, DBSObject> objectMaker = mock(DBEObjectMaker.class);
    Mockito.<DBSObjectCache<? extends DBSObject, DBSObject>>when(
            objectMaker.getObjectsCache(Mockito.<DBSObject>any()))
        .thenReturn(dbsObjectCache);
    CreateObjectReflector<DBSObject> createObjectReflector =
        new CreateObjectReflector<>(objectMaker);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBSObject dbsObject = mock(DBSObject.class);
    when(dbsObject.getDataSource()).thenReturn(dbpDataSource);
    DBECommandProperty<DBSObject> command =
        new DBECommandProperty<>(dbsObject, mock(DBEPropertyHandler.class));

    // Act
    createObjectReflector.redoCommand(command);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(objectMaker).getObjectsCache(isA(DBSObject.class));
    verify(dbsObject).getDataSource();
    verify(dbsObjectCache).cacheObject(isA(DBSObject.class));
  }

  /**
   * Test CreateObjectReflector {@link CreateObjectReflector#redoCommand(DBECommand)}.
   *
   * <ul>
   *   <li>Then calls {@link DBVContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link CreateObjectReflector#redoCommand(DBECommand)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CreateObjectReflector.redoCommand(DBECommand)"})
  public void testCreateObjectReflectorRedoCommand_thenCallsGetDataSource() {
    // Arrange
    DBSObjectCache<? extends DBSObject, DBSObject> dbsObjectCache = mock(DBSObjectCache.class);
    doNothing().when(dbsObjectCache).cacheObject(Mockito.<DBSObject>any());

    DBEObjectMaker<DBSObject, DBSObject> objectMaker = mock(DBEObjectMaker.class);
    Mockito.<DBSObjectCache<? extends DBSObject, DBSObject>>when(
            objectMaker.getObjectsCache(Mockito.<DBSObject>any()))
        .thenReturn(dbsObjectCache);
    CreateObjectReflector<DBSObject> createObjectReflector =
        new CreateObjectReflector<>(objectMaker);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    DBECommand<DBSObject> command = mock(DBECommand.class);
    when(command.getObject()).thenReturn(new DBVEntityForeignKey(entity));

    // Act
    createObjectReflector.redoCommand(command);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(command, atLeast(1)).getObject();
    verify(objectMaker).getObjectsCache(isA(DBSObject.class));
    verify(dbsObjectCache).cacheObject(isA(DBSObject.class));
    verify(parent).getDataSource();
  }

  /**
   * Test CreateObjectReflector {@link CreateObjectReflector#redoCommand(DBECommand)}.
   *
   * <ul>
   *   <li>When {@link DBECommand} {@link DBECommand#getObject()} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link CreateObjectReflector#redoCommand(DBECommand)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CreateObjectReflector.redoCommand(DBECommand)"})
  public void testCreateObjectReflectorRedoCommand_whenDBECommandGetObjectReturnNull() {
    // Arrange
    DBSObjectCache<? extends DBSObject, DBSObject> dbsObjectCache = mock(DBSObjectCache.class);
    doNothing().when(dbsObjectCache).cacheObject(Mockito.<DBSObject>any());

    DBEObjectMaker<DBSObject, DBSObject> objectMaker = mock(DBEObjectMaker.class);
    Mockito.<DBSObjectCache<? extends DBSObject, DBSObject>>when(
            objectMaker.getObjectsCache(Mockito.<DBSObject>any()))
        .thenReturn(dbsObjectCache);
    CreateObjectReflector<DBSObject> createObjectReflector =
        new CreateObjectReflector<>(objectMaker);

    DBECommand<DBSObject> command = mock(DBECommand.class);
    when(command.getObject()).thenReturn(null);

    // Act
    createObjectReflector.redoCommand(command);

    // Assert
    verify(command, atLeast(1)).getObject();
    verify(objectMaker).getObjectsCache(isNull());
    verify(dbsObjectCache).cacheObject(isNull());
  }

  /**
   * Test CreateObjectReflector {@link CreateObjectReflector#redoCommand(DBECommand)}.
   *
   * <ul>
   *   <li>When {@link DBSObject} {@link DBSObject#getDataSource()} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link CreateObjectReflector#redoCommand(DBECommand)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CreateObjectReflector.redoCommand(DBECommand)"})
  public void testCreateObjectReflectorRedoCommand_whenDBSObjectGetDataSourceReturnNull() {
    // Arrange
    DBSObjectCache<? extends DBSObject, DBSObject> dbsObjectCache = mock(DBSObjectCache.class);
    doNothing().when(dbsObjectCache).cacheObject(Mockito.<DBSObject>any());

    DBEObjectMaker<DBSObject, DBSObject> objectMaker = mock(DBEObjectMaker.class);
    Mockito.<DBSObjectCache<? extends DBSObject, DBSObject>>when(
            objectMaker.getObjectsCache(Mockito.<DBSObject>any()))
        .thenReturn(dbsObjectCache);
    CreateObjectReflector<DBSObject> createObjectReflector =
        new CreateObjectReflector<>(objectMaker);

    DBSObject dbsObject = mock(DBSObject.class);
    when(dbsObject.getDataSource()).thenReturn(null);
    DBECommandProperty<DBSObject> command =
        new DBECommandProperty<>(dbsObject, mock(DBEPropertyHandler.class));

    // Act
    createObjectReflector.redoCommand(command);

    // Assert
    verify(objectMaker).getObjectsCache(isA(DBSObject.class));
    verify(dbsObject).getDataSource();
    verify(dbsObjectCache).cacheObject(isA(DBSObject.class));
  }

  /**
   * Test CreateObjectReflector {@link CreateObjectReflector#undoCommand(DBECommand)}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSource} {@link DBPDataSource#getContainer()} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link CreateObjectReflector#undoCommand(DBECommand)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CreateObjectReflector.undoCommand(DBECommand)"})
  public void testCreateObjectReflectorUndoCommand_givenDBPDataSourceGetContainerReturnNull() {
    // Arrange
    DBSObjectCache<? extends DBSObject, DBSObject> dbsObjectCache = mock(DBSObjectCache.class);
    doNothing().when(dbsObjectCache).removeObject(Mockito.<DBSObject>any(), anyBoolean());

    DBEObjectMaker<DBSObject, DBSObject> objectMaker = mock(DBEObjectMaker.class);
    Mockito.<DBSObjectCache<? extends DBSObject, DBSObject>>when(
            objectMaker.getObjectsCache(Mockito.<DBSObject>any()))
        .thenReturn(dbsObjectCache);
    CreateObjectReflector<DBSObject> createObjectReflector =
        new CreateObjectReflector<>(objectMaker);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBSObject dbsObject = mock(DBSObject.class);
    when(dbsObject.getDataSource()).thenReturn(dbpDataSource);
    DBECommandProperty<DBSObject> command =
        new DBECommandProperty<>(dbsObject, mock(DBEPropertyHandler.class));

    // Act
    createObjectReflector.undoCommand(command);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(objectMaker).getObjectsCache(isA(DBSObject.class));
    verify(dbsObject).getDataSource();
    verify(dbsObjectCache).removeObject(isA(DBSObject.class), eq(false));
  }

  /**
   * Test CreateObjectReflector {@link CreateObjectReflector#undoCommand(DBECommand)}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceContainer#fireEvent(DBPEvent)}.
   * </ul>
   *
   * <p>Method under test: {@link CreateObjectReflector#undoCommand(DBECommand)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CreateObjectReflector.undoCommand(DBECommand)"})
  public void testCreateObjectReflectorUndoCommand_thenCallsFireEvent() {
    // Arrange
    DBSObjectCache<? extends DBSObject, DBSObject> dbsObjectCache = mock(DBSObjectCache.class);
    doNothing().when(dbsObjectCache).removeObject(Mockito.<DBSObject>any(), anyBoolean());

    DBEObjectMaker<DBSObject, DBSObject> objectMaker = mock(DBEObjectMaker.class);
    Mockito.<DBSObjectCache<? extends DBSObject, DBSObject>>when(
            objectMaker.getObjectsCache(Mockito.<DBSObject>any()))
        .thenReturn(dbsObjectCache);
    CreateObjectReflector<DBSObject> createObjectReflector =
        new CreateObjectReflector<>(objectMaker);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBSObject dbsObject = mock(DBSObject.class);
    when(dbsObject.getDataSource()).thenReturn(dbpDataSource);
    DBECommandProperty<DBSObject> command =
        new DBECommandProperty<>(dbsObject, mock(DBEPropertyHandler.class));

    // Act
    createObjectReflector.undoCommand(command);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(objectMaker).getObjectsCache(isA(DBSObject.class));
    verify(dbsObject).getDataSource();
    verify(dbsObjectCache).removeObject(isA(DBSObject.class), eq(false));
  }

  /**
   * Test CreateObjectReflector {@link CreateObjectReflector#undoCommand(DBECommand)}.
   *
   * <ul>
   *   <li>Then calls {@link DBVContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link CreateObjectReflector#undoCommand(DBECommand)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CreateObjectReflector.undoCommand(DBECommand)"})
  public void testCreateObjectReflectorUndoCommand_thenCallsGetDataSource() {
    // Arrange
    DBSObjectCache<? extends DBSObject, DBSObject> dbsObjectCache = mock(DBSObjectCache.class);
    doNothing().when(dbsObjectCache).removeObject(Mockito.<DBSObject>any(), anyBoolean());

    DBEObjectMaker<DBSObject, DBSObject> objectMaker = mock(DBEObjectMaker.class);
    Mockito.<DBSObjectCache<? extends DBSObject, DBSObject>>when(
            objectMaker.getObjectsCache(Mockito.<DBSObject>any()))
        .thenReturn(dbsObjectCache);
    CreateObjectReflector<DBSObject> createObjectReflector =
        new CreateObjectReflector<>(objectMaker);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    DBECommand<DBSObject> command = mock(DBECommand.class);
    when(command.getObject()).thenReturn(new DBVEntityForeignKey(entity));

    // Act
    createObjectReflector.undoCommand(command);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(command, atLeast(1)).getObject();
    verify(objectMaker).getObjectsCache(isA(DBSObject.class));
    verify(dbsObjectCache).removeObject(isA(DBSObject.class), eq(false));
    verify(parent).getDataSource();
  }

  /**
   * Test CreateObjectReflector {@link CreateObjectReflector#undoCommand(DBECommand)}.
   *
   * <ul>
   *   <li>When {@link DBECommand} {@link DBECommand#getObject()} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link CreateObjectReflector#undoCommand(DBECommand)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CreateObjectReflector.undoCommand(DBECommand)"})
  public void testCreateObjectReflectorUndoCommand_whenDBECommandGetObjectReturnNull() {
    // Arrange
    DBSObjectCache<? extends DBSObject, DBSObject> dbsObjectCache = mock(DBSObjectCache.class);
    doNothing().when(dbsObjectCache).removeObject(Mockito.<DBSObject>any(), anyBoolean());

    DBEObjectMaker<DBSObject, DBSObject> objectMaker = mock(DBEObjectMaker.class);
    Mockito.<DBSObjectCache<? extends DBSObject, DBSObject>>when(
            objectMaker.getObjectsCache(Mockito.<DBSObject>any()))
        .thenReturn(dbsObjectCache);
    CreateObjectReflector<DBSObject> createObjectReflector =
        new CreateObjectReflector<>(objectMaker);

    DBECommand<DBSObject> command = mock(DBECommand.class);
    when(command.getObject()).thenReturn(null);

    // Act
    createObjectReflector.undoCommand(command);

    // Assert
    verify(command, atLeast(1)).getObject();
    verify(objectMaker).getObjectsCache(isNull());
    verify(dbsObjectCache).removeObject(isNull(), eq(false));
  }

  /**
   * Test CreateObjectReflector {@link CreateObjectReflector#undoCommand(DBECommand)}.
   *
   * <ul>
   *   <li>When {@link DBSObject} {@link DBSObject#getDataSource()} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link CreateObjectReflector#undoCommand(DBECommand)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CreateObjectReflector.undoCommand(DBECommand)"})
  public void testCreateObjectReflectorUndoCommand_whenDBSObjectGetDataSourceReturnNull() {
    // Arrange
    DBSObjectCache<? extends DBSObject, DBSObject> dbsObjectCache = mock(DBSObjectCache.class);
    doNothing().when(dbsObjectCache).removeObject(Mockito.<DBSObject>any(), anyBoolean());

    DBEObjectMaker<DBSObject, DBSObject> objectMaker = mock(DBEObjectMaker.class);
    Mockito.<DBSObjectCache<? extends DBSObject, DBSObject>>when(
            objectMaker.getObjectsCache(Mockito.<DBSObject>any()))
        .thenReturn(dbsObjectCache);
    CreateObjectReflector<DBSObject> createObjectReflector =
        new CreateObjectReflector<>(objectMaker);

    DBSObject dbsObject = mock(DBSObject.class);
    when(dbsObject.getDataSource()).thenReturn(null);
    DBECommandProperty<DBSObject> command =
        new DBECommandProperty<>(dbsObject, mock(DBEPropertyHandler.class));

    // Act
    createObjectReflector.undoCommand(command);

    // Assert
    verify(objectMaker).getObjectsCache(isA(DBSObject.class));
    verify(dbsObject).getDataSource();
    verify(dbsObjectCache).removeObject(isA(DBSObject.class), eq(false));
  }

  /**
   * Test DeleteObjectReflector {@link DeleteObjectReflector#redoCommand(DBECommand)}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSource} {@link DBPDataSource#getContainer()} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DeleteObjectReflector#redoCommand(DBECommand)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DeleteObjectReflector.redoCommand(DBECommand)"})
  public void testDeleteObjectReflectorRedoCommand_givenDBPDataSourceGetContainerReturnNull() {
    // Arrange
    DBSObjectCache<? extends DBSObject, DBSObject> dbsObjectCache = mock(DBSObjectCache.class);
    doNothing().when(dbsObjectCache).removeObject(Mockito.<DBSObject>any(), anyBoolean());

    DBEObjectMaker<DBSObject, DBSObject> objectMaker = mock(DBEObjectMaker.class);
    Mockito.<DBSObjectCache<? extends DBSObject, DBSObject>>when(
            objectMaker.getObjectsCache(Mockito.<DBSObject>any()))
        .thenReturn(dbsObjectCache);
    DeleteObjectReflector<DBSObject> deleteObjectReflector =
        new DeleteObjectReflector<>(objectMaker);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBSObject dbsObject = mock(DBSObject.class);
    when(dbsObject.getDataSource()).thenReturn(dbpDataSource);
    DBECommandProperty<DBSObject> command =
        new DBECommandProperty<>(dbsObject, mock(DBEPropertyHandler.class));

    // Act
    deleteObjectReflector.redoCommand(command);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(objectMaker).getObjectsCache(isA(DBSObject.class));
    verify(dbsObject).getDataSource();
    verify(dbsObjectCache).removeObject(isA(DBSObject.class), eq(false));
  }

  /**
   * Test DeleteObjectReflector {@link DeleteObjectReflector#redoCommand(DBECommand)}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceContainer#fireEvent(DBPEvent)}.
   * </ul>
   *
   * <p>Method under test: {@link DeleteObjectReflector#redoCommand(DBECommand)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DeleteObjectReflector.redoCommand(DBECommand)"})
  public void testDeleteObjectReflectorRedoCommand_thenCallsFireEvent() {
    // Arrange
    DBSObjectCache<? extends DBSObject, DBSObject> dbsObjectCache = mock(DBSObjectCache.class);
    doNothing().when(dbsObjectCache).removeObject(Mockito.<DBSObject>any(), anyBoolean());

    DBEObjectMaker<DBSObject, DBSObject> objectMaker = mock(DBEObjectMaker.class);
    Mockito.<DBSObjectCache<? extends DBSObject, DBSObject>>when(
            objectMaker.getObjectsCache(Mockito.<DBSObject>any()))
        .thenReturn(dbsObjectCache);
    DeleteObjectReflector<DBSObject> deleteObjectReflector =
        new DeleteObjectReflector<>(objectMaker);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBSObject dbsObject = mock(DBSObject.class);
    when(dbsObject.getDataSource()).thenReturn(dbpDataSource);
    DBECommandProperty<DBSObject> command =
        new DBECommandProperty<>(dbsObject, mock(DBEPropertyHandler.class));

    // Act
    deleteObjectReflector.redoCommand(command);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(objectMaker).getObjectsCache(isA(DBSObject.class));
    verify(dbsObject).getDataSource();
    verify(dbsObjectCache).removeObject(isA(DBSObject.class), eq(false));
  }

  /**
   * Test DeleteObjectReflector {@link DeleteObjectReflector#redoCommand(DBECommand)}.
   *
   * <ul>
   *   <li>Then calls {@link DBVContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DeleteObjectReflector#redoCommand(DBECommand)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DeleteObjectReflector.redoCommand(DBECommand)"})
  public void testDeleteObjectReflectorRedoCommand_thenCallsGetDataSource() {
    // Arrange
    DBSObjectCache<? extends DBSObject, DBSObject> dbsObjectCache = mock(DBSObjectCache.class);
    doNothing().when(dbsObjectCache).removeObject(Mockito.<DBSObject>any(), anyBoolean());

    DBEObjectMaker<DBSObject, DBSObject> objectMaker = mock(DBEObjectMaker.class);
    Mockito.<DBSObjectCache<? extends DBSObject, DBSObject>>when(
            objectMaker.getObjectsCache(Mockito.<DBSObject>any()))
        .thenReturn(dbsObjectCache);
    DeleteObjectReflector<DBSObject> deleteObjectReflector =
        new DeleteObjectReflector<>(objectMaker);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    DBECommand<DBSObject> command = mock(DBECommand.class);
    when(command.getObject()).thenReturn(new DBVEntityForeignKey(entity));

    // Act
    deleteObjectReflector.redoCommand(command);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(command, atLeast(1)).getObject();
    verify(objectMaker).getObjectsCache(isA(DBSObject.class));
    verify(dbsObjectCache).removeObject(isA(DBSObject.class), eq(false));
    verify(parent).getDataSource();
  }

  /**
   * Test DeleteObjectReflector {@link DeleteObjectReflector#redoCommand(DBECommand)}.
   *
   * <ul>
   *   <li>When {@link DBECommand} {@link DBECommand#getObject()} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DeleteObjectReflector#redoCommand(DBECommand)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DeleteObjectReflector.redoCommand(DBECommand)"})
  public void testDeleteObjectReflectorRedoCommand_whenDBECommandGetObjectReturnNull() {
    // Arrange
    DBSObjectCache<? extends DBSObject, DBSObject> dbsObjectCache = mock(DBSObjectCache.class);
    doNothing().when(dbsObjectCache).removeObject(Mockito.<DBSObject>any(), anyBoolean());

    DBEObjectMaker<DBSObject, DBSObject> objectMaker = mock(DBEObjectMaker.class);
    Mockito.<DBSObjectCache<? extends DBSObject, DBSObject>>when(
            objectMaker.getObjectsCache(Mockito.<DBSObject>any()))
        .thenReturn(dbsObjectCache);
    DeleteObjectReflector<DBSObject> deleteObjectReflector =
        new DeleteObjectReflector<>(objectMaker);

    DBECommand<DBSObject> command = mock(DBECommand.class);
    when(command.getObject()).thenReturn(null);

    // Act
    deleteObjectReflector.redoCommand(command);

    // Assert
    verify(command, atLeast(1)).getObject();
    verify(objectMaker).getObjectsCache(isNull());
    verify(dbsObjectCache).removeObject(isNull(), eq(false));
  }

  /**
   * Test DeleteObjectReflector {@link DeleteObjectReflector#redoCommand(DBECommand)}.
   *
   * <ul>
   *   <li>When {@link DBSObject} {@link DBSObject#getDataSource()} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DeleteObjectReflector#redoCommand(DBECommand)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DeleteObjectReflector.redoCommand(DBECommand)"})
  public void testDeleteObjectReflectorRedoCommand_whenDBSObjectGetDataSourceReturnNull() {
    // Arrange
    DBSObjectCache<? extends DBSObject, DBSObject> dbsObjectCache = mock(DBSObjectCache.class);
    doNothing().when(dbsObjectCache).removeObject(Mockito.<DBSObject>any(), anyBoolean());

    DBEObjectMaker<DBSObject, DBSObject> objectMaker = mock(DBEObjectMaker.class);
    Mockito.<DBSObjectCache<? extends DBSObject, DBSObject>>when(
            objectMaker.getObjectsCache(Mockito.<DBSObject>any()))
        .thenReturn(dbsObjectCache);
    DeleteObjectReflector<DBSObject> deleteObjectReflector =
        new DeleteObjectReflector<>(objectMaker);

    DBSObject dbsObject = mock(DBSObject.class);
    when(dbsObject.getDataSource()).thenReturn(null);
    DBECommandProperty<DBSObject> command =
        new DBECommandProperty<>(dbsObject, mock(DBEPropertyHandler.class));

    // Act
    deleteObjectReflector.redoCommand(command);

    // Assert
    verify(objectMaker).getObjectsCache(isA(DBSObject.class));
    verify(dbsObject).getDataSource();
    verify(dbsObjectCache).removeObject(isA(DBSObject.class), eq(false));
  }

  /**
   * Test DeleteObjectReflector {@link DeleteObjectReflector#undoCommand(DBECommand)}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSource} {@link DBPDataSource#getContainer()} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DeleteObjectReflector#undoCommand(DBECommand)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DeleteObjectReflector.undoCommand(DBECommand)"})
  public void testDeleteObjectReflectorUndoCommand_givenDBPDataSourceGetContainerReturnNull() {
    // Arrange
    DBSObjectCache<? extends DBSObject, DBSObject> dbsObjectCache = mock(DBSObjectCache.class);
    doNothing().when(dbsObjectCache).cacheObject(Mockito.<DBSObject>any());

    DBEObjectMaker<DBSObject, DBSObject> objectMaker = mock(DBEObjectMaker.class);
    Mockito.<DBSObjectCache<? extends DBSObject, DBSObject>>when(
            objectMaker.getObjectsCache(Mockito.<DBSObject>any()))
        .thenReturn(dbsObjectCache);
    DeleteObjectReflector<DBSObject> deleteObjectReflector =
        new DeleteObjectReflector<>(objectMaker);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBSObject dbsObject = mock(DBSObject.class);
    when(dbsObject.getDataSource()).thenReturn(dbpDataSource);
    DBECommandProperty<DBSObject> command =
        new DBECommandProperty<>(dbsObject, mock(DBEPropertyHandler.class));

    // Act
    deleteObjectReflector.undoCommand(command);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(objectMaker).getObjectsCache(isA(DBSObject.class));
    verify(dbsObject).getDataSource();
    verify(dbsObjectCache).cacheObject(isA(DBSObject.class));
  }

  /**
   * Test DeleteObjectReflector {@link DeleteObjectReflector#undoCommand(DBECommand)}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceContainer#fireEvent(DBPEvent)}.
   * </ul>
   *
   * <p>Method under test: {@link DeleteObjectReflector#undoCommand(DBECommand)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DeleteObjectReflector.undoCommand(DBECommand)"})
  public void testDeleteObjectReflectorUndoCommand_thenCallsFireEvent() {
    // Arrange
    DBSObjectCache<? extends DBSObject, DBSObject> dbsObjectCache = mock(DBSObjectCache.class);
    doNothing().when(dbsObjectCache).cacheObject(Mockito.<DBSObject>any());

    DBEObjectMaker<DBSObject, DBSObject> objectMaker = mock(DBEObjectMaker.class);
    Mockito.<DBSObjectCache<? extends DBSObject, DBSObject>>when(
            objectMaker.getObjectsCache(Mockito.<DBSObject>any()))
        .thenReturn(dbsObjectCache);
    DeleteObjectReflector<DBSObject> deleteObjectReflector =
        new DeleteObjectReflector<>(objectMaker);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBSObject dbsObject = mock(DBSObject.class);
    when(dbsObject.getDataSource()).thenReturn(dbpDataSource);
    DBECommandProperty<DBSObject> command =
        new DBECommandProperty<>(dbsObject, mock(DBEPropertyHandler.class));

    // Act
    deleteObjectReflector.undoCommand(command);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(objectMaker).getObjectsCache(isA(DBSObject.class));
    verify(dbsObject).getDataSource();
    verify(dbsObjectCache).cacheObject(isA(DBSObject.class));
  }

  /**
   * Test DeleteObjectReflector {@link DeleteObjectReflector#undoCommand(DBECommand)}.
   *
   * <ul>
   *   <li>Then calls {@link DBVContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DeleteObjectReflector#undoCommand(DBECommand)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DeleteObjectReflector.undoCommand(DBECommand)"})
  public void testDeleteObjectReflectorUndoCommand_thenCallsGetDataSource() {
    // Arrange
    DBSObjectCache<? extends DBSObject, DBSObject> dbsObjectCache = mock(DBSObjectCache.class);
    doNothing().when(dbsObjectCache).cacheObject(Mockito.<DBSObject>any());

    DBEObjectMaker<DBSObject, DBSObject> objectMaker = mock(DBEObjectMaker.class);
    Mockito.<DBSObjectCache<? extends DBSObject, DBSObject>>when(
            objectMaker.getObjectsCache(Mockito.<DBSObject>any()))
        .thenReturn(dbsObjectCache);
    DeleteObjectReflector<DBSObject> deleteObjectReflector =
        new DeleteObjectReflector<>(objectMaker);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    DBECommand<DBSObject> command = mock(DBECommand.class);
    when(command.getObject()).thenReturn(new DBVEntityForeignKey(entity));

    // Act
    deleteObjectReflector.undoCommand(command);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(command, atLeast(1)).getObject();
    verify(objectMaker).getObjectsCache(isA(DBSObject.class));
    verify(dbsObjectCache).cacheObject(isA(DBSObject.class));
    verify(parent).getDataSource();
  }

  /**
   * Test DeleteObjectReflector {@link DeleteObjectReflector#undoCommand(DBECommand)}.
   *
   * <ul>
   *   <li>When {@link DBECommand} {@link DBECommand#getObject()} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DeleteObjectReflector#undoCommand(DBECommand)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DeleteObjectReflector.undoCommand(DBECommand)"})
  public void testDeleteObjectReflectorUndoCommand_whenDBECommandGetObjectReturnNull() {
    // Arrange
    DBSObjectCache<? extends DBSObject, DBSObject> dbsObjectCache = mock(DBSObjectCache.class);
    doNothing().when(dbsObjectCache).cacheObject(Mockito.<DBSObject>any());

    DBEObjectMaker<DBSObject, DBSObject> objectMaker = mock(DBEObjectMaker.class);
    Mockito.<DBSObjectCache<? extends DBSObject, DBSObject>>when(
            objectMaker.getObjectsCache(Mockito.<DBSObject>any()))
        .thenReturn(dbsObjectCache);
    DeleteObjectReflector<DBSObject> deleteObjectReflector =
        new DeleteObjectReflector<>(objectMaker);

    DBECommand<DBSObject> command = mock(DBECommand.class);
    when(command.getObject()).thenReturn(null);

    // Act
    deleteObjectReflector.undoCommand(command);

    // Assert
    verify(command, atLeast(1)).getObject();
    verify(objectMaker).getObjectsCache(isNull());
    verify(dbsObjectCache).cacheObject(isNull());
  }

  /**
   * Test DeleteObjectReflector {@link DeleteObjectReflector#undoCommand(DBECommand)}.
   *
   * <ul>
   *   <li>When {@link DBSObject} {@link DBSObject#getDataSource()} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DeleteObjectReflector#undoCommand(DBECommand)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DeleteObjectReflector.undoCommand(DBECommand)"})
  public void testDeleteObjectReflectorUndoCommand_whenDBSObjectGetDataSourceReturnNull() {
    // Arrange
    DBSObjectCache<? extends DBSObject, DBSObject> dbsObjectCache = mock(DBSObjectCache.class);
    doNothing().when(dbsObjectCache).cacheObject(Mockito.<DBSObject>any());

    DBEObjectMaker<DBSObject, DBSObject> objectMaker = mock(DBEObjectMaker.class);
    Mockito.<DBSObjectCache<? extends DBSObject, DBSObject>>when(
            objectMaker.getObjectsCache(Mockito.<DBSObject>any()))
        .thenReturn(dbsObjectCache);
    DeleteObjectReflector<DBSObject> deleteObjectReflector =
        new DeleteObjectReflector<>(objectMaker);

    DBSObject dbsObject = mock(DBSObject.class);
    when(dbsObject.getDataSource()).thenReturn(null);
    DBECommandProperty<DBSObject> command =
        new DBECommandProperty<>(dbsObject, mock(DBEPropertyHandler.class));

    // Act
    deleteObjectReflector.undoCommand(command);

    // Assert
    verify(objectMaker).getObjectsCache(isA(DBSObject.class));
    verify(dbsObject).getDataSource();
    verify(dbsObjectCache).cacheObject(isA(DBSObject.class));
  }
}

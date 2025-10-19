package org.jkiss.dbeaver.model.virtual;

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
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSEntityConstraintType;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class DBVModelSerializerModernDiffblueTest {
  /**
   * Test {@link DBVModelSerializerModern#serializeContainer(DBRProgressMonitor, JsonWriter,
   * DBVContainer)}.
   *
   * <p>Method under test: {@link DBVModelSerializerModern#serializeContainer(DBRProgressMonitor,
   * JsonWriter, DBVContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBVModelSerializerModern.serializeContainer(DBRProgressMonitor, JsonWriter, DBVContainer)"
  })
  public void testSerializeContainer() throws IOException, DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    JsonWriter json = new JsonWriter(new StringWriter());

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBVModel object = new DBVModel(dataSourceContainer, source);
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity entity = new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "");
    object.addEntity(entity);
    DBVContainer parent3 = mock(DBVContainer.class);
    DBVContainer parent4 = new DBVContainer(parent3, "Name", new HashMap<>());
    object.addContainer(new DBVContainer(parent4, "Name"));

    // Act
    DBVModelSerializerModern.serializeContainer(monitor, json, object);

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
  }

  /**
   * Test {@link DBVModelSerializerModern#serializeContainer(DBRProgressMonitor, JsonWriter,
   * DBVContainer)}.
   *
   * <p>Method under test: {@link DBVModelSerializerModern#serializeContainer(DBRProgressMonitor,
   * JsonWriter, DBVContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBVModelSerializerModern.serializeContainer(DBRProgressMonitor, JsonWriter, DBVContainer)"
  })
  public void testSerializeContainer2() throws IOException, DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    JsonWriter json = new JsonWriter(new StringWriter());

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBVModel object = new DBVModel(dataSourceContainer, source);
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVContainer container = new DBVContainer(parent2, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", new HashMap<>());
    object.addEntity(entity);
    DBVContainer parent3 = mock(DBVContainer.class);
    DBVContainer parent4 = new DBVContainer(parent3, "Name", new HashMap<>());
    object.addContainer(new DBVContainer(parent4, "Name"));

    // Act
    DBVModelSerializerModern.serializeContainer(monitor, json, object);

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
  }

  /**
   * Test {@link DBVModelSerializerModern#serializeContainer(DBRProgressMonitor, JsonWriter,
   * DBVContainer)}.
   *
   * <p>Method under test: {@link DBVModelSerializerModern#serializeContainer(DBRProgressMonitor,
   * JsonWriter, DBVContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBVModelSerializerModern.serializeContainer(DBRProgressMonitor, JsonWriter, DBVContainer)"
  })
  public void testSerializeContainer3() throws IOException, DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    JsonWriter json = new JsonWriter(new StringWriter());

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

    DBVModel object = new DBVModel(dataSourceContainer, source);
    object.addEntity(entity);
    DBVContainer parent3 = mock(DBVContainer.class);
    DBVContainer parent4 = new DBVContainer(parent3, "Name", new HashMap<>());
    object.addContainer(new DBVContainer(parent4, "Name"));

    // Act
    DBVModelSerializerModern.serializeContainer(monitor, json, object);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBVModelSerializerModern#serializeContainer(DBRProgressMonitor, JsonWriter,
   * DBVContainer)}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSource#getContainer()}.
   * </ul>
   *
   * <p>Method under test: {@link DBVModelSerializerModern#serializeContainer(DBRProgressMonitor,
   * JsonWriter, DBVContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBVModelSerializerModern.serializeContainer(DBRProgressMonitor, JsonWriter, DBVContainer)"
  })
  public void testSerializeContainer_thenCallsGetContainer() throws IOException, DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    JsonWriter json = new JsonWriter(new StringWriter());

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

    DBVModel object = new DBVModel(dataSourceContainer, source);
    object.addEntity(entity);
    DBVContainer parent3 = mock(DBVContainer.class);
    DBVContainer parent4 = new DBVContainer(parent3, "Name", new HashMap<>());
    object.addContainer(new DBVContainer(parent4, "Name"));

    // Act
    DBVModelSerializerModern.serializeContainer(monitor, json, object);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(parent).getDataSource();
  }
}

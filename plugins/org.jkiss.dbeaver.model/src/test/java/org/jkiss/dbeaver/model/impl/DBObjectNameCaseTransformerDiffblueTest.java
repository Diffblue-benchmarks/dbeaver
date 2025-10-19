package org.jkiss.dbeaver.model.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.impl.sql.BasicSQLDialect;
import org.jkiss.dbeaver.model.struct.DBSDocumentConstraint;
import org.jkiss.dbeaver.model.struct.DBSDocumentContainer;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.jkiss.dbeaver.model.virtual.DBVContainer;
import org.jkiss.dbeaver.model.virtual.DBVEntity;
import org.jkiss.dbeaver.model.virtual.DBVEntityForeignKey;
import org.jkiss.dbeaver.model.virtual.DBVModel;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBObjectNameCaseTransformerDiffblueTest {
  /**
   * Test {@link DBObjectNameCaseTransformer#transform(DBSObject, String)} with {@code DBSObject},
   * {@code String}.
   *
   * <p>Method under test: {@link DBObjectNameCaseTransformer#transform(DBSObject, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBObjectNameCaseTransformer.transform(DBSObject, String)"})
  public void testTransformWithDBSObjectString() {
    // Arrange
    DBObjectNameCaseTransformer dbObjectNameCaseTransformer = new DBObjectNameCaseTransformer();

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenThrow(new IllegalArgumentException());
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> dbObjectNameCaseTransformer.transform(new DBVEntityForeignKey(entity), "42"));
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBObjectNameCaseTransformer#transform(DBSObject, String)} with {@code DBSObject},
   * {@code String}.
   *
   * <p>Method under test: {@link DBObjectNameCaseTransformer#transform(DBSObject, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBObjectNameCaseTransformer.transform(DBSObject, String)"})
  public void testTransformWithDBSObjectString2() {
    // Arrange
    DBObjectNameCaseTransformer dbObjectNameCaseTransformer = new DBObjectNameCaseTransformer();

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenThrow(new IllegalArgumentException());

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> dbObjectNameCaseTransformer.transform(new DBVEntityForeignKey(entity), "42"));
    verify(dbpDataSource).getSQLDialect();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBObjectNameCaseTransformer#transform(DBSObject, String)} with {@code DBSObject},
   * {@code String}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSource#getContainer()}.
   * </ul>
   *
   * <p>Method under test: {@link DBObjectNameCaseTransformer#transform(DBSObject, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBObjectNameCaseTransformer.transform(DBSObject, String)"})
  public void testTransformWithDBSObjectString_thenCallsGetContainer() {
    // Arrange
    DBObjectNameCaseTransformer dbObjectNameCaseTransformer = new DBObjectNameCaseTransformer();

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getPreferenceStore()).thenThrow(new IllegalArgumentException());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> dbObjectNameCaseTransformer.transform(new DBSDocumentConstraint(entity), "42"));
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource).getSQLDialect();
    verify(dbpDataSourceContainer).getPreferenceStore();
    verify(entity).getDataSource();
  }

  /**
   * Test {@link DBObjectNameCaseTransformer#transform(DBSObject, String)} with {@code DBSObject},
   * {@code String}.
   *
   * <ul>
   *   <li>Then calls {@link DBSDocumentContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBObjectNameCaseTransformer#transform(DBSObject, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBObjectNameCaseTransformer.transform(DBSObject, String)"})
  public void testTransformWithDBSObjectString_thenCallsGetDataSource() {
    // Arrange
    DBObjectNameCaseTransformer dbObjectNameCaseTransformer = new DBObjectNameCaseTransformer();

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenThrow(new IllegalArgumentException());

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> dbObjectNameCaseTransformer.transform(new DBSDocumentConstraint(entity), "42"));
    verify(dbpDataSource).getSQLDialect();
    verify(entity).getDataSource();
  }

  /**
   * Test {@link DBObjectNameCaseTransformer#transform(DBSObject, String)} with {@code DBSObject},
   * {@code String}.
   *
   * <ul>
   *   <li>Then return {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link DBObjectNameCaseTransformer#transform(DBSObject, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBObjectNameCaseTransformer.transform(DBSObject, String)"})
  public void testTransformWithDBSObjectString_thenReturn42() {
    // Arrange
    DBObjectNameCaseTransformer dbObjectNameCaseTransformer = new DBObjectNameCaseTransformer();
    DBVModel container = new DBVModel("42", new HashMap<>());
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    // Act and Assert
    assertEquals(
        "42", dbObjectNameCaseTransformer.transform(new DBVEntityForeignKey(entity), "42"));
  }

  /**
   * Test {@link DBObjectNameCaseTransformer#transform(DBSObject, String)} with {@code DBSObject},
   * {@code String}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBObjectNameCaseTransformer#transform(DBSObject, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBObjectNameCaseTransformer.transform(DBSObject, String)"})
  public void testTransformWithDBSObjectString_whenNull_thenReturnNull() {
    // Arrange
    DBObjectNameCaseTransformer dbObjectNameCaseTransformer = new DBObjectNameCaseTransformer();
    DBVModel container = new DBVModel("42", new HashMap<>());
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    // Act and Assert
    assertNull(dbObjectNameCaseTransformer.transform(new DBVEntityForeignKey(entity), null));
  }

  /**
   * Test {@link DBObjectNameCaseTransformer#transformObjectName(DBSObject, String)}.
   *
   * <p>Method under test: {@link DBObjectNameCaseTransformer#transformObjectName(DBSObject,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBObjectNameCaseTransformer.transformObjectName(DBSObject, String)"})
  public void testTransformObjectName() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenThrow(new IllegalArgumentException());
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            DBObjectNameCaseTransformer.transformObjectName(new DBVEntityForeignKey(entity), "42"));
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBObjectNameCaseTransformer#transformObjectName(DBSObject, String)}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSource#getContainer()}.
   * </ul>
   *
   * <p>Method under test: {@link DBObjectNameCaseTransformer#transformObjectName(DBSObject,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBObjectNameCaseTransformer.transformObjectName(DBSObject, String)"})
  public void testTransformObjectName_thenCallsGetContainer() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getPreferenceStore()).thenThrow(new IllegalArgumentException());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            DBObjectNameCaseTransformer.transformObjectName(
                new DBSDocumentConstraint(entity), "42"));
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource).getSQLDialect();
    verify(dbpDataSourceContainer).getPreferenceStore();
    verify(entity).getDataSource();
  }

  /**
   * Test {@link DBObjectNameCaseTransformer#transformObjectName(DBSObject, String)}.
   *
   * <ul>
   *   <li>Then calls {@link DBSDocumentContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBObjectNameCaseTransformer#transformObjectName(DBSObject,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBObjectNameCaseTransformer.transformObjectName(DBSObject, String)"})
  public void testTransformObjectName_thenCallsGetDataSource() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenThrow(new IllegalArgumentException());

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            DBObjectNameCaseTransformer.transformObjectName(
                new DBSDocumentConstraint(entity), "42"));
    verify(dbpDataSource).getSQLDialect();
    verify(entity).getDataSource();
  }

  /**
   * Test {@link DBObjectNameCaseTransformer#transformObjectName(DBSObject, String)}.
   *
   * <ul>
   *   <li>When {@link DBVContainer} {@link DBVContainer#getDataSource()} return {@link
   *       DBPDataSource}.
   * </ul>
   *
   * <p>Method under test: {@link DBObjectNameCaseTransformer#transformObjectName(DBSObject,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBObjectNameCaseTransformer.transformObjectName(DBSObject, String)"})
  public void testTransformObjectName_whenDBVContainerGetDataSourceReturnDBPDataSource() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenThrow(new IllegalArgumentException());

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            DBObjectNameCaseTransformer.transformObjectName(new DBVEntityForeignKey(entity), "42"));
    verify(dbpDataSource).getSQLDialect();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBObjectNameCaseTransformer#transformObjectName(DBSObject, String)}.
   *
   * <ul>
   *   <li>When {@link DBVModel#DBVModel(String, Map)} with id is {@code 42} and map is {@link
   *       HashMap#HashMap()}.
   *   <li>Then return {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link DBObjectNameCaseTransformer#transformObjectName(DBSObject,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBObjectNameCaseTransformer.transformObjectName(DBSObject, String)"})
  public void testTransformObjectName_whenDBVModelWithIdIs42AndMapIsHashMap_thenReturn42() {
    // Arrange
    DBVModel container = new DBVModel("42", new HashMap<>());
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    // Act and Assert
    assertEquals(
        "42",
        DBObjectNameCaseTransformer.transformObjectName(new DBVEntityForeignKey(entity), "42"));
  }

  /**
   * Test {@link DBObjectNameCaseTransformer#transformObjectName(DBSObject, String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBObjectNameCaseTransformer#transformObjectName(DBSObject,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBObjectNameCaseTransformer.transformObjectName(DBSObject, String)"})
  public void testTransformObjectName_whenNull_thenReturnNull() {
    // Arrange
    DBVModel container = new DBVModel("42", new HashMap<>());
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    // Act and Assert
    assertNull(
        DBObjectNameCaseTransformer.transformObjectName(new DBVEntityForeignKey(entity), null));
  }

  /**
   * Test {@link DBObjectNameCaseTransformer#transformName(DBPDataSource, String)}.
   *
   * <ul>
   *   <li>Given {@link IllegalArgumentException#IllegalArgumentException()}.
   * </ul>
   *
   * <p>Method under test: {@link DBObjectNameCaseTransformer#transformName(DBPDataSource, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBObjectNameCaseTransformer.transformName(DBPDataSource, String)"})
  public void testTransformName_givenIllegalArgumentException() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> DBObjectNameCaseTransformer.transformName(dataSource, "42"));
    verify(dataSource).getSQLDialect();
  }

  /**
   * Test {@link DBObjectNameCaseTransformer#transformName(DBPDataSource, String)}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSource#getContainer()}.
   * </ul>
   *
   * <p>Method under test: {@link DBObjectNameCaseTransformer#transformName(DBPDataSource, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBObjectNameCaseTransformer.transformName(DBPDataSource, String)"})
  public void testTransformName_thenCallsGetContainer() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getPreferenceStore()).thenThrow(new IllegalArgumentException());

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> DBObjectNameCaseTransformer.transformName(dataSource, "42"));
    verify(dataSource).getContainer();
    verify(dataSource).getSQLDialect();
    verify(dbpDataSourceContainer).getPreferenceStore();
  }

  /**
   * Test {@link DBObjectNameCaseTransformer#transformName(DBPDataSource, String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link DBObjectNameCaseTransformer#transformName(DBPDataSource, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBObjectNameCaseTransformer.transformName(DBPDataSource, String)"})
  public void testTransformName_whenNull_thenReturn42() {
    // Arrange, Act and Assert
    assertEquals("42", DBObjectNameCaseTransformer.transformName(null, "42"));
  }

  /**
   * Test {@link DBObjectNameCaseTransformer#transformName(DBPDataSource, String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBObjectNameCaseTransformer#transformName(DBPDataSource, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBObjectNameCaseTransformer.transformName(DBPDataSource, String)"})
  public void testTransformName_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(DBObjectNameCaseTransformer.transformName(null, null));
  }
}

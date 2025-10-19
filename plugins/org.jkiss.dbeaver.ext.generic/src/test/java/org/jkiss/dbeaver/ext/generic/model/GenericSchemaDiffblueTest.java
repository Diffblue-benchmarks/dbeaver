package org.jkiss.dbeaver.ext.generic.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.ext.generic.model.GenericSchema.SchemaNameTermProvider;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceInfo;
import org.jkiss.dbeaver.model.struct.DBSDocumentConstraint;
import org.jkiss.dbeaver.model.struct.DBSDocumentContainer;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class GenericSchemaDiffblueTest {
  /**
   * Test SchemaNameTermProvider {@link SchemaNameTermProvider#transform(DBSObject, String)} with
   * {@code DBSObject}, {@code String}.
   *
   * <p>Method under test: {@link SchemaNameTermProvider#transform(DBSObject, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SchemaNameTermProvider.transform(DBSObject, String)"})
  public void testSchemaNameTermProviderTransformWithDBSObjectString()
      throws IllegalArgumentException {
    // Arrange
    SchemaNameTermProvider schemaNameTermProvider = new SchemaNameTermProvider();

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getInfo()).thenThrow(new IllegalArgumentException());

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> schemaNameTermProvider.transform(new DBSDocumentConstraint(entity), "42"));
    verify(dbpDataSource).getInfo();
    verify(entity).getDataSource();
  }

  /**
   * Test SchemaNameTermProvider {@link SchemaNameTermProvider#transform(DBSObject, String)} with
   * {@code DBSObject}, {@code String}.
   *
   * <p>Method under test: {@link SchemaNameTermProvider#transform(DBSObject, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SchemaNameTermProvider.transform(DBSObject, String)"})
  public void testSchemaNameTermProviderTransformWithDBSObjectString2()
      throws IllegalArgumentException {
    // Arrange
    SchemaNameTermProvider schemaNameTermProvider = new SchemaNameTermProvider();

    DBPDataSourceInfo dbpDataSourceInfo = mock(DBPDataSourceInfo.class);
    when(dbpDataSourceInfo.getSchemaTerm()).thenReturn("");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getInfo()).thenReturn(dbpDataSourceInfo);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);

    // Act
    String actualTransformResult =
        schemaNameTermProvider.transform(new DBSDocumentConstraint(entity), "42");

    // Assert
    verify(dbpDataSource).getInfo();
    verify(dbpDataSourceInfo).getSchemaTerm();
    verify(entity).getDataSource();
    assertEquals("Name", actualTransformResult);
  }

  /**
   * Test SchemaNameTermProvider {@link SchemaNameTermProvider#transform(DBSObject, String)} with
   * {@code DBSObject}, {@code String}.
   *
   * <p>Method under test: {@link SchemaNameTermProvider#transform(DBSObject, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SchemaNameTermProvider.transform(DBSObject, String)"})
  public void testSchemaNameTermProviderTransformWithDBSObjectString3()
      throws IllegalArgumentException {
    // Arrange
    SchemaNameTermProvider schemaNameTermProvider = new SchemaNameTermProvider();

    DBPDataSourceInfo dbpDataSourceInfo = mock(DBPDataSourceInfo.class);
    when(dbpDataSourceInfo.getSchemaTerm()).thenReturn(null);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getInfo()).thenReturn(dbpDataSourceInfo);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);

    // Act
    String actualTransformResult =
        schemaNameTermProvider.transform(new DBSDocumentConstraint(entity), "42");

    // Assert
    verify(dbpDataSource).getInfo();
    verify(dbpDataSourceInfo).getSchemaTerm();
    verify(entity).getDataSource();
    assertEquals("Name", actualTransformResult);
  }

  /**
   * Test SchemaNameTermProvider {@link SchemaNameTermProvider#transform(DBSObject, String)} with
   * {@code DBSObject}, {@code String}.
   *
   * <p>Method under test: {@link SchemaNameTermProvider#transform(DBSObject, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SchemaNameTermProvider.transform(DBSObject, String)"})
  public void testSchemaNameTermProviderTransformWithDBSObjectString4()
      throws IllegalArgumentException {
    // Arrange
    SchemaNameTermProvider schemaNameTermProvider = new SchemaNameTermProvider();

    DBPDataSourceInfo dbpDataSourceInfo = mock(DBPDataSourceInfo.class);
    when(dbpDataSourceInfo.getSchemaTerm()).thenThrow(new IllegalArgumentException());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getInfo()).thenReturn(dbpDataSourceInfo);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> schemaNameTermProvider.transform(new DBSDocumentConstraint(entity), "42"));
    verify(dbpDataSource).getInfo();
    verify(dbpDataSourceInfo).getSchemaTerm();
    verify(entity).getDataSource();
  }

  /**
   * Test SchemaNameTermProvider {@link SchemaNameTermProvider#transform(DBSObject, String)} with
   * {@code DBSObject}, {@code String}.
   *
   * <ul>
   *   <li>Then return {@code Schema Term Name}.
   * </ul>
   *
   * <p>Method under test: {@link SchemaNameTermProvider#transform(DBSObject, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SchemaNameTermProvider.transform(DBSObject, String)"})
  public void testSchemaNameTermProviderTransformWithDBSObjectString_thenReturnSchemaTermName()
      throws IllegalArgumentException {
    // Arrange
    SchemaNameTermProvider schemaNameTermProvider = new SchemaNameTermProvider();

    DBPDataSourceInfo dbpDataSourceInfo = mock(DBPDataSourceInfo.class);
    when(dbpDataSourceInfo.getSchemaTerm()).thenReturn("Schema Term");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getInfo()).thenReturn(dbpDataSourceInfo);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);

    // Act
    String actualTransformResult =
        schemaNameTermProvider.transform(new DBSDocumentConstraint(entity), "42");

    // Assert
    verify(dbpDataSource).getInfo();
    verify(dbpDataSourceInfo).getSchemaTerm();
    verify(entity).getDataSource();
    assertEquals("Schema Term Name", actualTransformResult);
  }
}

package org.jkiss.dbeaver.ext.generic.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.ext.generic.model.GenericCatalog.CatalogNameTermProvider;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceInfo;
import org.jkiss.dbeaver.model.struct.DBSDocumentConstraint;
import org.jkiss.dbeaver.model.struct.DBSDocumentContainer;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class GenericCatalogDiffblueTest {
  /**
   * Test CatalogNameTermProvider {@link CatalogNameTermProvider#transform(DBSObject, String)} with
   * {@code DBSObject}, {@code String}.
   *
   * <p>Method under test: {@link CatalogNameTermProvider#transform(DBSObject, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String CatalogNameTermProvider.transform(DBSObject, String)"})
  public void testCatalogNameTermProviderTransformWithDBSObjectString()
      throws IllegalArgumentException {
    // Arrange
    CatalogNameTermProvider catalogNameTermProvider = new CatalogNameTermProvider();

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getInfo()).thenThrow(new IllegalArgumentException());

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> catalogNameTermProvider.transform(new DBSDocumentConstraint(entity), "42"));
    verify(dbpDataSource).getInfo();
    verify(entity).getDataSource();
  }

  /**
   * Test CatalogNameTermProvider {@link CatalogNameTermProvider#transform(DBSObject, String)} with
   * {@code DBSObject}, {@code String}.
   *
   * <p>Method under test: {@link CatalogNameTermProvider#transform(DBSObject, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String CatalogNameTermProvider.transform(DBSObject, String)"})
  public void testCatalogNameTermProviderTransformWithDBSObjectString2()
      throws IllegalArgumentException {
    // Arrange
    CatalogNameTermProvider catalogNameTermProvider = new CatalogNameTermProvider();

    DBPDataSourceInfo dbpDataSourceInfo = mock(DBPDataSourceInfo.class);
    when(dbpDataSourceInfo.getCatalogTerm()).thenReturn("");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getInfo()).thenReturn(dbpDataSourceInfo);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);

    // Act
    String actualTransformResult =
        catalogNameTermProvider.transform(new DBSDocumentConstraint(entity), "42");

    // Assert
    verify(dbpDataSource).getInfo();
    verify(dbpDataSourceInfo).getCatalogTerm();
    verify(entity).getDataSource();
    assertEquals("Name", actualTransformResult);
  }

  /**
   * Test CatalogNameTermProvider {@link CatalogNameTermProvider#transform(DBSObject, String)} with
   * {@code DBSObject}, {@code String}.
   *
   * <p>Method under test: {@link CatalogNameTermProvider#transform(DBSObject, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String CatalogNameTermProvider.transform(DBSObject, String)"})
  public void testCatalogNameTermProviderTransformWithDBSObjectString3()
      throws IllegalArgumentException {
    // Arrange
    CatalogNameTermProvider catalogNameTermProvider = new CatalogNameTermProvider();

    DBPDataSourceInfo dbpDataSourceInfo = mock(DBPDataSourceInfo.class);
    when(dbpDataSourceInfo.getCatalogTerm()).thenReturn(null);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getInfo()).thenReturn(dbpDataSourceInfo);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);

    // Act
    String actualTransformResult =
        catalogNameTermProvider.transform(new DBSDocumentConstraint(entity), "42");

    // Assert
    verify(dbpDataSource).getInfo();
    verify(dbpDataSourceInfo).getCatalogTerm();
    verify(entity).getDataSource();
    assertEquals("Name", actualTransformResult);
  }

  /**
   * Test CatalogNameTermProvider {@link CatalogNameTermProvider#transform(DBSObject, String)} with
   * {@code DBSObject}, {@code String}.
   *
   * <p>Method under test: {@link CatalogNameTermProvider#transform(DBSObject, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String CatalogNameTermProvider.transform(DBSObject, String)"})
  public void testCatalogNameTermProviderTransformWithDBSObjectString4()
      throws IllegalArgumentException {
    // Arrange
    CatalogNameTermProvider catalogNameTermProvider = new CatalogNameTermProvider();

    DBPDataSourceInfo dbpDataSourceInfo = mock(DBPDataSourceInfo.class);
    when(dbpDataSourceInfo.getCatalogTerm()).thenReturn("Catalog Term");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getInfo()).thenReturn(dbpDataSourceInfo);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);

    // Act
    String actualTransformResult =
        catalogNameTermProvider.transform(new DBSDocumentConstraint(entity), "42");

    // Assert
    verify(dbpDataSource).getInfo();
    verify(dbpDataSourceInfo).getCatalogTerm();
    verify(entity).getDataSource();
    assertEquals("Catalog Term Name", actualTransformResult);
  }

  /**
   * Test CatalogNameTermProvider {@link CatalogNameTermProvider#transform(DBSObject, String)} with
   * {@code DBSObject}, {@code String}.
   *
   * <p>Method under test: {@link CatalogNameTermProvider#transform(DBSObject, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String CatalogNameTermProvider.transform(DBSObject, String)"})
  public void testCatalogNameTermProviderTransformWithDBSObjectString5()
      throws IllegalArgumentException {
    // Arrange
    CatalogNameTermProvider catalogNameTermProvider = new CatalogNameTermProvider();

    DBPDataSourceInfo dbpDataSourceInfo = mock(DBPDataSourceInfo.class);
    when(dbpDataSourceInfo.getCatalogTerm()).thenThrow(new IllegalArgumentException());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getInfo()).thenReturn(dbpDataSourceInfo);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> catalogNameTermProvider.transform(new DBSDocumentConstraint(entity), "42"));
    verify(dbpDataSource).getInfo();
    verify(dbpDataSourceInfo).getCatalogTerm();
    verify(entity).getDataSource();
  }
}

package org.jkiss.dbeaver.model.connection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.jkiss.dbeaver.runtime.IVariableResolver;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class DBPConnectionBootstrapDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBPConnectionBootstrap#DBPConnectionBootstrap()}
   *   <li>{@link DBPConnectionBootstrap#setDefaultCatalogName(String)}
   *   <li>{@link DBPConnectionBootstrap#setDefaultSchemaName(String)}
   *   <li>{@link DBPConnectionBootstrap#setDefaultTransactionIsolation(Integer)}
   *   <li>{@link DBPConnectionBootstrap#setIgnoreErrors(boolean)}
   *   <li>{@link DBPConnectionBootstrap#setDefaultAutoCommit(Boolean)}
   *   <li>{@link DBPConnectionBootstrap#getDefaultAutoCommit()}
   *   <li>{@link DBPConnectionBootstrap#getDefaultCatalogName()}
   *   <li>{@link DBPConnectionBootstrap#getDefaultSchemaName()}
   *   <li>{@link DBPConnectionBootstrap#getDefaultTransactionIsolation()}
   *   <li>{@link DBPConnectionBootstrap#getInitQueries()}
   *   <li>{@link DBPConnectionBootstrap#isIgnoreErrors()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBPConnectionBootstrap.<init>()",
    "Boolean DBPConnectionBootstrap.getDefaultAutoCommit()",
    "String DBPConnectionBootstrap.getDefaultCatalogName()",
    "String DBPConnectionBootstrap.getDefaultSchemaName()",
    "Integer DBPConnectionBootstrap.getDefaultTransactionIsolation()",
    "List DBPConnectionBootstrap.getInitQueries()",
    "boolean DBPConnectionBootstrap.isIgnoreErrors()",
    "void DBPConnectionBootstrap.setDefaultAutoCommit(Boolean)",
    "void DBPConnectionBootstrap.setDefaultCatalogName(String)",
    "void DBPConnectionBootstrap.setDefaultSchemaName(String)",
    "void DBPConnectionBootstrap.setDefaultTransactionIsolation(Integer)",
    "void DBPConnectionBootstrap.setIgnoreErrors(boolean)"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    DBPConnectionBootstrap actualDbpConnectionBootstrap = new DBPConnectionBootstrap();
    actualDbpConnectionBootstrap.setDefaultCatalogName("Default Catalog Name");
    actualDbpConnectionBootstrap.setDefaultSchemaName("Default Schema Name");
    actualDbpConnectionBootstrap.setDefaultTransactionIsolation(1);
    actualDbpConnectionBootstrap.setIgnoreErrors(true);
    actualDbpConnectionBootstrap.setDefaultAutoCommit(true);
    Boolean actualDefaultAutoCommit = actualDbpConnectionBootstrap.getDefaultAutoCommit();
    String actualDefaultCatalogName = actualDbpConnectionBootstrap.getDefaultCatalogName();
    String actualDefaultSchemaName = actualDbpConnectionBootstrap.getDefaultSchemaName();
    Integer actualDefaultTransactionIsolation =
        actualDbpConnectionBootstrap.getDefaultTransactionIsolation();
    List<String> actualInitQueries = actualDbpConnectionBootstrap.getInitQueries();
    boolean actualIsIgnoreErrorsResult = actualDbpConnectionBootstrap.isIgnoreErrors();

    // Assert
    assertEquals("Default Catalog Name", actualDefaultCatalogName);
    assertEquals("Default Schema Name", actualDefaultSchemaName);
    assertEquals(1, actualDefaultTransactionIsolation.intValue());
    assertTrue(actualInitQueries.isEmpty());
    assertTrue(actualDefaultAutoCommit);
    assertTrue(actualIsIgnoreErrorsResult);
  }

  /**
   * Test {@link DBPConnectionBootstrap#DBPConnectionBootstrap(DBPConnectionBootstrap)}.
   *
   * <ul>
   *   <li>Then return DefaultAutoCommit is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DBPConnectionBootstrap#DBPConnectionBootstrap(DBPConnectionBootstrap)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBPConnectionBootstrap.<init>(DBPConnectionBootstrap)"})
  public void testNewDBPConnectionBootstrap_thenReturnDefaultAutoCommitIsNull() {
    // Arrange and Act
    DBPConnectionBootstrap actualDbpConnectionBootstrap =
        new DBPConnectionBootstrap(new DBPConnectionBootstrap());

    // Assert
    assertNull(actualDbpConnectionBootstrap.getDefaultAutoCommit());
    assertNull(actualDbpConnectionBootstrap.getDefaultTransactionIsolation());
    assertNull(actualDbpConnectionBootstrap.getDefaultCatalogName());
    assertNull(actualDbpConnectionBootstrap.getDefaultSchemaName());
    assertFalse(actualDbpConnectionBootstrap.hasData());
    assertFalse(actualDbpConnectionBootstrap.isIgnoreErrors());
    assertTrue(actualDbpConnectionBootstrap.getInitQueries().isEmpty());
  }

  /**
   * Test {@link DBPConnectionBootstrap#setInitQueries(Collection)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.
   *   <li>Then {@link DBPConnectionBootstrap#DBPConnectionBootstrap()} hasData.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionBootstrap#setInitQueries(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBPConnectionBootstrap.setInitQueries(Collection)"})
  public void testSetInitQueries_given42_whenArrayListAdd42_thenDBPConnectionBootstrapHasData() {
    // Arrange
    DBPConnectionBootstrap dbpConnectionBootstrap = new DBPConnectionBootstrap();

    ArrayList<String> queries = new ArrayList<>();
    queries.add("42");
    queries.add("foo");

    // Act
    dbpConnectionBootstrap.setInitQueries(queries);

    // Assert
    assertTrue(dbpConnectionBootstrap.hasData());
    assertEquals(queries, dbpConnectionBootstrap.getInitQueries());
  }

  /**
   * Test {@link DBPConnectionBootstrap#setInitQueries(Collection)}.
   *
   * <ul>
   *   <li>Given {@code foo}.
   *   <li>Then {@link DBPConnectionBootstrap#DBPConnectionBootstrap()} hasData.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionBootstrap#setInitQueries(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBPConnectionBootstrap.setInitQueries(Collection)"})
  public void testSetInitQueries_givenFoo_thenDBPConnectionBootstrapHasData() {
    // Arrange
    DBPConnectionBootstrap dbpConnectionBootstrap = new DBPConnectionBootstrap();

    ArrayList<String> queries = new ArrayList<>();
    queries.add("foo");

    // Act
    dbpConnectionBootstrap.setInitQueries(queries);

    // Assert
    assertTrue(dbpConnectionBootstrap.hasData());
    assertEquals(queries, dbpConnectionBootstrap.getInitQueries());
  }

  /**
   * Test {@link DBPConnectionBootstrap#setInitQueries(Collection)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then not {@link DBPConnectionBootstrap#DBPConnectionBootstrap()} hasData.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionBootstrap#setInitQueries(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBPConnectionBootstrap.setInitQueries(Collection)"})
  public void testSetInitQueries_whenArrayList_thenNotDBPConnectionBootstrapHasData() {
    // Arrange
    DBPConnectionBootstrap dbpConnectionBootstrap = new DBPConnectionBootstrap();

    // Act
    dbpConnectionBootstrap.setInitQueries(new ArrayList<>());

    // Assert that nothing has changed
    assertFalse(dbpConnectionBootstrap.hasData());
    assertTrue(dbpConnectionBootstrap.getInitQueries().isEmpty());
  }

  /**
   * Test {@link DBPConnectionBootstrap#hasData()}.
   *
   * <ul>
   *   <li>Given {@link DBPConnectionBootstrap#DBPConnectionBootstrap()} DefaultCatalogName is
   *       {@code Default Catalog Name}.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionBootstrap#hasData()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBPConnectionBootstrap.hasData()"})
  public void testHasData_givenDBPConnectionBootstrapDefaultCatalogNameIsDefaultCatalogName() {
    // Arrange
    DBPConnectionBootstrap dbpConnectionBootstrap = new DBPConnectionBootstrap();
    dbpConnectionBootstrap.setDefaultCatalogName("Default Catalog Name");

    // Act and Assert
    assertTrue(dbpConnectionBootstrap.hasData());
  }

  /**
   * Test {@link DBPConnectionBootstrap#hasData()}.
   *
   * <ul>
   *   <li>Given {@link DBPConnectionBootstrap#DBPConnectionBootstrap()} DefaultCatalogName is empty
   *       string.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionBootstrap#hasData()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBPConnectionBootstrap.hasData()"})
  public void testHasData_givenDBPConnectionBootstrapDefaultCatalogNameIsEmptyString() {
    // Arrange
    DBPConnectionBootstrap dbpConnectionBootstrap = new DBPConnectionBootstrap();
    dbpConnectionBootstrap.setDefaultCatalogName("");

    // Act and Assert
    assertFalse(dbpConnectionBootstrap.hasData());
  }

  /**
   * Test {@link DBPConnectionBootstrap#hasData()}.
   *
   * <ul>
   *   <li>Given {@link DBPConnectionBootstrap#DBPConnectionBootstrap()} DefaultSchemaName is {@code
   *       Default Schema Name}.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionBootstrap#hasData()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBPConnectionBootstrap.hasData()"})
  public void testHasData_givenDBPConnectionBootstrapDefaultSchemaNameIsDefaultSchemaName() {
    // Arrange
    DBPConnectionBootstrap dbpConnectionBootstrap = new DBPConnectionBootstrap();
    dbpConnectionBootstrap.setDefaultSchemaName("Default Schema Name");

    // Act and Assert
    assertTrue(dbpConnectionBootstrap.hasData());
  }

  /**
   * Test {@link DBPConnectionBootstrap#hasData()}.
   *
   * <ul>
   *   <li>Given {@link DBPConnectionBootstrap#DBPConnectionBootstrap()} DefaultTransactionIsolation
   *       is one.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionBootstrap#hasData()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBPConnectionBootstrap.hasData()"})
  public void testHasData_givenDBPConnectionBootstrapDefaultTransactionIsolationIsOne() {
    // Arrange
    DBPConnectionBootstrap dbpConnectionBootstrap = new DBPConnectionBootstrap();
    dbpConnectionBootstrap.setDefaultTransactionIsolation(1);

    // Act and Assert
    assertTrue(dbpConnectionBootstrap.hasData());
  }

  /**
   * Test {@link DBPConnectionBootstrap#hasData()}.
   *
   * <ul>
   *   <li>Given {@link DBPConnectionBootstrap#DBPConnectionBootstrap()} IgnoreErrors is {@code
   *       true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionBootstrap#hasData()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBPConnectionBootstrap.hasData()"})
  public void testHasData_givenDBPConnectionBootstrapIgnoreErrorsIsTrue_thenReturnTrue() {
    // Arrange
    DBPConnectionBootstrap dbpConnectionBootstrap = new DBPConnectionBootstrap();
    dbpConnectionBootstrap.setIgnoreErrors(true);

    // Act and Assert
    assertTrue(dbpConnectionBootstrap.hasData());
  }

  /**
   * Test {@link DBPConnectionBootstrap#hasData()}.
   *
   * <ul>
   *   <li>Given {@link DBPConnectionBootstrap#DBPConnectionBootstrap()}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionBootstrap#hasData()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBPConnectionBootstrap.hasData()"})
  public void testHasData_givenDBPConnectionBootstrap_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new DBPConnectionBootstrap().hasData());
  }

  /**
   * Test {@link DBPConnectionBootstrap#equals(Object)}, and {@link
   * DBPConnectionBootstrap#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionBootstrap#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBPConnectionBootstrap.equals(Object)"})
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    DBPConnectionBootstrap dbpConnectionBootstrap = new DBPConnectionBootstrap();
    DBPConnectionBootstrap dbpConnectionBootstrap2 = new DBPConnectionBootstrap();

    // Act and Assert
    assertEquals(dbpConnectionBootstrap, dbpConnectionBootstrap2);
    assertNotEquals(dbpConnectionBootstrap.hashCode(), dbpConnectionBootstrap2.hashCode());
  }

  /**
   * Test {@link DBPConnectionBootstrap#equals(Object)}, and {@link
   * DBPConnectionBootstrap#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionBootstrap#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBPConnectionBootstrap.equals(Object)"})
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    DBPConnectionBootstrap dbpConnectionBootstrap = new DBPConnectionBootstrap();

    // Act and Assert
    assertEquals(dbpConnectionBootstrap, dbpConnectionBootstrap);
    int expectedHashCodeResult = dbpConnectionBootstrap.hashCode();
    assertEquals(expectedHashCodeResult, dbpConnectionBootstrap.hashCode());
  }

  /**
   * Test {@link DBPConnectionBootstrap#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionBootstrap#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBPConnectionBootstrap.equals(Object)"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new DBPConnectionBootstrap(), 1);
  }

  /**
   * Test {@link DBPConnectionBootstrap#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionBootstrap#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBPConnectionBootstrap.equals(Object)"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    DBPConnectionBootstrap dbpConnectionBootstrap = new DBPConnectionBootstrap();
    dbpConnectionBootstrap.setDefaultCatalogName("Default Catalog Name");

    // Act and Assert
    assertNotEquals(dbpConnectionBootstrap, new DBPConnectionBootstrap());
  }

  /**
   * Test {@link DBPConnectionBootstrap#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionBootstrap#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBPConnectionBootstrap.equals(Object)"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    DBPConnectionBootstrap dbpConnectionBootstrap = new DBPConnectionBootstrap();
    dbpConnectionBootstrap.setDefaultSchemaName("Default Schema Name");

    // Act and Assert
    assertNotEquals(dbpConnectionBootstrap, new DBPConnectionBootstrap());
  }

  /**
   * Test {@link DBPConnectionBootstrap#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionBootstrap#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBPConnectionBootstrap.equals(Object)"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    DBPConnectionBootstrap dbpConnectionBootstrap = new DBPConnectionBootstrap();
    dbpConnectionBootstrap.setDefaultTransactionIsolation(1);

    // Act and Assert
    assertNotEquals(dbpConnectionBootstrap, new DBPConnectionBootstrap());
  }

  /**
   * Test {@link DBPConnectionBootstrap#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionBootstrap#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBPConnectionBootstrap.equals(Object)"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual5() {
    // Arrange
    DBPConnectionBootstrap dbpConnectionBootstrap = new DBPConnectionBootstrap();
    dbpConnectionBootstrap.setIgnoreErrors(true);

    // Act and Assert
    assertNotEquals(dbpConnectionBootstrap, new DBPConnectionBootstrap());
  }

  /**
   * Test {@link DBPConnectionBootstrap#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionBootstrap#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBPConnectionBootstrap.equals(Object)"})
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new DBPConnectionBootstrap(), null);
  }

  /**
   * Test {@link DBPConnectionBootstrap#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionBootstrap#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBPConnectionBootstrap.equals(Object)"})
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new DBPConnectionBootstrap(), "Different type to DBPConnectionBootstrap");
  }

  /**
   * Test {@link DBPConnectionBootstrap#resolveDynamicVariables(IVariableResolver)}.
   *
   * <p>Method under test: {@link DBPConnectionBootstrap#resolveDynamicVariables(IVariableResolver)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBPConnectionBootstrap.resolveDynamicVariables(IVariableResolver)"})
  public void testResolveDynamicVariables() {
    // Arrange
    DBPConnectionBootstrap dbpConnectionBootstrap = new DBPConnectionBootstrap();
    dbpConnectionBootstrap.setDefaultCatalogName("Default Catalog Name");

    // Act
    dbpConnectionBootstrap.resolveDynamicVariables(mock(IVariableResolver.class));

    // Assert that nothing has changed
    assertEquals("Default Catalog Name", dbpConnectionBootstrap.getDefaultCatalogName());
  }

  /**
   * Test {@link DBPConnectionBootstrap#resolveDynamicVariables(IVariableResolver)}.
   *
   * <p>Method under test: {@link DBPConnectionBootstrap#resolveDynamicVariables(IVariableResolver)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBPConnectionBootstrap.resolveDynamicVariables(IVariableResolver)"})
  public void testResolveDynamicVariables2() {
    // Arrange
    DBPConnectionBootstrap dbpConnectionBootstrap = new DBPConnectionBootstrap();
    dbpConnectionBootstrap.setDefaultCatalogName("");

    // Act
    dbpConnectionBootstrap.resolveDynamicVariables(mock(IVariableResolver.class));

    // Assert that nothing has changed
    assertEquals("", dbpConnectionBootstrap.getDefaultCatalogName());
  }

  /**
   * Test {@link DBPConnectionBootstrap#resolveDynamicVariables(IVariableResolver)}.
   *
   * <ul>
   *   <li>Then {@link DBPConnectionBootstrap#DBPConnectionBootstrap()} DefaultCatalogName is {@code
   *       Get}.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionBootstrap#resolveDynamicVariables(IVariableResolver)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBPConnectionBootstrap.resolveDynamicVariables(IVariableResolver)"})
  public void testResolveDynamicVariables_thenDBPConnectionBootstrapDefaultCatalogNameIsGet() {
    // Arrange
    DBPConnectionBootstrap dbpConnectionBootstrap = new DBPConnectionBootstrap();
    dbpConnectionBootstrap.setDefaultCatalogName("${U:U}");

    IVariableResolver variableResolver = mock(IVariableResolver.class);
    when(variableResolver.get(Mockito.<String>any())).thenReturn("Get");

    // Act
    dbpConnectionBootstrap.resolveDynamicVariables(variableResolver);

    // Assert
    verify(variableResolver).get("U");
    assertEquals("Get", dbpConnectionBootstrap.getDefaultCatalogName());
  }

  /**
   * Test {@link DBPConnectionBootstrap#resolveDynamicVariables(IVariableResolver)}.
   *
   * <ul>
   *   <li>Then {@link DBPConnectionBootstrap#DBPConnectionBootstrap()} DefaultCatalogName is {@code
   *       null}.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionBootstrap#resolveDynamicVariables(IVariableResolver)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBPConnectionBootstrap.resolveDynamicVariables(IVariableResolver)"})
  public void testResolveDynamicVariables_thenDBPConnectionBootstrapDefaultCatalogNameIsNull() {
    // Arrange
    DBPConnectionBootstrap dbpConnectionBootstrap = new DBPConnectionBootstrap();

    // Act
    dbpConnectionBootstrap.resolveDynamicVariables(mock(IVariableResolver.class));

    // Assert that nothing has changed
    assertNull(dbpConnectionBootstrap.getDefaultCatalogName());
  }
}

package org.jkiss.dbeaver.model.sql.schema;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import javax.management.loading.MLet;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLSchemaConfigDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@link SQLInitialSchemaFiller}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SQLSchemaConfig#SQLSchemaConfig(String, String, String, int, int,
   *       SQLSchemaVersionManager, ClassLoader, SQLInitialSchemaFiller)}
   *   <li>{@link SQLSchemaConfig#setInitialSchemaFiller(SQLInitialSchemaFiller)}
   *   <li>{@link SQLSchemaConfig#getClassLoader()}
   *   <li>{@link SQLSchemaConfig#getCreateScriptPath()}
   *   <li>{@link SQLSchemaConfig#getInitialSchemaFiller()}
   *   <li>{@link SQLSchemaConfig#getSchemaId()}
   *   <li>{@link SQLSchemaConfig#getSchemaVersionActual()}
   *   <li>{@link SQLSchemaConfig#getSchemaVersionObsolete()}
   *   <li>{@link SQLSchemaConfig#getUpdateScriptPrefix()}
   *   <li>{@link SQLSchemaConfig#getVersionManager()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLSchemaConfig.<init>(String, String, String, int, int, SQLSchemaVersionManager, ClassLoader)",
    "void SQLSchemaConfig.<init>(String, String, String, int, int, SQLSchemaVersionManager, ClassLoader, SQLInitialSchemaFiller)",
    "ClassLoader SQLSchemaConfig.getClassLoader()",
    "String SQLSchemaConfig.getCreateScriptPath()",
    "SQLInitialSchemaFiller SQLSchemaConfig.getInitialSchemaFiller()",
    "String SQLSchemaConfig.getSchemaId()",
    "int SQLSchemaConfig.getSchemaVersionActual()",
    "int SQLSchemaConfig.getSchemaVersionObsolete()",
    "String SQLSchemaConfig.getUpdateScriptPrefix()",
    "SQLSchemaVersionManager SQLSchemaConfig.getVersionManager()",
    "void SQLSchemaConfig.setInitialSchemaFiller(SQLInitialSchemaFiller)"
  })
  public void testGettersAndSetters_whenSQLInitialSchemaFiller() {
    // Arrange
    SQLSchemaVersionManager versionManager = mock(SQLSchemaVersionManager.class);
    MLet classLoader = new MLet();

    // Act
    SQLSchemaConfig actualSqlSchemaConfig =
        new SQLSchemaConfig(
            "42",
            "Create Script Path",
            "2020-03-01",
            1,
            1,
            versionManager,
            classLoader,
            mock(SQLInitialSchemaFiller.class));
    SQLInitialSchemaFiller initialSchemaFiller = mock(SQLInitialSchemaFiller.class);
    actualSqlSchemaConfig.setInitialSchemaFiller(initialSchemaFiller);
    ClassLoader actualClassLoader = actualSqlSchemaConfig.getClassLoader();
    String actualCreateScriptPath = actualSqlSchemaConfig.getCreateScriptPath();
    SQLInitialSchemaFiller actualInitialSchemaFiller =
        actualSqlSchemaConfig.getInitialSchemaFiller();
    String actualSchemaId = actualSqlSchemaConfig.getSchemaId();
    int actualSchemaVersionActual = actualSqlSchemaConfig.getSchemaVersionActual();
    int actualSchemaVersionObsolete = actualSqlSchemaConfig.getSchemaVersionObsolete();
    String actualUpdateScriptPrefix = actualSqlSchemaConfig.getUpdateScriptPrefix();

    // Assert
    assertEquals("2020-03-01", actualUpdateScriptPrefix);
    assertEquals("42", actualSchemaId);
    assertEquals("Create Script Path", actualCreateScriptPath);
    assertNotNull(actualClassLoader);
    assertEquals(1, actualSchemaVersionActual);
    assertEquals(1, actualSchemaVersionObsolete);
    assertSame(classLoader, actualClassLoader);
    assertSame(initialSchemaFiller, actualInitialSchemaFiller);
    assertSame(versionManager, actualSqlSchemaConfig.getVersionManager());
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@link SQLSchemaVersionManager}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SQLSchemaConfig#SQLSchemaConfig(String, String, String, int, int,
   *       SQLSchemaVersionManager, ClassLoader)}
   *   <li>{@link SQLSchemaConfig#setInitialSchemaFiller(SQLInitialSchemaFiller)}
   *   <li>{@link SQLSchemaConfig#getClassLoader()}
   *   <li>{@link SQLSchemaConfig#getCreateScriptPath()}
   *   <li>{@link SQLSchemaConfig#getInitialSchemaFiller()}
   *   <li>{@link SQLSchemaConfig#getSchemaId()}
   *   <li>{@link SQLSchemaConfig#getSchemaVersionActual()}
   *   <li>{@link SQLSchemaConfig#getSchemaVersionObsolete()}
   *   <li>{@link SQLSchemaConfig#getUpdateScriptPrefix()}
   *   <li>{@link SQLSchemaConfig#getVersionManager()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLSchemaConfig.<init>(String, String, String, int, int, SQLSchemaVersionManager, ClassLoader)",
    "void SQLSchemaConfig.<init>(String, String, String, int, int, SQLSchemaVersionManager, ClassLoader, SQLInitialSchemaFiller)",
    "ClassLoader SQLSchemaConfig.getClassLoader()",
    "String SQLSchemaConfig.getCreateScriptPath()",
    "SQLInitialSchemaFiller SQLSchemaConfig.getInitialSchemaFiller()",
    "String SQLSchemaConfig.getSchemaId()",
    "int SQLSchemaConfig.getSchemaVersionActual()",
    "int SQLSchemaConfig.getSchemaVersionObsolete()",
    "String SQLSchemaConfig.getUpdateScriptPrefix()",
    "SQLSchemaVersionManager SQLSchemaConfig.getVersionManager()",
    "void SQLSchemaConfig.setInitialSchemaFiller(SQLInitialSchemaFiller)"
  })
  public void testGettersAndSetters_whenSQLSchemaVersionManager() {
    // Arrange
    SQLSchemaVersionManager versionManager = mock(SQLSchemaVersionManager.class);
    MLet classLoader = new MLet();

    // Act
    SQLSchemaConfig actualSqlSchemaConfig =
        new SQLSchemaConfig(
            "42", "Create Script Path", "2020-03-01", 1, 1, versionManager, classLoader);
    SQLInitialSchemaFiller initialSchemaFiller = mock(SQLInitialSchemaFiller.class);
    actualSqlSchemaConfig.setInitialSchemaFiller(initialSchemaFiller);
    ClassLoader actualClassLoader = actualSqlSchemaConfig.getClassLoader();
    String actualCreateScriptPath = actualSqlSchemaConfig.getCreateScriptPath();
    SQLInitialSchemaFiller actualInitialSchemaFiller =
        actualSqlSchemaConfig.getInitialSchemaFiller();
    String actualSchemaId = actualSqlSchemaConfig.getSchemaId();
    int actualSchemaVersionActual = actualSqlSchemaConfig.getSchemaVersionActual();
    int actualSchemaVersionObsolete = actualSqlSchemaConfig.getSchemaVersionObsolete();
    String actualUpdateScriptPrefix = actualSqlSchemaConfig.getUpdateScriptPrefix();

    // Assert
    assertEquals("2020-03-01", actualUpdateScriptPrefix);
    assertEquals("42", actualSchemaId);
    assertEquals("Create Script Path", actualCreateScriptPath);
    assertNotNull(actualClassLoader);
    assertEquals(1, actualSchemaVersionActual);
    assertEquals(1, actualSchemaVersionObsolete);
    assertSame(classLoader, actualClassLoader);
    assertSame(initialSchemaFiller, actualInitialSchemaFiller);
    assertSame(versionManager, actualSqlSchemaConfig.getVersionManager());
  }
}

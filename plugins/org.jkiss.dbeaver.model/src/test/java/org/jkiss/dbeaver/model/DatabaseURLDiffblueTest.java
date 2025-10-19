package org.jkiss.dbeaver.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import java.util.Set;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DatabaseURL.MetaURL;
import org.jkiss.dbeaver.model.connection.DBPConnectionConfiguration;
import org.jkiss.dbeaver.model.connection.DBPDriver;
import org.jkiss.dbeaver.model.connection.DBPDriverConfigurationType;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DatabaseURLDiffblueTest {
  /**
   * Test {@link DatabaseURL#generateUrlByTemplate(DBPDriver, DBPConnectionConfiguration)} with
   * {@code driver}, {@code connectionInfo}.
   *
   * <p>Method under test: {@link DatabaseURL#generateUrlByTemplate(DBPDriver,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DatabaseURL.generateUrlByTemplate(DBPDriver, DBPConnectionConfiguration)"
  })
  public void testGenerateUrlByTemplateWithDriverConnectionInfo() {
    // Arrange
    DBPDriver driver = mock(DBPDriver.class);
    when(driver.getSampleURL()).thenReturn("https://example.org/example");

    DBPConnectionConfiguration connectionInfo =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());
    connectionInfo.setUrl("");
    connectionInfo.setHostPort("");
    connectionInfo.setHostName("");
    connectionInfo.setServerName("");
    connectionInfo.setDatabaseName("");

    // Act
    String actualGenerateUrlByTemplateResult =
        DatabaseURL.generateUrlByTemplate(driver, connectionInfo);

    // Assert
    verify(driver).getSampleURL();
    assertEquals("https://example.org/example", actualGenerateUrlByTemplateResult);
  }

  /**
   * Test {@link DatabaseURL#generateUrlByTemplate(DBPDriver, DBPConnectionConfiguration)} with
   * {@code driver}, {@code connectionInfo}.
   *
   * <p>Method under test: {@link DatabaseURL#generateUrlByTemplate(DBPDriver,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DatabaseURL.generateUrlByTemplate(DBPDriver, DBPConnectionConfiguration)"
  })
  public void testGenerateUrlByTemplateWithDriverConnectionInfo2() {
    // Arrange
    DBPDriver driver = mock(DBPDriver.class);
    when(driver.getSampleURL()).thenReturn("https://example.org/example");

    DBPConnectionConfiguration connectionInfo =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());
    connectionInfo.setUrl("");
    connectionInfo.setHostPort("");
    connectionInfo.setHostName("");
    connectionInfo.setServerName("");
    connectionInfo.setDatabaseName("https://example.org/example");

    // Act
    String actualGenerateUrlByTemplateResult =
        DatabaseURL.generateUrlByTemplate(driver, connectionInfo);

    // Assert
    verify(driver).getSampleURL();
    assertEquals("https://example.org/example", actualGenerateUrlByTemplateResult);
  }

  /**
   * Test {@link DatabaseURL#generateUrlByTemplate(DBPDriver, DBPConnectionConfiguration)} with
   * {@code driver}, {@code connectionInfo}.
   *
   * <p>Method under test: {@link DatabaseURL#generateUrlByTemplate(DBPDriver,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DatabaseURL.generateUrlByTemplate(DBPDriver, DBPConnectionConfiguration)"
  })
  public void testGenerateUrlByTemplateWithDriverConnectionInfo3() {
    // Arrange
    DBPDriver driver = mock(DBPDriver.class);
    when(driver.getSampleURL()).thenReturn("https://example.org/example");

    DBPConnectionConfiguration connectionInfo =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());
    connectionInfo.setUrl("");
    connectionInfo.setHostPort("");
    connectionInfo.setHostName("");
    connectionInfo.setServerName("https://example.org/example");
    connectionInfo.setDatabaseName("");

    // Act
    String actualGenerateUrlByTemplateResult =
        DatabaseURL.generateUrlByTemplate(driver, connectionInfo);

    // Assert
    verify(driver).getSampleURL();
    assertEquals("https://example.org/example", actualGenerateUrlByTemplateResult);
  }

  /**
   * Test {@link DatabaseURL#generateUrlByTemplate(DBPDriver, DBPConnectionConfiguration)} with
   * {@code driver}, {@code connectionInfo}.
   *
   * <p>Method under test: {@link DatabaseURL#generateUrlByTemplate(DBPDriver,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DatabaseURL.generateUrlByTemplate(DBPDriver, DBPConnectionConfiguration)"
  })
  public void testGenerateUrlByTemplateWithDriverConnectionInfo4() {
    // Arrange
    DBPDriver driver = mock(DBPDriver.class);
    when(driver.getSampleURL()).thenReturn("https://example.org/example");

    DBPConnectionConfiguration connectionInfo = new DBPConnectionConfiguration();
    connectionInfo.setHostName(DBPConnectionConfiguration.VARIABLE_USER);

    // Act
    String actualGenerateUrlByTemplateResult =
        DatabaseURL.generateUrlByTemplate(driver, connectionInfo);

    // Assert
    verify(driver).getSampleURL();
    assertEquals("https://example.org/example", actualGenerateUrlByTemplateResult);
  }

  /**
   * Test {@link DatabaseURL#generateUrlByTemplate(DBPDriver, DBPConnectionConfiguration)} with
   * {@code driver}, {@code connectionInfo}.
   *
   * <p>Method under test: {@link DatabaseURL#generateUrlByTemplate(DBPDriver,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DatabaseURL.generateUrlByTemplate(DBPDriver, DBPConnectionConfiguration)"
  })
  public void testGenerateUrlByTemplateWithDriverConnectionInfo5() {
    // Arrange
    DBPDriver driver = mock(DBPDriver.class);
    when(driver.getSampleURL()).thenReturn("https://example.org/example");

    DBPConnectionConfiguration connectionInfo = new DBPConnectionConfiguration();
    connectionInfo.setHostPort(DBPConnectionConfiguration.VARIABLE_USER);

    // Act
    String actualGenerateUrlByTemplateResult =
        DatabaseURL.generateUrlByTemplate(driver, connectionInfo);

    // Assert
    verify(driver).getSampleURL();
    assertEquals("https://example.org/example", actualGenerateUrlByTemplateResult);
  }

  /**
   * Test {@link DatabaseURL#generateUrlByTemplate(DBPDriver, DBPConnectionConfiguration)} with
   * {@code driver}, {@code connectionInfo}.
   *
   * <p>Method under test: {@link DatabaseURL#generateUrlByTemplate(DBPDriver,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DatabaseURL.generateUrlByTemplate(DBPDriver, DBPConnectionConfiguration)"
  })
  public void testGenerateUrlByTemplateWithDriverConnectionInfo6() {
    // Arrange
    DBPDriver driver = mock(DBPDriver.class);
    when(driver.getSampleURL()).thenReturn("https://example.org/example");

    DBPConnectionConfiguration connectionInfo = new DBPConnectionConfiguration();
    connectionInfo.setUrl("https://example.org/example");

    // Act
    String actualGenerateUrlByTemplateResult =
        DatabaseURL.generateUrlByTemplate(driver, connectionInfo);

    // Assert
    verify(driver).getSampleURL();
    assertEquals("https://example.org/example", actualGenerateUrlByTemplateResult);
  }

  /**
   * Test {@link DatabaseURL#generateUrlByTemplate(DBPDriver, DBPConnectionConfiguration)} with
   * {@code driver}, {@code connectionInfo}.
   *
   * <p>Method under test: {@link DatabaseURL#generateUrlByTemplate(DBPDriver,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DatabaseURL.generateUrlByTemplate(DBPDriver, DBPConnectionConfiguration)"
  })
  public void testGenerateUrlByTemplateWithDriverConnectionInfo7() {
    // Arrange
    DBPDriver driver = mock(DBPDriver.class);
    when(driver.getSampleURL()).thenReturn("https://example.org/example");

    DBPConnectionConfiguration connectionInfo =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());
    connectionInfo.setUrl("https://example.org/example");
    connectionInfo.setHostPort("");
    connectionInfo.setHostName("https://example.org/example");
    connectionInfo.setServerName("");
    connectionInfo.setDatabaseName("");

    // Act
    String actualGenerateUrlByTemplateResult =
        DatabaseURL.generateUrlByTemplate(driver, connectionInfo);

    // Assert
    verify(driver).getSampleURL();
    assertEquals("https://example.org/example", actualGenerateUrlByTemplateResult);
  }

  /**
   * Test {@link DatabaseURL#generateUrlByTemplate(DBPDriver, DBPConnectionConfiguration)} with
   * {@code driver}, {@code connectionInfo}.
   *
   * <ul>
   *   <li>Given {@code janedoe}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseURL#generateUrlByTemplate(DBPDriver,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DatabaseURL.generateUrlByTemplate(DBPDriver, DBPConnectionConfiguration)"
  })
  public void testGenerateUrlByTemplateWithDriverConnectionInfo_givenJanedoe() {
    // Arrange
    DBPDriver driver = mock(DBPDriver.class);
    when(driver.getSampleURL()).thenReturn("https://example.org/example");

    DBPConnectionConfiguration connectionInfo = new DBPConnectionConfiguration();
    connectionInfo.setUserName("janedoe");

    // Act
    String actualGenerateUrlByTemplateResult =
        DatabaseURL.generateUrlByTemplate(driver, connectionInfo);

    // Assert
    verify(driver).getSampleURL();
    assertEquals("https://example.org/example", actualGenerateUrlByTemplateResult);
  }

  /**
   * Test {@link DatabaseURL#generateUrlByTemplate(DBPDriver, DBPConnectionConfiguration)} with
   * {@code driver}, {@code connectionInfo}.
   *
   * <ul>
   *   <li>Given {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseURL#generateUrlByTemplate(DBPDriver,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DatabaseURL.generateUrlByTemplate(DBPDriver, DBPConnectionConfiguration)"
  })
  public void testGenerateUrlByTemplateWithDriverConnectionInfo_givenNull() {
    // Arrange
    DBPDriver driver = mock(DBPDriver.class);
    when(driver.getSampleURL()).thenReturn("https://example.org/example");

    DBPConnectionConfiguration connectionInfo =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());
    connectionInfo.setUrl("https://example.org/example");
    connectionInfo.setHostPort("");
    connectionInfo.setHostName(null);
    connectionInfo.setServerName("");
    connectionInfo.setDatabaseName("https://example.org/example");

    // Act
    String actualGenerateUrlByTemplateResult =
        DatabaseURL.generateUrlByTemplate(driver, connectionInfo);

    // Assert
    verify(driver).getSampleURL();
    assertEquals("https://example.org/example", actualGenerateUrlByTemplateResult);
  }

  /**
   * Test {@link DatabaseURL#generateUrlByTemplate(DBPDriver, DBPConnectionConfiguration)} with
   * {@code driver}, {@code connectionInfo}.
   *
   * <ul>
   *   <li>Given {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseURL#generateUrlByTemplate(DBPDriver,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DatabaseURL.generateUrlByTemplate(DBPDriver, DBPConnectionConfiguration)"
  })
  public void testGenerateUrlByTemplateWithDriverConnectionInfo_givenNull2() {
    // Arrange
    DBPDriver driver = mock(DBPDriver.class);
    when(driver.getSampleURL()).thenReturn("https://example.org/example");

    DBPConnectionConfiguration connectionInfo =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());
    connectionInfo.setUrl("https://example.org/example");
    connectionInfo.setHostPort("");
    connectionInfo.setHostName(null);
    connectionInfo.setServerName("https://example.org/example");
    connectionInfo.setDatabaseName("");

    // Act
    String actualGenerateUrlByTemplateResult =
        DatabaseURL.generateUrlByTemplate(driver, connectionInfo);

    // Assert
    verify(driver).getSampleURL();
    assertEquals("https://example.org/example", actualGenerateUrlByTemplateResult);
  }

  /**
   * Test {@link DatabaseURL#generateUrlByTemplate(DBPDriver, DBPConnectionConfiguration)} with
   * {@code driver}, {@code connectionInfo}.
   *
   * <ul>
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseURL#generateUrlByTemplate(DBPDriver,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DatabaseURL.generateUrlByTemplate(DBPDriver, DBPConnectionConfiguration)"
  })
  public void testGenerateUrlByTemplateWithDriverConnectionInfo_thenReturnEmptyString() {
    // Arrange
    DBPDriver driver = mock(DBPDriver.class);
    when(driver.getSampleURL()).thenReturn("[");

    // Act
    String actualGenerateUrlByTemplateResult =
        DatabaseURL.generateUrlByTemplate(driver, new DBPConnectionConfiguration());

    // Assert
    verify(driver).getSampleURL();
    assertEquals("", actualGenerateUrlByTemplateResult);
  }

  /**
   * Test {@link DatabaseURL#generateUrlByTemplate(DBPDriver, DBPConnectionConfiguration)} with
   * {@code driver}, {@code connectionInfo}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseURL#generateUrlByTemplate(DBPDriver,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DatabaseURL.generateUrlByTemplate(DBPDriver, DBPConnectionConfiguration)"
  })
  public void testGenerateUrlByTemplateWithDriverConnectionInfo_thenReturnNull() {
    // Arrange
    DBPDriver driver = mock(DBPDriver.class);
    when(driver.getSampleURL()).thenReturn("");

    // Act
    String actualGenerateUrlByTemplateResult =
        DatabaseURL.generateUrlByTemplate(driver, new DBPConnectionConfiguration());

    // Assert
    verify(driver).getSampleURL();
    assertNull(actualGenerateUrlByTemplateResult);
  }

  /**
   * Test {@link DatabaseURL#generateUrlByTemplate(DBPDriver, DBPConnectionConfiguration)} with
   * {@code driver}, {@code connectionInfo}.
   *
   * <ul>
   *   <li>When {@link DBPConnectionConfiguration#DBPConnectionConfiguration()}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseURL#generateUrlByTemplate(DBPDriver,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DatabaseURL.generateUrlByTemplate(DBPDriver, DBPConnectionConfiguration)"
  })
  public void testGenerateUrlByTemplateWithDriverConnectionInfo_whenDBPConnectionConfiguration() {
    // Arrange
    DBPDriver driver = mock(DBPDriver.class);
    when(driver.getSampleURL()).thenReturn("https://example.org/example");

    // Act
    String actualGenerateUrlByTemplateResult =
        DatabaseURL.generateUrlByTemplate(driver, new DBPConnectionConfiguration());

    // Assert
    verify(driver).getSampleURL();
    assertEquals("https://example.org/example", actualGenerateUrlByTemplateResult);
  }

  /**
   * Test {@link DatabaseURL#generateUrlByTemplate(String, DBPConnectionConfiguration)} with {@code
   * urlTemplate}, {@code connectionInfo}.
   *
   * <p>Method under test: {@link DatabaseURL#generateUrlByTemplate(String,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DatabaseURL.generateUrlByTemplate(String, DBPConnectionConfiguration)"
  })
  public void testGenerateUrlByTemplateWithUrlTemplateConnectionInfo() {
    // Arrange, Act and Assert
    assertEquals(
        "https://example.org/example",
        DatabaseURL.generateUrlByTemplate(
            "https://example.org/example", new DBPConnectionConfiguration()));
  }

  /**
   * Test {@link DatabaseURL#generateUrlByTemplate(String, DBPConnectionConfiguration)} with {@code
   * urlTemplate}, {@code connectionInfo}.
   *
   * <p>Method under test: {@link DatabaseURL#generateUrlByTemplate(String,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DatabaseURL.generateUrlByTemplate(String, DBPConnectionConfiguration)"
  })
  public void testGenerateUrlByTemplateWithUrlTemplateConnectionInfo2() {
    // Arrange
    DBPConnectionConfiguration connectionInfo =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());
    connectionInfo.setUrl("");
    connectionInfo.setHostPort("");
    connectionInfo.setHostName("");
    connectionInfo.setServerName("");
    connectionInfo.setDatabaseName("");

    // Act
    String actualGenerateUrlByTemplateResult =
        DatabaseURL.generateUrlByTemplate("https://example.org/example", connectionInfo);

    // Assert
    assertEquals("https://example.org/example", actualGenerateUrlByTemplateResult);
  }

  /**
   * Test {@link DatabaseURL#generateUrlByTemplate(String, DBPConnectionConfiguration)} with {@code
   * urlTemplate}, {@code connectionInfo}.
   *
   * <p>Method under test: {@link DatabaseURL#generateUrlByTemplate(String,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DatabaseURL.generateUrlByTemplate(String, DBPConnectionConfiguration)"
  })
  public void testGenerateUrlByTemplateWithUrlTemplateConnectionInfo3() {
    // Arrange
    DBPConnectionConfiguration connectionInfo =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());
    connectionInfo.setUrl("");
    connectionInfo.setHostPort("");
    connectionInfo.setHostName("");
    connectionInfo.setServerName("");
    connectionInfo.setDatabaseName("https://example.org/example");

    // Act
    String actualGenerateUrlByTemplateResult =
        DatabaseURL.generateUrlByTemplate("https://example.org/example", connectionInfo);

    // Assert
    assertEquals("https://example.org/example", actualGenerateUrlByTemplateResult);
  }

  /**
   * Test {@link DatabaseURL#generateUrlByTemplate(String, DBPConnectionConfiguration)} with {@code
   * urlTemplate}, {@code connectionInfo}.
   *
   * <p>Method under test: {@link DatabaseURL#generateUrlByTemplate(String,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DatabaseURL.generateUrlByTemplate(String, DBPConnectionConfiguration)"
  })
  public void testGenerateUrlByTemplateWithUrlTemplateConnectionInfo4() {
    // Arrange
    DBPConnectionConfiguration connectionInfo =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());
    connectionInfo.setUrl("");
    connectionInfo.setHostPort("");
    connectionInfo.setHostName("");
    connectionInfo.setServerName("https://example.org/example");
    connectionInfo.setDatabaseName("");

    // Act
    String actualGenerateUrlByTemplateResult =
        DatabaseURL.generateUrlByTemplate("https://example.org/example", connectionInfo);

    // Assert
    assertEquals("https://example.org/example", actualGenerateUrlByTemplateResult);
  }

  /**
   * Test {@link DatabaseURL#generateUrlByTemplate(String, DBPConnectionConfiguration)} with {@code
   * urlTemplate}, {@code connectionInfo}.
   *
   * <p>Method under test: {@link DatabaseURL#generateUrlByTemplate(String,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DatabaseURL.generateUrlByTemplate(String, DBPConnectionConfiguration)"
  })
  public void testGenerateUrlByTemplateWithUrlTemplateConnectionInfo5() {
    // Arrange
    DBPConnectionConfiguration connectionInfo =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());
    connectionInfo.setUrl("");
    connectionInfo.setHostPort("");
    connectionInfo.setHostName("https://example.org/example");
    connectionInfo.setServerName("");
    connectionInfo.setDatabaseName("");

    // Act
    String actualGenerateUrlByTemplateResult =
        DatabaseURL.generateUrlByTemplate("https://example.org/example", connectionInfo);

    // Assert
    assertEquals("https://example.org/example", actualGenerateUrlByTemplateResult);
  }

  /**
   * Test {@link DatabaseURL#generateUrlByTemplate(String, DBPConnectionConfiguration)} with {@code
   * urlTemplate}, {@code connectionInfo}.
   *
   * <p>Method under test: {@link DatabaseURL#generateUrlByTemplate(String,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DatabaseURL.generateUrlByTemplate(String, DBPConnectionConfiguration)"
  })
  public void testGenerateUrlByTemplateWithUrlTemplateConnectionInfo6() {
    // Arrange
    DBPConnectionConfiguration connectionInfo = new DBPConnectionConfiguration();
    connectionInfo.setUrl("https://example.org/example");

    // Act and Assert
    assertEquals(
        "https://example.org/example",
        DatabaseURL.generateUrlByTemplate("https://example.org/example", connectionInfo));
  }

  /**
   * Test {@link DatabaseURL#generateUrlByTemplate(String, DBPConnectionConfiguration)} with {@code
   * urlTemplate}, {@code connectionInfo}.
   *
   * <p>Method under test: {@link DatabaseURL#generateUrlByTemplate(String,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DatabaseURL.generateUrlByTemplate(String, DBPConnectionConfiguration)"
  })
  public void testGenerateUrlByTemplateWithUrlTemplateConnectionInfo7() {
    // Arrange
    DBPConnectionConfiguration connectionInfo =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());
    connectionInfo.setUrl("https://example.org/example");
    connectionInfo.setHostPort("");
    connectionInfo.setHostName("");
    connectionInfo.setServerName("");
    connectionInfo.setDatabaseName("https://example.org/example");

    // Act
    String actualGenerateUrlByTemplateResult =
        DatabaseURL.generateUrlByTemplate("https://example.org/example", connectionInfo);

    // Assert
    assertEquals("https://example.org/example", actualGenerateUrlByTemplateResult);
  }

  /**
   * Test {@link DatabaseURL#generateUrlByTemplate(String, DBPConnectionConfiguration)} with {@code
   * urlTemplate}, {@code connectionInfo}.
   *
   * <p>Method under test: {@link DatabaseURL#generateUrlByTemplate(String,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DatabaseURL.generateUrlByTemplate(String, DBPConnectionConfiguration)"
  })
  public void testGenerateUrlByTemplateWithUrlTemplateConnectionInfo8() {
    // Arrange
    DBPConnectionConfiguration connectionInfo =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());
    connectionInfo.setUrl("https://example.org/example");
    connectionInfo.setHostPort(DBPConnectionConfiguration.VARIABLE_DATABASE);
    connectionInfo.setHostName("");
    connectionInfo.setServerName("");
    connectionInfo.setDatabaseName("https://example.org/example");

    // Act
    String actualGenerateUrlByTemplateResult =
        DatabaseURL.generateUrlByTemplate("https://example.org/example", connectionInfo);

    // Assert
    assertEquals("https://example.org/example", actualGenerateUrlByTemplateResult);
  }

  /**
   * Test {@link DatabaseURL#generateUrlByTemplate(String, DBPConnectionConfiguration)} with {@code
   * urlTemplate}, {@code connectionInfo}.
   *
   * <p>Method under test: {@link DatabaseURL#generateUrlByTemplate(String,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DatabaseURL.generateUrlByTemplate(String, DBPConnectionConfiguration)"
  })
  public void testGenerateUrlByTemplateWithUrlTemplateConnectionInfo9() {
    // Arrange
    DBPConnectionConfiguration connectionInfo =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());
    connectionInfo.setUrl("https://example.org/example");
    connectionInfo.setHostPort("");
    connectionInfo.setHostName(DBPConnectionConfiguration.VARIABLE_DATABASE);
    connectionInfo.setServerName("");
    connectionInfo.setDatabaseName("https://example.org/example");

    // Act
    String actualGenerateUrlByTemplateResult =
        DatabaseURL.generateUrlByTemplate("https://example.org/example", connectionInfo);

    // Assert
    assertEquals("https://example.org/example", actualGenerateUrlByTemplateResult);
  }

  /**
   * Test {@link DatabaseURL#generateUrlByTemplate(String, DBPConnectionConfiguration)} with {@code
   * urlTemplate}, {@code connectionInfo}.
   *
   * <p>Method under test: {@link DatabaseURL#generateUrlByTemplate(String,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DatabaseURL.generateUrlByTemplate(String, DBPConnectionConfiguration)"
  })
  public void testGenerateUrlByTemplateWithUrlTemplateConnectionInfo10() {
    // Arrange
    DBPConnectionConfiguration connectionInfo =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());
    connectionInfo.setUrl("https://example.org/example");
    connectionInfo.setHostPort("");
    connectionInfo.setHostName("");
    connectionInfo.setServerName(DBPConnectionConfiguration.VARIABLE_DATABASE);
    connectionInfo.setDatabaseName("https://example.org/example");

    // Act
    String actualGenerateUrlByTemplateResult =
        DatabaseURL.generateUrlByTemplate("https://example.org/example", connectionInfo);

    // Assert
    assertEquals("https://example.org/example", actualGenerateUrlByTemplateResult);
  }

  /**
   * Test {@link DatabaseURL#generateUrlByTemplate(String, DBPConnectionConfiguration)} with {@code
   * urlTemplate}, {@code connectionInfo}.
   *
   * <p>Method under test: {@link DatabaseURL#generateUrlByTemplate(String,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DatabaseURL.generateUrlByTemplate(String, DBPConnectionConfiguration)"
  })
  public void testGenerateUrlByTemplateWithUrlTemplateConnectionInfo11() {
    // Arrange, Act and Assert
    assertEquals(
        "\\{(.*?)}",
        DatabaseURL.generateUrlByTemplate("\\{(.*?)}", new DBPConnectionConfiguration()));
  }

  /**
   * Test {@link DatabaseURL#generateUrlByTemplate(String, DBPConnectionConfiguration)} with {@code
   * urlTemplate}, {@code connectionInfo}.
   *
   * <ul>
   *   <li>Given {@code janedoe}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseURL#generateUrlByTemplate(String,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DatabaseURL.generateUrlByTemplate(String, DBPConnectionConfiguration)"
  })
  public void testGenerateUrlByTemplateWithUrlTemplateConnectionInfo_givenJanedoe() {
    // Arrange
    DBPConnectionConfiguration connectionInfo = new DBPConnectionConfiguration();
    connectionInfo.setUserName("janedoe");

    // Act and Assert
    assertEquals(
        "https://example.org/example",
        DatabaseURL.generateUrlByTemplate("https://example.org/example", connectionInfo));
  }

  /**
   * Test {@link DatabaseURL#generateUrlByTemplate(String, DBPConnectionConfiguration)} with {@code
   * urlTemplate}, {@code connectionInfo}.
   *
   * <ul>
   *   <li>Given {@link DBPConnectionConfiguration#VARIABLE_USER}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseURL#generateUrlByTemplate(String,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DatabaseURL.generateUrlByTemplate(String, DBPConnectionConfiguration)"
  })
  public void testGenerateUrlByTemplateWithUrlTemplateConnectionInfo_givenVariable_user() {
    // Arrange
    DBPConnectionConfiguration connectionInfo = new DBPConnectionConfiguration();
    connectionInfo.setHostPort(DBPConnectionConfiguration.VARIABLE_USER);

    // Act and Assert
    assertEquals(
        "https://example.org/example",
        DatabaseURL.generateUrlByTemplate("https://example.org/example", connectionInfo));
  }

  /**
   * Test {@link DatabaseURL#generateUrlByTemplate(String, DBPConnectionConfiguration)} with {@code
   * urlTemplate}, {@code connectionInfo}.
   *
   * <ul>
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseURL#generateUrlByTemplate(String,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DatabaseURL.generateUrlByTemplate(String, DBPConnectionConfiguration)"
  })
  public void testGenerateUrlByTemplateWithUrlTemplateConnectionInfo_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", DatabaseURL.generateUrlByTemplate("[", new DBPConnectionConfiguration()));
  }

  /**
   * Test {@link DatabaseURL#generateUrlByTemplate(String, DBPConnectionConfiguration)} with {@code
   * urlTemplate}, {@code connectionInfo}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseURL#generateUrlByTemplate(String,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DatabaseURL.generateUrlByTemplate(String, DBPConnectionConfiguration)"
  })
  public void testGenerateUrlByTemplateWithUrlTemplateConnectionInfo_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(DatabaseURL.generateUrlByTemplate("", new DBPConnectionConfiguration()));
  }

  /**
   * Test MetaURL getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link MetaURL}
   *   <li>{@link MetaURL#getAvailableProperties()}
   *   <li>{@link MetaURL#getRequiredProperties()}
   *   <li>{@link MetaURL#getUrlComponents()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MetaURL.<init>()",
    "Set MetaURL.getAvailableProperties()",
    "Set MetaURL.getRequiredProperties()",
    "List MetaURL.getUrlComponents()"
  })
  public void testMetaURLGettersAndSetters() {
    // Arrange and Act
    MetaURL actualMetaURL = new MetaURL();
    Set<String> actualAvailableProperties = actualMetaURL.getAvailableProperties();
    Set<String> actualRequiredProperties = actualMetaURL.getRequiredProperties();

    // Assert
    assertTrue(actualMetaURL.getUrlComponents().isEmpty());
    assertTrue(actualAvailableProperties.isEmpty());
    assertTrue(actualRequiredProperties.isEmpty());
  }

  /**
   * Test {@link DatabaseURL#parseSampleURL(String)}.
   *
   * <ul>
   *   <li>Then return UrlComponents first is {@code \}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseURL#parseSampleURL(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"MetaURL DatabaseURL.parseSampleURL(String)"})
  public void testParseSampleURL_thenReturnUrlComponentsFirstIsBackslash() throws DBException {
    // Arrange and Act
    MetaURL actualParseSampleURLResult = DatabaseURL.parseSampleURL("\\{(.*?)}");

    // Assert
    List<String> urlComponents = actualParseSampleURLResult.getUrlComponents();
    assertEquals(2, urlComponents.size());
    assertEquals("\\", urlComponents.get(0));
    assertEquals("{(.*?)}", urlComponents.get(1));
    Set<String> availableProperties = actualParseSampleURLResult.getAvailableProperties();
    assertEquals(1, availableProperties.size());
    assertTrue(availableProperties.contains("(.*?)"));
    assertEquals(availableProperties, actualParseSampleURLResult.getRequiredProperties());
  }

  /**
   * Test {@link DatabaseURL#parseSampleURL(String)}.
   *
   * <ul>
   *   <li>Then return UrlComponents first is {@code [\}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseURL#parseSampleURL(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"MetaURL DatabaseURL.parseSampleURL(String)"})
  public void testParseSampleURL_thenReturnUrlComponentsFirstIsLeftSquareBracketBackslash()
      throws DBException {
    // Arrange and Act
    MetaURL actualParseSampleURLResult = DatabaseURL.parseSampleURL("[\\{(.*?)}");

    // Assert
    List<String> urlComponents = actualParseSampleURLResult.getUrlComponents();
    assertEquals(2, urlComponents.size());
    assertEquals("[\\", urlComponents.get(0));
    assertEquals("{(.*?)}", urlComponents.get(1));
    Set<String> availableProperties = actualParseSampleURLResult.getAvailableProperties();
    assertEquals(1, availableProperties.size());
    assertTrue(availableProperties.contains("(.*?)"));
    assertEquals(availableProperties, actualParseSampleURLResult.getRequiredProperties());
  }

  /**
   * Test {@link DatabaseURL#parseSampleURL(String)}.
   *
   * <ul>
   *   <li>When {@code https://example.org/example}.
   *   <li>Then return UrlComponents size is one.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseURL#parseSampleURL(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"MetaURL DatabaseURL.parseSampleURL(String)"})
  public void testParseSampleURL_whenHttpsExampleOrgExample_thenReturnUrlComponentsSizeIsOne()
      throws DBException {
    // Arrange and Act
    MetaURL actualParseSampleURLResult = DatabaseURL.parseSampleURL("https://example.org/example");

    // Assert
    List<String> urlComponents = actualParseSampleURLResult.getUrlComponents();
    assertEquals(1, urlComponents.size());
    assertEquals("https://example.org/example", urlComponents.get(0));
    assertTrue(actualParseSampleURLResult.getAvailableProperties().isEmpty());
    assertTrue(actualParseSampleURLResult.getRequiredProperties().isEmpty());
  }

  /**
   * Test {@link DatabaseURL#getPattern(String)}.
   *
   * <ul>
   *   <li>Then return pattern is {@code ^\Qhttps://example.org/example\E}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseURL#getPattern(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.regex.Pattern DatabaseURL.getPattern(String)"})
  public void testGetPattern_thenReturnPatternIsQhttpsExampleOrgExampleE() {
    // Arrange, Act and Assert
    assertEquals(
        "^\\Qhttps://example.org/example\\E",
        DatabaseURL.getPattern("https://example.org/example").pattern());
  }

  /**
   * Test {@link DatabaseURL#getPattern(String)}.
   *
   * <ul>
   *   <li>When {@code [UU]}.
   *   <li>Then return pattern is {@code ^\Q\E(?:\QUU\E)?\Q\E}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseURL#getPattern(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.regex.Pattern DatabaseURL.getPattern(String)"})
  public void testGetPattern_whenUu_thenReturnPatternIsQEQuuEQE() {
    // Arrange, Act and Assert
    assertEquals("^\\Q\\E(?:\\QUU\\E)?\\Q\\E", DatabaseURL.getPattern("[UU]").pattern());
  }

  /**
   * Test {@link DatabaseURL#getPattern(String)}.
   *
   * <ul>
   *   <li>When {@code {UU}}.
   *   <li>Then return pattern is {@code ^\Q\E(?<\QUU\E>[\w\-_.~]+)\Q\E}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseURL#getPattern(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.regex.Pattern DatabaseURL.getPattern(String)"})
  public void testGetPattern_whenUu_thenReturnPatternIsQEQuuEWQE() {
    // Arrange, Act and Assert
    assertEquals(
        "^\\Q\\E(?<\\QUU\\E>[\\w\\-_.~]+)\\Q\\E", DatabaseURL.getPattern("{UU}").pattern());
  }

  /**
   * Test {@link DatabaseURL#extractConfigurationFromUrl(String, String)}.
   *
   * <p>Method under test: {@link DatabaseURL#extractConfigurationFromUrl(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBPConnectionConfiguration DatabaseURL.extractConfigurationFromUrl(String, String)"
  })
  public void testExtractConfigurationFromUrl() {
    // Arrange, Act and Assert
    assertNull(DatabaseURL.extractConfigurationFromUrl("\\[(.*?)]", "https://example.org/example"));
  }

  /**
   * Test {@link DatabaseURL#extractConfigurationFromUrl(String, String)}.
   *
   * <ul>
   *   <li>When {@code https://example.org/example}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseURL#extractConfigurationFromUrl(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBPConnectionConfiguration DatabaseURL.extractConfigurationFromUrl(String, String)"
  })
  public void testExtractConfigurationFromUrl_whenHttpsExampleOrgExample_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(
        DatabaseURL.extractConfigurationFromUrl(
            "https://example.org/example", "https://example.org/example"));
  }

  /**
   * Test {@link DatabaseURL#extractConfigurationFromUrl(String, String)}.
   *
   * <ul>
   *   <li>When {@code {UU}}.
   *   <li>Then return AuthModelId is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseURL#extractConfigurationFromUrl(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBPConnectionConfiguration DatabaseURL.extractConfigurationFromUrl(String, String)"
  })
  public void testExtractConfigurationFromUrl_whenUu_thenReturnAuthModelIdIsNull() {
    // Arrange and Act
    DBPConnectionConfiguration actualExtractConfigurationFromUrlResult =
        DatabaseURL.extractConfigurationFromUrl("{UU}", "https://example.org/example");

    // Assert
    assertNull(actualExtractConfigurationFromUrlResult.getAuthModelId());
    assertNull(actualExtractConfigurationFromUrlResult.getClientHomeId());
    assertNull(actualExtractConfigurationFromUrlResult.getConfigProfileName());
    assertNull(actualExtractConfigurationFromUrlResult.getConfigProfileSource());
    assertNull(actualExtractConfigurationFromUrlResult.getConnectionColor());
    assertNull(actualExtractConfigurationFromUrlResult.getDatabaseName());
    assertNull(actualExtractConfigurationFromUrlResult.getHostName());
    assertNull(actualExtractConfigurationFromUrlResult.getHostPort());
    assertNull(actualExtractConfigurationFromUrlResult.getServerName());
    assertNull(actualExtractConfigurationFromUrlResult.getUrl());
    assertNull(actualExtractConfigurationFromUrlResult.getUserName());
    assertNull(actualExtractConfigurationFromUrlResult.getUserPassword());
    assertNull(actualExtractConfigurationFromUrlResult.getAuthProperties());
    assertEquals(0, actualExtractConfigurationFromUrlResult.getCloseIdleInterval());
    assertEquals(0, actualExtractConfigurationFromUrlResult.getKeepAliveInterval());
    assertEquals(0, actualExtractConfigurationFromUrlResult.getDeclaredEvents().length);
    assertEquals(
        DBPDriverConfigurationType.MANUAL,
        actualExtractConfigurationFromUrlResult.getConfigurationType());
    assertTrue(actualExtractConfigurationFromUrlResult.getHandlers().isEmpty());
    assertTrue(actualExtractConfigurationFromUrlResult.getProperties().isEmpty());
    assertTrue(actualExtractConfigurationFromUrlResult.getProviderProperties().isEmpty());
    assertTrue(actualExtractConfigurationFromUrlResult.getRuntimeAttribute().isEmpty());
    assertTrue(actualExtractConfigurationFromUrlResult.isCloseIdleConnection());
  }

  /**
   * Test {@link DatabaseURL#extractConfigurationFromUrl(String, String)}.
   *
   * <ul>
   *   <li>When {@code [UU]}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseURL#extractConfigurationFromUrl(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBPConnectionConfiguration DatabaseURL.extractConfigurationFromUrl(String, String)"
  })
  public void testExtractConfigurationFromUrl_whenUu_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(DatabaseURL.extractConfigurationFromUrl("[UU]", "https://example.org/example"));
  }
}

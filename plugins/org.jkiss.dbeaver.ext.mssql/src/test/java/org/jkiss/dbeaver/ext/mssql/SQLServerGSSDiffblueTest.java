package org.jkiss.dbeaver.ext.mssql;

import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Properties;
import org.jkiss.dbeaver.model.connection.DBPConnectionConfiguration;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLServerGSSDiffblueTest {
  /**
   * Test {@link SQLServerGSS#initCredentials(DBPConnectionConfiguration, Properties)}.
   *
   * <ul>
   *   <li>Given {@code Connection Info}.
   *   <li>Then throw {@link DBCException}.
   * </ul>
   *
   * <p>Method under test: {@link SQLServerGSS#initCredentials(DBPConnectionConfiguration,
   * Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLServerGSS.initCredentials(DBPConnectionConfiguration, Properties)"})
  public void testInitCredentials_givenConnectionInfo_thenThrowDBCException() throws DBCException {
    // Arrange
    DBPConnectionConfiguration connectionInfo =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());
    connectionInfo.setUserName("Connection Info");

    Properties properties = new Properties();
    properties.put("gsscredential", "Properties");

    // Act and Assert
    assertThrows(
        DBCException.class, () -> SQLServerGSS.initCredentials(connectionInfo, properties));
  }

  /**
   * Test {@link SQLServerGSS#initCredentials(DBPConnectionConfiguration, Properties)}.
   *
   * <ul>
   *   <li>Given empty string.
   * </ul>
   *
   * <p>Method under test: {@link SQLServerGSS#initCredentials(DBPConnectionConfiguration,
   * Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLServerGSS.initCredentials(DBPConnectionConfiguration, Properties)"})
  public void testInitCredentials_givenEmptyString() throws DBCException {
    // Arrange
    DBPConnectionConfiguration connectionInfo =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());
    connectionInfo.setUserName("");

    Properties properties = new Properties();
    properties.put("gsscredential", "Properties");

    // Act and Assert
    SQLServerGSS.initCredentials(connectionInfo, properties);
  }

  /**
   * Test {@link SQLServerGSS#initCredentials(DBPConnectionConfiguration, Properties)}.
   *
   * <ul>
   *   <li>When {@link DBPConnectionConfiguration#DBPConnectionConfiguration()}.
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link SQLServerGSS#initCredentials(DBPConnectionConfiguration,
   * Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLServerGSS.initCredentials(DBPConnectionConfiguration, Properties)"})
  public void testInitCredentials_whenDBPConnectionConfiguration_thenDoesNotThrow()
      throws DBCException {
    // Arrange
    DBPConnectionConfiguration connectionInfo = new DBPConnectionConfiguration();

    // Act and Assert
    SQLServerGSS.initCredentials(connectionInfo, new Properties());
  }
}

package org.jkiss.dbeaver.registry.rm;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSourceConfigurationStorage;
import org.jkiss.dbeaver.model.app.DBPProject;
import org.jkiss.dbeaver.model.app.DBPWorkspace;
import org.jkiss.dbeaver.model.auth.SMSessionContext;
import org.jkiss.dbeaver.model.impl.auth.SessionContextImpl;
import org.jkiss.dbeaver.model.rm.RMController;
import org.jkiss.dbeaver.registry.project.LocalProjectImpl;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class DataSourceConfigurationManagerRMDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DataSourceConfigurationManagerRM#DataSourceConfigurationManagerRM(DBPProject,
   *       RMController)}
   *   <li>{@link DataSourceConfigurationManagerRM#isReadOnly()}
   *   <li>{@link DataSourceConfigurationManagerRM#isSecure()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DataSourceConfigurationManagerRM.<init>(DBPProject, RMController)",
    "boolean DataSourceConfigurationManagerRM.isReadOnly()",
    "boolean DataSourceConfigurationManagerRM.isSecure()"
  })
  public void testGettersAndSetters() {
    // Arrange
    DBPWorkspace workspace = mock(DBPWorkspace.class);
    Path projectPath = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    LocalProjectImpl project =
        new LocalProjectImpl(workspace, new SessionContextImpl(null), projectPath);

    // Act
    DataSourceConfigurationManagerRM actualDataSourceConfigurationManagerRM =
        new DataSourceConfigurationManagerRM(project, mock(RMController.class));
    boolean actualIsReadOnlyResult = actualDataSourceConfigurationManagerRM.isReadOnly();

    // Assert
    assertFalse(actualIsReadOnlyResult);
    assertTrue(actualDataSourceConfigurationManagerRM.isSecure());
  }

  /**
   * Test {@link DataSourceConfigurationManagerRM#getConfigurationStorages()}.
   *
   * <p>Method under test: {@link DataSourceConfigurationManagerRM#getConfigurationStorages()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DataSourceConfigurationManagerRM.getConfigurationStorages()"})
  public void testGetConfigurationStorages() {
    // Arrange
    DBPWorkspace workspace = mock(DBPWorkspace.class);
    SessionContextImpl sessionContext = new SessionContextImpl(mock(SMSessionContext.class));
    Path projectPath = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    LocalProjectImpl project = new LocalProjectImpl(workspace, sessionContext, projectPath);
    DataSourceConfigurationManagerRM dataSourceConfigurationManagerRM =
        new DataSourceConfigurationManagerRM(project, mock(RMController.class));

    // Act
    List<DBPDataSourceConfigurationStorage> actualConfigurationStorages =
        dataSourceConfigurationManagerRM.getConfigurationStorages();

    // Assert
    assertEquals(1, actualConfigurationStorages.size());
    DBPDataSourceConfigurationStorage getResult = actualConfigurationStorages.get(0);
    assertTrue(getResult instanceof DataSourceRMStorage);
    assertNull(getResult.getStatus());
    assertNull(getResult.getStorageSubId());
    assertTrue(getResult.isDefault());
    assertTrue(getResult.isValid());
    assertTrue(getResult.isVirtual());
  }

  /**
   * Test {@link DataSourceConfigurationManagerRM#readConfiguration(String, Collection)}.
   *
   * <p>Method under test: {@link DataSourceConfigurationManagerRM#readConfiguration(String,
   * Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "InputStream DataSourceConfigurationManagerRM.readConfiguration(String, Collection)"
  })
  public void testReadConfiguration() throws IOException, DBException {
    // Arrange
    DBPWorkspace workspace = mock(DBPWorkspace.class);
    SessionContextImpl sessionContext = new SessionContextImpl(mock(SMSessionContext.class));
    Path projectPath = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    LocalProjectImpl project = new LocalProjectImpl(workspace, sessionContext, projectPath);
    project.setInMemory(true);
    DataSourceConfigurationManagerRM dataSourceConfigurationManagerRM =
        new DataSourceConfigurationManagerRM(project, mock(RMController.class));

    // Act and Assert
    assertThrows(
        DBException.class,
        () -> dataSourceConfigurationManagerRM.readConfiguration("Name", new ArrayList<>()));
  }

  /**
   * Test {@link DataSourceConfigurationManagerRM#readConfiguration(String, Collection)}.
   *
   * <p>Method under test: {@link DataSourceConfigurationManagerRM#readConfiguration(String,
   * Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "InputStream DataSourceConfigurationManagerRM.readConfiguration(String, Collection)"
  })
  public void testReadConfiguration2() throws IOException, DBException {
    // Arrange
    DBPWorkspace workspace = mock(DBPWorkspace.class);
    SessionContextImpl sessionContext = new SessionContextImpl(mock(SMSessionContext.class));
    Path projectPath = Paths.get(System.getProperty("java.io.tmpdir"), "Name");

    LocalProjectImpl project = new LocalProjectImpl(workspace, sessionContext, projectPath);
    project.setInMemory(true);

    RMController client = mock(RMController.class);
    when(client.getProjectsDataSources(Mockito.<String>any(), Mockito.<String[]>any()))
        .thenThrow(new DBException("An error occurred"));

    DataSourceConfigurationManagerRM dataSourceConfigurationManagerRM =
        new DataSourceConfigurationManagerRM(project, client);

    // Act and Assert
    assertThrows(
        DBException.class,
        () -> dataSourceConfigurationManagerRM.readConfiguration("Name", new ArrayList<>()));
    verify(client).getProjectsDataSources(eq("Name"), isA(String[].class));
  }

  /**
   * Test {@link DataSourceConfigurationManagerRM#readConfiguration(String, Collection)}.
   *
   * <ul>
   *   <li>Given {@link RMController} {@link RMController#getProjectsDataSources(String, String[])}
   *       return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DataSourceConfigurationManagerRM#readConfiguration(String,
   * Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "InputStream DataSourceConfigurationManagerRM.readConfiguration(String, Collection)"
  })
  public void testReadConfiguration_givenRMControllerGetProjectsDataSourcesReturnNull()
      throws IOException, DBException {
    // Arrange
    DBPWorkspace workspace = mock(DBPWorkspace.class);
    SessionContextImpl sessionContext = new SessionContextImpl(mock(SMSessionContext.class));
    Path projectPath = Paths.get(System.getProperty("java.io.tmpdir"), "Name");

    LocalProjectImpl project = new LocalProjectImpl(workspace, sessionContext, projectPath);
    project.setInMemory(true);

    RMController client = mock(RMController.class);
    when(client.getProjectsDataSources(Mockito.<String>any(), Mockito.<String[]>any()))
        .thenReturn(null);

    DataSourceConfigurationManagerRM dataSourceConfigurationManagerRM =
        new DataSourceConfigurationManagerRM(project, client);

    // Act
    InputStream actualReadConfigurationResult =
        dataSourceConfigurationManagerRM.readConfiguration("Name", new ArrayList<>());

    // Assert
    verify(client).getProjectsDataSources(eq("Name"), isA(String[].class));
    assertNull(actualReadConfigurationResult);
  }

  /**
   * Test {@link DataSourceConfigurationManagerRM#readConfiguration(String, Collection)}.
   *
   * <ul>
   *   <li>Then return read is twenty-one.
   * </ul>
   *
   * <p>Method under test: {@link DataSourceConfigurationManagerRM#readConfiguration(String,
   * Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "InputStream DataSourceConfigurationManagerRM.readConfiguration(String, Collection)"
  })
  public void testReadConfiguration_thenReturnReadIsTwentyOne() throws IOException, DBException {
    // Arrange
    DBPWorkspace workspace = mock(DBPWorkspace.class);
    SessionContextImpl sessionContext = new SessionContextImpl(mock(SMSessionContext.class));
    Path projectPath = Paths.get(System.getProperty("java.io.tmpdir"), "Name");

    LocalProjectImpl project = new LocalProjectImpl(workspace, sessionContext, projectPath);
    project.setInMemory(true);

    RMController client = mock(RMController.class);
    when(client.getProjectsDataSources(Mockito.<String>any(), Mockito.<String[]>any()))
        .thenReturn("Projects Data Sources");

    DataSourceConfigurationManagerRM dataSourceConfigurationManagerRM =
        new DataSourceConfigurationManagerRM(project, client);

    // Act
    InputStream actualReadConfigurationResult =
        dataSourceConfigurationManagerRM.readConfiguration("Name", new ArrayList<>());

    // Assert
    verify(client).getProjectsDataSources(eq("Name"), isA(String[].class));
    byte[] byteArray = new byte[21];
    assertEquals(21, actualReadConfigurationResult.read(byteArray));
    assertArrayEquals("Projects Data Sources".getBytes("UTF-8"), byteArray);
  }

  /**
   * Test {@link DataSourceConfigurationManagerRM#readConfiguration(String, Collection)}.
   *
   * <ul>
   *   <li>When {@code credentials-config}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DataSourceConfigurationManagerRM#readConfiguration(String,
   * Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "InputStream DataSourceConfigurationManagerRM.readConfiguration(String, Collection)"
  })
  public void testReadConfiguration_whenCredentialsConfig_thenReturnNull()
      throws IOException, DBException {
    // Arrange
    DBPWorkspace workspace = mock(DBPWorkspace.class);
    SessionContextImpl sessionContext = new SessionContextImpl(mock(SMSessionContext.class));
    Path projectPath = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    LocalProjectImpl project = new LocalProjectImpl(workspace, sessionContext, projectPath);
    DataSourceConfigurationManagerRM dataSourceConfigurationManagerRM =
        new DataSourceConfigurationManagerRM(project, mock(RMController.class));

    // Act and Assert
    assertNull(dataSourceConfigurationManagerRM.readConfiguration("credentials-config", null));
  }

  /**
   * Test {@link DataSourceConfigurationManagerRM#writeConfiguration(String, byte[])}.
   *
   * <p>Method under test: {@link DataSourceConfigurationManagerRM#writeConfiguration(String,
   * byte[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataSourceConfigurationManagerRM.writeConfiguration(String, byte[])"})
  public void testWriteConfiguration() throws UnsupportedEncodingException, DBException {
    // Arrange
    DBPWorkspace workspace = mock(DBPWorkspace.class);
    SessionContextImpl sessionContext = new SessionContextImpl(mock(SMSessionContext.class));
    Path projectPath = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    LocalProjectImpl project = new LocalProjectImpl(workspace, sessionContext, projectPath);
    DataSourceConfigurationManagerRM dataSourceConfigurationManagerRM =
        new DataSourceConfigurationManagerRM(project, mock(RMController.class));

    // Act and Assert
    assertThrows(
        DBException.class,
        () ->
            dataSourceConfigurationManagerRM.writeConfiguration(
                "Name", "AXAXAXAX".getBytes("UTF-8")));
  }
}

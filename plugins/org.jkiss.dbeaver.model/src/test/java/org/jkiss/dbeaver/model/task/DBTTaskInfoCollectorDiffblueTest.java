package org.jkiss.dbeaver.model.task;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Collection;
import java.util.Set;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.task.DBTTaskInfoCollector.TaskInformation;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBTTaskInfoCollectorDiffblueTest {
  /**
   * Test TaskInformation {@link TaskInformation#addDataSource(DBPDataSourceContainer)}.
   *
   * <ul>
   *   <li>Then {@link TaskInformation} (default constructor) DataSources size is one.
   * </ul>
   *
   * <p>Method under test: {@link TaskInformation#addDataSource(DBPDataSourceContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TaskInformation.addDataSource(DBPDataSourceContainer)"})
  public void testTaskInformationAddDataSource_thenTaskInformationDataSourcesSizeIsOne() {
    // Arrange
    TaskInformation taskInformation = new TaskInformation();

    // Act
    taskInformation.addDataSource(mock(DBPDataSourceContainer.class));

    // Assert
    Collection<DBPDataSourceContainer> dataSources = taskInformation.getDataSources();
    assertEquals(1, dataSources.size());
    assertTrue(dataSources instanceof Set);
  }

  /**
   * Test TaskInformation {@link TaskInformation#addDataSource(DBPDataSourceContainer)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then {@link TaskInformation} (default constructor) InetAddresses {@link Set}.
   * </ul>
   *
   * <p>Method under test: {@link TaskInformation#addDataSource(DBPDataSourceContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TaskInformation.addDataSource(DBPDataSourceContainer)"})
  public void testTaskInformationAddDataSource_whenNull_thenTaskInformationInetAddressesSet() {
    // Arrange
    TaskInformation taskInformation = new TaskInformation();

    // Act
    taskInformation.addDataSource(null);

    // Assert that nothing has changed
    Collection<DBPDataSourceContainer> dataSources = taskInformation.getDataSources();
    assertTrue(dataSources instanceof Set);
    Collection<String> inetAddresses = taskInformation.getInetAddresses();
    assertTrue(inetAddresses instanceof Set);
    assertTrue(dataSources.isEmpty());
    assertTrue(inetAddresses.isEmpty());
  }

  /**
   * Test TaskInformation {@link TaskInformation#addInetAddress(String)}.
   *
   * <ul>
   *   <li>Then {@link TaskInformation} (default constructor) InetAddresses size is one.
   * </ul>
   *
   * <p>Method under test: {@link TaskInformation#addInetAddress(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TaskInformation.addInetAddress(String)"})
  public void testTaskInformationAddInetAddress_thenTaskInformationInetAddressesSizeIsOne() {
    // Arrange
    TaskInformation taskInformation = new TaskInformation();

    // Act
    taskInformation.addInetAddress("Host Or Ip");

    // Assert
    Collection<String> inetAddresses = taskInformation.getInetAddresses();
    assertEquals(1, inetAddresses.size());
    assertTrue(inetAddresses instanceof Set);
    assertTrue(inetAddresses.contains("Host Or Ip"));
  }

  /**
   * Test TaskInformation {@link TaskInformation#addInetAddress(String)}.
   *
   * <ul>
   *   <li>When empty string.
   * </ul>
   *
   * <p>Method under test: {@link TaskInformation#addInetAddress(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TaskInformation.addInetAddress(String)"})
  public void testTaskInformationAddInetAddress_whenEmptyString() {
    // Arrange
    TaskInformation taskInformation = new TaskInformation();

    // Act
    taskInformation.addInetAddress("");

    // Assert that nothing has changed
    Collection<String> inetAddresses = taskInformation.getInetAddresses();
    assertTrue(inetAddresses instanceof Set);
    assertTrue(inetAddresses.isEmpty());
  }

  /**
   * Test TaskInformation {@link TaskInformation#addInetAddress(String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then {@link TaskInformation} (default constructor) InetAddresses Empty.
   * </ul>
   *
   * <p>Method under test: {@link TaskInformation#addInetAddress(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TaskInformation.addInetAddress(String)"})
  public void testTaskInformationAddInetAddress_whenNull_thenTaskInformationInetAddressesEmpty() {
    // Arrange
    TaskInformation taskInformation = new TaskInformation();

    // Act
    taskInformation.addInetAddress(null);

    // Assert that nothing has changed
    Collection<String> inetAddresses = taskInformation.getInetAddresses();
    assertTrue(inetAddresses instanceof Set);
    assertTrue(inetAddresses.isEmpty());
  }

  /**
   * Test TaskInformation {@link TaskInformation#addLocation(String)}.
   *
   * <ul>
   *   <li>Then {@link TaskInformation} (default constructor) IOLocations size is one.
   * </ul>
   *
   * <p>Method under test: {@link TaskInformation#addLocation(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TaskInformation.addLocation(String)"})
  public void testTaskInformationAddLocation_thenTaskInformationIOLocationsSizeIsOne() {
    // Arrange
    TaskInformation taskInformation = new TaskInformation();

    // Act
    taskInformation.addLocation("Path Or URI");

    // Assert
    Collection<String> iOLocations = taskInformation.getIOLocations();
    assertEquals(1, iOLocations.size());
    assertTrue(iOLocations instanceof Set);
    assertTrue(iOLocations.contains("Path Or URI"));
  }

  /**
   * Test TaskInformation {@link TaskInformation#addLocation(String)}.
   *
   * <ul>
   *   <li>When empty string.
   * </ul>
   *
   * <p>Method under test: {@link TaskInformation#addLocation(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TaskInformation.addLocation(String)"})
  public void testTaskInformationAddLocation_whenEmptyString() {
    // Arrange
    TaskInformation taskInformation = new TaskInformation();

    // Act
    taskInformation.addLocation("");

    // Assert that nothing has changed
    Collection<String> iOLocations = taskInformation.getIOLocations();
    assertTrue(iOLocations instanceof Set);
    assertTrue(iOLocations.isEmpty());
  }

  /**
   * Test TaskInformation {@link TaskInformation#addLocation(String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then {@link TaskInformation} (default constructor) IOLocations Empty.
   * </ul>
   *
   * <p>Method under test: {@link TaskInformation#addLocation(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TaskInformation.addLocation(String)"})
  public void testTaskInformationAddLocation_whenNull_thenTaskInformationIOLocationsEmpty() {
    // Arrange
    TaskInformation taskInformation = new TaskInformation();

    // Act
    taskInformation.addLocation(null);

    // Assert that nothing has changed
    Collection<String> iOLocations = taskInformation.getIOLocations();
    assertTrue(iOLocations instanceof Set);
    assertTrue(iOLocations.isEmpty());
  }

  /**
   * Test TaskInformation getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link TaskInformation}
   *   <li>{@link TaskInformation#getDataSources()}
   *   <li>{@link TaskInformation#getIOLocations()}
   *   <li>{@link TaskInformation#getInetAddresses()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void TaskInformation.<init>()",
    "Collection TaskInformation.getDataSources()",
    "Collection TaskInformation.getIOLocations()",
    "Collection TaskInformation.getInetAddresses()"
  })
  public void testTaskInformationGettersAndSetters() {
    // Arrange and Act
    TaskInformation actualTaskInformation = new TaskInformation();
    Collection<DBPDataSourceContainer> actualDataSources = actualTaskInformation.getDataSources();
    Collection<String> actualIOLocations = actualTaskInformation.getIOLocations();

    // Assert
    assertTrue(actualDataSources instanceof Set);
    assertTrue(actualIOLocations instanceof Set);
    assertTrue(actualTaskInformation.getInetAddresses() instanceof Set);
  }
}

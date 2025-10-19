package org.jkiss.dbeaver.model.qm.meta;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.connection.DBPConnectionConfiguration;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class QMMObjectDiffblueTest {
  /**
   * Test {@link QMMObject#close()}.
   *
   * <p>Method under test: {@link QMMObject#close()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void QMMObject.close()"})
  public void testClose() {
    // Arrange
    QMMTransactionInfo qmmTransactionInfo =
        new QMMTransactionInfo(mock(QMMConnectionInfo.class), 1L);

    // Act
    qmmTransactionInfo.close();

    // Assert
    assertTrue(qmmTransactionInfo.isUpdated());
  }

  /**
   * Test {@link QMMObject#reopen()}.
   *
   * <p>Method under test: {@link QMMObject#reopen()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void QMMObject.reopen()"})
  public void testReopen() {
    // Arrange
    QMMProjectInfo projectInfo =
        QMMProjectInfo.builder()
            .setId("42")
            .setAnonymous(true)
            .setName("Name")
            .setPath("Path")
            .build();
    QMMConnectionInfo qmmConnectionInfo =
        new QMMConnectionInfo(
            1L,
            1L,
            projectInfo,
            "42",
            "Container Name",
            "42",
            new DBPConnectionConfiguration(),
            "Instance ID",
            "Context Name",
            true);

    // Act
    qmmConnectionInfo.reopen();

    // Assert
    assertEquals(-1L, qmmConnectionInfo.getDuration());
    assertEquals(0L, qmmConnectionInfo.getCloseTime());
    assertFalse(qmmConnectionInfo.isClosed());
    assertTrue(qmmConnectionInfo.isUpdated());
  }

  /**
   * Test {@link QMMObject#isUpdated()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link QMMObject#isUpdated()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean QMMObject.isUpdated()"})
  public void testIsUpdated_thenReturnFalse() {
    // Arrange
    QMMProjectInfo projectInfo =
        QMMProjectInfo.builder()
            .setId("42")
            .setAnonymous(true)
            .setName("Name")
            .setPath("Path")
            .build();
    QMMConnectionInfo qmmConnectionInfo =
        new QMMConnectionInfo(
            1L,
            1L,
            projectInfo,
            "42",
            "Container Name",
            "42",
            new DBPConnectionConfiguration(),
            "Instance ID",
            "Context Name",
            true);

    // Act and Assert
    assertFalse(qmmConnectionInfo.isUpdated());
  }

  /**
   * Test {@link QMMObject#isUpdated()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link QMMObject#isUpdated()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean QMMObject.isUpdated()"})
  public void testIsUpdated_thenReturnTrue() {
    // Arrange
    QMMProjectInfo projectInfo =
        QMMProjectInfo.builder()
            .setId("42")
            .setAnonymous(true)
            .setName("Name")
            .setPath("Path")
            .build();

    QMMConnectionInfo qmmConnectionInfo =
        new QMMConnectionInfo(
            1L,
            1L,
            projectInfo,
            "42",
            "Container Name",
            "42",
            new DBPConnectionConfiguration(),
            "Instance ID",
            "Context Name",
            true);
    qmmConnectionInfo.update();

    // Act and Assert
    assertTrue(qmmConnectionInfo.isUpdated());
  }

  /**
   * Test {@link QMMObject#getOpenTime()}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link QMMObject#getOpenTime()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long QMMObject.getOpenTime()"})
  public void testGetOpenTime_thenReturnOne() {
    // Arrange
    QMMProjectInfo projectInfo =
        QMMProjectInfo.builder()
            .setId("42")
            .setAnonymous(true)
            .setName("Name")
            .setPath("Path")
            .build();
    QMMConnectionInfo qmmConnectionInfo =
        new QMMConnectionInfo(
            1L,
            1L,
            projectInfo,
            "42",
            "Container Name",
            "42",
            new DBPConnectionConfiguration(),
            "Instance ID",
            "Context Name",
            true);

    // Act and Assert
    assertEquals(1L, qmmConnectionInfo.getOpenTime());
  }

  /**
   * Test {@link QMMObject#getCloseTime()}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link QMMObject#getCloseTime()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long QMMObject.getCloseTime()"})
  public void testGetCloseTime_thenReturnOne() {
    // Arrange
    QMMProjectInfo projectInfo =
        QMMProjectInfo.builder()
            .setId("42")
            .setAnonymous(true)
            .setName("Name")
            .setPath("Path")
            .build();
    QMMConnectionInfo qmmConnectionInfo =
        new QMMConnectionInfo(
            1L,
            1L,
            projectInfo,
            "42",
            "Container Name",
            "42",
            new DBPConnectionConfiguration(),
            "Instance ID",
            "Context Name",
            true);

    // Act and Assert
    assertEquals(1L, qmmConnectionInfo.getCloseTime());
  }

  /**
   * Test {@link QMMObject#isClosed()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link QMMObject#isClosed()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean QMMObject.isClosed()"})
  public void testIsClosed_thenReturnFalse() {
    // Arrange
    QMMProjectInfo projectInfo =
        QMMProjectInfo.builder()
            .setId("42")
            .setAnonymous(true)
            .setName("Name")
            .setPath("Path")
            .build();
    QMMConnectionInfo qmmConnectionInfo =
        new QMMConnectionInfo(
            1L,
            0L,
            projectInfo,
            "42",
            "Container Name",
            "42",
            new DBPConnectionConfiguration(),
            "Instance ID",
            "Context Name",
            true);

    // Act and Assert
    assertFalse(qmmConnectionInfo.isClosed());
  }

  /**
   * Test {@link QMMObject#isClosed()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link QMMObject#isClosed()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean QMMObject.isClosed()"})
  public void testIsClosed_thenReturnTrue() {
    // Arrange
    QMMProjectInfo projectInfo =
        QMMProjectInfo.builder()
            .setId("42")
            .setAnonymous(true)
            .setName("Name")
            .setPath("Path")
            .build();
    QMMConnectionInfo qmmConnectionInfo =
        new QMMConnectionInfo(
            1L,
            1L,
            projectInfo,
            "42",
            "Container Name",
            "42",
            new DBPConnectionConfiguration(),
            "Instance ID",
            "Context Name",
            true);

    // Act and Assert
    assertTrue(qmmConnectionInfo.isClosed());
  }

  /**
   * Test {@link QMMObject#getObjectType()}.
   *
   * <ul>
   *   <li>Then return {@code CONNECTION_INFO}.
   * </ul>
   *
   * <p>Method under test: {@link QMMObject#getObjectType()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"QMMetaObjectType QMMObject.getObjectType()"})
  public void testGetObjectType_thenReturnConnectionInfo() {
    // Arrange
    QMMProjectInfo projectInfo =
        QMMProjectInfo.builder()
            .setId("42")
            .setAnonymous(true)
            .setName("Name")
            .setPath("Path")
            .build();
    QMMConnectionInfo qmmConnectionInfo =
        new QMMConnectionInfo(
            1L,
            1L,
            projectInfo,
            "42",
            "Container Name",
            "42",
            new DBPConnectionConfiguration(),
            "Instance ID",
            "Context Name",
            true);

    // Act and Assert
    assertEquals(QMMetaObjectType.CONNECTION_INFO, qmmConnectionInfo.getObjectType());
  }

  /**
   * Test {@link QMMObject#update()}.
   *
   * <p>Method under test: {@link QMMObject#update()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void QMMObject.update()"})
  public void testUpdate() {
    // Arrange
    QMMProjectInfo projectInfo =
        QMMProjectInfo.builder()
            .setId("42")
            .setAnonymous(true)
            .setName("Name")
            .setPath("Path")
            .build();
    QMMConnectionInfo qmmConnectionInfo =
        new QMMConnectionInfo(
            1L,
            1L,
            projectInfo,
            "42",
            "Container Name",
            "42",
            new DBPConnectionConfiguration(),
            "Instance ID",
            "Context Name",
            true);

    // Act
    qmmConnectionInfo.update();

    // Assert
    assertTrue(qmmConnectionInfo.isUpdated());
  }

  /**
   * Test {@link QMMObject#getDuration()}.
   *
   * <ul>
   *   <li>Then return minus one.
   * </ul>
   *
   * <p>Method under test: {@link QMMObject#getDuration()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long QMMObject.getDuration()"})
  public void testGetDuration_thenReturnMinusOne() {
    // Arrange
    QMMProjectInfo projectInfo =
        QMMProjectInfo.builder()
            .setId("42")
            .setAnonymous(true)
            .setName("Name")
            .setPath("Path")
            .build();
    QMMConnectionInfo qmmConnectionInfo =
        new QMMConnectionInfo(
            1L,
            0L,
            projectInfo,
            "42",
            "Container Name",
            "42",
            new DBPConnectionConfiguration(),
            "Instance ID",
            "Context Name",
            true);

    // Act and Assert
    assertEquals(-1L, qmmConnectionInfo.getDuration());
  }

  /**
   * Test {@link QMMObject#getDuration()}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link QMMObject#getDuration()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long QMMObject.getDuration()"})
  public void testGetDuration_thenReturnZero() {
    // Arrange
    QMMProjectInfo projectInfo =
        QMMProjectInfo.builder()
            .setId("42")
            .setAnonymous(true)
            .setName("Name")
            .setPath("Path")
            .build();
    QMMConnectionInfo qmmConnectionInfo =
        new QMMConnectionInfo(
            1L,
            1L,
            projectInfo,
            "42",
            "Container Name",
            "42",
            new DBPConnectionConfiguration(),
            "Instance ID",
            "Context Name",
            true);

    // Act and Assert
    assertEquals(0L, qmmConnectionInfo.getDuration());
  }
}

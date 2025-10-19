package org.jkiss.dbeaver.model.qm.meta;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.app.DBPProject;
import org.jkiss.dbeaver.model.connection.DBPConnectionConfiguration;
import org.jkiss.dbeaver.model.connection.DBPDriver;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class QMMDataSourceConnectErrorInfoDiffblueTest {
  /**
   * Test {@link QMMDataSourceConnectErrorInfo#QMMDataSourceConnectErrorInfo(DBPDataSourceContainer,
   * String, String)}.
   *
   * <ul>
   *   <li>Then return Text is empty string.
   * </ul>
   *
   * <p>Method under test: {@link
   * QMMDataSourceConnectErrorInfo#QMMDataSourceConnectErrorInfo(DBPDataSourceContainer, String,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void QMMDataSourceConnectErrorInfo.<init>(DBPDataSourceContainer, String, String)"
  })
  public void testNewQMMDataSourceConnectErrorInfo_thenReturnTextIsEmptyString() {
    // Arrange
    DBPProject dbpProject = mock(DBPProject.class);
    when(dbpProject.getId()).thenReturn("42");

    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.getId()).thenReturn("42");

    DBPDataSourceContainer container = mock(DBPDataSourceContainer.class);
    when(container.getConnectionConfiguration()).thenReturn(new DBPConnectionConfiguration());
    when(container.getId()).thenReturn("42");
    when(container.getName()).thenReturn("Name");
    when(container.getDriver()).thenReturn(dbpDriver);
    when(container.getProject()).thenReturn(dbpProject);

    // Act
    QMMDataSourceConnectErrorInfo actualQmmDataSourceConnectErrorInfo =
        new QMMDataSourceConnectErrorInfo(container, "An error occurred", "An error occurred");

    // Assert
    verify(container).getConnectionConfiguration();
    verify(container).getDriver();
    verify(container).getId();
    verify(container).getProject();
    verify(container).getName();
    verify(dbpProject).getId();
    verify(dbpDriver).getId();
    assertEquals("", actualQmmDataSourceConnectErrorInfo.getText());
    assertEquals("42", actualQmmDataSourceConnectErrorInfo.getContainerId());
    assertEquals("42", actualQmmDataSourceConnectErrorInfo.getDriverId());
    assertEquals("42", actualQmmDataSourceConnectErrorInfo.getProjectId());
    assertEquals("An error occurred", actualQmmDataSourceConnectErrorInfo.getErrorMessage());
    assertEquals("An error occurred", actualQmmDataSourceConnectErrorInfo.getErrorType());
    assertEquals("Name", actualQmmDataSourceConnectErrorInfo.getContainerName());
    assertNull(actualQmmDataSourceConnectErrorInfo.getConnectionUrl());
    assertNull(actualQmmDataSourceConnectErrorInfo.getConnection());
    assertEquals(-1L, actualQmmDataSourceConnectErrorInfo.getDuration());
    assertEquals(0L, actualQmmDataSourceConnectErrorInfo.getCloseTime());
    assertEquals(
        QMMetaObjectType.CONNECTION_ERROR_INFO,
        actualQmmDataSourceConnectErrorInfo.getObjectType());
    assertFalse(actualQmmDataSourceConnectErrorInfo.isClosed());
    assertFalse(actualQmmDataSourceConnectErrorInfo.isUpdated());
  }
}

package org.jkiss.dbeaver.model.access;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.DBPDataSourceProvider;
import org.jkiss.dbeaver.model.connection.DBPConnectionConfiguration;
import org.jkiss.dbeaver.model.connection.DBPDriver;
import org.jkiss.dbeaver.model.impl.auth.AuthModelDatabaseNative;
import org.jkiss.dbeaver.model.impl.auth.AuthModelDatabaseNativeCredentials;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBAAuthModelDiffblueTest {
  /**
   * Test {@link DBAAuthModel#provideCredentials(DBPDataSourceContainer, DBPConnectionConfiguration,
   * DBAAuthCredentials)}.
   *
   * <ul>
   *   <li>Then {@link DBPConnectionConfiguration#DBPConnectionConfiguration()} UserPassword is
   *       {@code iloveyou}.
   * </ul>
   *
   * <p>Method under test: {@link DBAAuthModel#provideCredentials(DBPDataSourceContainer,
   * DBPConnectionConfiguration, DBAAuthCredentials)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBAAuthModel.provideCredentials(DBPDataSourceContainer, DBPConnectionConfiguration, DBAAuthCredentials)"
  })
  public void testProvideCredentials_thenDBPConnectionConfigurationUserPasswordIsIloveyou() {
    // Arrange
    AuthModelDatabaseNative<AuthModelDatabaseNativeCredentials> authModelDatabaseNative =
        new AuthModelDatabaseNative<>();
    DBPDataSourceContainer dataSource = mock(DBPDataSourceContainer.class);
    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();

    AuthModelDatabaseNativeCredentials authModelDatabaseNativeCredentials =
        new AuthModelDatabaseNativeCredentials();
    authModelDatabaseNativeCredentials.setUserName("janedoe");
    authModelDatabaseNativeCredentials.setUserPassword("iloveyou");

    // Act
    authModelDatabaseNative.provideCredentials(
        dataSource, configuration, authModelDatabaseNativeCredentials);

    // Assert
    assertEquals("iloveyou", configuration.getUserPassword());
    assertEquals("janedoe", configuration.getUserName());
  }

  /**
   * Test {@link DBAAuthModel#createCredentialsForm(DBPDataSourceContainer,
   * DBPConnectionConfiguration)}.
   *
   * <ul>
   *   <li>Then return UserPassword is empty string.
   * </ul>
   *
   * <p>Method under test: {@link DBAAuthModel#createCredentialsForm(DBPDataSourceContainer,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBAAuthCredentialsForm DBAAuthModel.createCredentialsForm(DBPDataSourceContainer, DBPConnectionConfiguration)"
  })
  public void testCreateCredentialsForm_thenReturnUserPasswordIsEmptyString() {
    // Arrange
    AuthModelDatabaseNative<AuthModelDatabaseNativeCredentials> authModelDatabaseNative =
        new AuthModelDatabaseNative<>();

    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isAllowsEmptyPassword()).thenReturn(true);
    when(dbpDriver.getDataSourceProvider()).thenReturn(mock(DBPDataSourceProvider.class));

    DBPDataSourceContainer dataSource = mock(DBPDataSourceContainer.class);
    when(dataSource.getDriver()).thenReturn(dbpDriver);

    // Act
    DBAAuthCredentialsForm actualCreateCredentialsFormResult =
        authModelDatabaseNative.createCredentialsForm(dataSource, new DBPConnectionConfiguration());

    // Assert
    verify(dataSource, atLeast(1)).getDriver();
    verify(dbpDriver).getDataSourceProvider();
    verify(dbpDriver).isAllowsEmptyPassword();
    assertTrue(actualCreateCredentialsFormResult instanceof AuthModelDatabaseNativeCredentials);
    assertEquals(
        "",
        ((AuthModelDatabaseNativeCredentials) actualCreateCredentialsFormResult).getUserPassword());
    assertNull(
        ((AuthModelDatabaseNativeCredentials) actualCreateCredentialsFormResult).getUserName());
    assertTrue(
        ((AuthModelDatabaseNativeCredentials) actualCreateCredentialsFormResult).isComplete());
  }

  /**
   * Test {@link DBAAuthModel#createCredentialsForm(DBPDataSourceContainer,
   * DBPConnectionConfiguration)}.
   *
   * <ul>
   *   <li>When {@link DBPDataSourceContainer}.
   * </ul>
   *
   * <p>Method under test: {@link DBAAuthModel#createCredentialsForm(DBPDataSourceContainer,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBAAuthCredentialsForm DBAAuthModel.createCredentialsForm(DBPDataSourceContainer, DBPConnectionConfiguration)"
  })
  public void testCreateCredentialsForm_whenDBPDataSourceContainer() {
    // Arrange
    AuthModelDatabaseNative<AuthModelDatabaseNativeCredentials> authModelDatabaseNative =
        new AuthModelDatabaseNative<>();

    // Act
    DBAAuthCredentialsForm actualCreateCredentialsFormResult =
        authModelDatabaseNative.createCredentialsForm(mock(DBPDataSourceContainer.class), null);

    // Assert
    assertTrue(actualCreateCredentialsFormResult instanceof AuthModelDatabaseNativeCredentials);
    assertNull(
        ((AuthModelDatabaseNativeCredentials) actualCreateCredentialsFormResult).getUserName());
    assertNull(
        ((AuthModelDatabaseNativeCredentials) actualCreateCredentialsFormResult).getUserPassword());
    assertTrue(
        ((AuthModelDatabaseNativeCredentials) actualCreateCredentialsFormResult).isComplete());
  }

  /**
   * Test {@link DBAAuthModel#createCredentialsForm(DBPDataSourceContainer,
   * DBPConnectionConfiguration)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return UserPassword is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBAAuthModel#createCredentialsForm(DBPDataSourceContainer,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBAAuthCredentialsForm DBAAuthModel.createCredentialsForm(DBPDataSourceContainer, DBPConnectionConfiguration)"
  })
  public void testCreateCredentialsForm_whenNull_thenReturnUserPasswordIsNull() {
    // Arrange
    AuthModelDatabaseNative<AuthModelDatabaseNativeCredentials> authModelDatabaseNative =
        new AuthModelDatabaseNative<>();

    // Act
    DBAAuthCredentialsForm actualCreateCredentialsFormResult =
        authModelDatabaseNative.createCredentialsForm(null, null);

    // Assert
    assertTrue(actualCreateCredentialsFormResult instanceof AuthModelDatabaseNativeCredentials);
    assertNull(
        ((AuthModelDatabaseNativeCredentials) actualCreateCredentialsFormResult).getUserName());
    assertNull(
        ((AuthModelDatabaseNativeCredentials) actualCreateCredentialsFormResult).getUserPassword());
    assertTrue(
        ((AuthModelDatabaseNativeCredentials) actualCreateCredentialsFormResult).isComplete());
  }
}

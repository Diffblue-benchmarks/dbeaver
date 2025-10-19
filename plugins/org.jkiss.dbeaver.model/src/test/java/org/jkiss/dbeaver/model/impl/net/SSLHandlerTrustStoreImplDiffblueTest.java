package org.jkiss.dbeaver.model.impl.net;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.net.DBWHandlerConfiguration;
import org.jkiss.dbeaver.model.net.DBWHandlerDescriptor;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SSLHandlerTrustStoreImplDiffblueTest {
  /**
   * Test {@link SSLHandlerTrustStoreImpl#readCertificate(DBWHandlerConfiguration, String, String)}
   * with {@code configuration}, {@code basePropName}, {@code altPropName}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SSLHandlerTrustStoreImpl#readCertificate(DBWHandlerConfiguration,
   * String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "byte[] SSLHandlerTrustStoreImpl.readCertificate(DBWHandlerConfiguration, String, String)"
  })
  public void testReadCertificateWithConfigurationBasePropNameAltPropName_thenReturnNull()
      throws IOException {
    // Arrange
    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");
    DBWHandlerConfiguration configuration =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));

    // Act
    byte[] actualReadCertificateResult =
        SSLHandlerTrustStoreImpl.readCertificate(configuration, "Base Prop Name", "Alt Prop Name");

    // Assert
    verify(descriptor).getId();
    assertNull(actualReadCertificateResult);
  }

  /**
   * Test {@link SSLHandlerTrustStoreImpl#readCertificate(DBWHandlerConfiguration, String, String)}
   * with {@code configuration}, {@code basePropName}, {@code altPropName}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SSLHandlerTrustStoreImpl#readCertificate(DBWHandlerConfiguration,
   * String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "byte[] SSLHandlerTrustStoreImpl.readCertificate(DBWHandlerConfiguration, String, String)"
  })
  public void testReadCertificateWithConfigurationBasePropNameAltPropName_thenReturnNull2()
      throws IOException {
    // Arrange
    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");
    DBWHandlerConfiguration configuration =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));

    // Act
    byte[] actualReadCertificateResult =
        SSLHandlerTrustStoreImpl.readCertificate(configuration, "Base Prop Name", null);

    // Assert
    verify(descriptor).getId();
    assertNull(actualReadCertificateResult);
  }

  /**
   * Test {@link SSLHandlerTrustStoreImpl#readCertificate(DBWHandlerConfiguration, String)} with
   * {@code configuration}, {@code basePropName}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SSLHandlerTrustStoreImpl#readCertificate(DBWHandlerConfiguration,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "byte[] SSLHandlerTrustStoreImpl.readCertificate(DBWHandlerConfiguration, String)"
  })
  public void testReadCertificateWithConfigurationBasePropName_given42_thenReturnNull()
      throws IOException {
    // Arrange
    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");
    DBWHandlerConfiguration configuration =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));

    // Act
    byte[] actualReadCertificateResult =
        SSLHandlerTrustStoreImpl.readCertificate(configuration, "Base Prop Name");

    // Assert
    verify(descriptor).getId();
    assertNull(actualReadCertificateResult);
  }

  /**
   * Test {@link SSLHandlerTrustStoreImpl#loadDerFromPem(DBWHandlerConfiguration, Path)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link DBWHandlerDescriptor} {@link DBWHandlerDescriptor#getId()} return {@code 42}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SSLHandlerTrustStoreImpl#loadDerFromPem(DBWHandlerConfiguration,
   * Path)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean SSLHandlerTrustStoreImpl.loadDerFromPem(DBWHandlerConfiguration, Path)"
  })
  public void testLoadDerFromPem_given42_whenDBWHandlerDescriptorGetIdReturn42_thenReturnFalse()
      throws IOException {
    // Arrange
    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");
    DBWHandlerConfiguration handler =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));

    // Act
    boolean actualLoadDerFromPemResult =
        SSLHandlerTrustStoreImpl.loadDerFromPem(
            handler, Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

    // Assert
    verify(descriptor).getId();
    assertFalse(actualLoadDerFromPemResult);
  }

  /**
   * Test {@link SSLHandlerTrustStoreImpl#readTrustStoreData(DBWHandlerConfiguration, String)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * SSLHandlerTrustStoreImpl#readTrustStoreData(DBWHandlerConfiguration, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "byte[] SSLHandlerTrustStoreImpl.readTrustStoreData(DBWHandlerConfiguration, String)"
  })
  public void testReadTrustStoreData_given42_thenReturnNull() throws DBException {
    // Arrange
    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");
    DBWHandlerConfiguration configuration =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));

    // Act
    byte[] actualReadTrustStoreDataResult =
        SSLHandlerTrustStoreImpl.readTrustStoreData(configuration, "Property");

    // Assert
    verify(descriptor).getId();
    assertNull(actualReadTrustStoreDataResult);
  }

  /**
   * Test new {@link SSLHandlerTrustStoreImpl} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link SSLHandlerTrustStoreImpl}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SSLHandlerTrustStoreImpl.<init>()"})
  public void testNewSSLHandlerTrustStoreImpl() {
    // Arrange, Act and Assert
    assertEquals(0, new SSLHandlerTrustStoreImpl().getDependentDataSources().length);
  }
}

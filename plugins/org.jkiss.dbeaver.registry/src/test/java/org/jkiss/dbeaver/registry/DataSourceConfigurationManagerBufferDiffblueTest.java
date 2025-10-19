package org.jkiss.dbeaver.registry;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.UnsupportedEncodingException;
import java.util.List;
import org.jkiss.dbeaver.model.DBPDataSourceConfigurationStorage;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DataSourceConfigurationManagerBufferDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link DataSourceConfigurationManagerBuffer}
   *   <li>{@link DataSourceConfigurationManagerBuffer#setData(byte[])}
   *   <li>{@link DataSourceConfigurationManagerBuffer#getConfigurationStorages()}
   *   <li>{@link DataSourceConfigurationManagerBuffer#getData()}
   *   <li>{@link DataSourceConfigurationManagerBuffer#isReadOnly()}
   *   <li>{@link DataSourceConfigurationManagerBuffer#isSecure()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DataSourceConfigurationManagerBuffer.<init>()",
    "List DataSourceConfigurationManagerBuffer.getConfigurationStorages()",
    "byte[] DataSourceConfigurationManagerBuffer.getData()",
    "boolean DataSourceConfigurationManagerBuffer.isReadOnly()",
    "boolean DataSourceConfigurationManagerBuffer.isSecure()",
    "void DataSourceConfigurationManagerBuffer.setData(byte[])"
  })
  public void testGettersAndSetters() throws UnsupportedEncodingException {
    // Arrange and Act
    DataSourceConfigurationManagerBuffer actualDataSourceConfigurationManagerBuffer =
        new DataSourceConfigurationManagerBuffer();
    byte[] data = "AXAXAXAX".getBytes("UTF-8");
    actualDataSourceConfigurationManagerBuffer.setData(data);
    List<DBPDataSourceConfigurationStorage> actualConfigurationStorages =
        actualDataSourceConfigurationManagerBuffer.getConfigurationStorages();
    byte[] actualData = actualDataSourceConfigurationManagerBuffer.getData();
    boolean actualIsReadOnlyResult = actualDataSourceConfigurationManagerBuffer.isReadOnly();

    // Assert
    assertNull(actualConfigurationStorages);
    assertFalse(actualIsReadOnlyResult);
    assertTrue(actualDataSourceConfigurationManagerBuffer.isSecure());
    assertSame(data, actualData);
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualData);
  }
}

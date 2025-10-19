package org.jkiss.dbeaver.ext.h2gis.data;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.data.DBDFormatSettings;
import org.jkiss.dbeaver.model.impl.SimpleTypedObject;
import org.jkiss.dbeaver.model.struct.DBSTypedObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class H2GISValueHandlerProviderDiffblueTest {
  /**
   * Test {@link H2GISValueHandlerProvider#getValueHandler(DBPDataSource, DBDFormatSettings,
   * DBSTypedObject)}.
   *
   * <p>Method under test: {@link H2GISValueHandlerProvider#getValueHandler(DBPDataSource,
   * DBDFormatSettings, DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.data.DBDValueHandler H2GISValueHandlerProvider.getValueHandler(DBPDataSource, DBDFormatSettings, DBSTypedObject)"
  })
  public void testGetValueHandler() {
    // Arrange
    H2GISValueHandlerProvider h2gisValueHandlerProvider = new H2GISValueHandlerProvider();
    DBPDataSource dataSource = mock(DBPDataSource.class);
    DBDFormatSettings preferences = mock(DBDFormatSettings.class);

    // Act and Assert
    assertNull(
        h2gisValueHandlerProvider.getValueHandler(
            dataSource, preferences, new SimpleTypedObject("Type Name")));
  }
}

package org.jkiss.dbeaver.ext.generic.data;

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

public class GenericValueHandlerProviderDiffblueTest {
  /**
   * Test {@link GenericValueHandlerProvider#getValueHandler(DBPDataSource, DBDFormatSettings,
   * DBSTypedObject)}.
   *
   * <p>Method under test: {@link GenericValueHandlerProvider#getValueHandler(DBPDataSource,
   * DBDFormatSettings, DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.data.DBDValueHandler GenericValueHandlerProvider.getValueHandler(DBPDataSource, DBDFormatSettings, DBSTypedObject)"
  })
  public void testGetValueHandler() {
    // Arrange
    GenericValueHandlerProvider genericValueHandlerProvider = new GenericValueHandlerProvider();
    DBPDataSource dataSource = mock(DBPDataSource.class);
    DBDFormatSettings preferences = mock(DBDFormatSettings.class);

    // Act and Assert
    assertNull(
        genericValueHandlerProvider.getValueHandler(
            dataSource, preferences, new SimpleTypedObject("Type Name")));
  }
}

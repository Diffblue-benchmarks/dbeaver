package org.jkiss.dbeaver.ext.oracle.model;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.data.DBDAttributeBinding;
import org.jkiss.dbeaver.model.data.DBDAttributeBindingCustom;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class OracleAttributeContentTypeProviderDiffblueTest {
  /**
   * Test {@link OracleAttributeContentTypeProvider#getContentType(DBDAttributeBinding)}.
   *
   * <ul>
   *   <li>Given {@code Type Name}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * OracleAttributeContentTypeProvider#getContentType(DBDAttributeBinding)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String OracleAttributeContentTypeProvider.getContentType(DBDAttributeBinding)"
  })
  public void testGetContentType_givenTypeName_thenReturnNull() {
    // Arrange
    DBDAttributeBindingCustom binding = mock(DBDAttributeBindingCustom.class);
    when(binding.getTypeName()).thenReturn("Type Name");

    // Act
    String actualContentType = OracleAttributeContentTypeProvider.INSTANCE.getContentType(binding);

    // Assert
    verify(binding).getTypeName();
    assertNull(actualContentType);
  }
}

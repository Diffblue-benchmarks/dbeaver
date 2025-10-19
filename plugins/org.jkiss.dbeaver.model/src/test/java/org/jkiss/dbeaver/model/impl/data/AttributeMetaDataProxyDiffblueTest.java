package org.jkiss.dbeaver.model.impl.data;

import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AttributeMetaDataProxyDiffblueTest {
  /**
   * Test {@link AttributeMetaDataProxy#getEntityName()}.
   *
   * <ul>
   *   <li>Given {@link AttributeMetaDataProxy#AttributeMetaDataProxy(DBSAttributeBase)} with
   *       attribute is {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link AttributeMetaDataProxy#getEntityName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String AttributeMetaDataProxy.getEntityName()"})
  public void testGetEntityName_givenAttributeMetaDataProxyWithAttributeIsNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new AttributeMetaDataProxy(null).getEntityName());
  }
}

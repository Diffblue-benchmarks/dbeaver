package org.jkiss.dbeaver.ext.mysql.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.ext.mysql.model.MySQLCatalog.AdditionalInfo;
import org.jkiss.dbeaver.ext.mysql.model.MySQLCatalog.CharsetListProvider;
import org.jkiss.dbeaver.ext.mysql.model.MySQLCatalog.CollationListProvider;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class MySQLCatalogDiffblueTest {
  /**
   * Test AdditionalInfo getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link AdditionalInfo}
   *   <li>{@link AdditionalInfo#setDefaultCharset(MySQLCharset)}
   *   <li>{@link AdditionalInfo#setSqlPath(String)}
   *   <li>{@link AdditionalInfo#getDefaultCharset()}
   *   <li>{@link AdditionalInfo#getDefaultCollation()}
   *   <li>{@link AdditionalInfo#getSqlPath()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AdditionalInfo.<init>()",
    "MySQLCharset AdditionalInfo.getDefaultCharset()",
    "MySQLCollation AdditionalInfo.getDefaultCollation()",
    "String AdditionalInfo.getSqlPath()",
    "void AdditionalInfo.setDefaultCharset(MySQLCharset)",
    "void AdditionalInfo.setDefaultCollation(MySQLCollation)",
    "void AdditionalInfo.setSqlPath(String)"
  })
  public void testAdditionalInfoGettersAndSetters() {
    // Arrange and Act
    AdditionalInfo actualAdditionalInfo = new AdditionalInfo();
    actualAdditionalInfo.setDefaultCharset(null);
    actualAdditionalInfo.setSqlPath("Sql Path");
    MySQLCharset actualDefaultCharset = actualAdditionalInfo.getDefaultCharset();
    MySQLCollation actualDefaultCollation = actualAdditionalInfo.getDefaultCollation();

    // Assert
    assertEquals("Sql Path", actualAdditionalInfo.getSqlPath());
    assertNull(actualDefaultCharset);
    assertNull(actualDefaultCollation);
  }

  /**
   * Test CharsetListProvider {@link CharsetListProvider#allowCustomValue()}.
   *
   * <p>Method under test: {@link CharsetListProvider#allowCustomValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean CharsetListProvider.allowCustomValue()"})
  public void testCharsetListProviderAllowCustomValue() {
    // Arrange, Act and Assert
    assertFalse(new CharsetListProvider().allowCustomValue());
  }

  /**
   * Test CollationListProvider {@link CollationListProvider#allowCustomValue()}.
   *
   * <p>Method under test: {@link CollationListProvider#allowCustomValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean CollationListProvider.allowCustomValue()"})
  public void testCollationListProviderAllowCustomValue() {
    // Arrange, Act and Assert
    assertFalse(new CollationListProvider().allowCustomValue());
  }
}

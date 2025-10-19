package org.jkiss.dbeaver.model.struct;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPImage;
import org.jkiss.dbeaver.model.impl.struct.AbstractObjectType;
import org.jkiss.dbeaver.model.impl.struct.RelationalObjectType;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBSObjectTypeDiffblueTest {
  /**
   * Test {@link DBSObjectType#isCompatibleWith(DBSObjectType)}.
   *
   * <ul>
   *   <li>Given {@code DBSObject}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBSObjectType#isCompatibleWith(DBSObjectType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSObjectType.isCompatibleWith(DBSObjectType)"})
  public void testIsCompatibleWith_givenOrgJkissDbeaverModelStructDBSObject_thenReturnTrue() {
    // Arrange
    DBPImage image = mock(DBPImage.class);
    Class<DBSObject> objectClass = DBSObject.class;

    AbstractObjectType abstractObjectType =
        new AbstractObjectType(
            "Type Name", "The characteristics of someone or something", image, objectClass);

    // Act and Assert
    assertTrue(abstractObjectType.isCompatibleWith(RelationalObjectType.TYPE_CATALOG));
  }
}

package org.jkiss.dbeaver.model.impl.struct;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPImage;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AbstractObjectTypeDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link AbstractObjectType#AbstractObjectType(String, String, DBPImage, Class)}
   *   <li>{@link AbstractObjectType#toString()}
   *   <li>{@link AbstractObjectType#getDescription()}
   *   <li>{@link AbstractObjectType#getImage()}
   *   <li>{@link AbstractObjectType#getTypeClass()}
   *   <li>{@link AbstractObjectType#getTypeName()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AbstractObjectType.<init>(String, String, DBPImage, Class)",
    "String AbstractObjectType.getDescription()",
    "DBPImage AbstractObjectType.getImage()",
    "Class AbstractObjectType.getTypeClass()",
    "String AbstractObjectType.getTypeName()",
    "String AbstractObjectType.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    DBPImage image = mock(DBPImage.class);
    Class<DBSObject> objectClass = DBSObject.class;

    // Act
    AbstractObjectType actualAbstractObjectType =
        new AbstractObjectType(
            "Type Name", "The characteristics of someone or something", image, objectClass);
    String actualToStringResult = actualAbstractObjectType.toString();
    String actualDescription = actualAbstractObjectType.getDescription();
    DBPImage actualImage = actualAbstractObjectType.getImage();
    Class<? extends DBSObject> actualTypeClass = actualAbstractObjectType.getTypeClass();

    // Assert
    assertEquals("The characteristics of someone or something", actualDescription);
    assertEquals("Type Name", actualAbstractObjectType.getTypeName());
    assertEquals("Type(Type Name)", actualToStringResult);
    Class<DBSObject> expectedTypeClass = DBSObject.class;
    assertEquals(expectedTypeClass, actualTypeClass);
    assertSame(image, actualImage);
  }
}

package org.jkiss.dbeaver.model.erd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.virtual.DBVContainer;
import org.jkiss.dbeaver.model.virtual.DBVEntity;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class ERDObjectDiffblueTest {
  /**
   * Test {@link ERDObject#getObject()}.
   *
   * <p>Method under test: {@link ERDObject#getObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ERDObject.getObject()"})
  public void testGetObject() {
    // Arrange, Act and Assert
    assertEquals("Text", new ERDNote("Text").getObject());
  }

  /**
   * Test {@link ERDObject#setObject(Object)}.
   *
   * <p>Method under test: {@link ERDObject#setObject(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ERDObject.setObject(Object)"})
  public void testSetObject() {
    // Arrange
    ERDEntity erdEntity = new ERDEntity(mock(DBPDataSource.class));
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity dbvEntity = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    erdEntity.setObject(dbvEntity);

    // Assert
    assertEquals("Name", erdEntity.getName());
    assertSame(dbvEntity, erdEntity.getObject());
  }

  /**
   * Test {@link ERDObject#getUserData()}.
   *
   * <p>Method under test: {@link ERDObject#getUserData()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ERDObject.getUserData()"})
  public void testGetUserData() {
    // Arrange, Act and Assert
    assertNull(new ERDNote("Text").getUserData());
  }

  /**
   * Test {@link ERDObject#setUserData(Object)}.
   *
   * <p>Method under test: {@link ERDObject#setUserData(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ERDObject.setUserData(Object)"})
  public void testSetUserData() {
    // Arrange
    ERDNote erdNote = new ERDNote("Text");

    // Act
    erdNote.setUserData("User Data");

    // Assert
    assertEquals("User Data", erdNote.getUserData());
  }

  /**
   * Test {@link ERDObject#firePropertyChange(String, Object, Object)}.
   *
   * <ul>
   *   <li>Then calls {@link PropertyChangeListener#propertyChange(PropertyChangeEvent)}.
   * </ul>
   *
   * <p>Method under test: {@link ERDObject#firePropertyChange(String, Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ERDObject.firePropertyChange(String, Object, Object)"})
  public void testFirePropertyChange_thenCallsPropertyChange() {
    // Arrange
    PropertyChangeListener l = mock(PropertyChangeListener.class);
    doNothing().when(l).propertyChange(Mockito.<PropertyChangeEvent>any());

    ERDNote erdNote = new ERDNote("Text");
    erdNote.addPropertyChangeListener(l);

    // Act
    erdNote.firePropertyChange("Prop", "Old", "New Value");

    // Assert
    verify(l).propertyChange(isA(PropertyChangeEvent.class));
  }

  /**
   * Test {@link ERDObject#getAdapter(Class)}.
   *
   * <ul>
   *   <li>Given {@link ERDNote#ERDNote(String)} with {@code Text}.
   *   <li>When {@code Object}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ERDObject#getAdapter(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ERDObject.getAdapter(Class)"})
  public void testGetAdapter_givenERDNoteWithText_whenJavaLangObject_thenReturnNull() {
    // Arrange
    ERDNote erdNote = new ERDNote("Text");
    Class<Object> adapter = Object.class;

    // Act and Assert
    assertNull(erdNote.getAdapter(adapter));
  }
}

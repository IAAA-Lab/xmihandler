/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/xmihandler/LICENSE.txt for details.
 */

package gov.nih.nci.ncicb.xmiinout.domain;

/**
 * Represents the end of an association
 */
public interface UMLAssociationEnd extends UMLTaggableElement {

  /**
   * @return the element (i.e class) this end points to
   */
  UMLAssociable getUMLElement();

  /**
   * @return the end role name, or null if there isn't any.
   */
  String getRoleName();

  /**
   * @return the low multiplicity.
   */
  int getLowMultiplicity();

  /**
   * @return the high multiplicity, -1 represents * (or n)
   */
  int getHighMultiplicity();

  /**
   * @return true is this end is navigable
   */
  boolean isNavigable();

  /**
   * @return the visibility (scope)
   */
  UMLVisibility getVisibility();

  /**
   * @return the association this end belongs to.
   */
  UMLAssociation getOwningAssociation();


}

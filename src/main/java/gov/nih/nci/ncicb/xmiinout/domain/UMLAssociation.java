/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/xmihandler/LICENSE.txt for details.
 */

package gov.nih.nci.ncicb.xmiinout.domain;

import java.util.List;

/**
 * Represents a UML Association between UML Classes
 */
public interface UMLAssociation extends UMLTaggableElement {

  /**
   * @return a List of at least two Association Ends.
   */
  List<UMLAssociationEnd> getAssociationEnds();

  /**
   * @return the association's role name, or null if there isn't any
   */
  String getRoleName();


}

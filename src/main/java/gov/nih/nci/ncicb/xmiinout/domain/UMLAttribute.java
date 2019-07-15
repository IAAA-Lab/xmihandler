/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/xmihandler/LICENSE.txt for details.
 */

package gov.nih.nci.ncicb.xmiinout.domain;

public interface UMLAttribute extends UMLTaggableElement {

  /**
   * @return the attribute name
   */
  String getName();

  /**
   * @return the visibility (scope)
   */
  UMLVisibility getVisibility();

  /**
   * @return the attribute's datatype
   */
  UMLDatatype getDatatype();


}


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
 * Classes implement this interface to indicate that an association end can point to an object of this type.
 */
public interface UMLGeneralizable {

  String getName();

  /**
   * @return a List of all Generalizations where one end points to this class.
   */
  List<UMLGeneralization> getGeneralizations();

}

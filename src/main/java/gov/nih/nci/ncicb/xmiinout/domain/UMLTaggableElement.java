/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/xmihandler/LICENSE.txt for details.
 */

package gov.nih.nci.ncicb.xmiinout.domain;

import java.util.Collection;

/**
 * Designates an Element that holds tagged Values
 */
public interface UMLTaggableElement {

  /**
   * Retrieve all tagged value by name.
   *
   * @param name the tagged value name (or key identifier)
   */
  UMLTaggedValue getTaggedValue(String name);

  /**
   * Retrieve tagged value, case-sensitive.
   *
   * @param name       the tagged value name (or key identifier)
   * @param ignoreCase if true, will return a case insensitive match.
   */
  UMLTaggedValue getTaggedValue(String name, boolean ignoreCase);

  /**
   * Retrieve all tagged values for this element.
   */
  Collection<UMLTaggedValue> getTaggedValues();

  /**
   * Add a tagged value to this element
   *
   * @param name  the tagged value nam
   * @param value the value of the tagged value
   */
  UMLTaggedValue addTaggedValue(String name, String value);

  /**
   * remove tagged value from this element.<br>
   * If more than one tagged value exist with this name, all with be removed
   *
   * @param name the tagged value name (or key identifier)
   */
  void removeTaggedValue(String name);


}

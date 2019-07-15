/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/xmihandler/LICENSE.txt for details.
 */

package gov.nih.nci.ncicb.xmiinout.domain;

/**
 * represents a tagged value
 */
public interface UMLTaggedValue {

  /**
   * the tag's name, key, id or tag
   */
  String getName();

  /**
   * the tag's value
   */
  String getValue();

  /**
   * Modify a taggedValue's value.
   *
   * @param value the value to change to.
   */
  void setValue(String value);

}

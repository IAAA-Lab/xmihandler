/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/xmihandler/LICENSE.txt for details.
 */

package gov.nih.nci.ncicb.xmiinout.writer;

import gov.nih.nci.ncicb.xmiinout.domain.UMLDependency;
import gov.nih.nci.ncicb.xmiinout.domain.UMLTaggedValue;


public interface UMLDependencyWriter {

  UMLTaggedValue writeTaggedValue(UMLDependency dep, UMLTaggedValue taggedValue);

  void removeTaggedValue(UMLDependency dep, UMLTaggedValue taggedValue);

  void writeStereotype(UMLDependency dep, String stereotype);

  void removeStereotype(UMLDependency dep, String stereotype);

}

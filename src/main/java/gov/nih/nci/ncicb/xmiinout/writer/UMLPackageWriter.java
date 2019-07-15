/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/xmihandler/LICENSE.txt for details.
 */

package gov.nih.nci.ncicb.xmiinout.writer;

import gov.nih.nci.ncicb.xmiinout.domain.UMLPackage;
import gov.nih.nci.ncicb.xmiinout.domain.UMLTaggedValue;

public interface UMLPackageWriter {

  UMLTaggedValue writeTaggedValue(UMLPackage pkg, UMLTaggedValue taggedValue);

  void removeTaggedValue(UMLPackage pkg, UMLTaggedValue taggedValue);

}

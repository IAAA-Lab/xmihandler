/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/xmihandler/LICENSE.txt for details.
 */

package gov.nih.nci.ncicb.xmiinout.writer;

import gov.nih.nci.ncicb.xmiinout.domain.UMLOperation;
import gov.nih.nci.ncicb.xmiinout.domain.UMLTaggedValue;


public interface UMLOperationWriter {

  UMLTaggedValue writeTaggedValue(UMLOperation att, UMLTaggedValue taggedValue);

  void removeTaggedValue(UMLOperation att, UMLTaggedValue taggedValue);

}

/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/xmihandler/LICENSE.txt for details.
 */

package gov.nih.nci.ncicb.xmiinout.writer;

import gov.nih.nci.ncicb.xmiinout.domain.UMLDependency;
import gov.nih.nci.ncicb.xmiinout.domain.UMLModel;
import gov.nih.nci.ncicb.xmiinout.domain.UMLTaggedValue;

public interface UMLModelWriter {

  UMLTaggedValue writeTaggedValue(UMLModel model, UMLTaggedValue taggedValue);

  void removeTaggedValue(UMLModel model, UMLTaggedValue taggedValue);

  UMLDependency writeDependency(UMLModel model, UMLDependency dependency);
}

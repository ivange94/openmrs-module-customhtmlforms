/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.customhtmlforms.api;

import org.openmrs.annotation.Authorized;
import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.customhtmlforms.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The main service of this module, which is exposed for other modules. See
 * moduleApplicationContext.xml on how it is wired up.
 */
public interface CustomHtmlFormsService extends OpenmrsService {
	
	/**
	 * @return
	 * @throws APIException
	 */
	@Authorized(CustomHtmlFormsConfig.MODULE_PRIVILEGE)
	@Transactional(readOnly = true)
	SmearResult getSmearResultByUuid(String uuid) throws APIException;
	
	/**
	 * @return
	 * @throws APIException
	 */
	@Authorized(CustomHtmlFormsConfig.MODULE_PRIVILEGE)
	@Transactional
	SmearResult saveSmearResult(SmearResult smearResult) throws APIException;
	
	/**
	 * @param smearResult
	 * @return
	 */
	@Authorized(CustomHtmlFormsConfig.MODULE_PRIVILEGE)
	@Transactional
	SmearResult addSmearResult(SmearResult smearResult);
	
	/**
	 * @param uuid
	 * @return
	 * @throws APIException
	 */
	@Authorized
	@Transactional(readOnly = true)
	HivTestResult getHivTestResultByUuid(String uuid) throws APIException;
	
	@Authorized
	@Transactional(readOnly = true)
	List<HivTestResult> getAllHivTestResults() throws APIException;
	
	@Authorized
	@Transactional
	HivTestResult saveHivTestResult(HivTestResult hivTestResult) throws APIException;
	
	@Authorized
	@Transactional
	HivTestResult addHivTestResult(HivTestResult hivTestResult) throws APIException;
	
	/**
	 * @param uuid
	 * @return
	 * @throws APIException
	 */
	@Authorized
	@Transactional(readOnly = true)
	DstTestResult getDstTestResultByUuid(String uuid) throws APIException;
	
	@Authorized
	@Transactional(readOnly = true)
	List<DstTestResult> getAllDstTestResults() throws APIException;
	
	@Authorized
	@Transactional
	DstTestResult saveDstTestResult(DstTestResult dstTestResult) throws APIException;
	
	@Authorized
	@Transactional
	DstTestResult addDstTestResult(DstTestResult dstTestResult) throws APIException;
	
	/**
	 * @param uuid
	 * @return
	 * @throws APIException
	 */
	@Authorized
	@Transactional(readOnly = true)
	TbHivInformation getTbHivInformationByUuid(String uuid) throws APIException;
	
	@Authorized
	@Transactional(readOnly = true)
	List<TbHivInformation> getAllTbHivInformation() throws APIException;
	
	@Authorized
	@Transactional
	TbHivInformation saveTbHivInformation(TbHivInformation tbHivInformation) throws APIException;
	
	@Authorized
	@Transactional
	TbHivInformation addTbHivInformation(TbHivInformation tbHivInformation) throws APIException;
	
	/**
	 * @param uuid
	 * @return
	 * @throws APIException
	 */
	@Authorized
	@Transactional(readOnly = true)
	CultureResult getCultureResultByUuid(String uuid) throws APIException;
	
	@Authorized
	@Transactional(readOnly = true)
	List<CultureResult> getAllCultureResults() throws APIException;
	
	@Authorized
	@Transactional
	CultureResult saveCultureResult(CultureResult cultureResult) throws APIException;
	
	@Authorized
	@Transactional
	CultureResult addCultureResult(CultureResult cultureResult) throws APIException;
}

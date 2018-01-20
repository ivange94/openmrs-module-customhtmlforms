/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.customhtmlforms.api.dao;

import org.hibernate.criterion.Restrictions;
import org.openmrs.api.db.hibernate.DbSession;
import org.openmrs.api.db.hibernate.DbSessionFactory;
import org.openmrs.module.customhtmlforms.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("customhtmlforms.CustomHtmlFormsDao")
public class CustomHtmlFormsDao {
	
	@Autowired
	DbSessionFactory sessionFactory;
	
	private DbSession getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public Item getItemByUuid(String uuid) {
		return (Item) getSession().createCriteria(Item.class).add(Restrictions.eq("uuid", uuid)).uniqueResult();
	}
	
	public Item saveItem(Item item) {
		getSession().saveOrUpdate(item);
		return item;
	}
	
	public SmearResult getSmearResultByUuid(String uuid) {
		return (SmearResult) getSession().createCriteria(SmearResult.class).add(Restrictions.eq("uuid", uuid))
		        .uniqueResult();
	}
	
	public SmearResult saveSmearResult(SmearResult smearResult) {
		getSession().saveOrUpdate(smearResult);
		return smearResult;
	}
	
	public HivTestResult getHivTestResultByUuid(String uuid) {
		return (HivTestResult) getSession().createCriteria(HivTestResult.class).add(Restrictions.eq("uuid", uuid))
		        .uniqueResult();
	}
	
	public List<HivTestResult> getAllHivTestResults() {
		return (List<HivTestResult>) getSession().createCriteria(HivTestResult.class).list();
	}
	
	public HivTestResult saveHivTestResult(HivTestResult hivTestResult) {
		getSession().saveOrUpdate(hivTestResult);
		return hivTestResult;
	}
	
	public DstTestResult getDstTestResultByUuid(String uuid) {
		return (DstTestResult) getSession().createCriteria(DstTestResult.class).add(Restrictions.eq("uuid", uuid))
		        .uniqueResult();
	}
	
	public List<DstTestResult> getAllDstTestResults() {
		return (List<DstTestResult>) getSession().createCriteria(DstTestResult.class).list();
	}
	
	public DstTestResult saveDstTestResult(DstTestResult dstTestResult) {
		getSession().saveOrUpdate(dstTestResult);
		return dstTestResult;
	}
	
	public TbHivInformation getTbHivInformationByUuid(String uuid) {
		return (TbHivInformation) getSession().createCriteria(TbHivInformation.class).add(Restrictions.eq("uuid", uuid))
		        .uniqueResult();
	}
	
	public List<TbHivInformation> getAllTbInformation() {
		return (List<TbHivInformation>) getSession().createCriteria(TbHivInformation.class).list();
	}
	
	public TbHivInformation saveTbHivInformation(TbHivInformation tbHivInformation) {
		getSession().saveOrUpdate(tbHivInformation);
		return tbHivInformation;
	}
	
	public CultureResult getCultureResultByUuid(String uuid) {
		return (CultureResult) getSession().createCriteria(CultureResult.class).add(Restrictions.eq("uuid", uuid))
		        .uniqueResult();
	}
	
	public List<CultureResult> getAllCultureResults() {
		return (List<CultureResult>) getSession().createCriteria(CultureResult.class).list();
	}
	
	public CultureResult saveCultureResult(CultureResult cultureResult) {
		getSession().saveOrUpdate(cultureResult);
		return cultureResult;
	}
}

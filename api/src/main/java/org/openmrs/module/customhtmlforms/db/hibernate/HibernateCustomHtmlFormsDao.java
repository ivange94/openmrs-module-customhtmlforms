package org.openmrs.module.customhtmlforms.db.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.openmrs.api.db.hibernate.DbSession;
import org.openmrs.api.db.hibernate.DbSessionFactory;
import org.openmrs.module.customhtmlforms.*;
import org.openmrs.module.customhtmlforms.db.CustomHtmlFormsDao;

import java.util.List;

public class HibernateCustomHtmlFormsDao implements CustomHtmlFormsDao {
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public SmearResult getSmearResultByUuid(String uuid) {
		return (SmearResult) getSession().createCriteria(SmearResult.class).add(Restrictions.eq("uuid", uuid))
		        .uniqueResult();
	}
	
	@Override
	public SmearResult saveSmearResult(SmearResult smearResult) {
		getSession().saveOrUpdate(smearResult);
		return smearResult;
	}
	
	@Override
	public HivTestResult getHivTestResultByUuid(String uuid) {
		return (HivTestResult) getSession().createCriteria(HivTestResult.class).add(Restrictions.eq("uuid", uuid))
		        .uniqueResult();
	}
	
	@Override
	public List<HivTestResult> getAllHivTestResults() {
		return (List<HivTestResult>) getSession().createCriteria(HivTestResult.class).list();
	}
	
	@Override
	public HivTestResult saveHivTestResult(HivTestResult hivTestResult) {
		getSession().saveOrUpdate(hivTestResult);
		return hivTestResult;
	}
	
	@Override
	public DstTestResult getDstTestResultByUuid(String uuid) {
		return (DstTestResult) getSession().createCriteria(DstTestResult.class).add(Restrictions.eq("uuid", uuid))
		        .uniqueResult();
	}
	
	@Override
	public List<DstTestResult> getAllDstTestResults() {
		return (List<DstTestResult>) getSession().createCriteria(DstTestResult.class).list();
	}
	
	@Override
	public DstTestResult saveDstTestResult(DstTestResult dstTestResult) {
		getSession().saveOrUpdate(dstTestResult);
		return dstTestResult;
	}
	
	@Override
	public TbHivInformation getTbHivInformationByUuid(String uuid) {
		return (TbHivInformation) getSession().createCriteria(TbHivInformation.class).add(Restrictions.eq("uuid", uuid))
		        .uniqueResult();
	}
	
	@Override
	public List<TbHivInformation> getAllTbInformation() {
		return (List<TbHivInformation>) getSession().createCriteria(TbHivInformation.class).list();
	}
	
	@Override
	public TbHivInformation saveTbHivInformation(TbHivInformation tbHivInformation) {
		getSession().saveOrUpdate(tbHivInformation);
		return tbHivInformation;
	}
	
	@Override
	public CultureResult getCultureResultByUuid(String uuid) {
		return (CultureResult) getSession().createCriteria(CultureResult.class).add(Restrictions.eq("uuid", uuid))
		        .uniqueResult();
	}
	
	@Override
	public List<CultureResult> getAllCultureResults() {
		return (List<CultureResult>) getSession().createCriteria(CultureResult.class).list();
	}
	
	@Override
	public CultureResult saveCultureResult(CultureResult cultureResult) {
		getSession().saveOrUpdate(cultureResult);
		return cultureResult;
	}
}

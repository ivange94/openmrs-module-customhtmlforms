package org.openmrs.module.customhtmlforms.db;

import org.openmrs.module.customhtmlforms.*;

import java.util.List;

public interface CustomHtmlFormsDao {
	
	public SmearResult getSmearResultByUuid(String uuid);
	
	public SmearResult saveSmearResult(SmearResult smearResult);
	
	public HivTestResult getHivTestResultByUuid(String uuid);
	
	public List<HivTestResult> getAllHivTestResults();
	
	public HivTestResult saveHivTestResult(HivTestResult hivTestResult);
	
	public DstTestResult getDstTestResultByUuid(String uuid);
	
	public List<DstTestResult> getAllDstTestResults();
	
	public DstTestResult saveDstTestResult(DstTestResult dstTestResult);
	
	public TbHivInformation getTbHivInformationByUuid(String uuid);
	
	public List<TbHivInformation> getAllTbInformation();
	
	public TbHivInformation saveTbHivInformation(TbHivInformation tbHivInformation);
	
	public CultureResult getCultureResultByUuid(String uuid);
	
	public List<CultureResult> getAllCultureResults();
	
	public CultureResult saveCultureResult(CultureResult cultureResult);
}

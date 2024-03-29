package ar.com.larreta.commons;

import java.util.Collection;

import org.apache.log4j.Logger;


public interface AppConfigData extends AppObject{
	
	public String getVersion();
	public Integer getMailPort() ;
	public String getMailHost();
	public String getMailPassword();
	public String getMailUser();
	public String packagesToScan();
	public String getGeneralDateFormat();
	public String getDatabasePassword();
	public String getDatabaseUsername();
	public String getDatabaseAdminPassword();
	public String getDatabaseAdminUsername();
	public String getDatabaseDriver();
	public String getDatabaseURL();
	public String getDatabaseURLSchemma();
	public String getDatabaseURLPort();
	public String getDatabaseURLDomain();
	public String getDatabaseURLPrefix();
	public Integer getReportVirtualizerSize();
	public String getReportVirtualizerDirectory();
	public Boolean getReportPDFAutoprint();
	public Boolean getDatabaseBackup();
	public Boolean getDatabaseInitialize();
	public Collection<String> getDatabaseInitializeScripts();	
	public Boolean getDatabaseInitializeDropSchemma();
	public String getDatabaseHomeDirectory();
	public String getDatabaseDumpCommand();
	public String getDatabaseDumpCommandParam();
	public String getDatabaseDumpDirectory();
	public Integer getSessionTimeout();
	public Long getNewUserDefaultProfile();
	public String getMailTemplateRegistration();
	public String getMailTemplateConfirm();
	public Integer getMessageRefreshTime();
	public String decryptedPasswordUserMail();
	public String decryptedMailUser();
	public Logger getLog();
	public void setLog(Logger log);
	public Long statisticsStart(String mark);
	public void statisticsStop(Long id);
	public String getProperty(String key);
	public Object get(Object key);
}

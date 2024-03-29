package ar.com.larreta.commons.impl;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.larreta.commons.AppObject;
import ar.com.larreta.commons.AppObjectImpl;
import ar.com.larreta.commons.AuditInterceptor;
import ar.com.larreta.commons.domain.audit.AuditableEntity;
import ar.com.larreta.commons.services.AuditService;

@Service
public class AuditInterceptorImpl extends EmptyInterceptor implements AuditInterceptor {
	
	@Autowired
	private AuditService auditService;
	
	private AppObject appObject = new AppObjectImpl(AuditInterceptorImpl.class);
	
	@Override
	public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		super.onDelete(entity, id, state, propertyNames, types);
	}

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		if (entity instanceof AuditableEntity) {
			auditService.saveUpgrade((AuditableEntity) entity);
		}
		
		return super.onSave(entity, id, state, propertyNames, types);
	}

	@Override
	public Logger getLog() {
		return appObject.getLog();
	}

	@Override
	public void setLog(Logger log) {
		appObject.setLog(log);
	}

	@Override
	public Long statisticsStart(String mark) {
		return appObject.statisticsStart(mark);
	}

	@Override
	public void statisticsStop(Long id) {
		appObject.statisticsStop(id);
	}

}

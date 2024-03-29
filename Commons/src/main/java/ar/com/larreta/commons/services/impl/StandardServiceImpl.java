package ar.com.larreta.commons.services.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.com.larreta.commons.AppConfigData;
import ar.com.larreta.commons.AppObjectImpl;
import ar.com.larreta.commons.domain.Entity;
import ar.com.larreta.commons.exceptions.AppException;
import ar.com.larreta.commons.exceptions.NotImplementedException;
import ar.com.larreta.commons.persistence.dao.StandardDAO;
import ar.com.larreta.commons.persistence.dao.impl.CountArguments;
import ar.com.larreta.commons.persistence.dao.impl.LoadArguments;
import ar.com.larreta.commons.persistence.dao.impl.MaxArguments;
import ar.com.larreta.commons.persistence.dao.impl.StandardDAOImpl;
import ar.com.larreta.commons.services.StandardService;

@Service(StandardServiceImpl.STANDARD_SERVICE)
@Transactional
/**
 * El unico servicio que deberia ser prototype es el estandar
 * Y este deberia consumirse a travez del service config donde esta declarada la entidad que soporta
 */
public class StandardServiceImpl extends AppObjectImpl implements StandardService{
	
	public static final String STANDARD_SERVICE = "standardService";

	public static final String PERCENT = "%";
	
	@Autowired
	@Qualifier(StandardDAOImpl.STANDAR_DAO)
	protected StandardDAO dao;
	
	@Autowired
	protected AppConfigData appConfigData;
	
	//protected Class entityclass;
	
	public StandardDAO getDao() {
		return dao;
	}

	public void setDao(StandardDAO dao) {
		this.dao = dao;
	}

	/**
	 * Guarda la entidad pasada por parametro
	 * @param entity
	 */
	public void save(Entity entity){
		dao.save(entity);
	}
	
	public void update(Entity entity){
		dao.update(entity);
	}

	public void delete(Entity entity){
		dao.delete(entity);
	}
	
	/**
	 * Guarda o actualiza la entidad pasada por parametro
	 * @param entity
	 */
	public void saveOrUpdate(Entity entity){
		dao.saveOrUpdate(entity);
	}
	
	public void saveOrUpdate(Collection entities){
		dao.saveOrUpdate(entities);
	}
	
	/**
	 * Guarda la entidad pasada por parametro
	 * Solo en el caso de que no exista, evaluando el valor del campo indicado por parametro
	 * @param entity
	 * @param field
	 */
	public void saveIfNotExist(Entity entity, String field){
		if (!isExist((Entity) entity, field)){
			save(entity);
		}
	}	
	
	/**
	 * Retorna true si la entidad existe
	 * @param entity
	 * @return
	 */
	public Boolean isExist(Entity entity) {
		Boolean exist = Boolean.FALSE;
		if (entity.getId()!=null){
			exist = (getEntity(entity)!=null);
		}
		return exist;
	}

	
	/**
	 * Retonra una entidad desde la base a partir del id de la entidad pasada por parametro
	 * @param entity
	 * @return
	 */
	public Entity getEntity(Entity entity) {
		return dao.getEntity(entity.getClass(), entity.getId());
	}

	/**
	 * Retorna true si la entidad existe evaluando el campo pasado por parametro
	 * @param entity
	 * @return
	 */
	public Boolean isExist(Entity entity, String field) {
		return (getEntity(entity, field)!=null);
	}

	/**
	 * Retorna una entidad a partir del valor de la entidad en el campo indicado por parametro
	 * @param entity
	 * @param field
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public Entity getEntity(Entity entity, String field) {
		try {
			return dao.getEntity(entity.getClass(), field, PropertyUtils.getProperty(entity, field));
		} catch (Exception e) {
			getLog().error(AppException.getStackTrace(e));
		}
		return null;
	}

	
	/**
	 * Retorna todos los elementos para una determinada entidad
	 * @param entityType
	 * @return
	 */
	public Collection load(Class entityType){
		return dao.load(new LoadArguments(entityType));
	}

	public Collection load(Class entityType, Integer firstResult, Integer maxResults, Order order, Map<String, Object> filters){
		LoadArguments args = new LoadArguments(entityType);
		args.setFirstResult(firstResult);
		args.setMaxResults(maxResults);
		return dao.load(args);
	}
	
	/**
	 * Retorna todos los elementos para la entidad 
	 * @param entityType
	 * @return
	 * @throws NotImplementedException 
	 */
	/*public Collection load() throws NotImplementedException{
		return load(getEntityClass());
	}
	
	public Collection load(Integer firstResult, Integer maxResults, Order order, Map<String, Object> filters) throws NotImplementedException{
		LoadArguments args = new LoadArguments(getEntityClass());
		args.setFirstResult(firstResult);
		args.setMaxResults(maxResults);
		return dao.load(args);
	}*/
	
	protected Collection<Criterion> processRestrictions(Map<String, Object> filters){
		if ((filters!=null) && (!filters.isEmpty())){
			Collection<Criterion> criterions = new ArrayList<Criterion>();
			Iterator<String> it = filters.keySet().iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				Criterion criterion = processCriterion(key, filters.get(key));
				if (criterion!=null){
					criterions.add(criterion);
				}
			}
			return criterions;
		}
		return new ArrayList<Criterion>();
	}

	
	protected Criterion processCriterion(String key, Object object) {
		return null;
	}

	/**
	 * Retorna la cantidad de elementos de una determinada entidad
	 * @param entityType
	 * @return
	 */
	public Long count(Class entityType){
		return dao.count(new CountArguments(entityType));
	}
	
	/**
	 * Retorna la cantidad de elementos de la entidad 
	 * @param entityType
	 * @return
	 * @throws NotImplementedException 
	 */
	/*public Long count() throws NotImplementedException{
		return count(getEntityClass());
	}*/

	/*public Class getEntityClass() throws NotImplementedException {
		if (entityclass==null){
			throw new NotImplementedException();
		}
		return entityclass;
	}
	
	public void setEntityClass(Class entityclass) {
		this.entityclass = entityclass;
	}*/
	
	/**
	 * Retorna el valor maximo del id para la clase pasada por parametro
	 * @param type
	 * @return
	 */
	public Long getMaxId(Class type) {
		Long max = null;
		MaxArguments args = new MaxArguments(type, Entity.ID);
		Collection ids = dao.load(args);
		if (ids!=null){
			Iterator itIds = ids.iterator();
			if (itIds.hasNext()){
				max = (Long) itIds.next();
			}
		}
		if (max==null){
			max = new Long(0);
		}
		return max;
	}
	
}

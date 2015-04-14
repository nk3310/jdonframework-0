/*
 * Copyright 2003-2009 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package com.jdon.framework.test.repository.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import java.sql.Blob;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.jdon.annotation.Component;
import com.jdon.framework.test.Constants;
import com.jdon.framework.test.domain.UploadFile;
import com.jdon.framework.test.repository.UploadRepository;
import com.jdon.model.query.JdbcTemp;
import com.jdon.util.Debug;

@Component("uploadRepository")
public class UploadFileDaoSql implements UploadRepository {
	private final static Logger logger = Logger.getLogger(UploadFileDaoSql.class);

	protected JdbcTemp jdbcTemp;

	public UploadFileDaoSql(Constants constants) {
		try {
			Context ic = new InitialContext();
			DataSource dataSource = (DataSource) ic.lookup(constants.getJndiname());
			jdbcTemp = new JdbcTemp(dataSource);
		} catch (Exception slx) {
			logger.error(slx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jdon.framework.test.repository.dao.UploadRepository#getUploadFile
	 * (java.lang.String)
	 */
	@Override
	public UploadFile getUploadFile(String parentId) {
		String GET_ALL_ITEMS = "select OBJECTID, NAME, DESCRIPTION, DATAS, MESSAGEID, SIZE, CONTENTTYPE from UPLOAD where MESSAGEID = ?";
		Collection params = new ArrayList();
		params.add(parentId);
		UploadFile ret = null;
		Blob blob = null;
		try {
			List list = jdbcTemp.queryMultiObject(params, GET_ALL_ITEMS);
			Iterator iter = list.iterator();
			if (iter.hasNext()) {
				
				//debug by chenj begin
				logger.info("{debug by chenj} iter.hasNext()" );
				//debug by chenj end
				
				ret = new UploadFile();
				Map map = (Map) iter.next();
				
				
				ret.setId((String) map.get("OBJECTID"));
				
				//debug by chenj begin
				logger.info("{debug by chenj} map.get('OBJECTID') " + (String) map.get("OBJECTID") );
				//debug by chenj end
				
				ret.setName((String) map.get("NAME"));
				
				//debug by chenj begin
				logger.info("{debug by chenj} map.get('NAME') " + (String) map.get("NAME") );
				//debug by chenj end
				
				ret.setDescription((String) map.get("DESCRIPTION"));
				
				//debug by chenj begin
				logger.info("{debug by chenj} map.get('DESCRIPTION') " + (String) map.get("DESCRIPTION") );
				//debug by chenj end
				
				
				//ret.setData((byte[]) map.get("DATAS")); // ??? java.sql.Blob.getBytes()
				
				blob = (Blob) map.get("DATAS");
				
				byte[] datas = blob.getBytes(1, (int) blob.length());
				if (datas == null) {
					logger.warn("upload datas is null!");
				}
				ret.setData(datas);
				
				
				//debug by chenj begin
				logger.info("{debug by chenj} map.get('DATAS') " + datas.toString() ); 
				//debug by chenj end

				ret.setParentId((String) map.get("MESSAGEID"));
				
				//debug by chenj begin
				logger.info("{debug by chenj} map.get('MESSAGEID') " + (String) map.get("MESSAGEID") );
				//debug by chenj end
				
				
				ret.setSize(((Integer) map.get("SIZE")).intValue());
				
				//debug by chenj begin
				logger.info("{debug by chenj} map.get('SIZE') " + (String) map.get("SIZE").toString() ); 
				//debug by chenj end
				
				ret.setContentType((String) map.get("CONTENTTYPE"));
				
				//debug by chenj begin
				logger.info("{debug by chenj} map.get('CONTENTTYPE') " + (String) map.get("CONTENTTYPE") );
				//debug by chenj end
				
				
				//debug by chenj begin
				logger.info("{debug by chenj} ret.getId = " + ret.getId());
				logger.info("{debug by chenj} ret.getName = " + ret.getName());
				logger.info("{debug by chenj} ret.getDescription = " + ret.getDescription());
				logger.info("{debug by chenj} ret.ParentId = " + ret.getParentId());
				logger.info("{debug by chenj} ret.Size = " + ret.getSize());
				logger.info("{debug by chenj} ret.ContentType = " + ret.getContentType());
				
				//debuy by chenj end
			}
		} catch (Exception se) {
			logger.error("getAdjunct messageId=" + parentId + se);
		}
		return ret;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jdon.jivejdon.dao.UploadFileDao#createUploadFile(com.jdon.strutsutil
	 * .file.UploadFile)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jdon.framework.test.repository.dao.UploadRepository#createUploadFile
	 * (com.jdon.framework.test.domain.UploadFile)
	 */
	@Override
	public void createUploadFile(UploadFile uploadFile) {
		logger.debug("enter createUploadFile uploadId =" + uploadFile.getId());
		try {
			String ADD_SQL = "INSERT INTO upload(objectId, name, description, datas, messageId, size, creationDate, contentType) "
					+ " VALUES (?,?,?,?,?,?,?,?)";
			List queryParams = new ArrayList();
			queryParams.add(uploadFile.getId());
			queryParams.add(uploadFile.getName());
			queryParams.add(uploadFile.getDescription());
			byte[] datas = uploadFile.getData();
			if (datas == null) {
				logger.warn("upload datas is null!");
			}
			queryParams.add(datas);
			queryParams.add(uploadFile.getParentId());
			queryParams.add(new Long(uploadFile.getSize()));

			long now = System.currentTimeMillis();
			queryParams.add(now);

			queryParams.add(uploadFile.getContentType());
			
			
			
			//debug by chenj begin
			int i = 1;
			Object key = null;
			
			Iterator iter = queryParams.iterator();
			while (iter.hasNext()) {
				key = iter.next();
				if (key != null) {
					
					logger.info("{debug by chenj} parameter " + i + " = " + key.toString());
				}
				i++;
			}
     		//debug by chenj end

			this.jdbcTemp.operate(queryParams, ADD_SQL);
			

		} catch (Exception e) {
			logger.error("createUploadFile uploadId =" + uploadFile.getId() + e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jdon.jivejdon.dao.UploadFileDao#deleteUploadFile(java.lang.String)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jdon.framework.test.repository.dao.UploadRepository#deleteUploadFile
	 * (java.lang.String)
	 */
	@Override
	public void deleteUploadFile(String parentId) {
		try {
			String sql = "DELETE FROM upload WHERE messageId=?";
			List queryParams = new ArrayList();
			queryParams.add(parentId);
			jdbcTemp.operate(queryParams, sql);
		} catch (Exception e) {
			logger.error("deleteAllUploadFile parentId" + parentId + e);
		}

	}
}

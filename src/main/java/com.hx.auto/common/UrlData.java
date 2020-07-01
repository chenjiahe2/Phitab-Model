package com.hx.auto.common;

/**属性保存*/
public class UrlData {

	/**总包*/
	private String totalUrl;
	/**生成action的包路径*/
	private String[] actionUrl;
	/**生成mapper.xml路径*/
	private String[] mapperUrl;
	/**生成DAO的包路径*/
	private String[] daoUrl;
	/**生成service接口的包路径*/
	private String[] serviceUrl;
	/**生成serviceImpl接口的包路径*/
	private String[] serviceImplUrl;
	/**********************************/
	/**
	 * 一下类引入的路径需要
	 * @param totalUrl 总包，如：com.songSir
	 */
	public void  totalUrlData(String totalUrl){
		this.totalUrl = totalUrl;
	}

	/**生成action的包路径
	 * @param packRoot 根目录，如：src.main.java
	 * @param actionPack 包目录，如：com.songSir.model
	 */
	public void actionUrlData(String packRoot,String actionPack) {
		actionUrl = new String[] {packRoot,actionPack};
	}
	/**生成mapper.xml路径
	 * 
	 * @param packRoot 根目录，如：src.main.java
	 * @param mapperPack 包目录/文件目录，如：com.songSir.mapper 或者 com/songSir/mapper
	 */
	public void mapperUrlData(String packRoot,String mapperPack) {
		mapperUrl = new String[] {packRoot,mapperPack};
	}
	/**生成DAO的包路径
	 * 
	 * @param packRoot 根目录，如：src.main.java
	 * @param daoPack 包目录，如：com.songSir.dao
	 */
	public void daoUrlData(String packRoot,String daoPack) {
		daoUrl = new String[] {packRoot,daoPack};
	}
	/**生成service接口的包路径
	 * 
	 * @param packRoot 根目录，如：src.main.java
	 * @param servicePack 包目录，如：com.songSir.service
	 */
	public void serviceUrlData(String packRoot,String servicePack) {
		serviceUrl = new String[] {packRoot,servicePack};
	}
	/**生成service接口的包路径
	 * 
	 * @param packRoot 根目录，如：src.main.java
	 * @param serviceImpUrlPack 包目录，如：com.songSir.serviceImp
	 */
	public void serviceImplUrlData(String packRoot,String serviceImpUrlPack) {
		serviceImplUrl = new String[] {packRoot,serviceImpUrlPack};
	}
	/*********************************/
	public String[] getActionUrl() {
		return actionUrl;
	}
	public void setActionUrl(String[] actionUrl) {
		this.actionUrl = actionUrl;
	}
	public String[] getMapperUrl() {
		return mapperUrl;
	}
	public void setMapperUrl(String[] mapperUrl) {
		this.mapperUrl = mapperUrl;
	}
	public String[] getDaoUrl() {
		return daoUrl;
	}
	public void setDaoUrl(String[] daoUrl) {
		this.daoUrl = daoUrl;
	}
	public String[] getServiceUrl() {
		return serviceUrl;
	}
	public void setServiceUrl(String[] serviceUrl) {
		this.serviceUrl = serviceUrl;
	}
	public String[] getServiceImplUrl() {
		return serviceImplUrl;
	}
	public void setServiceImplUrl(String[] serviceImplUrl) {
		this.serviceImplUrl = serviceImplUrl;
	}

	public String getTotalUrl() {
		return totalUrl;
	}

	public void setTotalUrl(String totalUrl) {
		this.totalUrl = totalUrl;
	}
}

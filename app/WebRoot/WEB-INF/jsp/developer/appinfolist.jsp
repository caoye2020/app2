<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="common/header.jsp"%>
<script>
  var ctx="${pageContext.request.contextPath}";
</script>
<div class="clearfix"></div>
<div class="row">
	<div class="col-md-12">
		<div class="x_panel">
			<div class="x_title">
				<h2>
					APP 信息管理维护 <i class="fa fa-user"></i><small>${devUserSession.devName}
						- 您可以通过搜索或者其他的筛选项对APP的信息进行修改、删除等管理操作。^_^</small>
				</h2>
				<div class="clearfix"></div>
			</div>
			<div class="x_content">
				<form id="forms" method="post" action="${pageContext.request.contextPath}/sys/devindex/list">
					<input type="hidden" name="pageIndex" value="1" />
			    <ul>
					<li>
						<div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12">软件名称</label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<input name="appName" type="text" class="form-control col-md-7 col-xs-12" value="${appName}">
							</div>
						</div>
					</li>
					
					<li>
						<div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12">APP状态</label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<select name="status" class="form-control">
									<c:if test="${statusList != null }">
									   <option value="-1">--请选择--</option>
									   <c:forEach var="dataDictionary" items="${statusList}">
									   		<option <c:if test="${dataDictionary.valueId ==status}">selected="selected"</c:if>
									   		value="${dataDictionary.valueId}">${dataDictionary.valueName}</option>
									   </c:forEach>
									</c:if>
        						</select>
							</div>
						</div>
					</li>
					<li>
						<div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12">所属平台</label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<select name="flatform" class="form-control">
									<c:if test="${flatformList != null }">
									   <option value="-1">--请选择--</option>
									   <c:forEach var="dataDictionary" items="${flatformList}">
									   		<option <c:if test="${dataDictionary.valueId == flatform}">selected="selected"</c:if>
									   		value="${dataDictionary.valueId}">${dataDictionary.valueName}</option>
									   </c:forEach>
									</c:if>
        						</select>
							</div>
						</div>
					</li>
					<li>
						<div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12">一级分类</label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<select id="queryCategoryLevel1" name="yiji" class="form-control">
									<c:if test="${categoryLevel1List != null }">
									   <option value="-1">--请选择--</option>
									   <c:forEach var="appCategory" items="${categoryLevel1List}">
									   		<option <c:if test="${appCategory.id == yiji }">selected="selected"</c:if>
									   		value="${appCategory.id}">${appCategory.categoryName}</option>
									   </c:forEach>
									</c:if>
        						</select>
							</div>
						</div>
					</li>
					<li>
						<div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12">二级分类</label>
							<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="hidden" name="categorylevel2list" id="categorylevel2list"/>
        						<select name="erji" id="queryCategoryLevel2" class="form-control">
        							<c:if test="${list2 != null }">
									   <option value="-1">--请选择--</option>
									   <c:forEach var="appCategory" items="${list2}">
									   		<option <c:if test="${appCategory.id==erji}">selected="selected"</c:if>
									   		value="${appCategory.id}">${appCategory.categoryName}</option>
									   </c:forEach>
									</c:if>
        						</select>
							</div>
						</div>
					</li>
					<li>
						<div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12">三级分类</label>
							<div class="col-md-6 col-sm-6 col-xs-12">
        						<select name="sanji" id="queryCategoryLevel3" class="form-control">
        							<c:if test="${list3 != null }">
									   <option value="-1">--请选择--</option>
									   <c:forEach var="appCategory" items="${list3}">
									   		<option <c:if test="${appCategory.id == sanji }">selected="selected"</c:if>
									   		value="${appCategory.id}">${appCategory.categoryName}</option>
									   </c:forEach>
									</c:if>
        						</select>
							</div>
						</div>
					</li>
					<li><button id="btn" type="submit" class="btn btn-primary"> 查 &nbsp;&nbsp;&nbsp;&nbsp;询 </button></li>
				</ul>
			</form>
		</div>
	</div>
</div>
<div class="col-md-12 col-sm-12 col-xs-12">
	<div class="x_panel">
		<div class="x_content">
			<p class="text-muted font-13 m-b-30"></p>
			<div id="datatable-responsive_wrapper"
				class="dataTables_wrapper form-inline dt-bootstrap no-footer">
				<div class="row">
					<div class="col-sm-12">
					<a href="${pageContext.request.contextPath}/sys/devindex/appinfoadd" class="btn btn-success btn-sm">新增APP基础信息</a>
						<table id="datatable-responsive" class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed"
							cellspacing="0" width="100%" role="grid" aria-describedby="datatable-responsive_info" style="width: 100%;">
							<thead>
								<tr role="row">
									<th class="sorting_asc" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										aria-label="First name: activate to sort column descending"
										aria-sort="ascending">软件名称</th>
									<th class="sorting" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										aria-label="Last name: activate to sort column ascending">
										APK名称</th>
									<th class="sorting" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										aria-label="Last name: activate to sort column ascending">
										软件大小(单位:M)</th>
									<th class="sorting" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										aria-label="Last name: activate to sort column ascending">
										所属平台</th>
									<th class="sorting" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										aria-label="Last name: activate to sort column ascending">
										所属分类(一级分类、二级分类、三级分类)</th>
									<th class="sorting" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										aria-label="Last name: activate to sort column ascending">
										状态</th>
									<th class="sorting" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										aria-label="Last name: activate to sort column ascending">
										下载次数</th>
									<th class="sorting" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										aria-label="Last name: activate to sort column ascending">
										最新版本号</th>
									<th class="sorting" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										style="width: 124px;"
										aria-label="Last name: activate to sort column ascending">
										操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="appInfo" items="${page.listA}" varStatus="status">
									<tr role="row" class="odd">
										<td tabindex="0" class="sorting_1">${appInfo.softwareName}</td>
										<td>${appInfo.APKName }</td>
										<td>${appInfo.softwareSize }</td>
										<td>${appInfo.flatformName }</td>
										<td>${appInfo.categoryLevel1Name } -> ${appInfo.categoryLevel2Name } -> ${appInfo.categoryLevel3Name }</td>
										<td><span id="appInfoStatus${appInfo.id}">${appInfo.statusName }</span></td>
										<td>${appInfo.downloads }</td>
										<td>${appInfo.versionNo }</td>
										<td>
										<div class="btn-group">
                      <button type="button" class="btn btn-danger">点击操作</button>
                      <button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                        <span class="caret"></span>
                        <span class="sr-only">Toggle Dropdown</span>
                      </button>
                      <ul class="dropdown-menu" role="menu">
                        <li>
                        	<c:choose>
											<c:when test="${appInfo.status == 2 || appInfo.status == 5}">
												<a class="saleSwichOpen" saleSwitch="open" appinfoid=${appInfo.id }  appsoftwarename=${appInfo.softwareName } data-toggle="tooltip" data-placement="top" title="" data-original-title="恭喜您，您的审核已经通过，您可以点击上架发布您的APP">上架</a>
											</c:when>
											<c:when test="${appInfo.status == 4}">
												<a class="saleSwichClose" saleSwitch="close" appinfoid=${appInfo.id }  appsoftwarename=${appInfo.softwareName } data-toggle="tooltip" data-placement="top" title="" data-original-title="您可以点击下架来停止发布您的APP，市场将不提供APP的下载">下架</a>
											</c:when>
										</c:choose>
                        </li>
                        <li><a class="addVersion" href="${pageContext.request.contextPath}/sys/devindex/addversionadd?id=${appInfo.id}" appinfoid="${appInfo.id }" data-toggle="tooltip" data-placement="top" title="" data-original-title="新增APP版本信息">新增版本</a>
                        </li>
                        <li><a class="modifyVersion" 
											appinfoid="${appInfo.id }"  versionid="${appInfo.versionId }" status="${appInfo.status }" 
											statusname="${appInfo.statusName }"											
											data-toggle="tooltip" data-placement="top" title="" data-original-title="修改APP最新版本信息">修改版本</a>
                        </li>
                        <li><a  class="modifyAppInfo" 
											appinfoid="${appInfo.id }" status="${appInfo.status }" statusname="${appInfo.statusName }"
											data-toggle="tooltip" data-placement="top" title="" data-original-title="修改APP基础信息">修改</a></li>
                        <li><a  class="viewApp" appinfoid=${appInfo.id } versionNo=${appInfo }  data-toggle="tooltip" data-placement="top" title="" data-original-title="查看APP基础信息以及全部版本信息">查看</a></li>
						<li><a  class="deleteApp" appinfoid=${appInfo.id }  appsoftwarename=${appInfo.softwareName } data-toggle="tooltip" data-placement="top" title="" data-original-title="删除APP基础信息以及全部版本信息">删除</a></li>
                      </ul>
                    </div>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-5">
						<div class="dataTables_info" id="datatable-responsive_info"
							role="status" aria-live="polite">共${page.totalCount }条记录
							${page.currPageNo }/${page.totalPageCount }页</div>
					</div>
					<div class="col-sm-7">
						<div class="dataTables_paginate paging_simple_numbers"
							id="datatable-responsive_paginate">
							<ul class="pagination">
								<c:if test="${page.currPageNo > 1}">
									<li class="paginate_button previous"><a
										href="${pageContext.request.contextPath}/sys/devindex/list?currPageNo=1&appName=${appName}&status=${status}&flatform=${flatform}&yiji=${yiji}&erji=${erji}&sanji=${sanji}"
										aria-controls="datatable-responsive" data-dt-idx="0"
										tabindex="0">首页</a>
									</li>
									<li class="paginate_button "><a
										href="${pageContext.request.contextPath}/sys/devindex/list?currPageNo=${page.currPageNo-1}&appName=${appName}&status=${status}&flatform=${flatform}&yiji=${yiji}&erji=${erji}&sanji=${sanji}"
										aria-controls="datatable-responsive" data-dt-idx="1"
										tabindex="0">上一页</a>
									</li>
								</c:if>
								<c:if test="${page.currPageNo < page.totalPageCount }">
									<li class="paginate_button "><a
										href="${pageContext.request.contextPath}/sys/devindex/list?currPageNo=${page.currPageNo+1}&appName=${appName}&status=${status}&flatform=${flatform}&yiji=${yiji}&erji=${erji}&sanji=${sanji}"
										aria-controls="datatable-responsive" data-dt-idx="1"
										tabindex="0">下一页</a>
									</li>
									<li class="paginate_button next"><a
										href="${pageContext.request.contextPath}/sys/devindex/list?currPageNo=${page.totalPageCount}&appName=${appName}&status=${status}&flatform=${flatform}&yiji=${yiji}&erji=${erji}&sanji=${sanji}"
										aria-controls="datatable-responsive" data-dt-idx="7"
										tabindex="0">最后一页</a>
									</li>
								</c:if>
							</ul>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
</div>
</div>
<%@include file="common/footer.jsp"%>
<script src="${pageContext.request.contextPath }/statics/localjs/rollpage.js"></script>
<script src="${pageContext.request.contextPath }/statics/localjs/appinfolist.js"></script>
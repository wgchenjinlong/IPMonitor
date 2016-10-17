<#import "../layout.ftl" as l>
<#import "../lib.ftl" as lib>
<#assign header>
</#assign>
<#assign footer>
<script src="js/monitor/monitor.js"></script>
</#assign>
<@l.layout title="IP监控" header=header footer=footer>
<h2 class="sub-header">监控列表</h2>
<div class="text-right btn-toolbar">
    <button type="button" class="btn btn-default" data-toggle="modal" data-target="#addModal">
        <i class="glyphicon glyphicon-plus"></i> 添加
    </button>
</div>
<div class="table-responsive">
    <table class="table table-hover" id="monitor-table">
        <thead>
        <tr>
            <th>序号</th>
            <th>IP地址</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
            <#list ipInfos as ipInfo>
            <tr class="${ipInfo.status.color!}">
                <td>${ipInfo?counter}</td>
                <td class="ip-address" data-address="${ipInfo.ipAddress!}">${ipInfo.ipAddress!}</td>
                <td class="status">${ipInfo.status.statusName!}</td>
                <td> <a href="#" data-id="${(ipInfo.id)!}" data-toggle="modal"
                        data-target="#deleteModal">
                    删除</a></td>
            </tr>
            </#list>
        </tbody>
    </table>
</div>
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="addModalLabel" aria-hidden="true"
     data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title" id="addModalLabel">添加IP</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="col-md-3 control-label asterisk" for="ipAddr">IP地址</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" id="ipAddr">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="name">名称</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" id="name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="commit">备注</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" id="commit">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="save">保存</button>
            </div>
        </div>
    </div>
</div>
    <@lib.deleteDialog baseUrl="/monitor/delete"/>
</@l.layout>
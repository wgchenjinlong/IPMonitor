<#import "../layout.ftl" as l>
<#assign header>
</#assign>
<#assign footer>
<script src="js/monitor/monitor.js"></script>
</#assign>
<@l.layout title="IP监控" header=header footer=footer>
<h2 class="sub-header">监控列表</h2>
<div class="text-right btn-toolbar">
    <button type="button" class="btn btn-default">
        <i class="glyphicon glyphicon-plus"></i> 添加
    </button>
</div>
<div class="table-responsive">
    <table class="table table-bordered table-hover" id="monitor-table">
        <thead>
        <tr>
            <th>序号</th>
            <th>IP地址</th>
            <th>状态</th>
        </tr>
        </thead>
        <tbody>
        <#list ipInfos as ipInfo>
            <tr class="${ipInfo.status.color!}">
                <td>${ipInfo?counter}</td>
                <td class="ip-address" data-address="${ipInfo.ipAddress!}">${ipInfo.ipAddress!}</td>
                <td class="status">${ipInfo.status.statusName!}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>
</@l.layout>
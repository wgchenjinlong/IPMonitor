<#import "../layout.ftl" as l>
<#assign header>
</#assign>
<#assign footer>
<script src="js/monitor/monitor.js"></script>
</#assign>
<@l.layout title="IP监控" header=header footer=footer>
<h2 class="sub-header">Section title</h2>
<div class="table-responsive">
    <table class="table table-striped" id="monitor-table">
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
                <td>${ipInfo.ipAddress!}</td>
                <td>${ipInfo.status.statusName!}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>
</@l.layout>
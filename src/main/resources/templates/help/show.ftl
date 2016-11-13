<#import "../layout.ftl" as l>
<#import "../lib.ftl" as lib>
<#assign header>
</#assign>
<#assign footer>
</#assign>
<@l.layout title="帮助" header=header footer=footer>
<h2 class="sub-header">帮助详情</h2>
<div class="panel panel-default">
    <div class="panel-heading">${help.question!}</div>
    <div class="panel-body">
        <pre>${help.answer!?html}</pre>
    </div>
</div>
</@l.layout>